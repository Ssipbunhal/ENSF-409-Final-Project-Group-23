package src.FrontEnd;

import com.mysql.cj.xdevapi.AddStatement;
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

public class MedicalTaskInputForm extends JFrame implements ActionListener {
    public static ArrayList<Treatment> treatments = new ArrayList<Treatment>();
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
    public MedicalTaskInputForm(generateSchedule schedule) throws SQLException, ClassNotFoundException, InvalidAnimalTypeException {
        var test = new DbContext();
        this.schedule = schedule;
        this.treatments =  test.getAllTreatments();

        createUI();
    }

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

    private boolean validateInputs() {


        if (animalNicknameInput.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Animal Nickname cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }

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



        if (taskDescriptionInput.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task Description cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }

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

        if (taskNameInput.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task Name cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // If all inputs are valid, return true
        return true;
    }




    private void createEditTaskButton() {
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 0;
        inputConstraints.gridy = 15;
        inputConstraints.insets = new Insets(5, 10, 5, 10);

        editTaskButton = new JButton("Edit Task");
        editTaskButton.addActionListener(this);
        add(editTaskButton, inputConstraints);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        try {
            if (e.getSource() == submitButton) {
                if (validateInputs()) {
                    addTreatment();
                    dispose();
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
    private void addWindowListenerToShowMainMenu() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                schedule.setVisible(true);
            }
        });
    }

    public void addTreatment() throws InvalidAnimalTypeException {
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
        MedicalTask fetchedTask = new MedicalTask(taskID, taskDescription, taskDuration, taskMaxWindow, taskName);

        Treatment newTreatment = new Treatment(fetchedAnimal, fetchedTask, startHour);
        treatments.add(newTreatment);

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
    private void createAnimalInputFields() {
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
    private void createSubmitButton() {
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 1;
        inputConstraints.gridy = 14; // Adjusted this value
        inputConstraints.insets = new Insets(5, 10, 5, 10);

        submitButton = new JButton("Add Treatment");
        submitButton.addActionListener(this);
        add(submitButton, inputConstraints);
    }
    private void createEditButton() {
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 1;
        inputConstraints.gridy = 14;
        inputConstraints.insets = new Insets(5, 10, 5, 10);

        submitEdit = new JButton("Add Treatment");
        submitEdit.addActionListener(this);
        add(submitEdit, inputConstraints);
    }
    private void createTaskInputFields() {
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
}
