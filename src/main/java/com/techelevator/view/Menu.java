package com.techelevator.view;

import com.techelevator.Inventory;
import com.techelevator.Item;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private PrintWriter out;
    private Scanner in;

    public Menu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }

    private void displayMenuOptions(Object[] options) {
        out.println();
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }

    public Item showPurchaseSelection(Inventory inventory) {
        while (true) {
            System.out.println(inventory.displayInventory());
            System.out.println("Please select an item from above: ");
            String input = in.nextLine();
            if (input.length() == 2) {
                for (Map.Entry<Item, Integer> e : inventory.getCurrentStock().entrySet()) { //Check each item in inventory
                    if (e.getKey().getSlotLocation().equals(input)) { //If user's key matches a key in inventory
                        if (e.getValue() > 0) { //If there is more than 0 in stock
                            return e.getKey();
                        } else {
                            System.out.println("Item out of stock.");
                        }
                    }
                }
            }
            return null;
        }

    }
}
