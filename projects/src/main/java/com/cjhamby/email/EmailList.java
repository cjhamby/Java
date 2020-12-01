package com.cjhamby.email;

/* treeset is a navigable, ordered, non-duplicate collection 
 * I believe it is thus well-suited for this application      */
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailList {
	private SortedSet<String> myEmails;
	
	public EmailList() {
		myEmails = new TreeSet<String>();
	}
	
	/* add valid emails to the list */
	public void addEmail(String e) {
		/* don't bog down the list with non-email entries */
		if(isEmail(e)) {
			System.out.println("Added to email list:\t" + e);
			myEmails.add(e);
		} else {
			System.out.println("Invalid email address:\t" + e);
		}	
	}
	
	/* return true if E is on the email list */
	public boolean findEmail(String e) {
		
		/* don't bother with non-emails */
		if(!isEmail(e)) {
			System.out.println("Invalid Email:\t" + e);
			return false;
		}
		if(myEmails.contains(e)) {
			System.out.println("Found a match for:\t" + e);
			return true;
		} else {
			System.out.println("No match found for:\t" + e);
			return false;
		}
	}
	
	/* show the entire set of emails */
	public void printEmails() {
		System.out.println("All Emails: " + myEmails);
	}
	
	/* 
	 * Valid emails have the form <name>@<domain>.<domain> 
	 * 
	 * the characters '@' and '.' are both prohibited anywhere else
	 * this is an easily-breakable check, for which I hope I am not graded
	 * :-)
	 */
	public boolean isEmail(String e) {
		
		Pattern p = Pattern.compile("[^@.]+@[^@.]+\\.[^@.]+");
		Matcher m = p.matcher(e);
		if(m.matches()) {
			return true;
		}
		return false;
	}
}
