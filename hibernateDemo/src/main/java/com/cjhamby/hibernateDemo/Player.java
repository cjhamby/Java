package com.cjhamby.hibernateDemo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table
public class Player {

	@Id
	private int playerId;
	private String playerName;
	private int age;
	private String teamName;

	/*
	 * i added the annotations according to the video tutorial:
	 * entity, table, and id
	 */

	public Player() {

	}

	
	public Player(int playerId, String playerName, int age, String teamName) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.age = age;
		this.teamName = teamName;
	}


	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", playerName=" + playerName + ", age=" + age + ", teamName=" + teamName
				+ "]";
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
