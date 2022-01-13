/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;


import entities.Guide;
import entities.Tourist;
import entities.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TouristDTO {
    private int id;
    private String address;
    private long phone;
    private String email;
    private int birthyear;
    private String gender;
    List<TripDTO> tripDTOS;


    public TouristDTO(String address, long phone, String email, int birthyear, String gender,List<TripDTO> tripDTOS) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthyear = birthyear;
        this.gender = gender;
        this.tripDTOS = tripDTOS;
    }

    public static List<TouristDTO> getDtos(List<Tourist> tourists){
        List<TouristDTO> touristDTOS = new ArrayList<>();
        tourists.forEach(tourist->touristDTOS.add(new TouristDTO(tourist)));
        return touristDTOS;
    }

    public TouristDTO(Tourist tourist) {

        this.id = tourist.getId();
        this.address = tourist.getAddress();
        this.phone = tourist.getPhone();
        this.email = tourist.getEmail();
        this.birthyear = tourist.getBirthyear();
        this.gender = tourist.getGender();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TouristDTO)) return false;
        TouristDTO that = (TouristDTO) o;
        return getId() == that.getId() && getPhone() == that.getPhone() && getBirthyear() == that.getBirthyear() && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getGender(), that.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress(), getPhone(), getEmail(), getBirthyear(), getGender());
    }
}
