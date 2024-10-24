package be.neoo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table( name = "todo" )
public class Todo implements Serializable {

    private static final long    serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", nullable = false )
    private              int     id;
    @Column( name = "description", nullable = false )
    private              String  description;
    @Column( name = "done", nullable = false )
    private              Boolean done;

    @ManyToOne
    @JoinColumn( name = "employe", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private Employee employee;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone( Boolean done ) {
        this.done = done;
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
        Todo todo = (Todo) o;
        return id == todo.id && Objects.equals( description, todo.description ) && Objects.equals( done,
                todo.done ) && Objects.equals( employee, todo.employee );
    }

    @Override public int hashCode() {
        return Objects.hash( id, description, done, employee );
    }
}
