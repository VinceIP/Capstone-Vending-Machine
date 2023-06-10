package com.techelevator.view;

import com.techelevator.*;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Logger {

    private PrintWriter printWriter;
    private Scanner in;
    public static String inventoryFilePath = "vendingmachine.csv";
    public static String logFilePath = "Log.txt";

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Logger() {
        try{
            printWriter = new PrintWriter(new File(logFilePath));
        }
        catch(FileNotFoundException e){
            System.out.println("Logger couldn't write to new file.");
        }
    }

    public String logPurchase(Item item, double balance){
        String output = item.getName() + "  " + item.getSlotLocation() + " " +
                "$" + df.format(item.getPrice()) + " $" + df.format(balance);
        writeLogEntry(output);
        return output;
    }

    public String logFeedMoney(double moneyFed, double balance){
        String output = "FEED MONEY: $" + df.format(moneyFed) + " $" + df.format(balance);
        writeLogEntry(output);
        return output;
    }

    public String logGiveChange(double changeGiven, double balance){
        String output = "GIVE CHANGE: $" + df.format(changeGiven) + " $" + df.format(balance);
        writeLogEntry(output);
        return output;
    }

    public void writeLogEntry(String string){
        LocalDateTime now = LocalDateTime.now();
        String output = now.toLocalDate() + " " +
                now.toLocalTime().getHour() + ":" +
                now.toLocalTime().getMinute() + ":" +
                now.toLocalTime().getSecond();
        output += " " + string;

        printWriter.println(output);
        printWriter.flush();
    }
}
