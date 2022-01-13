package facades;


import entities.Trip;
import exceptions.TripNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class TripFacadeTest {

    private static EntityManagerFactory emf;
    private static TripFacade facade;
    private static Trip p1, p2, p3;


    public TripFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = TripFacade.getTripFacade(emf);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        p1 = new Trip("PN1", "PD1", "PT1","PL1","PD1","PP1");
        p2 = new Trip("PN2", "PD2", "PT2","PL2","PD2","PP2");
        p3 = new Trip("PN3", "PD3", "PT3","PL3","PD3","PP3");



        try {
            em.getTransaction().begin();
            em.createNamedQuery("Trip.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }





     @Test
    public void getTrip() throws TripNotFoundException {
        String expected = p1.getName();
        String actual = facade.getTripById(p1.getId()).getName();
        assertEquals(expected, actual);
    }

    @Test
    public void getAllTrips() throws TripNotFoundException {
        assertEquals(3, facade.getAllTrips().size());
    }


}