package vending;

import student.TestCase;

public class VendingMachineTest extends student.TestCase {
    private VendingMachine machine;
    private User user;
    private Item item1;
    private Item specialItem;

    public void setUp() {
        machine = new VendingMachine();
        user = new User("Student", 2.00, 5.00);
        item1 = new Item("Chips", 1.50, 2, "A1", false);
        specialItem = new Item("Secret Candy", 0.00, 1, "S1", true);

        machine.addItem(item1);
        machine.addItem(specialItem);
    }

    public void testAddItemNull() {
        Exception thrown = null;
        try {
            machine.addItem(null);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testAddRiddleNull() {
        Exception thrown = null;
        try {
            machine.addRiddle(null);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testRemoveItemSuccess() {
        Item removed = machine.removeItem(" a1 ");
        assertEquals("Chips", removed.getName());
        assertEquals(1, item1.getQuantity());
    }

    public void testRemoveItemNullKey() {
        Exception thrown = null;
        try {
            machine.removeItem(null);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testRemoveItemInvalidKey() {
        Exception thrown = null;
        try {
            machine.removeItem("Z9");
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testRemoveItemOutOfStock() {
        machine.removeItem("A1");
        machine.removeItem("A1");

        Exception thrown = null;
        try {
            machine.removeItem("A1");
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalStateException);
    }

    public void testProcessPaymentCash() {
        assertTrue(machine.processPayment(user, 1.50, "CASH"));
        assertEquals(0.50, user.getPhysicalWallet(), 0.001);

        assertFalse(machine.processPayment(user, 1.00, "CASH"));
    }

    public void testProcessPaymentCard() {
        assertTrue(machine.processPayment(user, 2.00, "CARD"));
        assertEquals(3.00, user.getDigitalWallet(), 0.001);

        assertFalse(machine.processPayment(user, 10.00, "CARD"));
    }

    public void testProcessPaymentInvalidType() {
        assertFalse(machine.processPayment(user, 1.00, "BITCOIN"));
    }

    public void testProcessPaymentNullArgs() {
        Exception thrown = null;
        try {
            machine.processPayment(null, 1.00, "CASH");
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            machine.processPayment(user, 1.00, null);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testGetRandomRiddle() {
        assertNull(machine.getRandomRiddle());

        Riddle riddle = new Riddle("Question?", "Answer");
        machine.addRiddle(riddle);
        assertEquals(riddle, machine.getRandomRiddle());
    }

    public void testDisplayItems() {
        machine.displayItems(false);
        String output = systemOut().getHistory();
        assertTrue(output.contains("Chips"));
        assertFalse(output.contains("Secret Candy"));

        systemOut().clearHistory();

        machine.displayItems(true);
        output = systemOut().getHistory();
        assertTrue(output.contains("Chips"));
        assertTrue(output.contains("Secret Candy"));
    }
}