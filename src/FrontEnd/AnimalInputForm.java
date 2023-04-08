package src.FrontEnd;
import src.Animals.*;
import src.Exceptions.InvalidAnimalTypeException;
import src.Treatment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AnimalInputForm extends JFrame implements ActionListener {
    private JButton submitButton;
    public static ArrayList<Animal> animals = new ArrayList<Animal>();
    private JTextField animalIDInput;
    private JTextField animalNicknameInput;
    private JCheckBox orphanedInput;
    private JComboBox animalInput;
    private generateSchedule schedule;


    public AnimalInputForm(generateSchedule schedule) {
        this.schedule = schedule;
        createUI();

    }
    private void createUI() {
        setTitle("WildLife Rescue Centre");
        setSize(500, 500);
        setLayout(new GridBagLayout());
        createAnimalInputFields();
        createSubmitButton();
        addWindowListenerToShowMainMenu();
    }

    private void createSubmitButton() {
        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 1;
        inputConstraints.gridy = 14;
        inputConstraints.insets = new Insets(5, 10, 5, 10);

        submitButton = new JButton("Add Animal");
        submitButton.addActionListener(this);
        add(submitButton, inputConstraints);
    }
    private void addWindowListenerToShowMainMenu() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                schedule.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
            if (e.getSource() == submitButton) {
                try {
                    if(validateInputs()){
                        addAnimal();
                        dispose();

                    }
                } catch (InvalidAnimalTypeException ex) {
                    throw new RuntimeException(ex);
                }
            }
    }

    private void addAnimal() throws InvalidAnimalTypeException {
        String animalType = (String) animalInput.getSelectedItem();
        String animalID = animalIDInput.getText();
        String animalNickname = animalNicknameInput.getText();
        boolean orphaned = orphanedInput.isSelected();
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
        animals.add(fetchedAnimal);

    }
    private boolean validateInputs() {


        if (animalNicknameInput.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Animal Nickname cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        return true;
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
}
