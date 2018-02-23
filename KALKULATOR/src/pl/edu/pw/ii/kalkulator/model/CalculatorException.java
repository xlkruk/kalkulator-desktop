package pl.edu.pw.ii.kalkulator.model;

public class CalculatorException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String text = "";

	public CalculatorException(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
}
