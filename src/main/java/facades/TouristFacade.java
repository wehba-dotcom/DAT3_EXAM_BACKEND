package facades;

import dtos.GuideDTO;
import dtos.TouristDTO;
import entities.Guide;
import entities.Tourist;
import exceptions.MissingInputException;
import exceptions.TripNotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;


public class TouristFacade {

    private static TouristFacade instance;
    private static EntityManagerFactory emf;


    private TouristFacade() {}

    public static TouristFacade getTouristFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TouristFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public TouristDTO createtourist(TouristDTO gd) throws TripNotFoundException,MissingInputException {
   Tourist tourist = new Tourist(gd.getAddress(), gd.getPhone(), gd.getEmail(), gd.getBirthyear(),gd.getGender());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tourist);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new TouristDTO(tourist);
    }



    


    
    public List<TouristDTO> getAllTourists() throws TripNotFoundException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Tourist> query = em.createQuery("SELECT r FROM Tourist r", Tourist.class);
        List<Tourist> tourists = query.getResultList();
        return TouristDTO.getDtos(tourists);
    }
    
    public static void main(String[] args) throws TripNotFoundException {
        emf = EMF_Creator.createEntityManagerFactory();
        TouristFacade pf = getTouristFacade(emf);
        pf.getAllTourists().forEach(dto->System.out.println(pf));
    }

}
