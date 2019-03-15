package domain;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.persistence.*;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class RuralHouse implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private Integer houseNumber;
	private String description;
	private String city;
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Embedded
	public Vector<Offer> offers;
	private String address;
	private String roomsNumber;
	@Embedded
	@OneToMany(fetch = FetchType.EAGER)
	private Vector<byte[]> images;
	@XmlIDREF
	@OneToOne(fetch = FetchType.EAGER)
	private Owner owner;
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@Embedded
	private  Vector<FeedBack> listaFeedBack;
	
	
	public RuralHouse(){}
	public RuralHouse(String city, String description, String address,
			String roomsNumber, Owner o) {
		super();
		this.description = description;
		this.city = city;
		this.address = address;

		this.roomsNumber = roomsNumber;
		this.owner = o;
		offers = new Vector<Offer>();
		images = new Vector<byte[]>();
		listaFeedBack= new Vector<FeedBack>();
	}
	public void addFeedBack(FeedBack f){
		listaFeedBack.add(f);
	}
	public Vector<FeedBack> getListaFeedBack(){
		return listaFeedBack;
	}

	public Vector<Offer> getOffers() {
		return offers;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Vector<byte[]> getImages() {
		return images;
	}

	public void addImage(byte[] img) {
		if(images==null)
			images =  new Vector<byte[]>();
		this.images.addElement(img);
	}

	public void setImages(Vector<byte[]> images) {
		this.images = images;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoomsNumber() {
		return roomsNumber;
	}

	public void setRoomsNumber(String roomsNumber) {
		this.roomsNumber = roomsNumber;
	}

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * This method creates an offer with a house number, first day, last day and
	 * price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */
	public Offer createOffer(Date firstDay, Date lastDay, float price) {
		System.out.println("LLAMADA RuralHouse createOffer, offerNumber="
				+ " firstDay=" + firstDay + " lastDay=" + lastDay + " price="
				+ price);
		Offer off = new Offer(firstDay, lastDay, price, this, false);
		offers.add(off);
		return off;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + houseNumber.hashCode();
		return result;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("#" + houseNumber + "  " + city + "  " + address);
		return sb.toString();
	}

	public String toString1() {
		StringBuilder sb = new StringBuilder();
		sb.append("houseNumber: " + this.houseNumber + " description: "
				+ this.description + " city: " + this.city + " address: "
				+ this.address + " roomsNumber: " + this.roomsNumber
				+ " imageSize" + images.size() + "\n");
		sb.append("Offers: \n");
		for (Offer offer : offers) {
			sb.append(offer.toString() + "\n");
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		RuralHouse other = (RuralHouse) obj;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		// if (houseNumber != other.houseNumber) // NO COMPARAR AS� ya que
		// houseNumber NO ES "int" sino objeto de "java.lang.Integer"
		if (!houseNumber.equals(other.houseNumber))
			return false;
		return true;
	}

	/**
	 * This method obtains available offers for a concrete house in a certain
	 * period
	 * 
	 * @param houseNumber
	 *            , the house number where the offers must be obtained
	 * @param firstDay
	 *            , first day in a period range
	 * @param lastDay
	 *            , last day in a period range
	 * @return a vector of offers(Offer class) available in this period
	 */
	public Vector<Offer> getOffers(Date firstDay, Date lastDay) {

		Vector<Offer> availableOffers = new Vector<Offer>();
		Iterator<Offer> e = offers.iterator();
		Offer offer;
		while (e.hasNext()) {
			offer = e.next();
			if ((offer.getFirstDay().compareTo(firstDay) >= 0)
					&& (offer.getLastDay().compareTo(lastDay) <= 0))
				availableOffers.add(offer);
		}
		return availableOffers;

	}

	/**
	 * This method obtains the first offer that overlaps with the provided dates
	 * 
	 * @param firstDay
	 *            , first day in a period range
	 * @param lastDay
	 *            , last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there
	 *         is no overlapping offer
	 */

	public Offer overlapsWith(Date firstDay, Date lastDay) {

		Iterator<Offer> e = offers.iterator();
		Offer offer = null;
		while (e.hasNext()) {
			offer = e.next();
			if ((offer.getFirstDay().compareTo(lastDay) < 0)
					&& (offer.getLastDay().compareTo(firstDay) > 0))
				return offer;
		}
		return null;
	}
}