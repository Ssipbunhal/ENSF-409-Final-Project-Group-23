package src;

public class MedicalTask {

    private String type;
    private int timeSpent;
    private int timeAvailable;
    private int qty;
    private boolean volunteerNeeded;

    public MedicalTask(String type, int timeSpent, int timeAvailable, int qty, boolean volunteerNeeded) {
        this.type = type;
        this.timeSpent = timeSpent;
        this.timeAvailable = timeAvailable;
        this.qty = qty;
        this.volunteerNeeded = volunteerNeeded;
    }

    public String getType() {
        return this.type;
    }

    public int getTimespent() {

        return this.timeSpent;
    }

    public int getTimeavailable() {

        return this.timeAvailable;
    }

    public int getQty() {

        return this.qty;
    }
    //Added Not in UML
    public boolean getVolunteerNeeded() {

        return this.volunteerNeeded;
    }

    public void setType(String type) {

        this.type = type;
    }

    public void setTimespent(int timeSpent) {

        this.timeSpent = timeSpent;
    }

    public void setTimeavailable(int timeAvailable) {

        this.timeAvailable = timeAvailable;
    }

    public void setQty(int qty) {

        this.qty = qty;
    }


    //Added Not in UML
    public void setVolunteerNeeded(boolean volunteerNeeded) {

        this.volunteerNeeded = volunteerNeeded;
    }
}

