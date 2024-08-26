package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "airports")
public class Airport {
    @Id
    private String portId;
    @Column(name="name",nullable=false)
    private String name;
    @Column(name="city",nullable=false)
    private String city;
    @Column(name="country",nullable=false)
    private String country;

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
