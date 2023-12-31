package design.pattern.creational;

import common.helper.entity.Employee;
import common.helper.entity.Entity;
import common.helper.entity.Student;

/**
 * {@link SimpleFactory} is used to move the object creation in a separate method.
 */
public interface SimpleFactory {}

class EntitySimpleFactory implements SimpleFactory {
    public static Entity getInstance(String entityName) {

        if (entityName.equals("Employee")) {
            return new Employee();
        } else if (entityName.equals("Student")) {
            return new Student();
        }

        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        Employee employee = (Employee) EntitySimpleFactory.getInstance(Employee.class.getSimpleName());

        Student student = (Student) EntitySimpleFactory.getInstance(Student.class.getSimpleName());

        Employee employee1 = (Employee) EntitySimpleFactory.getInstance("Student");

        System.out.println(employee);
    }
}

