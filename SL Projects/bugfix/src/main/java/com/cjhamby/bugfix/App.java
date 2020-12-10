/*
 * Fix the bugs in the provided code
 */

package com.cjhamby.bugfix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Code copied from Simplilearn github
 * 
 * added function #4 to sort expenditures
 * added function #5 to search for a particular expenditure
 */
public class App {

	public static void main(String[] args) {
		System.out.println("\n**************************************\n");
		System.out.println("\tWelcome to TheDesk \n");
		System.out.println("**************************************");
		
		try {
			optionsSelection();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input, exiting program");
		} catch (Exception e) {
			System.out.println("Something happened- sorry");
		}
	}

	private static void optionsSelection() throws InputMismatchException {
		String[] options = { "1. I wish to review my expenditure", 
				"2. I wish to add my expenditure",
				"3. I wish to delete my expenditure", 
				"4. I wish to sort the expenditures",
				"5. I wish to search for a particular expenditure", 
				"6. Close the application" };
		//int[] arr1 = { 1, 2, 3, 4, 5, 6 };
		int numOptions = options.length;
		
		// ArrayList<Integer> arrlist = new ArrayList<Integer>();
		ArrayList<Integer> expenses = new ArrayList<Integer>();
		expenses.add(1000);
		expenses.add(2300);
		expenses.add(45000);
		expenses.add(32000);
		expenses.add(110);
		// expenses.addAll(arrlist);
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			
			for (int i = 0; i < numOptions; i++) {
				System.out.println(options[i]);
			}
			System.out.println("\nEnter your choice:\t");
			int option = sc.nextInt();

			switch (option) {
				case 1:
					System.out.println("Your saved expenses are listed below: \n");
					System.out.println(expenses + "\n");
					//optionsSelection();
					break;
				case 2:
					System.out.println("Enter the value to add your Expense: \n");
					int value = sc.nextInt();
					expenses.add(value);
					System.out.println("Your value is updated\n");
					// expenses.addAll(arrlist);
					System.out.println(expenses + "\n");
					//optionsSelection();
	
					break;
				case 3:
					System.out.println(
							"You are about the delete all your expenses! \nConfirm again by selecting the same option...\n");
					int con_choice = sc.nextInt();
					if (con_choice == option) {
						expenses.clear();
						System.out.println(expenses + "\n");
						System.out.println("All your expenses are erased!\n");
					} else {
						System.out.println("Oops... try again!");
					}
					//optionsSelection();
					break;
				case 4:
					sortExpenses(expenses);
					//optionsSelection();
					break;
				case 5:
					System.out.println("Enter the expense you need to search:\t");
					int expense = sc.nextInt();
					searchExpenses(expense, expenses);
					//optionsSelection();
					break;
				case 6:
					sc.close();
					closeApp();
					break;
				default:
					System.out.println("You have made an invalid choice!");
					break;
			}
		}

	}

	private static void closeApp() {
		System.out.println("Closing your application... \nThank you!");
		System.exit(0);
	}

	/* completed this method */
	private static void searchExpenses(int expense, ArrayList<Integer> arrayList) {
		int numOccurences = 0;

		for(Integer i: arrayList) {
			if(i == expense) {
				numOccurences++;
			}
		}
		System.out.println("Found " + numOccurences + " expense(s) of amount " + expense);
	}

	/* completed this method */
	private static void sortExpenses(ArrayList<Integer> arrayList) {
		Collections.sort(arrayList);
	}
}
