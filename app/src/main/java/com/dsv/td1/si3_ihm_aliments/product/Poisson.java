package com.dsv.td1.si3_ihm_aliments.product;

public class Poisson extends Product{

    private String fishingPlace;
    private String fishingDate;


    public Poisson(String name, String quantity, String pricePerKg, String imageName) {
        super(name, quantity, pricePerKg, imageName);
    }

    public Poisson(String name, String quantity, String pricePerKg, String imageName, String fishingPlace, String fishingDate) {
        super(name, quantity, pricePerKg, imageName);
        this.fishingPlace = fishingPlace;
        this.fishingDate = fishingDate;
    }
}
