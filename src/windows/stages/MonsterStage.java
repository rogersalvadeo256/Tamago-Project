package windows.stages;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tamago.Monster;

public class MonsterStage extends AnchorPane {

	Label ttlLife, ttlFood, ttlWater, ttlWeight, ttlAge, ttlDicipline, ttlPoop;
	Label lblLife, lblFood, lblWater, lblWeight, lblAge, lblDicipline, lblPoop;
	Label lblName;

	VBox tlCorner = new VBox(), trCorner = new VBox(),tMiddle = new VBox();
	HBox hbxName = new HBox(), hbxLife = new HBox(), hbxFood = new HBox(), hbxWater = new HBox(),
			hbxWeight = new HBox(), hbxAge = new HBox(), hbxDicipline = new HBox(), hbxPoop = new HBox();

	Monster mst = new Monster();

	public MonsterStage() {
		// TODO Auto-generated constructor stub

		mst.setHp(250);
		mst.setFood(100);
		mst.setWater(100);
		mst.setDiscipline(100);
		mst.setAge(1);
		
		
		// UI Stuff, for the Monster stage
		this.ttlLife = new Label("HP: ");
		this.ttlFood = new Label("Hunger: ");
		this.ttlWater = new Label("Thirst: ");
		this.ttlWeight = new Label("Weight: ");
		this.ttlAge = new Label("Age: ");
		this.ttlDicipline = new Label("Dicipline: ");
		this.ttlPoop = new Label("Necessities");

		lblLife = new Label(String.valueOf(mst.getHp()));
		lblFood = new Label(String.valueOf(mst.getFood()));
		lblWater = new Label(String.valueOf(mst.getWater()));
		lblDicipline = new Label(String.valueOf(mst.getDiscipline()));
				
		hbxFood.getChildren().addAll(ttlFood,lblFood);
		hbxFood.setAlignment(Pos.BASELINE_RIGHT);
		hbxWater.getChildren().addAll(ttlWater,lblWater);
		hbxWater.setAlignment(Pos.BASELINE_RIGHT);
		hbxDicipline.getChildren().addAll(ttlDicipline,lblDicipline);
		hbxDicipline.setAlignment(Pos.BASELINE_RIGHT);
		hbxLife.getChildren().addAll(ttlLife,lblLife);
		hbxLife.setAlignment(Pos.BASELINE_RIGHT);
		
		this.tlCorner.getChildren().addAll(hbxFood,hbxWater);
		this.trCorner.getChildren().addAll(hbxDicipline,hbxLife);
		this.tMiddle.getChildren().addAll(lblAge= new Label(String.valueOf(mst.getAge())));
		
		Button btnMinus = new Button("tira");
		btnMinus.setOnAction(e ->{
			mst.setHp(mst.getHp()-1);
			lblLife.setText(String.valueOf(mst.getHp()));
		});
		
	
		AnchorPane.setRightAnchor(trCorner, 5d);
		AnchorPane.setTopAnchor(trCorner, 20d);
		AnchorPane.setLeftAnchor(tlCorner, 5d);
		AnchorPane.setTopAnchor(tlCorner, 20d);
		
		AnchorPane.setLeftAnchor(tMiddle, 200d);
		AnchorPane.setTopAnchor(tMiddle, 20d);
		//AnchorPane.setRightAnchor(tMiddle, 200d);

		
		this.getChildren().addAll(trCorner,tlCorner,tMiddle,btnMinus);

	}

}
