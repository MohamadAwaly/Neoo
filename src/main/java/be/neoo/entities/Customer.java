package be.neoo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NamedQueries( value = {
        @NamedQuery( name = "customer.list",
                query = "select c from Customer c" ),
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
    private List<AdressCustomer> adressCustomers;

    @OneToMany( mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
    private List<Order> orders;

}
