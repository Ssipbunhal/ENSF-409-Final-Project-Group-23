//package src.Front_End;
//import src.Animals.Animal;
//import src.Database.DbContext;
//import src.Exceptions.InvalidAnimalTypeException;
//import src.Schedules.Schedule;
//import src.Tasks.ScheduledTask;
//import src.Treatment;
//import src.Tasks.MedicalTask;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.StringWriter;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Map;
//
//
//public class AddMedicalTasks extends JFrame implements ActionListener {
//    private JButton executeButton;
//    private JTextArea outputArea;
//    private JCheckBox medicalCheckbox;
//    private ArrayList<Animal> animals;
//    private ArrayList<Treatment> treatments;
//    private ArrayList<MedicalTask> medicalTasks;
//
//    public AddMedicalTasks() {
//        createUI();
//
//    }
//
//    public void createUI() {
//        setTitle("WildLife Rescue Centre");
//        setSize(500, 400);
//        setLayout(new GridBagLayout());
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//        executeButton = new JButton("Generate Schedule");
//        GridBagConstraints buttonConstraints = new GridBagConstraints();
//        buttonConstraints.gridx = 1;
//        buttonConstraints.gridy = 1;
//        buttonConstraints.anchor = GridBagConstraints.LAST_LINE_END;
//        buttonConstraints.insets = new Insets(0, 0, 10, 10);
//        executeButton.addActionListener(this);
//        add(executeButton, buttonConstraints);
//
//        medicalCheckbox = new JCheckBox("Add Medical Tasks");
//        GridBagConstraints medicalCheckboxConstraints = new GridBagConstraints();
//        medicalCheckboxConstraints.gridx = 0;
//        medicalCheckboxConstraints.gridy = 1;
//        medicalCheckboxConstraints.anchor = GridBagConstraints.LINE_START;
//        medicalCheckboxConstraints.insets = new Insets(0, 10, 10, 0);
//        add(medicalCheckbox, medicalCheckboxConstraints);
//
//        outputArea = new JTextArea();
//        outputArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(outputArea);
//        GridBagConstraints outputConstraints = new GridBagConstraints();
//        outputConstraints.gridx = 0;
//        outputConstraints.gridy = 0;
//        outputConstraints.gridwidth = 2;
//        outputConstraints.fill = GridBagConstraints.BOTH;
//        outputConstraints.weightx = 1.0;
//        outputConstraints.weighty = 1.0;
//        outputConstraints.insets = new Insets(10, 10, 10, 10);
//        add(scrollPane, outputConstraints);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        try {
//            if (e.getSource() == executeButton) {
//                executeCode();
//            }
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public void executeCode() throws InvalidAnimalTypeException, SQLException, ClassNotFoundException {
//        var db = new DbContext();
//        animals = db.getAllAnimals();
//        treatments = db.getAllTreatments();
//
//        if (medicalCheckbox.isSelected()) {
//            medicalTasks = db.getAllMedicalTasks();
//        }
//
//        try {
//            var schedule = new Schedule();
//            Map<Integer, ArrayList<ScheduledTask>> scheduleMap = schedule.createSchedule(animals, treatments, medicalTasks);
//
//            outputArea.append("Schedule for " + LocalDate.now().toString() + "\n\n");
//
//            for (var task : scheduleMap.entrySet()) {
//                outputArea.append(String.format("%02d:00", task.getKey()));
//                if (!schedule.scheduleFullOnHour(task.getKey())) {
//                    outputArea.append(" [+ backup volunteer]");
//                }
//                outputArea.append("\n");
//
//                for (var j : task.getValue()) {
//                    outputArea.append("* " + j.getTaskDescription());
//                    if (j instanceof MedicalTask) {
//                        outputArea.append(" [MEDICAL]");
//                    } else if (j.getTaskType().equals("treatment")) {
//                        outputArea.append(" [TREATMENT]");
//                    }
//                    outputArea.append("\n");
//                }
//                outputArea.append("\n");
//            }
//        } catch (Exception e) {
//            outputArea.append(e.getMessage());
//        }
//    }
//}
//
//
