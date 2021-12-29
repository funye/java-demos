package com.fun.ratelimit;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import com.google.common.util.concurrent.SimpleTimeLimiter;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GuavaRetrying {

    public static void main(String[] args) throws ExecutionException, RetryException {

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfException()
                .retryIfResult(Predicates.equalTo(false))
//                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))
                .withAttemptTimeLimiter(new AttemptTimeLimiter<Boolean>() {
                    @Override
                    public Boolean call(Callable<Boolean> callable) throws Exception {
                        System.out.println("call AttemptTimeLimiter ");
                        return SimpleTimeLimiter.create(Executors.newFixedThreadPool(1))
                                .callWithTimeout(callable, 2, TimeUnit.SECONDS);
                    }
                })  // 设置重试动作执行的超时时间
                .withWaitStrategy(WaitStrategies.fixedWait(3, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        System.out.println("this is try count=" + attempt.getAttemptNumber());
                        System.out.println("deply=" + attempt.getDelaySinceFirstAttempt());
                    }
                })
                .build();

        AtomicInteger count = new AtomicInteger();
        boolean rs = retryer.call(() -> {
            System.out.println("call this method");
            count.getAndIncrement();
//            if (count.get() == 4) {
//                return true;
//            }
            if (count.get() == 1) {
                TimeUnit.SECONDS.sleep(4);
            }
            return false;
        });
        System.out.println(rs);

    }
}
