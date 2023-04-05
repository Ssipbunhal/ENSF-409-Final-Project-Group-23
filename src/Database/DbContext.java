package src.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import src.Treatment;
import src.Animals.Animal;
import src.Exceptions.InvalidAnimalTypeException;
import src.Tasks.MedicalTask;
import src.Utils.AnimalCreaterUtil;

public class DbContext {

	public final String DBURL;
	public final String USERNAME;
	public final String PASSWORD;

	private Connection connect = null;
	private ResultSet result = null;

	private final String ORPHANED_REGEX = ",|\\band\\b";


    

	// constructor
	public DbContext() throws SQLException, ClassNotFoundException {
		DBURL = "jdbc:mysql://localhost:3306/ewr?useSSL=false";
		USERNAME = "root";
		PASSWORD = "password1";
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

    public  ArrayList<Animal> getAllAnimals() throws InvalidAnimalTypeException{
        Statement stmt = null;
		ArrayList<Animal> searchResults = new ArrayList<Animal>();
		try {
			String query = "SELECT * FROM ANIMALS";

			stmt = connect.createStatement();
			result = stmt.executeQuery(query);
			
			while (result.next()) {
				var multipleNames = result.getString("AnimalNickname").split(ORPHANED_REGEX);
				var orphan = multipleNames.length != 1;
				searchResults.add(AnimalCreaterUtil.createAnimal(result.getString("AnimalID"),
						result.getString("AnimalNickname"),
						result.getString("AnimalSpecies"),orphan));
			}

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
					result.getInt("MaxWindow"),
					"");
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

	public ArrayList<Treatment> getAllTreatments() throws InvalidAnimalTypeException{
        Statement stmt = null;
		ArrayList<Treatment> searchResults = new ArrayList<Treatment>();
		try {
			String query = "SELECT * FROM TREATMENTS AS TR JOIN ANIMALS AS A ON TR.AnimalID= A.AnimalID JOIN tasks" +
			" AS T ON T.TaskID = TR.TaskID order by A.AnimalSpecies";

			stmt = connect.createStatement();
			result = stmt.executeQuery(query);

			while (result.next()) {
				var multipleNames = result.getString("AnimalNickname").split(ORPHANED_REGEX);
				var orphan = multipleNames.length != 1;
				//System.out.println(orphan);
				Animal animal = AnimalCreaterUtil.createAnimal(result.getString("AnimalID"),
								result.getString("AnimalNickname"),
								result.getString("AnimalSpecies"),orphan);
								// String description, int timeSpent, int duration, int qty, boolean volunteerNeeded
				var task = new MedicalTask(result.getString("TaskID"),
					result.getString("Description"),result.getInt("Duration"), 
					result.getInt("MaxWindow"),
					result.getString("AnimalNickname"));

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
