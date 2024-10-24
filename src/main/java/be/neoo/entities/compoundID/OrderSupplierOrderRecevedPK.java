package be.neoo.entities.compoundID;

import java.io.Serializable;

public class OrderSupplierOrderRecevedPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private int orderReceved;
    private int orderSupplier;

    public OrderSupplierOrderRecevedPK ( int orderReceved, int orderSupplier){
        this.orderReceved = orderReceved;
        this.orderSupplier = orderSupplier;
    }

    public int getOrderReceved() {
        return orderReceved;
    }

    public void setOrderReceved( int orderReceved ) {
        this.orderReceved = orderReceved;
    }

    public int getOrderSupplier() {
        return orderSupplier;
    }

    public void setOrderSupplier( int orderSupplier ) {
        this.orderSupplier = orderSupplier;
    }
}
