package be.neoo.entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order_receved")
public class OrderReceved implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "unite_price", nullable = false)
    private long unitePrice;

    @Column(name = "deliver_date", nullable = false)
    private Date deliverDate;

    @ManyToOne
    @JoinColumn( name = "warehouse", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private Warehouse warehouse;

    @OneToMany( mappedBy = "orderReceved", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
    private List<OrderSupplierOrderReceved> ordersSuppliersOrderReceveds;

    @OneToMany( mappedBy = "orderReceved", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
    private List<OrderSpent> orderSpents;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }

    public long getUnitePrice() {
        return unitePrice;
    }

    public void setUnitePrice( long unitePrice ) {
        this.unitePrice = unitePrice;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate( Date deliverDate ) {
        this.deliverDate = deliverDate;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse( Warehouse warehouse ) {
        this.warehouse = warehouse;
    }

    public List<OrderSupplierOrderReceved> getOrdersSuppliersOrderReceveds() {
        return ordersSuppliersOrderReceveds;
    }

    public void setOrdersSuppliersOrderReceveds(
            List<OrderSupplierOrderReceved> ordersSuppliersOrderReceveds ) {
        this.ordersSuppliersOrderReceveds = ordersSuppliersOrderReceveds;
    }

    public List<OrderSpent> getOrderSpents() {
        return orderSpents;
    }

    public void setOrderSpents( List<OrderSpent> orderSpents ) {
        this.orderSpents = orderSpents;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        OrderReceved that = (OrderReceved) o;
        return id == that.id && quantity == that.quantity && unitePrice == that.unitePrice && Objects.equals(
                deliverDate, that.deliverDate ) && Objects.equals( warehouse, that.warehouse )
                && Objects.equals( ordersSuppliersOrderReceveds, that.ordersSuppliersOrderReceveds )
                && Objects.equals( orderSpents, that.orderSpents );
    }

    @Override public int hashCode() {
        return Objects.hash( id, quantity, unitePrice, deliverDate, warehouse, ordersSuppliersOrderReceveds,
                orderSpents );
    }
}
