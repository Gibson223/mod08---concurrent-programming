package block4.cp;


public class LeftRightDeadlock {
    public LeftRightDeadlock() {
        left = new Object();
        right = new Object();
    }

    private int num;
    private final Object left;
    private final Object right;
    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                this.num += 1;            }        }    }
    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                this.num += 1;            }        }    } }
