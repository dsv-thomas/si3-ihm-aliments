package com.dsv.td1.si3_ihm_aliments.product;

public class Vegetaux extends Product {

    private String origin;
    private String variety;
    private String category;


    public Vegetaux(String name, String quantity, String pricePerKg) {
        super(name, quantity, pricePerKg);
    }

    public Vegetaux(String name, String quantity, String pricePerKg, String origin, String variety, String category) {
        super(name, quantity, pricePerKg);
        this.origin = origin;
        this.variety = variety;
        this.category = category;
    }
}
