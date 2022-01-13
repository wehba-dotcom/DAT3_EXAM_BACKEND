package facades;

import dtos.PassengerDTO;
import entities.Passenger;
import exceptions.MissingInputException;
import exceptions.PassengerNotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;


public class PassengerFacade {

    private static PassengerFacade instance;
    private static EntityManagerFactory emf;


    private PassengerFacade() {}

    public static PassengerFacade getPassengerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PassengerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PassengerDTO create(PassengerDTO pd) throws PassengerNotFoundException,MissingInputException {
      Passenger passenger = new Passenger(pd.getName(), pd.getDate(), pd.getTime(),pd.getLocation(), pd.getDuration(), pd.getPackingList());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(passenger);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PassengerDTO(passenger);
    }
    public PassengerDTO getPassengerById(int id) throws  PassengerNotFoundException {
            EntityManager em = getEntityManager();
          Passenger passenger = em.find(Passenger.class, id);
            if (passenger == null) {
                throw new PassengerNotFoundException(404, "No person with provided id found");
            }
            return new PassengerDTO(passenger);
        }


    

    public long getPassengerCount() throws PassengerNotFoundException{
        EntityManager em = getEntityManager();
        try{
            long PaasengerCount = (long)em.createQuery("SELECT COUNT(r) FROM Passenger r").getSingleResult();
            return PaasengerCount;
        }finally{  
            em.close();
        }
    }
    
    public List<PassengerDTO> getAllPassenger() throws  PassengerNotFoundException{
        EntityManager em = emf.createEntityManager();
        TypedQuery<Passenger> query = em.createQuery("SELECT r FROM Passenger r", Passenger.class);
        List<Passenger> passengers = query.getResultList();
        return PassengerDTO.getDtos(passengers);
    }
    
    public static void main(String[] args) throws PassengerNotFoundException {
        emf = EMF_Creator.createEntityManagerFactory();
        PassengerFacade pf = getPassengerFacade(emf);
        pf.getAllPassenger().forEach(dto->System.out.println(dto));
    }

}
