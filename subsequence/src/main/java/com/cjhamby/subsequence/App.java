package com.cjhamby.subsequence;

import java.util.Set;
import java.util.TreeSet;

/*
 * Algorithm to find the longest increasing subsequence
 * 
 * done without recursion, for the added pain
 */
public class App {

	/*
	 * demonstrates the algorithm using a randomized array with 100 values between
	 * -100 and 100
	 */
	public static void main(String[] args) {
		int[] unsortedList = new int[20];
		randomizeArray(unsortedList, 0, 50);

		System.out.println("Finding longest subsequence in list: ");
		printArray(unsortedList);
		getLongestRisingSubsequence(unsortedList);
	}

	/* specify lower and upper bounds */
	public static void randomizeArray(int[] array, int low, int high) {
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * (high - low) + low);
		}
	}

	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	/*
	 * find the longest subsequence of every parent node see documentation for an
	 * illustrated example
	 */
	public static void getLongestRisingSubsequence(int[] list) {
		int numEntries = list.length;
		Set<Tree> allTrees = new TreeSet<>();

		/* if list[i] is in a subsequence, it will never be a tree-head */
		boolean[] isInSubsequence = new boolean[numEntries];

		/* create all the possible trees as needed */
		for (int i = 0; i < numEntries; i++) {
			if (isInSubsequence[i]) { /* don't touch child-ed values */
				continue;             /* these are not potential tree-heads */
			}
			Tree newTree = new Tree();

			for (int j = i; j < numEntries; j++) { /* create trees */
				if (newTree.addToTree(list[j])) {
					isInSubsequence[i] = true;     /* flag children values */
				}
			}
			allTrees.add(newTree); /* collect all trees */
		}

		/* printing stuff below --- */
		int maxLength = 0;
		int numSubsequences = 0;
		System.out.println("-----------------------------------");
		System.out.println("The longest subsequence(s) found:");
		for (Tree tree : allTrees) {
			if (tree.getMaxLevel() >= maxLength) {
				maxLength = tree.getMaxLevel();
				tree.printLongestSubsequence();
				numSubsequences += tree.getNumOfSubsequences();
			} else {
				System.out.println();
				System.out.println("Found (" + numSubsequences + ") sequences of length: " + (maxLength + 1));
				System.out.println("-----------------------------------");
				break;
			}
		}
	}
}
