package be.kdg.artillery;

import be.kdg.artillery.model.Artillery;
import be.kdg.artillery.view.splashscreen.SplashscreenPresenter;
import be.kdg.artillery.view.splashscreen.SplashscreenView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Robby Dirix & Laurens de Voogd
 * 18-Feb-16
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Artillery model = new Artillery();
        SplashscreenView view = new SplashscreenView();
        new SplashscreenPresenter(model, view);
        Scene scene = new Scene(view);
        scene.getStylesheets().add("be/kdg/artillery/view/stylesheet/main.css");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:Artillery/src/be/kdg/artillery/view/images/proj/weapon_2.png"));
        primaryStage.setTitle("Artillery");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
