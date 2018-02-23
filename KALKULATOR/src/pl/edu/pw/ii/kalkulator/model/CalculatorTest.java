package pl.edu.pw.ii.kalkulator.model;

import pl.edu.pw.ii.kalkulator.polecenie.DivideCommand;
import pl.edu.pw.ii.kalkulator.polecenie.MultiplyCommand;
import pl.edu.pw.ii.kalkulator.polecenie.PercentCommand;
import pl.edu.pw.ii.kalkulator.polecenie.PlusCommand;
import pl.edu.pw.ii.kalkulator.polecenie.ReverseSignCommand;
import pl.edu.pw.ii.kalkulator.polecenie.SquareCommand;
import pl.edu.pw.ii.kalkulator.polecenie.SubstractCommand;

public class CalculatorTest {
	public static void main(String[] args) {
		
		
		CalculatorInteface calculator = CalculatorFactory.getCalculator();
		PlusCommand plus = new PlusCommand(calculator);
		SubstractCommand minus = new SubstractCommand(calculator);
		MultiplyCommand multiply = new MultiplyCommand(calculator);
		DivideCommand divide = new DivideCommand(calculator);
		PercentCommand percent = new PercentCommand(calculator);
		SquareCommand square = new SquareCommand(calculator);
		ReverseSignCommand reverse = new ReverseSignCommand(calculator);
		
		System.out.println("Budowanie 123.1");
		calculator.getCurrentState().enterNonZeroDigit(1);
		calculator.getCurrentState().enterNonZeroDigit(2);
		calculator.getCurrentState().enterNonZeroDigit(3);
		System.out.println(calculator.getDisplay());
		calculator.getCurrentState().enterDecimalSeparator();
		System.out.println(calculator.getDisplay());
		calculator.getCurrentState().enterNonZeroDigit(1);
		System.out.println(calculator.getDisplay());
		System.out.println("przed + display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterMathOperator(plus);
		System.out.println("po +  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		calculator.getCurrentState().enterNonZeroDigit(1);
		System.out.println(calculator.getDisplay());
		System.out.println("przed =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterEquals();
		System.out.println("po =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		System.out.println("przed - display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterMathOperator(minus);
		System.out.println("po -  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		calculator.getCurrentState().enterNonZeroDigit(1);
		calculator.getCurrentState().enterNonZeroDigit(1);
		calculator.getCurrentState().enterDecimalSeparator();
		calculator.getCurrentState().enterNonZeroDigit(1);
		System.out.println(calculator.getDisplay());
		System.out.println("przed =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterEquals();
		System.out.println("po =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		System.out.println("przed * display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterMathOperator(multiply);
		System.out.println("po *  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		calculator.getCurrentState().enterNonZeroDigit(2);
		calculator.getCurrentState().enterDecimalSeparator();
		calculator.getCurrentState().enterNonZeroDigit(1);
		System.out.println(calculator.getDisplay());
		System.out.println("przed =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterEquals();
		System.out.println("po =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		
		System.out.println("przed / display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterMathOperator(divide);
		System.out.println("po /  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		calculator.getCurrentState().enterNonZeroDigit(2);
		calculator.getCurrentState().enterDecimalSeparator();
		calculator.getCurrentState().enterNonZeroDigit(1);
		System.out.println(calculator.getDisplay());
		System.out.println("przed =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterEquals();
		System.out.println("po =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		
		System.out.println("przed % display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterMathOperator(percent);
		System.out.println("po %  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		calculator.getCurrentState().enterNonZeroDigit(9);
		System.out.println(calculator.getDisplay());
		System.out.println("przed =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterEquals();
		System.out.println("po =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		
		System.out.println("przed sqrt display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterMathOperator(square);
		System.out.println("po sqrt display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		calculator.getCurrentState().enterNonZeroDigit(3);
		System.out.println(calculator.getDisplay());
		System.out.println("przed =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterEquals();
		System.out.println("po =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		
		System.out.println("przed sqrt display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterMathOperator(reverse);
		System.out.println("po sqrt display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		calculator.getCurrentState().enterNonZeroDigit(3);
		System.out.println(calculator.getDisplay());
		System.out.println("przed =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		calculator.getCurrentState().enterEquals();
		System.out.println("po =  display:"+calculator.getDisplay() + ", curr="+calculator.getCurrentOperand()+", second="+calculator.getSecondOperand()+", result=" + calculator.getResult());
		System.out.println(calculator.getDisplay());
		
		//System.out.println(0/0);
	}
}
