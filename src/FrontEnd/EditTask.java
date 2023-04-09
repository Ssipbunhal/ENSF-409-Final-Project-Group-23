package src.FrontEnd;

import src.Exceptions.InvalidAnimalTypeException;
import src.Treatment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import static src.FrontEnd.generateSchedule.isMedicalFormCompleted;
/**
 * This class represents a form for editing the start hour of tasks in the schedule
 * The form allows users to select which task they would like to edit
 * and input a new start hour for the selected task.
 */
public class EditTask extends JFrame implements ActionListener {
    private ArrayList<Treatment> treatments;
    private JComboBox<String> taskSelection;
    private JTextField startHourInput;
    private JButton updateButton;

    /**
     * Constructor for the EditTask class.
     * Initializes the form and the treatments selectable .
     *
     * @param treatments An Array list of treatments.
     */

    public EditTask(ArrayList<Treatment> treatments) {
        this.treatments = treatments;
        createUI();
    }

    /**
     * creates the user interface for the form.
     */
    private void createUI() {
        setTitle("Task Editor");
        setSize(700, 200);
        setLayout(new GridBagLayout());
        createTaskSelection();
        createUpdateStartHourButton();
        createUpdateInput();
    }

    /**
     * This method creates the update start hour input and adds it to the form..
     */
    private void createUpdateInput() {
        GridBagConstraints inputConstraints = new GridBagConstraints();

        inputConstraints.gridx = 0;
        inputConstraints.gridy = 15;
        inputConstraints.insets = new Insets(5, 10, 5, 10);
        JLabel startHourLabel = new JLabel("Start Hour:");
        add(startHourLabel, inputConstraints);

        inputConstraints.gridx = 1;
        inputConstraints.fill = GridBagConstraints.HORIZONTAL;
        inputConstraints.weightx = 1.0;

        startHourInput = new JTextField(10);
        startHourInput.addActionListener(this);
        add(startHourInput, inputConstraints);
    }

    /**
     * this method creates the "Update Start Hour" button and adds it to the form.
     */
    private void createUpdateStartHourButton() {
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 1;
        inputConstraints.gridy = 16;
        inputConstraints.insets = new Insets(5, 10, 5, 10);

        updateButton = new JButton("Update Start Hour");
        updateButton.addActionListener(this);
        add(updateButton, inputConstraints);
    }
    /**
     * this method updates the start hour of the selected task in the list of medical tasks
     */
    private void updateStartHour() {
        try {
            int index = taskSelection.getSelectedIndex();
            int newStartHour = Integer.parseInt(startHourInput.getText());

            if (newStartHour >= 0 && newStartHour <= 23) {
                treatments.get(index).setStartHour(newStartHour);
                MedicalTaskInputForm.treatments.get(index).setStartHour(newStartHour);
                isMedicalFormCompleted = true;
                JOptionPane.showMessageDialog(this, "Start hour updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Start hour must be between 0 and 23.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Start hour must be a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * this method updates the list of selectable tasks that can be edited by a user
     */
    private void refreshTaskList() {
        taskSelection.removeAllItems();
        for (int i = 0; i < treatments.size(); i++) {
            Treatment treatment = treatments.get(i);
            String taskInfo = String.format("Task %d: %s (Animal: %s) Star hour: %s", i, treatment.getTaskToPreform().getFormattedTaskDescription(), treatment.getAnimalToTreat().getAnimalNickname(),treatment.getStartHour());
            taskSelection.addItem(taskInfo);
        }
    }

    /**
     * this method creates a combo box of all tasks and adds it to the form
     */
    private void createTaskSelection() {
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 0;
        inputConstraints.gridy = 14;
        inputConstraints.insets = new Insets(5, 10, 5, 10);

        taskSelection = new JComboBox<>();
        refreshTaskList();
        add(new JLabel("Select Task: "), inputConstraints);
        inputConstraints.gridx = 1;
        add(taskSelection, inputConstraints);
    }

    /**
     * handles the action events for the form, such as submitting the form
     * to update the start hour.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            updateStartHour();
        }
    }
}
