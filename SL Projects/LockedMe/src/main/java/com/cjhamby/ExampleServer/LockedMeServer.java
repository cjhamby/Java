package com.cjhamby.ExampleServer;

import java.nio.file.Paths;

/* 
 * the backend of the application
 */
public class LockedMeServer extends FileServer {
	
	public LockedMeServer() {
		label = "LOCKEDME\\";
	}
	/*
	 * this is where the files are stored
	 * pretend that it's server-side
	 */
	public void locateRootDirectory() {
		serverRootPath = Paths.get(System.getProperty("user.dir"), "resources", "server");
	}
}
