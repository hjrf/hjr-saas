package com.hjr.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hjr_question_lib")
public class QuestionLibModel extends Baseinfo {
	String queLeiBie;
	String queType;
	int queSelectNum;
	String queNianJi;
	int queDifficult;
	String queTag;
	int queScore;

	public String getQueLeiBie() {
		return queLeiBie;
	}

	public void setQueLeiBie(String queLeiBie) {
		this.queLeiBie = queLeiBie;
	}

	public String getQueType() {
		return queType;
	}

	public void setQueType(String queType) {
		this.queType = queType;
	}

	public int getQueSelectNum() {
		return queSelectNum;
	}

	public void setQueSelectNum(int queSelectNum) {
		this.queSelectNum = queSelectNum;
	}

	public String getQueNianJi() {
		return queNianJi;
	}

	public void setQueNianJi(String queNianJi) {
		this.queNianJi = queNianJi;
	}

	public int getQueDifficult() {
		return queDifficult;
	}

	public void setQueDifficult(int queDifficult) {
		this.queDifficult = queDifficult;
	}

	public String getQueTag() {
		return queTag;
	}

	public void setQueTag(String queTag) {
		this.queTag = queTag;
	}

	public int getQueScore() {
		return queScore;
	}

	public void setQueScore(int queScore) {
		this.queScore = queScore;
	}

}