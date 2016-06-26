package be.kdg.artillery.view.game;

import be.kdg.artillery.model.*;
import be.kdg.artillery.view.about.AboutPresenter;
import be.kdg.artillery.view.about.AboutView;
import be.kdg.artillery.view.highscore.HighscorePresenter;
import be.kdg.artillery.view.highscore.HighscoreView;
import be.kdg.artillery.view.info.InfoPresenter;
import be.kdg.artillery.view.info.InfoView;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;

/**
 * Robby Dirix & Laurens de Voogd
 * 18-Feb-16
 */
public class TankPresenter {
    private Artillery model;
    private TankView view;
    boolean isOutOfBounds;


    public TankPresenter(Artillery model, TankView view) {
        this.model = model;
        this.view = view;
        view.setNaam1(model.getSpeler1().getName());
        view.setNaam2(model.getSpeler2().getName());
        view.setColor1(model.getSpeler1().getColor());
        view.setColor2(model.getSpeler2().getColor());
        addEventHandlers();
        updateView();
    }

    private void updateView() {
        view.drawGame();
    }

    private void updateShot() {
        view.animateShot();
        view.getBtnFire().setDisable(true);
        view.getTransition().setOnFinished(event -> {
            updateView();
            view.shoot();
            view.updateHp();
            if (!isOutOfBounds)
                view.explode();
            view.getImgvproj()[view.getCbmProjectiles().getSelectionModel().getSelectedIndex()].setVisible(false);
            view.getBtnFire().setDisable(false);

            if (model.getEnemyPlayer().getTank().isDead()) {
                model.getHighscoreEntry();
                List<HighscoreEntry> entries = SaveData.loadHighscoreEntries();
                entries.add(model.getHighscoreEntry()[model.getSelectedPlayerIndex()]);
                SaveData.saveHiscore(entries);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setGraphic(new ImageView(new Image("file:Artillery/src/WonIcon.png")));
                alert.setTitle(String.format("%s heeft gewonnen", model.getSelectedPlayer().getName()));
                alert.setHeaderText("Game Over");
                alert.setContentText(String.format("%s heeft gewonnen.\nMaak via het menu een nieuw spel aan", model.getSelectedPlayer().getName()));
                alert.show();
                view.getBtnFire().setDisable(true);
            }
            model.switchPlayer();
        });
        if (model.getSpeler1().isDoge() || model.getSpeler2().isDoge()) {
            Random rnd = new Random();
            view.drawDoge(rnd.nextInt(500) + 50, rnd.nextInt(150) + 50);
        }
    }

    private void addEventHandlers() {
        view.getAbout().setOnAction(event -> {
            AboutView aboutView = new AboutView();
            AboutPresenter aboutPresenter = new AboutPresenter(model, aboutView);
            Stage aboutStage = new Stage();
            aboutStage.initOwner(view.getScene().getWindow());
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.setScene(new Scene(aboutView));
            aboutStage.centerOnScreen();
            aboutStage.showAndWait();
        });
        view.getHighscores().setOnAction(event -> {
            HighscoreView highscoreView = new HighscoreView();
            HighscorePresenter highscorePresenter = new HighscorePresenter(model, highscoreView);
            Stage highScoreStage = new Stage();
            highScoreStage.initOwner(view.getScene().getWindow());
            highScoreStage.initModality(Modality.APPLICATION_MODAL);
            highScoreStage.setScene(new Scene(highscoreView));
            highScoreStage.centerOnScreen();
            highScoreStage.showAndWait();
        });
        view.getInfo().setOnAction(event -> {
            InfoView infoView = new InfoView();
            InfoPresenter infoPresenter = new InfoPresenter(model, infoView);
            Stage infoStage = new Stage();
            infoStage.initOwner(view.getScene().getWindow());
            infoStage.initModality(Modality.APPLICATION_MODAL);
            infoStage.setScene(new Scene(infoView));
            infoStage.centerOnScreen();
            infoStage.showAndWait();
        });
        view.getNewGame().setOnAction(event -> {
            model.getHighscoreEntry()[model.getSelectedPlayerIndex()].resetBeurten();
            model.setSpeler1(new Player(view.getNaam1(), model.getSpeler1().getColor()));
            model.setSpeler2(new Player(view.getNaam2(), model.getSpeler2().getColor()));
            model.setTerrain(model.loadTerrain());
            model.generateTerrain(view.getPrefWidth(), view.getPrefHeight());
            TankView tankView = new TankView(model.getTerrain().getX(), model.getTerrain().getY());
            TankPresenter tankPresenter = new TankPresenter(model, tankView);
            view.getScene().setRoot(tankView);
            tankView.getScene().getWindow().sizeToScene();
            tankView.getScene().getWindow().centerOnScreen();
        });
        view.getCbmProjectiles().setOnAction(event -> view.setSelectedProj(view.getCbmProjectiles().getSelectionModel().getSelectedIndex()));

        view.getExit().setOnAction(event -> view.getScene().getWindow().hide());
        view.getSave().setOnAction(event -> model.saveTerrain(view.getX(), view.getY()));

        view.getBtnFire().setOnAction(event -> {
            model.getHighscoreEntry()[model.getSelectedPlayerIndex()].addBeurt();
            Vec2d[] traject = new Vec2d[0];
            if (model.getSelectedPlayerIndex() == 0)
                traject = model.getTraject(-view.getSpAngle().getValue(), view.getSldStrength().getValue(), view.getWind(), model.getTerrain().getX()[5] + 33, model.getTerrain().getY()[5] - 42);
            else
                traject = model.getTraject(view.getSpAngle().getValue() + 180, view.getSldStrength().getValue(), view.getWind(), model.getTerrain().getX()[41] + 33, model.getTerrain().getY()[41] - 40);

            view.setPath(Collision.trim(traject, view.getPrefWidth(), view.getPrefHeight(), model.getTerrain().getX(), model.getTerrain().getY()));

            boolean raak = false;

            for (Vec2d path : traject) {

                Vec2d posTank = model.getSelectedPlayerIndex() == 1 ? new Vec2d(model.getTerrain().getX()[5], model.getTerrain().getY()[5] - 50) :
                        new Vec2d(model.getTerrain().getX()[41], model.getTerrain().getY()[41] - 50);

                if (Collision.outOfBounds(path.x, path.y, view.getPrefWidth(), view.getPrefHeight())) {
                    isOutOfBounds = true;
                    break;
                }

                isOutOfBounds = false;
                if (Collision.tankCollision(path.x, path.y, posTank)) {
                    raak = true;
                    view.setEndXProj(path.x);
                    view.setEndYProj(path.y);
                    break;
                } else if (Collision.terrainCollision(path.x, path.y, model.getTerrain().getX(), model.getTerrain().getY())) {
                    view.setEndXProj(path.x);
                    view.setEndYProj(path.y);
                    break;
                }
            }

            if (raak) {
                int damage = model.getProjDamage(view.getCbmProjectiles().getSelectionModel().getSelectedIndex());
                model.getEnemyPlayer().getTank().hit(damage);

                if (model.getSelectedPlayerIndex() == 1)
                    view.setHpTankLeft(model.getEnemyPlayer().getTank().getHealth());
                else
                    view.setHpTankRight(model.getEnemyPlayer().getTank().getHealth());

            }
            if (new Random().nextInt(10) == 0) {
                view.setWind((0.5 - new Random().nextDouble()) * 6);
            }
            updateShot();

        });

        view.getSpAngle().valueProperty().addListener(event -> {

            if (model.getSelectedPlayerIndex() == 0)
                view.setAngleTankLeft(view.getSpAngle().getValue());
            else
                view.setAngleTankRight(view.getSpAngle().getValue());

            updateView();
        });

    }
}
