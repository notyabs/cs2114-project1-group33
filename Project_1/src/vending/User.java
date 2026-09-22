package vending;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private double physicalWallet;
    private double digitalWallet;
    private List<Item> itemsPurchased;

    public User(String name, double physicalWallet, double digitalWallet) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty.");
        }
        if (physicalWallet < 0) {
            throw new IllegalArgumentException("Physical wallet cannot be negative.");
        }
        if (digitalWallet < 0) {
            throw new IllegalArgumentException("Digital wallet cannot be negative.");
        }
        this.name = name;
        this.physicalWallet = physicalWallet;
        this.digitalWallet = digitalWallet;
        this.itemsPurchased = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty.");
        }
        this.name = name;
    }

    public double getPhysicalWallet() {
        return physicalWallet;
    }

    public void setPhysicalWallet(double physicalWallet) {
        if (physicalWallet < 0) {
            throw new IllegalArgumentException("Wallet balance cannot be negative.");
        }
        this.physicalWallet = physicalWallet;
    }

    public double getDigitalWallet() {
        return digitalWallet;
    }

    public void setDigitalWallet(double digitalWallet) {
        if (digitalWallet < 0) {
            throw new IllegalArgumentException("Wallet balance cannot be negative.");
        }
        this.digitalWallet = digitalWallet;
    }

    public List<Item> getItemsPurchased() {
        return itemsPurchased;
    }

    public void addPurchasedItem(Item item) {
        if (item != null) {
            this.itemsPurchased.add(item);
        }
    }
}