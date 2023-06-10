package com.techelevator;

import com.techelevator.view.Logger;
import com.techelevator.view.Menu;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select product";
    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE};
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};
    private double money = 0.00;
    private Inventory inventory = new Inventory();

    private static final int INITIAL_STOCK = 5;
    private Menu menu;

    private Logger logger = new Logger();

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // display vending machine items
                System.out.println(inventory.displayInventory());

            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                while (true) {
                    System.out.printf("Current Money Provided: $%.2f", money);
                    // do purchase
                    choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
                    // do purchase item stuff here
                    if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                        Item selectedItem = menu.showPurchaseSelection(inventory);
                        if (selectedItem == null) {
                            System.out.println("Invalid selection.");
                            continue;
                        } else if (money < selectedItem.price) {
                            System.out.println("Insufficient funds.");
                        } else {
                            System.out.println("You selected: " + selectedItem.getName());
                            purchaseItem(selectedItem);
                        }
                    }

                    // do feed money
                    try {
                        if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                            System.out.println("Enter money amount (in whole dollars): ");
                            Scanner userInput = new Scanner(System.in);
                            String inputMoney = userInput.nextLine();
                            int toAdd = Integer.parseInt(inputMoney);
                            if(toAdd <= 0) throw new NumberFormatException();
                            money += toAdd;
                            logger.logFeedMoney(toAdd, money);
                        }
                    } catch(NumberFormatException e) {
                        System.out.println("Invalid input.");
                    }

                    // do finish transaction
                    if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
                        returnChange();
                        break;
                    }
                }
            }
        }
    }

    public void purchaseItem(Item itemToPurchase) {
        inventory.removeFromInventory(itemToPurchase);
        money -= itemToPurchase.getPrice();
        itemToPurchase.makeSound();
        logger.logPurchase(itemToPurchase, money);
    }

    public void returnChange() {
        double quarter = 0.25;
        double dime = 0.10;
        double nickel = 0.05;
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;
        double changeGiven = 0.0;
        while (money > 0.00) {
            if (money >= 0.25) {
                quarterCount++;
                changeGiven += 0.25;
                money -= 0.25;
            } else if (money >= 0.10) {
                dimeCount++;
                changeGiven += 0.10;
                money -= 0.10;
            } else {
                nickelCount++;
                changeGiven += 0.05;
                money = 0;
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(quarterCount + " quarters, " + dimeCount + " dimes, and " + nickelCount + " nickels.");
        System.out.println("Your total change is: $" + df.format(changeGiven));
        logger.logGiveChange(changeGiven, money);
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
