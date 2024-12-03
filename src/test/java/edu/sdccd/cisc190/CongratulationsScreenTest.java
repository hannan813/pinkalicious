package edu.sdccd.cisc190;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CongratulationsScreenTest {

    private CongratulationsScreen congratulationsScreen;

    @BeforeEach
    public void setUp() {
        // Initialize the CongratulationsScreen before each test
        congratulationsScreen = new CongratulationsScreen();
    }

    @Test
    public void testCreateCongratsScreen() {
        // Call the createCongratsScreen method
        Pane congratsPane = congratulationsScreen.createCongratsScreen();

        // Verify the Pane is not null
        assertNotNull(congratsPane, "Pane should not be null");

        // Verify the Pane contains the expected number of children
        assertEquals(1, congratsPane.getChildren().size(), "Pane should contain exactly one child");

        // Verify the child is a Text node
        assertTrue(congratsPane.getChildren().get(0) instanceof Text, "Child of Pane should be a Text node");

        // Verify the text content of the Text node
        Text congratsText = (Text) congratsPane.getChildren().get(0);
        assertEquals("Congratulations, you finished the game!", congratsText.getText(), "Text content should match");

        // Verify the font size style of the Text node
        assertTrue(congratsText.getStyle().contains("-fx-font-size: 30px;"), "Text style should contain font size 30px");
    }
}
