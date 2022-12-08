package quizapp

import org.testfx.framework.spock.ApplicationSpec
import static org.testfx.api.FxAssert.verifyThat
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import org.testfx.api.FxToolkit;
import javafx.stage.Stage;
import javafx.stage.Screen;
import org.testfx.matcher.base.NodeMatchers;

class StartUITestRemoveCards extends ApplicationSpec{
    public static StartUI = new StartUI()

    @Override
    void init() throws Exception{
        FxToolkit.registerStage{new Stage()}
    }

    @Override
    void start(Stage stage) throws Exception{

        StartUI.stage = stage;
        stage.setTitle("QuizApp");

        StartUI.buildMainMenu();
        StartUI.updateScene(StartUI.mainMenu);

        StartUI.buildStartMenu();

        StartUI.buildAddQuestionsMenu();

        StartUI.buildStudyScreen();

        StartUI.buildAddCourse();

        StartUI.scene = new Scene(StartUI.root);
        //This is just to make it easier to close the app with the escape key.
        StartUI.scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE)
                Platform.exit();
        });

        stage.setScene(StartUI.scene);
        stage.setX(((Screen.getPrimary().getVisualBounds().getWidth() /2) - (StartUI.mainWidth /2)));
        stage.setY(50);
        stage.setMinHeight(400);
        stage.setMinWidth(400);
        stage.show();
        stage.setAlwaysOnTop(true); //This is a hacky way to get the window to pop up.
        stage.setAlwaysOnTop(false);
    }
    @Override
    void stop() throws Exception{
        FxToolkit.hideStage()
    }

    def "test adding cards"(){
        given:
        def StartUI = new StartUI()

        when:
        clickOn("Add Course")
        write("Statistics")
        clickOn("Add Course")
        clickOn("Back")
        clickOn("Add Questions")
        clickOn("--Course--");
        clickOn("Statistics")
        clickOn("Add Card")
        clickOn("Back")
        clickOn("Start Studying")
        clickOn("--Course--")
        clickOn("Statistics")
        clickOn("Start")

        then:
        verifyThat("OK", NodeMatchers.isVisible())
    }
}
