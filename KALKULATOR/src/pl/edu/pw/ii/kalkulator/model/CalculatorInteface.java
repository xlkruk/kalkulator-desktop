package pl.edu.pw.ii.kalkulator.model;

import pl.edu.pw.ii.kalkulator.model.state.State;
import pl.edu.pw.ii.kalkulator.polecenie.Command;

public interface CalculatorInteface extends CalculatorStateInterface, CalculatorModelInterface{
	public void setPendingOperation(Command operation);

	public Command getPendingOperation();

	public void setError(boolean error);

	public void reset();

	public void setDisplay(String s);

	public void setCurrentState(State currState);

	public void setResult(double num);

	public void setCurrentOperand(double a);

	public void setSecondOperand(double b);

	public void processErr();
	
	public boolean hasPendingOperation();

}
