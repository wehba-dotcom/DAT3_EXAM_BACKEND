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


public class GuideDTO {
    private int id;
    private String name;
    private String gender;
    private long birthYear;
    private String profile;
    List<TripDTO> tripDTOS;

    public List<TripDTO> getTripDTOS() {
        return tripDTOS;
    }

    public void setTripDTOS(List<TripDTO> tripDTOS) {
        this.tripDTOS = tripDTOS;
    }

    public GuideDTO(String name, String gender, long birthYear, String profile,List<TripDTO> tripDTOS) {
        this.name = name;
        this.gender = gender;
        this.birthYear = birthYear;
        this.profile = profile;
    }

    public static List<GuideDTO> getDtos(List<Guide> guides) {
        List<GuideDTO> guideDTOS = new ArrayList<>();
        guides.forEach(guide -> guideDTOS.add(new GuideDTO(guide)));
        return guideDTOS;
    }


    public GuideDTO(Guide guide) {

        this.id = guide.getId();
        this.name = guide.getName();
        this.gender = guide.getGender();
        this.birthYear = guide.getBirthYear();
        this.profile = guide.getProfile();
        this.tripDTOS= TripDTO.getDtos(guide.getTrips());
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(long birthYear) {
        this.birthYear = birthYear;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}