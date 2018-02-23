package pl.edu.pw.ii.kalkulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import pl.edu.pw.ii.kalkulator.controller.CalculatorController;
import pl.edu.pw.ii.kalkulator.model.CalculatorModelInterface;

/**
 * A frame with a calculator panel.
 */
public class CalculatorFrame extends JFrame implements CalculatorView {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final int MIN_WIDTH = 440;
	public static final int MIN_HEIGH = 460;

	public static final int MAX_WIDTH = 1000;
	public static final int MAX_HEIGH = 800;

	int xMouse;
	int yMouse;
	JFrame calculatorFrame;
	CalculatorPanel calculatorPanel;
	CalculatorController controller;
	CalculatorModelInterface caclulator;

	public CalculatorFrame(CalculatorController controller, CalculatorModelInterface calculator) {
		ComponentResizer cr = new ComponentResizer(); //klasa napisana przez Roba Camicka https://tips4java.wordpress.com/2009/09/13/resizing-components/
		cr.setSnapSize(new Dimension(10, 10));
		cr.registerComponent(this);
		
		this.controller = controller;
		this.caclulator = calculator;
		calculatorFrame = this;
		this.getContentPane().setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(MIN_WIDTH+145, MIN_HEIGH+75));
		this.setMaximumSize(new Dimension(MAX_WIDTH, MAX_HEIGH));

		JPanel mainPanel = new JPanel(); // panel zewnêtrzny do narysowania ramki
		mainPanel.setBackground(Color.WHITE);

		Border outborder = new RoundedRectBorder(Color.BLACK);
		((RoundedRectBorder) outborder).setvDiam(15).sethDiam(15);// ramka zewnetrzna
		Border empty = new EmptyBorder(10, 10, 10, 10);
		CompoundBorder mainPanelBorder = new CompoundBorder(outborder, empty);
		mainPanel.setBorder(mainPanelBorder);
		mainPanel.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();

		topPanel.setBackground(Color.WHITE);
		topPanel.setBorder(new LineBorder(Color.BLACK, 1));
		GridBagLayout topPanelLayout = new GridBagLayout();
		topPanelLayout.columnWidths = new int[] { 80, 80, 80, 80, 80 };
		topPanelLayout.rowHeights = new int[] { 30 };
		GridBagConstraints topPanelRightGBC = new GridBagConstraints();
		topPanel.setLayout(topPanelLayout);

		JPanel naviPanel = new JPanel(); // panel na -+X
		naviPanel.setBackground(Color.WHITE);
		BoxLayout naviPanelLayout = new BoxLayout(naviPanel, BoxLayout.X_AXIS);
		naviPanel.setLayout(naviPanelLayout);
		topPanelRightGBC.weighty = 1;
		topPanelRightGBC.weightx = 0.2;
		topPanelRightGBC.gridx = 4;
		topPanelRightGBC.gridy = 0;
		topPanelRightGBC.gridwidth = 1;
		topPanelRightGBC.gridheight = 1;
		topPanelRightGBC.fill = GridBagConstraints.BOTH;
		topPanel.add(naviPanel, topPanelRightGBC);

		JLabel closeBtn = new JLabel("X");
		closeBtn.setFont(new Font("Times new Roman", Font.BOLD, 24));
		closeBtn.setToolTipText("Zamknij");
		closeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				System.exit(0);
			}
		});
		closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JLabel zoomInBtn = new JLabel("+");
		zoomInBtn.setToolTipText("Maksymalizuj");
		zoomInBtn.setFont(new Font("Times new Roman", Font.BOLD, 24));
		zoomInBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (zoomInBtn.getText().equals("+")) {
					setExtendedState(JFrame.MAXIMIZED_BOTH);
					zoomInBtn.setText("><");
					zoomInBtn.setToolTipText("Przywróæ w dó³");
				}else{
					setExtendedState(JFrame.NORMAL);
					zoomInBtn.setText("+");
					zoomInBtn.setToolTipText("Maksymalizuj");
				}

			}
		});
		zoomInBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));


		JLabel zoomOutBtn = new JLabel("-");
		zoomOutBtn.setFont(new Font("Times new Roman", Font.BOLD, 24));
		zoomOutBtn.setToolTipText("Minimalizuj");
		zoomOutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		zoomOutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		naviPanel.add(Box.createHorizontalStrut(5));
		naviPanel.add(zoomOutBtn);
		naviPanel.add(Box.createHorizontalGlue());
		naviPanel.add(zoomInBtn);
		naviPanel.add(Box.createHorizontalGlue());
		naviPanel.add(closeBtn);
		naviPanel.add(Box.createHorizontalStrut(5));

		GridBagConstraints topPanelLeftGBC = new GridBagConstraints();
		topPanelLeftGBC.weighty = 1;
		topPanelLeftGBC.weightx = 0.2;
		topPanelLeftGBC.gridx = 0;
		topPanelLeftGBC.gridy = 0;
		topPanelLeftGBC.gridwidth = 1;
		topPanelLeftGBC.gridheight = 1;
		topPanelLeftGBC.fill = GridBagConstraints.BOTH;
		topPanel.add(Box.createGlue(), topPanelLeftGBC);

		JPanel titlePanel = new JPanel(); // panel tytu³
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBackground(Color.WHITE);
		JLabel title = new JLabel("ZDA Kalkulator", SwingConstants.CENTER);
		title.setFont(new Font("Times new Roman", Font.BOLD, 24));
		title.setOpaque(true);
		title.setBackground(Color.WHITE);
		titlePanel.add(title, BorderLayout.CENTER);
		GridBagConstraints topPanelCenterGBC = new GridBagConstraints();
		topPanelCenterGBC.weighty = 1;
		topPanelCenterGBC.weightx = 0.6;
		topPanelCenterGBC.gridx = 1;
		topPanelCenterGBC.gridy = 0;
		topPanelCenterGBC.gridwidth = 3;
		topPanelCenterGBC.gridheight = 1;
		topPanelCenterGBC.fill = GridBagConstraints.BOTH;
		topPanel.add(titlePanel, topPanelCenterGBC);

		topPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				calculatorFrame.setLocation(x - xMouse - 10, y - yMouse - 10); // uwzglêdznienie
																				// wgrubosci
																				// ramki
																				// zewnetrznej
			}

		});

		topPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});

		// SPINANIE PANELI
		this.getContentPane().add(mainPanel, BorderLayout.CENTER); // dodanie
																	// mainPanel
																	// do ramki
		mainPanel.add(topPanel, BorderLayout.NORTH);
		calculatorPanel = new CalculatorPanel(controller, calculator);
		mainPanel.add(calculatorPanel, BorderLayout.CENTER);
		mainPanel.add(new JLabel("£ukasz Kruk : 2016", SwingConstants.CENTER), BorderLayout.SOUTH);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(CalculatorFrame.MIN_WIDTH, CalculatorFrame.MIN_HEIGH));
		this.setMaximumSize(new Dimension(2 * CalculatorFrame.MIN_WIDTH, 2 * CalculatorFrame.MIN_HEIGH));
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.pack();
		// this.setVisible(true);

	}

	public CalculatorPanel getCalculatorPanel() {
		return calculatorPanel;
	}

	public void init() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				CalculatorFrame frame = new CalculatorFrame(controller, caclulator);
				frame.setVisible(true);
			}
		});
	}

	@Override
	public void disableButtons() {
		this.calculatorPanel.disableButtons();
	}

	@Override
	public void enableButtons() {
		this.calculatorPanel.enableButtons();

	};

}
