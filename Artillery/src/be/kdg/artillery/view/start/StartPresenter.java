package be.kdg.artillery.view.start;

import be.kdg.artillery.model.Artillery;
import be.kdg.artillery.model.HighscoreEntry;
import be.kdg.artillery.model.Player;
import be.kdg.artillery.view.game.TankPresenter;
import be.kdg.artillery.view.game.TankView;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Robby Dirix & Laurens de Voogd
 * 25-Feb-16
 */
public class StartPresenter {

    Artillery model;
    StartView view;

    public StartPresenter(Artillery model, StartView view) {
        this.model = model;
        this.view = view;
        addEventHandelers();
    }

    private void addEventHandelers() {
        view.getBtnStart().setOnAction(event -> {
            model.generateTerrain(TankView.PREF_WIDTH, TankView.PREF_HEIGHT);
            model.setSpeler1(new Player(view.getTxtPlayer1().getText(), view.getCmb1().getValue()));
            model.getHighscoreEntry()[0] = new HighscoreEntry(view.getTxtPlayer1().getText());
            model.getHighscoreEntry()[1] = new HighscoreEntry(view.getTxtPlayer2().getText());
            model.setSpeler2(new Player(view.getTxtPlayer2().getText(), view.getCmb2().getValue()));
            TankView tankView = new TankView(model.getTerrain().getX(), model.getTerrain().getY());
            TankPresenter tankPresenter = new TankPresenter(model, tankView);
            view.getScene().setRoot(tankView);
            tankView.getScene().getWindow().sizeToScene();
            tankView.getScene().getWindow().centerOnScreen();

        });
        view.getBtnVerder().setOnAction((e) -> {
            Path f = Paths.get(model.getFileNameTer());

            if (Files.exists(f)) {
                model.setTerrain(model.loadTerrain());
                model.setSpeler1(new Player(view.getTxtPlayer1().getText(), view.getCmb1().getValue()));
                model.getHighscoreEntry()[0] = new HighscoreEntry(view.getTxtPlayer1().getText());
                model.getHighscoreEntry()[1] = new HighscoreEntry(view.getTxtPlayer2().getText());
                model.setSpeler2(new Player(view.getTxtPlayer2().getText(), view.getCmb2().getValue()));
                TankView tankView = new TankView(model.getTerrain().getX(), model.getTerrain().getY());
                TankPresenter tankPresenter = new TankPresenter(model, tankView);
                view.getScene().setRoot(tankView);
                tankView.getScene().getWindow().sizeToScene();
                tankView.getScene().getWindow().centerOnScreen();
            }
        });
    }
}
