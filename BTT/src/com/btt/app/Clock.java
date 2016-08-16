package com.btt.app;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 统一系统时钟
 * Created by zhouyun on 2016/8/15.
 */
public class Clock extends Thread {
    private CyclicBarrier startBarrier;
    private CyclicBarrier watchBarrier;
    static AtomicInteger seconds = new AtomicInteger(0);

    public Clock(CyclicBarrier startBarrier, CyclicBarrier watchBarrier) {
        setDaemon(true);
        this.startBarrier = startBarrier;
        this.watchBarrier = watchBarrier;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                TimeUnit.SECONDS.sleep(1);
                seconds.incrementAndGet();
                startBarrier.await();
                watchBarrier.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            //startBarrier.reset();
        }
        //System.out.println("时钟结束");
    }
}
