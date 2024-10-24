package be.neoo.entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "request_holiday")
public class RequestHoliday implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "aprobator_message")
    private String aprobatorMessage;

    @Column(name = "begin_date", nullable = false)
    private Date beginDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @ManyToOne
    @JoinColumn( name = "request_type", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private RequestType requestType;

    @OneToMany( mappedBy = "requestHoliday", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
    private List<UserRequest> userRequests;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getAprobatorMessage() {
        return aprobatorMessage;
    }

    public void setAprobatorMessage( String aprobatorMessage ) {
        this.aprobatorMessage = aprobatorMessage;
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

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType( RequestType requestType ) {
        this.requestType = requestType;
    }

    public List<UserRequest> getUserRequests() {
        return userRequests;
    }

    public void setUserRequests( List<UserRequest> userRequests ) {
        this.userRequests = userRequests;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        RequestHoliday that = (RequestHoliday) o;
        return id == that.id && Objects.equals( aprobatorMessage, that.aprobatorMessage )
                && Objects.equals( beginDate, that.beginDate ) && Objects.equals( endDate,
                that.endDate ) && Objects.equals( requestType, that.requestType ) && Objects.equals(
                userRequests, that.userRequests );
    }

    @Override public int hashCode() {
        return Objects.hash( id, aprobatorMessage, beginDate, endDate, requestType, userRequests );
    }
}
