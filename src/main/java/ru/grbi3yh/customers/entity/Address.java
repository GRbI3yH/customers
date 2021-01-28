package ru.grbi3yh.customers.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "public", name = "address")
public class Address {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "contry")
    private String contry;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "flat")
    private String flat;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    public Address(){
        created = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
        modified = new Date();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
        modified = new Date();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        modified = new Date();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
        modified = new Date();
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
        modified = new Date();
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
        modified = new Date();
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }
}
