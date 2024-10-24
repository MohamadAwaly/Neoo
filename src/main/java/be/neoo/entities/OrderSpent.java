package be.neoo.entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "order_spent")
public class OrderSpent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "amount", nullable = false)
    private long amount;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn( name = "order_receved", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private OrderReceved orderReceved;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount( long amount ) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public OrderReceved getOrderReceved() {
        return orderReceved;
    }

    public void setOrderReceved( OrderReceved orderReceved ) {
        this.orderReceved = orderReceved;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        OrderSpent that = (OrderSpent) o;
        return id == that.id && amount == that.amount && Objects.equals( date, that.date )
                && Objects.equals( orderReceved, that.orderReceved );
    }

    @Override public int hashCode() {
        return Objects.hash( id, amount, date, orderReceved );
    }
}
