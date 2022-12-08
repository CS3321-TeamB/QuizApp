package quizapp;

import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * StartUI Class
 */
public class StartUI extends Application {
    private final int mainWidth = 500;
    private final int mainHeight = 600;
    private int topHeight = 130;
    private int centerHeight = 100;
    private int centerWidth = 100;
    private int bottomHeight = 50;
    private Scene scene;
    private final BorderPane root;
    private final BorderPane mainMenu;
    private final BorderPane startMenu;
    private final BorderPane addMenu;
    private final BorderPane studyScreen;
    private final BorderPane addCourse;
    private Stage stage;
    private final BorderStroke stroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
    private final Border border = new Border(stroke);
    private final String topFrontColor = "#CCCCCC;";
    private final String topBackColor = "#5D5D5D;";
    private final String centerColor = "#B5B5B5;";
    private final String bottomFrontColor = "#CCCCCC;";
    private final String bottomBackColor = "#5D5D5D;";
    private ArrayList<course> courseList = new ArrayList<>();
    private final List<String> subjectList = new ArrayList<>();
    private String currentCourse;
    private int deckIterator = 0;
    private int totalCards = 0;
    private boolean answerButtonClicked = false;
    private card currentCard;
    private course addCourseTo;

    /**
     * StartUI Constructor
     */
    public StartUI() {
        root = new BorderPane();
        mainMenu = new BorderPane();
        startMenu = new BorderPane();
        addMenu = new BorderPane();
        studyScreen = new BorderPane();
        addCourse = new BorderPane();
    }

    /**
     * Method for updating the scene.
     * @param node
     */
    private void updateScene(Node node) {
        root.getChildren().clear();
        root.setCenter(node);
        stage.setHeight(topHeight + centerHeight + bottomHeight);
        stage.setWidth(centerWidth);
    }

    /**
     * Method to start the main window.
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("QuizApp");

        buildMainMenu();
        updateScene(mainMenu);

        buildStartMenu();

        buildAddQuestionsMenu();
        System.out.println(deckIterator);
        buildStudyScreen();

        buildAddCourse();

        scene = new Scene(root);
        //This is just to make it easier to close the app with the escape key.
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE)
                Platform.exit();
        });

        stage.setScene(scene);
        stage.setX(((Screen.getPrimary().getVisualBounds().getWidth() /2) - (mainWidth /2)));
        stage.setY(50);
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        stage.show();
        stage.setAlwaysOnTop(true); //This is a hacky way to get the window to pop up.
        stage.setAlwaysOnTop(false);

        system.loadState();
        courseList = system.getCourseList();
        courseList.forEach((course) -> subjectList.add(course.getCourseName()));
    }

    /**
     * Method for building the "main menu".
     */
    private void buildMainMenu() {
        mainMenu.setTop(addTitleBox());
        mainMenu.setCenter(addMainMenu());
        mainMenu.setBottom(addAuthorBox());
    }

    /**
     * Method for building the "start" screen.
     */
    private void buildStartMenu() {
        startMenu.setTop(clearTop());
        startMenu.setCenter(addStartMenu());
        startMenu.setBottom(clearBottom());
    }

    /**
     * Method for building the "add questions" screen.
     */
    private void buildAddQuestionsMenu() {
        addMenu.setTop(clearTop());
        addMenu.setCenter(addAddQuestionsMenu());
        addMenu.setBottom(clearBottom());
    }

    /**
     * Method for building the "study" screen.
     */
    private void buildStudyScreen() {
        studyScreen.setTop(clearTop());
        studyScreen.setCenter(addStudyScreen());
        studyScreen.setBottom(clearBottom());
    }

    /**
     * Method for building the "add course" screen.
     */
    private void buildAddCourse() {
        addCourse.setTop(clearTop());
        addCourse.setCenter(addCourseMenu());
        addCourse.setBottom(clearBottom());
    }

    /**
     * Method for adding the "title" box.
     * @return HBox
     */
    private HBox addTitleBox() {
        topHeight = 130;

        HBox topHBox = new HBox();
        VBox titleBox = new VBox();
        VBox descBox = new VBox();

        topHBox.setAlignment(Pos.CENTER);
        topHBox.setStyle("-fx-background-color:" + topBackColor);
        titleBox.setBorder(border);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle("-fx-background-color: " + topFrontColor);
        titleBox.setPadding(new Insets(10, 10, 10, 10));
        descBox.setAlignment(Pos.CENTER);
        descBox.setPadding(new Insets(0, 0, 5, 0));

        Text appName = new Text("QuizApp");
        appName.setFont(Font.font("Trebuchet MS", 64));
        appName.setFill(new Color(0.3, 0.5, 1, 1));

        Text description = new Text("A card based studying solution");
        description.setFont(Font.font("Tahoma", 16));

        descBox.getChildren().addAll(description);
        titleBox.getChildren().addAll(appName, descBox);
        topHBox.getChildren().addAll(titleBox);

        return topHBox;
    }

    /**
     * Method for adding the "main menu".
     * @return HBox
     */
    private HBox addMainMenu() {
        centerHeight = 250;
        centerWidth = 400;

        HBox centerHBox = new HBox();
        VBox buttonBox = new VBox();

        centerHBox.setBorder(border);
        centerHBox.setAlignment(Pos.CENTER);
        centerHBox.setStyle("-fx-background-color:" + centerColor);
        centerHBox.setSpacing(10);

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 10, 0));
        buttonBox.setSpacing(10);

        Button startButton = new Button("Start Studying");
        Button addButton = new Button("Add Questions");
        Button addCourseButton = new Button("Add Course");
        Button exitButton = new Button("Exit");

        startButton.setOnAction((ActionEvent startSession) -> {
            buildStartMenu();
            updateScene(startMenu);
        });

        addCourseButton.setOnAction((ActionEvent addCourseMenu) -> {
            buildAddCourse();
            updateScene(addCourse);
        });

        addButton.setOnAction((ActionEvent addQuestions) -> {
            buildAddQuestionsMenu();
            updateScene(addMenu);
        });

        exitButton.setOnAction((ActionEvent exit) -> {
            try {
                system.saveState();
                System.out.println("Testing Save");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Platform.exit();
        });

        buttonBox.getChildren().addAll(startButton, addCourseButton, addButton, exitButton);
        centerHBox.getChildren().addAll(buttonBox);

        return centerHBox;
    }

    /**
     * Method for adding the "author" box.
     * @return HBox
     */
    private HBox addAuthorBox() {
        bottomHeight = 50;

        HBox bottomHBox = new HBox();
        VBox authorBox = new VBox();

        bottomHBox.setPadding(new Insets(15, 5, 15, 5)); // Top, Right, Bottom, Left
        bottomHBox.setSpacing(10);
        bottomHBox.setBorder(border);
        bottomHBox.setStyle("-fx-background-color: " + bottomBackColor);
        bottomHBox.setAlignment(Pos.CENTER);
        bottomHBox.setPrefHeight(50);
        authorBox.setAlignment(Pos.BASELINE_CENTER);
        authorBox.setBorder(border);
        authorBox.setStyle("-fx-background-color:" + bottomFrontColor);
        authorBox.setPadding(new Insets(10, 10, 10, 10));

        Text authors = new Text("By: Chris Blaser, Scott Brown, and Natalia Castaneda");
        authors.setFont(Font.font("Tahoma", 10));

        authorBox.getChildren().addAll(authors);
        bottomHBox.getChildren().addAll(authorBox);

        return bottomHBox;
    }

    /**
     * Method for adding the "start" menu.
     * @return HBox
     */
    private HBox addStartMenu() {
        centerHeight = 200;
        centerWidth = 400;

        HBox centerHBox = new HBox();
        VBox containerBox = new VBox();
        VBox textBox = new VBox();
        HBox buttonBox = new HBox();

        centerHBox.setBorder(border);
        centerHBox.setAlignment(Pos.BASELINE_CENTER);
        centerHBox.setStyle("-fx-background-color:" + centerColor);
        centerHBox.setSpacing(30);

        containerBox.setAlignment(Pos.BASELINE_CENTER);
        containerBox.setSpacing(10);

        textBox.setAlignment(Pos.BASELINE_CENTER);

        Text subjectText = new Text("Please select a course:");


        ComboBox subjectDropDown = new ComboBox(FXCollections.observableArrayList(subjectList));
        subjectDropDown.setPromptText("--Course--");

        buttonBox.setAlignment(Pos.BASELINE_CENTER);
        buttonBox.setPadding(new Insets(40, 0, 40, 0));
        buttonBox.setSpacing(10);

        Button startButton = new Button("Start");
        Button backButton = new Button("Back");

        startButton.setOnAction((ActionEvent start) -> {
            currentCourse = (String) subjectDropDown.getValue();
            if (system.isEmpty((String) subjectDropDown.getValue())) {
                popupOkBox("There are no cards for that subject!");
            }
            else {
                buildStudyScreen();
                updateScene(studyScreen);
            }
        });

        backButton.setOnAction((ActionEvent back) -> {
            buildMainMenu();
            updateScene(mainMenu);
        });

        textBox.getChildren().addAll(subjectText, subjectDropDown);
        buttonBox.getChildren().addAll(startButton, backButton);
        containerBox.getChildren().addAll(textBox, buttonBox);
        centerHBox.getChildren().addAll(containerBox);

        return centerHBox;
    }

    /**
     * Method for adding the "add Questions" menu.
     * @return HBox
     */
    private HBox addAddQuestionsMenu() {
        centerHeight = 250;
        centerWidth = 500;

        HBox centerHBox = new HBox();
        VBox containerBox = new VBox();
        HBox subjectBox = new HBox();
        HBox dataBox = new HBox();
        VBox questionBox = new VBox();
        VBox answerBox = new VBox();
        HBox buttonBox = new HBox();

        centerHBox.setBorder(border);
        centerHBox.setAlignment(Pos.CENTER);
        centerHBox.setStyle("-fx-background-color:" + centerColor);
        centerHBox.setSpacing(10);
        centerHBox.setPadding(new Insets(10, 0, 0, 0));

        containerBox.setAlignment(Pos.CENTER);
        containerBox.setSpacing(10);

        subjectBox.setAlignment(Pos.CENTER);

        Text subjectText = new Text("Add card to what course:");
        ComboBox subjectDropDown = new ComboBox(FXCollections.observableArrayList(subjectList));
        subjectDropDown.setPromptText("--Course--");

        dataBox.setSpacing(15);
        dataBox.setPadding(new Insets(10, 10, 0, 10));

        TextArea questionField = new TextArea();
        Text questionText = new Text("Question:");
        TextArea answerField = new TextArea();
        Text answerText = new Text("Answer:");

        questionText.setFont(Font.font("Tahoma", 16));
        answerText.setFont(Font.font("Tahoma", 16));
        questionField.setPrefHeight(200);
        answerField.setPrefHeight(200);
        questionField.setBorder(border);
        answerField.setBorder(border);

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 10, 0));
        buttonBox.setSpacing(10);

        Button addCardButton = new Button("Add Card");
        Button backButton = new Button("Back");

        addCardButton.setOnAction((ActionEvent addCard) -> {
            if (!questionField.getText().equals("") && !answerField.getText().equals("")) {
                String front = questionField.getText();
                String back = answerField.getText();

                system.addToDeck((String) subjectDropDown.getValue(), front, back);
                questionField.clear();
                answerField.clear();
                questionField.setPromptText("Card added to the " + subjectDropDown.getValue() + " deck.");

            }
            else {
                questionField.setPromptText("Please add a question.");
                answerField.setPromptText("Please add an answer.");
                subjectDropDown.requestFocus();

            }
        });

        backButton.setOnAction((ActionEvent back) -> {
            buildMainMenu();
            updateScene(mainMenu);
        });

        subjectBox.getChildren().addAll(subjectText, subjectDropDown);
        questionBox.getChildren().addAll(questionText, questionField);
        answerBox.getChildren().addAll(answerText, answerField);
        dataBox.getChildren().addAll(questionBox, answerBox);
        buttonBox.getChildren().addAll(addCardButton, backButton);
        containerBox.getChildren().addAll(subjectBox, dataBox, buttonBox);
        centerHBox.getChildren().addAll(containerBox);

        return centerHBox;
    }

    /**
     * Method for adding the "Study" screen.
     * @return VBox
     */
    private VBox addStudyScreen() {
        centerHeight = 200;
        centerWidth = 500;
        deckIterator = system.deckIterator;
        totalCards = 0;
        VBox centerVBox = new VBox();
        VBox cardBox = new VBox();
        HBox cardTextBox = new HBox();
        Region fillerBox = new Region();
        VBox cardDataBox = new VBox();
        VBox buttonBox = new VBox();
        HBox upperButtonBox = new HBox();
        HBox lowerButtonBox = new HBox();

        centerVBox.setBorder(border);
        centerVBox.setAlignment(Pos.CENTER);
        centerVBox.setStyle("-fx-background-color:" + centerColor);
        centerVBox.setSpacing(10);
        centerVBox.setPadding(new Insets(10, 10, 10, 10));

        cardBox.setPrefHeight(400);
        fillerBox.setMinWidth(10);
        cardDataBox.setAlignment(Pos.CENTER);

        buttonBox.setAlignment(Pos.CENTER);
        upperButtonBox.setAlignment(Pos.CENTER);
        lowerButtonBox.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back to Main Menu");
        Button removeCardButton = new Button("Remove Card");
        Button showAnswerButton = new Button("Show Answer");
        Button nextCardButton = new Button("Next Question");
        Button prevCardButton = new Button("Previous Question");

        Text cardTypeText = new Text("Question");
        Text cardAmountText = new Text();
        TextArea cardData = new TextArea();
        cardData.setEditable(false);

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(5);

        if(subjectList.size() !=0) {

           currentCard = system.getCourse(currentCourse).questions.drawCard(deckIterator);
           totalCards = system.getCourse(currentCourse).getDeckSize();
           cardData.setText(currentCard.getFront());
           cardAmountText.setText((deckIterator + 1) + "/" + totalCards);
        }else
            cardData.setText("No deck chosen");

        backButton.setOnAction((ActionEvent back) -> {
            if (popupYesNoBox("Are you sure you want to go back?")) {
                buildMainMenu();
                updateScene(mainMenu);
            }
        });

        removeCardButton.setOnAction((ActionEvent removeCard) -> {
            if (popupYesNoBox("Are you sure you want to remove the card?")) {
                system.deleteCard(currentCourse, currentCard);
                System.out.println("Iterator: " + deckIterator + " Total Cards: " + totalCards);
                totalCards = system.getCourse(currentCourse).getDeckSize();
                if (deckIterator == totalCards) {
                    deckIterator--;
                }
                if (totalCards == 0) {
                    popupOkBox("There are no cards left for this subject. \n Returning to the main menu");
                    buildMainMenu();
                    updateScene(mainMenu);
                }
                else {
                    currentCard = system.getCourse(currentCourse).questions.drawCard(deckIterator);
                    cardData.setText(currentCard.getFront());
                    cardAmountText.setText((deckIterator + 1) + "/" + totalCards);
                }
            }
        });

        showAnswerButton.setOnAction((ActionEvent showAnswer) -> {
            if (!answerButtonClicked) {
                cardData.setText(currentCard.getBack());
                answerButtonClicked = true;
                cardTypeText.setText("Answer");
                showAnswerButton.setText("Show Question");
            }
            else if (answerButtonClicked) {
                cardData.setText(currentCard.getFront());
                answerButtonClicked = false;
                cardTypeText.setText("Question");
                showAnswerButton.setText("Show Answer");
            }
        });

        nextCardButton.setOnAction((ActionEvent nextCard) -> {
            if (deckIterator + 1 >= totalCards) {
                deckIterator = 0;
            }
            else {
                deckIterator++;
            }
            currentCard = system.getCourse(currentCourse).questions.drawCard(deckIterator);
            totalCards = system.getCourse(currentCourse).getDeckSize();
            cardData.setText(currentCard.getFront());
            cardAmountText.setText((deckIterator + 1) + "/" + totalCards);
            if (answerButtonClicked) {
                cardTypeText.setText("Question");
                showAnswerButton.setText("Show Answer");
                answerButtonClicked = false;
            }
        });

        prevCardButton.setOnAction((ActionEvent prevCard) -> {
            if (deckIterator <= 0) {
                deckIterator = totalCards - 1;
            }
            else {
                deckIterator--;
            }
            currentCard = system.getCourse(currentCourse).questions.drawCard(deckIterator);
            totalCards = system.getCourse(currentCourse).getDeckSize();
            cardData.setText(currentCard.getFront());
            cardAmountText.setText((deckIterator + 1) + "/" + totalCards);
            if (answerButtonClicked) {
                cardTypeText.setText("Question");
                showAnswerButton.setText("Show Answer");
                answerButtonClicked = false;
            }
        });

        cardDataBox.getChildren().addAll(cardData);
        cardTextBox.getChildren().addAll(cardTypeText, fillerBox, cardAmountText);
        cardBox.getChildren().addAll(cardTextBox, cardDataBox);
        upperButtonBox.getChildren().addAll(prevCardButton, showAnswerButton, nextCardButton);
        lowerButtonBox.getChildren().addAll(backButton, removeCardButton);
        buttonBox.getChildren().addAll(upperButtonBox, lowerButtonBox);
        centerVBox.getChildren().addAll(cardBox, buttonBox);
        return centerVBox;
    }

    /**
     * Method for adding the "addCourse" menu.
     * @return VBox
     */
    private VBox addCourseMenu() {
        centerHeight = 200;
        centerWidth = 500;
        VBox centerVBox = new VBox();
        HBox textBox = new HBox();
        VBox textFieldBox = new VBox();
        HBox buttonBox = new HBox();

        centerVBox.setBorder(border);
        centerVBox.setAlignment(Pos.CENTER);
        centerVBox.setStyle("-fx-background-color:" + centerColor);
        centerVBox.setSpacing(10);
        centerVBox.setPadding(new Insets(5, 0, 5, 0));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        textBox.setAlignment(Pos.CENTER);
        textFieldBox.setAlignment(Pos.CENTER);
        textFieldBox.setSpacing(10);
        textFieldBox.setPadding(new Insets(0, 10, 0, 10));
        textFieldBox.setMaxWidth(500);

        Text addCourseText = new Text("Enter the new course name:");
        TextField addCourseTextField = new TextField();
        Text currentCoursesText = new Text("Current Courses:");
        TextArea currentCoursesArea = new TextArea();

        addCourseTextField.setMaxWidth(200);
        currentCoursesArea.setEditable(false);

        // Populates the current courses text area.
        currentCoursesArea.setText(outputCourseNames());

        Button addCourseButton = new Button("Add Course");
        Button backButton = new Button("Back");

        backButton.setOnAction((ActionEvent back) -> {
            buildMainMenu();
            updateScene(mainMenu);
        });

        addCourseButton.setOnAction((ActionEvent addCourse) -> {
            if(!addCourseTextField.getText().equals("")) {
                addCourseTo = system.createCourse(addCourseTextField.getText());
                subjectList.add(addCourseTo.getCourseName());
                addCourseTextField.clear();
                currentCoursesArea.setText(outputCourseNames());
                addCourseTextField.requestFocus();
            }
            else {
                addCourseTextField.setPromptText("Please enter a course name.");
            }
        });

        textBox.getChildren().addAll(addCourseText);
        textFieldBox.getChildren().addAll(addCourseTextField, currentCoursesText, currentCoursesArea);
        buttonBox.getChildren().addAll(addCourseButton, backButton);
        centerVBox.getChildren().addAll(textBox, textFieldBox, buttonBox);
        return centerVBox;
    }

    /**
     * Method for returning the course names.
     * @return course names
     */
    private String outputCourseNames() {
        String currCourses = "\0";
        if (!subjectList.isEmpty()) {
            for (String courseName : subjectList) {
                if (currCourses.equals("\0")) {
                    currCourses = courseName + "\n";
                }
                else {
                    currCourses = currCourses + courseName + "\n";
                }
            }
        }
        return currCourses;
    }

    /**
     * Method to clear the top box.
     * @return
     */
    private HBox clearTop() {
        topHeight = 100;
        HBox emptyBox = new HBox();

        emptyBox.setStyle("-fx-background-color:" + topBackColor);
        emptyBox.setBorder(border);
        emptyBox.setMinHeight(50);

        return emptyBox;
    }

    /**
     * Method to clear the bottom box.
     * @return
     */
    private HBox clearBottom() {
        bottomHeight = 100;
        HBox emptyBox = new HBox();

        emptyBox.setStyle("-fx-background-color:" + bottomBackColor);
        emptyBox.setBorder(border);
        emptyBox.setMinHeight(50);

        return emptyBox;
    }

    /**
     * Method to pop up a yes/no alert box.
     * @param msg message
     * @return boolean
     */
    private boolean popupYesNoBox(String msg) {
        Alert messageBox = new Alert(Alert.AlertType.NONE);
        ButtonType yesButton = ButtonType.YES;
        ButtonType noButton = ButtonType.NO;
        messageBox.getDialogPane().getButtonTypes().addAll(yesButton, noButton);
        messageBox.getDialogPane().setContentText(msg);
        messageBox.getDialogPane().setMaxWidth(100);
        messageBox.showAndWait();

        return messageBox.getResult() == ButtonType.YES;
    }

    /**
     * Method to pop up an OK alert box.
     * @param msg message
     */
    private void popupOkBox(String msg) {
        Alert messageBox = new Alert(Alert.AlertType.NONE);
        ButtonType okButton = ButtonType.OK;
        messageBox.getDialogPane().getButtonTypes().addAll(okButton);
        messageBox.getDialogPane().setContentText(msg);
        messageBox.getDialogPane().setMaxWidth(150);
        messageBox.showAndWait();
    }
}
