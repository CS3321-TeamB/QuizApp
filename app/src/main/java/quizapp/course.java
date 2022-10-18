package quizapp;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Course class will be used to hold each courses subjects
 */
public class course {
    protected String courseName;
    protected ArrayList<cardStack> subjects;


    /**
     * Constructor for course, creates a new course with
     * an empty list of subjects
     * @param name
     */
    protected course(String name){
        this.courseName = name;
        subjects = new ArrayList<>();
    }

    /**
     * Creates a new subject stack to be added to
     * the courses list of subjects
     * @param name subject name
     * @return
     */
    protected void newSubject(String name){
        cardStack subject = new cardStack(name);
        subjects.add(subject);
    }

    /**
     * Method for getting the index of the subject for
     * addCard/deleteCard methods
     * @param subjectName
     * @return index of subject if it exists, else returns -1
     */
    protected int getIndex(String subjectName){
        for(int i=0; i < subjects.size(); i++){
            if(subjects.get(i).getSubject().equals(subjectName)){
                return i;
            }
        }
        return -1;
    }

    /**
     * method for adding a card to a subject
     * @param subject subject name of stack
     * @param card the card being added to the stack
     */
    protected void addCard(String subject, card card){
        subjects.get(getIndex(subject)).addCard(card);
    }

    /**
     * method for removing a card from a subject
     * @param subject subject name of stack
     * @param card the card being removed from stack
     */
    protected void deleteCard(String subject, card card){
        subjects.get(getIndex(subject)).removeCard(card);
    }

}
