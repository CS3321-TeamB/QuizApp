
package quizapp;

/**
 * Card class holds to methods to create a new flash card
 * including the front and back, along with a couple booleans
 * to track successful studying and difficulty selected for the
 * card.
 */

public class card {
    private String front;
    private String back;

    /**
     * getter for front of card
     * @return the string on the front of the card
     */
    protected String getFront() {return front;
    }

    /**
     * getter for back of card
     * @return the string on the back of the card
     */
    protected String getBack() {
        return this.back;
    }

    /**
     * Editing the front of the flashcard in case the user
     * wishes change the front of the card
     * @param newFront
     */
    protected void setFront(String newFront){
        front = newFront;
    }

    /**
     * similar to setFront, method used to edit the back of the
     * card
     * @param newBack
     */
    protected void setBack(String newBack){
        back = newBack;
    }

    /**
     * Constructor for creating a flash card
     * @param front This will be the study material on the front of the card
     * @param back This will be the study material on the back of the card
     * Each card should be instantiated as a false pass and easy mode card
     * by default.
     */
    protected card(String front, String back) {
        this.front = front;
        this.back = back;
    }
}