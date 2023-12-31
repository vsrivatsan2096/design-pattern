package common.helper.unit;


abstract public class GameUnit implements Unit, Cloneable {
    protected Point3D position = Point3D.getStartingPoint();

    @Override
    public GameUnit clone() throws CloneNotSupportedException {
        return (GameUnit) super.clone();
    }

    protected void initialize() {
        this.position = Point3D.getStartingPoint();

        reset();
    }

    public void moveUnit(int x, int y, int z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public abstract void reset();
}


