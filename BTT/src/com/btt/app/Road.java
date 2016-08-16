package com.btt.app;

/**
 * 路径
 * Created by zhouyun on 2016/8/15.
 */
public class Road {
    public static final int DEFAULT_LENGTH = 220; // 默认120公里

    private volatile double length;             // 道路长度
    private volatile boolean close = false;  // 道路是否关闭

    public Road(int length) {
        this.length = length;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public void closeTheRoad() {
        close = true;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Road{" +
                "length=" + length +
                '}';
    }
}
