package net.watertao;

/**
 * Created by watertao on 8/16/16.
 */
public class Bootstrap {

	public static void main(String[] arguments) {
		Runner trainA = new Runner(0, 1, 150f / 60); // 甲地的火车
		Runner trainB = new Runner(120f, -1, 200f / 60); // 乙地的火车
		Runner bird = new Runner(0, 1, 400f / 60); // 甲地的小鸟

		KnockCalculator calc = new KnockCalculator(trainA, trainB, bird);

		//new Thread(new Timer(trainA, trainB, bird, calc)).start();
		new Timer(trainA, trainB, bird, calc).run();
	}

}
