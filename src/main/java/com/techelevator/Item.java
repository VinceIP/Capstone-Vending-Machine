package com.techelevator;

public class Item implements ItemType{

    protected String name;
    protected double price;
    protected String itemType;
    protected String slotLocation;
    protected String sound = "";

    public Item(String name, double price, String itemType, String slotLocation) {
        this.name = name;
        this.price = price;
        this.itemType = itemType;
        this.slotLocation = slotLocation;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getItemType() {
        return itemType;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    @Override
    public String makeSound() {
        System.out.println(this.sound);
        return this.sound;
    }
}
