package pl.edu.pw.ii.kalkulator.model.state;

import pl.edu.pw.ii.kalkulator.model.CalculatorInteface;
import pl.edu.pw.ii.kalkulator.polecenie.Command;

public class ErrorState implements State {
	private CalculatorInteface calculator;

	public ErrorState(CalculatorInteface calculator) {
		this.calculator = calculator;
	}

	@Override
	public void enterZero() {

	}

	@Override
	public void enterNonZeroDigit(int digit) {

	}

	@Override
	public void enterDecimalSeparator() {

	}

	@Override
	public void enterMathOperator(Command operator) {

	}

	@Override
	public void enterEquals() {

	}

	@Override
	public void enterClear() {
		calculator.reset();
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterSignChange() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void enterUnaryOperator(Command operator) {
		// TODO Auto-generated method stub
		
	}


}
