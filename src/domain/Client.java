package domain;

import java.io.Serializable;
import java.util.Vector;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import businessLogic.GenerateValidationCode;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Client implements Serializable{
	@XmlID
	@Id
	private String userName;
	private String password;
	private String telegramId;
	private String cuentaBancaria;
	private String email;
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Embedded
	private Vector<Reserva> listaReserva;
	private int validationNumber;
	private boolean validated;
	private boolean sendBookByEmail;
	private boolean sendBookByTelegram;
	
	public Client(){}

	public Client(String userName, String password, String telegramId,
			String cuentaBancaria, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.telegramId = telegramId;
		this.cuentaBancaria = cuentaBancaria;
		this.email = email;
		listaReserva = new Vector<Reserva>();
		GenerateValidationCode gvc = new GenerateValidationCode();
		validationNumber = gvc.getValidationNumber();
		validated=false;
		sendBookByEmail=false;
		sendBookByTelegram=false;
	}
	
	public boolean isValidated() {
		return validated;
	}

	public boolean isSendBookByEmail() {
		return sendBookByEmail;
	}

	public void setSendBookByEmail(boolean sendBookByEmail) {
		this.sendBookByEmail = sendBookByEmail;
	}

	public boolean isSendBookByTelegram() {
		return sendBookByTelegram;
	}

	public void setSendBookByTelegram(boolean sendBookByTelegram) {
		this.sendBookByTelegram = sendBookByTelegram;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}


	public int getValidationNumber() {
		return validationNumber;
	}

	public void setValidationNumber(int validationNumber) {
		this.validationNumber = validationNumber;
	}

	public void addReserva(Reserva r) {
		listaReserva.addElement(r);
	}

	public void removeReserva(Reserva r) {
		listaReserva.remove(r);
	}

	public Vector<Reserva> getListaReserva() {
		return listaReserva;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("username: " + this.userName + " password: " + this.password
				+ " telegramId: " + this.telegramId + " cuentaBancaria: "
				+ this.cuentaBancaria + " email: " + this.email);

		return sb.toString();
	}

}