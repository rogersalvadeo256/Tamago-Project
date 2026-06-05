package view.stages;

import java.io.File;

import controller.TamagoController;
import facade.GameFacade;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.TamagoState;
import view.MainWindow;
import view.NameWindowView;

public class TamagoStageView extends AnchorPane {

    private static final String LIGHTS_ON_STYLE = "-fx-background-color:WHITE";
    private static final String LIGHTS_OFF_STYLE = "-fx-background-color:#2d3e50";
    private static final double TOP_MARGIN = 20d;
    private static final double SIDE_MARGIN = 10d;
    private static final double LAMP_BOTTOM_MARGIN = 45d;
    private static final double LAMP_LEFT_MARGIN = 8d;
    private static final double LAMP_BUTTON_BOTTOM_MARGIN = 10d;
    private static final double LAMP_BUTTON_Y = 75d;
    private static final double TAMAGO_X = 50d;
    private static final double TAMAGO_Y = 30d;
    private static final double TIMER_RIGHT_MARGIN = 75d;
    private static final double LAMP_BUTTON_SCALE = 2d;

    private final Label lblWarmth = new Label();
    private final Label lblHappiness = new Label();
    private final Label lblTime = new Label();

    private final Image imgLampOn = new Image(new File("Resources/Finals/UI/lampOn.png").toURI().toString());
    private final Image imgLampOff = new Image(new File("Resources/Finals/UI/lampOff.png").toURI().toString());
    private final Image imgOnBtn = new Image(new File("Resources/Finals/UI/onButton.png").toURI().toString());
    private final Image imgOffBtn = new Image(new File("Resources/Finals/UI/offButton.png").toURI().toString());

    private final ImageView btnLamp = new ImageView(imgOnBtn);
    private final ImageView lampImg = new ImageView(imgLampOn);

    private final TamagoController controller;
    private final GameFacade facade;

    private boolean lightsOn = true;
    private boolean expired;

    public TamagoStageView(GameFacade facade, MainWindow mainWindow) {
        this.facade = facade;

        ImageView imgTamago = new ImageView(new Image(new File("Resources/Finals/Tamagos/DigitalEgg.png").toURI().toString()));
        ImageView imgSmile = new ImageView(new Image(new File("Resources/Finals/UI/smile.png").toURI().toString()));
        ImageView imgWarmth = new ImageView(new Image(new File("Resources/Finals/UI/thermometer.png").toURI().toString()));

        VBox vbHappiness = new VBox(imgSmile, lblHappiness);
        VBox vbWarmth = new VBox(imgWarmth, lblWarmth);

        controller = new TamagoController(facade.loadTamago(), new TamagoController.Listener() {
            @Override
            public void onStateUpdated(TamagoState state) {
                Platform.runLater(() -> render(state));
            }

            @Override
            public void onExpired(TamagoState finalState) {
                expired = true;
                facade.saveTamago(finalState);
                Platform.runLater(() -> new NameWindowView(mainWindow));
            }
        });

        imgTamago.setOnMouseClicked(event -> controller.pet());
        btnLamp.setOnMouseClicked(event -> toggleLights());

        btnLamp.setScaleX(LAMP_BUTTON_SCALE);
        btnLamp.setScaleY(LAMP_BUTTON_SCALE);

        lblHappiness.setFont(new Font("Super Legend Boy", 15));
        lblTime.setFont(new Font("Super Legend Boy", 15));
        lblWarmth.setFont(new Font("Super Legend Boy", 15));

        AnchorPane.setLeftAnchor(vbHappiness, SIDE_MARGIN);
        AnchorPane.setTopAnchor(vbHappiness, TOP_MARGIN);
        AnchorPane.setRightAnchor(vbWarmth, SIDE_MARGIN);
        AnchorPane.setTopAnchor(vbWarmth, TOP_MARGIN);
        AnchorPane.setBottomAnchor(btnLamp, LAMP_BUTTON_BOTTOM_MARGIN);
        AnchorPane.setBottomAnchor(lampImg, LAMP_BOTTOM_MARGIN);
        AnchorPane.setLeftAnchor(lampImg, LAMP_LEFT_MARGIN);

        btnLamp.setX(LAMP_BUTTON_Y);
        imgTamago.setX(TAMAGO_X);
        imgTamago.setY(TAMAGO_Y);
        AnchorPane.setRightAnchor(lblTime, TIMER_RIGHT_MARGIN);

        getChildren().addAll(vbWarmth, vbHappiness, imgTamago, lampImg, btnLamp, lblTime);
        controller.start();
    }

    public void shutdown() {
        TamagoState snapshot = controller.snapshot();
        controller.stop();

        if (!expired) {
            facade.saveTamago(snapshot);
        }
    }

    private void render(TamagoState state) {
        lblWarmth.setText(String.valueOf(state.getWarmth()));
        lblHappiness.setText(String.valueOf(state.getHappiness()));
        lblTime.setText(secondsToString(state.getTime()));
    }

    private void toggleLights() {
        lightsOn = !lightsOn;
        controller.setLightsOn(lightsOn);

        if (lightsOn) {
            setStyle(LIGHTS_ON_STYLE);
            btnLamp.setImage(imgOnBtn);
            lampImg.setImage(imgLampOn);
            return;
        }

        setStyle(LIGHTS_OFF_STYLE);
        btnLamp.setImage(imgOffBtn);
        lampImg.setImage(imgLampOff);
    }

    private String secondsToString(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds - (minutes * 60);
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }
}
