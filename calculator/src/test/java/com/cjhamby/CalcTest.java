package com.cjhamby;
import java.util.Scanner;

public class CalcTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Calculator calc = new Calculator(scan);
		
		/* run until an exit condition occurs */
		while(true) {
			calc.run();
		}
	}

}


