package common.helper.image;

import common.helper.creational.Poolable;
import common.helper.unit.Point3D;

public interface Image extends Poolable {
    void setPoint3D(Point3D point3D);

    Point3D getPoint3D();

    void render();
}
