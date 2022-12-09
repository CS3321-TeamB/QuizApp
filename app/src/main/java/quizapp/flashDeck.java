package quizapp;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
    private String subject;
    ArrayList<card> cardStack;
    private int deckIterator = 0;
    private static final int STACKSIZE = 100;


    /**
     * Constructor for creating a new flash card deck
     * @param course The course name associated with the
     *               deck
     */
    protected flashDeck(String course){
        subject = course;
        cardStack = new ArrayList<card>(STACKSIZE);
    }

    protected void setIterator(int increment){
        deckIterator += increment;
    }

    /**
     * getters for flash deck attributes
     */

    protected int getTotalCards(){return totalCards;}
    protected String getSubject(){return this.subject;}

    protected card getCard(int index){
        return cardStack.get(index);
    }

    /**
     * Method to add a flash card to the deck
     * newCard a flash card with a front and
     *             back value
     */
    protected void addCardToDeck(card newCard){
        cardStack.add(newCard);
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

    /**
     * Draw card. returns the card at the index supplied
     * This does not remove the card from the deck, it only
     * returns the card so that it can be displayed to the user
     * @param index the index for getting a card from the deck
     * @return a card
     */
    protected card drawCard(int index){
        return cardStack.get(index);
    }


    /**
     * Save method for saving the flash card deck
     * @param jsonFile
     * @param course_obj
     * @throws IOException
     */

    public static void saveCardStack(String jsonFile, course course_obj) throws IOException{
        Gson gson = new Gson();
        String jsonString = gson.toJson(course_obj);
        File saveFolder = new File(System.getProperty("user.dir") + "/saves");
        if (!saveFolder.exists()) {
            saveFolder.mkdirs();
        }

        try {
            String savePath = saveFolder + "/" + jsonFile;
            File cStackFile = new File(savePath);

            FileWriter wr = new FileWriter(cStackFile);
            wr.write(jsonString);
            wr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loading method
     * @param jsonFile
     * @return
     */
    public static course loadCardStack(String jsonFile) {
        try {
            File saveFolder = new File(System.getProperty("user.dir") + "/saves");
            String savePath = saveFolder + "/" + jsonFile;
            //create Gson instance
            Gson gson = new Gson();
            //create a reader
            Reader rd = Files.newBufferedReader(Paths.get(String.valueOf(savePath)));
            //set type for cardStack
            Type cardStackType = new TypeToken<course>(){}.getType();
            //convert JSON string to cardStack object
            course cardStack_obj = gson.fromJson(rd, cardStackType);
            //close reader
            rd.close();

            return cardStack_obj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}





