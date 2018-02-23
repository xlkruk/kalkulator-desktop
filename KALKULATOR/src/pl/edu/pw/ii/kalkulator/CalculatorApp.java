package pl.edu.pw.ii.kalkulator;

import pl.edu.pw.ii.kalkulator.controller.CalculatorController;
import pl.edu.pw.ii.kalkulator.controller.Controller;
import pl.edu.pw.ii.kalkulator.model.CalculatorFactory;
import pl.edu.pw.ii.kalkulator.model.CalculatorInteface;

public class CalculatorApp {
	public static void main(String[] args) {
		CalculatorInteface calculator = CalculatorFactory.getCalculator();
		CalculatorController controller = new Controller(calculator);
	}

}