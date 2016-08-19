package cn.jag.floating;

/**
 * @author zhangjie
 */
public class Mover {

	protected String name; // 标识

	protected float speed; // 速度

	protected float position; // 距离甲地的距离

	protected Direction direction; // 方向

	public Mover(String name, float speed, float position, Direction direction) {
		this.name = name;
		this.speed = speed;
		this.position = position;
		this.direction = direction;
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
