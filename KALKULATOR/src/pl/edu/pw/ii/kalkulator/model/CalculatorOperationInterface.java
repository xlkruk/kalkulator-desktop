package pl.edu.pw.ii.kalkulator.model;

import pl.edu.pw.ii.kalkulator.polecenie.Command;

public interface CalculatorOperationInterface {
	void enterZero();
	void enterNonZeroDigit(int digit);
	void enterDecimalSeparator();
	void enterMathOperator(Command operator);
	void enterEquals();
	void enterClear();
	void enterReverseSign();
	void enterUnaryOperator(Command operator);
	}
