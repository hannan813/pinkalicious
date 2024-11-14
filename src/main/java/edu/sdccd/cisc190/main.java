package edu.sdccd.cisc190;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a JFXPanel to embed Swing components in JavaFX
        JFXPanel jfxPanel = new JFXPanel();

        // Create an instance of your GamePanel (Swing component)
        GamePanel gamePanel = new GamePanel();

        // Embed the Swing component into the JFXPanel
        SwingUtilities.invokeLater(() -> {
            jfxPanel.setScene(new Scene(gamePanel));
        });

        // Set up JavaFX Scene and Stage
        StackPane root = new StackPane();
        root.getChildren().add(jfxPanel);  // Add JFXPanel (which contains GamePanel) to the layout

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("End Of Year Project");
        primaryStage.setScene(scene);
        primaryStage.show();

        gamePanel.requestFocus(); // Focus to accept key inputs
    }

    public main(String[] args) {
        launch(args);
    }
}
