package be.neoo.entities.compoundID;

import java.io.Serializable;

public class UserRequestPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private int employe;
    private int requestHoliday;

    public UserRequestPK ( int employe, int requestHoliday){
        this.employe = employe;
        this.requestHoliday = requestHoliday;
    }

    public int getEmploye() {
        return employe;
    }

    public void setEmploye( int employe ) {
        this.employe = employe;
    }

    public int getRequestHoliday() {
        return requestHoliday;
    }

    public void setRequestHoliday( int requestHoliday ) {
        this.requestHoliday = requestHoliday;
    }
}
