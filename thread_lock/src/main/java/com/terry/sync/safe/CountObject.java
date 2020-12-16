package com.terry.sync.safe;

public class CountObject implements Runnable {

    /**
     * @param args
     */
    private static int count = 0;


    public synchronized void run() {

        for (int i = 0; i < 100000; i++) {
            count++;
            System.out.println("count=" + count);

        }


    }
}
