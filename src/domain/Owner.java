package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.jdo.annotations.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import businessLogic.GenerateValidationCode;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Owner implements Serializable {
	@XmlID
	@Id
	private String userName;
	private String password;
	@Embedded
	private ArrayList<RuralHouse> rhList;
	private String telegramId;
	private String cuentaBancaria;
	private String email;
	private int validationNumber;
	private boolean validated;
	private boolean sendBookByTelegram;

	public Owner() {
	}

	public Owner(String userName, String password, String telegramId,
			String cuentaBancaria, String email) {
		super();
		this.userName = userName;
		this.password = password;
		rhList = new ArrayList<RuralHouse>();
		this.telegramId = telegramId;
		this.cuentaBancaria = cuentaBancaria;
		this.email = email;
		GenerateValidationCode gvc = new GenerateValidationCode();
		validationNumber = gvc.getValidationNumber();
		validated = false;
		sendBookByTelegram = false;
	}

	public boolean isSendBookByTelegram() {
		return sendBookByTelegram;
	}

	public void setSendBookByTelegram(boolean sendBookByTelegram) {
		this.sendBookByTelegram = sendBookByTelegram;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	public String getTelegramId() {
		return telegramId;
	}

	public void setTelegramId(String telegramId) {
		this.telegramId = telegramId;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getValidationNumber() {
		return validationNumber;
	}

	public void setValidationNumber(int validationNumber) {
		this.validationNumber = validationNumber;
	}

	public Vector<RuralHouse> getRhListAsVector() {
		Vector<RuralHouse> list = new Vector<RuralHouse>();
		for (RuralHouse rh : rhList) {
			list.add(rh);
		}
		return list;
	}

	public ArrayList<RuralHouse> getRhList() {
		return rhList;
	}

	public void addRhList(RuralHouse rh) {
		this.rhList.add(rh);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void removeRuralHouse(RuralHouse rh) {
		rhList.remove(rh);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("username: " + this.userName + " password: " + this.password
				+ " telegramId: " + this.telegramId + " cuentaBancaria: "
				+ this.cuentaBancaria + " email: " + this.email
				+ " sizeArrayRH" + rhList.size() + "\n");
		sb.append("Lista de rh");
		for (RuralHouse rh : this.rhList) {
			sb.append(rh.toString() + " \n");
		}
		return sb.toString();
	}

}