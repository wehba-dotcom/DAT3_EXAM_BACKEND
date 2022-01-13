/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.GuideDTO;
import dtos.TouristDTO;
import dtos.TripDTO;
import dtos.RenameMeDTO;
import entities.Guide;
import entities.Tourist;
import entities.Trip;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import exceptions.MissingInputException;
import exceptions.TripNotFoundException;
import utils.EMF_Creator;

import java.util.List;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = FacadeExample.getFacadeExample(emf);
        fe.create(new RenameMeDTO(new RenameMe("First 1", "Last 1")));
        fe.create(new RenameMeDTO(new RenameMe("First 2", "Last 2")));
        fe.create(new RenameMeDTO(new RenameMe("First 3", "Last 3")));
    }
    public static void populate1() throws TripNotFoundException, MissingInputException {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    TripFacade fe = TripFacade.getTripFacade(emf);
        fe.create(new TripDTO(new Trip("wehba", "10/09/2022","08:30","Dubai"," dayes","glothes uder ware")));
        fe.create(new TripDTO(new Trip("wehba2", "11/06/2022","08:30","Bornholm"," dayes","glothes uder ware")));
        fe.create(new TripDTO(new Trip("wehba3", "04/05/2022","08:30","Aarus"," dayes","glothes uder ware")));
        fe.create(new TripDTO(new Trip("wehba4", "11/07/2022","08:30","Ittalia"," dayes","glothes uder ware")));

    }
    public static void populate3() throws TripNotFoundException, MissingInputException {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        TouristFacade fe = TouristFacade.getTouristFacade(emf);
        fe.createtourist(new TouristDTO(new Tourist("Rønne",456456,"weg@wrew",1983,"male")));
        fe.createtourist(new TouristDTO(new Tourist("GudHjem",66666777,"yuyuyu@wrew",1978,"female")));
    }
    public static void populate4() throws TripNotFoundException, MissingInputException {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
       GuideFacade fe = GuideFacade.getGuideFacade(emf);
        fe.createGuide(new GuideDTO(new Guide("Ram","Male",1978,"work with firma 20 years")));
        fe.createGuide(new GuideDTO(new Guide("das","feMale",1998,"work with firma7 years")));

    }

    public static void populate2() throws TripNotFoundException, MissingInputException {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        TripFacade fe = TripFacade.getTripFacade(emf);
        GuideFacade fe2 = GuideFacade.getGuideFacade(emf);

       Trip trip1  =new Trip("Fun", "10/09/2022","08:30","Dubai"," 5 dayes","glothes uder ware");
        Trip trip2  =new Trip("Nice", "01/06/2022","08:30","Bornholm"," 7 dayes","glothes uder ware");

        Guide guide1 = new Guide("Ram","Male",1978,"work with firma 20 years");
        Guide guide2 = new Guide("Mad","female",2000,"work with firma 20 years");
        Guide guide3 = new Guide("Sade","Male",1988,"work with firma 20 years");

        Tourist tourist1= new Tourist("Rønne",456456,"weg@wrew",1983,"male");
        Tourist tourist2= new Tourist("Allinge",234234,"wwww@wrew",1993,"female");





        em.getTransaction().begin();
        em.persist(tourist1);
        em.persist(tourist2);
        em.getTransaction().commit();


    }





    
    public static void main(String[] args) throws TripNotFoundException, MissingInputException {
        populate4();
    }
}
