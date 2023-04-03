package Tests;

import org.junit.Test;
import src.Animals.Animal;
import src.Animals.Beaver;
import src.Exceptions.InvalidAnimalType;
import src.Tasks.MedicalTask;
import src.WildlifeRescueCentre;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test_WildlifeRescueCentre {
    /*
     * Test creating a new medical task
     */
    @Test
    public void test_add_MedicalTask() throws InvalidAnimalType {
        WildlifeRescueCentre centre = new WildlifeRescueCentre();

        MedicalTask task = new MedicalTask("123", "animal cleaning",25, 30);
        centre.addMedicalTasks(task);
        ArrayList<MedicalTask> tasks =  centre.getMedicalTasks();

        assertTrue(tasks.contains(task));
    }
    /*
     * Test adding a new feeding time
     */
    @Test
    public void test_add_FeedingTime() throws InvalidAnimalType {
        Animal animal = new Beaver("123", "beav","herbivore",true);
        animal.getFeedingTime();
    }
    /*
     * Test adding a new volunteer

     */
    @Test
    public void test_add_Volunteer() {

    }
    /*
     * Test adding a new animal
     */
    @Test
    public void test_add_Animal() {
        //Animal animal = new Beaver("123", "beav","herbivore",true);

    }
    /*
     * Test getting medical tasks
     */
    @Test
    public void test_get_MedicalTasks() {

    }
    /*
     * Test getting feeding times
     */
    @Test
    public void test_get_FeedingTimes() {

    }
    /*
     * Test getting volunteers
     */
    @Test
    public void test_get_Volunteers() {

    }

    /*
     * Test getting Animals
     */
    @Test
    public void test_get_Animals() {

    }

    /*
     * Test adding a backup volunteer
     *
     */
    @Test
    public void test_add_BackupVolunteer() {

    }
    /*
     * Test adding and getting multiple medical tasks for different animals
     *
     */
    @Test
    public void test_add_get_MedicalTasks() {

    }
    /*
     * Test adding and getting multiple feeding times for different animals
     *
     */
    @Test
    public void test_add_getFeedingTimes() {

    }

    /*
     * Test adding and getting multiple volunteers with different details
     *
     */
    @Test
    public void test_add_get_Volunteers() {

    }

    /*
     * Test adding and getting multiple animals with different details
     *
     */
    @Test
    public void test_add_get_Animals() {

    }

}
