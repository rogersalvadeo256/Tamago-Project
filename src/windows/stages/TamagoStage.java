package windows.stages;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
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

public class TamagoStage extends AnchorPane {

	Label ttlWarmth, ttlHappines;
	Label lblWarmth, lblHappines, lblTime;
	ToggleButton tgbLight = new ToggleButton("On");

	File tmgImg = new File("Resources/Finals/Tamagos/DigitalEgg.png");
	File smile = new File("Resources/Finals/UI/smile.png");
	File thermomether = new File("Resources/Finals/UI/thermometer.png");

	File lampOn = new File("Resources/Finals/UI/lampOn.png");
	Image imgLampOn = new Image(lampOn.toURI().toString());
	File lampOff = new File("Resources/Finals/UI/lampOff.png");
	Image imgLampOff = new Image(lampOff.toURI().toString());

	ImageView lampImg = new ImageView(imgLampOn);

	File onButton = new File("Resources/Finals/UI/onButton.png");
	Image imgOnBtn = new Image(onButton.toURI().toString());
	File offButton = new File("Resources/Finals/UI/offButton.png");
	Image imgOffBtn = new Image(offButton.toURI().toString());

	ImageView btnLamp = new ImageView(imgOnBtn);

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

	private boolean runThis = true;
	private boolean lights = true;

	Tamago tmg = new Tamago();

	public TamagoStage() throws JAXBException {

		if (getFirstTime(global)) {
			System.out.println("here Tamago1");
			tmg.setHappines(100);
			tmg.setTime(1800);
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

		vbHappy.getChildren().addAll(imgSmile, lblHappines);
		lblHappines.setAlignment(Pos.CENTER);
		vbWarmth.getChildren().addAll(imgWarmth, lblWarmth);
		lblWarmth.setAlignment(Pos.CENTER);

		lblHappines.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String t, String t1) {

			}
		});

		Task counter = new Task<Void>() {
			protected Void call() throws InterruptedException {
				while (runThis) {
					for (int i = (int) tmg.getTime(); i > 0; i--) {
						if (runThis) {
							tmg.setTime(i);

							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									lblTime.setText(String.valueOf(tmg.getTime()));

								}
							});

							Thread.sleep(1000);
						} else {
							break;
						}

					}
				}
				return null;

			}
		};

		Task happyTask = new Task<Void>() {
			protected Void call() throws InterruptedException {

				for (int i = tmg.getHappines(); i > 0; i--) {
					if (runThis) {

						tmg.setHappines(i);
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								lblHappines.setText(String.valueOf(tmg.getHappines()));

							}
						});

						Thread.sleep(3000);

					} else {
						break;
					}

				}
				return null;
			}

		};

		Task warmthTaskOff = new Task<Void>() {
			protected Void call() throws Exception {

				for (int i = tmg.getWarmth(); i > 0; i--) {
					if (runThis) {
						tmg.setWarmth(i);
						System.out.println(i);
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								lblWarmth.setText(String.valueOf(tmg.getWarmth()));

							}
						});

						Thread.sleep(3000);
					} else {
						break;
					}
				}

				return null;
			}

		};

		Task warmthTaskOn = new Task<Void>() {
			protected Void call() throws Exception {

				for (int i = tmg.getWarmth(); i < 100; i++) {
					if (runThis) {
						tmg.setWarmth(i);
						System.out.println(i);
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								lblWarmth.setText(String.valueOf(tmg.getWarmth()));

							}
						});

						Thread.sleep(3000);
					} else {
						break;
					}
				}

				return null;
			}

		};
		
		Thread count = new Thread(counter);
		Thread happyThread = new Thread(happyTask);
		Thread warmthThreadOff = new Thread(warmthTaskOff);
		Thread warmthThreadOn = new Thread(warmthTaskOn);
		
		count.start();
		happyThread.start();

		if(lights=false) {
			warmthThreadOff.start();
			System.out.println(1);
		}else if (lights=true) {
			warmthThreadOn.start();
			System.out.println(2);
		}
		
		if (counter.isDone()) {
			count.interrupt();
		}
		if (happyTask.isDone()) {
			happyThread.interrupt();
		}
		if (warmthTaskOff.isDone()) {
			warmthThreadOff.interrupt();
		}
		if(warmthTaskOn.isDone()) {
			warmthThreadOn.interrupt();
		}

		btnLamp.setOnMouseClicked(e -> {

			if (btnLamp.getImage() == imgOnBtn) {
				this.setStyle("-fx-background-color:POWDERBLUE");

				btnLamp.setImage(imgOffBtn);
				lampImg.setImage(imgLampOff);
				
				warmthThreadOff.start();
				warmthTaskOn.cancel();
				System.out.println(warmthTaskOff.getWorkDone());
				
			} else {
				this.setStyle("-fx-background-color:WHITE");
				
				btnLamp.setImage(imgOnBtn);
				lampImg.setImage(imgLampOn);

				warmthThreadOn.start();
				System.out.println(warmthTaskOff.getWorkDone());
				

			}
		});

		btnLamp.setScaleX(2);
		btnLamp.setScaleY(2);

		AnchorPane.setLeftAnchor(vbHappy, 10d);
		AnchorPane.setTopAnchor(vbHappy, 20d);
		AnchorPane.setRightAnchor(vbWarmth, 10d);
		AnchorPane.setTopAnchor(vbWarmth, 20d);
		AnchorPane.setBottomAnchor(btnLamp, 10d);
		btnLamp.setX(75);
		// AnchorPane.setTopAnchor(lblTime, 20d);
		// AnchorPane.setRightAnchor(tgbLight, 5d);
		imgTamago.setX(50);
		imgTamago.setY(30);
		this.getChildren().addAll(vbWarmth, vbHappy, imgTamago, btnLamp, lblTime);

	}

	public boolean isRunThis() {
		return runThis;
	}

	public void setRunThis(boolean runThis) {
		this.runThis = runThis;
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

	public Tamago getTamago() {
		return this.tmg;
	}

}
