package block6.cp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Helper implements FLThread {

    private Integer cntr, start;
    private Integer[] dirty;
    private Object[] memory;
    private Lock ttaslock;
    private Lock mcslock;
    private HashSet<Integer> readSet = new HashSet<>();
    private HashMap<Integer, Object> writeMap = new HashMap<>();

    public Helper (Integer cntr, Integer[] dirty, Object[] memory, Lock ttaslock, Lock mcslock) {
        this.cntr = cntr;
        this.dirty = dirty;
        this.memory = memory;
        this.ttaslock = ttaslock;
        this.mcslock = mcslock;
    }

    public void start() {
        start = cntr & ~1;
    }

    public Object read(Integer addr) {
        if(writeMap.containsKey(addr)) {
            return writeMap.get(addr);
        }
        Object val = memory[addr];
        if (dirty[addr] > start) {
            abort();
        }
        readSet.add(addr);
        return val;
    }
    
    public void write(Integer addr, Object val) {
        if (dirty[addr] > start) {
            abort();
        }
        writeMap.put(addr, val);
    }
    
    public boolean validate() {
        if (cntr <= start) {
            return true;
        }
        HashSet<Integer> union = new HashSet<>(readSet);
        union.addAll(writeMap.keySet());
        for (Integer addr : union) {
            if (dirty[addr] > start) {
                return false;
            }
        }
        return true;
    }

    public void commit() {
        if (writeMap.keySet().isEmpty()) {
            return;
        }
        mcslock.lock();
        ttaslock.lock();
        if (!validate()) {
            ttaslock.unlock();
            mcslock.unlock();
            abort();
        }
        cntr++;
        for (Integer addr : writeMap.keySet()) {
            dirty[addr] = cntr;
            memory[addr] = writeMap.get(addr);
        }
        ttaslock.unlock();
        mcslock.unlock();
    }

    public void abort() {
        readSet.clear();
        writeMap.clear();
        start();
    }

}