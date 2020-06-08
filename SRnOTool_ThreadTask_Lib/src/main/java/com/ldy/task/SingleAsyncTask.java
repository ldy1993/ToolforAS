package com.ldy.task;

import android.widget.BaseAdapter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ================================================
 * 作    者：刘东阳
 * 版    本：
 * 创建日期：2019/11/18
 * 描    述：
 * 修订历史：
 * ================================================
 */
public abstract class SingleAsyncTask<T> extends BaseAsyncTask<T> {

    @Override
    protected int setCorePoolSize() {
        return 1;
    }

    @Override
    protected int setMaximumPoolSize() {
        return 1;
    }

    @Override
    protected long setkeepAliveTime() {
        return 0L;
    }

    @Override
    protected TimeUnit setunit() {
        return TimeUnit.MILLISECONDS;
    }

    @Override
    protected BlockingQueue<Runnable> setworkQueue() {
        return new LinkedBlockingQueue<Runnable>();
    }

    @Override
    protected ThreadFactory setthreadFactory() {
        return null;
    }

    @Override
    protected RejectedExecutionHandler sethandler() {
        return null;
    }
}
