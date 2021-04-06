package io.zipcoder;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * Modify the run function so that the monkeys each grab the next word and write it to the copy.
 */
public class UnsafeCopier extends Copier {

    private boolean _debug = true;

    public UnsafeCopier(String toCopy) {
        super(toCopy);
    }

    public void run() {
        while (stringIterator.hasNext()) {
            try {
                sleep(100);
                if (stringIterator.hasNext()) {
                    copied += stringIterator.next() + " ";
                    if (_debug) System.out.println(currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
