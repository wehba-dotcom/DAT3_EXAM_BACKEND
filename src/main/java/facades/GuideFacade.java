package facades;

import dtos.GuideDTO;
import dtos.TouristDTO;
import entities.Guide;
import exceptions.MissingInputException;
import exceptions.TripNotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;


public class GuideFacade {

    private static GuideFacade instance;
    private static EntityManagerFactory emf;


    private GuideFacade() {}

    public static GuideFacade getGuideFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GuideFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public GuideDTO createGuide(GuideDTO gd) throws TripNotFoundException,MissingInputException {
     Guide guide = new Guide(gd.getName(), gd.getGender(), gd.getBirthYear(), gd.getProfile());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(guide);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new GuideDTO(guide);
    }
    public GuideDTO getGuideById(int id) throws TripNotFoundException {
            EntityManager em = getEntityManager();
          Guide guide = em.find(Guide.class, id);
            if (guide == null) {
                throw new TripNotFoundException(404, "No person with provided id found");
            }
            return new GuideDTO(guide);
        }


    

    public long getGuidesCount() throws TripNotFoundException {
        EntityManager em = getEntityManager();
        try{
            long GuideCount = (long)em.createQuery("SELECT COUNT(r) FROM Guide r").getSingleResult();
            return GuideCount;
        }finally{  
            em.close();
        }
    }
    
    public List<GuideDTO> getAllGuides() throws TripNotFoundException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Guide> query = em.createQuery("SELECT r FROM Guide r", Guide.class);
        List<Guide> guides = query.getResultList();
        return GuideDTO.getDtos(guides);
    }
    
    public static void main(String[] args) throws TripNotFoundException {
        emf = EMF_Creator.createEntityManagerFactory();
        GuideFacade pf = getGuideFacade(emf);
        pf.getAllGuides().forEach(dto->System.out.println(pf));
    }

}
