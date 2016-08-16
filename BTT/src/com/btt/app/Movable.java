package com.btt.app;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 所有能移动物体的抽象
 * Created by zhouyun on 2016/8/15.
 */
public abstract class Movable extends Thread {
    public static final int DERECTION_LEFT = -1; // 向左行驶
    public static final int DERECTION_RIGHT = 1; // 向右行驶

    private CyclicBarrier startBarrier;
    private CyclicBarrier watchBarrier;

    private String labelName;

    private Road road;                        // 所处道路
    private volatile int direction;           // 方向
    private volatile double speed;            // 速度
    private volatile double currentPosition;  // 当前位置

    public Movable(CyclicBarrier startBarrier, CyclicBarrier watchBarrier) {
        this.startBarrier = startBarrier;
        this.watchBarrier = watchBarrier;
    }

    /**
     * 运动轨迹
     */
    public abstract void move();

    /**
     * 检查是否发生了碰撞
     * @param movable
     * @return
     */
    public boolean crashWith(Movable movable) {
        if (this.moveToRight() && movable.moveToLeft()) {
            return this.currentPosition >= movable.currentPosition;
        }
        if (this.moveToLeft() && movable.moveToRight()) {
            return this.currentPosition <= movable.currentPosition;
        }
        return false;
    }

    @Override
    public final void run() {
        while (!Thread.interrupted()) {
            try {
                startBarrier.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (BrokenBarrierException e) {
                startBarrier.reset();
            }
            if (!getRoad().isClose()) {
                this.move();
            }
            try {
                watchBarrier.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (BrokenBarrierException e) {
                watchBarrier.reset();
            }
        }
        //System.out.println(getLabelName() + " 退出");
    }

    /**
     * 调头
     */
    public void reverseDirection() {
        if (moveToRight()) {
            this.direction = DERECTION_LEFT;
        }
        if (moveToLeft()) {
            this.direction = DERECTION_RIGHT;
        }
    }

    public void directRight() {
        this.direction = DERECTION_RIGHT;
    }

    public void directLeft() {
        this.direction = DERECTION_LEFT;
    }

    public boolean moveToLeft() {
        return direction == DERECTION_LEFT;
    }

    public boolean moveToRight() {
        return direction == DERECTION_RIGHT;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public double getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(double currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Movable{" +
                "labelName='" + labelName + '\'' +
                ", road=" + road +
                ", direction=" + direction +
                ", speed=" + speed +
                ", currentPosition=" + currentPosition +
                '}';
    }
}
