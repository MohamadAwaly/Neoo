package be.neoo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table( name = "order_supplier" )
public class OrderSupplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", nullable = false )
    private              int  id;

    @Column( name = "order_date", nullable = false )
    private Date orderDate;

    @Column( name = "payed", nullable = false )
    private Boolean payed;

    @Column( name = "estimated_date_deliver", nullable = false )
    private Date estimaredDateDeliver;

    @Column( name = "status", nullable = false )
    private Boolean status;

    @Column( name = "cost_price", nullable = false )
    private long costPrice;

    @Column( name = "total_quantity" )
    private int totalQuantity;

    @ManyToOne
    @JoinColumn( name = "employe", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private Employee employee;

    @ManyToOne
    @JoinColumn( name = "supplier", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private Supplier supplier;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate( Date orderDate ) {
        this.orderDate = orderDate;
    }

    public Boolean getPayed() {
        return payed;
    }

    public void setPayed( Boolean payed ) {
        this.payed = payed;
    }

    public Date getEstimaredDateDeliver() {
        return estimaredDateDeliver;
    }

    public void setEstimaredDateDeliver( Date estimaredDateDeliver ) {
        this.estimaredDateDeliver = estimaredDateDeliver;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus( Boolean status ) {
        this.status = status;
    }

    public long getCostPrice() {
        return costPrice;
    }

    public void setCostPrice( long costPrice ) {
        this.costPrice = costPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity( int totalQuantity ) {
        this.totalQuantity = totalQuantity;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee( Employee employee ) {
        this.employee = employee;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier( Supplier supplier ) {
        this.supplier = supplier;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        OrderSupplier that = (OrderSupplier) o;
        return id == that.id && costPrice == that.costPrice && totalQuantity == that.totalQuantity
                && Objects.equals( orderDate, that.orderDate ) && Objects.equals( payed, that.payed )
                && Objects.equals( estimaredDateDeliver, that.estimaredDateDeliver ) && Objects.equals(
                status, that.status ) && Objects.equals( employee, that.employee ) && Objects.equals(
                supplier, that.supplier );
    }

    @Override public int hashCode() {
        return Objects.hash( id, orderDate, payed, estimaredDateDeliver, status, costPrice, totalQuantity, employee,
                supplier );
    }
}
