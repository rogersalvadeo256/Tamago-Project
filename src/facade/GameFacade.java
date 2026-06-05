package facade;

import java.nio.file.Path;

import model.GlobalSettings;
import model.MonsterState;
import model.TamagoState;
import repository.XmlRepository;

public class GameFacade {

    private static final String GLOBAL_FILE = "Global.xml";
    private static final String TAMAGO_FILE = "Tamago.xml";
    private static final String MONSTER_FILE = "Monster.xml";

    private final XmlRepository xmlRepository;

    public GameFacade(Path dataDirectory) {
        this.xmlRepository = new XmlRepository(dataDirectory);
    }

    public void bootstrap() {
        GlobalSettings settings = loadGlobal();

        if (settings.isFirstTime()) {
            TamagoState tamago = createDefaultTamago();
            saveTamago(tamago);

            MonsterState monster = createDefaultMonster();
            saveMonster(monster);

            settings.setFirstTime(false);
            settings.setMethod(true);
            saveGlobal(settings);
            return;
        }

        if (!hasMonsterName()) {
            saveMonster(createDefaultMonster());
        }
    }

    public boolean isTamagoMode() {
        return loadGlobal().isMethod();
    }

    public TamagoState loadTamago() {
        return xmlRepository.read(TAMAGO_FILE, TamagoState.class, this::createDefaultTamago);
    }

    public void saveTamago(TamagoState tamagoState) {
        xmlRepository.write(TAMAGO_FILE, tamagoState, TamagoState.class);
    }

    public MonsterState loadMonster() {
        return xmlRepository.read(MONSTER_FILE, MonsterState.class, this::createDefaultMonster);
    }

    public void saveMonster(MonsterState monsterState) {
        xmlRepository.write(MONSTER_FILE, monsterState, MonsterState.class);
    }

    public void switchToMonsterMode(String monsterName) {
        MonsterState monster = loadMonster();
        monster.setName(monsterName);
        saveMonster(monster);

        GlobalSettings settings = loadGlobal();
        settings.setMethod(false);
        settings.setFirstTime(false);
        saveGlobal(settings);
    }

    private boolean hasMonsterName() {
        MonsterState monster = loadMonster();
        return monster.getName() != null && !monster.getName().trim().isEmpty();
    }

    private GlobalSettings loadGlobal() {
        return xmlRepository.read(GLOBAL_FILE, GlobalSettings.class, GlobalSettings::new);
    }

    private void saveGlobal(GlobalSettings globalSettings) {
        xmlRepository.write(GLOBAL_FILE, globalSettings, GlobalSettings.class);
    }

    private TamagoState createDefaultTamago() {
        TamagoState tamago = new TamagoState();
        tamago.setTime(1800);
        tamago.setWarmth(50);
        tamago.setHappiness(50);
        return tamago;
    }

    private MonsterState createDefaultMonster() {
        MonsterState monster = new MonsterState();
        monster.setHp(250);
        monster.setFood(100);
        monster.setWater(100);
        monster.setDiscipline(100);
        monster.setAge(1);
        return monster;
    }
}
