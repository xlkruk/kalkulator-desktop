package pl.edu.pw.ii.kalkulator.polecenie;

import pl.edu.pw.ii.kalkulator.model.CalculatorException;
import pl.edu.pw.ii.kalkulator.model.CalculatorInteface;

public class SquareCommand extends UnaryOperation {
	CalculatorInteface calculator;

	public SquareCommand(CalculatorInteface calculator) {
		super(calculator);
		this.calculator=calculator;
	}

	@Override
	public double computeUnaryOperation() throws CalculatorException{
		if (calculator.getCurrentOperand()<0)
			throw new CalculatorException("pierwiastek z liczby ujemnej");
		calculator.setCurrentOperand( Math.sqrt(Double.parseDouble(calculator.getDisplay())));
		return calculator.getCurrentOperand();
	}

}
