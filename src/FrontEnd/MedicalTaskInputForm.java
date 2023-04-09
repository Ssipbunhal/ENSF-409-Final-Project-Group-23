package src.FrontEnd;
import src.Animals.*;
import src.Database.DbContext;
import src.Exceptions.InvalidAnimalTypeException;
import src.Tasks.MedicalTask;
import src.Treatment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static src.FrontEnd.generateSchedule.isMedicalFormCompleted;

/**
 * This class represents a form for inputting medical tasks for animals.
 * The form allows users to input animal information and task information
 * to create a treatment schedule.
 */
public class MedicalTaskInputForm extends JFrame implements ActionListener {
    public static ArrayList<Treatment> treatments = new ArrayList<Treatment>();
    public static ArrayList<Treatment> all_treatments = new ArrayList<Treatment>();

    private JTextArea outputArea;
    private JTextField animalIDInput;
    private JTextField animalNicknameInput;
    private JCheckBox orphanedInput;
    private JComboBox animalInput;
    private JTextField taskIDInput;
    private JTextField taskDescriptionInput;
    private JTextField taskDurationInput;
    private JTextField taskMaxWindowInput;
    private JTextField taskNameInput;
    private JTextField taskInput;
    private JTextField startHourInput;
    private JButton submitButton;
    private JButton updateStartHourButton;
    private JComboBox taskSelection;
    private JButton submitEdit;

    private JButton editTaskButton;

    private generateSchedule schedule;

    /**
     * Constructor for the MedicalTaskInputForm class.
     * Initializes the form and fetches treatments from the database.
     *
     * @param schedule The instance of the generateSchedule class.
     * @throws SQLException                If there's an issue with the database connection.
     * @throws ClassNotFoundException      If the DbContext class is not found.
     * @throws InvalidAnimalTypeException If an invalid animal type is found.
     */

    public MedicalTaskInputForm(generateSchedule schedule) throws SQLException, ClassNotFoundException, InvalidAnimalTypeException {
        var test = new DbContext();
        this.schedule = schedule;
        if(!isMedicalFormCompleted){
            this.treatments =  test.getAllTreatments();

        }

        createUI();
    }
    /**
     * creates the user interface for the form.
     */
    private void createUI() {
        setTitle("WildLife Rescue Centre");
        setSize(500, 500);
        setLayout(new GridBagLayout());
        createAnimalInputFields();
        createTaskInputFields();
        createSubmitButton();
        addWindowListenerToShowMainMenu();
        createEditTaskButton();
        createStartHourInputField();
    }
    /**
     * This method validates the input fields and returns true if all inputs are valid.
     * Otherwise, it shows an error message and returns false.
     *
     * @return true if all inputs are valid, false otherwise.
     */
    private boolean validateInputs() {

        // Validate animal nickname input
        if (animalNicknameInput.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Animal Nickname cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate start hour input
        try {
            int startHour = Integer.parseInt(startHourInput.getText());
            if (startHour < 0 || startHour > 23) {
                JOptionPane.showMessageDialog(this, "Start Hour must be between 0 and 23.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Start Hour must be a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        // Validate task description input
        if (taskDescriptionInput.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task Description cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate task duration input
        try {
            int taskDuration = Integer.parseInt(taskDurationInput.getText());
            if (taskDuration <= 0) {
                JOptionPane.showMessageDialog(this, "Task Duration must be a positive integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Task Duration must be a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate task max window input
        try {
            int taskMaxWindow = Integer.parseInt(taskMaxWindowInput.getText());
            if (taskMaxWindow <= 0) {
                JOptionPane.showMessageDialog(this, "Task Max Window must be a positive integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Task Max Window must be a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate task name input
        if (taskNameInput.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task Name cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // If all inputs are valid, return true
        return true;
    }



    /**
     * creates the "Edit Task" button and adds it to the form.
     */
    private void createEditTaskButton() {
        // Add layout constraints and create the button
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 0;
        inputConstraints.gridy = 15;
        inputConstraints.insets = new Insets(5, 10, 5, 10);

        editTaskButton = new JButton("Edit Task");
        editTaskButton.addActionListener(this);
        add(editTaskButton, inputConstraints);
    }


    /**
     * handles the action events for the form, such as submitting the form
     * or editing a task.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == submitButton) {
                if (validateInputs()) {
                    addTreatment();
                    dispose();
                    isMedicalFormCompleted = true;
                }
            }
            else if (e.getSource() == editTaskButton) {
                EditTask editTask = new EditTask(treatments);
                editTask.setVisible(true);
            }

        } catch (InvalidAnimalTypeException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * This method adds a window listener to the form that shows the main menu
     * when the form is closed.
     */
    private void addWindowListenerToShowMainMenu() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                schedule.setVisible(true);
            }
        });
    }

    /**
     * This method adds a treatment to the treatments list based on the user's input.
     * It creates an animal, a medical task, and a treatment with the specified start hour.
     *
     * @throws InvalidAnimalTypeException If an invalid animal type is found.
     */
    public void addTreatment() throws InvalidAnimalTypeException {

        // Get user inputs
        String animalType = (String) animalInput.getSelectedItem();
        String animalID = animalIDInput.getText();
        String animalNickname = animalNicknameInput.getText();
        boolean orphaned = orphanedInput.isSelected();
        int startHour = Integer.parseInt(startHourInput.getText());
        String taskID = taskIDInput.getText();
        String taskDescription = taskDescriptionInput.getText();
        int taskDuration = Integer.parseInt(taskDurationInput.getText());
        int taskMaxWindow = Integer.parseInt(taskMaxWindowInput.getText());
        String taskName = taskNameInput.getText();


        // Create Animal object based on user input
        Animal fetchedAnimal;
        switch (animalType) {
            case "fox":
                fetchedAnimal = new Fox(animalID, animalNickname, animalType, orphaned);
                break;
            case "coyote":
                fetchedAnimal = new Coyote(animalID, animalNickname, animalType, orphaned);
                break;
            case "beaver":
                fetchedAnimal = new Beaver(animalID, animalNickname, animalType, orphaned);
                break;
            case "porcupine":
                fetchedAnimal = new Porcupine(animalID, animalNickname, animalType, orphaned);
                break;
            case "raccoon":
                fetchedAnimal = new Raccoon(animalID, animalNickname, animalType, orphaned);
                break;
            default:
                throw new RuntimeException("Invalid animal type");
        }

        // Create MedicalTask object based on user input
        MedicalTask fetchedTask = new MedicalTask(taskID, taskDescription, taskDuration, taskMaxWindow, taskName);

        // Create Treatment object and add it to the treatments list
        Treatment newTreatment = new Treatment(fetchedAnimal, fetchedTask, startHour);
        treatments.add(newTreatment);

        // Clear input fields for next treatment input
        animalIDInput.setText("");
        animalNicknameInput.setText("");
        orphanedInput.setSelected(false);
        startHourInput.setText("");
        taskIDInput.setText("");
        taskDescriptionInput.setText("");
        taskDurationInput.setText("");
        taskMaxWindowInput.setText("");
        taskNameInput.setText("");
    }
    /**
     * This method creates and adds the animal input fields to the form.
     */
    private void createAnimalInputFields() {

        // Add layout constraints and create input fields
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 0;
        inputConstraints.gridy = 1;
        inputConstraints.insets = new Insets(0, 10, 10, 10);

        String[] animalChoices = {"fox", "coyote", "beaver", "porcupine", "raccoon"};
        animalInput = new JComboBox<>(animalChoices);
        add(new JLabel("Animal Type: "), inputConstraints);
        inputConstraints.gridx = 1;
        add(animalInput, inputConstraints);
        inputConstraints.gridx = 0;
        inputConstraints.gridy = 2;
        animalIDInput = new JTextField(10);
        add(new JLabel("Animal ID: "), inputConstraints);
        inputConstraints.gridx = 1;
        add(animalIDInput, inputConstraints);

        inputConstraints.gridx = 0;
        inputConstraints.gridy = 3;
        animalNicknameInput = new JTextField(10);
        add(new JLabel("Animal Nickname: "), inputConstraints);
        inputConstraints.gridx = 1;
        add(animalNicknameInput, inputConstraints);


        inputConstraints.gridx = 0;
        inputConstraints.gridy = 4;
        orphanedInput = new JCheckBox("Orphaned");
        add(orphanedInput, inputConstraints);

        inputConstraints.gridy = 6;
        inputConstraints.gridy = 7;
    }
    /**
     * This method creates and adds the "Add Treatment" submit button to the form.
     */
    private void createSubmitButton() {

        // Add layout constraints and create the button
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 1;
        inputConstraints.gridy = 14; // Adjusted this value
        inputConstraints.insets = new Insets(5, 10, 5, 10);

        submitButton = new JButton("Add Treatment");
        submitButton.addActionListener(this);
        add(submitButton, inputConstraints);
    }


    /**
     * This method creates and adds the task input fields to the form.
     */
    private void createTaskInputFields() {

        // Add layout constraints and create input fields
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 0;
        inputConstraints.gridy = 8;
        inputConstraints.insets = new Insets(0, 10, 10, 10);


        taskIDInput = new JTextField(10);
        add(new JLabel("Task ID: "), inputConstraints);
        inputConstraints.gridx = 1;
        add(taskIDInput, inputConstraints);

        inputConstraints.gridx = 0;
        inputConstraints.gridy = 9;
        taskDescriptionInput = new JTextField(10);
        add(new JLabel("Task Description: "), inputConstraints);
        inputConstraints.gridx = 1;
        add(taskDescriptionInput, inputConstraints);

        inputConstraints.gridx = 0;
        inputConstraints.gridy = 10;
        taskDurationInput = new JTextField(10);
        add(new JLabel("Task Duration: "), inputConstraints);
        inputConstraints.gridx = 1;
        add(taskDurationInput, inputConstraints);

        inputConstraints.gridx = 0;
        inputConstraints.gridy = 11;
        taskMaxWindowInput = new JTextField(10);
        add(new JLabel("Task Max Window: "), inputConstraints);
        inputConstraints.gridx = 1;
        add(taskMaxWindowInput, inputConstraints);

        inputConstraints.gridx = 0;
        inputConstraints.gridy = 12;
        taskNameInput = new JTextField(10);
        add(new JLabel("Task Name: "), inputConstraints);
        inputConstraints.gridx = 1;
        add(taskNameInput, inputConstraints);


    }

    /**
     * This method creates and adds the start hour input field to the form.
     */
    private void createStartHourInputField() {
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 0;
        inputConstraints.gridy = 13;
        inputConstraints.insets = new Insets(5, 10, 5, 10);

        startHourInput = new JTextField(10);
        add(new JLabel("Start Hour: "), inputConstraints);
        inputConstraints.gridx = 1;
        add(startHourInput, inputConstraints);
    }

}
