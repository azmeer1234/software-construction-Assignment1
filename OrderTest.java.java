package com.company;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Tax strategy interface
interface TaxStrategy {
    double calculateTax(Item item);
}

// USA tax strategy
class USATaxStrategy implements TaxStrategy {
    private static final Map<String, Double> stateRates = new HashMap<>();

    static {
        stateRates.put("CA", 0.075);
        stateRates.put("NY", 0.085);
    }

    private String state;

    public USATaxStrategy(String state) {
        this.state = state;
    }

    @Override
    public double calculateTax(Item item) {
        double rate = stateRates.getOrDefault(state, 0.05);
        return item.getPrice() * item.getQuantity() * rate;
    }
}

// EU tax strategy
class EUTaxStrategy implements TaxStrategy {
    @Override
    public double calculateTax(Item item) {
        return item.getPrice() * item.getQuantity() * 0.20;
    }
}

// China tax strategy
class ChinaTaxStrategy implements TaxStrategy {
    @Override
    public double calculateTax(Item item) {
        double rate = item.getProduct().equals("Electronics") ? 0.17 : 0.10;
        return item.getPrice() * item.getQuantity() * rate;
    }
}

// Item class
class Item {
    private String product;
    private double price;
    private int quantity;

    public Item(String product, double price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

// Order class
class Order {
    private List<Item> items;
    private TaxStrategy taxStrategy;

    public Order(List<Item> items, TaxStrategy taxStrategy) {
        this.items = items;
        this.taxStrategy = taxStrategy;
    }

    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            double subtotal = item.getPrice() * item.getQuantity();
            double tax = taxStrategy.calculateTax(item);
            total += subtotal + tax;
        }
        return total;
    }
}

// Test class
public class OrderTest {
    public static void main(String[] args) {
        Item item1 = new Item("Laptop", 1000, 1);
        Item item2 = new Item("Phone", 500, 2);

        TaxStrategy taxStrategy = new USATaxStrategy("CA");
        Order order = new Order(Arrays.asList(item1, item2), taxStrategy);

        System.out.printf("Total Order Cost: $%.2f%n", order.getTotal());
    }
}
