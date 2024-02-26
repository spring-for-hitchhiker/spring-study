package com.apress.springrecipes.shop;

import javax.annotation.Resource;
import javax.inject.Inject;

@Resource
public class Disc extends Product {

    @Inject
    private int capacity;

    public Disc() {
        super();
    }

    public Disc(String name, double price) {
        super(name, price);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
