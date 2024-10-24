package be.neoo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import be.neoo.entities.compoundID.AdressEmployePK;
import be.neoo.enums.AddressTypeEnum;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(AdressEmployePK.class)
@Table(name = "address_employees")
public class AddressEmploye implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "employe", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Employee employe;

    @Id
    @ManyToOne
    @JoinColumn(name = "address", referencedColumnName = "id", nullable = false)
//    @JsonBackReference
    private Address address;

    @Column(name = "address_type", nullable = false, length = 255)
    @Enumerated(EnumType.STRING)
    private AddressTypeEnum addressType;

    public Employee getEmploye() {
        return employe;
    }

    public void setEmploye(Employee employe) {
        this.employe = employe;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public AddressTypeEnum getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressTypeEnum addressType) {
        this.addressType = addressType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AddressEmploye that = (AddressEmploye) o;
        return Objects.equals(employe, that.employe) && Objects.equals(address, that.address)
                && addressType == that.addressType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employe, address, addressType);
    }
}
