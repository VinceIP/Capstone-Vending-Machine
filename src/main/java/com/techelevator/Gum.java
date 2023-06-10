package com.techelevator;

public class Gum extends Item implements ItemType {

    public Gum(String name, double price, String itemType, String slotLocation) {
        super(name, price, itemType, slotLocation);
        this.sound = "Chew Chew, Yum!";
    }
}
