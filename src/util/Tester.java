package util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Tester {
	private int[][] board = new int[9][9];

	private int[][] convert(String fileName) throws IOException {
		File file = getFile(fileName);
		int[][] puzzle = new int[9][9];
		int i = 0;
		for (String string : Files.readAllLines(
				Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)) {
			puzzle[i] = converterUtil(string);
			i++;
		}
		return puzzle;
	}

	private int[] converterUtil(String line) {
		String[] array = line.split(" ");
		int[] ints = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			ints[i] = Integer.parseInt(array[i]);
		}
		return ints;
	}

	private File getFile(String fileName) throws IOException {
		File directory = new File(".\\samples");
		return new File(directory.getCanonicalPath() + File.separator
				+ fileName);
	}

	public static void main(String[] args) throws IOException {

	}
}
