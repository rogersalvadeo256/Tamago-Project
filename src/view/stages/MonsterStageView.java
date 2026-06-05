package view.stages;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.MonsterState;

public class MonsterStageView extends AnchorPane {

    private static final double SIDE_MARGIN = 5d;
    private static final double TOP_MARGIN = 20d;
    private static final double CENTER_LEFT = 180d;

    public MonsterStageView(MonsterState monster) {
        HBox hbxLife = createMetric("HP: ", monster.getHp());
        HBox hbxFood = createMetric("Hunger: ", monster.getFood());
        HBox hbxWater = createMetric("Thirst: ", monster.getWater());
        HBox hbxDiscipline = createMetric("Discipline: ", monster.getDiscipline());
        HBox hbxAge = createMetric("Age: ", monster.getAge());

        VBox leftCorner = new VBox(hbxFood, hbxWater);
        VBox rightCorner = new VBox(hbxDiscipline, hbxLife);
        VBox middle = new VBox(hbxAge);

        AnchorPane.setLeftAnchor(leftCorner, SIDE_MARGIN);
        AnchorPane.setTopAnchor(leftCorner, TOP_MARGIN);
        AnchorPane.setRightAnchor(rightCorner, SIDE_MARGIN);
        AnchorPane.setTopAnchor(rightCorner, TOP_MARGIN);
        AnchorPane.setLeftAnchor(middle, CENTER_LEFT);
        AnchorPane.setTopAnchor(middle, TOP_MARGIN);

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
