package pl.edu.pw.ii.kalkulator.polecenie;

import pl.edu.pw.ii.kalkulator.model.CalculatorException;
import pl.edu.pw.ii.kalkulator.model.CalculatorInteface;

public abstract class BinaryOperation implements Command {
	private CalculatorInteface calculator;
	private boolean isPending = false;
	
	public boolean isPendingOperation(){
		return isPending;
	};
	
	public BinaryOperation(CalculatorInteface calculator) {
		this.calculator = calculator;
	}
	
	public abstract double computeBinaryOperation() throws CalculatorException;
	
	@Override
	public void compute() throws CalculatorException {
		isPending = true;
		calculator.setResult(this.computeBinaryOperation());		
	}

	public boolean isUnary(){
		return false;
	}
}
