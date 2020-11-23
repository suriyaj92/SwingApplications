package org.javacampaign.studentenroll.view;

import java.awt.BorderLayout;
import java.awt.Label;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;


public class View {
	// View uses Swing framework to display UI to user
	private JFrame frame;
	private JLabel nameLabel;
	private JTextField nameTextfield;
	private JLabel ageLabel;
	private JTextField ageTextfield;
	private JLabel proofOfBirthLabel;
	private JComboBox<String> proofOfBirthCombobox;
	private JLabel genderLabel;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private JLabel emergencyContactLabel;
	private JTextField emergencyContactTextfield;
	private JLabel imageLabel;
	private JButton saveButton;
	private JButton resetButton;
	
	private String wrapStr = "span, grow, wrap";

	public View(String title) {
		frame = new JFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(320, 480);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		// Create UI elements
		nameLabel = new JLabel("Name :");
		ageLabel = new JLabel("Age :");
		proofOfBirthLabel = new JLabel("Proof Of Birth :");
		genderLabel = new JLabel("Gender :");
		emergencyContactLabel = new JLabel("Emergency Contact :");
		imageLabel = new JLabel();
		
		nameTextfield = new JTextField();
		ageTextfield = new JTextField();
		proofOfBirthCombobox = new JComboBox<>(new String[] {"Select", "Adhaar", "Birth Certificate", "Ration Card"});
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		ButtonGroup genderBtnGroup = new ButtonGroup();
		genderBtnGroup.add(maleRadio);
		genderBtnGroup.add(femaleRadio);
		emergencyContactTextfield = new JTextField();
		
		saveButton = new JButton("Save");
		resetButton = new JButton("Reset");
		
		// Add UI element to frame
		JPanel panel = new JPanel(new MigLayout("wrap 3"));
	    panel.add(nameLabel);
	    panel.add(nameTextfield, wrapStr);
	    panel.add(ageLabel);
	    panel.add(ageTextfield, wrapStr);
	    panel.add(proofOfBirthLabel);
	    panel.add(proofOfBirthCombobox, wrapStr);
	    panel.add(genderLabel);
	    panel.add(maleRadio);
	    panel.add(femaleRadio);
	    panel.add(emergencyContactLabel);
	    panel.add(emergencyContactTextfield, wrapStr);
	    panel.add(imageLabel, "cell 0 5 3 3");
	    panel.add(new Label());
	    panel.add(saveButton);
	    panel.add(resetButton);
		frame.getContentPane().add(panel);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public JTextField getNameTextfield() {
		return nameTextfield;
	}

	public void setNameTextfield(JTextField nameTextfield) {
		this.nameTextfield = nameTextfield;
	}

	public JLabel getAgeLabel() {
		return ageLabel;
	}

	public void setAgeLabel(JLabel ageLabel) {
		this.ageLabel = ageLabel;
	}

	public JTextField getAgeTextfield() {
		return ageTextfield;
	}

	public void setAgeTextfield(JTextField ageTextfield) {
		this.ageTextfield = ageTextfield;
	}

	public JLabel getProofOfBirthLabel() {
		return proofOfBirthLabel;
	}

	public void setProofOfBirthLabel(JLabel proofOfBirthLabel) {
		this.proofOfBirthLabel = proofOfBirthLabel;
	}

	public JComboBox<String> getProofOfBirthCombobox() {
		return proofOfBirthCombobox;
	}

	public void setProofOfBirthCombobox(JComboBox<String> proofOfBirthCombobox) {
		this.proofOfBirthCombobox = proofOfBirthCombobox;
	}

	public JLabel getGenderLabel() {
		return genderLabel;
	}

	public void setGenderLabel(JLabel genderLabel) {
		this.genderLabel = genderLabel;
	}

	public JRadioButton getMaleRadio() {
		return maleRadio;
	}

	public void setMaleRadio(JRadioButton maleRadio) {
		this.maleRadio = maleRadio;
	}

	public JRadioButton getFemaleRadio() {
		return femaleRadio;
	}

	public void setFemaleRadio(JRadioButton femaleRadio) {
		this.femaleRadio = femaleRadio;
	}

	public JLabel getEmergencyContactLabel() {
		return emergencyContactLabel;
	}

	public void setEmergencyContactLabel(JLabel emergencyContactLabel) {
		this.emergencyContactLabel = emergencyContactLabel;
	}

	public JTextField getEmergencyContactTextfield() {
		return emergencyContactTextfield;
	}

	public void setEmergencyContactTextfield(JTextField emergencyContactTextfield) {
		this.emergencyContactTextfield = emergencyContactTextfield;
	}

	public JLabel getImageLabel() {
		return imageLabel;
	}

	public void setImageLabel(JLabel imageLabel) {
		this.imageLabel = imageLabel;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JButton getResetButton() {
		return resetButton;
	}

	public void setResetButton(JButton resetButton) {
		this.resetButton = resetButton;
	}
	
}