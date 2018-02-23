package pl.edu.pw.ii.kalkulator.model.state;

import pl.edu.pw.ii.kalkulator.polecenie.Command;

public interface State {
	void enterZero();
	void enterNonZeroDigit(int digit);
	void enterDecimalSeparator();
	void enterMathOperator(Command operator);
	void enterEquals();
	void enterClear();
	void enterSignChange();
	void enterUnaryOperator(Command operator);
}
