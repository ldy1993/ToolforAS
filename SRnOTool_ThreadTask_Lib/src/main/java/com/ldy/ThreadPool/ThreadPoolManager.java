package com.ldy.ThreadPool;


import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author chendd 2019-11-25
 *
 * 线程池管理帮助类
 *
 * 线程资源必须通过线程池提供，不允许在应用中自行显示的创建线程,
 * 1. 使线程的创建更加规范， 可以合理控制开辟线程的数量
 * 2. 线程的细节管理交给线程池处理，优化了资源的开销。
 *
 * corePoolSize         :指定了线程池中的线程数量，它的数量决定了添加的任务是开辟新的线程去执行，还是放到workQueue任务队列中去；
 * maximumPoolSize      :指定了线程池中的最大线程数量，这个参数会根据你使用的workQueue任务队列的类型，决定线程池会开辟的最大线程数量；
 * keepAliveTime        :当线程池中空闲线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁；
 * unit                 :keepAliveTime的单位
 * workQueue            :任务队列，被添加到线程池中，但尚未被执行的任务；它一般分为直接提交队列、有界任务队列、无界任务队列、优先任务队列几种；
 * threadFactory        :线程工厂，用于创建线程，一般用默认即可；
 * handler              :拒绝策略；当任务太多来不及处理时，如何拒绝任务；
 *
 * https://www.cnblogs.com/dafanjoy/p/9729358.html
 */
public class ThreadPoolManager {
    private static final String TAG = "ThreadPoolManager";

    private static ThreadFactory sThreadFactory = new ThreadFactory() {
        private int mThreadCount = 0;

        @Override
        public Thread newThread(@NonNull Runnable r) {
            mThreadCount++;
            return new Thread(r, "ThreadPoolManager[" + mThreadCount + "]");
        }
    };

    /**
     * 创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
     * 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。
     * 此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
     *
     * @return {@link ExecutorService}
     */
    public static ExecutorService newSingleThreadPool() {
        return new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                sThreadFactory);
    }

    /**
     * 创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
     * 线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
     *
     * @param corePoolSize 线程池核心大小
     *
     * @return {@link ExecutorService}
     */
    public static ExecutorService newFixedThreadPool(int corePoolSize) {
        return new ThreadPoolExecutor(corePoolSize,
                corePoolSize,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                sThreadFactory);
    }

    /**
     * 创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，
     * 那么就会回收部分空闲（60秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。
     * 此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
     *
     * @return {@link ExecutorService}
     */
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0,
                Integer.MAX_VALUE,
                60L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                sThreadFactory);
    }

    /**
     * 创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
     * ScheduledExecutorService比Timer更安全，功能更强大
     *
     * @param corePoolSize 线程池核心大小
     *
     * @return {@link ExecutorService}
     */
    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize, sThreadFactory);
    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     *
     * @return {@link ExecutorService}
     */
    public static ScheduledExecutorService newSingleThreadScheduledExecutor() {
        return new ScheduledThreadPoolExecutor(1, sThreadFactory);
    }

}
