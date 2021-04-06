package io.zipcoder;

import java.util.ArrayList;

public class MonkeyTypewriter {
    public final int MAXTHREADS = 5;
    public int max = 0;
    public String introduction = "It was the best of times,\n" +
            "it was the blurst of times,\n" +
            "it was the age of wisdom,\n" +
            "it was the age of foolishness,\n" +
            "it was the epoch of belief,\n" +
            "it was the epoch of incredulity,\n" +
            "it was the season of Light,\n" +
            "it was the season of Darkness,\n" +
            "it was the spring of hope,\n" +
            "it was the winter of despair,\n" +
            "we had everything before us,\n" +
            "we had nothing before us,\n" +
            "we were all going direct to Heaven,\n" +
            "we were all going direct the other way--\n" +
            "in short, the period was so far like the present period, that some of\n" +
            "its noisiest authorities insisted on its being received, for good or for\n" +
            "evil, in the superlative degree of comparison only. ";

    public static void main(String[] args) {

        // Do all of the Monkey / Thread building here
        // For each Copier(one safe and one unsafe), create and start 5 monkeys copying the introduction to
        // A Tale Of Two Cities.

        MonkeyTypewriter typewriter = new MonkeyTypewriter();
        typewriter.runThreads();

        // This wait is here because main is still a thread and we want the main method to print the finished copies
        // after enough time has passed.
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("MAIN INTERRUPTED");
        }

        // Print out the copied versions here.
        //System.out.println(unsafeCopier.copied);
        //System.out.println(safeCopier.copied);
    }

    private void runThreads() {
        for (max = MAXTHREADS; max <= MAXTHREADS; max++) {
            UnsafeCopier unsafeCopier = new UnsafeCopier(introduction);
            unsafeThreadListMaker(unsafeCopier);

            try{
                Thread.sleep(100);
                System.out.println(unsafeCopier.copied);
                System.out.printf("UNSAFE (%d) diff: (%d)", max, introduction.length()-unsafeCopier.copied.length());
                if (unsafeCopier.copied.equals(introduction)) {
                    System.out.println("UNSAFE passes");
                } else {
                    System.out.println("UNSAFE fails");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean runSafe = true;
            if (runSafe) {
                SafeCopier safeCopier = new SafeCopier(introduction);
                this.safeThreadListMaker(safeCopier);
                System.out.println(safeCopier.copied);
                if (safeCopier.copied.equals(introduction)) {
                    System.out.println("SUCCESS");
                } else {
                    System.out.println("FALIED");
                }
            }
        }

    }

    private void safeThreadListMaker(SafeCopier safeCopier) {
        for (int i = 0; i < 5; i++) {
            new Thread(safeCopier).start();
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void unsafeThreadListMaker(UnsafeCopier unsafe) {
        ArrayList<Thread> unsafeThreads = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            unsafeThreads.add(new Thread(unsafe));
        }
        for (int i = 0; i < max; i++) {
            unsafeThreads.get(i).start();
        }
    }
}