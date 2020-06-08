package com.ldy.task;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/11/18
 * 描    述：创建异步任务抽象类
 * 修订历史：
 * ================================================
 */
public abstract class BaseAsyncTask<T> {
    private final int SON_THREAD = 0;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SON_THREAD) {
                //主线程处理来自子线程数据
                T t = (T) msg.obj;
                endDeal(t);
            }
        }
    };
    private ThreadPoolExecutor threadPoolExecutor;

    public void init() {
        if (threadPoolExecutor == null) {
            Log.e("ldy","线程池构造方法实例");
            //threadPoolExecutor构造方法实例
            ThreadFactory threadFactory = setthreadFactory();
            RejectedExecutionHandler rejectedExecutionHandler = sethandler();
            if (threadFactory != null && rejectedExecutionHandler != null) {
                threadPoolExecutor = new ThreadPoolExecutor(
                        setCorePoolSize(), setMaximumPoolSize(), setkeepAliveTime(),
                        setunit(), setworkQueue(), threadFactory, rejectedExecutionHandler);
            } else {
                threadPoolExecutor = new ThreadPoolExecutor(
                        setCorePoolSize(), setMaximumPoolSize(), setkeepAliveTime(),
                        setunit(), setworkQueue());
            }
        }
    }

    public <T> void excute() {
        //主线程预处理
        predeal();
        init();

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                T t = doInBackgroud();
                handler.obtainMessage(SON_THREAD, t).sendToTarget();
            }
        });
    }

    /**
     * 主线程中预处理
     */
    protected abstract void predeal();

    /**
     * 子线程中处理
     */
    protected abstract <T> T doInBackgroud();

    /**
     * 主线程中结束处理
     */
    protected abstract void endDeal(T t);

    /**
     * 线程池中核心线程的数量
     */
    protected abstract int setCorePoolSize();

    /**
     * 线程池中最大线程数量
     */
    protected abstract int setMaximumPoolSize();

    /**
     * 非核心线程的超时时长
     */
    protected abstract long setkeepAliveTime();

    /**
     * 第三个参数的单位，有纳秒、微秒、毫秒、秒、分、时、天等
     */
    protected abstract TimeUnit setunit();

    /**
     * 线程池中的任务队列，该队列主要用来存储已经被提交但是尚未执行的任务。存储在这里的任务是由ThreadPoolExecutor的execute方法提交来的。
     */
    protected abstract BlockingQueue<Runnable> setworkQueue();

    /**
     * 为线程池提供创建新线程的功能，这个我们一般使用默认即可
     */
    protected abstract ThreadFactory setthreadFactory();

    /**
     * 拒绝策略，当线程无法执行新任务时（一般是由于线程池中的线程数量已经达到最大数或者线程池关闭导致的），默认情况下，当线程池无法处理新线程时，会抛出一个RejectedExecutionException。
     */
    protected abstract RejectedExecutionHandler sethandler();
}
