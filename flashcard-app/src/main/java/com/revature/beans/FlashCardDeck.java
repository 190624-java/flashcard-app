package com.revature.beans;

import java.util.Date;

public class FlashCardDeck {

	private int deckId;
	private String deckName;
	private String deckDescription;
	private Date deckCreatedDate;
	private User deckOwner;
	
	public FlashCardDeck() {}

	public FlashCardDeck(String deckName, String deckDescription, Date deckCreatedDate, User deckOwner) {
		this.deckName = deckName;
		this.deckDescription = deckDescription;
		this.deckCreatedDate = deckCreatedDate;
		this.deckOwner = deckOwner;
	}
	
	public FlashCardDeck(int deckId, String deckName, String deckDescription, Date deckCreatedDate, User deckOwner) {
		this.deckId = deckId;
		this.deckName = deckName;
		this.deckDescription = deckDescription;
		this.deckCreatedDate = deckCreatedDate;
		this.deckOwner = deckOwner;
	}

	public int getDeckId() {
		return deckId;
	}

	public void setDeckId(int deckId) {
		this.deckId = deckId;
	}

	public String getDeckName() {
		return deckName;
	}

	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}

	public String getDeckDescription() {
		return deckDescription;
	}

	public void setDeckDescription(String deckDescription) {
		this.deckDescription = deckDescription;
	}

	public Date getDeckCreatedDate() {
		return deckCreatedDate;
	}

	public void setDeckCreatedDate(Date deckCreatedDate) {
		this.deckCreatedDate = deckCreatedDate;
	}

	public User getDeckOwner() {
		return deckOwner;
	}

	public void setDeckOwner(User deckOwner) {
		this.deckOwner = deckOwner;
	}
	
}
