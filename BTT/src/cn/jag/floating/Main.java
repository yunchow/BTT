package cn.jag.floating;

import java.util.concurrent.TimeUnit;

import static cn.jag.floating.Direction.LEFT;
import static cn.jag.floating.Direction.RIGHT;

/**
 * 两列火车同时从相距120公里的甲、乙两地相对开出，从甲地开出的火车以时速150公里/小时行驶，从乙地开出的火车以时速200公里/小时行驶。
 * 假设有一个超级愤怒的小鸟，也从甲地与火车同时出发，并以400公里/小时的时速飞行，当小鸟遇到乙地出发的火车时，立刻折返，遇到甲地出发的火车也立即折返。当两列火车相遇时结束。
 * 请您编写一段程序模拟这个过程，要求：
 * 1、时间刻度假设程序中的1秒等于现实中的1分钟
 * 2、火车开始运行以及相遇时的时间点、耗时，需要打印出来
 * 3、当愤怒的小鸟在遇到任意一辆火车时，需要将此时时间点、耗时打印出来
 * 4、最终，小鸟实际飞行的距离、折返的次数需要打印出来
 * 考察项：多线程编程、模型设计、算法
 *
 * @author zhangjie
 */
public class Main {

	public static void main(String[] args) {
		// 时间刻度假设程序中的1秒等于现实中的1分钟，可以理解为最小时间刻度为1分钟
		Mover trainA = new Mover("trainA", 150f / 60f, 0f, RIGHT); // 150公里/小时，即150/60公里/分钟
		Mover trainB = new Mover("trainB", 200f / 60f, 120f, LEFT);
		Bird bird = new Bird("bird", 400f / 60f, 0f, RIGHT);
		Checker checker = new Checker(trainA, trainB, bird);
		do {
			checker.tick(TimeUnit.MINUTES.toMinutes(1));
		} while (!checker.checkCrash());
		System.out.println("小鸟总共飞行了" + bird.getDistance() + "公里，折返次数是" + bird.getTurnBackCnt() + "次");
	}
}