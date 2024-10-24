package be.neoo.entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "contracts")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "authorize_day_off", nullable = false)
    private int authorizeDayOff;

    @Column(name = "hourly_salary", nullable = false)
    private long hourlySalary;

    @Column(name = "status")
    private boolean status;

    @OneToMany( mappedBy = "contract", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
    private List<ContractUser> contractUsers;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public int getAuthorizeDayOff() {
        return authorizeDayOff;
    }

    public void setAuthorizeDayOff( int authorizeDayOff ) {
        this.authorizeDayOff = authorizeDayOff;
    }

    public long getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary( long hourlySalary ) {
        this.hourlySalary = hourlySalary;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus( boolean status ) {
        this.status = status;
    }

    public List<ContractUser> getContractUsers() {
        return contractUsers;
    }

    public void setContractUsers( List<ContractUser> contractUsers ) {
        this.contractUsers = contractUsers;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        Contract contract = (Contract) o;
        return id == contract.id && authorizeDayOff == contract.authorizeDayOff && hourlySalary == contract.hourlySalary
                && status == contract.status && Objects.equals( contractUsers, contract.contractUsers );
    }

    @Override public int hashCode() {
        return Objects.hash( id, authorizeDayOff, hourlySalary, status, contractUsers );
    }
}
