
import org.junit.Test;
import src.AnimalCare;
import src.Animals.Animal;
import src.Animals.Beaver;
import src.Animals.Coyote;
import src.Animals.Fox;
import src.Availability;
import src.Exceptions.InvalidAnimalTypeException;
import src.Tasks.MedicalTask;
import src.Volunteer;
import src.WildlifeRescueCentre;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Test_WildlifeRescueCentre {
    /*
     * Test creating a new medical task
     */
    @Test
    public void test_add_MedicalTask()  {
        WildlifeRescueCentre centre = new WildlifeRescueCentre();

        MedicalTask task = new MedicalTask("123", "animal cleaning",25, 30,"");
        centre.addMedicalTasks(task);
        ArrayList<MedicalTask> tasks =  centre.getMedicalTasks();

        assertTrue(tasks.contains(task));
    }
    /*
     * Test adding a new feeding time
     */
    @Test
    public void test_add_FeedingTime() throws InvalidAnimalTypeException {
        WildlifeRescueCentre centre = new WildlifeRescueCentre();
        Animal animal = new Beaver("123", "beav","beaver",true);
        AnimalCare ft = animal.getFeedingTime();
        centre.addFeedingTime(ft);

        ArrayList<AnimalCare> ftimes= centre.getFeedingTime();

        assertTrue(ftimes.contains(ft));

    }
    /*
     * Test adding a new volunteer

     */
    @Test
    public void test_add_Volunteer() {
        WildlifeRescueCentre centre = new WildlifeRescueCentre();
        ArrayList<MedicalTask> tasks =  new  ArrayList<MedicalTask> ();
        List<Availability> availabilities = new ArrayList<>();
        availabilities.add(new Availability(LocalDate.now(), LocalTime.of(8, 0),LocalTime.of(16, 0)));
        Volunteer v = new Volunteer("123", "bob", availabilities,tasks );
        centre.addVolunteers(v);

        ArrayList<Volunteer> vs = centre.getVolunteers();
        assertTrue(vs.contains(v));

    }
    /*
     * Test adding a new animal
     */
    @Test
    public void test_add_Animal() throws InvalidAnimalTypeException {
        WildlifeRescueCentre centre = new WildlifeRescueCentre();
        Animal animal = new Beaver("123", "beav","beaver",true);
        centre.addAnimal(animal);
        ArrayList<Animal> animals =  centre.getAnimals();
        boolean animalFound = false;

        for (Animal a : animals) {
            if (a.getAnimalID().equals("123")) {
                animalFound = true;
                break;
            }
        }

        assertTrue(animalFound);

    }
    /*
     * Test getting medical tasks
     */
//    @Test
//    public void test_get_MedicalTasks() {
//
//    }
//    /*
//     * Test getting feeding times
//     */
//    @Test
//    public void test_get_FeedingTimes() {
//
//    }
//

    /*
     * Test adding a backup volunteer
     *
     */
//    @Test
//    public void test_add_BackupVolunteer() {
//
//    }
    /*
     * Test adding and getting multiple medical tasks for different animals
     *
     */
    @Test
    public void test_add_get_MedicalTasks() {
        WildlifeRescueCentre centre = new WildlifeRescueCentre();

        MedicalTask task1 = new MedicalTask("1233", "animal cleaning",25, 30,"");
        MedicalTask task2 = new MedicalTask("123", "h",25, 30,"");
        MedicalTask task3 = new MedicalTask("12333", "ggg",25, 30,"");

        centre.addMedicalTasks(task1);
        centre.addMedicalTasks(task2);
        centre.addMedicalTasks(task3);

        ArrayList<MedicalTask> tasks =  centre.getMedicalTasks();

        for (MedicalTask task : tasks) {
            if (task.getId().equals(task1.getId()) || task.getId().equals(task2.getId()) || task.getId().equals(task3.getId())) {
                continue;
            }
            fail("MedicalTask " + task + " not found");
        }

    }
    /*
     * Test adding and getting multiple feeding times for different animals
     *
     */
    @Test
    public void test_add_getFeedingTimes() throws InvalidAnimalTypeException {
        WildlifeRescueCentre centre = new WildlifeRescueCentre();
        Animal animal = new Beaver("123", "beaver","beaver",true);
        Animal animal1 = new Fox("123", "fox","fox",true);
        Animal animal2 = new Coyote("123", "beav","coyote",true);

        AnimalCare ft = animal.getFeedingTime();
        centre.addFeedingTime(ft);

        AnimalCare ft1 = animal1.getFeedingTime();
        centre.addFeedingTime(ft1);

        AnimalCare ft2 = animal2.getFeedingTime();
        centre.addFeedingTime(ft2);

        ArrayList<AnimalCare> ftimes= centre.getFeedingTime();

        assertTrue(ftimes.contains(ft));
        assertTrue(ftimes.contains(ft1));
        assertTrue(ftimes.contains(ft2));

    }

    /*
     * Test adding and getting multiple volunteers with different details
     *
     */
    @Test
    public void test_add_get_Volunteers() {
        WildlifeRescueCentre centre = new WildlifeRescueCentre();
        ArrayList<MedicalTask> tasks =  new  ArrayList<MedicalTask> ();
        List<Availability> availabilities = new ArrayList<>();
        availabilities.add(new Availability(LocalDate.now(), LocalTime.of(8, 0),LocalTime.of(16, 0)));

        Volunteer v = new Volunteer("123", "bob", availabilities,tasks );
        centre.addVolunteers(v);

        Volunteer v1 = new Volunteer("1283", "bobk", availabilities,tasks);
        centre.addVolunteers(v1);

        Volunteer v2 = new Volunteer("1238", "bobk", availabilities,tasks) ;
        centre.addVolunteers(v2);

        ArrayList<Volunteer> vs = centre.getVolunteers();
        assertTrue(vs.contains(v));
        assertTrue(vs.contains(v1));
        assertTrue(vs.contains(v2));

    }

    /*
     * Test adding and getting multiple animals with different details
     *
     */
    @Test
    public void test_add_get_Animals() throws InvalidAnimalTypeException {
        WildlifeRescueCentre centre = new WildlifeRescueCentre();
        Animal animal = new Beaver("123", "beav","beaver",true);
        Animal animal1 = new Fox("1234", "fc","fox",true);
        Animal animal2 = new Coyote("12345", "x","coyote",true);

        centre.addAnimal(animal);
        centre.addAnimal(animal1);
        centre.addAnimal(animal2);

        ArrayList<Animal> animals =  centre.getAnimals();
        boolean animalFound = false;

        for (Animal a : animals) {
            if (a.getAnimalID().equals("123")) {
                animalFound = true;
                break;
            }
            else{
                animalFound = false;

            }
        }
        for (Animal a : animals) {
            if (a.getAnimalID().equals("1234")) {
                animalFound = true;
                break;
            }
            else{
                animalFound = false;

            }
        }
        for (Animal a : animals) {
            if (a.getAnimalID().equals("12345")) {
                animalFound = true;
                break;
            }
            else{
                animalFound = false;

            }
        }
        assertTrue(animalFound);


    }

}
