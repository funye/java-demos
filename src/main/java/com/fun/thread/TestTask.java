package com.fun.thread;

/**
 * 实现runnable接口的任务类
 *
 * @author fun
 * @version v1.0.0
 * @create 2017-03-13 21:46
 */
public class TestTask implements Runnable {

    private int taskId;
    volatile private int count; // 可以做共享变量

    public TestTask(int taskId,int count) {
        this.taskId = taskId;
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("taskId is:"+taskId+" , count is:"+count);
        try {
            System.out.println("threadId: "+Thread.currentThread().getId()+
                    ", threadName: "+Thread.currentThread().getName()+
                    ",isDaemon " + Thread.currentThread().isDaemon());
            Thread.sleep(200);
            count--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
