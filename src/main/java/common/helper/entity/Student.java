package common.helper.entity;

public class Student implements Entity {

    private Long rollNo;
    private String name;
    private Address address;

    public Long getRollNo() {
        return rollNo;
    }

    public void setRollNo(Long rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }
}
