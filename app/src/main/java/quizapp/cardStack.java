package quizapp;
import java.util.ArrayList;
import java.util.Collections;

/**
 * CardStack class will be a container for cards.
 * There can be multiple stacks of flash cards in a single
 * course. Example: A stack of flash cards the user is studying
 * and a stack they have passed, but may wish to go through again at
 * another time.
 */

public class cardStack{
    private int totalCards = 0;
    private boolean passStack = false;
    private String course;
    private ArrayList<card> cardStack;

    /*
    Default MAX size set to 100 for each stack, because
    who needs more than 100 cards for a single class amirite.
    This can be adjusted later should we decide to change it
     */
    private static final int STACKSIZE = 100;

    protected int getTotalCards(){
        return cardStack.size();
    }
    protected String getCourse(){
        return course;
    }
    protected boolean getIsPass(){
        return passStack;
    }

    /**
     * Default constructor, creates an empty stack of cards
     * @param course this is the name of the course for
     * which the user wishes to study for, thus naming the stack
     * Each stack will default initialize with 0 cards and not
     * be a passed card stack.
     */
    protected cardStack(String course){
        this.course = course;
        cardStack = new ArrayList<card>(STACKSIZE);
    }

    /**
     * constrcutor to allow system to create a passed stack of the
     * same course name.
     * @param course course name
     * @param passStack set to true
     */
    protected cardStack(String course, boolean passStack){
        this.course = course;
        passStack = true;
        this.passStack = passStack;
        cardStack = new ArrayList<card>(STACKSIZE);
    }

    /**
     * Method to add a flash card to the deck
     * @param card a flash card with a front and
     *             back value
     */
    protected void addCard(card card){
        cardStack.add(card);
    }

    /**
     * Method to remove a card from a stack
     * @param card card chosen to remove
     * @return returns the card out of the stack to be added
     * to either a pass or non-pass stack or to be deleted.
     */
    protected card removeCard(card card){
        int index = cardStack.indexOf(card);
        return cardStack.remove(index);
    }

    /**
     * Shuffle method in case the user wishes to shuffle
     * the stack of cards to change the order in which
     * they are studying them
     */
    protected void shuffle(){
        Collections.shuffle(cardStack);
    }
}
