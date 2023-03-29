package src;
import java.util.ArrayList;

import src.Animals.Animal;

public class WildlifeRescueCentre {

    private ArrayList<Animal> animals;
    private ArrayList<MedicalTask> medicalTasks;
    private ArrayList<FeedingTime> feedingTime;
    private ArrayList<Volunteer> volunteers;

    public WildlifeRescueCentre() {
        animals = new ArrayList<Animal>();
        medicalTasks = new ArrayList<MedicalTask>();
        feedingTime = new ArrayList<FeedingTime>();
        volunteers = new ArrayList<Volunteer>();
    }

    public ArrayList<MedicalTask> getMedicalTasks() {
        return this.medicalTasks;
    }

    public void addMedicalTasks(MedicalTask task) {
        this.medicalTasks.add(task);
    }

    public ArrayList<FeedingTime> getFeedingTime() {
        return this.feedingTime;
    }

    public void addFeedingTime(FeedingTime time) {
        this.feedingTime.add(time);
    }

    public ArrayList<Volunteer> getVolunteers() {
        return this.volunteers;
    }

    public void addVolunteers(Volunteer volunteer) {
        this.volunteers.add(volunteer);
    }

    //Changed to get animals
    public ArrayList<Animal> getAnimals() {
        return this.animals;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }


}


