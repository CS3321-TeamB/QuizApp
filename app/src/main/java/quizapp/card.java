
package quizapp;

/**
 * Card class holds to methods to create a new flash card
 * including the front and back, along with a couple booleans
 * to track successful studying and difficulty selected for the
 * card.
 */

public class card {
    private String front, back;
    private boolean isHard = false;
    private boolean pass = false;      // This may be related to priority, we might be able to factor out later
    private boolean starred = false;   //in case we want to have users be able to increase priority of card
    private int priority = 0;          //TODO 0 priority being default maybe 5 is the lowest priority or something like that


    protected int getPriority(){return priority;}

    /**
     * getter for starred
     * @return if starred, user wishes to study more
     */
    protected boolean getStarred() {return starred;}

    /**
     * getter for front of card
     * @return the string on the front of the card
     */
    protected String getFront() {
        return front;
    }

    /**
     * getter for back of card
     * @return the string on the back of the card
     */
    protected String getBack() {
        return back;
    }

    /**
     * getter for difficulty settting of card
     * @return is true or false (true for is hard mode)
     */
    protected boolean getHard() {
        return isHard;
    }

    /**
     * getter for if the card has been successfully studyied
     * @return pass as boolean, true for successfully passing
     */
    protected boolean getPass() {
        return pass;
    }

    /**
     * Set priority should be used when the user wishes to study
     * this can be used to set when cards will appear when studying
     * lower number is higher priority, so 1 will be studied more than 5
     * for example
     * @param newPriority  The new priority to set this card to
     */
    protected void setPriority(int newPriority){
        this.priority = newPriority;
    }

    /**
     * Set starred should be set to true for a card the user
     * wishes to star indicating a card with higher priority they
     * wish to study more
     * @param value true/false
     */
    protected void setStarred(boolean value){
        this.starred = value;
    }

    /**
     * In case the user wishes to change the front of the card or
     * edit it with newer/better information, this method should be
     * used to edit that info
     * @param newFront
     */
    protected void setFront(String newFront){
        this.front = newFront;
    }

    /**
     * Similar to setFront, this method is in case the user wishes to
     * edit the back of the card with the answer
     * @param newBack
     */
    protected void setBack(String newBack){
        this.back = newBack;
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