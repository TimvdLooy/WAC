import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.v1wac.firstapp.model.Country;

public class StafBijFilm(){
	private int filmID;
	private int stafID;
	private String rol;
	
	public StafBijFilm(int filmID, int stafID, String rol){
		this.filmID = filmID;
		this.stafID = stafID;
		this.rol = rol;
	}
	
	public int getFilmID(){
		return this.filmID;
	}
	
	public int getStafID(){
		return this.stafID;
	}
	
	public String getRol(){
		return this.rol;
	}
	
	public void setFilmID(int filmID){
		this.filmID = filmID;
	}
	
	public void setStafID(int stafID){
		this.stafID = stafID;
	}
	
	public void setRol(String rol){
		this.rol = rol;
	}
}

public ArrayList<StafBijFilm> findAllStafBijFilm(){
	ArrayList<StafBijFilm> lijst = new ArrayList<StafBijFilm>();
	String query = "Select * From StafBijFilm";
	try (Connection con = super.getConnection()){
		Statement stmt = con.createStatement();
		ResultSet dbResultSet = stmt.executeQuery(query);
		
		while(dbResultSet.next()){
			int filmID = dbResultSet.getInt('filmID');
			int stafID = dbResultSet.getInt('stafId');
			String rol = dbResultSet.getString("rol");
			StafBijFilm newStafBijFilm = new StafBijFilm(filmID, stafID, rol);
			lijst.add(newStafBijFilm);
		}
		
	} catch (SQLException sqle) {sqle.printStackTrace(); }
	
	return lijst;
}

public StafBijFilm findByCode(int id) {
	return selectFilms("SELECT * FROM StafBijFilm WHERE filmID = '" + id + "'").get(0);
}

public StafBijFilm insert(StafBijFilm StafBijFilm) {
	try (Connection con = super.getConnection()) {
		Statement stmt = con.createStatement();
		String query = "INSERT INTO StafBijFilm"
				+ "(filmID, stafId, rol) "
				+ "VALUES ('" + StafBijFilm.getFilmID() + "', '" + StafBijFilm.getStafID()
				+ "', '" + StafBijFilm.getRol() + ")";
		stmt.executeUpdate(query);
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
	}
	
	return findByCode(StafBijFilm.getFilmID());
	}
}

public StafBijFilm update(StafBijFilm StafBijFilm) {
	try (Connection con = super.getConnection()) {
		Statement stmt = con.createStatement();
		String query = "UPDATE StafBijFilm SET filmID'" + StafBijFilm.getFilmID()
				+ "', stafId='" + StafBijFilm.getStafID() + "', rol='"
				+ StafBijFilm.getRol() + " WHERE filmID = '"
				+ StafBijFilm.getFilmID() + "'";
		stmt.executeUpdate(query);
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
	}
	
	return findByCode(StafBijFilm.getFilmID());
}

public boolean delete(StafBijFilm StafBijFilm) {
	try (Connection con = super.getConnection()) {
		Statement stmt = con.createStatement();
		String query = "DELETE FROM StafBijFilm WHERE filmID = '" + film.getFilmID() + "'";
		stmt.executeUpdate(query);
		return true;
		
	} catch (SQLException sqle) {
		sqle.printStackTrace();
		return false;
	}
}