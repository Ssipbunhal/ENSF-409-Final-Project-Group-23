// package src.Database;

// import java.sql.SQLException;
// import java.time.format.DateTimeFormatter;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.Map;
// import java.util.Set;
// import java.util.regex.Pattern;
// import java.util.stream.Collectors;

// import src.Treatment;
// import src.Exceptions.InvalidAnimalType;
// import src.Schedules.Schedule;

// public class dbTest {
//     public static void main(String[]args) throws ClassNotFoundException, SQLException, InvalidAnimalType{
		
//         // Pattern pattern = Pattern.compile("\\b(?!and\\b)\\w+\\b");
//         // var matcher = pattern.matcher("Annie, Oliver and Mowgli");
        
//         // while(matcher.find()){
            
//         //      System.out.println(matcher.group());
//         // }
//         // var mathc = pattern.matcher("asd");
//         var test =new DbContext();
// 		var testAnimal  = test.getAllAnimals();//.stream().filter(i->i.getOrphan() == false).collect(Collectors.toList());
//         // System.out.println(matcher.groupCount());

//         // String input = "appl";
//         // String[] words = input.split(",|\\band\\b");
//         // for(var i : words){
//         //     System.out.println(i);
//         // }        
        
//         var testTreatments  = test.getAllTreatments();
//         try{
//             // for(var i : testAnimal){
//             //     System.out.println(i.getAnimalNickname() + " -- " + i.getFeedingTime().getFeedtime().toString() + " -- " + i.getOrphan());
//             // }
//             // for(var i : testTask){
//             //     System.out.println(i.getTaskId());
//             // }
//             // for(var i : testTreatments){
//             //     System.out.println(i.getAnimalToTreat().getOrphan() + " -- " + i.getTaskToPreform().getdescription());
//             // }
            
//             //
//             // var map = new HashMap<Integer, Map<Integer,String>>();
            
//             // for(var i : testAnimal){
//             //    var time = i.getFeedingTime().getFeedtime();
//             //    var feedingInterval = i.getFeedingTime().getFeedingInterval();
//             //    var continueIncrement = false;
//             //    do{

//             //         if(map.containsKey(time.getHour())){
//             //             map.get(time.getHour()).add("Feeding task:" + i.getAnimalNickname() + " -- It will take:" + i.getFeedingTime().getTotalTime());
//             //         }else {
//             //             var set = new HashSet<String>();
//             //             set.add("Feeding task:" + i.getAnimalNickname() + " -- It will take:" + i.getFeedingTime().getTotalTime());
//             //             map.put(time.getHour(), set);
//             //         }
//             //         continueIncrement = time.getHour() + feedingInterval >= 24;
//             //          time= time.plusHours(i.getFeedingTime().getFeedingInterval());
//             //     } while(!continueIncrement);
//             // }

//             // for(var i : testTreatments){
//             //     if(map.containsKey(i.getStartHour())){
//             //         map.get(i.getStartHour()).add("Medical task:" + i.getAnimalToTreat().getAnimalNickname() + " -- " + i.getTaskToPreform().getdescription() +". It will take: " + i.getTaskToPreform().getduration() );
//             //     }else {
//             //         var set = new HashSet<String>();
//             //         set.add("Medical task:" + i.getAnimalToTreat().getAnimalNickname() + " -- " + i.getTaskToPreform().getdescription()+". It will take: " + i.getTaskToPreform().getduration());
//             //         map.put(i.getStartHour(), set);
//             //     }

//             // }






//                 var asd = new Schedule();
//                 asd.createSchedule(testAnimal, testTreatments);

//             // WORKS START
//             // var map = new HashMap<Integer, Set<String>>();
            
//             // for(var i : testAnimal){
//             //    var time = i.getFeedingTime().getFeedtime();
//             //    var feedingInterval = i.getFeedingTime().getFeedingInterval();
//             //    var continueIncrement = false;
//             //    do{

//             //         if(map.containsKey(time.getHour())){
//             //             map.get(time.getHour()).add("Feeding task:" + i.getAnimalNickname() + " -- It will take:" + i.getFeedingTime());
//             //         }else {
//             //             var set = new HashSet<String>();
//             //             set.add("Feeding task:" + i.getAnimalNickname() + " -- It will take:" + i.getFeedingTime());
//             //             map.put(time.getHour(), set);
//             //         }
//             //         continueIncrement = time.getHour() + feedingInterval >= 24;
//             //          time= time.plusHours(i.getFeedingTime().getFeedingInterval());
//             //     } while(!continueIncrement);
//             // }

//             // for(var i : testTreatments){
//             //     if(map.containsKey(i.getStartHour())){
//             //         map.get(i.getStartHour()).add("Medical task:" + i.getAnimalToTreat().getAnimalNickname() + " -- " + i.getTaskToPreform().getdescription() +". It will take: " + i.getTaskToPreform().getduration() );
//             //     }else {
//             //         var set = new HashSet<String>();
//             //         set.add("Medical task:" + i.getAnimalToTreat().getAnimalNickname() + " -- " + i.getTaskToPreform().getdescription()+". It will take: " + i.getTaskToPreform().getduration());
//             //         map.put(i.getStartHour(), set);
//             //     }

//             // }
            
//             // for(var i : map.entrySet()){
//             //     System.out.println("Hour: "+i.getKey());
//             //     for(var j : i.getValue()){
//             //         System.out.println(" -"+j);
//             //     }
//             // }

//             // WORKS END
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
// 	}

  
// }
