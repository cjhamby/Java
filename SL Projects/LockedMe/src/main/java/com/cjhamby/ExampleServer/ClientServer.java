package com.cjhamby.ExampleServer;

import java.nio.file.Paths;

/*
 * this class represents the client machine
 * which is a file server (it has files)
 * and also has a LockedMe server to send files to
 */
public class ClientServer extends FileServer {

    public ClientServer() {
    	 label = "USER\\";	/* formatting formality */
    }
    
    /* pretend that this is the user's home directory */
	protected void locateRootDirectory() {
		serverRootPath = Paths.get(System.getProperty("user.dir"), "resources", "client");
		/*
		 * alternatively, use the actual user home directory
		 */
		//userRootPath = Paths.get(System.getProperty("user.home"));
		
	}
}
