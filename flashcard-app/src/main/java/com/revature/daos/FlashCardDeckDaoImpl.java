package com.revature.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.FlashCard;
import com.revature.beans.FlashCardDeck;
import com.revature.beans.User;
import com.revature.util.ConnectionFactory;

public class FlashCardDeckDaoImpl implements FlashCardDeckDao {

	public Connection conn;
	public FlashCardDeckDaoImpl() {
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
	public FlashCardDeck createDeck(FlashCardDeck deck) {
		try {
			String sql = "INSERT INTO Flash_Card_Deck (deck_name, deck_description, deck_created_date, deck_owner)"
						+ "VALUES (?,?,?,?)";
			String[] primaryKeys = { "deck_id" };
			PreparedStatement ps = conn.prepareStatement(sql, primaryKeys);
			ps.setString(1, deck.getDeckName());
			ps.setString(2, deck.getDeckDescription());
			ps.setDate(3, (Date) deck.getDeckCreatedDate());
			ps.setInt(4, deck.getDeckOwner().getUserId());
			
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			while(keys.next()) {
				deck.setDeckId(keys.getInt(1));
			}
			return deck;
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to create a deck.");
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public FlashCardDeck getDeckByName(int ownerId, String deckName) {
		try {
			FlashCardDeck deck = null;
			String sql = "SELECT * FROM Flash_Card_Deck "
					+ "INNER JOIN Flash_Card_User"
					+ "ON Flash_Card_Deck.deck_owner = Flash_Card_User.user_id"
					+ "WHERE deck_name = ? AND deck_owner = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, deckName);
			ps.setInt(2, ownerId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getInt("user_id"), rs.getString("user_username"),
						rs.getString("user_password"));
				deck = new FlashCardDeck(
						rs.getInt("deck_id"), rs.getString("deck_name"),
						rs.getString("deck_description"),
						(Date)rs.getDate("deck_created_date"), user);
				
			}
			return deck;
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to get a deck");
		}
		return null;
	}

	@Override
	public List<FlashCardDeck> getDecksByUser(int ownerId) {
		try {
			List<FlashCardDeck> decks = new ArrayList<>();
			String sql = "SELECT * FROM Flash_Card_Deck "
					+ "INNER JOIN Flash_Card_User"
					+ "ON Flash_Card_Deck.deck_owner = Flash_Card_User.user_id"
					+ "WHERE Flash_Card_User.user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ownerId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getInt("user_id"), rs.getString("user_username"),
						rs.getString("user_password"));
				decks.add(new FlashCardDeck(
						rs.getInt("deck_id"), rs.getString("deck_name"),
						rs.getString("deck_description"),
						(Date)rs.getDate("deck_created_date"), user));
				
			}
			return decks;
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to get decks by user id");
		}
		return null;
	}

	@Override
	public List<FlashCardDeck> getDecksByUser(String username) {
		try {
			List<FlashCardDeck> decks = new ArrayList<>();
			String sql = "SELECT * FROM Flash_Card_Deck "
					+ "INNER JOIN Flash_Card_User"
					+ "ON Flash_Card_Deck.deck_owner = Flash_Card_User.user_id"
					+ "WHERE Flash_Card_User.user_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getInt("user_id"), rs.getString("user_username"),
						rs.getString("user_password"));
				decks.add(new FlashCardDeck(
						rs.getInt("deck_id"), rs.getString("deck_name"),
						rs.getString("deck_description"),
						(Date)rs.getDate("deck_created_date"), user));
				
			}
			return decks;
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to get decks by username");
		}
		return null;
	}

	@Override
	public void updateDeck(FlashCardDeck deck) {
		try {
			String sql = "UPDATE Flash_Card_Deck "
					+ "SET deck_name = ?, deck_description = ?, deck_created_date = ?, deck_owner = ? "
						+ "WHERE deck_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, deck.getDeckName());
			ps.setString(2, deck.getDeckDescription());
			ps.setDate(3, (Date) deck.getDeckCreatedDate());
			ps.setInt(4, deck.getDeckOwner().getUserId());
			ps.setInt(5, deck.getDeckId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to update a deck.");
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDeck(FlashCardDeck deck) {
		try {
			String sql = "DELETE FROM Flash_Card_Deck "
					+ "WHERE deck_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, deck.getDeckId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to delete a deck.");
			e.printStackTrace();
		}
	}

}
