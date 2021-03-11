package com.fun.concurrent.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest2 {

    public static void main(String[] args) {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        /*
         * 写锁未释放可以加写锁（线程相同，重入），
         * 写锁未释放可以加读锁 （线程相同，类似重入，但是锁类型不同， 线程不同则失败）
         */
        testWriteRead(lock);

        System.out.println("=========================");

        /*
         * 读锁未释放加写锁（加锁失败）
         * 读锁未释放可以加读锁 （共享锁）
         */
        testReadWrite(lock);

    }

    public static void testWriteRead(ReentrantReadWriteLock lock) {
        lock.writeLock().lock();
        System.out.println("write lock...");

        lock.readLock().lock();
        System.out.println("read lock...");

        lock.readLock().unlock();
        System.out.println("release read lock...");

        lock.writeLock().unlock();
        System.out.println("release write lock...");


    }

    public static void testReadWrite(ReentrantReadWriteLock lock) {
        lock.readLock().lock();
        System.out.println("read lock...");

        lock.writeLock().lock();
        System.out.println("write lock...");

        lock.writeLock().unlock();
        System.out.println("release write lock...");

        lock.readLock().unlock();
        System.out.println("release read lock...");



    }

}
