package be.neoo.entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "reminder_type")
public class ReminderType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "type", nullable = false, length = 60)
    private String type;

    @OneToMany( mappedBy = "reminderType", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY )
    private List<PayementReminder> payementReminders;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        ReminderType that = (ReminderType) o;
        return id == that.id && Objects.equals( type, that.type ) && Objects.equals( payementReminders,
                that.payementReminders );
    }

    @Override public int hashCode() {
        return Objects.hash( id, type, payementReminders );
    }
}
