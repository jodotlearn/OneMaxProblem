package edu.nchu.cs.ai.genetic;

import java.math.BigInteger;

public class OneMaxProblem {
	public static String findOneMaxValue(int bitCnt) {
		if (bitCnt > 62) {
			return "cannot support more thant 62 bit";
		}
		System.out.println("prepare to execute " + bitCnt + " bit");
		long startTime = System.currentTimeMillis();
		long endValue = 0;
		long startValue = (long) Math.pow(2, bitCnt);
		long keepValue = 0;
		int maxCount = 0;
		int cnt = 0;
		while(startValue >= endValue) {
			cnt = Long.bitCount(startValue);
			if (maxCount < cnt) {
				maxCount = cnt;
				keepValue = startValue;
			}
			startValue--;
		}
		String rtn = Long.toBinaryString(keepValue);
		System.out.println("The one-max objective value of " + bitCnt + " bit is:");
		System.out.println(rtn);
		System.out.println("spent " + ((double) (System.currentTimeMillis()-startTime))/1000 + " seconds");
		return rtn;
	}

	public static String findLargeScaleOneMaxValue(int bitCnt) {
		System.out.println("prepare to execute " + bitCnt + " bit");
		long startTime = System.currentTimeMillis();
		BigInteger endValue = BigInteger.valueOf(0);
		BigInteger startValue = BigInteger.valueOf((long) Math.pow(2, bitCnt));
		BigInteger keepValue = BigInteger.valueOf(0);
		int maxCount = 0;
		int cnt = 0;
		while(startValue.compareTo(endValue) >= 0) {
			cnt = startValue.bitCount();
			if (maxCount < cnt) {
				maxCount = cnt;
				keepValue = startValue;
			}
			startValue = startValue.subtract(BigInteger.ONE);
		}
		String rtn = keepValue.toString(2);
		System.out.println("The one-max objective value of " + bitCnt + " bit is:");
		System.out.println(rtn);
		System.out.println("spent " + ((double) (System.currentTimeMillis()-startTime))/1000 + " seconds");
		return rtn;
	}
}
