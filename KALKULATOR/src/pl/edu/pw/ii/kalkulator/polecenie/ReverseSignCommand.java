package pl.edu.pw.ii.kalkulator.polecenie;

import pl.edu.pw.ii.kalkulator.model.CalculatorInteface;

public class ReverseSignCommand extends UnaryOperation {

	CalculatorInteface calculator;

	public ReverseSignCommand(CalculatorInteface calculator) {
		super(calculator);
		this.calculator=calculator;
	}

	@Override
	public double computeUnaryOperation() {
		calculator.setCurrentOperand((-1)*Double.parseDouble(calculator.getDisplay()));
		return calculator.getCurrentOperand();
	}

}
