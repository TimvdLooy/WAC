package nl.hu.v1wac.firstapp.webservices;
import nl.hu.v1wac.firstapp.model.Country;
import nl.hu.v1wac.firstapp.model.ServiceProvider;
import nl.hu.v1wac.firstapp.model.WorldService;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

@Path ("/Countries")
public class WorldResource {
	private ServiceProvider sp;
	WorldService service = sp.getWorldService();
	JsonArrayBuilder jab = Json.createArrayBuilder();
	JsonObjectBuilder job = Json.createObjectBuilder();
	
	//@GET
	//@Path("/{iso3}")
	//@Produces("application/json")
	//public void update(@PathParam("iso3") String iso3,  ){
	//	
	//}
	
	@GET
	@Path("/zoek/{name}")
	@Produces("application/json")
	public String zoekFunctie(@PathParam("name") String name){
		for(Country c : service.zoekFunctie(name)){
		
			job.add("name", c.getName());
			job.add("Hoofdstad", c.getCapital());
			job.add("Regio", c.getRegion());
			job.add("Oppervlakte", c.getSurface());
			job.add("Inwoners", c.getPopulation());
			job.add("lat", c.getLatitude());
			job.add("long", c.getLongitude());
			jab.add(job);
		
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Produces("application/json")
	public String getCountryNames(){
		
		for (Country c : service.getAllCountries()){
			job.add("name", c.getName());
			job.add("Hoofdstad", c.getCapital());
			job.add("Regio", c.getRegion());
			job.add("Oppervlakte", c.getSurface());
			job.add("Inwoners", c.getPopulation());
			job.add("lat", c.getLatitude());
			job.add("long", c.getLongitude());
			job.add("goverment", c.getGovernment());
			job.add("continent", c.getContinent());
			job.add("isoCode2", c.getIso3Code());
			job.add("getCode", c.getCode());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/{landCode}")
	@Produces("application/json")
	public String getCountryByCode(@PathParam("landCode") String id){
		
		Country c = service.getCountryByCode(id);
		
		job.add("code ", c.getCode());
		job.add("population ", c.getPopulation());
		job.add("government ", c.getGovernment());
		job.add("iso3 ", c.getIso3Code());
		job.add("name ", c.getName());
		job.add("lng ", c.getLongitude());
		job.add("continent ", c.getContinent());
		job.add("capital ", c.getCapital());
		job.add("region ", c.getRegion());
		job.add("surface ", c.getSurface());
		job.add("lat ", c.getLatitude());
		jab.add(job);
		
		JsonArray array = jab.build();
		return array.toString();
	}
	

	@GET
	@Path("/largestsurfaces")
	@Produces("application/json")

	public String getBiggestSurface(){
		for (Country C: service.get10LargestSurfaces()){
			job.add("name ", C.getName());
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	
	}

	@GET
	@Path("largestpopulation")
	@Produces("application/json")
	
	public String getLargestPop(){
		for (Country C: service.get10LargestPopulations()){
			job.add("name ", C.getName());
			jab.add(job);
		}
		
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@PUT
	@RolesAllowed("user")
	@Path("/{code}")
	@Produces("application/json")
	public void updateCountry(@PathParam("code") String code,
	                             @FormParam("nameWaarde") String name,
	                             @FormParam("hoofdstadWaarde") String capital,
	                             @FormParam("regioWaarde") String regio,
	                             @FormParam("oppervlakteWaarde") int surface,
	                             @FormParam("inwonersWaarde") int pop,
	                             @FormParam("isoCode2") String code2,
	                             @FormParam("lat") Double lat,
	                             @FormParam("longitude") Double longitude,
	                             @FormParam("continent") String continent,
	                             @FormParam("regering") String goverment) {
		WorldService service = ServiceProvider.getWorldService();
		Country country = new Country(code2, code, name, capital, continent, regio, surface, pop, goverment, lat, longitude);
		Country found = service.updateCountry(country);
	}
	
	@DELETE
	@RolesAllowed("user")
	@Path("delete/{code}")
	public boolean delete(@PathParam("code") String code,
							@FormParam("nameWaarde") String name,
	                        @FormParam("hoofdstadWaarde") String capital,
	                        @FormParam("regioWaarde") String regio,
	                        @FormParam("oppervlakteWaarde") int surface,
	                        @FormParam("inwonersWaarde") int pop,
	                        @FormParam("isoCode2") String code2,
	                        @FormParam("lat") Double lat,
	                        @FormParam("longitude") Double longitude,
	                        @FormParam("continent") String continent,
	                        @FormParam("regering") String goverment){
		WorldService service = ServiceProvider.getWorldService();
		Country country = new Country(code2, code, name, capital, continent, regio, surface, pop, goverment, lat, longitude);
		return service.deleteCountry(country);
	}
	
	@POST
	@RolesAllowed("user")
	@Produces("application/json")
	public void insert(@FormParam("code") String code,
						@FormParam("name") String name,
						@FormParam("continent") String continent,
						@FormParam("region") String region,
						@FormParam("surface") int surface,
						@FormParam("pop") int pop,
						@FormParam("code2") String code2,
						@FormParam("hoofdstad") String hoofdstad,
						@FormParam("regering") String regering,
						@FormParam("lat") Double lat,
						@FormParam("longitude") Double longitude){
		WorldService service = ServiceProvider.getWorldService();
		Country country = new Country(code2, code, name, hoofdstad, continent, region, surface, pop, regering, lat, longitude);
		Country found = service.insertCountry(country);
	}

}