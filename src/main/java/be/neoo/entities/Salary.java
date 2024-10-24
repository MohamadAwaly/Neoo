package be.neoo.entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "salay")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "amount", nullable = false)
    private long amount;

    @ManyToOne
    @JoinColumn( name = "employe", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private Employee employee;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee( Employee employee ) {
        this.employee = employee;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        Salary salary = (Salary) o;
        return id == salary.id && amount == salary.amount && Objects.equals( date, salary.date )
                && Objects.equals( employee, salary.employee );
    }

    @Override public int hashCode() {
        return Objects.hash( id, date, amount, employee );
    }
}
