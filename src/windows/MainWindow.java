package windows;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import windows.stages.MonsterStage;
import windows.stages.TamagoStage;

public class MainWindow extends Stage {
	// layout stuff
	AnchorPane layout = new AnchorPane();

	MonsterStage mstStage = new MonsterStage();
	TamagoStage tmgStage = new TamagoStage();
	

	public MainWindow() {

		visualConfig();

	}

	private void visualConfig() {
		// TODO Auto-generated method stub

		Scene cena = new Scene(tmgStage, 400, 500);
		this.setScene(cena);
		this.show();
	}

}
