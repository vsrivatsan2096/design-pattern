package common.helper.entity;

public record Address(String street, String city, String country) implements Entity {

    @Override
    public String toString() {
        return street() + ", " +
                city() + ", " +
                country();
    }

    @Override
    public String getEntityName() {
        return this.getClass().getSimpleName();
    }
}
