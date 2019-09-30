package main.helper;

public class StringUtils {
	public static int stringToInt(String string) {
		return Integer.parseInt(string == null ? "0" : string);
	}
}
