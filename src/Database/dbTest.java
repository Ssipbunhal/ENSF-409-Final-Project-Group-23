package src.Database;

import java.sql.SQLException;
import java.util.regex.Pattern;

import src.Exceptions.InvalidAnimalType;

public class dbTest {
    public static void main(String[]args) throws ClassNotFoundException, SQLException, InvalidAnimalType{
		
        Pattern pattern = Pattern.compile("\\b(?!and\\b)\\w+\\b");
        // var matcher = pattern.matcher("Annie, Oliver and Mowgli");
        // while(matcher.find()){
        //     System.out.println(matcher.group());
        // }
        var test =new DbContext();
		var testAnimal  = test.getAllAnimals();
        // var testTask  = test.getAllTasks();
        // var testTreatments  = test.getAllTreatments();
        try{
            for(var i : testAnimal){
                System.out.println(i.getAnimalNickname() + " -- " + i.getFeedingTime().getFeedtime().toString());
            }
            // for(var i : testTask){
            //     System.out.println(i.getTaskId());
            // }
            // for(var i : testTreatments){
            //     System.out.println(i.getAnimalToTreat().getAnimalNickname() + " -- " + i.getTaskToPreform().getdescription());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }

       
	}

  
}
