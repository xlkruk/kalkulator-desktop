package pl.edu.pw.ii.kalkulator.controller;

public interface CalculatorController {
	void disableButtons();

	void enableButtons();

	void enterZero();

	void enterNonZeroDigit(int digit);

	void enterDecimalSeparator();

	void enterPlus();
	
	void enterMinus();
	void enterDivide();
	void enterMultiply();
	void enterPercent();
	void enterSqrt();
	void enterReverse();

	void enterEquals();

	void enterClear();
}
