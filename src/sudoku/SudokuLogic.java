package sudoku;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import util.BoardPrinter;

public class SudokuLogic {
	public static final int[] ANSWERS = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	public static final int BOARD_SIZE = 9;
	private int[][] board;
	private int counter;
	private int stopCondition;
	private int innerBoardSize;

	public SudokuLogic() {
		this.board = new int[BOARD_SIZE][BOARD_SIZE];
		this.stopCondition = BOARD_SIZE * BOARD_SIZE;
		this.innerBoardSize = BOARD_SIZE / 3;
	}

	private boolean isEmpty(int row, int col) {
		return (board[row][col]) == 0 ? true : false;
	}

	private void setNumber(int row, int col, int number) {
		board[row][col] = number;
	}

	private void findSolution() {
		counter++;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (isEmpty(row, col)) {
					if (getAnswer(row, col) != 0) {
						setNumber(row, col, getAnswer(row, col));
						break;
					}
				}
			}
		}
		if (stopCondition == counter) {
			BoardPrinter.printMatrix(board);
			return;
		}
		if (!isCompleted()) {
			findSolution();
		} else {
			BoardPrinter.printMatrix(board);
		}
	}

	private boolean isCompleted() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (isEmpty(row, col)) {
					return false;
				}
			}
		}
		return true;
	}

	private int getAnswer(int row, int col) {
		for (int answer : ANSWERS) {
			if (isDuplicate(row, col, answer)) {
				continue;
			}
			if (isOnlyPossibleAnswer(row, col, answer)) {
				return answer;
			}
		}
		return 0;
	}

	private boolean isDuplicate(int row, int col, int answer) {
		for (int i = 0; i < board[row].length; i++) {
			if (answer == board[row][i]) {
				return true;
			}
		}
		for (int i = 0; i < board.length; i++) {
			if (answer == board[i][col]) {
				return true;
			}
		}
		int rowRange = row / innerBoardSize * innerBoardSize;
		int colRange = col / innerBoardSize * innerBoardSize;
		for (int i = rowRange; i < rowRange + innerBoardSize; i++) {
			for (int j = colRange; j < colRange + innerBoardSize; j++) {
				if (answer == board[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isOnlyPossibleAnswer(int row, int col, int possibleAnswer) {
		List<Integer> numbers = new LinkedList<Integer>();
		for (int i = 0; i < board[row].length; i++) {
			if (board[row][i] != 0 && !numbers.contains(board[row][i])) {
				numbers.add(board[row][i]);
			}
		}
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] != 0 && !numbers.contains(board[i][col])) {
				numbers.add(board[i][col]);
			}
		}
		int rowRange = row / innerBoardSize * innerBoardSize;
		int colRange = col / innerBoardSize * innerBoardSize;
		for (int i = rowRange; i < rowRange + innerBoardSize; i++) {
			for (int j = colRange; j < colRange + innerBoardSize; j++) {
				if (board[i][j] != 0 && !numbers.contains(board[i][j])) {
					numbers.add(board[i][j]);
				}
			}
		}
		int answerSum = 0;
		int numberSum = 0;
		for (int answer : ANSWERS) {
			answerSum += answer;
		}
		for (Integer number : numbers) {
			numberSum += number;
		}
		if ((answerSum - possibleAnswer) == numberSum
				&& numbers.size() == (ANSWERS.length - 1)) {
			return true;
		}
		return false;
	}

	public void chooseSudoku() {
		File directory = new File(".\\samples");
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.print("directory:");
			} else {
				System.out.print("file: ");
			}
			System.out.println(file.getName());
			try {
				for (String line : Files.readAllLines(
						Paths.get(file.getAbsolutePath()),
						StandardCharsets.UTF_8)) {
					System.out.println(line);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("=================\n");
		}
	}

	private File getFile(String fileName) throws IOException {
		File directory = new File(".\\samples");
		return new File(directory.getCanonicalPath() + File.separator
				+ fileName);
	}

	private int[][] convert(String fileName) {
		try {
			File file = getFile(fileName);
			int i = 0;
			for (String string : Files.readAllLines(
					Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)) {
				board[i] = converterUtil(string);
				i++;
			}
		} catch (IOException ie) {
			System.out.println("Something goes wrong. Please try again");
		}
		return board;
	}

	private int[] converterUtil(String line) {
		String[] array = line.split(" ");
		int[] ints = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			ints[i] = Integer.parseInt(array[i]);
		}
		return ints;
	}

	public void startApp(String fileName) {
		board = convert(fileName);
		findSolution();
	}
}