package src.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.MedicalTask;
import src.Treatment;
import src.Animals.Animal;
import src.Exceptions.InvalidAnimalType;
import src.Utils.AnimalCreaterUtil;

public class DbContext {

	public final String DBURL;
	public final String USERNAME;
	public final String PASSWORD;

	private Connection connect = null;
	private ResultSet result = null;
	private ResultSet result2 = null;
	private ResultSet result3 = null;

	private final String ORPHANED_REGEX = "\\b(?!and\\b)\\w+\\b";
	private final Pattern ORPHANED_PATTERN = Pattern.compile(ORPHANED_REGEX);

    

	// constructor
	public DbContext() throws SQLException, ClassNotFoundException {
        // "jdbc:mysql://localhost:3306/SENG401Project?useSSL=false", "root","password"
		DBURL = "jdbc:mysql://localhost:3306/EWR?useSSL=false";
		USERNAME = "root";
		PASSWORD = "password";
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
	}

	// close all connections
	private void closeAll() {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				System.err.print("Failed to close connection to database.");
				// System.exit(1);
			}
		}
		if (result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				System.err.print("Failed to close ResultSet object.");
				// System.exit(1);
			}
		}
	}
	private ArrayList<Animal> getAnimalList(ResultSet result) throws InvalidAnimalType, SQLException{
		ArrayList<Animal> searchResults = new ArrayList<Animal>();
		while (result.next()) {

			var matcher = ORPHANED_PATTERN.matcher(result.getString("AnimalNickname"));
			while(matcher.find()){
				searchResults.add(AnimalCreaterUtil.createAnimal(result.getString("AnimalID"),
				matcher.group(),
				result.getString("AnimalSpecies")));
			}

		}
		return searchResults;
	}
    public  ArrayList<Animal> getAllAnimals() throws InvalidAnimalType{
        Statement stmt = null;
		ArrayList<Animal> searchResults = new ArrayList<Animal>();
		try {
			String query = "SELECT * FROM ANIMALS";

			stmt = connect.createStatement();
			result = stmt.executeQuery(query);
			
			searchResults = getAnimalList(result);

			stmt.close();

		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in searchItems.");
			// System.exit(1);
		}
		return searchResults;
    }

    public ArrayList<MedicalTask> getAllTasks(){
        Statement stmt = null;
		ArrayList<MedicalTask> searchResults = new ArrayList<MedicalTask>();
		try {
			String query = "SELECT * FROM TASKS";

			stmt = connect.createStatement();
			result = stmt.executeQuery(query);

			while (result.next()) {
				var task = new MedicalTask(result.getString("TaskID"),
					result.getString("Description"),result.getInt("Duration"), 
					result.getInt("MaxWindow"));
				searchResults.add(task);
			}

			stmt.close();

		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in searchItems.");
			// System.exit(1);
		}
		return searchResults;
    }

	public ArrayList<Treatment> getAllTreatments() throws InvalidAnimalType{
        Statement stmt = null;
		ArrayList<Treatment> searchResults = new ArrayList<Treatment>();
		try {
			String query = "SELECT * FROM TREATMENTS AS TR JOIN ANIMALS AS A ON TR.AnimalID" + 
			 "= A.AnimalID JOIN tasks AS T ON T.TaskID = TR.TaskID";

			stmt = connect.createStatement();
			result = stmt.executeQuery(query);

			while (result.next()) {

				Animal animal = AnimalCreaterUtil.createAnimal(result.getString("AnimalID"),
								result.getString("AnimalNickname"),
								result.getString("AnimalSpecies"));
				var task = new MedicalTask(result.getString("TaskID"),
					result.getString("Description"),result.getInt("Duration"), 
					result.getInt("MaxWindow"));
				var treatment = new Treatment(animal, task, result.getInt("StartHour"));
				searchResults.add(treatment);
			}

			stmt.close();

		} catch (SQLException e) {
			closeAll();
			System.err.println("SQLException in searchItems.");
			// System.exit(1);
		}
		return searchResults;
    }
}
