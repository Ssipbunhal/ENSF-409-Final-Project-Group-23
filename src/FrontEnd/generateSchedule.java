package src.FrontEnd;
import src.Animals.*;
import src.Database.DbContext;
import src.Exceptions.InvalidAnimalTypeException;
import src.Schedules.Schedule;
import src.Tasks.MedicalTask;
import src.Tasks.ScheduledTask;
import src.Treatment;
import src.WildlifeRescueCentre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class generateSchedule extends JFrame implements ActionListener {
//    private JTextField taskIDInput;
//    private JTextField taskDescriptionInput;
//    private JTextField taskDurationInput;
//    private JTextField taskMaxWindowInput;
//    private JTextField taskNameInput;
//    private JTextField animalIDInput;
//    private JTextField animalNicknameInput;
//    private JTextField animalSpeciesInput;
//    private JCheckBox orphanedInput;
//    private JComboBox animalInput;
//    private JTextField taskInput;
//    private JTextField startHourInput;
    private JButton submitButton;
    private JButton executeButton;
    private JTextArea outputArea;
//    private ArrayList<Animal> animals;
    private ArrayList<Treatment> treatments = new ArrayList<Treatment>();
    private WildlifeRescueCentre centre = new WildlifeRescueCentre();

    private JButton animalInputButton;
    private JButton medicalTaskInputButton;
    public generateSchedule() throws SQLException, InvalidAnimalTypeException, ClassNotFoundException {
        createUI();
    }
    public generateSchedule(generateSchedule previousSchedule) throws SQLException, InvalidAnimalTypeException, ClassNotFoundException {
        createUI();
    }
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
        executeCode();
    }

//    private void createAnimalInputFields() {
//        GridBagConstraints inputConstraints = new GridBagConstraints();
//        inputConstraints.gridx = 0;
//        inputConstraints.gridy = 1;
//        inputConstraints.insets = new Insets(0, 10, 10, 10);
//
//        String[] animalChoices = {"fox", "coyote", "beaver", "porcupine", "raccoon"};
//        animalInput = new JComboBox<>(animalChoices);
//        add(new JLabel("Animal Type: "), inputConstraints);
//        inputConstraints.gridx = 1;
//        add(animalInput, inputConstraints);
//        inputConstraints.gridx = 0;
//        inputConstraints.gridy = 2;
//        animalIDInput = new JTextField(10);
//        add(new JLabel("Animal ID: "), inputConstraints);
//        inputConstraints.gridx = 1;
//        add(animalIDInput, inputConstraints);
//
//        inputConstraints.gridx = 0;
//        inputConstraints.gridy = 3;
//        animalNicknameInput = new JTextField(10);
//        add(new JLabel("Animal Nickname: "), inputConstraints);
//        inputConstraints.gridx = 1;
//        add(animalNicknameInput, inputConstraints);
//
//
//        inputConstraints.gridx = 0;
//        inputConstraints.gridy = 4;
//        orphanedInput = new JCheckBox("Orphaned");
//        add(orphanedInput, inputConstraints);
//
//        inputConstraints.gridy = 6;
//        inputConstraints.gridy = 7;
//    }



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
                executeCode();
            } catch (InvalidAnimalTypeException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

    }


    public void executeCode() throws InvalidAnimalTypeException, SQLException, ClassNotFoundException {
        var test = new DbContext();
        var testAnimal = test.getAllAnimals();
        var testTreatments = test.getAllTreatments();
        testTreatments.addAll(MedicalTaskInputForm.treatments);
        testAnimal.addAll(AnimalInputForm.animals);
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
                    outputArea.append(j.getFormattedTaskDescription());

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
