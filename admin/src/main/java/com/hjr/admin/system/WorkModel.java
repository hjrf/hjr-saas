package com.hjr.admin.system;

import org.springframework.data.mongodb.core.mapping.Document;
import util.BaseModel;

@Document(collection = "hjr_work")
public class WorkModel extends BaseModel {
	String workType;
	String workState;
	String workStartDate;
	String workEndDate;
	String workKeci;
	int workSetState;
	int workCompletState;
	int workSetMum;
	int workSubmitMum;
	String questionLibModels;

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getWorkState() {
		return workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	public String getWorkStartDate() {
		return workStartDate;
	}

	public void setWorkStartDate(String workStartDate) {
		this.workStartDate = workStartDate;
	}

	public String getWorkEndDate() {
		return workEndDate;
	}

	public void setWorkEndDate(String workEndDate) {
		this.workEndDate = workEndDate;
	}

	public String getWorkKeci() {
		return workKeci;
	}

	public void setWorkKeci(String workKeci) {
		this.workKeci = workKeci;
	}

	public int getWorkSetState() {
		return workSetState;
	}

	public void setWorkSetState(int workSetState) {
		this.workSetState = workSetState;
	}

	public int getWorkCompletState() {
		return workCompletState;
	}

	public void setWorkCompletState(int workCompletState) {
		this.workCompletState = workCompletState;
	}

	public int getWorkSetMum() {
		return workSetMum;
	}

	public void setWorkSetMum(int workSetMum) {
		this.workSetMum = workSetMum;
	}

	public int getWorkSubmitMum() {
		return workSubmitMum;
	}

	public void setWorkSubmitMum(int workSubmitMum) {
		this.workSubmitMum = workSubmitMum;
	}

	public String getQuestionLibModels() {
		return questionLibModels;
	}

	public void setQuestionLibModels(String questionLibModels) {
		this.questionLibModels = questionLibModels;
	}

}