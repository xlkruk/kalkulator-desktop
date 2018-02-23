package pl.edu.pw.ii.kalkulator.model;

import pl.edu.pw.ii.kalkulator.model.state.State;

public interface CalculatorModelInterface extends CalculatorOperationInterface{

	public double getCurrentOperand();

	public double getSecondOperand();
	
	public double getResult();

	public State getCurrentState();
	
	public String getDisplay();
	
	public boolean isError();
	
	public void addDisplayListener(DisplayObserver o);
	
	public void removeDisplayListener(DisplayObserver o);
}
