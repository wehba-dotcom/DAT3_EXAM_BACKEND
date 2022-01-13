package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




import exceptions.PassengerNotFoundException;
import facades.PassengerFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


//Todo Remove or change relevant parts before ACTUAL use
@Path("passenger")
public class PassengerResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final PassengerFacade FACADE =  PassengerFacade.getPassengerFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo()  {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getpassengersCount() throws PassengerNotFoundException {
       
        long count = FACADE.getPassengerCount();

        return "{\"count\":"+count+"}";
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allPassengers() throws PassengerNotFoundException {
        return GSON.toJson(FACADE.getAllPassenger());
    }


}
