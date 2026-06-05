package view;

import java.nio.file.Paths;

import facade.GameFacade;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MonsterState;
import view.stages.MonsterStageView;
import view.stages.TamagoStageView;

public class MainWindow extends Stage {

    private final GameFacade facade;
    private TamagoStageView tamagoStageView;

    public MainWindow() {
        this.facade = new GameFacade(Paths.get("files"));
        this.facade.bootstrap();

        configureScene();
        configureCloseHandler();

        setTitle("Tamago Project");
        show();
    }

    public void switchToMonster(String name) {
        facade.switchToMonsterMode(name);

        if (tamagoStageView != null) {
            tamagoStageView.shutdown();
            tamagoStageView = null;
        }

        setScene(new Scene(new MonsterStageView(facade.loadMonster()), 400, 500));
    }

    private void configureScene() {
        if (facade.isTamagoMode()) {
            tamagoStageView = new TamagoStageView(facade, this);
            setScene(new Scene(tamagoStageView, 400, 500));
            return;
        }

        MonsterState monsterState = facade.loadMonster();
        setScene(new Scene(new MonsterStageView(monsterState), 400, 500));
    }

    private void configureCloseHandler() {
        setOnCloseRequest(event -> {
            if (tamagoStageView != null) {
                tamagoStageView.shutdown();
            }
        });
    }
}
