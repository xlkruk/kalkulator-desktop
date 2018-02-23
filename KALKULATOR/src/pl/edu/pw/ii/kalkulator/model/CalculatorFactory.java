package pl.edu.pw.ii.kalkulator.model;

public class CalculatorFactory {
	public static CalculatorInteface getCalculator() {
		return CalculatorImpl.getInstance();
	}

	public static CalculatorInteface getCalculator(String s) {
		return CalculatorImpl.getInstance();
	}
}
