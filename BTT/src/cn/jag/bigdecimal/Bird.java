package cn.jag.bigdecimal;

import java.math.BigDecimal;

/**
 * @author zhangjie
 */
public class Bird extends Mover {

	private int turnBackCnt; // 折返次数

	private BigDecimal distance; // 飞过的距离

	public Bird(String name, BigDecimal speed, BigDecimal position, Direction direction) {
		super(name, speed, position, direction);
		this.distance = new BigDecimal(0);
	}

	@Override
	public void move(long time) {
		super.move(time);
		distance = distance.add(speed.multiply(new BigDecimal(time)));
	}

	public void turnBack() {
		if (direction == Direction.LEFT) {
			direction = Direction.RIGHT;
		} else {
			direction = Direction.LEFT;
		}
		turnBackCnt++;
	}

	public int getTurnBackCnt() {
		return turnBackCnt;
	}

	public BigDecimal getDistance() {
		return distance;
	}
}
