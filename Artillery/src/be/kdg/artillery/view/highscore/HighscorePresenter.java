package be.kdg.artillery.view.highscore;

import be.kdg.artillery.model.Artillery;

/**
 * Robby Dirix & Laurens de Voogd
 * 25-Feb-16
 */
public class HighscorePresenter {
    private Artillery model;
    private HighscoreView view;

    public HighscorePresenter(Artillery model, HighscoreView view) {
        this.model = model;
        this.view = view;
        view.getLblHighscore().setText(model.loadHighscore());
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnClose().setOnAction(event -> view.getScene().getWindow().hide());
    }
}
