package edu.sdccd.cisc190;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class CongratulationsScreen {

    public Pane createCongratsScreen() {
        Pane congratsPane = new Pane();
        Text congratsText = new Text(300, 200, "Congratulations, you finished the game!");
        congratsText.setStyle("-fx-font-size: 30px;");
        congratsPane.getChildren().add(congratsText);
        return congratsPane;
    }
}