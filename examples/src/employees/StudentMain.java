package employees;

import java.util.PriorityQueue;

public class StudentMain {

	public static void main(String[] args) {
		Student s1 = new Student(50, "Chris", "Cary", "The Art of Cool");
		Student s2 = new Student(40, "Katie", "Cary", "How to Deal With Being Cute");
		Student s3 = new Student(30, "John", "Thomasville", "Math");
		Student s4 = new Student(20, "John", "Thomasville", "Math");
		Student s5 = new Student(10, "John", "Thomasville", "Math");

		PriorityQueue<Student> myQueue = new PriorityQueue<Student>();
		myQueue.add(s1);
		myQueue.add(s2);
		myQueue.add(s3);
		myQueue.add(s4);
		myQueue.add(s5);
		
		printQueue(myQueue);
	}
	
	
	/* this method illustrates an interesting aspect of PriorityQueues */
	public static void printQueue(PriorityQueue<Student> pq) {
		/* 
		 * The queue is implemented as a heap, which is a tree
		 * This is a loosely-organized structure where each parent node
		 * is lower in value than its children nodes
		 * 
		 * The loose organization explains why simply printing from an 
		 * iterator doesn't accurately represent order
		 * 
		 * in this example, the tree looks like:
		 * 		10
		 * 	   /  \
		 *    20  40
		 *   /  \
		 *  50  30 
		 */
		System.out.println("Iterator Print");
		pq.forEach(System.out::println);
		
		
		/*
		 * When removing a node from the queue, we get the "least" value
		 * (i.e. the one at the top)
		 * the queue heap tree is then re-organized to maintain the order
		 */
		System.out.println("Pop Print");
		int length = pq.size();
		for(int i=0; i<length; i++) {
			System.out.println(pq.remove());
		}
	}
}
