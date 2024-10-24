package be.neoo.entities.compoundID;

import java.io.Serializable;

public class PayementReminderPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private int order;
    private int reminderType;

    public PayementReminderPK() {}

    public PayementReminderPK ( int order, int reminderType){
        this.order = order;
        this.reminderType = reminderType;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder( int order ) {
        this.order = order;
    }

    public int getReminder() {
        return reminderType;
    }

    public void setReminder( int reminder ) {
        this.reminderType = reminder;
    }
}
