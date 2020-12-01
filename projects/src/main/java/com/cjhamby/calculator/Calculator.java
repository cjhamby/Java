package com.cjhamby.calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

	private static char operator = ' '; 		/* symbol for chosen math operation */
	private static int operation = 0;			/* input for chosen math operation */
	private static float num1 = 0;				/* first operand */
	private static float num2 = 0;				/* second operand */
	private static float result = 0;			/* operation result */
	private static boolean canCompute = true; 	/* flags invalid operations */
	
	private Scanner scan = new Scanner(System.in);
	
	public Calculator(Scanner s) {
		this.scan = s;
	}
	
	/* 
	 * Prompt for an operation selection and inputs 
	 * Perform the operation and print results
	 */
	public void run() {
		canCompute = true;
		
		System.out.println("Select an Operation:");
		System.out.println("0 == Addition");
		System.out.println("1 == Subtraction");
		System.out.println("2 == Multiplication");
		System.out.println("3 == Division");
		System.out.println("q == Quit ");
		
		
		/* try to get the inputs */
		canCompute = getInputs();
		
		if(canCompute) {
			try {
				switch(operation) {
					case 0:						/* add */
						result = num1 + num2;
						operator = '+';
						break;
					case 1:						/* subtract */
						result = num1 - num2;
						operator = '-';
						break;
					case 2:						/* multiply */
						result = num1 * num2;
						operator = 'x';
						break;
					case 3:						/* divide */
						result = num1 / num2;
						operator = '/';
						break;
					default:					/* leave */
						endProgram();
						break;
				}
				
			/* catch divide-by-0 */
			} catch (ArithmeticException e) {	
				System.out.println("DOES NOT COMPUTE");
				canCompute = false;
				
			/* fallback catch for weird events */
			} catch (Exception e) {
				System.out.println("DOES NOT COMPUTE: " + e);
				canCompute = false;
			}
		}
		
		/* only print valid results! */
		if(canCompute) {
			System.out.println(num1 + " " + operator + " " + num2 + " = " + result );
		}
		
		/* prompt user to continue */
		System.out.println("Press enter to continue");
		scan.nextLine();	/* getting the number inputs still leaves a newline char */
		scan.nextLine();	/* so, we get two newline chars before continuing */
	}
	
	
		
	/* 
	 * get the operation, and two input numbers to perform the operation with
	 * returns true if all inputs are valid  
	 */
	public boolean getInputs() {
		boolean valid = true;				   /* this method's return value */
		
		try {
			operation = scan.nextInt();		   /* get the operation number */
		} catch (InputMismatchException e) {   /* if the input is a letter */
			endProgram();					   /* the program ends */
		}
		
		/* readability formatting */
		switch(operation) {
			case 0:
				System.out.println("Addition");
				break;
			case 1:
				System.out.println("Subtraction");
				break;
			case 2:
				System.out.println("Multiplication");
				break;
			case 3:
				System.out.println("Division");
				break;
			default:			/* 0-3 are only valid entries */
				valid = false;
				break;
		}
			
		/* get the operand inputs */
		try {
			System.out.print("Enter the first number: ");
			num1 = scan.nextFloat();
			System.out.print("Enter the second number: ");
			num2 = scan.nextFloat();
		
		/* letter inputs will end the program */
		} catch (InputMismatchException e) {
			endProgram();
		}
		
		if(!valid) {
			System.out.println("Invalid Entry");	/* print feedback */
		}
		
		return valid;
	}
	
	
	/* clean way to leave the program */
	public void endProgram() {
		scan.close();
		System.out.println("Goodbye");
		System.exit(0);
	}
}