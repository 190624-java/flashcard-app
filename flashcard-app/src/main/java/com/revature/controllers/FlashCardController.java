package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.FlashCard;
import com.revature.beans.User;
import com.revature.daos.FlashCardDao;
import com.revature.daos.FlashCardDaoImpl;

public class FlashCardController {
	
	private static final FlashCardDao cardDao = new FlashCardDaoImpl();

	public void saveNewCard(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		FlashCard card = mapper.readValue(req.getInputStream(), FlashCard.class);
		cardDao.createFlashCard(card);
		resp.setStatus(201);
	}
	
	public void getAllUsersCards(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		User currentUser = (User)req.getSession().getAttribute("user");
		List<FlashCard> cards = cardDao.getAllFlashCardsByUser(currentUser.getUserId());
		
		ObjectMapper mapper = new ObjectMapper();
		resp.getWriter().print(mapper.writeValueAsString(cards));
	}
	
	public void saveExistingCard(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException {
		User currentUser = (User)req.getSession().getAttribute("user");
		ObjectMapper mapper = new ObjectMapper();
		FlashCard card = mapper.readValue(req.getInputStream(), FlashCard.class);
		FlashCard cardFromDb = cardDao.getFlashCard(card.getFlashCardId());
		if(cardFromDb != null && cardFromDb.getDeck().getDeckOwner().getUserId() == currentUser.getUserId()) {
			cardDao.updateFlashCard(card);
			resp.setStatus(200);
		} else if(cardFromDb == null){
			System.out.println("Invalid request. The flashcard does not exist in the database yet.");
			//System.out.println("This flashcard will be created in the database.");
			resp.setStatus(400);
		} else {
			System.out.println("Invalid request. The current user does not have permission to update this flashcard.");
			//TODO: direct to error page
			resp.setStatus(400);
		}
		
	}
	
}
