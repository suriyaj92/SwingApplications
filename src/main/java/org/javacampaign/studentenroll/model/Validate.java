package org.javacampaign.studentenroll.model;

import javax.swing.JComponent;

public class Validate {
	
	private final boolean invalid;
	
	private final String invalidMessage;
	
	private final JComponent component;

	public Validate(boolean invalid, String invalidMessage, JComponent component) {
		super();
		this.invalid = invalid;
		this.invalidMessage = invalidMessage;
		this.component = component;
	}
	
	public static Validate valid() {
		return new Validate(false, "", null);
	}
	
	public static Validate inValid(String msg, JComponent component) {
		return new Validate(true, msg, component);
	}

	public boolean isInvalid() {
		return invalid;
	}

	public String getInvalidMessage() {
		return invalidMessage;
	}

	public JComponent getComponent() {
		return component;
	}

}
