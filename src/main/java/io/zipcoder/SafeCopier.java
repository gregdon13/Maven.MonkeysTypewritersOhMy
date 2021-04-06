package io.zipcoder;

/**
 * Make this extend the Copier like `UnsafeCopier`, except use locks to make sure that the actual intro gets printed
 * correctly every time.  Make the run method thread safe.
 */
public class SafeCopier extends Copier{

    public SafeCopier(String toCopy) {
        super(toCopy);
    }

    public void run() {
        synchronized (stringIterator) {
            while (stringIterator.hasNext()) {
                stringIterator.notify();
                copied = copied + stringIterator.next() + " ";
                try {
                    stringIterator.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
