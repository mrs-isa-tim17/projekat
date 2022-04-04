package com.project.mrsisa.domain;

public class ClientReview {
	private String text;
	private boolean recomendedForPenalty;
	private ProcessingStatus status;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isRecomendedForPenalty() {
		return recomendedForPenalty;
	}
	public void setRecomendedForPenalty(boolean recomendedForPenalty) {
		this.recomendedForPenalty = recomendedForPenalty;
	}
	public ProcessingStatus getStatus() {
		return status;
	}
	public void setStatus(ProcessingStatus status) {
		this.status = status;
	}
	
	
	
}
