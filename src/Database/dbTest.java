package src.Database;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import src.Treatment;
import src.Exceptions.InvalidAnimalTypeException;
import src.Schedules.Schedule;

public class dbTest {
    public static void main(String[]args) throws ClassNotFoundException, SQLException, InvalidAnimalTypeException{
		
        var test =new DbContext();
		var testAnimal  = test.getAllAnimals();//.stream().filter(i->i.getOrphan() == false).collect(Collectors.toList());
    
        
        var testTreatments  = test.getAllTreatments();
        try{
                var asd = new Schedule();
                asd.createSchedule(testAnimal, testTreatments);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

  
}
