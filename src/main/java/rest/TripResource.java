package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import dtos.TripDTO;
import exceptions.MissingInputException;
import exceptions.TripNotFoundException;
import facades.TripFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



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

    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrip(String a) throws TripNotFoundException, MissingInputException {
        TripDTO tripDTO = GSON.fromJson(a,TripDTO.class);
        TripDTO reslt = FACADE.create(tripDTO.getName(),tripDTO.getDate(),tripDTO.getTime(),tripDTO.getDuration(),tripDTO.getLocation(),tripDTO.getPackingList());
        return Response.ok().entity(GSON.toJson(reslt)).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTrip(@PathParam("id")int id, String a) throws TripNotFoundException,MissingInputException
    {
        TripDTO tripDTO = GSON.fromJson(a,TripDTO.class);
        tripDTO.setId(id);
        TripDTO result = FACADE.editTrip(tripDTO);
        return Response.ok().entity(GSON.toJson(result)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteTrip(@PathParam("id") int id) throws TripNotFoundException {
        TripDTO result = FACADE.deletetrip(id);
        return Response.ok().entity(GSON.toJson(result)).build();
    }


}
