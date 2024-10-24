package be.neoo.entities;



import be.neoo.entities.compoundID.AdressCustomerPK;
import be.neoo.enums.AddressTypeEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass( AdressCustomerPK.class )
@Table( name = "address_customers" )
public class AdressCustomer implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @ManyToOne
    @JoinColumn( name = "customer", nullable = false )
    @JsonBackReference
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn( name = "address", nullable = false )
    @JsonBackReference
    private Address address;

    @Column( name = "address_type", nullable = false, length = 255 )
    @Enumerated( EnumType.STRING )
    private AddressTypeEnum addressType;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer( Customer customer ) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress( Address address ) {
        this.address = address;
    }

    public AddressTypeEnum getAddressType() {
        return addressType;
    }

    public void setAddressType( AddressTypeEnum addressType ) {
        this.addressType = addressType;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        AdressCustomer that = (AdressCustomer) o;
        return Objects.equals( customer, that.customer ) && Objects.equals( address, that.address )
                && addressType == that.addressType;
    }

    @Override public int hashCode() {
        return Objects.hash( customer, address, addressType );
    }
}
