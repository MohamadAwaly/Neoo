package be.neoo.entities;



import be.neoo.entities.compoundID.OrderSupplierOrderRecevedPK;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass( OrderSupplierOrderRecevedPK.class  )
public class OrderSupplierOrderReceved implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn( name = "order_receved", nullable = false )
    private OrderReceved orderReceved;

    @Id
    @ManyToOne
    @JoinColumn( name = "order_supplier", nullable = false )
    private OrderSupplier orderSupplier;

    public OrderReceved getOrderReceved() {
        return orderReceved;
    }

    public void setOrderReceved( OrderReceved orderReceved ) {
        this.orderReceved = orderReceved;
    }

    public OrderSupplier getOrderSupplier() {
        return orderSupplier;
    }

    public void setOrderSupplier( OrderSupplier orderSupplier ) {
        this.orderSupplier = orderSupplier;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        OrderSupplierOrderReceved that = (OrderSupplierOrderReceved) o;
        return Objects.equals( orderReceved, that.orderReceved ) && Objects.equals( orderSupplier,
                that.orderSupplier );
    }

    @Override public int hashCode() {
        return Objects.hash( orderReceved, orderSupplier );
    }
}
