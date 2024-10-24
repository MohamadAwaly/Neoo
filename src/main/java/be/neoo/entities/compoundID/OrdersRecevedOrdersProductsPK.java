package be.neoo.entities.compoundID;

import java.io.Serializable;

public class OrdersRecevedOrdersProductsPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private int orderProduct;
    private int orderReceved;

    public OrdersRecevedOrdersProductsPK( int orderProduct, int orderReceved ) {
        this.orderProduct = orderProduct;
        this.orderReceved = orderReceved;
    }

    public int getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct( int orderProduct ) {
        this.orderProduct = orderProduct;
    }

    public int getOrderReceved() {
        return orderReceved;
    }

    public void setOrderReceved( int orderReceved ) {
        this.orderReceved = orderReceved;
    }
}
