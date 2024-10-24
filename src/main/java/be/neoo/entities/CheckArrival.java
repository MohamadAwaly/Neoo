package be.neoo.entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "check_arrival")
public class CheckArrival implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "arrival_time", nullable = false)
    private Date arrivalTime;

    @Column(name = "departure_time")
    private Date departureTime;

    @ManyToOne
    @JoinColumn( name = "employe", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private Employee employee;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime( Date arrivalTime ) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime( Date departureTime ) {
        this.departureTime = departureTime;
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
        CheckArrival that = (CheckArrival) o;
        return id == that.id && Objects.equals( arrivalTime, that.arrivalTime ) && Objects.equals(
                departureTime, that.departureTime ) && Objects.equals( employee, that.employee );
    }

    @Override public int hashCode() {
        return Objects.hash( id, arrivalTime, departureTime, employee );
    }
}
