package src;

import java.util.ArrayList;
import java.util.List;

import src.Tasks.MedicalTask;

public class Volunteer extends Employee {
    private List<Availability> availability;
    private ArrayList<MedicalTask> medicalTasks;

    public Volunteer(String employeeId, String employeeName, String volunteerName, List<Availability> availability, ArrayList<MedicalTask> medicalTasks) {

        super(employeeName);
        this.availability = availability;
        this.medicalTasks = medicalTasks;
    }

    public void addMedicalTask(MedicalTask medicalTask) {

        medicalTasks.add(medicalTask);
    }
}
