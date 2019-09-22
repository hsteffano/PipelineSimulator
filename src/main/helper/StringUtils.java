package main.helper;

public final class StringUtils {
	public static int stringToInt(String string) {
		return Integer.parseInt(string == null ? "0" : string);
	}
}
