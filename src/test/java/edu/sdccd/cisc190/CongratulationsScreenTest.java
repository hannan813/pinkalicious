package edu.sdccd.cisc190;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CongratulationsScreenTest extends Application {

    private CongratulationsScreen congratsScreen;

    @Override
    public void start(javafx.stage.Stage stage) {
        congratsScreen = new CongratulationsScreen();
        stage.setScene(new javafx.scene.Scene(congratsScreen.createCongratsScreen(), 400, 400));
        stage.show();
    }

    @Test
    public void testCongratsScreenText() {
        Pane congratsPane = congratsScreen.createCongratsScreen();

        // Find the Text object in the Pane and test its content
        Text congratsText = (Text) congratsPane.getChildren().get(0);
        assertEquals("Congratulations! You finished the game!", congratsText.getText());
    }

    @Test
    public void testCongratsScreenBackgroundColor() {
        Pane congratsPane = congratsScreen.createCongratsScreen();

        // Check the background color of the Pane
        assertEquals("-fx-background-color: lightblue;", congratsPane.getStyle());
    }

    @Test
    public void testCongratsTextPositioning() {
        Pane congratsPane = congratsScreen.createCongratsScreen();
        Text congratsText = (Text) congratsPane.getChildren().get(0);

        // Verify the text position
        assertEquals(200, congratsText.getX(), 0.1);
        assertEquals(200, congratsText.getY(), 0.1);
    }
}