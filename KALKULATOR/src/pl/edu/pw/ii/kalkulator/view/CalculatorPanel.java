package pl.edu.pw.ii.kalkulator.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import pl.edu.pw.ii.kalkulator.controller.CalculatorController;
import pl.edu.pw.ii.kalkulator.model.CalculatorModelInterface;
import pl.edu.pw.ii.kalkulator.model.DisplayObserver;

/**
 * A panel with calculator buttons and a result display.
 */
public class CalculatorPanel extends JPanel implements DisplayObserver, CalculatorView {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField display; // wyswietlacz
	private JButton[] numberButtons; // guziki z liczbami
	private JButton[] functionButtons; // guziki funkcyjne

	private CalculatorController controller;
	private CalculatorModelInterface calculator;

	private GridBagLayout layout;
	private GridBagConstraints gbc;

	public JTextField getDisplay() {
		return display;
	}

	public JButton[] getNumberButtons() {
		return numberButtons;
	}

	public JButton[] getFunctionButtons() {
		return functionButtons;
	}

	// [0]-gridx, [1]-gridy, [2]-gridwidth, [3]-gridheight
	private int[][] numBtnConstraints = new int[][] { { 0, 4, 1, 1 }, // przycisk
																		// 0
			{ 0, 3, 1, 1 }, // przycisk 1
			{ 1, 3, 1, 1 }, // przycisk 2
			{ 2, 3, 1, 1 }, // przycisk 3
			{ 0, 2, 1, 1 }, // przycisk 4
			{ 1, 2, 1, 1 }, // przycisk 5
			{ 2, 2, 1, 1 }, // przycisk 6
			{ 0, 1, 1, 1 }, // przycisk 7
			{ 1, 1, 1, 1 }, // przycisk 8
			{ 2, 1, 1, 1 } // przycisk 9
	};

	// [0]-gridx, [1]-gridy, [2]-gridwidth, [3]-gridheight
	private int[][] functionBtnConstraints = new int[][] { { 4, 0, 1, 1 }, // przycisk
																			// C
			{ 3, 1, 1, 1 }, // przycisk /
			{ 4, 1, 1, 1 }, // przycisk sqrt
			{ 3, 2, 1, 1 }, // przycisk *
			{ 4, 2, 1, 1 }, // przycisk %
			{ 3, 3, 1, 1 }, // przycisk -
			{ 4, 3, 1, 2 }, // przycisk =
			{ 1, 4, 1, 1 }, // przycisk .
			{ 2, 4, 1, 1 }, // przycisk +/-
			{ 3, 4, 1, 1 }, // przycisk +
	};

	public CalculatorPanel(CalculatorController controller, CalculatorModelInterface calculator) {
		this.controller = controller;
		this.calculator = calculator;
		calculator.addDisplayListener(this);
		this.setBorder(new LineBorder(Color.BLACK, 1));
		this.setBackground(Color.WHITE);
		layout = new GridBagLayout();
		layout.columnWidths = new int[] { 70, 70, 70, 70, 70 }; // szerokoœæ
																// kolumn
																// kalkulatora
		layout.rowHeights = new int[] { 60, 60, 60, 60, 60 }; // wysokoœæ
																// wierszy
																// kalkulatora
		this.setLayout(layout);

		gbc = new GridBagConstraints();
		gbc.weighty = 1;
		gbc.weightx = 1;

		display = new JTextField("0"); // wyswietlacz

		Border border = new RoundedRectBorder(new Color(128, 0, 0));
		Border empty = new EmptyBorder(0, 0, 0, 10);
		CompoundBorder displayBorder = new CompoundBorder(border, empty);
		display.setBorder(displayBorder);
		display.setEditable(false);
		display.setBackground(Color.WHITE);
		display.setForeground(new Color(0, 0, 128));
		Font displayFont = new Font("Times new Roman", Font.BOLD, 36);
		display.setFont(displayFont);
		display.setHorizontalAlignment(SwingConstants.RIGHT); // wyrównanie do
																// prawej
		display.setColumns(10); // liczba 8-znakowa plus znak +/- plus ,
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(1, 1, 1, 1);
		this.add(display, gbc);

		numberButtons = new JButton[10];
		Font btnFont = new Font("Times new Roman", Font.BOLD, 24);
		for (int i = 0; i < numberButtons.length; i++) {
			numberButtons[i] = new JButton("" + i);
			numberButtons[i].setBackground(Color.WHITE);
			numberButtons[i].setForeground(new Color(128, 0, 0));
			numberButtons[i].setBorder(border);
			numberButtons[i].setFont(btnFont);
			gbc.gridx = numBtnConstraints[i][0];
			gbc.gridy = numBtnConstraints[i][1];
			gbc.gridwidth = numBtnConstraints[i][2];
			gbc.gridheight = numBtnConstraints[i][3];
			gbc.fill = GridBagConstraints.BOTH;
			gbc.insets = new Insets(1, 1, 1, 1); // obramówki wokó³ przycisku

			this.add(numberButtons[i], gbc);
			if (i == 0) {
				numberButtons[i].addActionListener(e -> controller.enterZero());
				numberButtons[i].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
						.put(KeyStroke.getKeyStroke(Integer.toString(i)), "pressed" + i);
				numberButtons[i].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(96 + i, 0),
						"pressed" + i);
				numberButtons[i].getActionMap().put("pressed" + i, new AbstractAction() {

					@Override
					public void actionPerformed(ActionEvent e) {
						controller.enterZero();

					}
				});

			} else {
				numberButtons[i].addActionListener(
						e -> controller.enterNonZeroDigit(Integer.parseInt(((JButton) e.getSource()).getText())));
				numberButtons[i].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
						.put(KeyStroke.getKeyStroke(Integer.toString(i)), "pressed" + i);
				numberButtons[i].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(96 + i, 0),
						"pressed" + i);
				numberButtons[i].getActionMap().put("pressed" + i, new AbstractAction() {

					@Override
					public void actionPerformed(ActionEvent e) {
						controller.enterNonZeroDigit(Integer.parseInt(((JButton) e.getSource()).getText()));

					}
				});
			}
		}

		functionButtons = new JButton[10];
		functionButtons[0] = new JButton("C");
		functionButtons[0].addActionListener(e -> controller.enterClear());
		functionButtons[0].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "pressedC");
		functionButtons[0].getActionMap().put("pressedC", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.enterClear();

			}
		});

		functionButtons[1] = new JButton("/");
		functionButtons[1].addActionListener(e -> controller.enterDivide());
		functionButtons[1].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0), "pressedDivide");
		functionButtons[1].getActionMap().put("pressedDivide", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.enterDivide();

			}
		});

		functionButtons[2] = new JButton("sqrt");
		functionButtons[2].addActionListener(e -> controller.enterSqrt());
		functionButtons[2].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0),
				"pressedSqrt");
		functionButtons[2].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.SHIFT_MASK), "pressedSqrt");
		functionButtons[2].getActionMap().put("pressedSqrt", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.enterSqrt();

			}
		});

		functionButtons[3] = new JButton("*");
		functionButtons[3].addActionListener(e -> controller.enterMultiply());
		functionButtons[3].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), "pressedMultiply");
		functionButtons[3].getActionMap().put("pressedMultiply", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.enterMultiply();

			}
		});

		functionButtons[4] = new JButton("%");
		functionButtons[4].addActionListener(e -> controller.enterPercent());
		functionButtons[4].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.SHIFT_MASK), "pressedPercent");
		functionButtons[4].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0),
				"pressedPercent");
		functionButtons[4].getActionMap().put("pressedPercent", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.enterPercent();

			}
		});

		functionButtons[5] = new JButton("-");
		functionButtons[5].addActionListener(e -> controller.enterMinus());
		functionButtons[5].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), "pressedMinus");
		functionButtons[5].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("-"),
				"pressedMinus");
		functionButtons[5].getActionMap().put("pressedMinus", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.enterMinus();

			}
		});

		functionButtons[6] = new JButton("=");
		functionButtons[6].addActionListener(e -> controller.enterEquals());
		functionButtons[6].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0), "pressedEquals");
		functionButtons[6].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "pressedEquals");
		functionButtons[6].getActionMap().put("pressedEquals", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.enterEquals();

			}
		});

		functionButtons[7] = new JButton(".");
		functionButtons[7].addActionListener(e -> controller.enterDecimalSeparator());
		functionButtons[7].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, 0), "pressedDecimal");
		functionButtons[7].getActionMap().put("pressedDecimal", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.enterDecimalSeparator();

			}
		});

		functionButtons[8] = new JButton("+/-");
		functionButtons[8].addActionListener(e -> controller.enterReverse());
		functionButtons[8].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0),
				"pressedReverse");
		functionButtons[8].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMBER_SIGN, InputEvent.SHIFT_MASK), "pressedReverse");
		functionButtons[8].getActionMap().put("pressedReverse", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.enterReverse();

			}
		});

		functionButtons[9] = new JButton("+");
		functionButtons[9].addActionListener(e -> controller.enterPlus());
		functionButtons[9].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke('=', InputEvent.SHIFT_MASK), "pressedPlus");
		functionButtons[9].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('+'),
				"pressedPlus");
		functionButtons[9].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), "pressedPlus");
		functionButtons[9].getActionMap().put("pressedPlus", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.enterPlus();

			}
		});

		for (int i = 0; i < functionButtons.length; i++) {
			functionButtons[i].setBackground(Color.WHITE);
			functionButtons[i].setForeground(new Color(128, 0, 0));
			functionButtons[i].setBorder(border);
			functionButtons[i].setFont(btnFont);
			gbc.gridx = functionBtnConstraints[i][0];
			gbc.gridy = functionBtnConstraints[i][1];
			gbc.gridwidth = functionBtnConstraints[i][2];
			gbc.gridheight = functionBtnConstraints[i][3];
			gbc.fill = GridBagConstraints.BOTH;
			gbc.insets = new Insets(1, 1, 1, 1); // obramówki wokó³ przycisku

			this.add(functionButtons[i], gbc);
		}

	}

	@Override
	public void updateDisplay() {
		DecimalFormat df = new DecimalFormat("#.############");
		String text = calculator.getDisplay();
		try {
			if ((text.charAt(text.length() - 1) != ".".charAt(0)) && (text.charAt(text.length() - 1) != "0".charAt(0))) // sprawdzenie
																														// czy
																														// display
																														// na
																														// koñcu
																														// ma
																														// ','
																														// lub
																														// '0'
				text = df.format(Double.parseDouble(text));
		} catch (Exception e) {
		}
		this.display.setText(text.replace(".", ","));
	}

	@Override
	public void disableButtons() {
		for (JButton b : numberButtons) {
			b.setEnabled(false);
		}

		for (JButton b : functionButtons) {
			if (b.getText() != "C")
				b.setEnabled(false);
		}

	}

	@Override
	public void enableButtons() {
		for (JButton b : numberButtons) {
			b.setEnabled(true);
		}

		for (JButton b : functionButtons) {
			b.setEnabled(true);
		}

	}

}
