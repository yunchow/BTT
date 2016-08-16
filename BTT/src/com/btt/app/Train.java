package com.btt.app;

import com.btt.app.utils.MathUtils;

import java.util.concurrent.CyclicBarrier;

/**
 * 火车
 * Created by zhouyun on 2016/8/15.
 */
public class Train extends Movable {

    public Train(CyclicBarrier startBarrier, CyclicBarrier watchBarrier) {
        super(startBarrier, watchBarrier);
    }

    @Override
    public void move() {
        //System.out.println(getLabelName() + ">>>> " + Clock.seconds);
        double len = MathUtils.multiply(getSpeed(), getDirection());
        setCurrentPosition(MathUtils.add(getCurrentPosition(), len));
    }
}
