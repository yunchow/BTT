package com.btt.app;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 观察者，用来观察A，B两列火车什么时候发生碰撞
 * Created by zhouyun on 2016/8/15.
 */
public class Watcher extends Thread {
    private CyclicBarrier startBarrier;
    private CyclicBarrier watchBarrier;
    private Road road;
    private Train trainA;
    private Train trainB;
    private Bird bird;
    private Clock clock;
    private String[] pos; // 所有移动物体位置坐标

    public Watcher(CyclicBarrier startBarrier, CyclicBarrier watchBarrier) {
        this.startBarrier = startBarrier;
        this.watchBarrier = watchBarrier;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            await();
            if (trainA.crashWith(trainB)) {
                doCrash();
            } else {
                if (bird.crashWith(trainB)) {
                    bird.directLeft();
                    bird.setCurrentPosition(trainB.getCurrentPosition());
                }
                if (bird.crashWith(trainA)) {
                    bird.directRight();
                    bird.setCurrentPosition(trainA.getCurrentPosition());
                }
            }
            printSnapShort();
        }
        System.out.println();
        System.out.println("两火车发生的碰撞，程序退出！");
        System.out.println("小鸟总距离：" + bird.getTotalLen() + " 折返次数：" + bird.getReverseTimes());
        //System.out.println("观察者退出");
    }

    private void printSnapShort() {
        String snap = Clock.seconds + " >>>" + trainA.getLabelName() + ":" + trainA.getCurrentPosition()
                + " >>>" + trainB.getLabelName() + ":" + trainB.getCurrentPosition()
                + " >>>" + bird.getLabelName() + ":" + bird.getCurrentPosition();
        int birdPos = (int)bird.getCurrentPosition();
        int aPos = (int)trainA.getCurrentPosition();
        int bPos = (int)trainB.getCurrentPosition();
        pos = new String[(int)road.getLength() + 1];
        Arrays.fill(pos, "_");
        pos[0] = "" + Clock.seconds;
        if (birdPos > 0 && birdPos <= Road.DEFAULT_LENGTH)
        pos[birdPos + 1] = bird.getLabelName();
        pos[aPos + 1] = trainA.getLabelName();
        pos[bPos] = trainB.getLabelName();
        for (String po : pos) {
            System.out.print(po);
        }
        System.out.println();
        //System.out.println(snap);
    }

    private void await() {
        try {
            startBarrier.await();
            watchBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void doCrash() {
        road.closeTheRoad();  // 发生碰撞，关闭道路
        clock.interrupt();
        trainA.interrupt();
        trainB.interrupt();
        bird.interrupt();
        Thread.currentThread().interrupt();
    }

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public Train getTrainA() {
        return trainA;
    }

    public void setTrainA(Train trainA) {
        this.trainA = trainA;
    }

    public Train getTrainB() {
        return trainB;
    }

    public void setTrainB(Train trainB) {
        this.trainB = trainB;
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }

}
