package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MainWindow;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        new MainWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
