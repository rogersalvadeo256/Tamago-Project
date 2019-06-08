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
import javafx.scene.text.Font;
import randonStuff.GlobalStuff;
import tamago.Tamago;
import windows.Name_Window;
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

	private boolean counterRunning=true;
	
	private boolean runThis = true;
	private boolean lights = true;

	Tamago tmg = new Tamago();

	public TamagoStage() throws JAXBException {

		if (getFirstTime(global)) {
			System.out.println("here Tamago1");
			tmg.setHappines(50);
			tmg.setTime(1800);
			tmg.setWarmth(50);

			st.starter(tmg);

			glb.setFirstTime(false);
			glb.setMethod(getMethod(global));

			sg.starter(glb);

		} else {
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

		Task<Void> counter = new Task<Void>() {
			protected Void call() throws InterruptedException {
				while (true) {
					if (runThis) {
						for (int i = (int) tmg.getTime(); i >= 0; i--) {
							if (runThis) {
								tmg.setTime(i);

								Platform.runLater(new Runnable() {

									@Override
									public void run() {

										lblTime.setText(secondsToString((int) tmg.getTime()));

									}
								});

								if(i==0) {
									System.out.println("i==0");
									runThis=false;
									break;
								}
								
								Thread.sleep(1000);
							} else {
								System.out.println("COCOCOCOMBO BREAKER");
								
								break;
							}

						}
					}
					else {
						System.out.println("breaked");
						runThis=false;
						break;
					}
				}
				return null;

			}
		};

		Task<Void> happyTask = new Task<Void>() {
			protected Void call() throws InterruptedException {

				while (runThis) {
					if (runThis) {
						int tmgHappy = tmg.getHappines();

						while (tmgHappy > 0) {
							if (runThis) {
								tmgHappy = tmg.getHappines();
								tmgHappy--;
								tmg.setHappines(tmgHappy);
								

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

					} else {
						break;
					}
				}
				return null;
			}

		};

		Task<Void> warmthTask = new Task<Void>() {
			protected Void call() throws InterruptedException {

				while (runThis) {

					for (int i = tmg.getWarmth(); i >= 0; i--) {

						if (runThis) {

							if (!lights) {

								System.out.println(lights);
								System.out.println(i);
								tmg.setWarmth(i);
								Platform.runLater(new Runnable() {

									@Override
									public void run() {
										lblWarmth.setText(String.valueOf(tmg.getWarmth()));

									}
								});

								Thread.sleep(3000);
							} else {
								System.out.println("break from the false");
								break;
							}

						} else {
							break;
						}

					}

					for (int j = tmg.getWarmth(); j <= 100; j++) {

						if (runThis) {

							if (lights) {
								System.out.println(lights);
								System.out.println(j);
								tmg.setWarmth(j);
								Platform.runLater(new Runnable() {

									public void run() {
										lblWarmth.setText(String.valueOf(tmg.getWarmth()));

									};

								});

								Thread.sleep(3000);
							} else {
								System.out.println("break from the truth");
								break;
							}

						} else {
							break;
						}

					}

					if (!runThis) {
						break;
					}
				}
				return null;
			}

		};

		Thread count = new Thread(counter);
		Thread happyThread = new Thread(happyTask);
		Thread warmthThread = new Thread(warmthTask);

		count.start();
		happyThread.start();
		warmthThread.start();

		if (counter.isDone()) {
			System.out.println("isDone");
			count.interrupt();
			runThis = false;
			happyThread.interrupt();
			warmthThread.interrupt();
			new Name_Window();

		}
		
		
		
//		if (happyTask.isDone()) {
//			happyThread.interrupt();
//		}
//		if (warmthTask.isDone()) {
//			warmthThread.interrupt();
//		}

		imgTamago.setOnMouseClicked(e -> {
			tmg.setHappines(tmg.getHappines() + 1);
			lblHappines.setText(String.valueOf(tmg.getHappines()));
		});

		btnLamp.setOnMouseClicked(e -> {

			if (btnLamp.getImage() == imgOnBtn) {
				this.setStyle("-fx-background-color:POWDERBLUE");
				lights = false;
				btnLamp.setImage(imgOffBtn);
				lampImg.setImage(imgLampOff);
				System.out.println("desliga");

			} else {
				this.setStyle("-fx-background-color:WHITE");
				lights = true;
				btnLamp.setImage(imgOnBtn);
				lampImg.setImage(imgLampOn);
				System.out.println("liga");
			}
		});

		btnLamp.setScaleX(2);
		btnLamp.setScaleY(2);

		lblHappines.setFont(new Font("Super Legend Boy", 15));
		lblTime.setFont(new Font("Super Legend Boy", 15));
		lblWarmth.setFont(new Font("Super Legend Boy", 15));

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
		AnchorPane.setRightAnchor(lblTime, 75d);
		this.getChildren().addAll(vbWarmth, vbHappy, imgTamago, btnLamp, lblTime);

	}

	private String secondsToString(int pTime) {
		final int min = pTime / 60;
		final int sec = pTime - (min * 60);

		final String strMin = placeZeroIfNeede(min);
		final String strSec = placeZeroIfNeede(sec);
		return String.format("%s:%s", strMin, strSec);
	}

	private String placeZeroIfNeede(int number) {
		return (number >= 10) ? Integer.toString(number) : String.format("0%s", Integer.toString(number));
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

	public boolean isCounterRunning() {
		return counterRunning;
	}

	public void setCounterRunning(boolean counterRunning) {
		this.counterRunning = counterRunning;
	}

	
	
}
