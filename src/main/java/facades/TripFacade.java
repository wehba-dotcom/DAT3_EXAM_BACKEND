package facades;

import dtos.TripDTO;
import entities.Guide;
import entities.Tourist;
import entities.Trip;
import exceptions.MissingInputException;
import exceptions.TripNotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;


public class TripFacade {

    private static TripFacade instance;
    private static EntityManagerFactory emf;


    private TripFacade() {}

    public static TripFacade getTripFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TripFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public TripDTO create(String name, String date, String time, String location, String duration, String packingList) throws TripNotFoundException,MissingInputException {
      Trip trip = new Trip(name,date,time,location,duration,packingList);

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(trip);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new TripDTO(trip);
    }
    public TripDTO getTripById(int id) throws TripNotFoundException {
            EntityManager em = getEntityManager();
          Trip trip = em.find(Trip.class, id);
            if (trip == null) {
                throw new TripNotFoundException(404, "No person with provided id found");
            }
            return new TripDTO(trip);
        }

    public TripDTO editTrip(TripDTO tripDTO) throws TripNotFoundException , MissingInputException {
        EntityManager em = getEntityManager();
        Trip trip = new Trip(tripDTO.getName(), tripDTO.getDate(),
                tripDTO.getTime(),tripDTO.getLocation(),tripDTO.getDuration(),tripDTO.getPackingList());
        trip.setId(tripDTO.getId());
        try {
            em.getTransaction().begin();
            em.merge(trip);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new TripDTO(trip);
    }

    public TripDTO deletetrip(int id) throws TripNotFoundException {
        EntityManager em = getEntityManager();
        Trip trip = em.find(Trip.class, id);
        Guide guide= em.find(Guide.class, trip.getGuide().getId());
        if (trip == null) {
            throw new TripNotFoundException(404, "Could not delete, provided id does not exist");
        }
        try {
            em.getTransaction().begin();
            em.remove(trip);
            em.remove(guide);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new TripDTO(trip);
    }
    

    public long getTripsCount() throws TripNotFoundException {
        EntityManager em = getEntityManager();
        try{
            long TripCount = (long)em.createQuery("SELECT COUNT(r) FROM Trip r").getSingleResult();
            return TripCount;
        }finally{  
            em.close();
        }
    }
    
    public List<TripDTO> getAllTrips() throws TripNotFoundException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Trip> query = em.createQuery("SELECT r FROM Trip  r", Trip.class);
        List<Trip> trips = query.getResultList();
        return TripDTO.getDtos(trips);
    }
    
    public static void main(String[] args) throws TripNotFoundException {
        emf = EMF_Creator.createEntityManagerFactory();
        TripFacade pf = getTripFacade(emf);
        pf.getAllTrips().forEach(dto->System.out.println(pf));
    }

}
