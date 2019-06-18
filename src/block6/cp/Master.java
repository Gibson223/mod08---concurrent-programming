package block6.cp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Master implements FLThread{
    
    private Integer cntr;
    private Integer[] dirty;
    private Object[] memory;
    private Lock ttaslock;

    public Master(Integer cntr, Integer[] dirty, Object[] memory, ReentrantLock ttaslock) {
        this.cntr = cntr;
        this.dirty = dirty;
        this.memory = memory;
        this.ttaslock = ttaslock;
    }

    public Object read(Integer addr) {
        return memory[addr];
    }

    public void write(Integer addr, Object val) {
        if ((cntr & 0x01) != 0) {
            cntr++;
        }
        dirty[addr % dirty.length] = cntr;
        memory[addr] = val;
    }

    public void commit() {
        if ((cntr & 0x01) != 0) {
            cntr++;
        }
        ttaslock.unlock();
    }

}