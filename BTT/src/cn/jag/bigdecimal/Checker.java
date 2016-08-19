package cn.jag.bigdecimal;

/**
 * @author zhangjie
 */
public class Checker {
	private Mover trainA; // 甲地的火车A
	private Mover trainB; // 乙地的火车B
	private Bird bird; // 愤怒的小鸟
	private int clock; // 时钟

	public Checker(Mover trainA, Mover trainB, Bird bird) {
		this.trainA = trainA;
		this.trainB = trainB;
		this.bird = bird;
		this.clock = 0;
	}

	/**
	 * 时钟滴答
	 */
	public void tick(long time) {
		trainA.move(time);
		trainB.move(time);
		bird.move(time);
		clock += time;
		printStatus();
	}

	public boolean checkCrash() {
		// 检测两辆火车之间是否发生了碰撞
		if (trainA.getPosition().compareTo(trainB.getPosition()) >= 0) {
			System.out.println("火车A与火车B发生了碰撞，程序终止");
			return true;
		}
		// 如果小鸟往乙地飞，与火车B发生了碰撞
		if (bird.getDirection() == Direction.RIGHT && bird.getPosition().compareTo(trainB.getPosition()) >= 0) {
			System.out.println("小鸟与火车B发生了碰撞!!掉头");
			bird.turnBack();
		}
		// 如果小鸟往甲地飞，与火车A发生了碰撞
		if (bird.getDirection() == Direction.LEFT && trainA.getPosition().compareTo(bird.getPosition()) >= 0) {
			System.out.println("小鸟与火车A发生了碰撞!!掉头");
			bird.turnBack();
		}
		return false;
	}

	private void printStatus() {
		StringBuilder sb = new StringBuilder();
		sb.append("第").append(clock).append("分钟，火车A距离甲地").append(trainA.getPosition()).append("公里, 火车B距离甲地").append(trainB.getPosition())
				.append("公里, 小鸟正在往").append(bird.getDirection() == Direction.RIGHT ? "乙地" : "甲地").append("飞，距离甲地").append(bird.getPosition());
		System.out.println(sb.toString());
	}
}
