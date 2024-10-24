package be.neoo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table( name = "spent" )
public class Spent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", nullable = false )
    private              int  id;

    @Column( name = "date", nullable = false )
    private Date date;
    @Column( name = "amount", nullable = false )
    private long amount;

    @Column( name = "description", nullable = false, length = 100 )
    private String description;

    @ManyToOne
    @JoinColumn( name = "employe", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private Employee employee;

    @ManyToOne
    @JoinColumn( name = "spent_type", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private SpentType spentType;

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

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee( Employee employee ) {
        this.employee = employee;
    }

    public SpentType getSpentType() {
        return spentType;
    }

    public void setSpentType( SpentType spentType ) {
        this.spentType = spentType;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        Spent spent = (Spent) o;
        return id == spent.id && amount == spent.amount && Objects.equals( date, spent.date )
                && Objects.equals( description, spent.description ) && Objects.equals( employee,
                spent.employee ) && Objects.equals( spentType, spent.spentType );
    }

    @Override public int hashCode() {
        return Objects.hash( id, date, amount, description, employee, spentType );
    }
}
