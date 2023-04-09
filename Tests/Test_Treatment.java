//import org.junit.Test;
//import src.Animals.Animal;
//import src.Tasks.MedicalTask;
//import src.Treatment;
//
//import static org.junit.Assert.assertEquals;
//
//public class Test_Treatment {
//
//    @Test
//    public void testGetAnimalToTreat() {
//        Animal animal = new Animal("Dog");
//        MedicalTask task = new MedicalTask("Surgery", "SURG");
//        Treatment treatment = new Treatment(animal, task, 10);
//        assertEquals(animal, treatment.getAnimalToTreat());
//    }
//
//    @Test
//    public void testGetTaskToPreform() {
//        Animal animal = new Animal("Cat");
//        MedicalTask task = new MedicalTask("Vaccination", "VACC");
//        Treatment treatment = new Treatment(animal, task, 13);
//        assertEquals(task, treatment.getTaskToPreform());
//    }
//
//    @Test
//    public void testGetStartHour() {
//        Animal animal = new Animal("Bird");
//        MedicalTask task = new MedicalTask("Checkup", "CHKP");
//        Treatment treatment = new Treatment(animal, task, 16);
//        assertEquals(16, treatment.getStartHour());
//    }
//
//    @Test
//    public void testToString() {
//        Animal animal = new Animal("Horse");
//        MedicalTask task = new MedicalTask("X-Ray", "XRAY");
//        Treatment treatment = new Treatment(animal, task, 19);
//        assertEquals("MedicalTask - XRAY", treatment.toString());
//    }
//}
