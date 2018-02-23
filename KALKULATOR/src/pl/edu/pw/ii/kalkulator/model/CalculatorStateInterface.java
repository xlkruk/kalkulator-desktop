package pl.edu.pw.ii.kalkulator.model;

import pl.edu.pw.ii.kalkulator.model.state.State;

public interface CalculatorStateInterface {
	public State getErrorState();

	public void setErrorState(State errorState);

	public State getAccumulateDecimalState();

	public void setAccumulateDecimalState(State accumulateDecimalState);

	public State getAccumulateDigitState();

	public void setAccumulateDigitState(State accumulateDigitState);

	public State getZeroState();

	public void setZeroState(State zeroState);

	public State getComputedState();

	public void setComputedState(State statecomputedState);
	
	public void notifyDisplayObservers();
}
