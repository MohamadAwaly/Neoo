package be.neoo.entities;



import be.neoo.entities.compoundID.ProductDocumentRmaPK;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass( ProductDocumentRmaPK.class )
public class ProductDocumentRma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn( name = "product", nullable = false )
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn( name = "document", nullable = false )
    private Document document;

    @Id
    @ManyToOne
    @JoinColumn( name = "rma", nullable = false )
    private Rma rma;

    @Column( name = "date", nullable = false )
    private Date date;

    public Product getProduct() {
        return product;
    }

    public void setProduct( Product product ) {
        this.product = product;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument( Document document ) {
        this.document = document;
    }

    public Rma getRma() {
        return rma;
    }

    public void setRma( Rma rma ) {
        this.rma = rma;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        ProductDocumentRma that = (ProductDocumentRma) o;
        return Objects.equals( product, that.product ) && Objects.equals( document, that.document )
                && Objects.equals( rma, that.rma ) && Objects.equals( date, that.date );
    }

    @Override public int hashCode() {
        return Objects.hash( product, document, rma, date );
    }
}
