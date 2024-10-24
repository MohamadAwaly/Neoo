package be.neoo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table( name = "supplier" )
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", nullable = false )
    private              int  id;

    @Column( name = "name", nullable = false, length = 60 )
    private String name;

    @ManyToOne
    @JoinColumn( name = "address", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private Address address;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress( Address address ) {
        this.address = address;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        Supplier supplier = (Supplier) o;
        return id == supplier.id && Objects.equals( name, supplier.name ) && Objects.equals( address,
                supplier.address );
    }

    @Override public int hashCode() {
        return Objects.hash( id, name, address );
    }
}
