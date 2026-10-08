package com.example.cart;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Map<String, Double> prices = new HashMap<>();
    private final Map<String, Integer> quantities = new HashMap<>();

    // Add an item (or increase its quantity if already in the cart)
    public void addItem(String name, double price, int quantity) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }
        if (price < 0 || quantity <= 0) {
            throw new IllegalArgumentException("Invalid price or quantity");
        }
        prices.put(name, price);
        quantities.merge(name, quantity, Integer::sum);
    }

    // Remove an item completely; returns true if it existed
    public boolean removeItem(String name) {
        if (!quantities.containsKey(name)) {
            return false;
        }
        quantities.remove(name);
        prices.remove(name);
        return true;
    }

    // Total number of units in the cart
    public int getTotalItems() {
        int total = 0;
        for (int qty : quantities.values()) {
            total += qty;
        }
        return total;
    }

    // Total price before discount
    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<String, Integer> entry : quantities.entrySet()) {
            total += prices.get(entry.getKey()) * entry.getValue();
        }
        return total;
    }

    // Apply a percentage discount (0 to 100) to the total
    public double applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        return calculateTotal() * (1 - percent / 100.0);
    }
}
