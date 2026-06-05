package view.stages;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.MonsterState;

public class MonsterStageView extends AnchorPane {

    public MonsterStageView(MonsterState monster) {
        HBox hbxLife = createMetric("HP: ", monster.getHp());
        HBox hbxFood = createMetric("Hunger: ", monster.getFood());
        HBox hbxWater = createMetric("Thirst: ", monster.getWater());
        HBox hbxDiscipline = createMetric("Discipline: ", monster.getDiscipline());
        HBox hbxAge = createMetric("Age: ", monster.getAge());

        VBox leftCorner = new VBox(hbxFood, hbxWater);
        VBox rightCorner = new VBox(hbxDiscipline, hbxLife);
        VBox middle = new VBox(hbxAge);

        AnchorPane.setLeftAnchor(leftCorner, 5d);
        AnchorPane.setTopAnchor(leftCorner, 20d);
        AnchorPane.setRightAnchor(rightCorner, 5d);
        AnchorPane.setTopAnchor(rightCorner, 20d);
        AnchorPane.setLeftAnchor(middle, 180d);
        AnchorPane.setTopAnchor(middle, 20d);

        getChildren().addAll(leftCorner, rightCorner, middle);
    }

    private HBox createMetric(String label, int value) {
        Label title = new Label(label);
        Label metric = new Label(String.valueOf(value));
        HBox box = new HBox(title, metric);
        box.setAlignment(Pos.BASELINE_RIGHT);
        return box;
    }
}
