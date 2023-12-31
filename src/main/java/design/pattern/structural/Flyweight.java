package design.pattern.structural;

import common.helper.unit.Unit;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Flyweight} is used when we can split the object as intrinsic (shared by all instances) and
 *              extrinsic (context based values which will be taken care by the client) values
 * */
public interface Flyweight {}

interface ArcherUnit extends Unit {
    String getUnitInfo(int currentHitPoints, int extraAttack, int extraDefence);
}

class ArcherFlyweightUnit implements Flyweight, ArcherUnit {
    private final int maxHitPoints;
    private final int baseAttack;
    private final int baseDefence;

    public ArcherFlyweightUnit(int maxHitPoints, int baseAttack, int baseDefence) {
        this.maxHitPoints = maxHitPoints;
        this.baseAttack = baseAttack;
        this.baseDefence = baseDefence;
    }

    @Override
    public String getUnitInfo(int currentHitPoints, int extraAttack, int extraDefence) {
        return "Archer unit" +
                " " +
                "{" +
                "HP :" +
                currentHitPoints +
                " / " +
                maxHitPoints +
                ", " +
                "ATK :" +
                baseAttack +
                " + " +
                extraAttack +
                ", " +
                "DEF :" +
                baseDefence +
                " + " +
                extraDefence +
                "}";
    }
}

class CrossbowFlyweightUnit implements Flyweight, ArcherUnit {
    private final int maxHitPoints;
    private final int baseAttack;
    private final int baseDefence;

    public CrossbowFlyweightUnit(int maxHitPoints, int baseAttack, int baseDefence) {
        this.maxHitPoints = maxHitPoints;
        this.baseAttack = baseAttack;
        this.baseDefence = baseDefence;
    }

    @Override
    public String getUnitInfo(int currentHitPoints, int extraAttack, int extraDefence) {
        return "Crossbow unit" +
                " " +
                "{" +
                "HP :" +
                currentHitPoints +
                " / " +
                maxHitPoints +
                ", " +
                "ATK :" +
                baseAttack +
                " + " +
                extraAttack +
                ", " +
                "DEF :" +
                baseDefence +
                " + " +
                extraDefence +
                "}";
    }
}

class SpecialArcherCommanderUnit implements ArcherUnit {
    private final int exp;

    SpecialArcherCommanderUnit(int exp) {
        this.exp = exp;
    }
    @Override
    public String getUnitInfo(int currentHitPoints, int extraAttack, int extraDefence) {
        return "Archer commander {" + "Exp :" + exp + " years" +"}";
    }
}

class ArcherFlyweightUnitFactory {
    enum ArcherType { DEFAULT, CROSSBOW, COMMANDER }

    private final Map<ArcherType, ArcherUnit> flyweightMap = new HashMap<>();

    private ArcherFlyweightUnitFactory() {
        flyweightMap.put(ArcherType.DEFAULT,
                new ArcherFlyweightUnit(100, 6, 4));
        flyweightMap.put(ArcherType.CROSSBOW,
                new CrossbowFlyweightUnit(140, 10, 8));
    }

    private static final ArcherFlyweightUnitFactory ARCHER_FLYWEIGHT_UNIT_FACTORY = new ArcherFlyweightUnitFactory();

    public static ArcherFlyweightUnitFactory getInstance() {
        return ARCHER_FLYWEIGHT_UNIT_FACTORY;
    }

    public ArcherUnit getArcherFlyweightUnit(ArcherType archerType) {
        if (ArcherType.COMMANDER == archerType) throw new UnsupportedOperationException();

        return flyweightMap.get(archerType);
    }

    public ArcherUnit getCommanderArcherUnit() {
        return new SpecialArcherCommanderUnit(15);
    }

    public static void main(String[] args) {
        ArcherFlyweightUnitFactory archerFlyweightUnitFactory = ArcherFlyweightUnitFactory.getInstance();

        ArcherUnit archerUnit1 = archerFlyweightUnitFactory.getArcherFlyweightUnit(ArcherType.DEFAULT);
        ArcherUnit archerUnit2 = archerFlyweightUnitFactory.getArcherFlyweightUnit(ArcherType.DEFAULT);


        ArcherUnit crossbowUnit1 = archerFlyweightUnitFactory.getArcherFlyweightUnit(ArcherType.CROSSBOW);
        ArcherUnit crossbowUnit2 = archerFlyweightUnitFactory.getArcherFlyweightUnit(ArcherType.CROSSBOW);

        ArcherUnit commanderUnit = archerFlyweightUnitFactory.getCommanderArcherUnit();

        System.out.println(archerUnit1.getUnitInfo(60, 3, 3));
        System.out.println(archerUnit2.getUnitInfo(80, 6, 1));

        System.out.println(crossbowUnit1.getUnitInfo(70, 3, 3));
        System.out.println(crossbowUnit2.getUnitInfo(90, 6, 1));

        System.out.println(commanderUnit.getUnitInfo(60, 3, 3));
    }
}