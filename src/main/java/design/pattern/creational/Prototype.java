package design.pattern.creational;

import common.helper.unit.GameUnit;

/**
 * {@link Prototype} will be used when the object creation is costly or not possible, so we implement {@link Cloneable}
 *          as well and clone the object
 * */
public interface Prototype extends Cloneable {}

class SwordsmanGameUnit extends GameUnit implements Prototype {
    private String unitState = "Idle";

    public void setUnitState(String unitState) {
        this.unitState = unitState;
    }

    @Override
    public void reset() {
        this.unitState = "Idle";
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + unitState + " @ " + super.position;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        SwordsmanGameUnit swordsmanGameUnit = new SwordsmanGameUnit();
        swordsmanGameUnit.setUnitState("Attacking");
        swordsmanGameUnit.moveUnit(10, -1, 5);

        SwordsmanGameUnit swordsmanGameUnitClone = (SwordsmanGameUnit) swordsmanGameUnit.clone();
        swordsmanGameUnitClone.initialize();
        swordsmanGameUnitClone.moveUnit(2, 1, -4);

        System.out.println("Original" + swordsmanGameUnit);
        System.out.println("Cloned" + swordsmanGameUnitClone);
    }

    @Override
    public String getUnitInfo(int currentHitPoints, int extraAttack, int extraDefence) {
        throw new UnsupportedOperationException();
    }
}
