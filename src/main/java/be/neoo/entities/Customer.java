package be.neoo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NamedQueries( value = {
//        @NamedQuery( name = "customer.list",
//                query = "select c from Customer c" ),
        @NamedQuery( name = "customer.list",
                query = "SELECT c FROM Customer c " +
                        "JOIN FETCH c.role r " +
                        "LEFT JOIN FETCH c.addressCustomers ac " +
                        "LEFT JOIN FETCH ac.address " ),
        @NamedQuery(name = "customer.login",
        query = "select c from Customer c where c.login = :login")
} )

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "customers" )
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", nullable = false )
    private int id;

    @Column( name = "login", nullable = false, length = 60 )
    private String login;

    @Column( name = "last_name", nullable = false, length = 60 )
    private String lastName;
    @Column( name = "first_name", nullable = false, length = 60 )
    private String firstName;

    @Column( name = "birthday", nullable = true )
    private Date birthday;

    @Column( name = "inscription_date", nullable = false )
    private Date inscriptionDate;

    @Column( name = "vat", nullable = true )
    private String vat;

    @Column( name = "mail", nullable = false, length = 255 )
    private String mail;

    @Column( name = "password", nullable = false, length = 60 )
    private String password;

    @Column( name = "status" )
    private String status;

    @Column( name = "actif" )
    private boolean actif;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "role", referencedColumnName = "id", nullable = false)
    private Role role;

    @OneToMany( mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
    @JsonManagedReference
    private List<AddressCustomer> addressCustomers = new ArrayList<>();
    @JsonManagedReference
    @OneToMany( mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
    private List<Order> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(Date inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<AddressCustomer> getAddressCustomers() {
        return addressCustomers;
    }

    public void setAddressCustomers(List<AddressCustomer> addressCustomers) {
        this.addressCustomers = addressCustomers;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
