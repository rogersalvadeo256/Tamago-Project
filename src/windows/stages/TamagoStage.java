package windows.stages;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import randonStuff.GlobalStuff;
import tamago.Tamago;
import xmlHandlers.StarterGlobal;
import xmlHandlers.StarterTamago;

public class TamagoStage extends AnchorPane {

	Label ttlWarmth, ttlHappines;
	Label lblWarmth, lblHappines, lblTime;
	ToggleButton tgbLight = new ToggleButton("On");

	GlobalStuff glb = new GlobalStuff();

	ImageView imgTamago;

	File global = new File("files/Global.xml");
	File tamagoFile = new File("files/Tamago.xml");

	StarterTamago st = new StarterTamago();
	StarterGlobal sg = new StarterGlobal();

	Tamago tmg = new Tamago();

	public TamagoStage() throws JAXBException {

		if (getFirstTime(global)) {
			System.out.println("here Tamago1");
			tmg.setHappines(100);
			tmg.setTime(60);
			tmg.setWarmth(100);

			st.starter(tmg);

			glb.setFirstTime(false);
			glb.setMethod(getMethod(global));

			sg.starter(glb);

		} else {
			System.out.println("here Tamago2");
			System.out.println(getFirstTime(global));
			tmg.setHappines(getTamago(tamagoFile).getHappines());
			tmg.setTime(getTamago(tamagoFile).getTime());
			tmg.setWarmth(getTamago(tamagoFile).getWarmth());
		}

		lblWarmth = new Label(String.valueOf(tmg.getWarmth()));
		lblHappines = new Label(String.valueOf(tmg.getHappines()));
		lblTime = new Label(String.valueOf(tmg.getTime()));

		tgbLight.setOnAction(e -> {
			if (tgbLight.getText() == "On") {
				tgbLight.setText("Off");
				this.setStyle("-fx-background-color:POWDERBLUE");
			} else {
				tgbLight.setText("On");
				this.setStyle("-fx-background-color:WHITE");
			}
		});
		
		
		AnchorPane.setRightAnchor(tgbLight, 5d);
		this.getChildren().addAll(lblWarmth, lblHappines, tgbLight);
	}

	public boolean getFirstTime(File global) {

		try {
			JAXBContext jc = JAXBContext.newInstance(GlobalStuff.class);
			Unmarshaller ju = jc.createUnmarshaller();

			GlobalStuff glbStuff = (GlobalStuff) ju.unmarshal(global);

			return glbStuff.isFirstTime();

		} catch (JAXBException e) {
			e.printStackTrace();
			return false;
		}

	}

	public Tamago getTamago(File tamagoFile) {
		try {
			JAXBContext jc = JAXBContext.newInstance(Tamago.class);
			Unmarshaller ju = jc.createUnmarshaller();

			Tamago tamago = (Tamago) ju.unmarshal(tamagoFile);

			return tamago;

		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}

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
