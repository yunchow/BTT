package net.watertao;

/**
 * Created by watertao on 8/16/16.
 */
public class KnockCalculator {

	private Runner trainA;
	private Runner trainB;
	private Runner bird;
	private int turnBackCnt = 0;

	public KnockCalculator(Runner trainA, Runner trainB, Runner bird) {
		this.trainA = trainA;
		this.trainB = trainB;
		this.bird = bird;
	}

	public void timeGo() {
		// 火车相遇
		if (trainA.getDeltaDistance() >= trainB.getDeltaDistance()) {
			System.out.println(">>> 辆车相撞,结束!");
			System.out.println(">>> 小鸟总共飞行了 " + bird.getWholeDistance() + " 公里");
			System.out.println(">>> 小鸟一共折返了 " + turnBackCnt + " 次");
			System.exit(0);
		}

		// 检查小鸟与火车A的碰撞
		if (trainA.getDirection() != bird.getDirection() && trainA.getDeltaDistance() >= bird.getDeltaDistance()) {
			System.out.println(">>> 小鸟遇到了火车A, 折返");
			bird.turnBack();
			turnBackCnt++;
		}

		// 检查小鸟与火车B的碰撞
		if (trainB.getDirection() != bird.getDirection() && trainB.getDeltaDistance() <= bird.getDeltaDistance()) {
			System.out.println(">>> 小鸟遇到了火车B, 折返");
			bird.turnBack();
			turnBackCnt++;
		}

	}

}