package vending;

public class Riddle {
    private String question;
    private String answer;

    public Riddle(String question, String answer) {
        if (question == null || question.isBlank() || answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("Question and answer cannot be null or blank.");
        }
        this.question = question;
        this.answer = answer.trim();
    }

    public String getQuestion() {
        return question;
    }

    public boolean checkAnswer(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Answer input cannot be null.");
        }
        return this.answer.equalsIgnoreCase(input.trim());
    }
}