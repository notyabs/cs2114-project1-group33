package vending;

import student.TestCase;

public class UserTest extends student.TestCase {
    private User user;

    public void setUp() {
        user = new User("Alex", 10.00, 20.00);
    }

    public void testValidUserCreation() {
        assertEquals("Alex", user.getName());
        assertEquals(10.00, user.getPhysicalWallet(), 0.001);
        assertEquals(20.00, user.getDigitalWallet(), 0.001);
        assertTrue(user.getItemsPurchased().isEmpty());
    }

    public void testInvalidConstructorArgs() {
        Exception thrown = null;
        try {
            new User(null, 5.0, 5.0);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            new User("   ", 5.0, 5.0);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            new User("Alex", -1.0, 5.0);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            new User("Alex", 5.0, -1.0);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testSetName() {
        user.setName("Jordan");
        assertEquals("Jordan", user.getName());

        Exception thrown = null;
        try {
            user.setName("  ");
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testSetPhysicalWallet() {
        user.setPhysicalWallet(15.50);
        assertEquals(15.50, user.getPhysicalWallet(), 0.001);

        Exception thrown = null;
        try {
            user.setPhysicalWallet(-0.01);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testSetDigitalWallet() {
        user.setDigitalWallet(0.00);
        assertEquals(0.00, user.getDigitalWallet(), 0.001);

        Exception thrown = null;
        try {
            user.setDigitalWallet(-5.00);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testAddPurchasedItem() {
        Item item = new Item("Water", 1.00, 1, "C2", false);
        user.addPurchasedItem(item);
        assertEquals(1, user.getItemsPurchased().size());
        assertEquals("Water", user.getItemsPurchased().get(0).getName());

        user.addPurchasedItem(null);
        assertEquals(1, user.getItemsPurchased().size());
    }
}