package be.kdg.artillery.view.highscore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

/**
 * Robby Dirix & Laurens de Voogd
 * 25-Feb-16
 */
public class HighscoreView extends BorderPane {

    private Button btnClose;
    private ScrollPane scrollPane;
    private Label lblHighscore;

    public HighscoreView() {
        initialiseNodes();
        layoutNodes();
    }

    public Label getLblHighscore() {
        return lblHighscore;
    }

    public Button getBtnClose() {
        return btnClose;
    }

    private void initialiseNodes() {
        btnClose = new Button("Close");
        btnClose.setPrefSize(150, 100);
        lblHighscore = new Label();
        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(this.getPrefWidth(), this.getPrefHeight());
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        setPrefSize(300, 500);
    }

    private void layoutNodes() {
        setMargin(btnClose, new Insets(20));
        setPadding(new Insets(15));
        setCenter(scrollPane);
        setBottom(btnClose);
        setAlignment(btnClose, Pos.CENTER);
        scrollPane.setContent(lblHighscore);
    }
}
