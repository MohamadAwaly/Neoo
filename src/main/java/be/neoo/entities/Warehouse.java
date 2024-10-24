package be.neoo.entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "warehouse")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;
    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn( name = "address", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private Address address;

    @OneToMany( mappedBy = "warehouse", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
    private List<WareHouseProduct> wareHouseProducts;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus( boolean status ) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress( Address address ) {
        this.address = address;
    }

    public List<WareHouseProduct> getWareHouseProducts() {
        return wareHouseProducts;
    }

    public void setWareHouseProducts( List<WareHouseProduct> wareHouseProducts ) {
        this.wareHouseProducts = wareHouseProducts;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        Warehouse warehouse = (Warehouse) o;
        return id == warehouse.id && status == warehouse.status && Objects.equals( name, warehouse.name )
                && Objects.equals( address, warehouse.address ) && Objects.equals( wareHouseProducts,
                warehouse.wareHouseProducts );
    }

    @Override public int hashCode() {
        return Objects.hash( id, name, status, address, wareHouseProducts );
    }
}
