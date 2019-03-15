package domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class FeedBack {

	private String feedBack;
	private int grade;
	@XmlIDREF
	@OneToOne
	private RuralHouse rh;

	public FeedBack(){}
	
	public FeedBack(String feedBack, int grade, RuralHouse rh) {
		super();
		this.feedBack = feedBack;
		this.grade = grade;
		this.rh=rh;
	}
	

	public RuralHouse getRh() {
		return rh;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String toString(){
		return feedBack + " " + grade + " "+ rh.toString();
	}
}
