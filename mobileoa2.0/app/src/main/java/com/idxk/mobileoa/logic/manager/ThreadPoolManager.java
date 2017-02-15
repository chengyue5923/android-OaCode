package com.idxk.mobileoa.logic.manager;

/**
 * Created by lenovo on 2015/4/28.
 */


import android.os.Process;
import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by linxi on 14-11-4.
 */
public class ThreadPoolManager {


    private static int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static int KEEP_ALIVE = 1;
    private static ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTaskCreate #" + mCount.getAndIncrement());
        }
    };
    BlockingQueue<Runnable> sPoolWorkQueue;
    ThreadPoolExecutor threadPoolExecutor;
    int size;

    public ThreadPoolManager(int size) {
        this.size = size;
        sPoolWorkQueue =
                new LinkedBlockingQueue<Runnable>(size);
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
                TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);

        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
    }

    public ThreadPoolManager() {
        sPoolWorkQueue =
                new LinkedBlockingQueue<Runnable>(10);
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
                TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);

        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @param thread
     */

    public void addTask(Thread thread) {
        threadPoolExecutor.execute(thread);

    }

    /**
     * 当设置了 qun的数量的是后
     *
     * @return
     */
    public synchronized boolean isFinishAllTask() {

        return size == (threadPoolExecutor.getCompletedTaskCount() + 1);

    }

    /**
     * 当设置了 qun的数量的是后
     *
     * @return
     */
    public synchronized boolean isFinishAllTask1() {

        Log.e("--数量-", String.valueOf(threadPoolExecutor.getActiveCount()));
        return threadPoolExecutor.getActiveCount() == 0 || (threadPoolExecutor.getActiveCount() - 1) == 0;


    }


    /**
     * 如果存活的线程还有一定数量  运行这些线程进行 关闭
     */
    public void shutDownNow() {


        threadPoolExecutor.shutdown();
        threadPoolExecutor.shutdownNow();
        threadPoolExecutor.getQueue();
        while (threadPoolExecutor.getQueue().size() != 0) {
            Runnable runnable = threadPoolExecutor.getQueue().poll();
            threadPoolExecutor.remove(runnable);
        }

    }


    public synchronized boolean read() {
        return false;
    }


}
