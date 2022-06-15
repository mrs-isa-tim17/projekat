package com.project.mrsisa.dto;

public class TextDTO {
	private String text;
	private boolean successfull;

	public TextDTO() {
		
	}
	
	public TextDTO(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public boolean isSuccessfull() {
		return successfull;
	}

	public void setSuccessfull(boolean successfull) {
		this.successfull = successfull;
	}
	
}
