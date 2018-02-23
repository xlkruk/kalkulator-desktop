package pl.edu.pw.ii.kalkulator.polecenie;

import pl.edu.pw.ii.kalkulator.model.CalculatorException;
import pl.edu.pw.ii.kalkulator.model.CalculatorInteface;

public class DivideCommand extends BinaryOperation {
	CalculatorInteface calculator;

	public DivideCommand(CalculatorInteface calculator) {
		super(calculator);
		this.calculator=calculator;
	}

	@Override
	public double computeBinaryOperation() throws CalculatorException {
		if (calculator.getCurrentOperand()==0)
			throw new CalculatorException("Nie wolno dzieliæ przez 0");
		return calculator.getSecondOperand()/calculator.getCurrentOperand();
	}

}
