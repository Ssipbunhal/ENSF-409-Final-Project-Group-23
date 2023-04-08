package src.FrontEnd;

import src.Treatment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditTask extends JFrame implements ActionListener {
    private ArrayList<Treatment> treatments;
    private JComboBox<String> taskSelection;
    private JTextField startHourInput;
    private JButton updateButton;
    public EditTask(ArrayList<Treatment> treatments) {
        this.treatments = treatments;
        createUI();
    }

    private void createUI() {
        setTitle("Task Editor");
        setSize(700, 200);
        setLayout(new GridBagLayout());
        createTaskSelection();
        createUpdateStartHourButton();
        createUpdateInput();
    }
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


    private void createUpdateStartHourButton() {
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 1;
        inputConstraints.gridy = 16;
        inputConstraints.insets = new Insets(5, 10, 5, 10);

        updateButton = new JButton("Update Start Hour");
        updateButton.addActionListener(this);
        add(updateButton, inputConstraints);
    }

    private void updateStartHour() {
        try {
            int index = taskSelection.getSelectedIndex();
            int newStartHour = Integer.parseInt(startHourInput.getText());

            if (newStartHour >= 0 && newStartHour <= 23) {
                treatments.get(index).setStartHour(newStartHour);
                JOptionPane.showMessageDialog(this, "Start hour updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Start hour must be between 0 and 23.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Start hour must be a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void refreshTaskList() {
        taskSelection.removeAllItems();
        for (int i = 0; i < treatments.size(); i++) {
            Treatment treatment = treatments.get(i);
            String taskInfo = String.format("Task %d: %s (Animal: %s) Star hour: %s", i, treatment.getTaskToPreform().getFormattedTaskDescription(), treatment.getAnimalToTreat().getAnimalNickname(),treatment.getStartHour());
            taskSelection.addItem(taskInfo);
        }
    }
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


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            updateStartHour();
        }
    }
}
