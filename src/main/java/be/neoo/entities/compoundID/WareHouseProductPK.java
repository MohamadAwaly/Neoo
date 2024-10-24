package be.neoo.entities.compoundID;

import java.io.Serializable;

public class WareHouseProductPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private int warehouse;
    private int product;

    public WareHouseProductPK( int warehouse, int product){
        this.warehouse = warehouse;
        this.product = product;
    }

    public int getWarehouse() {
        return warehouse;
    }

    public void setWarehouse( int warehouse ) {
        this.warehouse = warehouse;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct( int product ) {
        this.product = product;
    }
}
