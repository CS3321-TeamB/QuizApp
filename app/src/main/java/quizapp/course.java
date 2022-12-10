package quizapp;

/**
 * Course class will be used to hold each courses subjects
 */

public class course {
    protected  String courseName;
    protected flashDeck questions;
    static int deckIterator = 0;

    /**
     * Constructor for course, creates a new course with
     * an empty list of subjects
     * @param name
     */
    protected course(String name){
        courseName = name;
        questions = new flashDeck(name);
    }

    /**
     *Getters
     * @return
     */
    protected String getCourseName(){return courseName;}

    protected static int getDeckIterator(){return deckIterator;}

    protected int getDeckSize(){return questions.getTotalCards();}

    /**
     * Method ot add a card to a subject for the course
     * card card object for adding to the list
     */
    protected void addCard(card newCard){
        questions.addCardToDeck(newCard);
    }

    /**
     * Method to remove a card from a subject for the course
     * @param card card object for removing the list
     */
    protected void removeCard(card card){
        questions.removeCard(card);
    }
}
