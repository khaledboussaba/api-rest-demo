package fr.khaled.dev.demo_rest_api;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class AlienRepository {
	
	private Connection connection;

	public AlienRepository() {
		String url = "jdbc:mysql://localhost:3306/rest-api-db?serverTimezone=GMT";
		String username = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}		
	}
	
	public List<Alien> getAliens() {
		List<Alien> aliens = new ArrayList<Alien>();
		
		String query = "SELECT * FROM alien";
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				
				aliens.add(a);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return aliens;
	}
	
	public Alien getAlien(int id) {	
		Alien a = new Alien();
		String query = "SELECT * FROM alien WHERE id = " + id;
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return a;
	}

	public void create(Alien a) {
		String query = "INSERT INTO alien VALUES (?, ?, ?)";
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, a.getId());
			pst.setString(2, a.getName());
			pst.setInt(3, a.getPoints());
			pst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e);
		}		
	}
	
	public void update(Alien a) {
		String query = "UPDATE alien SET name = ?, points = ? WHERE id = ?";
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, a.getName());
			pst.setInt(2, a.getPoints());
			pst.setInt(3, a.getId());
			pst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e);
		}		
	}
	
	public void delete(int id) {
		String query = "DELETE FROM alien WHERE id = ?";
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, id);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e);
		}		
	}
	
}
