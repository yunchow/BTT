package cn.jag.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangjie
 */
public class Mover implements Runnable {

	protected String name; // 标识

	protected float speed; // 速度

	protected float position; // 距离甲地的距离

	protected Direction direction; // 方向

	protected CyclicBarrier cyclicBarrier; // 时钟同步屏障

	public static final long TIME = TimeUnit.MINUTES.toMinutes(1L);

	public Mover(String name, float speed, float position, Direction direction, CyclicBarrier cyclicBarrier) {
		this.name = name;
		this.speed = speed;
		this.position = position;
		this.direction = direction;
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				cyclicBarrier.await();
				move(TIME);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} catch (BrokenBarrierException e) {
				break;
			}
		}
	}

	public void move(long time) {
		position += speed * time * direction.getValue();
	}

	public float getPosition() {
		return position;
	}

	public Direction getDirection() {
		return direction;
	}

}
