package be.neoo.entities.compoundID;

import java.io.Serializable;

public class OrderDocumentPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private int order;
    private int document;


    public OrderDocumentPK( int order, int document ) {
        this.order = order;
        this.document = document;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder( int order ) {
        this.order = order;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument( int document ) {
        this.document = document;
    }
}
