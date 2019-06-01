package windows;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import randonStuff.GlobalStuff;
import windows.stages.MonsterStage;
import windows.stages.TamagoStage;
import xmlCreator.StarterGlobal;

public class MainWindow extends Stage {
	// layout stuff
	AnchorPane layout = new AnchorPane();

	MonsterStage mstStage = new MonsterStage();
	TamagoStage tmgStage = new TamagoStage();
	GlobalStuff glb = new GlobalStuff();
	Scene cena;

	StarterGlobal sg = new StarterGlobal();
	
	public MainWindow() {

		visualConfig();

	}

	private void visualConfig() {
		// TODO Auto-generated method stub

		glb.setMethod("Tamago");
		glb.setFirstTime(true);
		
		sg.starter(glb);
		
		if(glb.getMethod()=="Tamago"){
			this.cena = new Scene(tmgStage, 400, 500);
		}else if(glb.getMethod()=="Monster"){
			this.cena = new Scene(mstStage,400,500);
		}
		
		
		this.setScene(cena);
		this.show();
	}

}
