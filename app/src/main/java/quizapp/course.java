package quizapp;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Course class will be used to hold each courses subjects
 */

//TODO draw card methods and methods for keeping place in deck (forward backward movement)
//initial implementation probably will not use a priority setting for cards, can be developed
//later as a more advanced feature.


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
     *
     * @param subject
     * @return
     */
    protected flashDeck getDeck(String subject){
        return questions;
    }

    protected String getCourseName(){
        return courseName;
    }

    protected static int getDeckIterator(){
        return deckIterator;
    }

    protected int getDeckSize(){
        return questions.getTotalCards();
    }

    /**
     * method for drawing a card
     * @param indexOfCard
     * @return
     */
    protected static card drawCard(int indexOfCard){
       return flashDeck.drawCard(indexOfCard);
    }


//    /**
//     * Method for adding a new subject for a given course
//     * Requires a name and adds a new stack of cards for this
//     * course
//     * @param name String name of subject
//     */
//    protected static void newSubject(String name){
//        flashDeck subject = new flashDeck(name);
//        subjects.add(subject);
//    }


//    /**
//     * Method for getting the index of the chosen subject in
//     * the list of subjects for a given course
//     * @param subject String of subject name of desired subject
//     * @return
//     */
//    protected static int getIndex(String subject){
//        for(int i = 0; i < questions.getTotalCards(); i++){
//            if(questions.get.getSubject().equals(subject)){
//                return 1;
//            }
//        }
//        return -1; //subject not found
//    }

    /**
     * Method ot add a card to a subject for the course
     * @param card card object for adding to the list
     */
    protected void addCard(String front, String back){

        questions.addCardToDeck(front, back);
    }

    /**
     * Method to remove a card from a subject for the course
     * @param subject string of subject name
     * @param card card object for removing the list
     */
    protected void removeCard(String subject, card card){
        questions.removeCard(card);
    }
}
