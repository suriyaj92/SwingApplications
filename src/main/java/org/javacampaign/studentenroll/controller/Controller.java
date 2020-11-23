package org.javacampaign.studentenroll.controller;

import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.javacampaign.studentenroll.model.StudentDetails;
import org.javacampaign.studentenroll.model.Validate;
import org.javacampaign.studentenroll.util.Constants;
import org.javacampaign.studentenroll.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class Controller {
	
	private StudentDetails model;
	
	private View view;
	
	public Controller(StudentDetails m, View v) {
		model = m;
		view = v;
	}
	
	public void initController() {
		view.getMaleRadio().addActionListener(this::genderAction);
		view.getFemaleRadio().addActionListener(this::genderAction);
		view.getSaveButton().addActionListener(this::enrollStudent);
		view.getResetButton().addActionListener(e -> resetForm());
	}
	
	private void genderAction(ActionEvent e) {
		try {
			if(e.getSource() == view.getMaleRadio()) {
				BufferedImage wPic = ImageIO.read(this.getClass().getClassLoader().getResource("male_icon.png"));
				view.getImageLabel().setIcon(new ImageIcon(wPic));
			} else {
				BufferedImage wPic = ImageIO.read(this.getClass().getClassLoader().getResource("female_icon.png"));
				view.getImageLabel().setIcon(new ImageIcon(wPic));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	private void enrollStudent(ActionEvent e) {
		String name = view.getNameTextfield().getText();
		String age = view.getAgeTextfield().getText();
		int proofOfBirthIdx = view.getProofOfBirthCombobox().getSelectedIndex();
		String proofOfBirth = (String) view.getProofOfBirthCombobox().getSelectedItem();
		String emergencyContact = view.getEmergencyContactTextfield().getText();
		Validate retVal = validateForm(name, age, proofOfBirthIdx, emergencyContact);
		if(retVal.isInvalid()) {
			JOptionPane.showMessageDialog(null, retVal.getInvalidMessage(), "Info",
					JOptionPane.WARNING_MESSAGE);
			retVal.getComponent().requestFocus();
			return;
		}
		
		model.setName(name);
		model.setAge(Integer.parseInt(age));
		model.setProofOfBirth(proofOfBirth);
		model.setGender(view.getMaleRadio().isSelected() ? "Male" : "Female");
		model.setEmergencyContact(Long.parseLong(emergencyContact));
		
		ImageIcon imgIcon = (ImageIcon) view.getImageLabel().getIcon();
		Image img = imgIcon.getImage();
		BufferedImage buffrImage = toBufferedImage(img);
		String encodedString = Base64.getEncoder().encodeToString(getImageData(buffrImage));
		model.setImageData(encodedString);
		
		try {
			// TODO Test image data | convert byte[] back to a BufferedImage
//			byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//	        InputStream is = new ByteArrayInputStream(decodedBytes);
//	        BufferedImage newBi = ImageIO.read(is);
//	        ImageIO.write(newBi, "png", new File(System.getProperty(Constants.SAVE_PATH_KEY), "sample.png"));
			
			String path = System.getProperty(Constants.SAVE_PATH_KEY);
			File filePath = new File(path);
			if(!filePath.exists()) {
				filePath.mkdirs();
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(model);
			System.out.println(json);
			
			String schoolName = System.getProperty(Constants.SCHOOL_NAME_KEY);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
			String fileName = schoolName + "_" + dtf.format(LocalDateTime.now()) + ".json";  
			try (Writer writer = new FileWriter(new File(filePath, fileName))) {
				gson.toJson(model, writer);
			}
			JOptionPane.showMessageDialog(null, "Details saved!", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (JsonIOException | HeadlessException | IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Info",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private byte[] getImageData(BufferedImage buffrImage) {
		// get DataBufferBytes from Raster
		WritableRaster raster = buffrImage.getRaster();
		DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
		return (data.getData());
	}

	private BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}
		
		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}
	
	private Validate validateForm(String name, String age, int proofOfBirthIdx, String emergencyContact) {
		if(!validName(name)) {
			return Validate.inValid("Name should be 3 or above characters!", view.getNameTextfield());
		} else if(!validAge(age)) {
			return Validate.inValid("Age must between 3 and 20!", view.getAgeTextfield());
		} else if(proofOfBirthIdx == 0) {
			return Validate.inValid("Please select proof of birth!", view.getProofOfBirthCombobox());
		} else if(!validContact(emergencyContact)) {
			return Validate.inValid("Enter 10 digit contact number!", view.getEmergencyContactTextfield());
		}
		return Validate.valid();
	}
	
	private boolean empty(String str) {
		return str ==  null || str.trim().isEmpty();
	}
	
	private boolean validName(String str) {
		return !empty(str) && str.length() >= 3;
	}
	
	private boolean validAge(String str) {
		if(!empty(str)) {
			try {
				int age = Integer.parseInt(str);
				return age >= 3 && age <= 20;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return false;
	}
	
	private boolean validContact(String str) {
		if(!empty(str) && str.length() == 10) {
			try {
				Long.parseLong(str);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return false;
	}

	public void resetForm() {
		view.getNameTextfield().setText("");
		view.getAgeTextfield().setText("");
		view.getProofOfBirthCombobox().setSelectedIndex(0);
		view.getMaleRadio().setSelected(true);
		view.getEmergencyContactTextfield().setText("");
		try {
			BufferedImage wPic = ImageIO.read(this.getClass().getClassLoader().getResource("male_icon.png"));
			view.getImageLabel().setIcon(new ImageIcon(wPic));
		} catch (IOException e) {}
//		JOptionPane.showMessageDialog(null, "Form cleared!", "Info",
//				JOptionPane.INFORMATION_MESSAGE);
		view.getNameTextfield().requestFocus();
	}
	
}