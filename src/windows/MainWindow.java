package windows;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tamago.Monster;

public class MainWindow extends Stage {
	// layout stuff
	AnchorPane layout = new AnchorPane();
	HBox hbxTop, hbxTop2, hbxMiddle, hbxBottom;
	
	Button btnTmp = new Button("Tmp");
	
	
	// Titles for the UI things
	Label ttlTime, ttlHappines, ttlWarmth;

	// The Actual UI things
	Label lblName, lblLife, lblFood, lblWater, lblWeight, lblAge;
	Label lblTime, lblHappines, lblWarmth;

	VBox vbNL=new VBox(),vbFW = new VBox(),vbWA = new VBox(),vbTH = new VBox();
	
	// imageviews for both Tamago and Monster
	ImageView imgMonster, imgTamago;

	// buttons for interaction
	Button btnFeed = new Button("Feed"), btnDrink = new Button("Drink");
	VBox vbBtn = new VBox();
	
	ToggleButton tgbLight;

	Monster mst = new Monster();
	
	public MainWindow() {
		
		
		setTamagoAndMonster();
		visualConfig();
		
	}
	
	private void setTamagoAndMonster() {
	
		mst.setAge(1);
		mst.setBath(0);
		mst.setFood(100);
		mst.setHp(100);
		mst.setName("Ace");
		mst.setType("Fire");
		mst.setWater(100);
		mst.setWeight(15);
	
		
	}
	
	private void visualConfig() {
		// TODO Auto-generated method stub
		lblName = new Label(mst.getName());
		lblLife= new Label(String.valueOf(mst.getHp()));
		lblFood= new Label(String.valueOf(mst.getFood()));
		lblWater= new Label(String.valueOf(mst.getWater()));
		lblWeight= new Label(String.valueOf(mst.getWeight()));
		lblAge= new Label(String.valueOf(mst.getAge()));

		
		hbxTop = new HBox();
		hbxTop2 = new HBox();

		// UI Stuff, for the Monster stage
		this.ttlName = new Label("Name: ");
		this.ttlLife = new Label("HP: ");
		this.ttlFood = new Label("Hunger: ");
		this.ttlWater = new Label("Thirst: ");
		this.ttlWeight = new Label("Weight: ");
		this.ttlAge = new Label("Age: ");

		// UI Stuff for the tamago stage
		this.ttlTime = new Label("Time: ");
		this.ttlHappines = new Label("Happines: ");
		this.ttlWarmth = new Label("Warmth: ");

		// setting things in the hbox and vbox
		hbxName.getChildren().addAll(ttlName,lblName);
		hbxAge.getChildren().addAll(ttlAge,lblAge);
		hbxFood.getChildren().addAll(ttlFood,lblFood);
		hbxWater.getChildren().addAll(ttlWater,lblWater);
		HbxLife.getChildren().addAll(ttlLife,lblLife);
		
		this.vbNL.getChildren().addAll(hbxName,HbxLife);
		this.vbFW.getChildren().addAll(hbxFood,hbxWater);
		this.vbWA.getChildren().addAll(hbxWeight,hbxAge);
		
		this.hbxTop.getChildren().addAll(vbNL,vbFW,vbWA);
		//this.hbxTop2.getChildren().addAll();
		this.vbBtn.getChildren().addAll(btnDrink,btnFeed);
		
		this.btnTmp.setOnAction(e->{
			new Name_Window();
		});
		
		AnchorPane.setLeftAnchor(hbxTop, 12d);
		AnchorPane.setRightAnchor(hbxTop2, 12d);
		AnchorPane.setBottomAnchor(vbBtn, 25d);
		
		layout.getChildren().addAll(hbxTop, hbxTop2,vbBtn,btnTmp);
		
		Scene cena = new Scene(layout, 400, 500);
		this.setScene(cena);
		this.show();
	}

}
