package com.cjhamby.hibernateDemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * annotations are a newer way of accomplishing what the hbm file does
 */

@Entity
@Table(name = "player")
public class Player {

	
	/*
	 * @Id is used to identify and get a player to/from the table
	 * 
	 * if you wanted to generate a unique Id, you could add a second annotation
	 * @GeneratedValue
	 */
	@Id
	private int playerNum;
	
	// column annotation is not necessary, but can be customized
	@Column(name = "customColName")
	private String playerName;
	private String teamName;

	public Player() {

	}

	public Player(String playerName, int playerNum, String teamName) {
		this.playerName = playerName;
		this.playerNum = playerNum;
		this.teamName = teamName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", playerNum=" + playerNum
				+ ", teamName=" + teamName + "]";
	}



}
