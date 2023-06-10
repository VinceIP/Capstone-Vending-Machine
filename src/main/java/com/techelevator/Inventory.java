package com.techelevator;

import com.techelevator.view.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

public class Inventory {
    private Map<Item, Integer> currentStock = new HashMap<>();
    //private Map<Item, Integer> sortedMap;
    private final int DEFAULT_ITEM_QUANTITY = 5;

    public Inventory(){
        initializeInventory();
    }

    public Map<Item, Integer> getCurrentStock() {
        return currentStock;
    }

    public void initializeInventory(){
        //Read file
        //Get each item from line in file
        //Create new Item object, put it in currentStock map
        File inventoryFile = new File(Logger.inventoryFilePath);
        try (Scanner dataInput = new Scanner(inventoryFile)) {
            while (dataInput.hasNextLine()) {
                String line = dataInput.nextLine();
                String[] splitLine = line.split("\\|");                //Split each line at pipe character
                if (splitLine[3].equals("Chip")) {
                    currentStock.put(new Chips(splitLine[1], Double.valueOf(splitLine[2]), splitLine[3], splitLine[0]), DEFAULT_ITEM_QUANTITY);
                    //Create a new chip item
                } else if (splitLine[3].equals("Drink")) {
                    //Create a drink
                    currentStock.put(new Drink(splitLine[1], Double.valueOf(splitLine[2]), splitLine[3], splitLine[0]), DEFAULT_ITEM_QUANTITY);
                } else if (splitLine[3].equals("Gum")) {
                    //Create a gum
                    currentStock.put(new Gum(splitLine[1], Double.valueOf(splitLine[2]), splitLine[3], splitLine[0]), DEFAULT_ITEM_QUANTITY);
                } else if (splitLine[3].equals("Candy")) {
                    //Create a candy
                    currentStock.put(new Candy(splitLine[1], Double.valueOf(splitLine[2]), splitLine[3], splitLine[0]), DEFAULT_ITEM_QUANTITY);
                }
            }
            //Consider sorting inventory map!
            //sortedMap = new TreeMap<>(currentStock);

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public void removeFromInventory(Item item){
        for (Map.Entry<Item, Integer> e: currentStock.entrySet()){
            if (e.getKey().getName().equals(item.getName())){
                e.setValue(e.getValue() - 1);
            }
        }

    }

    public String displayInventory(){
        String toOutput = "";
        DecimalFormat df = new DecimalFormat("0.00");
        for(Map.Entry<Item,Integer> e: currentStock.entrySet()){
            toOutput += e.getKey().getSlotLocation() + " - ";
            toOutput += e.getKey().getName() + ": ";
            toOutput += "Price: $" + df.format(e.getKey().getPrice()) + " ";
            toOutput += "Current stock: - (" + e.getValue().toString() + ")";
            toOutput += "\n";
        }
        return  toOutput;
    }
}
