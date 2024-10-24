package be.neoo.entities.compoundID;

import java.io.Serializable;

public class ContractUserPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private int employe;
    private int contract;

    public ContractUserPK (int user, int conttract){
        this.employe = user;
        this.contract = conttract;
    }

    public int getEmploye() {
        return employe;
    }

    public void setEmploye( int employe ) {
        this.employe = employe;
    }

    public int getContract() {
        return contract;
    }

    public void setContract( int contract ) {
        this.contract = contract;
    }
}
