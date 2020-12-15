package com.cjhamby.LockedMe;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.cjhamby.ExampleServer.ClientServer;
import com.cjhamby.ExampleServer.FileAgent;
import com.cjhamby.ExampleServer.LockedMeServer;
import com.cjhamby.ExampleServer.NoPermissionToDoThatException;

/*
 * text-based UI with scanner and presentation method
 * 
 * gives instructions about each command
 * calls the appropriate agent methods based on user input
 */
public class TextApp implements TextAppInterface{
	
	private enum AppContexts {
		MAIN, ADD, SEARCH, REMOVE, EXIT
	}
	private AppContexts context;
	private FileAgent lmAgent;
	private FileAgent clientAgent;
	private Scanner scan;
	
	public TextApp() {
		this.initialize();
	}
	
	
	/* give some assurance that things are happening */
	private void initialize() {
		System.out.println("Starting LockedMe Client");
		System.out.println("-------------------------------------------");
		
		System.out.println(" --- Establishing Connection to LockedMe Server");
		lmAgent = new FileAgent(new LockedMeServer());

		System.out.println(" --- Establishing Connection to Client Machine");
		clientAgent = new FileAgent(new ClientServer());
		
		this.context = AppContexts.MAIN;
		scan = new Scanner(System.in);
		this.welcome();
	}
	
	private void welcome() {
		System.out.println("\n");
		System.out.println("=======================================================");
		System.out.println("               Welcome to LockedMe App");
		System.out.println();
		System.out.println("             By Company Lockers Pvt. Ltd.");
		System.out.println("              Programmed by Chris Hamby");
		System.out.println();
		System.out.println("The ultimate holder of files you want held ultimately!");
		System.out.println("=======================================================");
	}
	
	private void printInstructionsMain() {
		System.out.println("================================================");
		System.out.println(" Main Menu\n");
		System.out.println("------------------------------------------------");
		System.out.println("Actions");
		System.out.println("(1) Add a file");
		System.out.println("(2) Search for a file");
		System.out.println("(3) Remove a file");
		System.out.println("(4) List all files");
		System.out.println("(0) Leave the LockedMe App");
		System.out.print("\nPlease select an option: ");
	}
	
	private void printInstructionsAdd() {
		System.out.println("================================================");
		System.out.println(" Add Menu\n");
		System.out.println("Actions to add a file to LockedMe's Server");
		System.out.println("------------------------------------------------");
		System.out.println("Actions");
		System.out.println("(1) Navigate user directories");
		System.out.println("(2) Navigate server directories");
		System.out.println("(3) Add a file");
		System.out.println("(4) Create a directory");
		System.out.println("(0) Return to Main Menu");
		
		System.out.print("\nPlease select an option: ");
	}
	
	private void printInstructionsSearch() {
		System.out.println("================================================");
		System.out.println(" Search Menu\n");
		System.out.println("Actions to find a file on LockedMe's Server");
		System.out.println("------------------------------------------------");
		System.out.println("Actions");
		System.out.println("(1) Search for a file");
		System.out.println("(2) List all files");
		System.out.println("(0) Return to Main Menu");
		System.out.print("\nPlease select an option: ");
	}
	
	private void printInstructionsRemove() {
		System.out.println("================================================");
		System.out.println(" Delete Menu\n");
		System.out.println("Actions to remove a file from LockedMe's Server");
		System.out.println("------------------------------------------------");
		System.out.println("Actions");
		System.out.println("(1) Navigate to a directory");
		System.out.println("(2) Remove a file");
		System.out.println("(0) Return to Main Menu");
		System.out.print("\nPlease select an option: ");
	}
	
	
	/* this is basically the OS */
	public void run() {
		try {
			
			/* prevents two cases executing in a single run() cycle */
			AppContexts thisContext = context;
			
			int selection = -1;
			switch(thisContext) {
				case MAIN:
					printInstructionsMain();
					selection = Integer.parseInt(scan.nextLine());
					switch(selection) {
						case 0:
							context = AppContexts.EXIT;
							break;
						case 1:
							context = AppContexts.ADD;
							break;
						case 2:
							context = AppContexts.SEARCH;
							break;
						case 3:
							context = AppContexts.REMOVE;
							break;
						case 4:
							lmAgent.listAllFiles();
							break;
						default:
							System.out.println("Invalid Selection, try again");
							break;
					}
					break;
				case ADD:
					printInstructionsAdd();
					selection = Integer.parseInt(scan.nextLine());
					switch(selection) {
						case 0:
							context = AppContexts.MAIN;
							break;
						case 1:
							navigateUser();
							break;
						case 2:
							navigateServer();
							break;
						case 3:
							addFileToServer();
							break;
						case 4:
							addDirectoryToServer();
							break;
						default:
							System.out.println("Invalid Selection, try again");
							break;
					}
					break;
					
				case SEARCH:
					printInstructionsSearch();
					selection = Integer.parseInt(scan.nextLine());
					switch(selection) {
						case 0:
							context = AppContexts.MAIN;
							break;
						case 1:
							searchForFile();
							break;
						case 2:
							lmAgent.listAllFiles();
							break;
						default:
							System.out.println("Invalid Selection, try again");
							break;
					}
					break;
					
				case REMOVE:
					printInstructionsRemove();
					selection = Integer.parseInt(scan.nextLine());
					switch(selection) {
						case 0:
							context = AppContexts.MAIN;
							break;
						case 1:
							navigateServer();
							break;
						case 2:
							removeFileFromServer();
							break;
						default:
							System.out.println("Invalid Selection, try again");
							break;
					}
					break;
				case EXIT:
				default:
					leaveProgram();
					break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input");
		} catch (NumberFormatException e) {
			// just let it go man 
		} catch (Exception e) {
			System.out.println("Unexpected error, leaving program");
			System.out.println(e.getMessage());
			leaveProgram();
		}
	}

	private void leaveProgram() {
		System.out.println(" --- Exiting Program ---\n");
		System.out.println("Thank you for using LockedMe");
		System.exit(0);
	}
	
	
	
	/*
	 * add a file from the client working directory to the server working directory
	 * this method can only add single files, not directories
	 */
	private void addFileToServer() {
		System.out.println("------------------------------------------------");
		System.out.println("         Add file to LockedMe Server\n");
		System.out.print("Source\t\t");			/* print where file is going */
		clientAgent.printWorkingDirectory();
		System.out.print("Destination\t");		/* print where file is from */
		lmAgent.printWorkingDirectory();
		clientAgent.listWorkingDirectory();		/* print available files */
		System.out.println("\nPlease type the file you'd like to add");
		String choice = scan.nextLine();
		
		/* case sensitivity check */
		if(!clientAgent.verifyFile(choice)) {		/* if case doesn't match */
			choice = lmAgent.getFileName(choice); 	/* case-sensitive name */
		}
		
		if(clientAgent.verifyFile(choice)) {
			if(lmAgent.verifyFile(choice)) {		/* rewrite protection */
				System.out.println("Rewrite file " + choice + " ? (y/n): ");
				
				/* only check for 'n' char, anything else will rewrite file */
				if(scan.next().toLowerCase().charAt(0) == 'n') {
					System.out.println("File was not added\n");
					return;
				}
			}
			
			/* pull the file from the client's server */
			lmAgent.addFileToServer(clientAgent.stringToAbsPath(choice));
			System.out.println("file added successfully");
		} else {
			System.out.println("file not found");
		}
		System.out.println();
	}
	
	private void addDirectoryToServer() {
		System.out.println("------------------------------------------------");
		System.out.println("    Create a directory on LockedMe Server\n");
		System.out.print("Working Directory\t");
		lmAgent.printWorkingDirectory();
		
		System.out.print("Name for new directory: ");
		String choice = "";
		while(choice.length()<1) {
			choice = scan.nextLine();
		}

		lmAgent.makeSubdirectory(choice);
	}
	
	private void removeFileFromServer() {
		System.out.println("------------------------------------------------");
		System.out.println("      Remove file from LockedMe Server\n");
		System.out.print("Working Directory\t");
		lmAgent.printWorkingDirectory();
		lmAgent.listWorkingDirectory();
		
		/* case sensitive */
		System.out.println("\nPlease type the file you'd like to remove");
		System.out.println("Case-sensitive and file extension required");
		System.out.println("(Note: removing directories is not supported)");
		String choice = scan.nextLine();
		
		/*
		 * sorry to put logic in the UI layer 
		 * but, since the logic directly depends on the scanner input
		 * it seems more graceful to leave this here
		 */
		
		/* ensure there's actually a file to remove */
		if(lmAgent.verifyFile(choice)) {
			
			/* require explicit permission to remove the file */
			System.out.println("Really remove " + choice + "? (y/n): ");
			if(scan.next().toLowerCase().charAt(0) == 'y') {
				
				
				System.out.println("File removed\n");
				lmAgent.removeFileFromServer(choice);
				return;
			} else {
				System.out.println("File not removed");
			}
		} else {
			System.out.println("file not found");
		}
		System.out.println();
	}
	
	private void searchForFile() {
		System.out.println("------------------------------------------------");
		System.out.println("    Search for File on LockedMe Server\n");
		System.out.println(" This will search the entire server for a file ");
		System.out.println(" Note: search term is case-sensitive ");
		System.out.println(" file extension is not necessary");
		System.out.println("------------------------------------------------");
		System.out.print("Enter a file name to search for:\t");
		String choice = scan.nextLine();
		lmAgent.searchForFile(choice);
		return;
	}
	
	
	private void navigateUser() {
		System.out.println("------------------------------------------------");
		System.out.println("        Changing Client Working Directory\n");
		System.out.println("Please type the directory you wish to navigate to");
		System.out.println("or, \'..\' to go up a level\n");
		clientAgent.listSubdirectories();
		System.out.println("------------------------------------------------");
		System.out.println("Your choice:\t");
		String choice = scan.nextLine();
		try {
			clientAgent.moveToSubdirectory(choice);
		} catch (NoPermissionToDoThatException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("------------------------------------------------");
		return;
	}
	
	private void navigateServer() {
		System.out.println("------------------------------------------------");
		System.out.println("        Changing Server Working Directory\n");
		System.out.println("Please type the directory you wish to navigate to");
		System.out.println("or, \'..\' to go up a level\n");
		lmAgent.listSubdirectories();
		System.out.println("------------------------------------------------");
		System.out.println("Your choice:\t");
		String choice= scan.nextLine();
		try {
			lmAgent.moveToSubdirectory(choice);
		} catch (NoPermissionToDoThatException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("------------------------------------------------");
		return;
	}
}
