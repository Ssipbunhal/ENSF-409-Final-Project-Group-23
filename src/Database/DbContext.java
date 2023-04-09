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

	public DbContext() throws SQLException, ClassNotFoundException {
		DBURL = "jdbc:mysql://localhost:3306/ewr?useSSL=false";
		USERNAME = "root";
		PASSWORD = "password";
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
	}

	private void closeAll() {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("The database error connecting to the database.. The system will exit.");
				System.exit(0);
			}
		}
		if (result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("The database error connecting to the database.. The system will exit.");
				System.exit(0);
			}
		}
	}

	/**
	 * Will return all the animals from the database.
	 * @throws InvalidAnimalTypeException
	 * @Return ArrayList<Animal> from the database
	 */
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
			e.printStackTrace();
		}
		return searchResults;
    }

	/**
	 * Will return all the medical tasks from the database.
	 * @Return ArrayList<MedicalTask>  from the database
	 */
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
			e.printStackTrace();
		}
		return searchResults;
    }
	/**
	 * Will return all the treatments from the database.
	 * @throws InvalidAnimalTypeException
	 * @Return ArrayList<Treatment> from the database
	 */
	public ArrayList<Treatment> getAllTreatments() throws InvalidAnimalTypeException{
        Statement stmt = null;
		ArrayList<Treatment> searchResults = new ArrayList<Treatment>();
		try {
			String query = "SELECT * FROM TREATMENTS AS TR JOIN ANIMALS AS A ON TR.AnimalID= A.AnimalID JOIN tasks" +
			" AS T ON T.TaskID = TR.TaskID order by T.MaxWindow";

			stmt = connect.createStatement();
			result = stmt.executeQuery(query);

			while (result.next()) {
				var multipleNames = result.getString("AnimalNickname").split(ORPHANED_REGEX);
				var orphan = multipleNames.length != 1;
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
			System.err.println("SQLException in getAllTreatments.");
			e.printStackTrace();
		}
		return searchResults;
    }
}
