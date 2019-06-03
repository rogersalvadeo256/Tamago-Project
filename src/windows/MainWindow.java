package windows;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import randonStuff.GlobalStuff;
import windows.stages.MonsterStage;
import windows.stages.TamagoStage;
import xmlHandlers.StarterGlobal;

public class MainWindow extends Stage {
	// layout stuff
	AnchorPane layout = new AnchorPane();

	MonsterStage mstStage = new MonsterStage();
	
	Label lblDebug = new Label("a");
	
	TamagoStage tmgStage;
	GlobalStuff glb = new GlobalStuff();
	Scene cena;

	StarterGlobal sg = new StarterGlobal();

	public MainWindow() throws JAXBException {

		visualConfig();

	}

	private void visualConfig() throws JAXBException {
		// TODO Auto-generated method stub
		File fg = new File("files/Global.xml");
		File ft = new File("files/Tamago.xml");
		
		boolean method=true;
		
		cena = new Scene(layout,400,500);
		
		if (!fg.exists() && !ft.exists()) {
			System.out.println("Here1");
			glb.setMethod(true);
			glb.setFirstTime(true);

			sg.starter(glb);

		} else {
			System.out.println("Here2");
			method = getMethod(fg);
			
		}
		tmgStage = new TamagoStage();
		
		if (method == true) {
			this.cena = new Scene(tmgStage, 400, 500);
			System.out.println("here");
		} else{
			this.cena = new Scene(mstStage, 400, 500);
			System.out.println("here2");
		}
		

		this.setScene(cena);
		this.show();


		
	}

	private boolean getMethod(File global) throws JAXBException {
		// TODO Auto-generated method stub

		try {
			JAXBContext jc = JAXBContext.newInstance(GlobalStuff.class);
			Unmarshaller ju = jc.createUnmarshaller();

			GlobalStuff glbStuff = (GlobalStuff) ju.unmarshal(global);

			return glbStuff.getMethod();

		} catch (JAXBException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	

}
