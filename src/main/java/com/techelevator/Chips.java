package com.techelevator;

public class Chips extends Item implements ItemType{

    public Chips(String name, double price, String itemType, String slotLocation) {
        super(name, price, itemType, slotLocation);
        this.sound = "Crunch Crunch, Yum!";
    }
}
