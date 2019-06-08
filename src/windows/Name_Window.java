package windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tamago.Monster;
import windows.stages.MonsterStage;

public class Name_Window extends Stage{

	TextField txtName;
	Button btnOk;
	Label lblText,lblWarning;
	VBox layout=new VBox();
	Monster mst = new Monster();
	
	public Name_Window(MainWindow mw) {
		
		lblText = new Label("Give your new monster a Name");
		lblWarning=new Label("Please give a proper Name");
		txtName=new TextField();
		btnOk = new Button("OK!");
		
		this.lblWarning.setVisible(false);
		
		btnOk.setOnAction(e->{
			
			if(txtName.getText()!="") {
				this.mst.setName(txtName.getText());
				
				mw.setScene(MonsterStage());
				this.close();
			}else {
				this.lblWarning.setVisible(true);
			}
			
		});
		
		this.layout.getChildren().addAll(lblText,txtName,lblWarning,btnOk);
		Scene cena = new Scene(layout);
		this.setScene(cena);
		this.show();
	}
	
}
