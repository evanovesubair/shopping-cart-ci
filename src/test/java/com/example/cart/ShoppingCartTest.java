package com.example.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    void addItemsIncreasesTotalItems() {
        cart.addItem("Pen", 10.0, 3);
        cart.addItem("Notebook", 50.0, 2);
        assertEquals(5, cart.getTotalItems());
    }

    @Test
    void calculateTotalReturnsCorrectAmount() {
        cart.addItem("Pen", 10.0, 3);
        cart.addItem("Notebook", 50.0, 2);
        assertEquals(130.0, cart.calculateTotal(), 0.001);
    }

    @Test
    void removeItemUpdatesCart() {
        cart.addItem("Pen", 10.0, 3);
        assertTrue(cart.removeItem("Pen"));
        assertEquals(0, cart.getTotalItems());
        assertFalse(cart.removeItem("Pen"));
    }

    @Test
    void discountIsAppliedCorrectly() {
        cart.addItem("Bag", 200.0, 1);
        assertEquals(180.0, cart.applyDiscount(10), 0.001);
    }

    @Test
    void invalidQuantityThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Pen", 10.0, 0));
    }
}
