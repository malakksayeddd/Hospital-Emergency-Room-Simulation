
public class Semaphore {

    private int value;

    public Semaphore(int initialValue) {
        if (initialValue < 0) {
            throw new IllegalArgumentException("value must be >= 0");
        }
        this.value = initialValue;
    }

    public synchronized void P() throws InterruptedException {
        while (value == 0) {
            wait();
        }
        value--;
    }

 
    public synchronized void V() {
        value++;
        notify();
    }

  
    public synchronized boolean tryP(long timeoutMs)
            throws InterruptedException {

        long deadline = System.currentTimeMillis() + timeoutMs;

        while (value == 0) {
            long remainingMs = deadline - System.currentTimeMillis();

            if (remainingMs <= 0) {
                return false;
            }

            wait(remainingMs);
        }

        value--;
        return true;
    }

    public synchronized int availableValue() {
        return value;
    }
}
