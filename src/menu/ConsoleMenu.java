package menu;

import horse.HorseLogic;

import java.util.InputMismatchException;

import sudoku.SudokuLogic;
import tictactoe.TicTacToeGUI;
import util.ConsoleReader;
import util.Dot;

public class ConsoleMenu {
	private ConsoleReader consoleReader = new ConsoleReader();
	private HorseLogic horse;
	private SudokuLogic sudoku;

	private void printMenu() {
		System.out.println("=========================================");
		System.out.println("|\tPLEASE, SELECT GAME TO PLAY:\t|");
		System.out.println("=========================================");
		System.out
				.println("|\t\tOptions:\t\t|\n|\t1. Tic-Tac-Toe\t\t\t|\n|\t2. Horse\t\t\t|\n|\t3. Sudoku\t\t\t|");
		System.out.println("=========================================");
		System.out.println("|\t0. Exit\t\t\t\t|");
		System.out.println("=========================================");
	}

	public void runMainMenu() {
		printMenu();
		while (true) {
			int choice = consoleReader.readInitialChoice();
			switch (choice) {
			case 1:
				System.out.println(choice + " was chosen\n");
				TicTacToeGUI.startApp();
				break;
			case 2:
				horse = new HorseLogic();
				System.out.println(choice + " was chosen\n");
				System.out
						.println("\t\t\t*********\n\t\t\t* Horse *\n\t\t\t*********\n");
				runHorseBoardSizeChooserMenu();
				break;
			case 3:
				System.out.println(choice + " was chosen\n");
				System.out
						.println("\t\t\t**********\n\t\t\t* Sudoku *\n\t\t\t**********\n");
				sudoku = new SudokuLogic();
				runSudokuPuzzleChooserMenu();
				break;
			case 0:
				System.out.println("Bye-bye");
				System.exit(choice);
			}
		}
	}

	private void runSudokuPuzzleChooserMenu() {
		System.out
				.println("======================================================================");
		System.out.println("Choose file and it'll be solved by the program");
		System.out
				.println("======================================================================");
		sudoku.chooseSudoku();
		String selectedFile = null;
		while (selectedFile == null) {
			selectedFile = consoleReader.readSudokuChoise();
		}
		sudoku.startApp(selectedFile);
	}

	private void runHorseBoardSizeChooserMenu() {
		System.out
				.println("======================================================================");
		System.out
				.println("This program outputs all possible answers of horse trip on the board.\nAssume that default board size is 8x8\nand correct answers can fit only on the board beginning from size 6x6.\nWould you like to change default board size (y/n)?");
		System.out
				.println("======================================================================");

		while (true) { // TODO
			String choice = consoleReader.readYorN();
			switch (choice) {
			case "y":
				System.out.println(choice + " was chosen");
				runHorseBoardSizeSetterMenu();
				break;
			case "n":
				System.out.println(choice + " was chosen");
				runHorsePositionChooserMenu();
				break;
			}
		}
	}

	private void runHorseBoardSizeSetterMenu() {
		int customBoardSize = 0;
		System.out
				.println("======================================================================");
		System.out
				.println("Please, enter preffered board size,\n(range of possible board size is from 6 till Ur common sense allows U):");
		System.out
				.println("======================================================================");
		while (true) {
			customBoardSize = consoleReader.readCusomHorseBoardSize();
			if (customBoardSize != 0) {
				horse.setCurrentBoardSize(customBoardSize);
				runHorsePositionChooserMenu();
				break;
			}
		}
	}

	private void runHorsePositionChooserMenu() {
		System.out
				.println("======================================================================");
		System.out
				.println("Assume that default horse start position is 0,0\nWould you like to change it (y/n)?");
		System.out
				.println("======================================================================");
		while (true) { // TODO
			String choice = consoleReader.readYorN();
			switch (choice) {
			case "y":
				System.out.println(choice + " was chosen");
				runHorsePositionSetterMenu();
				break;
			case "n":
				System.out.println(choice + " was chosen");
				startHorseApp();
				break;
			}
		}
	}

	private void runHorsePositionSetterMenu() {
		Dot customStartPosition = horse.getCurrentStartPosition();
		int currentBoardSize = horse.getCurrentBoardSize();
		int row = 0;
		int col = 0;
		System.out
				.println("======================================================================");
		System.out
				.println("Please, Enter coordinates of custom start position.\nValid values are in range between 0 and "
						+ (horse.getCurrentBoardSize() - 1));
		System.out
				.println("======================================================================");
		System.out.println("Please, enter row value:");
		while (true) {
			try {
				row = consoleReader.readDotValue(currentBoardSize);
				customStartPosition.setRow(row);
				break;
			} catch (InputMismatchException ime) {
				System.out.println("Invalid input data.\nPlease, try again");
			}
		}
		System.out.println("Please, enter column value:");
		while (true) {
			try {
				col = consoleReader.readDotValue(currentBoardSize);
				customStartPosition.setRow(col);
				break;
			} catch (InputMismatchException ime) {
				System.out.println("Invalid input data.\nPlease, try again");
			}
		}
		startHorseApp();
	}

	private void startHorseApp() {
		System.out
				.println("======================================================================");
		System.out
				.println("Horse Program will start with following parameters\n\tBoard size: "
						+ horse.getCurrentBoardSize()
						+ "\n\tStart position: "
						+ horse.getCurrentStartPosition().getColumn()
						+ ","
						+ horse.getCurrentStartPosition().getRow());
		System.out
				.println("======================================================================");
		horse.startApp();
	}
}
