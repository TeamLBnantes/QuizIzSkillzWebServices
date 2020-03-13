package fr.dawan.formation.AppQCMMono.Models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import fr.dawan.formation.AppQCMMono.Enum.TypeMultimedia;

@Entity
public class Multimedia extends Entitie{
	
	@Enumerated(EnumType.STRING)
	TypeMultimedia typeMultimedia;
	String adresseCible;
	
	@OneToOne   
	@JoinColumn(name="mcq_id", unique=true)
	MCQ mcq;
	
	@OneToOne   
	@JoinColumn(name="question_id", unique=true)
	Question question;
	
	@OneToOne   
	@JoinColumn(name="answer_id", unique=true)
	Answer answer;
	
	
	
	
	public MCQ getMcq() {
		return mcq;
	}
	public void setMcq(MCQ mcq) {
		this.mcq = mcq;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	public TypeMultimedia getTypeMultimedia() {
		return typeMultimedia;
	}
	public void setTypeMultimedia(TypeMultimedia typeMultimedia) {
		this.typeMultimedia = typeMultimedia;
	}
	public String getAdresseCible() {
		return adresseCible;
	}
	public void setAdresseCible(String adresseCible) {
		this.adresseCible = adresseCible;
	}
	
	
}
