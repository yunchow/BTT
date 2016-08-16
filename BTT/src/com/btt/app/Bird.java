package com.btt.app;

import com.btt.app.utils.MathUtils;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 小鸟
 * Created by zhouyun on 2016/8/15.
 */
public class Bird extends Movable {
    private volatile double totalLen;  // 运动的总的距离
    private volatile AtomicInteger reverseTimes = new AtomicInteger(0); // 折返的次数

    public Bird(CyclicBarrier startBarrier, CyclicBarrier watchBarrier) {
        super(startBarrier, watchBarrier);
    }

    @Override
    public void move() {
        //System.out.println(getLabelName() + ">>>>" + Clock.seconds);
        double len = MathUtils.multiply(getSpeed(), getDirection());
        totalLen = MathUtils.add(totalLen, getSpeed());
        //System.out.println("<<小鸟移动距离为：" + len + " , 当前位置为：" + getCurrentPosition());
        setCurrentPosition(MathUtils.add(getCurrentPosition(), len));
        //System.out.println(">>小鸟移动距离为：" + len + " , 当前位置为：" + getCurrentPosition());
    }

    @Override
    public void directRight() {
        super.directRight();
        reverseTimes.incrementAndGet();
    }

    @Override
    public void directLeft() {
        super.directLeft();
        reverseTimes.incrementAndGet();
    }

    public double getTotalLen() {
        return totalLen;
    }

    public void setTotalLen(double totalLen) {
        this.totalLen = totalLen;
    }

    public AtomicInteger getReverseTimes() {
        return reverseTimes;
    }

    public void setReverseTimes(AtomicInteger reverseTimes) {
        this.reverseTimes = reverseTimes;
    }

}
