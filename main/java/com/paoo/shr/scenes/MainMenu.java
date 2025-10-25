package com.paoo.shr.scenes;

import com.paoo.shr.core.RequiresInitialization;
import com.paoo.shr.core.Resources;
import com.paoo.shr.graphics.Drawable;
import com.paoo.shr.sound.SoundManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import static com.paoo.shr.scenes.SceneManager.SceneIds.GAME;

public final class MainMenu extends Scene implements RequiresInitialization, Drawable {

    private Pane root;
    private Image spriteImage = new Image(getClass().getResourceAsStream("/sprites/NewB.png"));
    private ImageView buttonImageView = new ImageView(spriteImage);



    public ImageView getSprite(String path, double x, double y, double w, double h, double scale) {
        Image sheet = new Image(getClass().getResourceAsStream("/sprites/NewB.png"));
        ImageView view = new ImageView(sheet);
        view.setViewport(new Rectangle2D(x, y, w, h));
        view.setPreserveRatio(true);
        return view;
    }

    private MainMenu() {
        super(new Pane());

        root = (Pane) this.getRoot();

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(Resources.spr_bckgr.toString()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        root.setBackground(new Background(backgroundImage));

        // Creează butonul New Game
        Button newGameButton = new Button("");
        ImageView sprite1 = getSprite(Resources.spr_btns.toString(), 0, 0, 148, 65, 2);
        newGameButton.setGraphic(sprite1);
        newGameButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        newGameButton.setLayoutX(50);
        newGameButton.setLayoutY(50);
        newGameButton.setOnAction(e -> {
            System.out.println("New Game Pressed");
            SceneManager.getScene(GAME).changeScene().play();
            SoundManager.getInstance().playOof();
        });

        Button loadButton = new Button("");
        ImageView sprite2 = getSprite(Resources.spr_btns.toString(), 156, 0, 148, 65, 2);
        loadButton.setGraphic(sprite2);
        loadButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        loadButton.setLayoutX(50);
        loadButton.setLayoutY(150);
        loadButton.setOnAction(e -> {
            System.out.println("Load Game Pressed");
        });

        Button settingsButton = new Button("");
        ImageView sprite3 = getSprite("/spritesheet.png", 311, 0, 148, 65, 2);
        settingsButton.setGraphic(sprite3);
        settingsButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        settingsButton.setLayoutX(50);
        settingsButton.setLayoutY(250);
        settingsButton.setOnAction(e -> {
            System.out.println("Settings Pressed");
        });

        addComponent(newGameButton);
        addComponent(loadButton);
        addComponent(settingsButton);
        System.out.println("MainMenu");
    }


    @Override
    public void play() {

    }

    @Override
    protected void addComponent(Node component) {
        root.getChildren().add(component);
    }

    // Singleton
    public static MainMenu getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void init() {
        // incarcare de fisiere necesare pt main menu
    }

    @Override
    public void draw(GraphicsContext gc) {

    }

    private static final class InstanceHolder {
        private static final MainMenu INSTANCE = new MainMenu();
    }
}
