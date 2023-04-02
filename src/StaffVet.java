package src;

import java.util.ArrayList;

import src.Tasks.MedicalTask;

public class StaffVet extends Employee {
    private ArrayList<MedicalTask> medicalTasks;
    // Should probably include availibility to determine if they can  be in a certain time int the schedule
    public StaffVet(String employeeName, ArrayList<MedicalTask> medicalTasks) {
        super(employeeName);
        this.medicalTasks = medicalTasks;
    }
    public void addMedicalTask(MedicalTask medicalTask) {
        medicalTasks.add(medicalTask);
    }

}
