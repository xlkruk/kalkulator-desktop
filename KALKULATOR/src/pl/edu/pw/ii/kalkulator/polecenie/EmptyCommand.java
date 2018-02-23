package pl.edu.pw.ii.kalkulator.polecenie;

import pl.edu.pw.ii.kalkulator.model.CalculatorException;

public class EmptyCommand implements Command {
	
	@Override
	public boolean isPendingOperation(){
		return false;
	};
	
	@Override
	public void compute() throws CalculatorException {

	}

	@Override
	public boolean isUnary() {
		// TODO Auto-generated method stub
		return false;
	}

}
