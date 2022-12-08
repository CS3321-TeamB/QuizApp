package quizapp;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.File;

public class system {
    private static ArrayList<course> AllCourses = new ArrayList<>();
    static int deckIterator = 0;


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
        AllCourses.add(newCourse);
        return newCourse;
    }

    /**
     * ADD a new card to a deck of specified subject
     * @param subjectName
     * @param card
     */
    protected static void addToDeck(String subjectName, card card){
        system.getCourse(subjectName);
        course.questions.addCardToDeck(card);
    }


    /**
     * getCourseList
     * @return
     */
    protected static ArrayList getCourseList(){
        return AllCourses;
    }

    /**
     *
     * @param subject
     * @param index
     */
    protected static void deleteCard(String subject, int index){
        course.getDeck(subject).removeCard(course.getDeck(subject).getCard(index));
    }


    /**
     *
     * @param subject
     * @param increment
     * @return
     */
    protected static card drawCard(String subject, int increment){
        int indexOfCurrentCard = course.getDeck(subject).getIterator();
        return course.getDeck(subject).drawCard(indexOfCurrentCard + increment);
    }


    protected static int getIndex(String courseName1) {
        int index = -1;
        for (int i = 0; i < AllCourses.size(); i++) {
            if (AllCourses.get(i).courseName.equals(courseName1)) {
                index = i;
            }
        }
        return index;
    }
    protected static course getCourse(String courseName1){
        int i = 0;
        while ((!AllCourses.get(i).courseName.equals(courseName1))){
            i++;
        }
        return AllCourses.get(i);
    }

    /**
     *
     * @param courseName
     * @return boolean
     */
    protected static boolean isEmpty(String courseName) {
        if (course.getDeck(courseName).getTotalCards() == 0) {
            return true;
        }
        else {
            return false;
        }
    }


    protected static void saveState() throws IOException {
        for (course course: AllCourses) {
            flashDeck.saveCardStack(course.courseName, course.questions);
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
