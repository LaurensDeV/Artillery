package be.kdg.artillery.view.splashscreen;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * Robby Dirix
 * 12-Mar-16
 */
public class SplashscreenView extends BorderPane {

    public SplashscreenView() {
        initialiseNodes();
        layoutNodes();
    }

    ImageView splash;
    FadeTransition fade;

    public FadeTransition getFade() {
        return fade;
    }

    private void initialiseNodes() {
        splash = new ImageView(new Image("be/kdg/artillery/view/images/start.png"));
        fade = new FadeTransition(Duration.seconds(3), splash);
        fade.setCycleCount(1);
        fade.setFromValue(1.0);
        splash.setFitWidth(1024);
        splash.setFitHeight(768);
    }

    private void layoutNodes() {
        setPrefSize(1024, 768);
        setCenter(splash);
        fade.play();
    }
}
