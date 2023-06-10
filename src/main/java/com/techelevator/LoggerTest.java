package com.techelevator;

import com.techelevator.view.Logger;
import org.junit.Assert;
import org.junit.Test;

public class LoggerTest {
    private Logger logger = new Logger();

    @Test
    public void Logger_test_log_purchase(){
        Item testItem = new Candy("Test Candy", 3.00, "Candy", "A3");
        double balance = 5.00;
        String expected = "Test Candy  A3 $3.00 $5.00"; //We expect balance to not get subtracted, because only a real VendingMachineCLI can do that
        String actual = logger.logPurchase(testItem, balance);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void Logger_test_log_feed_money(){
        double moneyFed = 1.50;
        double balance = 0.00;
        String expected = "FEED MONEY: $1.50 $0.00"; //Balance cannot update without a real VendingMachineCLI
        String actual = logger.logFeedMoney(moneyFed, balance);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void Logger_test_log_give_change(){
        double changeGiven = 3.00;
        double balance = 0;
        String expected = "GIVE CHANGE: $3.00 $0.00";
        String actual = logger.logGiveChange(changeGiven, balance);
        Assert.assertEquals(expected,actual);
    }

}
