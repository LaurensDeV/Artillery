package be.kdg.artillery.view.start;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Robby Dirix & Laurens de Voogd
 * 23-Feb-16
 */
public class StartView extends BorderPane {
    private Button btnStart;
    private Button btnVerder;
    private Label lblPlayer1;
    private Label lblPlayer2;
    private TextField txtPlayer1;
    private TextField txtPlayer2;
    private ComboBox<String> cmb1;
    private ComboBox<String> cmb2;
    final private Insets inset = new Insets(10);


    public StartView() {
        initialiseNodes();
        layoutNodes();
    }

    public Button getBtnVerder() {
        return btnVerder;
    }

    public Button getBtnStart() {
        return btnStart;
    }

    public TextField getTxtPlayer1() {
        return txtPlayer1;
    }

    public TextField getTxtPlayer2() {
        return txtPlayer2;
    }

    public ComboBox<String> getCmb1() {
        return cmb1;
    }

    public ComboBox<String> getCmb2() {
        return cmb2;
    }

    private void initialiseNodes() {
        btnStart = new Button("Start nieuw spel");
        btnStart.setPrefSize(150, 100);
        btnVerder = new Button("Laad spel");
        btnVerder.setPrefSize(150, 100);
        ObservableList<String> colors =
                FXCollections.observableArrayList("Blauw", "Geel", "Groen", "Oranje", "Paars", "Rood", "Zwart");
        cmb1 = new ComboBox<>(colors);
        cmb2 = new ComboBox<>(colors);
        cmb1.getSelectionModel().select(1);
        cmb2.getSelectionModel().select(2);

        lblPlayer1 = new Label("Naam speler 1");
        lblPlayer2 = new Label("Naam speler 2");
        txtPlayer1 = new TextField();
        txtPlayer2 = new TextField();
    }

    private void layoutNodes() {

        GridPane playerSelecter = new GridPane();
        playerSelecter.add(lblPlayer1, 0, 0);
        playerSelecter.add(txtPlayer1, 1, 0);
        playerSelecter.add(cmb1, 2, 0);
        playerSelecter.add(lblPlayer2, 0, 1);
        playerSelecter.add(txtPlayer2, 1, 1);
        playerSelecter.add(cmb2, 2, 1);
        playerSelecter.setHgap(10);
        playerSelecter.setVgap(10);

        setPrefSize(500, 250);
        setCenter(playerSelecter);
        HBox hboxBut = new HBox();
        hboxBut.getChildren().addAll(btnStart, btnVerder);
        setBottom(hboxBut);
        playerSelecter.setAlignment(Pos.CENTER);
        hboxBut.setAlignment(Pos.CENTER);
        hboxBut.setPadding(new Insets(10));
        hboxBut.setSpacing(10);
        setPadding(inset);
    }
}
