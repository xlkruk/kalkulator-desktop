package pl.edu.pw.ii.kalkulator.polecenie;

import pl.edu.pw.ii.kalkulator.model.CalculatorException;

public interface Command {
	void compute() throws CalculatorException;
	boolean isUnary();
	public boolean isPendingOperation();
}
