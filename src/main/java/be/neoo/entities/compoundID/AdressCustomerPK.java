package be.neoo.entities.compoundID;

import java.io.Serializable;

public class AdressCustomerPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private int customer;
    private int address;

    public AdressCustomerPK() {

    }

    public AdressCustomerPK( int customer, int adress){
        this.customer = customer;
        this.address = adress;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress( int address ) {
        this.address = address;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer( int customer ) {
        this.customer = customer;
    }
}
