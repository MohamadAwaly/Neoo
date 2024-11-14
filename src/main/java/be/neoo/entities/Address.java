package be.neoo.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "box")
    private String box;
//, insertable = false, updatable = false
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city", referencedColumnName = "id", nullable = false)
    private City city;

//    @OneToMany(mappedBy = "address", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private List<AdressCustomer> adressCustomers;
//
//    @OneToMany(mappedBy = "address", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private List<AddressEmploye> addressEmployes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

//    public List<AddressEmploye> getAddressEmployes() {
//        return addressEmployes;
//    }
//
//    public void setAddressEmployes(List<AddressEmploye> addressEmployes) {
//        this.addressEmployes = addressEmployes;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && number == address.number && Objects.equals(street, address.street) && Objects.equals(box, address.box) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number, box, city);
    }
}
