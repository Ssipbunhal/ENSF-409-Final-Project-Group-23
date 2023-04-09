package src.Schedules;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

    /*
     * First this functions clears the hashmap. 
     * Secondly, the medical treatments are added to the schedule. 
     * Thirdly, the feeding tasks are added. 
     */
    public Map<Integer,ArrayList<ScheduledTask>> createSchedule(ArrayList<Animal> animals,ArrayList<Treatment> treatments){
        schedule.clear();
        addMedicalTasks(treatments);
        AddFeedingTasks(animals);
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

    /*
     * If returns -1 no optimalTime go with random insert. Else use the return value
     * where all animals can be inserted.
     */
    private int findOptimalTime(int start,int end, int totalTimeNeeded){
        int optimalTime = -1;
        if(totalTimeNeeded > 60){
            return optimalTime;
        }

        while(start < end){
            if(!schedule.keySet().contains(start)){
                return start;
            }
            var timeLeftOnHour = totalTimeNeeded +  sumOfTime(start);
            if(timeLeftOnHour < 60){
                return start;
            }
            start++;
        }
        return optimalTime;
    }

    /*
     * This code will first remove all orphans from the list as they are not a feeding task.
     * The code will group the animals based on their species.
     * Then findOptimalTime will try to find an hour where all animals can be inserted. 
     * If the code returns -1 no such hours exists and the animals will be randomly inserted. 
     * else all the animals will be inserted into that hour. 
     */
    private void AddFeedingTasks(ArrayList<Animal> list) {
        var animals = removeOrphanFromList(list);
        
        for(var animalList : animals.stream().collect(Collectors.groupingBy(Animal::getAnimalSpecies)).values()){
            var animal = animalList.get(0);
           var time = animal.getFeedingTime().getFeedtime();
           var feedingInterval = animal.getFeedingTime().getFeedingInterval();         
            
           var optimalTime = findOptimalTime(time.getHour(),
                                feedingInterval + time.getHour(),
                                animal.getFeedingTime().getTotalFeedingTime(animalList.size()));
           
            if(optimalTime != -1){
                // Found a spot where all animals can be inserted.
                AddNewFeedingTask(animalList, optimalTime);
            } else {
                 // Else no optimal time was found, do a random insert in the best spot. 
                for(var a : animalList){
                    time = IncrementTimeIfNeed(time, feedingInterval,animal.getFeedingTime().getTimeToFeed());
                    if(schedule.containsKey(time.getHour())){
                        var task = schedule.get(time.getHour());    
                        var xx = task.stream()
                                .filter(c -> c != null)
                                .filter(c -> c.equals(a.toString()))
                                .collect(Collectors.toList());
                        var feed = xx.size() == 0 ? null : xx.get(0);
                       
                        AddToExistingFeedingTask(a, task, feed);
                    }else {
                        AddNewFeedingTask(a, time);
                    }
                }
            }
        }
    }

    /*
     * Removes all oprthans from an arraylist
     */
    private  List<Animal> removeOrphanFromList(ArrayList<Animal> animals){
        return animals.stream()
                .filter(c -> c != null)
                .filter(c -> !c.getOrphan())
                .collect(Collectors.toList());
    }

    /*
     * inserts a single animal to the schedule based on an hour given
     */
    private void AddNewFeedingTask(Animal animal, LocalDateTime time) {
        var m = new ArrayList<ScheduledTask>();
        var sTask = new FeedingTask(animal.toString(),  
             animal.getFeedingTime(),
             FeedingTask.getInitialDesc(animal.getAnimalNickname(),animal.getAnimalSpecies()), 
             animal.getAnimalNickname());

        m.add(sTask);
        schedule.put(time.getHour(), m);
    }

    /*
     * Inserts a list of animals to the schedule based on an hour.
     */
    private void AddNewFeedingTask(List<Animal> animals, int time) {
        var animal =  animals.get(0);
        var m = new ArrayList<ScheduledTask>();
        var sTask = new FeedingTask(animal.toString(),  
             animal.getFeedingTime(),
             FeedingTask.getInitialDesc(animal.getAnimalNickname(),animal.getAnimalSpecies()), 
             animal.getAnimalNickname());

        for(var a : animals){
            if(a == animal){
                continue;
            }
            sTask.addAnimalToTask(a.getAnimalNickname());
        }

        m.add(sTask);
        schedule.put(time, m);
    }
    /*
     * updates the schedule with a new animal
     */
    private void AddToExistingFeedingTask(Animal animal, ArrayList<ScheduledTask> task, ScheduledTask feed) {
        if(task.contains(feed)){            
           var feeding = (FeedingTask)feed;
           feeding.addAnimalToTask(animal.getAnimalNickname());
        } else {
            var sTask = new FeedingTask(animal.toString(), 
            animal.getFeedingTime(),
            FeedingTask.getInitialDesc(animal.getAnimalNickname(),animal.getAnimalSpecies()),
            animal.getAnimalNickname());
            task.add(sTask);         
        }
    }

    /*
     * Finds a time where a the schedule is not full
     */
    private LocalDateTime IncrementTimeIfNeed(LocalDateTime time, int feedingInterval, int addedTime) {
        var incrementCounter = 0;
        if(!scheduleFullOnHour(time.getHour(),addedTime)){
            while(!scheduleFullOnHour(time.getHour(),addedTime)){
                if(time.getHour() >= 24 || incrementCounter == feedingInterval || addedTime > 60)
                {
                    break;
                } 
                time = time.plusHours(1);         
                incrementCounter++;                   
               }
           }
        return time;
    }

    /*
     * Adds medical tasks to the schedule. If the maxwindow allows if, the medical 
     * task will be moved if the schedule is full on the inital hour. 
     */
    private void addMedicalTasks(ArrayList<Treatment> treatments) {
        Collections.sort(treatments, (o1, o2) -> o1.getTaskToPreform().getMaxWindow().compareTo(o2.getTaskToPreform().getMaxWindow()));
        for(var treatment : treatments){     
            var time = IncrementTimeIfNeed(LocalDate.now().atTime(treatment.getStartHour(), 0),
                        treatment.getTaskToPreform().getMaxWindow(),
                        treatment.getTaskToPreform().getTimeSpent());
            
            if(schedule.containsKey(time.getHour())){
                var task = schedule.get(time.getHour());
                task.add(new ScheduledTask(treatment.getTaskToPreform(),
                            treatment.getAnimalToTreat().getAnimalNickname()));
            }else {
                var set = new ArrayList<ScheduledTask>();
                set.add(new ScheduledTask(treatment.getTaskToPreform(),
                        treatment.getAnimalToTreat().getAnimalNickname()));
                schedule.put(time.getHour(), set);
            }
        }
    }

    /*
     * Helper method to get the total time from an arraylist.
     */
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

    /*
     * Helper method to see if the schedule is full
     */
    public boolean scheduleFullOnHour(int hour, int addedTime){
        var sum = addedTime;
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
