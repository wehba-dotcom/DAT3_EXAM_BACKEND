package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




import exceptions.TripNotFoundException;
import facades.TripFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


//Todo Remove or change relevant parts before ACTUAL use
@Path("trip")
public class TripResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final TripFacade FACADE =  TripFacade.getTripFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo()  {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getTripsCount() throws TripNotFoundException {
       
        long count = FACADE.getTripsCount();

        return "{\"count\":"+count+"}";
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allTrips() throws TripNotFoundException {
        return GSON.toJson(FACADE.getAllTrips());
    }


}
