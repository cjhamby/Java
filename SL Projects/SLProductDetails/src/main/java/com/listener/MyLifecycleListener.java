package com.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.dal.App;

/**
 * Application Lifecycle Listener implementation class MyLifecycleListener
 *
 */
public class MyLifecycleListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public MyLifecycleListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	System.out.println("session created");
    	App.openFactory();
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	System.out.println("session destroyed");
    	App.closeFactory();
    }
	
}
