package util;

import horse.HorseLogic;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleReader {

	private Scanner in;

	public int readInitialChoice() {
		in = new Scanner(System.in); // TODO
		int choice = 0;
		try {
			choice = in.nextInt();
			if (choice < 0 || choice > 3) {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException ime) {
			System.out.println("Invalid input data.\nPlease, try again");
		}
		return choice;
	}

	public String readYorN() {
		in = new Scanner(System.in); // TODO
		String choice = null;
		try {
			choice = in.next();
			if (choice.equals("y")) {
				return choice;
			} else if (choice.equals("n")) {
				return choice;
			} else {
				throw new InputMismatchException();
			}

		} catch (InputMismatchException ime) {
			System.out.println("Invalid input data.\nPlease, try again");
		}
		return choice;
	}

	public int readCusomHorseBoardSize() {
		in = new Scanner(System.in); // TODO
		int temp = 0;
		int size = 0;
		try {
			temp = in.nextInt();
			if (temp < HorseLogic.SMALLEST_POSSIBLE_BOARD_SIZE) {
				throw new InputMismatchException();
			}
			size = temp;
		} catch (InputMismatchException ime) {
			System.out.println("Invalid input data.\nPlease, try again");
		}
		return size;
	}

	public int readDotValue(int boardSize) throws InputMismatchException {
		in = new Scanner(System.in); // TODO
		int temp = 0;
		int value = 0;
		temp = in.nextInt();
		if (temp < 0 || temp >= boardSize) {
			throw new InputMismatchException();
		}
		value = temp;
		return value;
	}

	public String readSudokuChoise() {
		System.out.println("Please, enter file name: ");
		File directory = new File(".\\samples");
		File[] files = directory.listFiles();
		in = new Scanner(System.in); // TODO
		String fileName = null;
		try {
			fileName = in.next();
			boolean match = false;
			for (File file : files) {
				if (file.getName().equalsIgnoreCase(fileName)) {
					match = true;
					return fileName;
				}
			}
			if (!match) {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException ime) {
			System.out
					.println("Invalid file name. Please be more attentive while entering file name: ");
		}
		return null;
	}
}
