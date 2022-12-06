package quizapp;

import java.util.ArrayList;

public class system {

    protected static card createCard(String front, String back){
        card newCard = new card(front, back);
        return newCard;
    }

    protected static void addToDeck(String subjectName, card card){

        course.addCard(subjectName, card);

    }

    protected static ArrayList getCourseList(){

        return course.subjects;

    }

    protected static card drawCard(String subject, int index){
//        course.subjects.get
    }

}
