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
    protected static String courseName;
    protected static ArrayList<flashDeck> subjects;



    /**
     *
     * @param subject
     * @return
     */
    protected static flashDeck getDeck(String subject){
        for(int i = 0; i < subjects.size(); i++){
            if (subjects.get(i).getSubject().equals(subject)){
              return subjects.get(i);
            }
        }
        return null;
    }


    /**
     * Constructor for course, creates a new course with
     * an empty list of subjects
     * @param name
     */
    protected course(String name){
        this.courseName = name;
        subjects = new ArrayList<flashDeck>();
    }

    /**
     * Method for adding a new subject for a given course
     * Requires a name and adds a new stack of cards for this
     * course
     * @param name String name of subject
     */
    protected static void newSubject(String name){
        flashDeck subject = new flashDeck(name);
        subjects.add(subject);
    }


    /**
     * Method for getting the index of the chosen subject in
     * the list of subjects for a given course
     * @param subject String of subject name of desired subject
     * @return
     */
    protected static int getIndex(String subject){
        for(int i = 0; i < subjects.size(); i++){
            if(subjects.get(i).getSubject().equals(subject)){
                return 1;
            }
        }
        return -1; //subject not found
    }

    /**
     * Method ot add a card to a subject for the course
     * @param subject string of subject name
     * @param card card object for adding to the list
     */
    protected static void addCard(String subject, card card){
        subjects.get(getIndex(subject)).addCard(card);
    }

    /**
     * Method to remove a card from a subject for the course
     * @param subject string of subject name
     * @param card card object for removing the list
     */
    protected void removeCard(String subject, card card){
        subjects.get(getIndex(subject)).removeCard(card);
    }
}
