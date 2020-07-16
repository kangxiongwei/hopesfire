package com.kxw.hopesfire.basic.util;

import java.util.concurrent.*;

/**
 * 线程池工具类
 *
 * @author kangxiongwei
 * @date 2020-07-14 16:43
 */
public final class ThreadPoolUtil {

    private static ExecutorService threadPoolExecutor;

    static {
        int processors = Runtime.getRuntime().availableProcessors();
        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        int corePoolSize = processors * 2;
        int maxPoolSize = processors * 2;
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 0, TimeUnit.SECONDS, queue, threadFactory);
    }

    /**
     * 提交某个线程
     *
     * @param task
     */
    public static void commit(Runnable task) {
        threadPoolExecutor.execute(task);
    }

}
