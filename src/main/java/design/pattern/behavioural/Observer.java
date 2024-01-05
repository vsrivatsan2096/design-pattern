package design.pattern.behavioural;


import common.helper.order.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link Observer} is used to define one-to-many dependency between objects which are interested in the state of object
 * */
public interface Observer<O> {
    void updated(O o);
}

class DiscountObserver implements Observer<Order> {

    @Override
    public void updated(Order order) {
        if (order.getTotalCost() >= 500) {
            order.setDiscount(50.0);
        } else if (order.getTotalCost() >= 200) {
            order.setDiscount(10.00);
        }
    }
}

class ShoppingCostObserver implements Observer<Order> {

    @Override
    public void updated(Order order) {
        if (order.getTotalCost() >= 500) {
            order.setShoppingCost(10.0);
        } else if (order.getTotalCost() >= 200) {
            order.setShoppingCost(20.00);
        } else {
            order.setShoppingCost(50.0);
        }
    }
}

class ObservedOrder extends Order {
    private final List<Observer<Order>> orderObserverList = new ArrayList<>();

    public ObservedOrder(String name, Integer quantity, Double price) {
        super(name, quantity, price);
    }

    public void attachObserver(Observer<Order> orderObserver) {
        orderObserverList.add(orderObserver);
    }

    public void detachObserver(Observer<Order> orderObserver) {
        orderObserverList.remove(orderObserver);
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
        this.totalCost = this.quantity * this.price;

        orderObserverList.forEach(o-> o.updated(this));
    }

    public static void main(String[] args) {
        ObservedOrder order = new ObservedOrder("Apple", 5, 45.89);

        DiscountObserver discountObserver = new DiscountObserver();
        ShoppingCostObserver shoppingCostObserver = new ShoppingCostObserver();

        order.attachObserver(discountObserver);
        order.attachObserver(shoppingCostObserver);

        System.out.println(order);

        order.addQuantity(5);

        System.out.println(order);

        order.detachObserver(shoppingCostObserver);

        order.addQuantity(20);

        System.out.println(order);
    }
}
