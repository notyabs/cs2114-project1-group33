package vending;

import student.TestCase;

public class ItemTest extends student.TestCase {
    private Item validItem;

    public void setUp() {
        validItem = new Item("Chips", 1.50, 5, "a1", false);
    }

    public void testValidItemCreation() {
        assertEquals("Chips", validItem.getName());
        assertEquals(1.50, validItem.getPrice(), 0.001);
        assertEquals(5, validItem.getQuantity());
        assertEquals("A1", validItem.getKey());
        assertFalse(validItem.isSpecial());
    }

    public void testKeyTrimmingAndUppercase() {
        Item item2 = new Item("Soda", 2.00, 3, "  c1  ", true);
        assertEquals("C1", item2.getKey());
        assertTrue(item2.isSpecial());
    }

    public void testInvalidNameThrowsException() {
        Exception thrown = null;
        try {
            new Item(null, 1.0, 1, "A1", false);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            new Item("   ", 1.0, 1, "A1", false);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testNegativePriceThrowsException() {
        Exception thrown = null;
        try {
            new Item("Candy", -0.01, 1, "A1", false);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testNegativeQuantityThrowsException() {
        Exception thrown = null;
        try {
            new Item("Candy", 1.0, -1, "A1", false);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testInvalidKeyThrowsException() {
        Exception thrown = null;
        try {
            new Item("Candy", 1.0, 1, null, false);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            new Item("Candy", 1.0, 1, "  ", false);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testSetQuantity() {
        validItem.setQuantity(4);
        assertEquals(4, validItem.getQuantity());

        validItem.setQuantity(0);
        assertEquals(0, validItem.getQuantity());

        Exception thrown = null;
        try {
            validItem.setQuantity(-1);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }
}