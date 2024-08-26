package com.elt.model;

public class PlaneDto {

    private String planeId;
    private String name;
    private int capacity;
    private String model;
    private String manufacturer;

    // Constructors
    public PlaneDto() {}

    public PlaneDto(String planeId,String name, int capacity, String model, String manufacturer) {
        this.planeId = planeId;
        this.name=name;
        this.capacity = capacity;
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getPlaneId() {
        return planeId;
    }

    public void setPlaneId(String planeId) {
        this.planeId = planeId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
