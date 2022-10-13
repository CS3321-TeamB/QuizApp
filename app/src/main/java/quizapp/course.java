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
        subjects = new ArrayList<cardStack>();
    }

    protected boolean newSubject(String name){

        cardStack subject = new cardStack(name);
        subjects.add(subject);
        return true;
    }

}
