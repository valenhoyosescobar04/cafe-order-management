package cue.edu.co.cafeteria.menu;


import cue.edu.co.cafeteria.model.BaseProduct;
import cue.edu.co.cafeteria.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Menu {
    private final List<Product> items = new ArrayList<>();

    public Menu() {
        loadDefaultMenu();
    }

    private void loadDefaultMenu() {
        items.add(new BaseProduct("Americano", "Café americano 250ml", 2.50));
        items.add(new BaseProduct("Latte", "Latte con leche entera 300ml", 3.20));
        items.add(new BaseProduct("Muffin", "Muffin de arándanos", 1.80));
        items.add(new BaseProduct("Cheesecake", "Porción de cheesecake", 3.50));
    }

    public List<Product> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(Product p) { items.add(p); }

    public void removeItem(Product p) { items.remove(p); }
}
