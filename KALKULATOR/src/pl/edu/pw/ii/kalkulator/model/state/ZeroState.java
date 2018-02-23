package pl.edu.pw.ii.kalkulator.model.state;

import java.math.BigDecimal;

import pl.edu.pw.ii.kalkulator.model.CalculatorException;
import pl.edu.pw.ii.kalkulator.model.CalculatorInteface;
import pl.edu.pw.ii.kalkulator.polecenie.Command;
import pl.edu.pw.ii.kalkulator.polecenie.EmptyCommand;

public class ZeroState implements State {
	private CalculatorInteface calculator;

	public ZeroState(CalculatorInteface calculator) {
		this.calculator = calculator;
	}

	@Override
	public void enterZero() {
		calculator.setDisplay("0");
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterNonZeroDigit(int digit) {
		calculator.setDisplay(Integer.toString(digit));
		calculator.setCurrentState(calculator.getAccumulateDigitState());
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterDecimalSeparator() {
		calculator.setDisplay(0 + ".");
		calculator.setCurrentOperand(0);
		calculator.setCurrentState(calculator.getAccumulateDecimalState());
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterMathOperator(Command operator) {
		try {
			calculator.setCurrentOperand(Double.parseDouble(calculator.getDisplay()));
			calculator.getPendingOperation().compute();
			calculator.setPendingOperation(operator);
			calculator.setCurrentState(calculator.getComputedState());

		} catch (CalculatorException e) {
			calculator.processErr();
		}
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterEquals() {
		try {
			calculator.setSecondOperand(calculator.getCurrentOperand());
			calculator.setCurrentOperand(Double.parseDouble(calculator.getDisplay()));
			calculator.getPendingOperation().compute();
			calculator.setPendingOperation(new EmptyCommand());
			calculator.setDisplay(new BigDecimal(calculator.getResult()).stripTrailingZeros().toPlainString());

		} catch (CalculatorException e) {
			calculator.processErr();
		}
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterClear() {
		calculator.reset();
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterSignChange() {
		calculator.setDisplay(new BigDecimal((-1) * Double.parseDouble(calculator.getDisplay())).stripTrailingZeros()
				.toPlainString());// formatowanie
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterUnaryOperator(Command operator) {
		try {
			calculator.setCurrentOperand(Double.parseDouble(calculator.getDisplay()));
			if (calculator.hasPendingOperation())
				calculator.setSecondOperand(calculator.getResult());
			double tmp = calculator.getSecondOperand();
			operator.compute();
			calculator.setCurrentOperand(calculator.getResult());
			calculator.setDisplay(new BigDecimal(calculator.getResult()).stripTrailingZeros().toPlainString());// formatowanie
																												// display
			calculator.setCurrentOperand(tmp);
		} catch (CalculatorException e) {
			calculator.processErr();
		}
		calculator.notifyDisplayObservers();
	}

}
