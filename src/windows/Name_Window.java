package windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import randonStuff.GlobalStuff;
import tamago.Monster;
import windows.stages.MonsterStage;

public class Name_Window extends Stage {

	TextField txtName;
	Button btnOk;
	Label lblText, lblWarning;
	VBox layout = new VBox();
	GlobalStuff gs = new GlobalStuff();
	Monster mst = new Monster();

	MonsterStage mStage = new MonsterStage();

	public Name_Window(MainWindow mw) {

		lblText = new Label("Give your new monster a Name");
		lblWarning = new Label("Please give a proper Name");
		txtName = new TextField();
		btnOk = new Button("OK!");

		this.lblWarning.setVisible(false);

		btnOk.setOnAction(e -> {

			if (!txtName.getText().trim().isEmpty()) {
				this.mst.setName(txtName.getText());

				mw.setMethod(false);

				this.close();
			} else {

			}

		});

		this.layout.getChildren().addAll(lblText, txtName, lblWarning, btnOk);
		Scene cena = new Scene(layout);
		this.setScene(cena);
		this.show();
	}

}
