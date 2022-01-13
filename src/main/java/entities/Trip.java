package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@NamedQuery(name = "Trip.deleteAllRows", query = "DELETE from Trip")
public class Trip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String date;
    private String time;
    private String location;
    private String duration;
    private String packingList;

    @OneToMany(mappedBy = "trip", cascade =CascadeType.PERSIST )
    List<Guide> guides;


    @ManyToMany(mappedBy = "trips", cascade =CascadeType.PERSIST )
    List<Tourist> tourists;


    public Trip() {
    }

    public Trip(String name, String date, String time, String location, String duration, String packingList) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.packingList = packingList;
        this.guides= new ArrayList<>();
        this.tourists= new ArrayList<>();
    }

    public List<Guide> getGuides() {
        return guides;
    }

    public void addGuide(Guide guide) {
        this.guides.add(guide);
        if (guide != null) {
            guide.setTrip(this);
        }
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

}
