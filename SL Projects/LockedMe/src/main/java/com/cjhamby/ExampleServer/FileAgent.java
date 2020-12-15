package com.cjhamby.ExampleServer;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;

/*
 * The FileAgent class has a working directory path
 * 
 * it includes methods to add/remove/search relative to the working path
 * The goal is to not use Path objects in ClientApp (UI Layer)
 * 
 * does not ordinarily print messages related to successes
 * only prints messages related to errors, and search results
 */
public class FileAgent implements AgentInterface {
	private FileServer fileServer = null;
	private Path serverPath = null;
	private Path workingPath = null;
	
	
	public FileAgent(FileServer fs) {
		this.fileServer = fs;
		this.serverPath = fileServer.getRootPath();
		this.workingPath = fileServer.getRootPath();
	}
	
	
	/* 
	 * returns the Path (workingDirectory/string) 
	 * doesn't check if the path actually exists
	 */
	public Path stringToAbsPath(String s) {
		return (getAbsWorkingDirectory().resolve(s));
	}
	
	/* returns the working directory as a relative Path object */
	public Path getWorkingDirectory() {
		return serverPath.relativize(workingPath);
	}
	
	/* returns the working directory as an absolute Path object */
	public Path getAbsWorkingDirectory() {
		return workingPath;
	}
	
	
	/*
	 * method to move the working directory
	 * '.' and '..' operations work
	 * but this prevents moving above the server path
	 */
	public boolean moveToSubdirectory(String s) throws NoPermissionToDoThatException {
		Path tempPath = workingPath.resolve(s).normalize();

		/* can't access directories above the server */
		if(tempPath.compareTo(serverPath) < 0) {
			throw new NoPermissionToDoThatException("Access Forbidden to " + tempPath);
		}
		/* verify the path exists */
		if(fileServer.verifyDirectory(tempPath)) {
			workingPath = tempPath;
			printWorkingDirectory();
			return true;
		} else {
			System.out.println("subdirectory " + s + " does not exist");
			return false;
		}
	}
	
	/* remove file from working directory */
	public void removeFileFromServer(String name) {
		fileServer.removeFileFromServer(stringToAbsPath(name));
	}
	
	/**
	 * add file at working directory 
	 * FILEPATH is an absolute path to the file, probably on the other server
	 */
	public void addFileToServer(Path filePath) {
		fileServer.addFileToServer(filePath, workingPath);
	}
	
	/* will not create an existing directory */
	public void makeSubdirectory(String name) {
		fileServer.createDirectory(stringToAbsPath(name));
	}
	
	/* list all files in the file server */
	public void listAllFiles() {
		fileServer.listFiles();
	}
	
	/* list all subdirectories of the current working path */
	public void listSubdirectories() {
		fileServer.listSubdirectories(workingPath);
	}
	
	/* list files/subdirectories in the current working path */
	public void listWorkingDirectory() {
		fileServer.listDirectory(workingPath);
	}
	
	/*
	 * verifies that FILE is an existing file in the current working path
	 * returns false if FILE is a directory
	 */
	public boolean verifyFile(String file) {
		Path pathToFile = workingPath.resolve(file);
		return fileServer.verifyFile(pathToFile);
	}
	
	/* verify that newDir is a subdirectory of the working path */
	public boolean verifySubdirectory(String newDir) {
		return fileServer.verifyDirectory((workingPath.resolve(newDir)));
	}
	
	/*
	 * file is a case-insensitive name of a file in the directory 
	 * returns the case-sensitive file if it exists
	 */
	public String getFileName(String file) {
		Set<Path> allPaths = fileServer.getFilePaths(workingPath);
		for(Path p: allPaths) {
			String temp = (p.getFileName().toString());
			if(temp.equalsIgnoreCase(file)) {
				return temp;
			}
		}
		return null;
	}
	
	
	/*
	 * returns the path to workingDirectory/file, if file exists 
	 * this is useful for case-insensitive tasks 
	 */
	public Path getFilePath(String file) {
		Set<Path> allPaths = fileServer.getFilePaths(workingPath);
		for(Path p: allPaths) {
			String temp = (p.getFileName().toString());
			if(temp.equalsIgnoreCase(file)) {
				return p;
			}
		}
		return null;
	}
	

	
	
	/* formatted print method */
	public void printWorkingDirectory() {
		System.out.println(fileServer.getLabel() + getWorkingDirectory());
	}
	
	
	/*
	 * case-sensitive search for a file
	 * anything containing "name" will return as a result
	 * 
	 * prints results, doesn't return them
	 */
	public void searchForFile(String name) {
		ArrayList<Path> results = new ArrayList<>();
		
		fileServer.getAllFilePaths().forEach( k -> {
				if (k.getFileName().toString().contains(name)) {
					results.add(k);
				}
			}
		);
		
		System.out.println("Searched for \"" + name + "\", found " + results.size() + " results:");
		results.forEach(k -> System.out.println(serverPath.relativize(k)));
	}
	
	
}
