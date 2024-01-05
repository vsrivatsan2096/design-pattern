package common.helper.order;

public class Order {
    private String name;
    protected Integer quantity;
    protected Double price;
    protected Double totalCost;
    private Double discount = 0.00;
    private Double shoppingCost = 100.00;

    public Order(String name, Integer quantity, Double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;

        this.totalCost = this.price * this.quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getShoppingCost() {
        return shoppingCost;
    }

    public void setShoppingCost(Double shoppingCost) {
        this.shoppingCost = shoppingCost;
    }

    public Double getTotalCost() {
        return totalCost + shoppingCost - discount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalCost=" + totalCost +
                ", discount=" + discount +
                ", shoppingCost=" + shoppingCost +
                '}';
    }
}
