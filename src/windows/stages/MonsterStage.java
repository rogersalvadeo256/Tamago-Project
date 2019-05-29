package windows.stages;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import tamago.Monster;

public class MonsterStage extends AnchorPane {

	Label ttlName, ttlLife, ttlFood, ttlWater, ttlWeight, ttlAge, ttlDicipline,ttlPoop;
	Label lblName, lblWeight, lblAge, lblPoop;
	ProgressBar pgbLife, pgbFood, pgbWater, pgbDicipline;
	HBox hbxName = new HBox(), HbxLife = new HBox(), hbxFood = new HBox(), hbxWater = new HBox(),
			hbxWeight = new HBox(), hbxAge = new HBox();

	Monster mst;
	
	public MonsterStage() {
		// TODO Auto-generated constructor stub

		// UI Stuff, for the Monster stage
		this.ttlName = new Label("Name: ");
		this.ttlLife = new Label("HP: ");
		this.ttlFood = new Label("Hunger: ");
		this.ttlWater = new Label("Thirst: ");
		this.ttlWeight = new Label("Weight: ");
		this.ttlAge = new Label("Age: ");
		this.ttlDicipline = new Label("Dicipline: ");
		this.ttlPoop = new Label("Necessities");
		
		
//		lblName = new Label(mst.getName());
//		lblWeight= new Label(String.valueOf(mst.getWeight()));
//		lblAge= new Label(String.valueOf(mst.getAge()));
		
		pgbLife = new ProgressBar(100d);
		pgbFood = new ProgressBar(100d);
		pgbWater = new ProgressBar(100d);
		pgbDicipline = new ProgressBar(100d);
		
		
		this.getChildren().addAll(pgbLife);
		
	}

}
