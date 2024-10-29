package be.neoo.entities;



import be.neoo.entities.compoundID.WareHouseProductPK;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass( WareHouseProductPK.class )
@Table( name = "warehouses_products" )
public class WareHouseProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn( name = "warehouse", nullable = false )
    private Warehouse warehouse;

    @Id
    @ManyToOne
    @JoinColumn( name = "product", nullable = false )
    @JsonBackReference
    private Product product;

    @Column( name = "date", nullable = false )
    private Date    date;

    @Column( name = "deliver", nullable = false )
    private Boolean deliver;

    @Column( name = "quantity", nullable = false )
    private int     quantity;

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse( Warehouse warehouse ) {
        this.warehouse = warehouse;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct( Product product ) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public Boolean getDeliver() {
        return deliver;
    }

    public void setDeliver( Boolean deliver ) {
        this.deliver = deliver;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        WareHouseProduct that = (WareHouseProduct) o;
        return quantity == that.quantity && Objects.equals( warehouse, that.warehouse )
                && Objects.equals( product, that.product ) && Objects.equals( date, that.date )
                && Objects.equals( deliver, that.deliver );
    }

    @Override public int hashCode() {
        return Objects.hash( warehouse, product, date, deliver, quantity );
    }
}
