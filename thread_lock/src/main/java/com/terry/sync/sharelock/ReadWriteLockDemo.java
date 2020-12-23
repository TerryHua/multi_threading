package com.terry.sync.sharelock;


public class ReadWriteLockDemo {

    public static void main(String[] args) {
        ShareLockDemo myCache = new ShareLockDemo();

        for (int i = 1; i <= 5; i++) {
            final int ii = i;
            new Thread(() -> myCache.put(ii + "", ii + "")
                    , String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int ii = i;
            new Thread(() -> myCache.get(ii + "")
                    , String.valueOf(i)).start();
        }
    }
}