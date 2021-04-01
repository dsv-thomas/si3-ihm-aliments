package com.dsv.td1.si3_ihm_aliments.model;

public class Producer {
    private String name;
    private String place;
    private String pNumber;
    private boolean isBio;

    public Producer(String name, String place, String pNumber, boolean isBio) {
        this.name = name;
        this.place = place;
        this.pNumber = pNumber;
        this.isBio = isBio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isBio() {
        return isBio;
    }

    public void setBio(boolean bio) {
        isBio = bio;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }
}
