package be.kdg.artillery.view.splashscreen;

import be.kdg.artillery.model.Artillery;
import be.kdg.artillery.view.start.StartPresenter;
import be.kdg.artillery.view.start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Robby Dirix
 * 12-Mar-16
 */
public class SplashscreenPresenter {
    Artillery model;
    SplashscreenView view;

    public SplashscreenPresenter(Artillery model, SplashscreenView view) {
        this.model = model;
        this.view = view;
        addEventHandelers();
    }

    private void addEventHandelers() {
        view.getFade().setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StartView startView = new StartView();
                StartPresenter splashscreenPresenter = new StartPresenter(model, startView);
                view.getScene().setRoot(startView);
                startView.getScene().getWindow().sizeToScene();
                startView.getScene().getWindow().centerOnScreen();
            }
        });
    }
}

