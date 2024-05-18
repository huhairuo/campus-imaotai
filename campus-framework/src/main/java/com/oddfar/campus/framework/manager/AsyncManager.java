package com.oddfar.campus.framework.manager;

import com.oddfar.campus.common.utils.SpringUtils;
import com.oddfar.campus.common.utils.Threads;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务管理器
 */
public class AsyncManager {
    /**
     * 异步操作任务调度线程池
     */
    private ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

    /**
     * 单例模式
     */
    private AsyncManager() {
    }

    private static AsyncManager me = new AsyncManager();

    public static AsyncManager me() {
        return me;
    }

    /**
     * 执行任务
     *
     * @param task 任务
     */
    public void execute(TimerTask task) {
        // 生成1到10之间的随机数，作为额外的延迟时间
        int additionalDelay = ThreadLocalRandom.current().nextInt(1, 11);
        // 让线程休眠3秒
        executor.schedule(task, 3, TimeUnit.SECONDS);
        // 让线程休眠额外的随机生成的延迟时间
        executor.schedule(task, additionalDelay, TimeUnit.SECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        Threads.shutdownAndAwaitTermination(executor);
    }
}
