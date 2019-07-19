package com.revature.daos;

import java.util.List;

import com.revature.beans.FlashCardDeck;

public interface FlashCardDeckDao {

	public FlashCardDeck createDeck(FlashCardDeck deck);
	public FlashCardDeck getDeckByName(int ownerId, String deckName);
	public List<FlashCardDeck> getDecksByUser(int ownerId);
	public List<FlashCardDeck> getDecksByUser(String username);
	public void updateDeck(FlashCardDeck deck);
	public void deleteDeck(FlashCardDeck deck);
	
}
