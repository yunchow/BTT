package cn.jag.bigdecimal;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * @author zhangjie
 */
public class BigDecimalTest {
	public static void main(String[] args) {
		BigDecimal bigDecimal1 = new BigDecimal(400).divide(new BigDecimal(60), 1000, ROUND_HALF_UP);
		BigDecimal bigDecimal2 = new BigDecimal(400).divide(new BigDecimal(60), 8, ROUND_HALF_UP);
		float floatVariable1 = 400f / 60;
		float floatVariable2 = 400f / 60f;
		float floatVariable3 = 400 / 60;
		System.out.println(bigDecimal1);
		System.out.println(bigDecimal2);
		System.out.println(floatVariable1);
		System.out.println(floatVariable2);
		System.out.println(floatVariable3);

//		BigDecimal bigDecimal1 = new BigDecimal(150).divide(new BigDecimal(60), 8, ROUND_HALF_UP);
//		BigDecimal bigDecimal2 = new BigDecimal("150").divide(new BigDecimal("60"), 8, ROUND_HALF_UP);
//		float floatVariable1 = 150f / 60;
//		float floatVariable2 = 150f / 60f;
//		float floatVariable3 = 150 / 60;
//		System.out.println(bigDecimal1);
//		System.out.println(bigDecimal2);
//		System.out.println(floatVariable1);
//		System.out.println(floatVariable2);
//		System.out.println(floatVariable3);
	}
}
