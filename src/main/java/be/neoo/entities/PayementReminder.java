package be.neoo.entities;

import be.neoo.entities.compoundID.PayementReminderPK;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass( PayementReminderPK.class )
@Table( name = "payement_reminder" )
public class PayementReminder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn( name = "orders", nullable = false )
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn( name = "reminder_type", nullable = false )
    private ReminderType reminderType;

    @Column( name = "date", nullable = false )
    private Date date;

    public Order getOrder() {
        return order;
    }

    public void setOrder( Order order ) {
        this.order = order;
    }

    public ReminderType getReminderType() {
        return reminderType;
    }

    public void setReminderType( ReminderType reminderType ) {
        this.reminderType = reminderType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        PayementReminder that = (PayementReminder) o;
        return Objects.equals( order, that.order ) && Objects.equals( reminderType, that.reminderType )
                && Objects.equals( date, that.date );
    }

    @Override public int hashCode() {
        return Objects.hash( order, reminderType, date );
    }
}
