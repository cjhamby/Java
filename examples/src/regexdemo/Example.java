/* use the testMatch helper method to learn about patterns and matchers */

package regexdemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Example {

	public static void main(String[] args) {
		
		/* convert a regex to a pattern, then search for a match 
		 * here are a few sample patterns
		 */
		Pattern p1 = Pattern.compile(".");		/* just the letter a */
		Pattern p2 = Pattern.compile("[.]");	/* a occurs 0+ times */
		Pattern p3 = Pattern.compile("a+");		/* a occurs 1+ times */
		Pattern p4 = Pattern.compile("a?");	   	/* a occurs 0 or 1 time */
		Pattern p5 = Pattern.compile("a{3}");  	/* a occurs exactly 3 times */
		Pattern p6 = Pattern.compile("a{3,}"); 	/* a occurs at least 3 times */
		Pattern p7 = Pattern.compile(".*a.*"); 	/* a surrounded by anything  */
		
		/* a in the middle of a lowercase word */
		Pattern p8 = Pattern.compile("[a-z]a[a-z]");
		
		
		/* replace these with your own strings */
		String s1 = ".";
		String s2 = "a";
		String s3 = "cat";
		
		/* arguments are: (Pattern, String) */
		testMatch(p1, s1);
		testMatch(p2, s1);
		testMatch(p1, s2);
		testMatch(p2, s2);
		//testMatch(p5, s2);
		//testMatch(p6, s2);
		//testMatch(p7, s2);
		//testMatch(p8, s3);
	}
	

	/* demonstrates Matcher functionality */
	public static void testMatch(Pattern p, String s) {
		Matcher m = p.matcher(s);
		System.out.println("String:  " + s + "\t\tPattern: " + p + "");
		
		/* print all occurances within the region (string) */
		while(m.find()) {
			System.out.println("Found at index " + m.start());
		}
		
		/* does the entire region (string) match the pattern? */
		System.out.println("matches() --> " + m.matches());
		System.out.println();
	}
	
	
	/* helper for when you don't want to make Pattern objects */
	public static void testMatch(String regex, String s) {
		Pattern p = Pattern.compile(regex);
		testMatch(p, s);
	}

}
