package com.dsv.td1.si3_ihm_aliments.product;

public class Vegetaux extends Product {

    private String origin;
    private String variety;
    private String category;


    public Vegetaux(String name, String quantity, String pricePerKg, String imageName) {
        super(name, quantity, pricePerKg, imageName);
    }

    public Vegetaux(String name, String quantity, String pricePerKg, String imageName, String origin, String variety, String category) {
        super(name, quantity, pricePerKg, imageName);
        this.origin = origin;
        this.variety = variety;
        this.category = category;
    }
}
