package util;

public class BoardPrinter {
	public static void printMatrix(int[][] board) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				System.out.print(board[row][col] + ",");
			}
			System.out.println();
		}
		System.out.println();
	}
}
