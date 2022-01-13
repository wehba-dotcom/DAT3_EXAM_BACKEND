package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@NamedQuery(name = "Guide.deleteAllRows", query = "DELETE from Guide")
public class Guide implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String gender;
    private long birthYear;
    private String profile;

    @OneToMany(mappedBy = "guide", cascade =CascadeType.PERSIST )
    List<Trip> trips;


    public Guide() {
    }

    public List<Trip> getTrips() {
        return trips;
    }


    public void addTrip(Trip trip) {
        this.trips.add(trip);
        if (trip != null) {
            trip.setGuide(this);
        }
    }

    public Guide(String name, String gender, long birthYear, String profile) {
        this.name = name;
        this.gender = gender;
        this.birthYear = birthYear;
        this.profile = profile;
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
