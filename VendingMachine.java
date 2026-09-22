package vending;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class VendingMachine {
    private Map<String, Item> inventory;
    private List<Riddle> riddles;

    public VendingMachine() {
        this.inventory = new LinkedHashMap<>();
        this.riddles = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item.");
        }
        inventory.put(item.getKey(), item);
    }

    public void addRiddle(Riddle riddle) {
        if (riddle == null) {
            throw new IllegalArgumentException("Cannot add null riddle.");
        }
        riddles.add(riddle);
    }

    public Item removeItem(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        String sanitizedKey = key.replaceAll("\\s+", "").toUpperCase();
        if (!inventory.containsKey(sanitizedKey)) {
            throw new IllegalArgumentException("Invalid selection code.");
        }
        Item item = inventory.get(sanitizedKey);
        if (item.getQuantity() <= 0) {
            throw new IllegalStateException("Item is out of stock.");
        }
        item.setQuantity(item.getQuantity() - 1);
        return item;
    }

    public boolean processPayment(User user, double price, String paymentType) {
        if (user == null || paymentType == null) {
            throw new IllegalArgumentException("User and payment type cannot be null.");
        }
        if (paymentType.equalsIgnoreCase("CASH")) {
            if (user.getPhysicalWallet() >= price) {
                user.setPhysicalWallet(user.getPhysicalWallet() - price);
                return true;
            }
        } else if (paymentType.equalsIgnoreCase("CARD")) {
            if (user.getDigitalWallet() >= price) {
                user.setDigitalWallet(user.getDigitalWallet() - price);
                return true;
            }
        }
        return false;
    }

    public void displayItems(boolean showSpecial) {
        System.out.println("\n--- VENDING MACHINE MENU ---");
        for (Item item : inventory.values()) {
            if (!item.isSpecial() || showSpecial) {
                String status = item.getQuantity() > 0 ? "In Stock: " + item.getQuantity() : "OUT OF STOCK";
                String specialTag = item.isSpecial() ? " [SECRET VIP ITEM]" : "";
                System.out.printf("[%s] %s - $%.2f (%s)%s\n", item.getKey(), item.getName(), item.getPrice(), status, specialTag);
            }
        }
        System.out.println("---------------------------");
    }

    public Riddle getRandomRiddle() {
        if (riddles.isEmpty()) {
            return null;
        }
        return riddles.get(new Random().nextInt(riddles.size()));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachine machine = new VendingMachine();

        // Inventory
        //Savory
        machine.addItem(new Item("Potato Chips", 1.50, random.nextInt(6), "A1", false));
        machine.addItem(new Item("Doritos", 2.00, random.nextInt(6), "A2", false));
        machine.addItem(new Item("Pretzels", 1.00, random.nextInt(6), "A3", false));

        //Sweet
        machine.addItem(new Item("Cookies", 1.50, random.nextInt(6), "B1", false));
        machine.addItem(new Item("Gummies", 2.00, random.nextInt(6), "B2", false));
        machine.addItem(new Item("Chocolate Bar", 1.00, random.nextInt(6), "B3", false));

        // Drinks
        machine.addItem(new Item("Energy Drink", 2.50, random.nextInt(6), "C1", false));
        machine.addItem(new Item("Water", 1.50, random.nextInt(6), "C2", false));
        machine.addItem(new Item("Soda", 1.50, random.nextInt(6), "C3", false));

        //Special Menu with secret items, no price
        machine.addItem(new Item("Riddler's Gummies", 0.00, 1, "S1", true));
        machine.addItem(new Item("Puzzling Pop Rocks", 0.00, 1, "S2", true));
        machine.addItem(new Item("Enigma Energy Drink", 0.00, 1, "S3", true));
        machine.addItem(new Item("Brain Candy", 0.00, 1, "S4", true));
        machine.addItem(new Item("K?t-K?t", 0.00, 1, "S5", true));
        machine.addItem(new Item("Riddle Cookie", 0.00, 1, "S6", true));
        machine.addItem(new Item("Gotham Granola", 0.00, 1, "S7", true));
        machine.addItem(new Item("Question Mark Gum", 0.00, 1, "S8", true));
        machine.addItem(new Item("Nygma's Nachos", 0.00, 1, "S9", true));

        // Riddles
        machine.addRiddle(new Riddle("I speak without a mouth and hear without ears. What am I?", "Echo"));
        machine.addRiddle(new Riddle("What has to be broken before you can use it?", "Egg"));

        User user = new User("Student", 5.00, 10.00);
        boolean secretUnlocked = false;

        System.out.println("Welcome to The Riddler Machine, " + user.getName() + "!");

        while (true) {
            System.out.printf("\nYour Wallet -> Cash: $%.2f | Card: $%.2f\n", user.getPhysicalWallet(), user.getDigitalWallet());
            machine.displayItems(secretUnlocked);
            System.out.print("Enter item code (or 'LEAVE' to exit): ");
            String input = scanner.nextLine();

            if (input.replaceAll("\\s+", "").equalsIgnoreCase("LEAVE")) {
                System.out.println("Thank you for using The Riddler Machine. Goodbye!");
                break;
            }

            String sanitizedKey = input.replaceAll("\\s+", "").toUpperCase();

            Item itemToPurchase = null;
            try {
                if (!machine.inventory.containsKey(sanitizedKey)) {
                    throw new IllegalArgumentException("Invalid item key.");
                }
                Item checkItem = machine.inventory.get(sanitizedKey);
                if (checkItem.isSpecial() && !secretUnlocked) {
                    throw new IllegalArgumentException("Invalid item key.");
                }
                if (checkItem.getQuantity() <= 0) {
                    throw new IllegalStateException("Item out of stock.");
                }
                itemToPurchase = checkItem;
            } catch (IllegalArgumentException e) {
                System.out.println("--> ERROR: Invalid selection code. Please try again.");
                continue;
            } catch (IllegalStateException e) {
                System.out.println("--> ERROR: That item is currently out of stock.");
                continue;
            }

            // Riddle Chance (50% probability)
            if (!secretUnlocked && Math.random() < 0.5) {
                Riddle riddle = machine.getRandomRiddle();
                if (riddle != null) {
                    System.out.println("\n*** RIDDLE CHALLENGE TRIGGERED! ***");
                    System.out.println("Question: " + riddle.getQuestion());
                    System.out.print("Your Answer: ");
                    String answerInput = scanner.nextLine();
                    if (riddle.checkAnswer(answerInput)) {
                        System.out.println("--> CORRECT! Secret VIP catalog unlocked!");
                        secretUnlocked = true;
                    } else {
                        System.out.println("--> Incorrect. Continuing transaction...");
                    }
                }
            }

            boolean paymentSuccess = false;
            while (!paymentSuccess) {
                System.out.print("Select payment method (CASH / CARD / CANCEL): ");
                String payMethod = scanner.nextLine().trim();

                if (payMethod.equalsIgnoreCase("CANCEL")) {
                    System.out.println("Transaction cancelled.");
                    break;
                }

                if (!payMethod.equalsIgnoreCase("CASH") && !payMethod.equalsIgnoreCase("CARD")) {
                    System.out.println("--> ERROR: Unknown payment type. Type CASH or CARD.");
                    continue;
                }

                if (machine.processPayment(user, itemToPurchase.getPrice(), payMethod)) {
                    machine.removeItem(itemToPurchase.getKey());
                    user.addPurchasedItem(itemToPurchase);
                    System.out.println("--> SUCCESS: Dispensing " + itemToPurchase.getName() + "!");
                    paymentSuccess = true;
                } else {
                    System.out.println("--> ERROR: Insufficient funds in selected payment method. Try again.");
                }
            }
        }
        scanner.close();
    }
}