package com.fun.concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fun
 * @date 2017/4/12.
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {

        ReentrantLockDemo test = new ReentrantLockDemo();

        Account fromAcct = test.newAccount(4000);
        Account toAcct = test.newAccount(1000);

        test.transferMoney(fromAcct,toAcct,1000L,20000,TimeUnit.NANOSECONDS);

        System.out.println("fromAccount balance=" + fromAcct.getBalance() + "\ntoAccount balance=" + toAcct.getBalance());

    }

    // 转账示例
    public boolean transferMoney(Account fromAcct,
                                 Account toAcct,
                                 long amount,
                                 long timeout,
                                 TimeUnit unit) {
        long stopTime = System.nanoTime() + unit.toNanos(timeout); // 超时时间
        while (true) {
            if (fromAcct.lock.tryLock()) {
                try {
                    if (toAcct.lock.tryLock()) {
                        try {
                            boolean rs = false;
                            if (fromAcct.debit(amount) ){
                                rs = toAcct.credit(amount);
                            }
                            return rs;
                        } finally {
                            toAcct.lock.unlock();
                        }
                    }
                } finally {
                    fromAcct.lock.unlock();
                }
            }
            if (System.nanoTime() > stopTime) { // 如果已经超时了，就直接返回，提前结束任务
                return false;
            }
            try {
                long x = new Random().nextInt(50) + 10;
                Thread.sleep(timeout/x ); // 过一会儿再尝试下一次获取锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected class Account {
        private Lock lock = new ReentrantLock();

        private long balance;

        public boolean debit(long amount) {
            if (balance < amount) {
                return false;
            }
            balance = balance - amount;
            return true;
        }

        public boolean credit(long amount) {
            balance = balance + amount;
            return true;
        }

        public Lock getLock() {
            return lock;
        }

        public void setLock(Lock lock) {
            this.lock = lock;
        }

        public long getBalance() {
            return balance;
        }

        public void setBalance(long balance) {
            this.balance = balance;
        }
    }

    public Account newAccount(long balance) {
        Account account = new Account();
        if (balance > 0) {
            account.setBalance(balance);
        } else {
            account.setBalance(0);
        }
        return account;
    }
}
