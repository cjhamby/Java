package com.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.utility.HibernateUtility;

public class MyListener implements HttpSessionListener {

    public MyListener() {
        // TODO Auto-generated constructor stub
    }

    public void sessionCreated(HttpSessionEvent se)  { 
         HibernateUtility.createFactory();
         System.out.println("listener created factory");
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
         HibernateUtility.closeFactory();
         System.out.println("listener destroyed factory");
    }
	
}
