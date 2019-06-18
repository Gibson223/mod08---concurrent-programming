package block6.cp;

public interface FLThread {

    public Object read(Integer addr);
    public void write(Integer addr, Object val);
    public void commit();
    
}