package pl.edu.pw.ii.kalkulator.model;

import java.util.ArrayList;

import pl.edu.pw.ii.kalkulator.model.state.AccumulateDecimalState;
import pl.edu.pw.ii.kalkulator.model.state.AccumulateDigitState;
import pl.edu.pw.ii.kalkulator.model.state.ComputeState;
import pl.edu.pw.ii.kalkulator.model.state.ErrorState;
import pl.edu.pw.ii.kalkulator.model.state.State;
import pl.edu.pw.ii.kalkulator.model.state.ZeroState;
import pl.edu.pw.ii.kalkulator.polecenie.Command;
import pl.edu.pw.ii.kalkulator.polecenie.EmptyCommand;

public class CalculatorImpl implements CalculatorInteface {
	private ArrayList<DisplayObserver> displayObservers;

	private boolean error;
	private Command pendingOperation;

	private String display; // display

	// private Command currentOperation = new EmptyCommand();
	// wynik
	private double result = 0;
	// operandy
	private double currentOperand;
	private double secondOperand;
	// stany
	private State errorState;
	private State accumulateDecimalState;
	private State accumulateDigitState;
	private State zeroState;
	private State computedState;

	// stan pocz¹tkowy
	private State currentState;

	private static CalculatorInteface instance;

	private CalculatorImpl() {
		this.displayObservers = new ArrayList<DisplayObserver>();
		this.error = false;
		this.display = "0";
		this.pendingOperation = new EmptyCommand();
		// this.currentOperation = new EmptyCommand();
		this.result = 0;
		this.currentOperand = 0;
		this.secondOperand = 0;
		this.errorState = new ErrorState(this);
		this.accumulateDecimalState = new AccumulateDecimalState(this);
		this.accumulateDigitState = new AccumulateDigitState(this);
		this.zeroState = new ZeroState(this);
		this.computedState = new ComputeState(this);
		this.currentState = this.zeroState;
	}

	public static CalculatorInteface getInstance() {
		if (instance == null)
			instance = new CalculatorImpl();
		return instance;
	}

	@Override
	public State getCurrentState() {
		return this.currentState;
	}

	@Override
	public void setCurrentState(State csi) {
		this.currentState = csi;

	}

	@Override
	public double getResult() {
		return this.result;
	}

	@Override
	public void setResult(double num) {
		this.result = num;
	}

	@Override
	public double getCurrentOperand() {
		return this.currentOperand;
	}

	@Override
	public double getSecondOperand() {
		return this.secondOperand;
	}

	@Override
	public void setCurrentOperand(double a) {
		this.currentOperand = a;
	}

	@Override
	public void setSecondOperand(double b) {
		this.secondOperand = b;
	}

	public State getErrorState() {
		return errorState;
	}

	public void setErrorState(State errorState) {
		this.errorState = errorState;
	}

	public State getAccumulateDecimalState() {
		return accumulateDecimalState;
	}

	public void setAccumulateDecimalState(State accumulateDecimalState) {
		this.accumulateDecimalState = accumulateDecimalState;
	}

	public State getAccumulateDigitState() {
		return accumulateDigitState;
	}

	public void setAccumulateDigitState(State accumulateDigitState) {
		this.accumulateDigitState = accumulateDigitState;
	}

	public State getZeroState() {
		return zeroState;
	}

	public void setZeroState(State zeroState) {
		this.zeroState = zeroState;
	}

	public void setComputedState(State computedState) {
		this.computedState = computedState;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return this.display;
	}

	@Override
	public State getComputedState() {
		// TODO Auto-generated method stub
		return this.computedState;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	@Override
	public void reset() {
		this.currentOperand = 0;
		this.secondOperand = 0;
		this.display="0";
		this.result=0;
		this.pendingOperation = new EmptyCommand();
		this.error = false;
		this.currentState = this.getZeroState();

	}

	/*
	 * @Override public void setOperation(Command operation) {
	 * this.currentOperation = operation;
	 * 
	 * }
	 */

	@Override
	public void setPendingOperation(Command operation) {
		this.pendingOperation = operation;

	}

	/*
	 * @Override public Command getOperation() { return this.currentOperation; }
	 */
	@Override
	public Command getPendingOperation() {
		return this.pendingOperation;
	}

	@Override
	public void processErr() {
		this.setDisplay("ERR");
		this.setError(true);
		this.setCurrentState(this.getErrorState());

	}

	@Override
	public void enterZero() {
		this.getCurrentState().enterZero();

	}

	@Override
	public void enterNonZeroDigit(int  digit) {
		this.getCurrentState().enterNonZeroDigit(digit);

	}

	@Override
	public void enterDecimalSeparator() {
		this.getCurrentState().enterDecimalSeparator();

	}

	@Override
	public void enterMathOperator(Command operator) {
		this.getCurrentState().enterMathOperator(operator);

	}

	@Override
	public void enterEquals() {
		this.getCurrentState().enterEquals();

	}

	@Override
	public void enterClear() {
		this.getCurrentState().enterClear();
	}

	@Override
	public void addDisplayListener(DisplayObserver o) {
		this.displayObservers.add(o);

	}

	@Override
	public void removeDisplayListener(DisplayObserver o) {
		int i = displayObservers.indexOf(o);
		if (i >= 0) {
			this.displayObservers.remove(o);
		}

	}

	public void notifyDisplayObservers(){
		for(DisplayObserver d: displayObservers){
			d.updateDisplay();
		}
	}

	@Override
	public void enterReverseSign() {
		this.getCurrentState().enterSignChange();
		
	}

	@Override
	public void enterUnaryOperator(Command operator) {
		this.getCurrentState().enterUnaryOperator(operator);
		
	}

	@Override
	public boolean hasPendingOperation() {
		// TODO Auto-generated method stub
		return this.getPendingOperation().isPendingOperation();
	}
}
