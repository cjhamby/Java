package com.cjhamby.subsequence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/* kind of like a sorted binary tree, without the binary part */
public class Tree implements Comparable<Tree> {
	private Node head = null;          /* head of tree is at level 0 */
	private int maxLevel = -1;         /* how far down the tree goes */
	private int numOfSubsequences = 0; /* how many SS' are max length */

	/*
	 * add a value to the tree, if value is greater than at least one node on the
	 * tree returns true if successful
	 */
	public boolean addToTree(int val) {

		if (this.head == null) {		/* if the tree is empty, start here */
			this.head = new Node(val);
			maxLevel = 0;
			return true;
		}

		/* get the possible parents for the node */
		List<Node> parents = getParents(val);

		/* node cannot be added to the tree */
		if (parents.size() == 0) {
			return false;
		}

		/* add the value to the tree */
		for (Node parent : parents) {
			int childLevel = parent.addChild(val);

			/* keep track of how tall the tree is */
			if (maxLevel < childLevel) {
				maxLevel = childLevel;
			}
		}
		return true;
	}

	/*
	 * get the possible parents for the node all possible parents are on the same,
	 * bottom-most level if a parent is available at level N, there will be no
	 * parents at level M < N
	 */
	private List<Node> getParents(int childVal) {
		/* the list of potential parents */
		List<Node> parents = new ArrayList<>();

		/* this flag prevents going higher in the tree */
		boolean parentAtLevel = false;

		for (int i = maxLevel; i >= 0; i--) {
			List<Node> nodesAtLevel = getNodesAtLevel(i);
			for (Node node : nodesAtLevel) {
				if (node.getValue() < childVal) {
					parentAtLevel = true;
					parents.add(node);
				}
			}
			if (parentAtLevel) {
				break;
			}
		}
		return parents;
	}

	/* print the tree to console */
	public void printTree() {
		System.out.println("---------------------");
		System.out.println("Print Tree");
		System.out.println("---------------------");
		if (maxLevel < 0) {
			System.out.println("No Tree");
		}
		for (int k = 0; k <= maxLevel; k++) {
			System.out.print("Level " + k + ":\t");
			getNodesAtLevel(k).forEach(System.out::print);
			System.out.println();
		}
		System.out.println("---------------------");
	}

	/*
	 * print the longest subsequence(s) in the tree also for fun: counts how many
	 * subsequences of this length there are
	 */
	public void printLongestSubsequence() {
		List<String> subsequences = new ArrayList<>();

		for (Node node : getNodesAtLevel(maxLevel)) {
			Node tempNode = node;
			String tempStr = "";

			while (tempNode != null) {
				tempStr = (tempNode + " " + tempStr);
				tempNode = tempNode.getParent();
			}
			subsequences.add(tempStr);
		}

		numOfSubsequences = subsequences.size();
		for (String s : subsequences) {
			System.out.println(s);
		}
	}

	public int getNumOfSubsequences() {
		return numOfSubsequences;
	}

	/* return a list with all nodes at tree level = LEVEL */
	private List<Node> getNodesAtLevel(int level) {
		if (level > maxLevel) { /* safety check */
			return null; 		/* this shouldn't execute ever */
		}

		List<Node> parents = new ArrayList<>();
		parents.add(head);

		for (int i = 0; i < level; i++) {
			List<Node> children = new ArrayList<>();
			for (Node parent : parents) {
				for (Node child : parent.getChildren()) {
					if (child == null) { 	/* safety check */
						continue; 			/* this shouldn't execute ever */
					}
					children.add(child);
				}
			}
			parents = children;
		}
		return parents;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	/*
	 * this is used by a sortedSet later on
	 */
	@Override
	public int compareTo(Tree o) {
		return (o.getMaxLevel() - this.getMaxLevel());
	}

	/*
	 * NODE CLASS ---------------------------------------
	 * 
	 * nodes of the tree
	 * they know their parent 
	 * they can have more than 2 children
	 */
	private class Node implements Comparable<Node> {
		private int treeLevel; /* top of tree = level 0 */
		private int value;
		private Collection<Node> children;
		private Node parent;

		public Node(int val) {
			treeLevel = 0;
			value = val;
			children = new TreeSet<>();	/* prevent duplicates */
			parent = null;
		}

		/*
		 * creates a node with the given value and appropriate level returns the level
		 * of the tree this node is located at
		 * 
		 * if a node with VALUE already exists, the treeset prevents duplicates
		 */
		public int addChild(int value) {
			Node child = new Node(value);
			child.setLevel((this.treeLevel + 1));
			child.setParent(this);
			children.add(child);
			return child.getLevel();
		}

		public int getValue() {
			return value;
		}

		public int getLevel() {
			return treeLevel;
		}

		public void setLevel(int level) {
			this.treeLevel = level;
		}

		public Collection<Node> getChildren() {
			return children;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public Node getParent() {
			return parent;
		}

		@Override
		public String toString() {
			return ("(" + value + ")");
		}
		
		@Override
		public int compareTo(Node o) {
			return this.getValue() - o.getValue();
		}
		
	}
}
