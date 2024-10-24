package be.neoo.entities;



import be.neoo.entities.compoundID.ContractUserPK;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass( ContractUserPK.class )
@Table( name = "contract_users" )
public class ContractUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn( name = "employe", nullable = false )
    private Employee employe;

    @Id
    @ManyToOne
    @JoinColumn( name = "contract", nullable = false )
    private Contract contract;

    @Column (name = "begin_date", nullable = false)
    private Date beginDate;

    @Column( name = "end_date", nullable = false)
    private Date endDate;

    public Employee getEmploye() {
        return employe;
    }

    public void setEmploye( Employee employe ) {
        this.employe = employe;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract( Contract contract ) {
        this.contract = contract;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate( Date beginDate ) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate( Date endDate ) {
        this.endDate = endDate;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        ContractUser that = (ContractUser) o;
        return Objects.equals( employe, that.employe ) && Objects.equals( contract, that.contract )
                && Objects.equals( beginDate, that.beginDate ) && Objects.equals( endDate,
                that.endDate );
    }

    @Override public int hashCode() {
        return Objects.hash( employe, contract, beginDate, endDate );
    }
}
