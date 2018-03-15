package edu.nchu.cs.ai.problems;

public class OneMaxProblem {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("missing arguments: bit count");
			return;
		}
		try {
			int bitCnt = Integer.valueOf(args[0]);
			exhaustiveSearch(Integer.valueOf(args[0]));
		}catch(NumberFormatException e) {
			System.out.println("bit count must be integer");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String exhaustiveSearch(int bitCnt) {
		System.out.println("prepare to execute " + bitCnt + " bit");
		long startTime = System.currentTimeMillis();
		String maxBinaryString = null;
		long endValue = (long) Math.pow(2, bitCnt);
		if (endValue == Long.MAX_VALUE) {
			return "out of range";
		}
		long startValue = 0;
		int maxCount = 0;
		int cnt = 0;

		//1.Initialize
		int[] binaryArray = new int[bitCnt];
		int[] keep = new int[bitCnt];
		
		while (startValue < endValue ){
			//2.transition
			binaryArray = transition(binaryArray);
			//3.evaluation
			cnt = evaluation(binaryArray);
			//4.determination
			if (cnt > maxCount) {
				maxCount = cnt;
				System.arraycopy(binaryArray, 0, keep, 0, binaryArray.length);
			}
			startValue++;
		}
//		maxBinaryString = StringUtils.join(keep, null);//commons-lang3 bug?
		
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < keep.length; i++) {
			buf.append(keep[i]);
		}
		maxBinaryString = buf.toString();
		System.out.println("  The one-max objective value of " + bitCnt + " bit is:");
		System.out.println("  " + maxBinaryString);
		System.out.println("  spent " + ((double) (System.currentTimeMillis()-startTime))/1000 + " seconds");
		System.out.println();
		return maxBinaryString;
	}
	
	private static int[] transition(int[] currentValue) {
		for (int i=currentValue.length-1;i>=0;i--) {
			if (currentValue[i] == 0) {
				currentValue[i] = 1;
				return currentValue;
			}
			currentValue[i] = 0;
		}
		return currentValue;
	}
	
	private static int evaluation(int[] currentValue) {
		int cnt = 0;
		for (int i=0;i<currentValue.length;i++) {
			cnt += currentValue[i];
		}
		return cnt;
	}
}
