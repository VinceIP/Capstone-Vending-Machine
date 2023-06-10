package com.techelevator;

import com.techelevator.Item;
import com.techelevator.ItemType;

public class Candy extends Item implements ItemType {

    public Candy(String name, double price, String itemType, String slotLocation) {
        super(name, price, itemType, slotLocation);
        this.sound = "Munch Munch, Yum!";
    }
}
