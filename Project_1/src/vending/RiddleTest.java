package vending;

import student.TestCase;

public class RiddleTest extends student.TestCase {
    private Riddle riddle;

    public void setUp() {
        riddle = new Riddle("What has to be broken before you can use it?", "Egg");
    }

    public void testValidRiddleCreation() {
        assertEquals("What has to be broken before you can use it?", riddle.getQuestion());
    }

    public void testInvalidConstructorArgs() {
        Exception thrown = null;
        try {
            new Riddle(null, "Answer");
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            new Riddle("   ", "Answer");
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            new Riddle("Question?", null);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            new Riddle("Question?", "   ");
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    public void testCheckAnswer() {
        assertTrue(riddle.checkAnswer("Egg"));
        assertTrue(riddle.checkAnswer("egg"));
        assertTrue(riddle.checkAnswer("  EGG  "));
        assertFalse(riddle.checkAnswer("Chicken"));
    }

    public void testCheckAnswerNullInput() {
        Exception thrown = null;
        try {
            riddle.checkAnswer(null);
        } catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }
}