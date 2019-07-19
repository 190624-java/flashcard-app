package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.User;
import com.revature.util.ConnectionFactory;

public class UserDaoImpl implements UserDao {

	public Connection conn;

	public UserDaoImpl() {
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to get the database connection.");
		}
	}

	@Override
	protected void finalize() throws Throwable {
		this.conn.close();
		super.finalize();
	}
	
	@Override
	public User createUser(User user) {
		try {
			String sql = "INSERT INTO Flash_Card_User (user_username, user_password)" + "VALUES (?,?)";
			String[] primaryKeys = { "user_id" };
			PreparedStatement ps = conn.prepareStatement(sql, primaryKeys);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			
			ps.executeUpdate();
			ResultSet keys = ps.getGeneratedKeys();
			while(keys.next()) {
				user.setUserId(keys.getInt(1));
			}
			return user;
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to create a user.");
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		try {
			User user = null;
			String sql = "SELECT * FROM Flash_Card_User WHERE user_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("user_username"),
						rs.getString("user_password"));
			}
			return user;
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to get a user");
		}
		return null;
	}

	@Override
	public void updateUser(User user) {
		try {
			String sql = "UPDATE Flash_Card_User "
					+ "SET user_username = ?, user_password = ? "
					+ "WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getUserId());
			ps.executeUpdate();			
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to update a user.");
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(User user) {
		try {
			String sql = "DELETE FROM Flash_Card_User "
					+ "WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			ps.executeUpdate();			
		} catch (SQLException e) {
			System.out.println("Something went wrong while trying to delete a user.");
			e.printStackTrace();
		}
	}

}
