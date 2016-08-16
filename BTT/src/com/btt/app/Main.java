package com.btt.app;

import com.btt.app.utils.MathUtils;

import java.util.concurrent.CyclicBarrier;

/**
 * 【笔试题】
 * 两列火车同时从相距120公里的甲、乙两地相对开出，从甲地开出的火车以时速150公里/小时行驶，
 * 从乙地开出的火车以时速200公里/小时行驶。假设有一个超级愤怒的小鸟，
 * 也从甲地与火车同时出发，并以40公里/小时的时速飞行，当小鸟遇到乙地出发的火车时，立刻折返，
 * 遇到甲地出发的火车也立即折返。 当两列火车相遇时结束。
 *
 * 请您编写一段程序模拟这个过程，要求：
 * 1、 时间刻度假设程序中的1秒等于现实中的1分钟
 * 2、 火车开始运行以及相遇时的时间点、耗时，需要打印出来
 * 3、 当愤怒的小鸟在遇到任意一辆火车时，需要将此时时间点、耗时打印出来
 * 4、 最终，小鸟实际飞行的距离、折返的次数需要打印出来
 *
 * 编程语言要求：
 * 1、 Java语言
 * 2、 只能使用标准JDK的接口，不能使用第三方类库
 * 3、 程序源码单独打包
 * 主要考察项，多线程编程、模型设计、算法
 *
 * Created by zhouyun on 2016/8/15.
 */
public class Main {
    public static final int CONCURRENT_SIZE = 5;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(CONCURRENT_SIZE);
        CyclicBarrier wbarrier = new CyclicBarrier(CONCURRENT_SIZE);

        // 1, 初始化道路
        Road road = new Road(Road.DEFAULT_LENGTH);

        // 2， 初始化火车A， 从左向右行驶
        Train trainA = new Train(barrier, wbarrier);
        trainA.setLabelName("甲火车");
        trainA.setDirection(Movable.DERECTION_RIGHT);
        trainA.setCurrentPosition(0);
        trainA.setSpeed(MathUtils.divide(150, 60));
        trainA.setRoad(road);
        trainA.setPriority(Thread.MAX_PRIORITY);
        System.out.println(trainA);

        // 3， 初始化火车B， 从右向左行驶
        Train trainB = new Train(barrier, wbarrier);
        trainB.setLabelName("已火车");
        trainB.setDirection(Movable.DERECTION_LEFT);
        trainB.setCurrentPosition(Road.DEFAULT_LENGTH);
        trainB.setSpeed(MathUtils.divide(200, 60));
        trainB.setRoad(road);
        trainB.setPriority(Thread.MAX_PRIORITY);
        System.out.println(trainB);

        // 4, 初始化小鸟，从左向右
        Bird bird = new Bird(barrier, wbarrier);
        bird.setLabelName("小鸟");
        bird.setDirection(Movable.DERECTION_RIGHT);
        bird.setCurrentPosition(0);
        bird.setSpeed(MathUtils.divide(400, 60));
        bird.setRoad(road);
        bird.setPriority(Thread.MAX_PRIORITY);
        System.out.println(bird);

        // 5, 统一系统时钟
        Clock clock = new Clock(barrier, wbarrier);


        // 6，初始化观察者
        Watcher watcher = new Watcher(barrier, wbarrier);
        watcher.setBird(bird);
        watcher.setRoad(road);
        watcher.setTrainA(trainA);
        watcher.setTrainB(trainB);
        watcher.setClock(clock);
        watcher.setPriority(Thread.MIN_PRIORITY);

        // 7, 开始计时
        clock.start();
        trainA.start();
        trainB.start();
        bird.start();
        watcher.start();

    }
}
