package quizapp;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

public class system {
    private final ArrayList<course> AllCourses;
    static int deckIterator = 0;
    private static system INSTANCE = null;


    /**
     * Singleton instance of system since there should
     * only ever be 1 instance
     * @return system
     */
    protected static system getInstance(){
        if (INSTANCE == null){
            INSTANCE = new system();
        }
        return INSTANCE;
    }

    /**
     * constructor
     */
    protected system(){
        this.AllCourses = new ArrayList<>();
    }


    /**
     * create card to be added.
     * @param front string for front of the card
     * @param back string for back of the card
     * @return returns a card
     */
    protected static card createCard(String front, String back){
        card newCard = new card(front, back);
        return newCard;
    }


    /**
     * Method for creating a new course
     * @param courseName name of the course
     * @return returns a new course
     */

    protected static course createCourse(String courseName){
        course newCourse = new course(courseName);
        getInstance().AllCourses.add(newCourse);
        return newCourse;
    }

    /**
     * ADD a new card to a deck of specified subject
     * @param subjectName name of the course
     * @param front the front of card to be added
     * @param back the back of card to be added
     */
    protected static void addToDeck(String subjectName, String front, String back){
        int index = getInstance().AllCourses.indexOf(system.getCourse(subjectName));
        System.out.println("Index: " + index);
        System.out.println("Subject we want: " + subjectName + " Subject we got: " + getInstance().AllCourses.get(index).courseName);
        getInstance().AllCourses.get(index).addCard(front, back);
    }


    /**
     * MEthod for deleting card from the deck
     * @param subject  course name
     * @param card card to be removed

     */
    protected static void deleteCard(String subject, card card){
        int index = getInstance().AllCourses.indexOf(system.getCourse(subject));
        getInstance().AllCourses.get(index).removeCard(subject, card);
    }


    /**
     * Method to get the course wanted by its string name
     * @param courseName1 course name
     * @return returns the course object
     */
    protected static course getCourse(String courseName1){
        int i = 0;
        while (!getInstance().AllCourses.get(i).getCourseName().equals(courseName1)){
            i++;
        }
        return getInstance().AllCourses.get(i);
    }


    /**
     * getCourseList
     * @return courselist
     */
    protected static ArrayList getCourseList(){
        return getInstance().AllCourses;
    }


    /**
     * Method to determine if there are cards in the course's flashDeck
     * @param courseName the course name
     * @return boolean
     */
    protected static boolean isEmpty(String courseName) {
        return getCourse(courseName).getDeckSize() == 0;
    }

    /**
     * method for saving instance
     * @throws IOException
     */
    protected static void saveState() throws IOException {
        for(int i = 0; i < getInstance().AllCourses.size(); i++) {
            flashDeck.saveCardStack(system.getInstance().AllCourses.get(i).getCourseName(), system.getInstance().AllCourses.get(i));
        }
    }

    /**
     * loads the course instances
     * @throws IOException
     */
    protected static void loadState() throws IOException {
        File saveFolder = new File("./saves");
        if (saveFolder.exists()) {
            if (!(saveFolder.list().length == 0)) {
                String[] names = saveFolder.list();
                for (String file : names) {
                    getInstance().AllCourses.add(flashDeck.loadCardStack(file));
                }
            }
        }
    }
}
