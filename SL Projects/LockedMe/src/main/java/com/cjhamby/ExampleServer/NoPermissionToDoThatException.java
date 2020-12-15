package com.cjhamby.ExampleServer;


public class NoPermissionToDoThatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoPermissionToDoThatException(String msg) {
		super(msg);
	}
	
}
