package com.terry.sync.safe;

import com.terry.sync.safe.CountObject;

public class SynchronizedDemo {


    public static void main(String[] args) {

        CountObject countObject = new CountObject();
        Thread thread1 = new Thread(countObject);
        Thread thread2 = new Thread(countObject);

        thread1.start();
        thread2.start();

    }

}
