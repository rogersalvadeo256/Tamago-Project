package windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainWindow extends Stage{

	AnchorPane layout = new AnchorPane();
	//Titles for the UI things
	Label ttlName,ttlLife,ttlFood,ttlWater,ttlWeight,ttlAge;
	Label ttlTime,ttlHappines,ttlWarmth;
	
	//The Actual UI things
	Label lblName,lblLife,lblFood,lblWater,lblWeight,lblAge;
	Label lblTime,lblHappines,lblWarmth;
	
	//
	Button btnFeed,btnDrink;
	ToggleButton tgbLight;
	
	public MainWindow() {
		//UI Stuff, for the Monster stage
		this.ttlName = new Label("Name: ");
		this.ttlLife = new Label("HP: ");
		this.ttlFood = new Label("Hunger: ");
		this.ttlWater = new Label("Thirst: ");
		this.ttlWeight = new Label("Weight: ");
		this.ttlAge = new Label("Age: ");
		
		//UI Stuff for the tamago stage
		this.ttlTime = new Label("Time: ");
		this.ttlHappines = new Label("Happines: ");
		this.ttlWarmth = new Label("Warmth: ");
		
		Scene cena = new Scene(layout,400,500);
		this.setScene(cena);
		this.show();
	}
	
}
