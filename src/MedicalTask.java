package src;

import src.Animals.Animal;

public class MedicalTask {

    private String description;
    private int timeSpent;
    private int duration;
    private int qty;
    private boolean volunteerNeeded;
    private String taskId;
    private int maxWindow;
    private Animal animalToTreat;



    public MedicalTask(String description, int timeSpent, int duration, int qty, boolean volunteerNeeded) {
        this.description = description;
        this.timeSpent = timeSpent;
        this.duration = duration;
        this.qty = qty;
        this.volunteerNeeded = volunteerNeeded;
    }

    public MedicalTask(String taskId,String description, int duration, int maxWindow) {
        this.description = description;
        this.taskId = taskId;
        this.duration = duration;
        this.maxWindow = maxWindow;
    }

    public int getMaxWindow() {
        return maxWindow;
    }

    public void setMaxWindow(int maxWindow) {
        this.maxWindow = maxWindow;
    }


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getdescription() {
        return this.description;
    }

    public int getTimespent() {

        return this.timeSpent;
    }

    public int getduration() {

        return this.duration;
    }

    public int getQty() {

        return this.qty;
    }
    //Added Not in UML
    public boolean getVolunteerNeeded() {

        return this.volunteerNeeded;
    }

    public void setdescription(String description) {

        this.description = description;
    }

    public void setTimespent(int timeSpent) {

        this.timeSpent = timeSpent;
    }

    public void setduration(int duration) {

        this.duration = duration;
    }

    public void setQty(int qty) {

        this.qty = qty;
    }


    //Added Not in UML
    public void setVolunteerNeeded(boolean volunteerNeeded) {

        this.volunteerNeeded = volunteerNeeded;
    }
}

