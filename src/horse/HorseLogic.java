package horse;

import menu.ConsoleMenu;
import util.BoardPrinter;
import util.Dot;

public class HorseLogic {
	public static final int DEFAULT_BOARD_SIZE = 8;
	public static final int SMALLEST_POSSIBLE_BOARD_SIZE = 6;
	public static final Dot DEFAULT_START_POSITION = new Dot(0, 0);
	private int currentBoardSize;
	private Dot currentStartPosition;
	private int[][] board;
	private int numberOfMoves;
	private int tracker;
	private static int counter;
	private ConsoleMenu mainMenu = new ConsoleMenu();
	private static final Dot[] moves = new Dot[] { new Dot(1, 2),
			new Dot(2, 1), new Dot(2, -1), new Dot(1, -2), new Dot(-1, -2),
			new Dot(-2, -1), new Dot(-2, 1), new Dot(-1, 2) };

	public HorseLogic() {
		currentBoardSize = DEFAULT_BOARD_SIZE;
		currentStartPosition = DEFAULT_START_POSITION;
		board = new int[currentBoardSize][currentBoardSize];
		numberOfMoves = (int) Math.pow(currentBoardSize, 2) - 1;
	}

	public int getCurrentBoardSize() {
		return currentBoardSize;
	}

	public void setCurrentBoardSize(int currentBoardSize) {
		System.out.println("setting board size to " + currentBoardSize);
		this.currentBoardSize = currentBoardSize;
		this.board = new int[currentBoardSize][currentBoardSize];
		this.numberOfMoves = (int) Math.pow(currentBoardSize, 2) - 1;
	}

	public Dot getCurrentStartPosition() {
		return currentStartPosition;
	}

	public void setCurrentStartPosition(Dot currentStartPosition) {
		this.currentStartPosition = currentStartPosition;
	}

	private boolean canMove(int row, int col) {
		return ((row >= 0 && row < currentBoardSize)
				&& (col >= 0 && col < currentBoardSize) && (isEmpty(row, col)) && (tracker <= numberOfMoves));
	}

	private void move(Dot startPosition) {
		for (int i = 0; i < moves.length; i++) {
			int possibleRow = startPosition.getRow() + moves[i].getRow();
			int possibleColumn = startPosition.getColumn()
					+ moves[i].getColumn();
			boolean condition = canMove(possibleRow, possibleColumn);
			if (condition) {
				tracker++;
				board[possibleRow][possibleColumn] = tracker;
				move(new Dot(possibleRow, possibleColumn));
			}
			if (i == moves.length - 1
					&& !isEmpty(startPosition.getRow(),
							startPosition.getColumn())) {
				condition = false;
			}
			if (i == moves.length - 1 && !condition) {
				board[startPosition.getRow()][startPosition.getColumn()] = 0;
				tracker--;
			}
		}
		if (tracker == (numberOfMoves)) {
			System.out.println(++counter);
			BoardPrinter.printMatrix(board);
			return;
		}
	}

	private boolean isEmpty(int row, int col) {
		return board[row][col] == 0;
	}

	public void startApp() {
		System.out.println("inside startApp(). Counting...");
		move(currentStartPosition);
		System.out.println("Done");
		mainMenu.runMainMenu();
	}
}
