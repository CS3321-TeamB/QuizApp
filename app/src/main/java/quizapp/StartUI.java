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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Screen;

public class StartUI extends Application {
    private int mainWidth = 500;
    private int mainHeight = 600;
    private int menuNumber;
    private Scene scene;
    private BorderPane root;
    private BorderPane menu1;
    private BorderPane menu2;
    private BorderPane menu3;
    private Stage stage;

    public StartUI() {
        menuNumber = 1;
        root = new BorderPane();
        menu1 = new BorderPane();
        menu2 = new BorderPane();
        menu3 = new BorderPane();
    }
    private void updateScene(Node node) {
        root.getChildren().clear();
        root.setCenter(node);
    }
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("QuizApp");

        buildMenu1();
        updateScene(menu1);

        buildMenu2();

        buildMenu3();

        scene = new Scene(root);
        //This is just to make it easier to close the app.
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
        stage.setAlwaysOnTop(true);
        stage.setAlwaysOnTop(false);

    }

    private void buildMenu1() {
        menu1.setTop(addMenu1Top());
        menu1.setCenter(addMenu1Center());
        menu1.setBottom(addMenu1Bottom());
    }

    private void buildMenu2() {
        menu2.setTop(addMenu1Top());
        menu2.setCenter(addMenu2Center());
        menu2.setBottom(addMenu1Bottom());
    }

    private void buildMenu3() {
        menu2.setTop(addMenu1Top());
        menu2.setCenter(addMenu3Center());
        menu2.setBottom(addMenu1Bottom());
    }

    private HBox addMenu1Top() {
        BorderStroke stroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
        Border border = new Border(stroke);
        String topFrontColor = "#CCCCCC;";
        String topBackColor = "#5D5D5D;";

        HBox topHBox = new HBox();
        VBox titleBox = new VBox();
        VBox descBox = new VBox();

        topHBox.setAlignment(Pos.BASELINE_CENTER);
        topHBox.setStyle("-fx-background-color:" + topBackColor);
        titleBox.setPrefSize(mainWidth - (mainWidth * 0.2), mainHeight - (mainHeight * 0.8));
        titleBox.setBorder(border);
        titleBox.setAlignment(Pos.BASELINE_CENTER);
        titleBox.setPrefHeight(130);
        titleBox.setMaxWidth(300);
        titleBox.setStyle("-fx-background-color: " + topFrontColor);
        descBox.setAlignment(Pos.BASELINE_CENTER);
        descBox.setSpacing(5);
        descBox.setPadding(new Insets(20, 0, 0, 0));

        Text appName = new Text("QuizApp");
        appName.setFont(Font.font("Tahoma", 64));
        appName.setFill(new Color(0.3, 0.5, 1, 1));

        Text description = new Text("A card based studying solution");
        description.setFont(Font.font("Tahoma", 16));

        descBox.getChildren().addAll(description);
        titleBox.getChildren().addAll(appName, descBox);
        topHBox.getChildren().addAll(titleBox);

        return topHBox;
    }

    private HBox addMenu1Center() {
        BorderStroke stroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
        Border border = new Border(stroke);
        String centerColor = "#B5B5B5;";

        HBox centerHBox = new HBox();
        VBox buttonBox = new VBox();

        centerHBox.setPrefSize(mainWidth - (mainWidth * 0.2), mainHeight - (mainHeight * 0.8));
        centerHBox.setBorder(border);
        centerHBox.setAlignment(Pos.BASELINE_CENTER);
        centerHBox.setStyle("-fx-background-color:" + centerColor);
        //centerHBox.setPrefHeight(150);
        centerHBox.setSpacing(30);

        buttonBox.setAlignment(Pos.BASELINE_CENTER);
        buttonBox.setPadding(new Insets(40, 0, 40, 0));
        buttonBox.setSpacing(5);


        Button startButton = new Button("Start New Study Session");
        Button loadButton = new Button("Load Saved Session");
        Button exitButton = new Button("Exit");

        startButton.setOnAction((ActionEvent startSession) -> {
            updateScene(menu2);
        });

        loadButton.setOnAction((ActionEvent loadSession) -> {
            updateScene(menu3);
        });

        exitButton.setOnAction((ActionEvent exit) -> {
            Platform.exit();
        });

        buttonBox.getChildren().addAll(startButton, loadButton, exitButton);
        centerHBox.getChildren().addAll(buttonBox);

        return centerHBox;
    }

    private HBox addMenu1Bottom() {
        BorderStroke stroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
        Border border = new Border(stroke);
        String bottomFrontColor = "#CCCCCC;";
        String bottomBackColor = "#5D5D5D;";

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

    private HBox addMenu2Center() {

        HBox box = new HBox();

        return box;
    }

    private HBox addMenu3Center() {

        HBox box = new HBox();

        return box;
    }

    private void popupError(String msg) {
        Dialog<ButtonType> box = new Dialog<>();
        box.getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
        box.setTitle("Error");
        box.setContentText(msg);
        box.show();
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        VBox root = new VBox(20);  // I set the spacing to 20 here just to make it cleaner looking.
//        Scene scene = new Scene(root, 500, 600);
//        BorderStroke stroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2));
//        Border border = new Border(stroke);
//
//        VBox topBox = new VBox(20);
//        topBox.setBorder(border);
//        topBox.setAlignment(Pos.BASELINE_CENTER);
//        topBox.setPrefHeight(100);
//
//        HBox titleBox = new HBox();
//
//        titleBox.setAlignment(Pos.BASELINE_CENTER);
//        titleBox.setStyle("-fx-font: normal bold 24px 'sansserif' ");
//        Text title = new Text("QuizApp");
//        titleBox.getChildren().addAll(title);
//
//        HBox descBox = new HBox();
//        descBox.setStyle("-fx-font: normal bold 12px 'sansserif' ");
//        descBox.setAlignment(Pos.BASELINE_CENTER);
//        Text description = new Text("A card based studying solution");
//        descBox.getChildren().addAll(description);
//
//        topBox.getChildren().addAll(titleBox, descBox);
//
//        VBox centerBox = new VBox();
//        Text subject = new Text("Pick a subject: ");
//        centerBox.setAlignment(Pos.BASELINE_CENTER);
//        centerBox.setSpacing(10);
//        String subjectList[] = {"Computer Science", "Mathematics", "Physics"};
//
//        ComboBox subjectDropDown = new ComboBox(FXCollections.observableArrayList(subjectList));
//        Button loadSession = new Button("Load Study Session");
//        Button newSession = new Button("New Study Session");
//        Button exitButton = new Button("Exit");
//        centerBox.getChildren().addAll(subject, subjectDropDown, loadSession, newSession, exitButton);
//
//        VBox lowerBox = new VBox();
//        lowerBox.setAlignment(Pos.BASELINE_CENTER);
//
//        root.getChildren().addAll(topBox, centerBox, lowerBox);
//
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("QuizApp");
//        primaryStage.show();
//    }
}