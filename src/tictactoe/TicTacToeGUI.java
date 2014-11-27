package tictactoe;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TicTacToeGUI extends JFrame {
	private JButton[][] button;
	private JLabel currentPlayerLabel;
	private JPanel buttonsPanel;
	private TicTacToeLogic logic;

	public TicTacToeGUI() {
		logic = new TicTacToeLogic();
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(4, 3));
		currentPlayerLabel = new JLabel(logic.getPlayerName(),
				SwingConstants.CENTER);
		Font buttonFont = new Font("Times New Roman", Font.BOLD, 90);
		button = new JButton[3][3];
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[i].length; j++) {
				button[i][j] = new JButton();
				button[i][j].setFont(buttonFont);
				button[i][j].addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent me) {
						JButton currentButton = (JButton) me.getComponent();
						if (currentButton.getText() != "") {
							return;
						}
						logic.setXorO(TicTacToeLogic.X); // X
						currentButton.setText(logic.getXorO());
						checkWorkflowCondition(button, TicTacToeLogic.X);
						logic.computerMove(button, logic.getXorO());
						logic.changeXorO(logic.getXorO());
						checkWorkflowCondition(button, TicTacToeLogic.O); // X
					}
				});
				buttonsPanel.add(button[i][j]);
			}
		}
		logic.setPlayerName(TicTacToeLogic.PLAYER_X);
		setPlayerNotificationOnLabel(logic.getPlayerName());
		buttonsPanel.add(currentPlayerLabel);
		add(buttonsPanel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(400, 450);
	}

	private void checkWorkflowCondition(JButton[][] button, String XorO) {
		System.out.println(XorO);
		if (logic.hasWinningCombination(button, XorO)) {
			displayWinningNotification();
		} else if (!logic.findEmptyCells(button)) {
			logic.reset(button);
		}
	}

	private void displayWinningNotification() {
		String[] str = { "OK" };
		String winnerName = (logic.getXorO().equals(TicTacToeLogic.X)) ? TicTacToeLogic.PLAYER_X
				: TicTacToeLogic.PLAYER_O;
		JOptionPane.showOptionDialog(this,
				winnerName.concat(" won!!! Congratulations!!!"),
				"Congratulations!", JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, str, " OK");
		logic.reset(button);
	}

	public void setPlayerNotificationOnLabel(String playerName) {
		currentPlayerLabel.setText(playerName + ", your turn.");
	}

	public TicTacToeLogic getLogic() {
		return logic;
	}

	public void setLogic(TicTacToeLogic logic) {
		this.logic = logic;
	}

	public static void startApp() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new TicTacToeGUI();
			}
		});
	}
}
