package quizapp;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.io.Reader;
import java.lang.reflect.Type;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * CardStack class will be a container for cards.
 * There can be multiple stacks of flash cards in a single
 * course. Example: A stack of flash cards the user is studying
 * and a stack they have passed, but may wish to go through again at
 * another time.
 */

//TODO add a drawcard method
public class cardStack{
    private int totalCards = 0;
    private boolean passStack = false;
    private String subject;
    private ArrayList<card> cardStack;

    /*
    Default MAX size set to 100 for each stack, because
    who needs more than 100 cards for a single class amirite.
    This can be adjusted later should we decide to change it
     */
    private static final int STACKSIZE = 100;

    //TODO documentation

    protected int getTotalCards(){
        return totalCards;
    }
    protected String getSubject(){
        return subject;
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
        this.subject = course;
        cardStack = new ArrayList<card>(STACKSIZE);
    }

    /**
     * constrcutor to allow system to create a passed stack of the
     * same course name. May not be needed in future iteration
     * @param course course name
     * @param passStack set to true
     */
    protected cardStack(String course, boolean passStack){
        this.subject = course;
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
        totalCards +=1;
    }

    /**
     * Method to remove a card from a stack
     * @param card card chosen to remove
     * @return returns the card out of the stack to be added
     * to either a pass or non-pass stack or to be deleted.
     */
    protected card removeCard(card card){
        int index = cardStack.indexOf(card);
        totalCards -= 1;
        return cardStack.remove(index);

    }

    //TODO finish this
    protected card drawCard(int index){
        return null;
    }

    /**
     * Shuffle method in case the user wishes to shuffle
     * the stack of cards to change the order in which
     * they are studying them
     */
    protected void shuffle(){
        Collections.shuffle(cardStack);
    }

    public static void saveCardStack(String jsonFile, cardStack cardStack_obj) throws IOException{

        Gson gson = new Gson();

        String jsonString = gson.toJson(cardStack_obj);

        try {
            File cStackFile = new File(jsonFile);

            FileWriter wr = new FileWriter(cStackFile);
            wr.write(jsonString);
            wr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static cardStack loadCardStack(String jsonFile) {
        try {
            //create Gson instance
            Gson gson = new Gson();
            //create a reader
            Reader rd = Files.newBufferedReader(Paths.get(jsonFile));
            //set type for cardStack
            Type cardStackType = new TypeToken<cardStack>(){}.getType();
            //convert JSON string to cardStack object
            cardStack cardStack_obj = gson.fromJson(rd, cardStackType);
            //close reader
            rd.close();

            return cardStack_obj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

