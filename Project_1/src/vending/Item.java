package vending;

public class Item {
    private String name;
    private double price;
    private int quantity;
    private String key;
    private boolean isSpecial;

    public Item(String name, double price, int quantity, String key, boolean isSpecial) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be null or blank.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Item key cannot be null or blank.");
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.key = key.toUpperCase().trim();
        this.isSpecial = isSpecial;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }

    public String getKey() {
        return key;
    }

    public boolean isSpecial() {
        return isSpecial;
    }
}