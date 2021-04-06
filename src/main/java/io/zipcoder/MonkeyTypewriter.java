package io.zipcoder;

public class MonkeyTypewriter {
    public static void main(String[] args) {
        String introduction = "It was the best of times,\n" +
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
                "evil, in the superlative degree of comparison only.";

        // Do all of the Monkey / Thread building here
        // For each Copier(one safe and one unsafe), create and start 5 monkeys copying the introduction to
        // A Tale Of Two Cities.
        UnsafeCopier unsafeCopier = new UnsafeCopier(introduction);
        Runnable runnable = () -> unsafeCopier.run();
        Thread threadOne = new Thread(runnable);
        Thread threadTwo = new Thread(runnable);
        Thread threadThree = new Thread(runnable);
        Thread threadFour = new Thread(runnable);
        Thread threadFive = new Thread(runnable);
        threadOne.start();
        threadTwo.start();
        threadThree.start();
        threadFour.start();
        threadFive.start();

        SafeCopier safeCopier = new SafeCopier(introduction);
        Runnable runnableSafe = () -> safeCopier.run();
        Thread monkeyOne = new Thread(runnableSafe);
        Thread monkeyTwo = new Thread(runnableSafe);
        Thread monkeyThree = new Thread(runnableSafe);
        Thread monkeyFour = new Thread(runnableSafe);
        Thread monkeyFive = new Thread(runnableSafe);
        monkeyOne.start();
        monkeyTwo.start();
        monkeyThree.start();
        monkeyFour.start();
        monkeyFive.start();

        // This wait is here because main is still a thread and we want the main method to print the finished copies
        // after enough time has passed.
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("MAIN INTERRUPTED");
        }

        // Print out the copied versions here.
        System.out.println(unsafeCopier.copied);
        System.out.println(safeCopier.copied);
    }
}