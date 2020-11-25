package jukebox;

public class Song {
	private String track;
	private String artist;
	private String rating;
	
	public Song(String track, String artist, String rating) {
		this.track = track;
		this.artist = artist;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Song [track=" + track + ", artist=" + artist + ", rating=" + rating + "]";
	}
	
	
}
