package be.neoo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table( name = "cashier" )
public class Cashier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", nullable = false )
    private              int  id;

    @Column( name = "date", nullable = false )
    private Date date;

    @Column( name = "amount", nullable = false )
    private long amount;

    @Column( name = "description" )
    private String descirption;

    @ManyToOne
    @JoinColumn( name = "employe", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private Employee employee;

    @ManyToOne
    @JoinColumn( name = "payement_type", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private PayementType payementType;

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

    public String getDescirption() {
        return descirption;
    }

    public void setDescirption( String descirption ) {
        this.descirption = descirption;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee( Employee employee ) {
        this.employee = employee;
    }

    public PayementType getPayementType() {
        return payementType;
    }

    public void setPayementType( PayementType payementType ) {
        this.payementType = payementType;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        Cashier cashier = (Cashier) o;
        return id == cashier.id && amount == cashier.amount && Objects.equals( date, cashier.date )
                && Objects.equals( descirption, cashier.descirption ) && Objects.equals( employee,
                cashier.employee ) && Objects.equals( payementType, cashier.payementType );
    }

    @Override public int hashCode() {
        return Objects.hash( id, date, amount, descirption, employee, payementType );
    }
}
