package quizapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class system {
    private final ArrayList<course> AllCourses;
    static int deckIterator = 0;

    private static system INSTANCE = null;


    /**
     * Singleton instance of system since there should
     * only ever be 1 instance
     * @return
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
        getInstance().AllCourses.get(index).addCard(front, back);
    }


    /**
     * getCourseList
     * @return courselist
     */
    protected static ArrayList getCourseList(){
        return getInstance().AllCourses;
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
     * Method to draw cards
     * @param subject course name to draw from
     * @param increment increments up or down depending on
     *                  whether the user wants to go forward or
     *                  backward for studying
     * @return  returns card from flashDeck
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

    /**
     * gets index of course
     * @param courseName1 the name of the course we want to index
     * @return index of courseName1 in AllCourses Array
     */
    protected static int getIndex(String courseName1) {
        int index = -1;
        for (int i = 0; i < getInstance().AllCourses.size(); i++) {
            if (getInstance().AllCourses.get(i).courseName != courseName1) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Method to get the course wanted by its string name
     * @param courseName1 course name
     * @return returns the course object
     */
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
     * Method to determine if there are cards in the course's flashDeck
     * @param courseName the course name
     * @return boolean
     */
    protected static boolean isEmpty(String courseName) {
//        getCourse(courseName).getDeckSize() ==0

//        for(int i = 0; i < getInstance().AllCourses.size(); i++) {
//            if (getInstance().AllCourses.get(i).courseName != courseName) {
//                i++;
//                j = i;
//            }
//        }
//        return getInstance().AllCourses.get(j).getDeck(courseName).getTotalCards() == 0;
        return getCourse(courseName).getDeckSize() == 0;
    }

    /**
     * method for saving instance
     * @throws IOException
     */
    protected static void saveState() throws IOException {
        for(int i = 0; i < getInstance().AllCourses.size(); i++) {
            flashDeck.saveCardStack(system.getInstance().AllCourses.get(i).courseName, system.getInstance().AllCourses.get(i).questions);
        }
    }


//    protected static void saveState() throws IOException {
//        File saveFolder = new File("./saves");
//        if (!saveFolder.exists()) {
//            saveFolder.mkdirs();
//        }
//
//        Writer writer = new FileWriter("./saves", false);
//        Gson gson = new GsonBuilder()
//                .setPrettyPrinting()
//                .create();
//        try{
//            gson.toJson(getInstance(), writer); //Not appending to keep file fresh on new save
//        }catch(Exception IOE){
////            LOGGER.warn("Unable to write game objects to file to save.");
//        }
//        writer.flush();
//        writer.close();
////        LOGGER.info("Game was saved");
//    }

//    public static void saveState() throws IOException{
//
//        Gson gson = new Gson();
//
//        String jsonString = gson.toJson(getInstance());
//
//        File saveFolder = new File("./saves");
//        if (!saveFolder.exists()) {
//            saveFolder.mkdirs();
//        }
//
//        try {
//            for(course course : getInstance().AllCourses) {
//                String jsonFile = course.courseName;
//                String savePath = saveFolder + "/" + jsonFile;
//                System.out.println(savePath);
//                File cStackFile = new File(savePath);
//
//                FileWriter wr = new FileWriter(cStackFile);
//                wr.write(jsonString);
//                wr.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//
    /**
     * loads the course instances
     * @throws IOException
     */
    protected static void loadState() throws IOException {
        File saveFolder = new File("./saves");
        Gson gson = new Gson();
        if (saveFolder.exists()) {
            String[] names = saveFolder.list();
            for (String file : names) {
                flashDeck deck = flashDeck.loadCardStack(file);
                createCourse(file);
            }
        }
    }
}
