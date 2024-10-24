package be.neoo.entities;



import be.neoo.entities.compoundID.OrdersRecevedOrdersProductsPK;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass( OrdersRecevedOrdersProductsPK.class )
public class OrdersRecevedOrdersProducts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn ( name = "order_product", nullable = false)
    private OrderProduct orderProduct;

    @Id
    @ManyToOne
    @JoinColumn ( name = "order_receved", nullable = false)
    private OrderReceved orderReceved;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct( OrderProduct orderProduct ) {
        this.orderProduct = orderProduct;
    }

    public OrderReceved getOrderReceved() {
        return orderReceved;
    }

    public void setOrderReceved( OrderReceved orderReceved ) {
        this.orderReceved = orderReceved;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        OrdersRecevedOrdersProducts that = (OrdersRecevedOrdersProducts) o;
        return quantity == that.quantity && Objects.equals( orderProduct, that.orderProduct )
                && Objects.equals( orderReceved, that.orderReceved );
    }

    @Override public int hashCode() {
        return Objects.hash( orderProduct, orderReceved, quantity );
    }
}
