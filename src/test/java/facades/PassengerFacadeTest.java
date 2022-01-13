package facades;


import entities.Passenger;
import exceptions.PassengerNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PassengerFacadeTest {

    private static EntityManagerFactory emf;
    private static PassengerFacade facade;
    private static Passenger p1, p2, p3;


    public PassengerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PassengerFacade.getPassengerFacade(emf);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        p1 = new Passenger("PN1", "PD1", "PT1","PL1","PD1","PP1");
        p2 = new Passenger("PN2", "PD2", "PT2","PL2","PD2","PP2");
        p3 = new Passenger("PN3", "PD3", "PT3","PL3","PD3","PP3");



        try {
            em.getTransaction().begin();
            em.createNamedQuery("Passenger.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }





     @Test
    public void getPerson() throws PassengerNotFoundException {
        String expected = p1.getName();
        String actual = facade.getPassengerById(p1.getId()).getName();
        assertEquals(expected, actual);
    }

    @Test
    public void getAllPersons() throws PassengerNotFoundException {
        assertEquals(3, facade.getAllPassenger().size());
    }


}