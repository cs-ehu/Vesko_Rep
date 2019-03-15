package domain;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Offer implements Serializable {
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private Integer offerNumber;
	private Date firstDay; // Dates are stored as java.util.Date objects instead of java.sql.Date objects
	private Date lastDay;  // because, they are not well stored in db4o as java.util.Date objects
	private float price;   // This is coherent because objects of java.sql.Date are objects of java.util.Date 
	@XmlIDREF
	@OneToOne
	private RuralHouse ruralHouse;
	@OneToOne(orphanRemoval=true)
	@XmlIDREF
	private Reserva reservaActual;
	private boolean booked;
	
	public Offer(){}

	public Offer(Date firstDay, Date lastDay, float price, RuralHouse ruralHouse, boolean booked){
		  this.firstDay=firstDay;
		  this.lastDay=lastDay;
		  this.price=price;
		  this.ruralHouse=ruralHouse;
		  booked=false;
		  
	}
	
	public boolean isBooked() {
		return booked;
	}
	public void setReserva (Reserva r){
		this.reservaActual=r;
		booked=true;
	}
	public Reserva getReserva (){
		return reservaActual;
	}
	public void removeReserva (){
		reservaActual = null;
		booked=false;
	}
	/**
	 * Get the house number of the offer
	 * 
	 * @return the house number
	 */
	public RuralHouse getRuralHouse() {
		return this.ruralHouse;
	}

	/**
	 * Set the house number to a offer
	 * 
	 * @param house number
	 */
	public void setRuralHouse(RuralHouse ruralHouse) {
		this.ruralHouse = ruralHouse;
	}


	/**
	 * Get the offer number
	 * 
	 * @return offer number
	 */
	public int getOfferNumber() {
		return this.offerNumber;
	}

	

	/**
	 * Get the first day of the offer
	 * 
	 * @return the first day
	 */
	public Date getFirstDay() {
		return this.firstDay;
	}

	/**
	 * Set the first day of the offer
	 * 
	 * @param firstDay
	 *            The first day
	 */
	public void setFirstDay(Date firstDay) {
		this.firstDay = firstDay;
	}

	/**
	 * Get the last day of the offer
	 * 
	 * @return the last day
	 */
	public Date getLastDay() {
		return this.lastDay;
	}

	/**
	 * Set the last day of the offer
	 * 
	 * @param lastDay
	 *            The last day
	 */
	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}

	/**
	 * Get the price
	 * 
	 * @return price
	 */
	public float getPrice() {
		return this.price;
	}

	/**
	 * Set the price
	 * 
	 * @param price
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(" %4d");//offernum
		sb.append("                    ");
		sb.append("%15s");//rh
		sb.append("    ");
		sb.append("%15s");//fistdate
		sb.append("    ");
		sb.append("%15s");//lastdate
		sb.append("           ");
		sb.append(price);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s=String.format(sb.toString(), offerNumber,ruralHouse,df.format(firstDay),df.format(lastDay));
		return s;
	}
	public String toString2() {
		return offerNumber+";"+firstDay.toString()+";"+lastDay.toString()+";"+price;
	}
}