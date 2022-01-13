/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PassengerDTO;
import dtos.RenameMeDTO;
import entities.Passenger;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;

import exceptions.MissingInputException;
import exceptions.PassengerNotFoundException;
import utils.EMF_Creator;

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
    public static void populate1() throws PassengerNotFoundException, MissingInputException {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    PassengerFacade fe = PassengerFacade.getPassengerFacade(emf);
        fe.create(new PassengerDTO(new Passenger("wehba", "10/09/2022","08:30","Dubai"," dayes","glothes uder ware")));
        fe.create(new PassengerDTO(new Passenger("wehba2", "11/06/2022","08:30","Bornholm"," dayes","glothes uder ware")));
        fe.create(new PassengerDTO(new Passenger("wehba3", "04/05/2022","08:30","Aarus"," dayes","glothes uder ware")));
        fe.create(new PassengerDTO(new Passenger("wehba4", "11/07/2022","08:30","Ittalia"," dayes","glothes uder ware")));

    }
    
    public static void main(String[] args) throws PassengerNotFoundException, MissingInputException {
        populate1();
    }
}
