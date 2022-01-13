/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;


import entities.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tha
 */
public class PassengerDTO {
    private int id;
    private String name;
    private String date;
    private String time;
    private String location;
    private String duration;
    private String packingList;


    public PassengerDTO(String name, String date, String time, String location, String duration, String packingList) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.packingList = packingList;
    }

    public static List<PassengerDTO> getDtos(List<Passenger> passengers){
        List<PassengerDTO> passengerDTOS = new ArrayList<>();
        passengers.forEach(passenger->passengerDTOS.add(new PassengerDTO(passenger)));
        return passengerDTOS;
    }


    public PassengerDTO(Passenger passenger) {

            this.id = passenger.getId();
            this.name = passenger.getName();
            this.date = passenger.getDate();
            this.time = passenger.getTime();
            this.location = passenger.getLocation();
            this.duration = passenger.getDuration();
            this.packingList = passenger.getPackingList();
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassengerDTO)) return false;
        PassengerDTO that = (PassengerDTO) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(getLocation(), that.getLocation()) && Objects.equals(getDuration(), that.getDuration()) && Objects.equals(getPackingList(), that.getPackingList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDate(), getTime(), getLocation(), getDuration(), getPackingList());
    }
}
