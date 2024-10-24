package be.neoo.entities.compoundID;


import java.io.Serializable;
import java.util.Objects;

public class AdressEmployePK implements Serializable {

    private static final long serialVersionUID = 1L;

    private int employe;

    private int address;

    public AdressEmployePK(){

    }

    public AdressEmployePK( int employe, int address ) {
        this.employe = employe;
        this.address = address;
    }

    public int getEmploye() {
        return employe;
    }

    public void setEmploye( int employe ) {
        this.employe = employe;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress( int address ) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdressEmployePK that = (AdressEmployePK) o;
        return employe == that.employe && address == that.address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employe, address);
    }
}
