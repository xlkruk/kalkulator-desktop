package pl.edu.pw.ii.kalkulator.model.state;

import java.math.BigDecimal;

import pl.edu.pw.ii.kalkulator.model.CalculatorException;
import pl.edu.pw.ii.kalkulator.model.CalculatorInteface;
import pl.edu.pw.ii.kalkulator.polecenie.Command;
import pl.edu.pw.ii.kalkulator.polecenie.EmptyCommand;

public class AccumulateDigitState implements State {
	private CalculatorInteface calculator;

	public AccumulateDigitState(CalculatorInteface calculator) {
		this.calculator = calculator;
	}

	@Override
	public void enterZero() {
		calculator.setDisplay("" + calculator.getDisplay() + "0");
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterNonZeroDigit(int digit) {
		calculator.setDisplay("" + calculator.getDisplay() + digit);
		calculator.notifyDisplayObservers();

	}

	@Override
	public void enterDecimalSeparator() {
		calculator.setCurrentOperand(Double.parseDouble(calculator.getDisplay()));
		calculator.setDisplay(calculator.getDisplay() + ".");
		calculator.setCurrentState(calculator.getAccumulateDecimalState());
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterMathOperator(Command operator) {
		try {
			if (calculator.hasPendingOperation()) {
				calculator.setSecondOperand(calculator.getResult());
			} else {
				calculator.setSecondOperand(calculator.getCurrentOperand());
			}
			calculator.setCurrentOperand(Double.parseDouble(calculator.getDisplay()));
			calculator.getPendingOperation().compute();
			if (calculator.hasPendingOperation()) {
				calculator.setDisplay(new BigDecimal(calculator.getResult()).stripTrailingZeros().toPlainString());// formatowanie}
				calculator.setCurrentOperand(calculator.getResult());
			} // display
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
			if (calculator.hasPendingOperation()) {
				calculator.setSecondOperand(calculator.getResult());
			} else {
				calculator.setSecondOperand(calculator.getCurrentOperand());
			}
			calculator.setCurrentOperand(Double.parseDouble(calculator.getDisplay()));
			calculator.getPendingOperation().compute();
			calculator.setPendingOperation(new EmptyCommand());
			calculator.setCurrentState(calculator.getZeroState());
			calculator.setDisplay(new BigDecimal(calculator.getResult()).stripTrailingZeros().toPlainString());// formatowanie
																												// display
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
			double operand = calculator.getCurrentOperand();
			calculator.setCurrentOperand(Double.parseDouble(calculator.getDisplay()));
			if (calculator.hasPendingOperation()) {
				calculator.setSecondOperand(calculator.getResult());
			} else {
				calculator.setSecondOperand(operand);
			}
			double tmp = calculator.getSecondOperand();
			operator.compute();

			calculator.setDisplay(new BigDecimal(calculator.getResult()).stripTrailingZeros().toPlainString());
			calculator.setCurrentOperand(tmp);
			calculator.setCurrentState(calculator.getZeroState());

		} catch (CalculatorException e) {
			calculator.processErr();
		}
		calculator.notifyDisplayObservers();
	}

}
