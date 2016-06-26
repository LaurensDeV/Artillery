package be.kdg.artillery.view.game;


import com.sun.javafx.geom.Vec2d;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.Random;


/**
 * Robby Dirix & Laurens de Voogd
 * 18-Feb-16
 */
public class TankView extends BorderPane {

    public static final int PREF_WIDTH = 1366;
    public static final int PREF_HEIGHT = 768;

    private MenuItem about;
    private MenuItem highscores;
    private MenuItem exit;
    private MenuItem save;
    private MenuItem info;

    private MenuItem newGame;

    private MenuBar menubar;

    private Spinner<Integer> spAngle;
    private Button btnFire;
    private Slider sldStrength;

    private ComboBox<String> cbmProjectiles;

    private Image imgBackground;
    private Image imgTerrain;
    private Image imgGrass;
    private Image imgTankLeft;
    private Image imgTankRight;
    private Image imgBarrel_left;
    private Image imgBarrel_right;


    private ImageView[] imgvproj = new ImageView[6];
    private Path projPath;
    private PathTransition transition;

    private Image expl;
    private ImageView imgvExpl;
    private Canvas cnvGame;

    private double endXProj;
    private double endYProj;

    private int selectedProj;

    private double[] x;
    private double[] y;
    private Vec2d[] path;
    private int angleTankRight;
    private int angleTankLeft;
    private int hpTankRight = 100;
    private int hpTankLeft = 100;
    private String color1;
    private String color2;
    private String naam1;
    private String naam2;
    private double wind;

    public TankView(double[] x, double[] y) {
        wind = (0.5 - new Random().nextDouble()) * 6;
        this.x = x;
        this.y = y;
        initialiseNodes();
        layoutNodes();
    }

    public ComboBox<String> getCbmProjectiles() {
        return cbmProjectiles;
    }

    public String getNaam1() {
        return naam1;
    }

    public String getNaam2() {
        return naam2;
    }

    public double getWind() {
        return wind;
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    public MenuItem getNewGame() {
        return newGame;
    }

    public MenuItem getAbout() {
        return about;
    }

    public MenuItem getSave() {
        return save;
    }

    public MenuItem getExit() {
        return exit;
    }

    public MenuItem getHighscores() {
        return highscores;
    }

    public MenuItem getInfo() {
        return info;
    }

    public Spinner<Integer> getSpAngle() {
        return spAngle;
    }

    public Button getBtnFire() {
        return btnFire;
    }

    public Slider getSldStrength() {
        return sldStrength;
    }

    public PathTransition getTransition() {
        return transition;
    }

    public ImageView[] getImgvproj() {
        return imgvproj;
    }

    public void setAngleTankLeft(int angleTankLeft) {
        this.angleTankLeft = angleTankLeft;
    }

    public void setAngleTankRight(int angleTankRight) {
        this.angleTankRight = angleTankRight;
    }

    public void setPath(Vec2d[] path) {
        this.path = path;
    }

    public void setNaam1(String naam1) {
        this.naam1 = naam1;
    }

    public void setNaam2(String naam2) {
        this.naam2 = naam2;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public void setHpTankLeft(int hpTankLeft) {
        this.hpTankLeft = hpTankLeft;
    }

    public void setHpTankRight(int hpTankRight) {
        this.hpTankRight = hpTankRight;
    }

    public void setEndXProj(double endXProj) {
        this.endXProj = endXProj;
    }

    public void setEndYProj(double endYProj) {
        this.endYProj = endYProj;
    }

    public void setSelectedProj(int selectedProj) {
        this.selectedProj = selectedProj;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    private void initialiseNodes() {
        setPrefSize(PREF_WIDTH, PREF_HEIGHT);

        Menu menu = new Menu("Menu");
        Menu menuhelp = new Menu("Help");
        about = new MenuItem("Spelregels");
        info = new MenuItem("Info");
        highscores = new MenuItem("Highscores");
        save = new MenuItem("Opslaan");
        exit = new MenuItem("Afsluiten");
        newGame = new MenuItem("Nieuw spel");
        MenuItem[] items = {highscores, save, newGame, exit};
        menu.getItems().addAll(items);
        menuhelp.getItems().addAll(about, info);
        menubar = new MenuBar();
        menubar.getMenus().addAll(menu, menuhelp);
        spAngle = new Spinner<>(0, 90, 45);
        spAngle.setEditable(true);
        btnFire = new Button("Fire");
        btnFire.setPrefSize(150, 100);

        sldStrength = new Slider(150, 1000, 50);
        sldStrength.setPrefWidth(400);


        cbmProjectiles = new ComboBox<>();
        ObservableList<String> proj = FXCollections.observableArrayList("Rocket", "Hellfire Missile", "Red Bomb", "Grenade", "Atomb Bomb", "Chicken");
        cbmProjectiles.setItems(proj);
        cbmProjectiles.getSelectionModel().select(0);

        color1 = "Geel";
        color2 = "Zwart";


        imgBackground = new Image("file:Artillery/src/be/kdg/artillery/view/images/background_03.png");
        imgTerrain = new Image("file:Artillery/src/be/kdg/artillery/view/images/TerrainPic.PNG");
        imgGrass = new Image("file:Artillery/src/be/kdg/artillery/view/images/grass.png");
        imgTankLeft = new Image("file:Artillery/src/be/kdg/artillery/view/images/Tank_Left.png");
        imgTankRight = new Image("file:Artillery/src/be/kdg/artillery/view/images/Tank_Right.png");
        imgBarrel_left = new Image("file:Artillery/src/be/kdg/artillery/view/images/barrels/Barrel_" + color1 + "_Left.png");
        imgBarrel_right = new Image("file:Artillery/src/be/kdg/artillery/view/images/barrels/Barrel_" + color2 + "_Right.png");

        transition = new PathTransition();
        for (int i = 0; i < imgvproj.length; i++) {
            double[] scale = {0.3, 0.3, 0.3, 0.3, 0.5, 0.5};
            imgvproj[i] = new ImageView(new Image(String.format("file:Artillery/src/be/kdg/artillery/view/images/proj/weapon_%s.png", i + 1)));
            imgvproj[i].setFitWidth(imgvproj[i].getBoundsInParent().getWidth() * scale[i]);
            imgvproj[i].setFitHeight(imgvproj[i].getBoundsInParent().getHeight() * scale[i]);
            imgvproj[i].setVisible(false);
        }

        expl = new Image("file:Artillery/src/be/kdg/artillery/view/images/explosion.png");

        imgvExpl = new ImageView(expl);

        imgvExpl.setVisible(false);
        projPath = new Path();
        projPath.setFill(null);
        projPath.setStrokeWidth(0);


        cnvGame = new Canvas(this.getPrefWidth(), this.getPrefHeight());

        path = new Vec2d[400];
        angleTankLeft = 45;
        angleTankRight = 45;
    }

    private void layoutNodes() {
        drawGame();

        setTop(menubar);
        GridPane gridPane = new GridPane();
        btnFire.getStyleClass().add("fire");
        sldStrength.getStyleClass().add("progressbar");
        gridPane.getStyleClass().add("gridPane");
        Label lblStrength = new Label("Sterkte");
        Label lblAngle = new Label("Hoek");
        Label lblProj = new Label("Wapens");
        GridPane.setHalignment(lblStrength, HPos.CENTER);
        GridPane.setHalignment(lblAngle, HPos.CENTER);
        GridPane.setHalignment(lblProj, HPos.CENTER);
        GridPane.setValignment(btnFire, VPos.CENTER);
        gridPane.add(lblAngle, 1, 0);
        gridPane.add(spAngle, 1, 1);
        gridPane.add(lblProj, 2, 0);
        gridPane.add(cbmProjectiles, 2, 1);
        gridPane.add(lblStrength, 3, 0);
        gridPane.add(sldStrength, 3, 1);
        gridPane.add(btnFire, 4, 0, 4, 2);

        gridPane.autosize();
        gridPane.setMinWidth(1366);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        Group group = new Group();
        setCenter(group);
        group.getChildren().addAll(cnvGame, imgvExpl);

        for (ImageView imageView : imgvproj)
            group.getChildren().add(imageView);

        setBottom(gridPane);
    }

    public void drawGame() {
        GraphicsContext gc = cnvGame.getGraphicsContext2D();

        gc.setFill(new ImagePattern(imgBackground));
        gc.fillRect(0, 0, cnvGame.getWidth(), cnvGame.getHeight());

        gc.setLineWidth(20);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.ROUND);

        gc.setStroke(new ImagePattern(imgGrass));
        gc.strokePolyline(x, y, 54);
        gc.setFill(new ImagePattern(imgTerrain));

        gc.fillPolygon(x, y, 54);
        imgBarrel_left = new Image("file:Artillery/src/be/kdg/artillery/view/images/barrels/Barrel_" + color1 + "_Left.png");
        imgBarrel_right = new Image("file:Artillery/src/be/kdg/artillery/view/images/barrels/Barrel_" + color2 + "_Right.png");
        gc.save();
        Rotate r = new Rotate(-angleTankLeft, x[5] + 33, y[5] - 37);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        gc.drawImage(imgBarrel_left, x[5] + 33, y[5] - 45, 44, 13);

        gc.restore();
        gc.save();
        r = new Rotate(+angleTankRight, x[41] + 33, y[41] - 37);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        gc.drawImage(imgBarrel_right, x[41] - 11, y[41] - 45, 44, 13);
        gc.restore();

        gc.drawImage(imgTankLeft, x[5], y[5] - 50, 65, 50);
        gc.drawImage(imgTankRight, x[41], y[41] - 50, 65, 50);

        updateHp();
        drawNames();
        gc.setFill(Color.DARKGREEN);
        gc.fillText(String.format("Wind: %.0f%% vanuit het %s", Math.abs(getWind() * 33.3f), getWind() > 0 ? "Oosten" : "Westen"), getPrefWidth() / 2 - 50, 50);
        btnFire.setDisable(false);
    }

    public void drawDoge(double x, double y) {
        String[] dogeText = new String[]{"Such damage", "Very explosion", "Rate 5/7", "Much boom", "Very pew pew",
                "Wow", "Many destruction", "Much skill", "Many projectile", "Very accuracy", "Much easter egg"
        };
        Color[] dogeColors = new Color[]{Color.RED, Color.BLACK, Color.HOTPINK, Color.PURPLE, Color.MAGENTA
        };

        GraphicsContext gc = cnvGame.getGraphicsContext2D();
        Random rnd = new Random();

        for (int i = 1; i < 3; i++) {
            gc.setFill(dogeColors[rnd.nextInt(dogeColors.length)]);
            gc.fillText(dogeText[rnd.nextInt(dogeText.length)], x * i, y * i);
        }
    }

    public void drawNames() {
        GraphicsContext gc = cnvGame.getGraphicsContext2D();
        gc.fillText(naam1, 30, 82);
        gc.fillText(naam2, getPrefWidth() - 278, 80);
    }

    public void updateHp() {
        drawHpBar(30, 30, hpTankLeft);
        drawHpBar(getPrefWidth() - 280, 30, hpTankRight);
    }

    public void drawHpBar(double x, double y, int lifepoints) {

        GraphicsContext gc = cnvGame.getGraphicsContext2D();
        gc.setFill(Color.RED);
        double scale = 2.5;
        gc.fillRect(x, y, 100 * scale, 30);
        gc.setFill(Color.GREEN);
        gc.fillRect(x, y, lifepoints * scale, 30);
    }

    public void animateShot() {

        imgvproj[selectedProj].setVisible(true);
        projPath.getElements().clear();
        projPath.getElements().add(new MoveTo(path[0].x, path[0].y));
        for (Vec2d aPath : path) {
            projPath.getElements().addAll(new LineTo(aPath.x, aPath.y));
        }
        transition = new PathTransition(Duration.seconds(2), projPath, imgvproj[selectedProj]);
        transition.setCycleCount(1);
        transition.setAutoReverse(false);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        transition.play();
    }

    public void shoot() {
        GraphicsContext gc = cnvGame.getGraphicsContext2D();
        gc.restore();
        for (Vec2d aPath : path) {
            gc.fillRect(aPath.x, aPath.y, 2, 2);
        }
    }

    public void explode() {
        imgvExpl.setVisible(true);
        final int frames = 10;
        double frameWidth = expl.getWidth() / frames;

        imgvExpl.setTranslateX(endXProj - frameWidth / 2);
        imgvExpl.setTranslateY(endYProj - expl.getHeight() / 2);
        Rectangle mask = new Rectangle(150, expl.getHeight());
        imgvExpl.setClip(mask);

        Timeline timeline = new Timeline();

        for (int i = 0; i < frames; i++) {
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.08f * i), new KeyValue(imgvExpl.xProperty(), -frameWidth * i, Interpolator.DISCRETE));
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.setCycleCount(1);
        timeline.play();
    }
}
