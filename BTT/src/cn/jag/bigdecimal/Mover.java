package cn.jag.bigdecimal;

import java.math.BigDecimal;

/**
 * @author zhangjie
 */
public class Mover {

	protected String name; // 标识

	protected BigDecimal speed; // 速度

	protected BigDecimal position; // 距离甲地的距离

	protected Direction direction; // 方向

	public Mover(String name, BigDecimal speed, BigDecimal position, Direction direction) {
		this.name = name;
		this.speed = speed;
		this.position = position;
		this.direction = direction;
	}

	public void move(long time) {
		position = position.add(speed.multiply(new BigDecimal(time)).multiply(new BigDecimal(direction.getValue())));
	}

	public BigDecimal getPosition() {
		return position;
	}

	public Direction getDirection() {
		return direction;
	}

}
