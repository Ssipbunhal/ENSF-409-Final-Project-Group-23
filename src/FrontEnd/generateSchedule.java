package src.FrontEnd;
import src.Animals.*;
import src.Database.DbContext;
import src.Exceptions.InvalidAnimalTypeException;
import src.Schedules.Schedule;
import src.Tasks.MedicalTask;
import src.Tasks.ScheduledTask;
import src.Treatment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;


public class generateSchedule extends JFrame implements ActionListener {
    private JTextField taskIDInput;
    private JTextField taskDescriptionInput;
    private JTextField taskDurationInput;
    private JTextField taskMaxWindowInput;
    private JTextField taskNameInput;
    private JTextField animalIDInput;
    private JTextField animalNicknameInput;
    private JTextField animalSpeciesInput;
    private JCheckBox orphanedInput;
    private JComboBox animalInput;
    private JTextField taskInput;
    private JTextField startHourInput;
    private JButton submitButton;
    private JButton executeButton;
    private JTextArea outputArea;
    private ArrayList<Animal> animals;
    private ArrayList<Treatment> treatments = new ArrayList<Treatment>();

    public generateSchedule() {
        createUI();
    }

    public void createUI() {
        setTitle("WildLife Rescue Centre");
        setSize(1000, 800);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createAnimalInputFields();
        createTaskInputFields();
        createStartHourInputField();
        createSubmitButton();
        createGenerateScheduleButton();
        createOutputArea();
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

    private void createSubmitButton() {
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 1;
        inputConstraints.gridy = 14; // Adjusted this value
        inputConstraints.insets = new Insets(5, 10, 5, 10);

        submitButton = new JButton("Add Treatment");
        submitButton.addActionListener(this);
        add(submitButton, inputConstraints);
    }

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


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        try {
            if (e.getSource() == executeButton) {
                executeCode();
            }
             else if (e.getSource() == submitButton) {
                addTreatment();
            }
        } catch (InvalidAnimalTypeException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

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

    public void executeCode() throws InvalidAnimalTypeException, SQLException, ClassNotFoundException {
        var test = new DbContext();
        var testAnimal = test.getAllAnimals();
        var testTreatments = test.getAllTreatments();
        testTreatments.addAll(treatments);

        try {
            var schedule = new Schedule();
            Map<Integer, ArrayList<ScheduledTask>> schedule1 = schedule.createSchedule(testAnimal, testTreatments);

            outputArea.append("Schedule for " + LocalDate.now().toString() + "\n\n");

            for (var task : schedule1.entrySet()) {
                outputArea.append(String.format("%02d:00", task.getKey()));
                if (!schedule.scheduleFullOnHour(task.getKey())) {
                    outputArea.append(" [+ backup volunteer]");
                }
                outputArea.append("\n");

                for (var j : task.getValue()) {
                    outputArea.append("* " + j.getNormalTaskDescription());

                    if (j.getQuantity() > 0) {
                        outputArea.append(" (" + j.getQuantity()+")" );
                    }
                    outputArea.append("\n");
                }

                outputArea.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            generateSchedule frame = new generateSchedule();
            frame.setVisible(true);
        });
    }
}
