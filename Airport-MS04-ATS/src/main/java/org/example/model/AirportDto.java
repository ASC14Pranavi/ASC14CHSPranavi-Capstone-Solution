package org.example.model;


public class AirportDto {
    private String portId;
    private String name;
    private String city;
    private String country;

    public AirportDto(){}

    public AirportDto(String portId, String name, String city, String country) {
        this.portId = portId;
        this.name = name;
        this.city = city;
        this.country = country;
    }
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
