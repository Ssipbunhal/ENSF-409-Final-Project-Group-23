package src.FrontEnd;
import src.Animals.Animal;
import src.Database.DbContext;
import src.Exceptions.InvalidAnimalTypeException;
import src.Schedules.Schedule;
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
    // Set the title of the JFrame
    private JButton executeButton;
    private JTextArea outputArea;
    private ArrayList<Animal> animals;
    private ArrayList<Treatment> treatments;
    public generateSchedule() {
        createUI();
    }
    public void createUI() {
        setTitle("WildLife Rescue Centre");
        setSize(500, 300);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        executeButton = new JButton("Generate Schedule");
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 1;
        buttonConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        buttonConstraints.insets = new Insets(0, 0, 10, 10);
        executeButton.addActionListener(this);
        add(executeButton, buttonConstraints);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        GridBagConstraints outputConstraints = new GridBagConstraints();
        outputConstraints.gridx = 0;
        outputConstraints.gridy = 0;
        outputConstraints.gridwidth = 2;
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
        } catch (InvalidAnimalTypeException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void executeCode() throws InvalidAnimalTypeException, SQLException, ClassNotFoundException {
        var test = new DbContext();
        var testAnimal = test.getAllAnimals();
        var testTreatments = test.getAllTreatments();

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
                    outputArea.append("* " + j.getTaskDescription());
                    if (j.getQuantity() > 0) {
                        outputArea.append(" (" + j.getQuantity() + ")");
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
