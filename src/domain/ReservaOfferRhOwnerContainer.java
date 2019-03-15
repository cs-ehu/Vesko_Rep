package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReservaOfferRhOwnerContainer {
	private Reserva reserva;
	private Offer offer;
	private RuralHouse rh;
	private Owner owner;

	public ReservaOfferRhOwnerContainer(Reserva r) {
		this.reserva = r;
		offer = r.getOffer();
		rh = offer.getRuralHouse();
		owner = rh.getOwner();
	}

	public ReservaOfferRhOwnerContainer() {

	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public RuralHouse getRh() {
		return rh;
	}

	public void setRh(RuralHouse rh) {
		this.rh = rh;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public String toString (){
		return offer+"";
	}
	public String toStringOfferRh (){
		return offer+" " + rh+" ";
	}
}
