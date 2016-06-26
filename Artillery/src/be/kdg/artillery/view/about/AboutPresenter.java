package be.kdg.artillery.view.about;

import be.kdg.artillery.model.Artillery;

/**
 * Robby Dirix & Laurens de Voogd
 * 25-Feb-16
 */
public class AboutPresenter {
    Artillery model;
    AboutView view;

    public AboutPresenter(Artillery model, AboutView view) {
        this.model = model;
        this.view = view;
        view.getLblAbout().setText(model.loadAbout());
        addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnCloseA().setOnAction(event -> view.getScene().getWindow().hide());
    }
}
