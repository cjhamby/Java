package com.cjhamby.calctest;

import java.util.Scanner;
import com.cjhamby.calculator.*;

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


