package be.neoo.entities.compoundID;

import java.io.Serializable;

public class ProductDocumentRmaPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private int product;
    private int document;
    private int rma;

    public ProductDocumentRmaPK ( int product, int document, int rma){
        this.product = product;
        this.document = document;
        this.rma = rma;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct( int product ) {
        this.product = product;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument( int document ) {
        this.document = document;
    }

    public int getRma() {
        return rma;
    }

    public void setRma( int rma ) {
        this.rma = rma;
    }
}
