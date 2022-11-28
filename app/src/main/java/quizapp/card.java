
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
    private boolean pass = false;
    private boolean starred = false;   //in case we want to have users be able to increase priority of card
    private int priority = 0;          //TODO 0 priority being default maybe 5 is the lowest priority or something like that


    //TODO finish setters and getters

    protected void setStarred(boolean bool){
        this.starred = bool;
    }

    protected void setPriority(int priority){
        this.priority = priority;
    }

    protected int getPriority(){
        return priority;
    }

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
     * Editing the front of the flashcard in case the user
     * wishes change the front of the card
     * @param newFront
     */
    protected void setFront(String newFront){
        this.front = newFront;
    }

    /**
     * similar to setFront, method used to edit the back of the
     * card
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