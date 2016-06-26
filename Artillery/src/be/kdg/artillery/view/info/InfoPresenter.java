package be.kdg.artillery.view.info;

import be.kdg.artillery.model.Artillery;

/**
 * Robby Dirix
 * 10-Mar-16
 */
public class InfoPresenter {
    Artillery model;
    InfoView view;

    public InfoPresenter(Artillery model, InfoView view) {
        this.model = model;
        this.view = view;
    }
}
