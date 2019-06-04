package windows.stages;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import randonStuff.GlobalStuff;
import tamago.Tamago;
import xmlHandlers.StarterGlobal;
import xmlHandlers.StarterTamago;

public class TamagoStage extends AnchorPane implements Runnable {

	Label ttlWarmth, ttlHappines;
	Label lblWarmth, lblHappines, lblTime;
	ToggleButton tgbLight = new ToggleButton("On");

	File tmgImg = new File("Resources/Finals/Tamagos/DigitalEgg.png");
	File smile = new File("Resources/Finals/UI/smile.png");
	File thermomether = new File("Resources/Finals/UI/thermometer.png");

	Image tamagoImg = new Image(tmgImg.toURI().toString());
	Image smileImg = new Image(smile.toURI().toString());
	Image warmthImg = new Image(thermomether.toURI().toString());

	ImageView imgTamago = new ImageView(tamagoImg);
	ImageView imgSmile = new ImageView(smileImg);
	ImageView imgWarmth = new ImageView(warmthImg);

	VBox vbHappy = new VBox();
	VBox vbWarmth = new VBox();
	
	GlobalStuff glb = new GlobalStuff();

	File global = new File("files/Global.xml");
	File tamagoFile = new File("files/Tamago.xml");

	StarterTamago st = new StarterTamago();
	StarterGlobal sg = new StarterGlobal();

	Tamago tmg = new Tamago();

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (tmg.getHappines() > 0) {

			try {

				lblHappines.setText(String.valueOf(tmg.getHappines()));
				this.tmg.setHappines(tmg.getHappines() - 1);
				System.out.println("ainda não bugou");
			} catch (Exception e) {
				System.out.println("Bugou rs");
				e.printStackTrace();
			}
		}

	}

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

		vbHappy.getChildren().addAll(imgSmile,lblHappines);
		lblHappines.setAlignment(Pos.CENTER);
		vbWarmth.getChildren().addAll(imgWarmth,lblWarmth);
		lblWarmth.setAlignment(Pos.CENTER);
		
		lblHappines.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String t, String t1) {
				System.out.println("Label Text Changed");
			}
		});

		tgbLight.setOnAction(e -> {
			if (tgbLight.getText() == "On") {
				tgbLight.setText("Off");
				this.setStyle("-fx-background-color:POWDERBLUE");
			} else {
				tgbLight.setText("On");
				this.setStyle("-fx-background-color:WHITE");
			}
		});

		AnchorPane.setLeftAnchor(vbHappy, 10d);
		AnchorPane.setTopAnchor(vbHappy, 20d);
		AnchorPane.setRightAnchor(vbWarmth, 10d);
		AnchorPane.setTopAnchor(vbWarmth, 20d);
		//AnchorPane.setTopAnchor(lblTime, 20d);
		//AnchorPane.setRightAnchor(tgbLight, 5d);
		imgTamago.setX(50);
		imgTamago.setY(30);
		this.getChildren().addAll(vbWarmth, vbHappy, tgbLight, imgTamago);

		
		run();

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
