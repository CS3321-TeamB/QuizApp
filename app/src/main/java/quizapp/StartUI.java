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

import java.util.ArrayList;


public class StartUI extends Application {
    private int mainWidth = 500;
    private int mainHeight = 600;
    private int topHeight = 130;
    private int centerHeight = 100;
    private int centerWidth = 100;
    private int bottomHeight = 50;
    private Scene scene;
    private BorderPane root;
    private BorderPane mainMenu;
    private BorderPane startMenu;
    private BorderPane addMenu;
    private BorderPane studyScreen;
    private BorderPane addCourse;
    private Stage stage;
    private BorderStroke stroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
    private Border border = new Border(stroke);
    private String topFrontColor = "#CCCCCC;";
    private String topBackColor = "#5D5D5D;";
    private String centerColor = "#B5B5B5;";
    private String bottomFrontColor = "#CCCCCC;";
    private String bottomBackColor = "#5D5D5D;";
    private String subjectList[] = {"Computer Science", "Mathematics", "Physics"};
    private ArrayList<course> listOfCourses = new ArrayList<>();


    public StartUI() {
        root = new BorderPane();
        mainMenu = new BorderPane();
        startMenu = new BorderPane();
        addMenu = new BorderPane();
        studyScreen = new BorderPane();
        addCourse = new BorderPane();
    }
    private void updateScene(Node node) {
        root.getChildren().clear();
        root.setCenter(node);
        stage.setHeight(topHeight + centerHeight + bottomHeight);
        stage.setWidth(centerWidth);
    }
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("QuizApp");

        buildMainMenu();
        updateScene(mainMenu);

        buildStartMenu();

        buildAddQuestionsMenu();

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

    }

    private void buildMainMenu() {
        mainMenu.setTop(addTitleBox());
        mainMenu.setCenter(addMainMenu());
        mainMenu.setBottom(addAuthorBox());
    }

    private void buildStartMenu() {
        startMenu.setTop(clearTop());
        startMenu.setCenter(addStartMenu());
        startMenu.setBottom(clearBottom());
    }

    private void buildAddQuestionsMenu() {
        addMenu.setTop(clearTop());
        addMenu.setCenter(addAddQuestionsMenu());
        addMenu.setBottom(clearBottom());
    }

    private void buildStudyScreen() {
        studyScreen.setTop(clearTop());
        studyScreen.setCenter(addStudyScreen());
        studyScreen.setBottom(clearBottom());
    }

    private void buildAddCourse() {
        addCourse.setTop(clearTop());
        addCourse.setCenter(addCourseMenu());
        addCourse.setBottom(clearBottom());
    }

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
            Platform.exit();
        });

        buttonBox.getChildren().addAll(startButton, addCourseButton, addButton, exitButton);
        centerHBox.getChildren().addAll(buttonBox);

        return centerHBox;
    }

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
            buildStudyScreen();
            updateScene(studyScreen);
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

        Button startButton = new Button("Add Card");
        Button backButton = new Button("Back");

        startButton.setOnAction((ActionEvent addCard) -> {
            if(system.getCourseList().size() != 0) {

                if (questionText.getText() != null && answerText.getText() != null) {
                    String front = questionText.getText();
                    String back = answerText.getText();
                    course.addCard(subjectDropDown.getPromptText(), system.createCard(front, back));
                }
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
        buttonBox.getChildren().addAll(startButton, backButton);
        containerBox.getChildren().addAll(subjectBox, dataBox, buttonBox);
        centerHBox.getChildren().addAll(containerBox);

        return centerHBox;
    }

    private VBox addStudyScreen() {
        centerHeight = 200;
        centerWidth = 500;
        VBox centerVBox = new VBox();
        VBox cardBox = new VBox();
        VBox cardDataBox = new VBox();
        HBox buttonBox = new HBox();

        centerVBox.setBorder(border);
        centerVBox.setAlignment(Pos.CENTER);
        centerVBox.setStyle("-fx-background-color:" + centerColor);
        centerVBox.setSpacing(10);
        centerVBox.setPadding(new Insets(10, 10, 10, 10));

        cardBox.setPrefHeight(400);

        cardDataBox.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back to Main Menu");

        Text cardTypeText = new Text("Question");
        TextArea cardData = new TextArea("Testing Answer");

        backButton.setOnAction((ActionEvent back) -> {
            if (popupYesNoBox("Are you sure you want to go back?")) {
                buildMainMenu();
                updateScene(mainMenu);
            }
        });

        cardDataBox.getChildren().addAll(cardData);
        cardBox.getChildren().addAll(cardTypeText, cardDataBox);
        buttonBox.getChildren().addAll(backButton);
        centerVBox.getChildren().addAll(cardBox, buttonBox);
        return centerVBox;
    }

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

        Button addCourseButton = new Button("Add Course");
        Button backButton = new Button("Back");

        backButton.setOnAction((ActionEvent back) -> {
            buildMainMenu();
            updateScene(mainMenu);
        });

        addCourseButton.setOnAction((ActionEvent addCourse) -> {
            if(addCourseTextField.getText() != null){

                listOfCourses.add(system.createCourse(addCourseTextField.getText()));
            }
            // Code for adding the new course goes here
        });

        textBox.getChildren().addAll(addCourseText);
        textFieldBox.getChildren().addAll(addCourseTextField, currentCoursesText, currentCoursesArea);
        buttonBox.getChildren().addAll(addCourseButton, backButton);
        centerVBox.getChildren().addAll(textBox, textFieldBox, buttonBox);
        return centerVBox;
    }

    private HBox clearTop() {
        topHeight = 100;
        HBox emptyBox = new HBox();

        emptyBox.setStyle("-fx-background-color:" + topBackColor);
        emptyBox.setBorder(border);
        emptyBox.setMinHeight(50);

        return emptyBox;
    }

    private HBox clearBottom() {
        bottomHeight = 100;
        HBox emptyBox = new HBox();

        emptyBox.setStyle("-fx-background-color:" + bottomBackColor);
        emptyBox.setBorder(border);
        emptyBox.setMinHeight(50);

        return emptyBox;
    }


    private boolean popupYesNoBox(String msg) {
        Alert messageBox = new Alert(Alert.AlertType.NONE);
        ButtonType yesButton = ButtonType.YES;
        ButtonType noButton = ButtonType.NO;
        messageBox.getDialogPane().getButtonTypes().addAll(yesButton, noButton);
        messageBox.getDialogPane().setContentText(msg);
        messageBox.getDialogPane().setMaxWidth(100);
        messageBox.showAndWait();

        if (messageBox.getResult() == ButtonType.YES) {
            return true;
        }
        return false;
    }
}
