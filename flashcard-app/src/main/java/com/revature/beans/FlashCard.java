package com.revature.beans;

public class FlashCard {

	private int flashCardId;
	private FlashCardDeck deck;
	private String flashCardTerm;
	private String flashCardDescription;
	private Boolean flashCardMastered;
	
	public FlashCard() {}

	public FlashCard(FlashCardDeck deck, String flashCardTerm, String flashCardDescription,
			Boolean flashCardMastered) {
		this.deck = deck;
		this.flashCardTerm = flashCardTerm;
		this.flashCardDescription = flashCardDescription;
		this.flashCardMastered = flashCardMastered;
	}
	
	public FlashCard(int flashCardId, FlashCardDeck deck, String flashCardTerm, String flashCardDescription,
			Boolean flashCardMastered) {
		this.flashCardId = flashCardId;
		this.deck = deck;
		this.flashCardTerm = flashCardTerm;
		this.flashCardDescription = flashCardDescription;
		this.flashCardMastered = flashCardMastered;
	}

	public int getFlashCardId() {
		return flashCardId;
	}

	public void setFlashCardId(int flashCardId) {
		this.flashCardId = flashCardId;
	}

	public FlashCardDeck getDeck() {
		return deck;
	}

	public void setDeck(FlashCardDeck deck) {
		this.deck = deck;
	}

	public String getFlashCardTerm() {
		return flashCardTerm;
	}

	public void setFlashCardTerm(String flashCardTerm) {
		this.flashCardTerm = flashCardTerm;
	}

	public String getFlashCardDescription() {
		return flashCardDescription;
	}

	public void setFlashCardDescription(String flashCardDescription) {
		this.flashCardDescription = flashCardDescription;
	}

	public Boolean getFlashCardMastered() {
		return flashCardMastered;
	}

	public void setFlashCardMastered(Boolean flashCardMastered) {
		this.flashCardMastered = flashCardMastered;
	}
	
}
