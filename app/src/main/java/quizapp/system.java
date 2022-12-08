package quizapp;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;

public class system {
    private final ArrayList<course> AllCourses;
    static int deckIterator = 0;

    private static system INSTANCE = null;

    protected static system getInstance(){
        if (INSTANCE == null){
            INSTANCE = new system();
        }
        return INSTANCE;
    }
    protected system(){
        this.AllCourses = new ArrayList<>();
    }

    /**
     * create card to be added.
     * @param front
     * @param back
     * @return
     */
    protected static card createCard(String front, String back){
        card newCard = new card(front, back);
        return newCard;
    }

//    /**
//     *
//     * @param course
//     */
//    protected static void createFlashDeck(String course){
//        course.(course);
//    }

    protected static course createCourse(String courseName){
        course newCourse = new course(courseName);
        getInstance().AllCourses.add(newCourse);
        return newCourse;
    }

    /**
     * ADD a new card to a deck of specified subject
     * @param subjectName
     * @param
     */
    protected static void addToDeck(String subjectName, String front, String back){

        int index = 0;
        index = getInstance().AllCourses.indexOf(system.getCourse(subjectName));
        getInstance().AllCourses.get(index).addCard(front, back);
    }


    /**
     * getCourseList
     * @return
     */
    protected static ArrayList getCourseList(){
        return getInstance().AllCourses;
    }

    /**
     * Method for deleting a card.
     * @param subject
     * @param card
     */
    protected static void deleteCard(String subject, card card){
        int index = 0;

        index = getInstance().AllCourses.indexOf(system.getCourse(subject));
        getInstance().AllCourses.get(index).removeCard(subject, card);
    }

//    protected static int getDeckIterator(){
//
//    }


    /**
     *
     * @param subject
     * @param increment
     * @return
     */
    protected static card drawCard(String subject, int increment){
        int j = 0;
        for(int i = 0; i < getInstance().AllCourses.size(); i++){
            if(getInstance().AllCourses.get(i).courseName != subject){
                i++;
                j = i;
            }
        }
        getInstance().AllCourses.get(j).questions.getIterator();
        int indexOfCurrentCard = getInstance().AllCourses.get(j).questions.getIterator();
        return flashDeck.drawCard(indexOfCurrentCard + increment);
    }


    protected static int getIndex(String courseName1) {
        int index = -1;
        for (int i = 0; i < getInstance().AllCourses.size(); i++) {
            if (getInstance().AllCourses.get(i).courseName != courseName1) {
                index = i;
            }
        }
        return index;
    }
    protected static course getCourse(String courseName1){
        int j = 0;
        for(int i = 0; i < getInstance().AllCourses.size(); i++){
            if(getInstance().AllCourses.get(i).courseName != courseName1){
                i++;
                j = i;
            }
        }
        return getInstance().AllCourses.get(j);
    }
    

    /**
     *
     * @param courseName
     * @return boolean
     */
    protected static boolean isEmpty(String courseName) {
        int j = 0;

        for(int i = 0; i < getInstance().AllCourses.size(); i++) {
            if (getInstance().AllCourses.get(i).courseName != courseName) {
                i++;
                j = i;
            }

        }
        return getInstance().AllCourses.get(j).getDeck(courseName).getTotalCards() == 0;
    }


    protected static void saveState() throws IOException {
        for(int i = 0; i < getInstance().AllCourses.size(); i++) {
            flashDeck.saveCardStack(system.getInstance().AllCourses.get(i).courseName, system.getInstance().AllCourses.get(i).questions);
        }
    }

    protected static void loadState() throws IOException {
        File saveFolder = new File("./saves");
        if (saveFolder.exists()) {
            String[] names = saveFolder.list();
            for (String file : names) {
                flashDeck deck = flashDeck.loadCardStack(file);
                createCourse(file);
            }
        }
    }
}
