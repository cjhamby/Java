package com.cjhamby.ExampleServer;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;


/**
 * used by FileServer for walking the file tree
 */
public class FilePrinter extends SimpleFileVisitor<Path>{
	
	private Path rootPath = null;
	
	public FilePrinter(Path root) {
		this.rootPath = root;
	}
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		System.out.print(" - .\\" + rootPath.relativize(file));
		if(attrs.isDirectory()) {
			System.out.println("\\");
		} else {
			System.out.println();
		}
		return super.visitFile(file, attrs);
	}
}
