package main;

import javafx.application.Application;
import javafx.stage.Stage;
import windows.MainWindow;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

		new MainWindow();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
