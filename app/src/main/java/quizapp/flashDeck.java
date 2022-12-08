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

public class flashDeck{
    private int totalCards = 0;
    private boolean passStack = false;
    private String subject;
    static ArrayList<card> cardStack;
    private int deckIterator = 0;

    /*
    Default MAX size set to 100 for each stack, because
    who needs more than 100 cards for a single class amirite.
    This can be adjusted later should we decide to change it
     */
    private static final int STACKSIZE = 100;

    //TODO documentation
    protected void setIterator(int increment){
        deckIterator += increment;
    }

    protected int getTotalCards(){
        return totalCards;
    }
    protected String getSubject(){
        return subject;
    }
    protected boolean getIsPass(){
        return passStack;
    }
    protected int getIterator(){return deckIterator;}
    /**
     * Default constructor, creates an empty stack of cards
     *  this is the name of the course for
     * which the user wishes to study for, thus naming the stack
     * Each stack will default initialize with 0 cards and not
     * be a passed card stack.
     */
    protected flashDeck(){
        cardStack = new ArrayList<card>(STACKSIZE);
    }

    protected flashDeck(String course){
        subject = course;
        cardStack = new ArrayList<card>(STACKSIZE);
    }

    protected static card getCard(int index){
        return cardStack.get(index);
    }
    /*may not need this method
     */

    /**
     * constrcutor to allow system to create a passed stack of the
     * same course name. May not be needed in future iteration
     * @param course course name
     * @param passStack set to true
     */
    protected flashDeck(String course, boolean passStack){
        subject = course;
        passStack = true;
        this.passStack = passStack;
        cardStack = new ArrayList<>(STACKSIZE);
    }

    /**
     * Method to add a flash card to the deck
     * newCard a flash card with a front and
     *             back value
     */
    protected void addCardToDeck(String front, String back){
        card newCard = new card(front, back);

        cardStack.add(newCard);
        totalCards +=1;
        for(card test : cardStack){
           System.out.println(test.getFront());
       }
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


    /**
     * Draw card. returns the card at the index supplied
     * This does not remove the card from the deck, it only
     * returns the card so that it can be displayed to the user
     * @param index the index for getting a card from the deck
     * @return a card
     */
    protected static card drawCard(int index){
        return cardStack.get(index);
    }

    /**
     * Shuffle method in case the user wishes to shuffle
     * the stack of cards to change the order in which
     * they are studying them
     */
    protected void shuffle(){
        Collections.shuffle(cardStack);
    }



    public static void saveCardStack(String jsonFile, flashDeck cardStack_obj) throws IOException{

        Gson gson = new Gson();

        String jsonString = gson.toJson(cardStack_obj);

        File saveFolder = new File("./saves");
        if (!saveFolder.exists()) {
            saveFolder.mkdirs();
        }

        try {
            String savePath = saveFolder + "/" + jsonFile;
            System.out.println(savePath);
            File cStackFile = new File(savePath);

            FileWriter wr = new FileWriter(cStackFile);
            wr.write(jsonString);
            wr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static flashDeck loadCardStack(String jsonFile) {
        try {
            File saveFolder = new File("./saves");
            String savePath = saveFolder + "/" + jsonFile;
            //create Gson instance
            Gson gson = new Gson();
            //create a reader
            Reader rd = Files.newBufferedReader(Paths.get(String.valueOf(savePath)));
            //set type for cardStack
            Type cardStackType = new TypeToken<flashDeck>(){}.getType();
            //convert JSON string to cardStack object
            flashDeck cardStack_obj = gson.fromJson(rd, cardStackType);
            //close reader
            rd.close();

            return cardStack_obj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}





