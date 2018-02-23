package pl.edu.pw.ii.kalkulator.polecenie;

import pl.edu.pw.ii.kalkulator.model.CalculatorException;
import pl.edu.pw.ii.kalkulator.model.CalculatorInteface;

public abstract class UnaryOperation implements Command {
	private CalculatorInteface calculator;
	private boolean isPending = false;
	
	public boolean isPendingOperation(){
		return isPending;
	};
	
	public UnaryOperation(CalculatorInteface calculator) {
		this.calculator = calculator;
	}
	
	public abstract double computeUnaryOperation() throws CalculatorException;
	
	@Override
	public void compute() throws CalculatorException {
		isPending=true;
		calculator.setResult(this.computeUnaryOperation());		
	}
	
	public boolean isUnary(){
		return true;
	}

}
