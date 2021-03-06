/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;


import entities.Guide;
import entities.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TripDTO {
    private int id;
    private String name;
    private String date;
    private String time;
    private String location;
    private String duration;
    private String packingList;
    private String nameg;
    private String gender;
    private long birthYear;
    private String profile;
    private List<GuideDTO> guideDTOS;


    public List<GuideDTO> getGuideDTOS() {
        return guideDTOS;
    }

    public void setGuideDTOS(List<GuideDTO> guideDTOS) {
        this.guideDTOS = guideDTOS;
    }

    public TripDTO(String name, String date, String time, String location, String duration, String packingList, List<GuideDTO> guideDTOS) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.packingList = packingList;

    }

    public static List<TripDTO> getDtos(List<Trip> passengers){
        List<TripDTO> passengerDTOS = new ArrayList<>();
        passengers.forEach(passenger->passengerDTOS.add(new TripDTO(passenger)));
        return passengerDTOS;
    }


    public TripDTO(Trip trip) {

            this.id = trip.getId();
            this.name = trip.getName();
            this.date = trip.getDate();
            this.time = trip.getTime();
            this.location = trip.getLocation();
            this.duration = trip.getDuration();
            this.packingList = trip.getPackingList();
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
        if (!(o instanceof TripDTO)) return false;
        TripDTO that = (TripDTO) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(getLocation(), that.getLocation()) && Objects.equals(getDuration(), that.getDuration()) && Objects.equals(getPackingList(), that.getPackingList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDate(), getTime(), getLocation(), getDuration(), getPackingList());
    }
}
