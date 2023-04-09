package src.FrontEnd;
import src.Database.DbContext;
import src.Exceptions.InvalidAnimalTypeException;
import src.Schedules.Schedule;
import src.Tasks.ScheduledTask;
import src.Treatment;
import src.WildlifeRescueCentre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

/**
 * The generateSchedule class is a JFrame that provides a user interface for
 * adding animals, medical tasks, and generating a schedule for the Wildlife Rescue Centre.
 * The class allows users to input data related to animals and medical tasks, and
 * then generates a schedule.
 */
public class generateSchedule extends JFrame implements ActionListener {

    private JButton submitButton;
    private JButton executeButton;
    private JTextArea outputArea;
    private ArrayList<Treatment> treatments = new ArrayList<Treatment>();
    private WildlifeRescueCentre centre = new WildlifeRescueCentre();
    private JButton animalInputButton;
    private JButton medicalTaskInputButton;
    public static boolean isMedicalFormCompleted = false;

    /**
     * Constructor to initialize the generateSchedule frame.
     */
    public generateSchedule() throws SQLException, InvalidAnimalTypeException, ClassNotFoundException {
        createUI();
    }
    /**
     * Constructor for the MedicalTaskInputForm class.
     * Initializes the form and fetches treatments from the database.
     *
     * @param previousSchedule The instance of the generateSchedule class.
     * @throws SQLException                If there's an issue with the database connection.
     * @throws ClassNotFoundException      If the DbContext class is not found.
     * @throws InvalidAnimalTypeException  If an invalid animal type is found.
     */
    public generateSchedule(generateSchedule previousSchedule) throws SQLException, InvalidAnimalTypeException, ClassNotFoundException {
        createUI();
    }

    /**
     * Creates the user interface for the generateSchedule frame.
     * @throws SQLException                If there's an issue with the database connection.
     * @throws ClassNotFoundException      If the DbContext class is not found.
     * @throws InvalidAnimalTypeException  If an invalid animal type is found.
     */
    public void createUI() throws SQLException, InvalidAnimalTypeException, ClassNotFoundException {
        setTitle("WildLife Rescue Centre");
        setSize(800, 700);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 0;
        inputConstraints.gridy = 1;
        inputConstraints.insets = new Insets(0, 10, 10, 10);

        animalInputButton = new JButton("Add Animal");
        animalInputButton.addActionListener(this);
        add(animalInputButton);
        inputConstraints.gridx = 1;

        medicalTaskInputButton = new JButton("Add Medical Task");
        medicalTaskInputButton.addActionListener(this);
        add(medicalTaskInputButton);

        createGenerateScheduleButton();
        createOutputArea();
        refreshSchedule();
    }



    /**
     * Creates the "Add Treatment" submit button.
     */
    private void createSubmitButton() {
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 1;
        inputConstraints.gridy = 14;
        inputConstraints.insets = new Insets(5, 10, 5, 10);

        submitButton = new JButton("Add Treatment");
        submitButton.addActionListener(this);
        add(submitButton, inputConstraints);
    }
    /**
     * Creates the "Generate Schedule" button.
     */
    private void createGenerateScheduleButton() {
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 14;
        buttonConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        buttonConstraints.insets = new Insets(0, 0, 10, 10);

        executeButton = new JButton("Generate Schedule");
        executeButton.addActionListener(this);
        add(executeButton, buttonConstraints);
    }

    /**
     * Creates the output area for displaying the generated schedule.
     */
    private void createOutputArea() {
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        GridBagConstraints outputConstraints = new GridBagConstraints();
        outputConstraints.gridx = 2;
        outputConstraints.gridy = 0;
        outputConstraints.gridheight = 15;
        outputConstraints.fill = GridBagConstraints.BOTH;
        outputConstraints.weightx = 1.0;
        outputConstraints.weighty = 1.0;
        outputConstraints.insets = new Insets(10, 10, 10, 10);

        add(scrollPane, outputConstraints);
    }

    /**
     * handles the action events for the frame, such as opening an add medical task form
     * or an add animal form. Also handles the regeneration of a new schedule.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == animalInputButton) {
            AnimalInputForm animalInputForm = new AnimalInputForm(this);
            animalInputForm.setVisible(true);
        } else if (e.getSource() == medicalTaskInputButton) {
            MedicalTaskInputForm medicalTaskInputForm = null;
            try {
                medicalTaskInputForm = new MedicalTaskInputForm(this);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidAnimalTypeException ex) {
                throw new RuntimeException(ex);
            }
            medicalTaskInputForm.setVisible(true);
        } else if(e.getSource() == executeButton){
            try {
                outputArea.setText(""); // Clear the output area
                refreshSchedule();
            } catch (InvalidAnimalTypeException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    /**
     * This method refreshes the schedule based on newly inputted medical tasks ,animals and database tasks.
     *
     * @throws SQLException                If there's an issue with the database connection.
     * @throws ClassNotFoundException      If the DbContext class is not found.
     * @throws InvalidAnimalTypeException  If an invalid animal type is found.
     */
    public void refreshSchedule() throws InvalidAnimalTypeException, SQLException, ClassNotFoundException {
        var db = new DbContext();
        var allAnimals = db.getAllAnimals();
        ArrayList<Treatment> testTreatments = new ArrayList<Treatment>();
        if(isMedicalFormCompleted){
            testTreatments = MedicalTaskInputForm.treatments;
        }
        else{
            testTreatments = db.getAllTreatments();
        }
        allAnimals.addAll(AnimalInputForm.animals);
        try {
            var schedule = new Schedule();
            Map<Integer, ArrayList<ScheduledTask>> schedule1 = schedule.createSchedule(allAnimals, testTreatments);

            outputArea.append("Schedule for " + LocalDate.now().toString() + "\n\n");

            for (var task : schedule1.entrySet()) {
                outputArea.append(String.format("%02d:00", task.getKey()));
                if (!schedule.scheduleFullOnHour(task.getKey())) {
                    outputArea.append(" [+ backup volunteer]");
                }
                outputArea.append("\n");

                for (var j : task.getValue()) {
                    outputArea.append(j.getFormattedTaskDescription());

                    outputArea.append("\n");
                }

                outputArea.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * The main method is the entry point of the generateSchedule application.
     * It creates a new instance of the generateSchedule JFrame and displays it.
     * The method handles SQLException, InvalidAnimalTypeException, and ClassNotFoundException
     * by wrapping them in a RuntimeException.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            generateSchedule frame = null;
            try {
                frame = new generateSchedule();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (InvalidAnimalTypeException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            frame.setVisible(true);
        });
    }
}
