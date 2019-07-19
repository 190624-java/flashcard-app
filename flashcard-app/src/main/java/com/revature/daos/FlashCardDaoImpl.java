package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.revature.beans.FlashCard;
import com.revature.beans.FlashCardDeck;
import com.revature.beans.User;
import com.revature.util.ConnectionFactory;

public class FlashCardDaoImpl implements FlashCardDao {
	
	public Connection conn;
	public FlashCardDaoImpl() {
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to get db connection.");
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		this.conn.close();
		super.finalize();
	}

	@Override
	public FlashCard createFlashCard(FlashCard card) {
		try {
			String sql = "INSERT INTO Flash_Card (deck_id, flash_card_term, flash_card_description, flash_card_mastered)"
						+ "VALUES (?,?,?,?)";
			String[] primaryKeys = { "flash_card_id" };
			PreparedStatement ps = conn.prepareStatement(sql, primaryKeys);
			ps.setInt(1, card.getDeck().getDeckId());
			ps.setString(2, card.getFlashCardTerm());
			ps.setString(3, card.getFlashCardDescription());
			ps.setInt(4, (card.getFlashCardMastered()) ? 1 : 0);
			
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			while(keys.next()) {
				card.setFlashCardId(keys.getInt(1));
			}
			return card;
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to create a flash card.");
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public FlashCard getFlashCard(int cardId) {
		try {
			FlashCard card = null;
			String sql = "SELECT * FROM Flash_Card "
					+ "INNER JOIN Flash_Card_Deck"
					+ "ON Flash_Card.deck_id = Flash_Card_Deck.deck_id"
					+ "INNER JOIN Flash_Card_User"
					+ "ON Flash_Card_Deck.deck_owner = Flash_Card_User.user_id"
					+ "WHERE flash_card_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cardId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getInt("user_id"), rs.getString("user_username"),
						rs.getString("user_password"));
				FlashCardDeck deck = new FlashCardDeck(
						rs.getInt("deck_id"), rs.getString("deck_name"),
						rs.getString("deck_description"),
						(Date)rs.getDate("deck_created_date"), user);
				card = new FlashCard(rs.getInt("flash_card_id"),
						deck, rs.getString("flash_card_term"),
						rs.getString("flash_card_description"),
						(rs.getInt("flash_card_mastered") == 1));
			}
			return card;
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to get a flash card");
		}
		return null;
	}

	@Override
	public List<FlashCard> getAllFlashCardsByUser(int userId) {
		try {
			List<FlashCard> cards = new ArrayList<FlashCard>();
			String sql = "SELECT * FROM Flash_Card "
					+ "INNER JOIN Flash_Card_Deck"
					+ "ON Flash_Card.deck_id = Flash_Card_Deck.deck_id"
					+ "INNER JOIN Flash_Card_User"
					+ "ON Flash_Card_Deck.deck_owner = Flash_Card_User.user_id"
					+ "WHERE Flash_Card_User.user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getInt("user_id"), rs.getString("user_username"),
						rs.getString("user_password"));
				FlashCardDeck deck = new FlashCardDeck(
						rs.getInt("deck_id"), rs.getString("deck_name"),
						rs.getString("deck_description"),
						(Date)rs.getDate("deck_created_date"), user);
				cards.add(new FlashCard(rs.getInt("flash_card_id"),
						deck, rs.getString("flash_card_term"),
						rs.getString("flash_card_description"),
						(rs.getInt("flash_card_mastered") == 1)));
			}
			return cards;
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to get flash cards");
		}
		return null;
	}

	@Override
	public List<FlashCard> getAllFlashCardsByUser(String username) {
		try {
			List<FlashCard> cards = new ArrayList<FlashCard>();
			String sql = "SELECT * FROM Flash_Card "
					+ "INNER JOIN Flash_Card_Deck"
					+ "ON Flash_Card.deck_id = Flash_Card_Deck.deck_id"
					+ "INNER JOIN Flash_Card_User"
					+ "ON Flash_Card_Deck.deck_owner = Flash_Card_User.user_id"
					+ "WHERE Flash_Card_User.user_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getInt("user_id"), rs.getString("user_username"),
						rs.getString("user_password"));
				FlashCardDeck deck = new FlashCardDeck(
						rs.getInt("deck_id"), rs.getString("deck_name"),
						rs.getString("deck_description"),
						(Date)rs.getDate("deck_created_date"), user);
				cards.add(new FlashCard(rs.getInt("flash_card_id"),
						deck, rs.getString("flash_card_term"),
						rs.getString("flash_card_description"),
						(rs.getInt("flash_card_mastered") == 1)));
			}
			return cards;
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to get flash cards");
		}
		return null;
	}

	@Override
	public void updateFlashCard(FlashCard card) {
		try {
			String sql = "UPDATE Flash_Card "
					+ "SET deck_id = ?, flash_card_term = ?, flash_card_description = ?, flash_card_mastered = ?"
						+ "WHERE flash_card_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, card.getDeck().getDeckId());
			ps.setString(2, card.getFlashCardTerm());
			ps.setString(3, card.getFlashCardDescription());
			ps.setInt(4, (card.getFlashCardMastered()) ? 1 : 0);
			ps.setInt(4, card.getFlashCardId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to update a flash card.");
			e.printStackTrace();
		}


	}

	@Override
	public void deleteFlashCard(FlashCard card) {
		try {
			String sql = "DELETE FROM Flash_Card " 
						+ "WHERE flash_card_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, card.getFlashCardId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to delete a flash card.");
			e.printStackTrace();
		}

	}

}
