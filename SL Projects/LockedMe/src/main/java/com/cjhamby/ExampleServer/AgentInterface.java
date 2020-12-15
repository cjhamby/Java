package com.cjhamby.ExampleServer;

import java.nio.file.Path;


/* methods called by the ClientApp */
public interface AgentInterface {
	
	/* formatted print method */
	public void printWorkingDirectory();
	
	/* returns the Path (workingDirectory/string) */
	public Path stringToAbsPath(String s);
	
	/* returns the working directory as a relative Path object */
	public Path getWorkingDirectory();
	
	/* returns the working directory as an absolute Path object */
	public Path getAbsWorkingDirectory();
	
	/* change the working directory */
	public boolean moveToSubdirectory(String s) throws NoPermissionToDoThatException;
	
	/* add file at working directory */
	public void addFileToServer(Path filePath);
	
	/* remove file from working directory */
	public void removeFileFromServer(String name);
	
	
	
	/* will not create an existing directory */
	public void makeSubdirectory(String name);
	
	/* list all files in the file server */
	public void listAllFiles();
	
	/* list all subdirectories of the current working path */
	public void listSubdirectories();
	
	/* list files/subdirectories in the current working path */
	public void listWorkingDirectory();
	
	/* verifies that FILE is an existing file in the current working path */
	public boolean verifyFile(String file);
	
	/* verify that newDir is a subdirectory of the working path */
	public boolean verifySubdirectory(String newDir);
	
	/*
	 * file is a case-insensitive name of a file in the directory 
	 * returns the case-sensitive file if it exists
	 */
	public String getFileName(String file);
	
	/*
	 * returns the path to workingDirectory/file, if file exists 
	 * this is useful for case-insensitive tasks 
	 */
	public Path getFilePath(String file);
	
	/*
	 * case-sensitive search through whole file system
	 * prints the results (anything containing "name")
	 */
	public void searchForFile(String name);
}
