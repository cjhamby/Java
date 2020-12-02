package com.cjhamby.emailtest;

import com.cjhamby.email.EmailList;

public class EmailTest {

	public static void main(String[] args) {
		EmailList myList = new EmailList();
		
		/* valid emails */
		myList.addEmail("ann@freemail.com");
		myList.addEmail("carl@zmail.com");
		myList.addEmail("bill@goodemail.org");
		myList.addEmail("john.smith@uniquenames.io");
		
		
		/* invalid email tests */
		myList.addEmail("@wrongmail.com"); 
		myList.addEmail("carlbozomail.com");
		myList.addEmail("a@mysiteontheinternet");
		myList.addEmail("hey@@my.site.on.the.internet");
		
		System.out.println();
		myList.printEmails();
		System.out.println();
		
		myList.findEmail("ann@freemail.com");	/* valid */
		myList.findEmail("bill@freemail.com");	/* invalid */
	}
}