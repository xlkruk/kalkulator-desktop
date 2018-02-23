package pl.edu.pw.ii.kalkulator.polecenie;

import pl.edu.pw.ii.kalkulator.model.CalculatorInteface;

public class PercentCommand extends UnaryOperation {

	CalculatorInteface calculator;

	public PercentCommand(CalculatorInteface calculator) {
		super(calculator);
		this.calculator=calculator;
	}

	@Override
	public double computeUnaryOperation() {
		return calculator.getCurrentOperand()/100;
	}

}
