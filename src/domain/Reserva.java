package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
public class Reserva implements Serializable{
	@OneToOne(fetch=FetchType.EAGER)
	private Offer offer;
	@GeneratedValue
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	private Integer numResrva;
	@XmlIDREF
	@OneToOne(fetch=FetchType.EAGER)
	private Client cliente;
	private Date bookDate;
	private boolean isRated;
	
	public Reserva(Offer offer, Client cliente, Date bookDate) {
		super();
		this.offer = offer;
		this.cliente = cliente;
		this.bookDate = bookDate;
		isRated=false;
	}
	public Reserva(){}

	public Offer getOffer() {
		return offer;
	}

	
	public boolean isRated() {
		return isRated;
	}
	public void setRated(boolean isRated) {
		this.isRated = isRated;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public int getNumResrva() {
		return numResrva;
	}

	public void setNumResrva(int numResrva) {
		this.numResrva = numResrva;
	}

	public Client getCliente() {
		return cliente;
	}

	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	/*public String toString(){
		return  offer.toString();
	}*/
	public String toString2 (){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = offer.getRuralHouse().getCity();
		String add = offer.getRuralHouse().getAddress();
		StringBuilder sb = new StringBuilder();
		sb.append(s + " ");
		sb.append(add + " ");
		sb.append("from: " + df.format(offer.getFirstDay()));
		sb.append(" to: " + df.format(offer.getLastDay()));
		return sb.toString();
	}

}