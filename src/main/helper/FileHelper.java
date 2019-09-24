package main.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class FileHelper {

	private static final String FILE_PATH = "src/main/resources/teste_exercício.txt";
	
	public static String lerLinha(int linha) {
		try (Stream<String> all_lines = Files.lines(Paths.get(FILE_PATH))) {
			return all_lines.skip(linha - 1).findFirst().orElse("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
