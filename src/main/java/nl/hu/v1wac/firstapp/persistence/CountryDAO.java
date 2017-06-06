package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import nl.hu.v1wac.firstapp.model.Country;

public class CountryDAO extends BaseDAO{
	
	public Country save(Country country){
		return country;
	}
	
	public ArrayList<Country> findAll(){
		ArrayList<Country> landLijst = new ArrayList<Country>();
		String query = "Select * From Country";
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				String iso2code = dbResultSet.getString("code");
				String iso3code = dbResultSet.getString("code2");
				String name = dbResultSet.getString("name");
				String capital = dbResultSet.getString("capital");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				Double surface = dbResultSet.getDouble("surfacearea");
				int population = dbResultSet.getInt("population");
				String goverment = dbResultSet.getString("governmentform");
				Double lat = dbResultSet.getDouble("latitude");
				Double longitude = dbResultSet.getDouble("longitude");
				Country newCountry = new Country(iso2code, iso3code, name, capital, continent, region,
						surface, population, goverment, lat, longitude);
				landLijst.add(newCountry);
			}	
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return landLijst;
	}
	
	public Country findByCode(String code){
		Country land = null;
		String query = "Select * From Country Where code = \'"+ code +"\'";
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				String iso2code = dbResultSet.getString("code");
				String iso3code = dbResultSet.getString("code2");
				String name = dbResultSet.getString("name");
				String capital = dbResultSet.getString("capital");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				Double surface = dbResultSet.getDouble("surfacearea");
				int population = dbResultSet.getInt("population");
				String goverment = dbResultSet.getString("governmentform");
				Double lat = dbResultSet.getDouble("latitude");
				Double longitude = dbResultSet.getDouble("longitude");
				Country newCountry = new Country(iso2code, iso3code, name, capital, continent, region,
						surface, population, goverment, lat, longitude);
				land = newCountry;
				
			}
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return land;
	}
	
	public ArrayList<Country> find10LargestPopulations(){
		ArrayList<Country> landLijst = new ArrayList<Country>();
		String query = "Select * From Country order by population DESC LIMIT 10";
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				String iso2code = dbResultSet.getString("code");
				String iso3code = dbResultSet.getString("code2");
				String name = dbResultSet.getString("name");
				String capital = dbResultSet.getString("capital");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				Double surface = dbResultSet.getDouble("surfacearea");
				int population = dbResultSet.getInt("population");
				String goverment = dbResultSet.getString("governmentform");
				Double lat = dbResultSet.getDouble("latitude");
				Double longitude = dbResultSet.getDouble("longitude");
				Country newCountry = new Country(iso2code, iso3code, name, capital, continent, region,
						surface, population, goverment, lat, longitude);
				landLijst.add(newCountry);
			}
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return landLijst;
	}
	
	public ArrayList<Country> find10LargestSurfaces(){
		ArrayList<Country> landLijst = new ArrayList<Country>();
		String query = "Select * From Country order by surfacearea DESC LIMIT 10";
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				String iso2code = dbResultSet.getString("code");
				String iso3code = dbResultSet.getString("code2");
				String name = dbResultSet.getString("name");
				String capital = dbResultSet.getString("capital");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				Double surface = dbResultSet.getDouble("surfacearea");
				int population = dbResultSet.getInt("population");
				String goverment = dbResultSet.getString("governmentform");
				Double lat = dbResultSet.getDouble("latitude");
				Double longitude = dbResultSet.getDouble("longitude");
				Country newCountry = new Country(iso2code, iso3code, name, capital, continent, region,
						surface, population, goverment, lat, longitude);
				landLijst.add(newCountry);
			}
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return landLijst;
	}
	
	public Country update(Country country){
		String query = "UPDATE country SET code2='" + country.getCode()
		+ "', name='" + country.getName() + "', capital='"
		+ country.getCapital() + "', continent='" + country.getContinent()
		+ "', region='" + country.getRegion() + "', surfacearea="
		+ country.getSurface() + ", population="
		+ country.getPopulation() + ", governmentform='"
		+ country.getGovernment() + "', latitude=" + country.getLatitude()+ " Where code='"+country.getIso3Code()+"'";
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
		} catch (SQLException sqle) {sqle.printStackTrace();}
		return findByCode(country.getCode());
	}
	
	public Country insert(Country country){
		String query = "INSERT INTO country (code, name, continent, region, surfacearea, population, code2, capital, governmentform, latitude, longitude) VALUES ('"
				+country.getIso3Code() 
				+ "', '"
				+ country.getName()
				+"', '"
				+country.getContinent()
				+"', '"
				+country.getRegion()
				+"', "
				+country.getSurface()
				+", "
				+country.getPopulation()
				+", '"
				+country.getCode()
				+"', '"
				+country.getCapital()
				+"', '"
				+country.getGovernment()
				+"', "
				+country.getLatitude()
				+", "
				+country.getLongitude()
				+")";
		System.out.println(query);
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
		} catch (SQLException sqle) {sqle.printStackTrace();}
		return findByCode(country.getCode());
	}
	
	public boolean delete(Country country){
		String query = "Delete From country where code = '"+ country.getIso3Code() + "'";
		System.out.println(query);
		try (Connection con = super.getConnection()){
		Statement stmt = con.createStatement();
		ResultSet dbResultSet = stmt.executeQuery(query);
		} catch (SQLException sqle) {sqle.printStackTrace();}
		return true;
	}
	
	public ArrayList<Country> zoekFunctie(String zoekWaarde){
		ArrayList<Country> landLijst = new ArrayList<Country>();
		String query = "Select * from country where name like ('%" + zoekWaarde + "%')";
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while(dbResultSet.next()){
				String iso2code = dbResultSet.getString("code");
				String iso3code = dbResultSet.getString("code2");
				String name = dbResultSet.getString("name");
				String capital = dbResultSet.getString("capital");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				Double surface = dbResultSet.getDouble("surfacearea");
				int population = dbResultSet.getInt("population");
				String goverment = dbResultSet.getString("governmentform");
				Double lat = dbResultSet.getDouble("latitude");
				Double longitude = dbResultSet.getDouble("longitude");
				Country newCountry = new Country(iso2code, iso3code, name, capital, continent, region,
						surface, population, goverment, lat, longitude);
				landLijst.add(newCountry);
			}
		} catch (SQLException sqle) {sqle.printStackTrace(); }
		return landLijst;
	}
}