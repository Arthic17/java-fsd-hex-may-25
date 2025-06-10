package com.springboot.assignment.dto;

public class MedicalHistoryDto {
	 private String illness;
	    private int numOfYears;
	    private String currentMedication;
	    
		public String getIllness() {
			return illness;
		}
		public void setIllness(String illness) {
			this.illness = illness;
		}
		public int getNumOfYears() {
			return numOfYears;
		}
		public void setNumOfYears(int numOfYears) {
			this.numOfYears = numOfYears;
		}
		public String getCurrentMedication() {
			return currentMedication;
		}
		public void setCurrentMedication(String currentMedication) {
			this.currentMedication = currentMedication;
		}
	    
}
