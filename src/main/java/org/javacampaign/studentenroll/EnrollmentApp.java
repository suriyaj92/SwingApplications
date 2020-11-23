package org.javacampaign.studentenroll;

import java.io.File;

import javax.swing.SwingUtilities;

import org.javacampaign.studentenroll.controller.Controller;
import org.javacampaign.studentenroll.model.StudentDetails;
import org.javacampaign.studentenroll.util.Constants;
import org.javacampaign.studentenroll.view.View;

public class EnrollmentApp {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			StudentDetails m = new StudentDetails();
			View v = new View("Student Enrollment App");
			Controller c = new Controller(m, v);
			c.initController();
			c.resetForm();
			System.setProperty(Constants.SCHOOL_NAME_KEY, "ABC");
			System.setProperty(Constants.SAVE_PATH_KEY,
					System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "student_details");
		});
	}
}