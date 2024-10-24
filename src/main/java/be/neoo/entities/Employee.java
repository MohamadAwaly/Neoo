package be.neoo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@NamedQueries( value = {
        @NamedQuery( name = "employe.find", query = "select e from Employee e "
                + "where e.login= :login " ),
        @NamedQuery( name = "employe.findAll", query = "select distinct e from Employee e " +
                "join fetch e.role r " +
                "join fetch r.rolePermissions rp " +
                "join fetch rp.permission p"),
        @NamedQuery( name = "employe.findByLogin", query = "select e from Employee e " +
                "where e.login = :login")
} )

@Entity
@Table( name = "employees" )
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", nullable = false )
    private int id;

    @Column( name = "login", nullable = false, length = 60, unique = true )
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

    @Column( name = "password", nullable = false, length = 255 )
    private String password;

    @Column( name = "status" )
    private String status;

    @Column( name = "actif" )
    private boolean actif;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "role", referencedColumnName = "id", nullable = false)
    private Role role;

    @OneToMany( mappedBy = "employe", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
    @JsonManagedReference
    private List<AddressEmploye> addressEmployes;

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

    public List<AddressEmploye> getAddressEmployes() {
        return addressEmployes;
    }

    public void setAddressEmployes(List<AddressEmploye> addressEmployes) {
        this.addressEmployes = addressEmployes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && actif == employee.actif && Objects.equals(login, employee.login) && Objects.equals(lastName, employee.lastName) && Objects.equals(firstName, employee.firstName) && Objects.equals(birthday, employee.birthday) && Objects.equals(inscriptionDate, employee.inscriptionDate) && Objects.equals(vat, employee.vat) && Objects.equals(mail, employee.mail) && Objects.equals(password, employee.password) && Objects.equals(status, employee.status) && Objects.equals(role, employee.role) && Objects.equals(addressEmployes, employee.addressEmployes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, lastName, firstName, birthday, inscriptionDate, vat, mail, password, status, actif, role, addressEmployes);
    }
}
