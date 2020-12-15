package com.cjhamby.ExampleServer;

import java.nio.file.Path;
import java.util.Set;

public interface ServerInterface {

	/**
	 * Create a label string for the given path, relative to server
	 * 
	 * @param p is an absolute path
	 * @return more readable format of the path
	 */
	String labelPath(Path p);

	/* returns a more convenient way to refer to the server */
	String getLabel();

	/* returns where the server is located */
	Path getRootPath();

	/* verify that something exists here */
	boolean verify(Path anything);

	/* verify that a file exists here */
	boolean verifyFile(Path filePath);

	/* verify that a directory exists here */
	boolean verifyDirectory(Path dirPath);

	/**
	 * Removes FILE from the server, but will not remove directories
	 * 
	 * @param file an absolute path to some file
	 */
	void removeFileFromServer(Path file);

	/**
	 * add a file to the server directory at server/destPath
	 * SOURCE is on a different server than DEST_DIRECTORY
	 * this will replace the existing file in DEST_DIRECTORY
	 * 
	 * @param source        File home, absolute path
	 * @param destDirectory Destination, absolute path
	 */
	void addFileToServer(Path source, Path destDirectory);

	/**
	 * create a new directory at the given path 
	 * 
	 * @param dest		directory to create
	 */
	void createDirectory(Path dest);

	/**
	 * List the files/folders contained inside PATH
	 * Doesn't include subdirectory contents
	 * 
	 * @param path		where files are found
	 */
	void listDirectory(Path path);

	/**
	 * list the subdirectories of the given path
	 * 
	 * @param path		where subdirectories are found
	 */
	void listSubdirectories(Path path);

	/**
	 * print the file tree to the console
	 */
	void listFiles();

	/**
	 * Walks through the server and locates all files
	 * 
	 * @return a set containing Paths to every file in the system
	 */
	Set<Path> getAllFilePaths();

	/**
	 * Walks through the given directory path and locates all files
	 * does not walk through subdirectories, however
	 * 
	 * @param path an existing directory
	 * @return a set containing Paths to every file found
	 */
	Set<Path> getFilePaths(Path path);

}