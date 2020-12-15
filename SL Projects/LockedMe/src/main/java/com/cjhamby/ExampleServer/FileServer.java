package com.cjhamby.ExampleServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.TreeSet;

/*
 * methods to implement a crude type of file server
 * which is an exaggeration, really, since this is only really a folder
 * 
 * ...i may have over-complicated this project
 */
public abstract class FileServer implements ServerInterface {

	protected Path serverRootPath; // all file locations are relative to this
	protected String label; // name of the file server

	/**
	 * child classes must implement this method
	 * it determines where the server root path is
	 */
	protected abstract void locateRootDirectory();

	public FileServer() {
		try {
			locateRootDirectory(); // implementation-specific

			if (!verifyDirectory(serverRootPath)) {
				System.out.println("no such path exists");
				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println("Please Implement locateRootDirectory");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Create a label string for the given path, relative to server
	 * 
	 * @param p is an absolute path
	 * @return more readable format of the path
	 */
	public String labelPath(Path p) {
		return (label + serverRootPath.relativize(p));
	}

	public String getLabel() {
		return label;
	}

	public Path getRootPath() {
		return serverRootPath;
	}

	/* verify that something exists here */
	public boolean verify(Path anything) {
		return anything.toFile().exists();
	}

	/* verify that a file exists here */
	public boolean verifyFile(Path filePath) {
		return filePath.toFile().isFile();
	}

	/* verify that a directory exists here */
	public boolean verifyDirectory(Path dirPath) {
		return dirPath.toFile().isDirectory();
	}

	/**
	 * Removes FILE from the server, but will not remove directories
	 * 
	 * @param file an absolute path to some file
	 */
	public void removeFileFromServer(Path file) {
		try {
			if (verifyFile(file)) {
				Files.delete(file);
			}
		} catch (Exception e) {
			System.out.println("Could not remove file");
		}
	}

	/**
	 * add a file to the server directory at server/destPath
	 * SOURCE is on a different server than DEST_DIRECTORY
	 * this will replace the existing file in DEST_DIRECTORY
	 * 
	 * @param source        File home, absolute path
	 * @param destDirectory Destination, absolute path
	 */
	public void addFileToServer(Path source, Path destDirectory) {
		try {
			Path destPath = destDirectory.resolve(source.getFileName());
			Files.copy(source, destPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			System.out.println("Could not add file");
		}
	}

	/**
	 * create a new directory at the given path
	 * 
	 * @param dest directory to create
	 */
	public void createDirectory(Path dest) {
		try {
			Files.createDirectory(dest);
		} catch (Exception e) {
			System.out.println("something already exists there!");
		}
	}

	/**
	 * List the files/folders contained inside PATH
	 * Doesn't include subdirectory contents
	 * 
	 * @param path where files are found
	 */
	public void listDirectory(Path path) {
		System.out.println("------------------------------------------------");
		System.out.println(" Contents of Directory:\t" + labelPath(path) + "\n");

		try {
			Set<String> contents = new TreeSet<>();

			/* iterate through a single level of the file tree */
			Files.walk(path, 1).forEach((k) -> {
				String result = (k.getFileName()).toString();

				// use a dot to represent this path
				if (k.compareTo(path) == 0) {
					result = ".";
				} else if (Files.isDirectory(k)) {
					result = result + "\\"; /* format directories */
				}
				contents.add(result);
			});

			/* print the found directory contents */
			contents.forEach((k) -> System.out.print(k + " "));
			System.out.println();
			System.out.println("------------------------------------------------");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * list the subdirectories of the given path
	 * 
	 * @param path where subdirectories are found
	 */
	public void listSubdirectories(Path path) {
		System.out.println("------------------------------------------------");
		System.out.println("Subdirectories of:\t" + labelPath(path) + "\n");

		try {
			Set<String> contents = new TreeSet<>();

			Files.walk(path, 1).forEach((k) -> {
				String result = (((Path) k).getFileName()).toString();
				if (Files.isDirectory(k) && !k.equals(path)) {
					contents.add(result);
				}
			});

			// printing formality
			contents.add(".");
			contents.forEach(System.out::println);

		} catch (Exception e) {
			System.out.println("error in listSubdirectories");
		}
	}

	/**
	 * print the file tree to the console
	 */
	public void listFiles() {
		FilePrinter fp = new FilePrinter(serverRootPath);
		try {
			System.out.println("------------------------------------------------");
			System.out.println("Files Stored on Server:\t" + label);
			Files.walkFileTree(serverRootPath, fp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Walks through the server and locates all files
	 * 
	 * @return a set containing Paths to every file in the system
	 */
	public Set<Path> getAllFilePaths() {
		TreeSet<Path> results = new TreeSet<>();
		try {
			Files.walk(serverRootPath).filter(k -> verifyFile(k)).forEach(k -> results.add(k));
		} catch (Exception e) {
			System.out.println("Could not get file paths");
		}
		return results;
	}

	/**
	 * Walks through the given directory path and locates all files
	 * does not walk through subdirectories, however
	 * 
	 * @param path an existing directory
	 * @return a set containing Paths to every file found
	 */
	public Set<Path> getFilePaths(Path path) {
		TreeSet<Path> results = new TreeSet<>();
		try {

			// add all file paths to the result set
			Files.walk(path, 1).forEach((k) -> {
				results.add(k);
			});
		} catch (Exception e) {
			System.out.println("error in getFilePaths() method");
		}

		return results;
	}

}
