package edu.sdccd.cisc190;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CongratulationsScreenTest {

    @Test
    public void testCreateCongratsScreen() {
        // Create an instance of the CongratulationsScreen class
        CongratulationsScreen congratsScreen = new CongratulationsScreen();

        // Call the createCongratsScreen method
        Pane pane = congratsScreen.createCongratsScreen();

        // Check that the returned Pane is not null
        assertNotNull(pane, "The Pane should not be null.");

        // Check that the Pane contains children
        assertFalse(pane.getChildren().isEmpty(), "The Pane should contain children.");

        // Check the first child is a Text node
        assertTrue(pane.getChildren().get(0) instanceof Text, "The first child should be a Text node.");

        // Check the properties of the Text node
        Text text = (Text) pane.getChildren().get(0);
        assertEquals("Congratulations! You finished the game!", text.getText(), "The Text content is incorrect.");
        assertEquals("Arial", text.getFont().getName(), "The font name is incorrect.");
        assertEquals(30, text.getFont().getSize(), "The font size is incorrect.");
        assertEquals("0x00008bff", text.getFill().toString(), "The text color is incorrect."); // Color.DARKBLUE
    }
}
