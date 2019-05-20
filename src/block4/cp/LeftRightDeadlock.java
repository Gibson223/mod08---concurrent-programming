package block4.cp;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class LeftRightDeadlock {
    public LeftRightDeadlock() {
        left = new Object();
        right = new Object();
    }

    public int num;
    private final Object left;
    private final Object right;
    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                for (int i=0; i < 1000 ; i++) {
                    i++;
                }
//                Thread.sleep(1000);
                this.num += 1;            }        }    }

    public void rightLeft() throws InterruptedException {
        synchronized (right) {
            synchronized (left) {
                Thread.sleep(1000);
                this.num += 1;            }        }    } }
