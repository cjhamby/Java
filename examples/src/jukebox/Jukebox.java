/* demonstrates using Files and Readers to bring info into the program */

package jukebox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Jukebox {
	
	/* where the song objects are stored */
    private ArrayList<Song> songs = new ArrayList<Song>();
	
    
    public static void main(String[] args) {
    	System.out.println("Jukebox");
    	new Jukebox().go();
	}
   
	private void go() {
		getSongs();
	}
		
	/* get all songs using bufferedreader */
	private void getSongs() {
		
		try {
			File file = new File("songs.txt");			/* bring in file */
			
			/* fr can read the file char-by-char, not line-by-line */
			FileReader fr = new FileReader(file);
			
			/* br can read the file line-by-line, accepts fr as arg */
			BufferedReader br = new BufferedReader(fr);	
			
			String str = br.readLine();		/* get first line */ 
			while(str != null) {			/* read all lines of the file */
				addSong(str);				/* store to songs arraylist */
				str = br.readLine();		/* get next line */
			}
			fr.close();						/* close the readers */
			br.close();
		
		} catch (Exception e) {
			System.out.println("No songs found!");
		}
	}
	
	/* format songs from string to object */
	private void addSong(String song) {
		
		/* track/artist/rating are separated by the forward slash */
		String[] token = song.split("/");
		
		/* try to make a song object, catch incomplete entries */
		try {
			Song thisSong = new Song(token[0], token[1], token[2]);
			System.out.println(thisSong);
			songs.add(thisSong);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("incomplete song data");
		}
		
	}
	
}
