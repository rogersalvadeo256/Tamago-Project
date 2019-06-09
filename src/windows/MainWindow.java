package windows;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import randonStuff.GlobalStuff;
import tamago.Monster;
import tamago.Tamago;
import windows.stages.MonsterStage;
import windows.stages.TamagoStage;
import xmlHandlers.MonsterStarter;
import xmlHandlers.StarterGlobal;
import xmlHandlers.StarterTamago;

public class MainWindow extends Stage {
	// layout stuff
	AnchorPane layout = new AnchorPane();

	MonsterStage mstStage = new MonsterStage();

	Label lblDebug = new Label("a");

	TamagoStage tmgStage;
	GlobalStuff glb = new GlobalStuff();
	Scene cena = new Scene(layout, 400, 500);;
	Tamago ta = new Tamago();
	Monster ms = new Monster();

	boolean method = true;

	boolean runThis=true;
	
	Parent prtStage;
	
	


	StarterGlobal sg = new StarterGlobal();
	

	public MainWindow() throws JAXBException {

		visualConfig();

	}

	private void visualConfig() throws JAXBException {
		// TODO Auto-generated method stub
		File fg = new File("files/Global.xml");
		File ft = new File("files/Tamago.xml");
		File fm = new File("files/Monster.xml");

		tmgStage = new TamagoStage(this);
		mstStage = new MonsterStage();

		if (prtStage == null) {
			prtStage = tmgStage;
		}

		if (method == true) {
			cena = new Scene(tmgStage, 200, 200);
			System.out.println("here");
		} else {
			cena = new Scene(mstStage, 400, 500);
			System.out.println("here2");
		}

		

		// this.setResizable(false);

		this.setOnCloseRequest(e -> {
			System.out.println("suck my dick");
			tmgStage.setRunThis(false);
			System.out.println(tmgStage.getTamago().getTime());

			try {
				saveTmg(tmgStage.getTamago());
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		});

		this.setScene(cena);
		this.show();

	}

	public boolean setMethod(boolean mtd) {

		return this.method = mtd;

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

	public void setScene(boolean method) {

		if (method) {
			prtStage = tmgStage;
		} else {
			prtStage = mstStage;
		}

	}
	
	public Scene checkScene() {
		return cena;
	}

	public Parent getParent() {
		return prtStage;
	}

	private void saveTmg(Tamago tmg) {

		ta.setHappines(tmg.getHappines());
		ta.setName(tmg.getName());
		ta.setTime(tmg.getTime());
		ta.setWarmth(tmg.getWarmth());

		StarterTamago stm = new StarterTamago();
		stm.starter(ta);

	}
	
	private void saveMst(Monster mst) {
		ms.setAge(mst.getAge());
		ms.setBath(mst.getBath());
		ms.setDiscipline(mst.getDiscipline());
		ms.setFood(mst.getFood());
		ms.setHp(mst.getHp());
		ms.setName(mst.getName());
		ms.setPoop(mst.getPoop());
		ms.setSpecies(mst.getSpecies());
		ms.setType(mst.getType());
		ms.setWater(mst.getWater());
		ms.setWeight(mst.getWeight());
		
		MonsterStarter smt= new MonsterStarter();
		smt.starter(ms);
		
		
		
	}

}
