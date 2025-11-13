package cue.edu.co.cafeteria.model;


public class BaseProduct implements Product {
    private final String name;
    private final String description;
    private final double price;

    public BaseProduct(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s - %s (%.2f)", name, description, price);
    }
}
