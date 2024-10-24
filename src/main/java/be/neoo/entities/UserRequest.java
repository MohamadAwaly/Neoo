package be.neoo.entities;



import be.neoo.entities.compoundID.UserRequestPK;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass( UserRequestPK.class )
@Table(name = "users_request")
public class UserRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn( name = "employe", nullable = false )
    private Employee employe;

    @Id
    @ManyToOne
    @JoinColumn( name = "request_holiday", nullable = false )
    private RequestHoliday requestHoliday;

    @Column(name = "date_request", nullable = false)
    private Date dateRequest;

    @Column(name = "date_response", nullable = false)
    private  Date dateResponse;

    @Column(name = "approve", nullable = false)
    private Boolean approve;

    public Employee getEmployee() {
        return employe;
    }

    public void setEmployee( Employee employee ) {
        this.employe = employee;
    }

    public RequestHoliday getRequestHoliday() {
        return requestHoliday;
    }

    public void setRequestHoliday( RequestHoliday requestHoliday ) {
        this.requestHoliday = requestHoliday;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest( Date dateRequest ) {
        this.dateRequest = dateRequest;
    }

    public Date getDateResponse() {
        return dateResponse;
    }

    public void setDateResponse( Date dateResponse ) {
        this.dateResponse = dateResponse;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove( Boolean approve ) {
        this.approve = approve;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        UserRequest that = (UserRequest) o;
        return Objects.equals( employe, that.employe ) && Objects.equals( requestHoliday,
                that.requestHoliday ) && Objects.equals( dateRequest, that.dateRequest )
                && Objects.equals( dateResponse, that.dateResponse ) && Objects.equals( approve,
                that.approve );
    }

    @Override public int hashCode() {
        return Objects.hash( employe, requestHoliday, dateRequest, dateResponse, approve );
    }
}
