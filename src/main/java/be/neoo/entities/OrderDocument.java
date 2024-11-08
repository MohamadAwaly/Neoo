package be.neoo.entities;



import be.neoo.entities.compoundID.OrderDocumentPK;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table( name = "order_document" )
@IdClass( OrderDocumentPK.class )
public class OrderDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn( name = "orders", nullable = false )
    private Order order;

    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn( name = "documents", nullable = false )
    private Document document;

    public Order getOrder() {
        return order;
    }

    public void setOrder( Order order ) {
        this.order = order;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument( Document document ) {
        this.document = document;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        OrderDocument that = (OrderDocument) o;
        return Objects.equals( order, that.order ) && Objects.equals( document, that.document );
    }

    @Override public int hashCode() {
        return Objects.hash( order, document );
    }

    protected boolean canEqual( Object other ) {
        return other instanceof OrderDocument;
    }
}
