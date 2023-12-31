package common.helper.entity;

public class Employee implements Entity {

    private Long id;
    private String name;
    private Address address;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee ID      : " + getId() + "\n" +
                "Employee Name    : " + getName() + "\n" +
                "Employee Address : " + getAddress();
    }

    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }
}
