package be.neoo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table( name = "payement_order" )
public class PayementOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", nullable = false )
    private              int  id;

    @Column( name = "date", nullable = false )
    private Date date;

    @Column( name = "amount", nullable = false )
    private long amount;
    @JsonBackReference
    @ManyToOne
    @JoinColumn( name = "id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private Order order;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount( long amount ) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder( Order order ) {
        this.order = order;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        PayementOrder that = (PayementOrder) o;
        return id == that.id && amount == that.amount && Objects.equals( date, that.date )
                && Objects.equals( order, that.order );
    }

    @Override public int hashCode() {
        return Objects.hash( id, date, amount, order );
    }
}
