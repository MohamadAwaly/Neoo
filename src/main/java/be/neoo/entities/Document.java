package be.neoo.entities;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "documents")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn( name = "id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false )
    private DocumentType documentType;

    @OneToMany( mappedBy = "document", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
    private List<OrderDocument> orderDocuments;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber( int number ) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType( DocumentType documentType ) {
        this.documentType = documentType;
    }

    public List<OrderDocument> getOrderDocuments() {
        return orderDocuments;
    }

    public void setOrderDocuments( List<OrderDocument> orderDocuments ) {
        this.orderDocuments = orderDocuments;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        Document document = (Document) o;
        return id == document.id && number == document.number && Objects.equals( date, document.date )
                && Objects.equals( documentType, document.documentType ) && Objects.equals(
                orderDocuments, document.orderDocuments );
    }

    @Override public int hashCode() {
        return Objects.hash( id, number, date, documentType, orderDocuments );
    }
}
