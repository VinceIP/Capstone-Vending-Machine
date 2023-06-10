package com.techelevator;

public class Drink extends Item implements ItemType {

    public Drink(String name, double price, String itemType, String slotLocation) {
        super(name, price, itemType, slotLocation);
        this.sound = "Glug Glug, Yum!";
    }
}
