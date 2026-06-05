package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NameWindowView extends Stage {

    public NameWindowView(MainWindow mainWindow) {
        Label title = new Label("Give your new monster a Name");
        Label warning = new Label("Please give a proper Name");
        warning.setVisible(false);

        TextField nameField = new TextField();
        Button okButton = new Button("OK!");

        okButton.setOnAction(event -> {
            String monsterName = nameField.getText();
            if (monsterName == null || monsterName.trim().isEmpty()) {
                warning.setVisible(true);
                return;
            }

            mainWindow.switchToMonster(monsterName.trim());
            close();
        });

        VBox layout = new VBox(title, nameField, warning, okButton);
        setScene(new Scene(layout));
        show();
    }
}
