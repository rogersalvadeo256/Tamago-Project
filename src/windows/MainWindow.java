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

public class MainWindow extends Stage {
	// layout stuff
	AnchorPane layout = new AnchorPane();
	HBox hbxTop, hbxTop2, hbxMiddle, hbxBottom;

	// Titles for the UI things
	Label ttlName, ttlLife, ttlFood, ttlWater, ttlWeight, ttlAge;
	HBox hbxName = new HBox(), HbxLife = new HBox(), hbxFood = new HBox(), hbxWater = new HBox(),
			hbxWeight = new HBox(), hbxAge = new HBox();
	Label ttlTime, ttlHappines, ttlWarmth;

	// The Actual UI things
	Label lblName, lblLife, lblFood, lblWater, lblWeight, lblAge;
	Label lblTime, lblHappines, lblWarmth;

	// imageviews for both Tamago and Monster
	ImageView imgMonster, imgTamago;

	// buttons for interaction
	Button btnFeed, btnDrink;
	ToggleButton tgbLight;

	public MainWindow() {
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
		hbxName.getChildren().addAll(ttlName);
		this.hbxTop.getChildren().addAll(hbxName, HbxLife);
		this.hbxTop2.getChildren().addAll(hbxWeight, hbxAge);

		layout.getChildren().addAll(hbxTop, hbxTop2);
		Scene cena = new Scene(layout, 400, 500);
		this.setScene(cena);
		this.show();
	}

}
