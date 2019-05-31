package windows.stages;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import tamago.Tamago;

public class TamagoStage extends AnchorPane{

	Label ttlWarmth,ttlHappines;
	Label lblWarmth,lblHappines,lblTime;
	ToggleButton tgbLight = new ToggleButton("Off");
	
	Tamago tmg = new Tamago();
	
	public TamagoStage() {

		tmg.setHappines(50);
		tmg.setWarmth(50);
		
		
		lblWarmth= new Label(String.valueOf(tmg.getWarmth()));
		lblHappines = new Label(String.valueOf(tmg.getHappines()));
		
		tgbLight.setOnAction(e->{
			if(tgbLight.getText()=="On") {
				tgbLight.setText("Off");
			}else {
				tgbLight.setText("On");
			}
		});
		
		
		
		AnchorPane.setRightAnchor(tgbLight, 5d);
		this.getChildren().addAll(lblWarmth,lblHappines,tgbLight);		
	}
	
}
