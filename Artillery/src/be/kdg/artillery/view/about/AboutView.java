package be.kdg.artillery.view.about;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


/**
 * Robby Dirix & Laurens de Voogd
 * 23-Feb-16
 */
public class AboutView extends BorderPane {

    private Label lblAbout;

    private Button btnCloseA;

    public Button getBtnCloseA() {
        return btnCloseA;
    }

    public Label getLblAbout() {
        return lblAbout;
    }

    public AboutView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        lblAbout = new Label("");
        btnCloseA = new Button("Close");
    }

    private void layoutNodes() {
        setCenter(lblAbout);
        setBottom(btnCloseA);
        setAlignment(btnCloseA, Pos.CENTER);
        setMargin(btnCloseA, new Insets(15));
        setPrefSize(500, 250);
    }
}
