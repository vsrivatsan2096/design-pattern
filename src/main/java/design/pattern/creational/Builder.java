package design.pattern.creational;

import common.helper.entity.Address;
import common.helper.entity.Employee;
import common.helper.entity.Entity;

/**
 * {@link Builder} is used when the constructor is complex or object creation has multiple steps
 * */
public interface Builder {
    Entity build();
}

class EmployeeBuilder implements Builder {
    private Long employeeId;
    private String employeeName;
    private String streetName;
    private String cityName;
    private String countryName;

    private static EmployeeBuilder employeeBuilder;

    public static EmployeeBuilder getBuilder() {
        if (employeeBuilder == null) {
            employeeBuilder = new EmployeeBuilder();
        }

        return employeeBuilder;
    }

    public EmployeeBuilder withEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public EmployeeBuilder withEmployeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }

    public EmployeeBuilder withStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public EmployeeBuilder withCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public EmployeeBuilder withCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    @Override
    public Employee build() {
        Employee employee = new Employee();

        employee.setId(employeeId);
        employee.setName(employeeName);;
        employee.setAddress(new Address(streetName, cityName, countryName));

        return employee;
    }

    public static void main(String[] args) {
        Employee employee = EmployeeBuilder.getBuilder()
                .withEmployeeId(10112L)
                .withEmployeeName("Srivatsan")
                .withCityName("Trichy")
                .withCountryName("India")
                .withStreetName("Srinivasa North Street")
                .build();

        System.out.println(employee);
    }
}