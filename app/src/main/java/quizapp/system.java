package quizapp;

import java.util.ArrayList;

public class system {

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

    /**
     *
     * @param subject
     */
    protected static void createFlashDeck(String subject){
        course.newSubject(subject);
    }



    /**
     * ADD a new card to a deck of specified subject
     * @param subjectName
     * @param card
     */
    protected static void addToDeck(String subjectName, card card){
        course.addCard(subjectName, card);
    }


    /**
     * getCourseList
     * @return
     */
    protected static ArrayList getCourseList(){
        return course.subjects;
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

}
