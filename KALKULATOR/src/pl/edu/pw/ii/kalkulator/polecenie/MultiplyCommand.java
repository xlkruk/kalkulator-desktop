package pl.edu.pw.ii.kalkulator.polecenie;

import pl.edu.pw.ii.kalkulator.model.CalculatorInteface;

public class MultiplyCommand extends BinaryOperation {
	CalculatorInteface calculator;
	
	public MultiplyCommand(CalculatorInteface calculator) {
		super(calculator);
		this.calculator=calculator;
	}

	@Override
	public double computeBinaryOperation() {
		return calculator.getSecondOperand()*calculator.getCurrentOperand();
	}

}
