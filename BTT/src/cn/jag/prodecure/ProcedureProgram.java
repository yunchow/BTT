package cn.jag.prodecure;

/**
 * @author zhangjie
 */
public class ProcedureProgram {

	public static void main(String[] args) {
		// 两列火车同时从相距120公里的甲、乙两地相对开出，从甲地开出的火车以时速150公里/小时行驶，从乙地开出的火车以时速200公里/小时行驶。
		// 假设有一个超级愤怒的小鸟，也从甲地与火车同时出发，并以400公里/小时的时速飞行，当小鸟遇到乙地出发的火车时，立刻折返，遇到甲地出发的火车也立即折返。当两列火车相遇时结束。
		//	请您编写一段程序模拟这个过程，要求：
		//	1、时间刻度假设程序中的1秒等于现实中的1分钟
		//	2、火车开始运行以及相遇时的时间点、耗时，需要打印出来
		//	3、当愤怒的小鸟在遇到任意一辆火车时，需要将此时时间点、耗时打印出来
		//	4、最终，小鸟实际飞行的距离、折返的次数需要打印出来
		// 考察项：多线程编程、模型设计、算法
		int totalDistance = 120;
		float costTimeForBird = 0;
		float remainDistance = totalDistance;
		int birdTurnAroundCount = 0;
		// 由于最小刻度是每秒，所以没法按照数学方法来算，因为会算出小数来的
		do {
			if (birdTurnAroundCount % 2 == 0) {
				costTimeForBird = 60 * remainDistance / (400 + 200);
			} else {
				costTimeForBird = 60 * remainDistance / (400 + 150);
			}
			remainDistance -= (150 + 200) * costTimeForBird / 60;
			birdTurnAroundCount++;
			System.out.printf("The bird met the train at %f second", costTimeForBird);
			System.out.println();
		} while ((int) costTimeForBird > 0);
		long trainMetCostTime = totalDistance * 60 / (150 + 200); // 先乘60，否则分子太小，值就是0了
		System.out.printf("The two trains met at %d second", trainMetCostTime);
		System.out.println();
		System.out.printf("bird turn around count:%d times", birdTurnAroundCount);
		System.out.println();
		System.out.printf("bird flied %d kilometre", 400 * trainMetCostTime / 60);
	}
}
