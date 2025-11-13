package cue.edu.co.cafeteria.patterns.structural.decorator;
import cue.edu.co.cafeteria.model.Product;

public abstract class ProductDecorator implements Product {
    protected final Product wrapped;

    public ProductDecorator(Product wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String getName() {
        return wrapped.getName();
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription();
    }

    @Override
    public double getPrice() {
        return wrapped.getPrice();
    }

    @Override
    public String toString() {
        return wrapped.toString();
    }
}
