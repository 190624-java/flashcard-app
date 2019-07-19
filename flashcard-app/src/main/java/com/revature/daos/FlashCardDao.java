package com.revature.daos;

import java.util.List;

import com.revature.beans.FlashCard;

public interface FlashCardDao {

	public FlashCard createFlashCard(FlashCard card);
	public FlashCard getFlashCard(int cardId);
	public List<FlashCard> getAllFlashCardsByUser(int userId);
	public List<FlashCard> getAllFlashCardsByUser(String username);
	public void updateFlashCard(FlashCard card);
	public void deleteFlashCard(FlashCard card);
}
