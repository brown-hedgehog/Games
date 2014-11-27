package tictactoe;

import javax.swing.JButton;

public class TicTacToeLogic {
	public static final String PLAYER_X = "Player X";
	public static final String PLAYER_O = "Player O";
	public static final String X = "X";
	public static final String O = "O";
	private String playerName = PLAYER_X;
	private String XorO;

	public void computerMove(JButton[][] button, String XorO) /* X */{
		XorO = changeXorO(XorO); // O
		setPlayerName(PLAYER_O);
		JButton computerButton = findThirdEmptyInARow(button, XorO);
		if (computerButton == null) {
			computerButton = findThirdEmptyInARow(button, changeXorO(XorO)); // X
			changeXorO(XorO); // O
			if (computerButton == null) {
				computerButton = placeInCenter(button, XorO);
				if (computerButton == null) {
					computerButton = placeInCorner(button, XorO);
					if (computerButton == null) {
						computerButton = placeOnSide(button, XorO);
					}
				}
			}
		}
		computerButton.setText(XorO);
	}

	public String findWinnerName(JButton[][] button) {
		if (hasWinningCombination(button, XorO)) {
			return (playerName == PLAYER_X) ? PLAYER_O : PLAYER_X;
		}
		return null;
	}

	public boolean hasWinningCombination(JButton[][] button, String XorO) {
		return threeHorizontal(button, XorO) ? true : (threeVertical(button,
				XorO) ? true : (threeDiagonal(button, XorO) ? true : false));
	}

	private boolean threeHorizontal(JButton[][] button, String XorO) {

		for (int i = 0; i < button.length; i++) {
			for (int j = 0, counter = 0; j < button[i].length; j++) {
				if (button[i][j].getText() == XorO) {
					counter += 1;
				} else {
					break;
				}
				if (counter == button.length) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean threeVertical(JButton[][] button, String XorO) {

		for (int i = 0; i < button.length; i++) {
			for (int j = 0, counter = 0; j < button[i].length; j++) {
				if (button[j][i].getText() == XorO) {
					counter = counter + 1;
				} else {
					break;
				}
				if (counter == button.length) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean threeDiagonal(JButton[][] button, String XorO) {

		for (int i = 0, counter = 0; i < button.length; i++) {
			int j = i;
			if (button[i][j].getText() == XorO) {
				counter = counter + 1;
			} else {
				break;
			}
			if (counter == button.length) {
				return true;
			}

		}

		for (int i = 0, counter = 0; i < button.length; i++) {
			int j = (button[i].length - 1) - i;
			if (button[i][j].getText() == XorO) {
				counter = counter + 1;
			} else {
				break;
			}
			if (counter == button.length) {
				return true;
			}
		}

		return false;
	}

	public boolean findEmptyCells(JButton[][] button) {

		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[i].length; j++) {
				if (button[i][j].getText() == "") {
					return true;
				}
			}
		}
		return false;
	}

	private JButton findThirdEmptyInARow(JButton[][] button, String XorO) {
		for (int i = 0; i < button.length; i++) {
			for (int j = 0, counter = 0; j < button[i].length; j++) {
				if (button[i][j].getText() == XorO) {
					counter = counter + 1;
				}
				if (counter == 2) {
					for (int k = 0; k < button[i].length; k++) {
						if (button[i][k].getText() == "") {
							return button[i][k];
						}
					}
				}
			}
		}

		for (int i = 0; i < button.length; i++) {
			for (int j = 0, counter = 0; j < button[i].length; j++) {
				if (button[j][i].getText() == XorO) {
					counter = counter + 1;
				}
				if (counter == 2) {
					for (int k = 0; k < button[i].length; k++) {
						if (button[k][i].getText() == "") {
							return button[k][i];
						}
					}
				}
			}
		}

		for (int i = 0, j = button.length - 1, counter = 0; i < button.length
				&& j >= 0; i++, j--) {
			if (button[j][i].getText() == XorO) {
				counter = counter + 1;
			}
			if (counter == 2) {
				for (int k = 0, l = button.length - 1; k < button.length
						&& l >= 0; k++, l--) {
					if (button[k][l].getText() == "")
						return button[k][l];
				}
			}

		}

		for (int i = 0, counter = 0; i < button.length; i++) {
			int j = i;
			if (button[i][j].getText() == XorO) {
				counter = counter + 1;
			}
			if (counter == 2) {
				for (int k = 0; k < button.length; k++) {
					int l = k;
					if (button[k][l].getText() == "") {
						return button[k][l];
					}
				}
			}
		}

		return null;
	}

	private JButton placeInCenter(JButton[][] button, String XorO) {
		int i = (button.length - 1) / 2;
		int j = (button[i].length - 1) / 2;
		return (button[i][j].getText() == "") ? button[i][j] : null;
	}

	private JButton placeInCorner(JButton[][] button, String XorO) {
		return button[0][0].getText() == "" ? button[0][0]
				: (button[button.length - 1][button.length - 1].getText() == "" ? button[button.length - 1][button.length - 1]
						: (button[0][button.length - 1].getText() == "" ? button[0][button.length - 1]
								: (button[button.length - 1][0].getText() == "" ? button[button.length - 1][0]
										: null)));
	}

	private JButton placeOnSide(JButton[][] button, String XorO) {
		return button[0][(button.length - 1) / 2].getText() == "" ? button[0][(button.length - 1) / 2]
				: (button[(button.length - 1) / 2][button.length - 1].getText() == "" ? button[(button.length - 1) / 2][button.length - 1]
						: (button[button.length - 1][(button.length - 1) / 2]
								.getText() == "" ? button[button.length - 1][(button.length - 1) / 2]
								: (button[(button.length - 1) / 2][0].getText() == "" ? button[(button.length - 1) / 2][0]
										: null)));
	}

	String changeXorO(String XorO) {
		return XorO == X ? (XorO = O) : (XorO = X);
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getX() {
		return X;
	}

	public String getO() {
		return O;
	}

	public String getXorO() {
		return XorO;
	}

	public void setXorO(String xorO) {
		XorO = xorO;
	}

	public void reset(JButton[][] button) {
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[i].length; j++) {
				button[i][j].setText("");
			}
		}
		setPlayerName(PLAYER_X);
	}
}
