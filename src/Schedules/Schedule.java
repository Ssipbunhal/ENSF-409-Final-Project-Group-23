package src.Schedules;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import src.Treatment;
import src.Volunteer;
import src.Animals.Animal;
import src.Tasks.FeedingTask;
import src.Tasks.MedicalTask;
import src.Tasks.ScheduledTask;

public class Schedule {

    private ArrayList<Volunteer> volunteer;
    private ArrayList<MedicalTask> medicalTasks;

    private Map<Integer,ArrayList<ScheduledTask>> schedule = new HashMap<Integer,ArrayList<ScheduledTask>>();
    

    public Schedule() {
        this.volunteer = new ArrayList<Volunteer>();
        this.medicalTasks = new ArrayList<MedicalTask>();
    }


    public Map<Integer,ArrayList<ScheduledTask>> createSchedule(ArrayList<Animal> animals,ArrayList<Treatment> treatments){
        schedule.clear();
        addMedicalTasks(treatments);
        AddFeedingTasks(animals);
        //TODO REMOVE ONLY TEST CODE
        for(var task : schedule.entrySet()){
            System.out.println("Time: "+task.getKey());
            for(var j : task.getValue()){
                System.out.println("\tTask: " + j.getTaskDescription());
                System.out.println("\tQty:" + (j.getQuantity() == 0 ? "-" : j.getQuantity()));
                System.out.println("\tTime spent: " + j.getTimeSpent());
                System.out.println("\tTime available: " + sumOfTime(task.getKey()));
                System.out.println();
            }
            if(!scheduleFullOnHour(task.getKey())){
                System.out.println("\t* Backup needed. *");
            }
        }
        return schedule;
    }

    public Map<Integer,ArrayList<ScheduledTask>> getSchedule(){
        return schedule;
    }

    public void addVolunteer(Volunteer volunteer) {

        this.volunteer.add(volunteer);
    }

    public void addMedicalTask(MedicalTask medicalTask) {

        this.medicalTasks.add(medicalTask);
    }


    private void AddFeedingTasks(ArrayList<Animal> animals) {
       
        for(var animal : animals){
           var time = animal.getFeedingTime().getFeedtime();
           var feedingInterval = animal.getFeedingTime().getFeedingInterval();         
            var incrementCounter = 0;
            time = IncrementTimeIfNeed(time, feedingInterval, incrementCounter);
                if(schedule.containsKey(time.getHour())){
                    var task = schedule.get(time.getHour());    
                    var xx = task.stream()
                            .filter(c -> c != null)
                            .filter(c -> c.equals(animal.toString()))
                            .collect(Collectors.toList());
                    var feed = xx.size() == 0 ? null : xx.get(0);
                   
                    AddToExistingFeedingTask(animal, task, feed);
                }else {
                    AddNewFeedingTask(animal, time);
                }
        }
    }


    private void AddNewFeedingTask(Animal animal, LocalDateTime time) {
        var m = new ArrayList<ScheduledTask>();
        var sTask = new FeedingTask(animal.toString(),  animal.getFeedingTime(),animal.getAnimalNickname());
        m.add(sTask);
        schedule.put(time.getHour(), m);
    }


    private void AddToExistingFeedingTask(Animal animal, ArrayList<ScheduledTask> task, ScheduledTask feed) {
        if(task.contains(feed)){            
           var feeding = (FeedingTask)feed;
           feeding.addAnimalToTask(animal.getAnimalNickname());
        } else {
            var sTask = new FeedingTask(animal.toString(), 
            animal.getFeedingTime(),
            animal.getAnimalNickname());
            task.add(sTask);         
        }
    }


    private LocalDateTime IncrementTimeIfNeed(LocalDateTime time, int feedingInterval, int incrementCounter) {
        if(!scheduleFullOnHour(time.getHour())){

            while(!scheduleFullOnHour(time.getHour())){
                if(time.getHour() >= 24 || incrementCounter == feedingInterval)
                {
                    break;
                } 
                time = time.plusHours(1);         
                incrementCounter++;                   
               }
           }
        return time;
    }

    private void addMedicalTasks(ArrayList<Treatment> treatments) {
        for(var treatment : treatments){
            if(schedule.containsKey(treatment.getStartHour())){
                var task = schedule.get(treatment.getStartHour());
                task.add(treatment.getTaskToPreform());
            }else {
                var set = new ArrayList<ScheduledTask>();
                set.add(treatment.getTaskToPreform());
                schedule.put(treatment.getStartHour(), set);
            }
        }
    }

    public int sumOfTime(int hour){
        var sum = 0;
        if(schedule.get(hour) == null){
            return 0;
        }
        for(var animal : schedule.get(hour)){
            sum += animal.getTimeSpent();
        }
        return sum;
    }

    public boolean scheduleFullOnHour(int hour){
        var sum = 0;
        if(schedule.get(hour) == null){
            return true;
        }
        for(var animal : schedule.get(hour)){
            sum += animal.getTimeSpent();
            if(sum >= 60){
                return false;
            }
        }
        return true;
    }

}
