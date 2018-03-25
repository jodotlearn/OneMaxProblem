package edn.nchu.cs.utils;

public class StringUtil {
	public static String toString(int[] arr) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			buf.append(arr[i]);
		}
		return buf.toString();
	}
}
