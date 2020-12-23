package com.terry.sync.sharelock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockDemo
 * @Description 独占锁（写锁），共享锁（读锁），互斥锁
 * <p>
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源时，应该可以同时进行
 * 但是如果有一个线程尝试去写共享资源时，就不应该有其他的线程去读或者写
 * 小总结： 读-读 能共存
 * 读-写 不能共存
 * 写-写 不能共存
 **/
class ShareLockDemo {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入: " + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成 ");
        } catch (Exception e) {
        } finally {
            rwLock.writeLock().unlock();
        }

    }

    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t 读取完成: " + map.get(key));
        } catch (Exception e) {
        } finally {
            rwLock.readLock().unlock();
        }
    }


    public void clear() {
        rwLock.writeLock().lock();
        try {

            System.out.println(Thread.currentThread().getName() + "\t 正在清空");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.clear();
            System.out.println(Thread.currentThread().getName() + "\t 清空完成");
        } catch (Exception e) {

        } finally {
            rwLock.writeLock().unlock();
        }
    }

}
