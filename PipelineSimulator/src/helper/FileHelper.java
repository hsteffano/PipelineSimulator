package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class FileHelper {
	
	private static BufferedReader reader;
	
	public static String lerLinha() {
		try {
			reader = new BufferedReader(new FileReader("/exemplo_codigo.txt"));
			String line = reader.readLine();
			return line;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
}
