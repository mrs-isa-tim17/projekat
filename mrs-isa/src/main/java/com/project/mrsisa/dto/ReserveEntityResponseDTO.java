package com.project.mrsisa.dto;

public class ReserveEntityResponseDTO {

    private boolean successfull;
    private String text;

    public ReserveEntityResponseDTO(){
        successfull = true;
    }

    public ReserveEntityResponseDTO(String text){
        successfull = false;
        this.text = text;
    }

    public boolean isSuccessfull() {
        return successfull;
    }

    public void setSuccessfull(boolean successfull) {
        this.successfull = successfull;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
