package pl.edu.pw.ii.kalkulator.model.state;

import java.math.BigDecimal;

import pl.edu.pw.ii.kalkulator.model.CalculatorException;
import pl.edu.pw.ii.kalkulator.model.CalculatorInteface;
import pl.edu.pw.ii.kalkulator.polecenie.Command;
import pl.edu.pw.ii.kalkulator.polecenie.EmptyCommand;

public class ComputeState implements State {
	CalculatorInteface calculator;

	public ComputeState(CalculatorInteface calculator) {
		this.calculator = calculator;
	}

	@Override
	public void enterZero() {
		calculator.setSecondOperand(calculator.getResult());
		calculator.setCurrentState(calculator.getZeroState());
		calculator.setDisplay("0");
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterNonZeroDigit(int digit) {
		if (calculator.getResult() != 0)
			calculator.setSecondOperand(calculator.getResult());
		calculator.setDisplay(Integer.toString(digit));
		calculator.setCurrentState(calculator.getAccumulateDigitState());
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterDecimalSeparator() {
		calculator.setDisplay(0 + ".");
		calculator.setCurrentState(calculator.getAccumulateDecimalState());
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterEquals() {
		try {
			calculator.setSecondOperand(calculator.getCurrentOperand());
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
	public void enterMathOperator(Command operator) {
		try {
			calculator.setSecondOperand(calculator.getCurrentOperand());
			calculator.setCurrentOperand(Double.parseDouble(calculator.getDisplay()));
			calculator.setPendingOperation(operator);
			calculator.setDisplay(new BigDecimal(calculator.getCurrentOperand()).stripTrailingZeros().toPlainString());// formatowanie
			// display

		} catch (Exception e) {
			calculator.processErr();
		}
		calculator.notifyDisplayObservers();
	}

	@Override
	public void enterSignChange() {
		calculator.setDisplay(new BigDecimal((-1) * Double.parseDouble(calculator.getDisplay())).stripTrailingZeros()
				.toPlainString());// formatowanie
		calculator.notifyDisplayObservers();
		System.out.printf("AFTER: Operand = %s; SecondOperand = %s; Result = %s; Display = %s; nextState = %s\n",
				calculator.getCurrentOperand(), calculator.getSecondOperand(), calculator.getResult(),
				calculator.getDisplay(), calculator.getCurrentState());
	}

	@Override
	public void enterUnaryOperator(Command operator) {
		try {
			calculator.setCurrentOperand(Double.parseDouble(calculator.getDisplay()));
			operator.compute();
			calculator.setCurrentOperand(calculator.getResult());
			calculator.setDisplay(new BigDecimal(calculator.getResult()).stripTrailingZeros().toPlainString());// formatowanie
																												// display
			calculator.setCurrentState(calculator.getZeroState());

		} catch (CalculatorException e) {
			calculator.processErr();
		}
		calculator.notifyDisplayObservers();
	}

}
