package be.kdg.artillery.view.info;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Robby Dirix
 * 10-Mar-16
 */
public class InfoView extends BorderPane {
    public InfoView() {
        initialiseNodes();
        layoutNodes();
    }

    ImageView splash;

    private void initialiseNodes() {
        splash = new ImageView(new Image("file:Artillery/src/be/kdg/artillery/view/images/start.png"));
        splash.setFitWidth(1024);
        splash.setFitHeight(768);


    }

    private void layoutNodes() {
        setPrefSize(1024, 768);
        setCenter(splash);
    }
}
