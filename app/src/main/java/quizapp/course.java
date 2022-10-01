package quizapp;

public class course {
    protected String courseName;


    public course(String courseName){
        cardStack studyStack = new cardStack(courseName);

        cardStack passStack = new cardStack(courseName);
    }

    //TODO these empty methods and more
    protected boolean addCard(card card){
        return true;
    }
    protected card removeCard(card card){
        return card;
    }
}
