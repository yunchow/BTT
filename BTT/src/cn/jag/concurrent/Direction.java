package cn.jag.concurrent;

/**
 * @author zhangjie
 */
public enum Direction {

	LEFT(-1), RIGHT(1);

	private int value;

	Direction(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
