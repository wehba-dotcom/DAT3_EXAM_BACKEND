package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@NamedQuery(name = "Tourist.deleteAllRows", query = "DELETE from Tourist")
public class Tourist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private long phone;
    private String email;
    private int birthyear;
    private String gender;

    @ManyToMany
    List<Trip> trips;


    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrip(List<Trip> trips) {
        this.trips = trips;
    }


    public Tourist() {
    }

    public Tourist(String address, long phone, String email, int birthyear, String gender) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthyear = birthyear;
        this.gender = gender;
        this.trips = new ArrayList<>();
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
    public Tourist(List<Trip> trips){
    this.trips = trips;
    }
}
