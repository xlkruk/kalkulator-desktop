package pl.edu.pw.ii.kalkulator.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.border.AbstractBorder;

class RoundedRectBorder extends AbstractBorder {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Color color = new Color(128, 128, 128);
	private int stroke = 1; // grubosc
	private int margin = 1; // margines
	private int hDiam = 20; // horyzontalna srednica
	private int vDiam = 20; // vertykalna srednica

	public RoundedRectBorder(Color color) {
		this.color = color;
	}

	public RoundedRectBorder() {
	}

	public int getStroke() {
		return stroke;
	}

	public RoundedRectBorder setStroke(int stroke) {
		if (stroke > 0)
			this.stroke = stroke;
		return this;
	}

	public int getMargin() {
		return margin;
	}

	public RoundedRectBorder setMargin(int margin) {
		if (margin >= 0)
			this.margin = margin;
		return this;
	}

	public int gethDiam() {
		return hDiam;
	}

	public RoundedRectBorder sethDiam(int hDiam) {
		if (hDiam >= 0)
			this.hDiam = hDiam;
		return this;
	}

	public int getvDiam() {

		return vDiam;
	}

	public RoundedRectBorder setvDiam(int vDiam) {
		if (vDiam >= 0)
			this.vDiam = vDiam;
		return this;
	}

	public RoundedRectBorder setColor(Color color) {
		this.color = color;
		return this;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		super.paintBorder(c, g, x, y, width, height);
		g.setColor(color);
		((Graphics2D) g).setStroke(new BasicStroke(stroke)); // pogrubienie
																// linii
		g.drawRoundRect(x + margin, y + margin, width - 2 * margin, height - 2 * margin, hDiam, vDiam); // rysowanie
		// zaokraglonego
		// prostok¹ta
		// jako
		// obramówki

	}

}
