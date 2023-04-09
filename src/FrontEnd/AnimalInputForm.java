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

/**
 * This class represents a form for adding animals to the centre.
 * The form allows users to input animal information and add them to the Wild Life Rescue Centre.
 */
public class AnimalInputForm extends JFrame implements ActionListener {
    private JButton submitButton;
    public static ArrayList<Animal> animals = new ArrayList<Animal>();
    private JTextField animalIDInput;
    private JTextField animalNicknameInput;
    private JCheckBox orphanedInput;
    private JComboBox animalInput;
    private generateSchedule schedule;

    /**
     * Constructor for the AnimalInputForm class.
     * Initializes the form and creates an instance of the current schedule.
     *
     * @param schedule The instance of the generateSchedule class.

     */
    public AnimalInputForm(generateSchedule schedule) {
        this.schedule = schedule;
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
        createSubmitButton();
        addWindowListenerToShowMainMenu();
    }

    /**
     * creates the "Add Animal" button and adds it to the form.
     */
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

    /**
     * handles the action events for the form, such as submitting the form.
     * @param e The action event.
     */
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

    /**
     * This method adds an animal to the list of animals on the Wild Life Rescue Centre
     * based on the inputted information in the form.
     *
     * @throws InvalidAnimalTypeException If an invalid animal type is found.
     */
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

    /**
     * This method validates the input fields and returns true if all inputs are valid.
     * Otherwise, it shows an error message and returns false.
     *
     * @return true if all inputs are valid, false otherwise.
     */
    private boolean validateInputs() {


        if (animalNicknameInput.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Animal Nickname cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        return true;
    }

    /**
     * This method creates and adds the animal input fields to the form.
     */
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
