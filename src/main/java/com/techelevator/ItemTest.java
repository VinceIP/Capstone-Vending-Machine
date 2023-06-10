package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class ItemTest {

    private Item itemTest;

    @Test
    public void item_test_candy_sound() {
        String expected = "Munch Munch, Yum!";
        itemTest = new Candy("Candy Test", 1.10, "Candy", "A1");
        String actual = itemTest.makeSound();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void item_test_drink_sound() {
        String expected = "Glug Glug, Yum!";
        itemTest = new Drink("Drink Test", 1.10, "Drink", "B2");
        String actual = itemTest.makeSound();
        Assert.assertEquals(expected, actual);
    }
}
