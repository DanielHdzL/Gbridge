package com.gbridge.gbridge.Domain;

public class Bill {

    private Product product;
    private float cost;

    public Bill(Product product, float cost) {
        this.product = product;
        this.cost = cost;
    }

    public Bill(){}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
