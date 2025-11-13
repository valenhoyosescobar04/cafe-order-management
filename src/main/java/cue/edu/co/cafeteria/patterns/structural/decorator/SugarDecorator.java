package cue.edu.co.cafeteria.patterns.structural.decorator;


import cue.edu.co.cafeteria.model.Product;

public class SugarDecorator extends ProductDecorator {
    private final double extraPrice = 0.10;
    private final String label = "Sugar";

    public SugarDecorator(Product wrapped) {
        super(wrapped);
    }

    @Override
    public String getName() {
        return wrapped.getName() + " + " + label;
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " (con " + label + ")";
    }

    @Override
    public double getPrice() {
        return wrapped.getPrice() + extraPrice;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%.2f)", getName(), getDescription(), getPrice());
    }
}
