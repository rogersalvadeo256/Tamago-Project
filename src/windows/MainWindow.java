package windows;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import windows.stages.MonsterStage;

public class MainWindow extends Stage {
	// layout stuff
	AnchorPane layout = new AnchorPane();

	MonsterStage mstStage = new MonsterStage();

	public MainWindow() {

		visualConfig();

	}

	private void visualConfig() {
		// TODO Auto-generated method stub

		Scene cena = new Scene(mstStage, 400, 500);
		this.setScene(cena);
		this.show();
	}

}
