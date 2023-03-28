package src.Database;

import java.sql.SQLException;

public class dbTest {
    public static void main(String[]args) throws ClassNotFoundException, SQLException{
		var test =new DbContext();
		var testAnimal  = test.getAllAnimals();
        var testTask  = test.getAllTasks();
        var testTreatments  = test.getAllTreatments();
        try{
            for(var i : testAnimal){
                System.out.println(i.getAnimalNickname());
            }
            for(var i : testTask){
                System.out.println(i.getTaskId());
            }
            for(var i : testTreatments){
                System.out.println(i.getAnimalToTreat().getAnimalNickname() + " -- " + i.getTaskToPreform().getdescription());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

       
	}

  
}
