package pl.edu.pw.ii.kalkulator.polecenie;

import pl.edu.pw.ii.kalkulator.model.CalculatorInteface;

public class PlusCommand extends BinaryOperation {
	CalculatorInteface calculator;

	public PlusCommand(CalculatorInteface calculator) {
		super(calculator);
		this.calculator=calculator;
	}

	@Override
	public double computeBinaryOperation() {
		return calculator.getCurrentOperand()+calculator.getSecondOperand();
	}

}
