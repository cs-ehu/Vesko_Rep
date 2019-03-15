package businessLogic;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Client;
import domain.FeedBack;
//import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.Reserva;
import domain.ReservaOfferRhOwnerContainer;
import domain.RuralHouse;
import exceptions.BadDates;
import exceptions.OverlappingClientExists;
import exceptions.OverlappingOfferExists;
import exceptions.OverlappingOwnerExist;

//Service Implementation
@WebService(endpointInterface = "businessLogic.ApplicationFacadeInterfaceWS")
public class FacadeImplementationWS implements ApplicationFacadeInterfaceWS {

	public Reserva createReserva(Offer offer, Client cliente, Date bookDate,
			String state) {
		DataAccess dbManager = new DataAccess();
		Reserva r = dbManager.createReserva(offer, cliente, bookDate);
		dbManager.close();
		return r;
	}

	public void removeReserva(Reserva r) {
		DataAccess dbManager = new DataAccess();
		dbManager.removeReserva(r);
		dbManager.close();
	}

	public Offer createOffer(RuralHouse ruralHouse, Date firstDay,
			Date lastDay, float price) throws OverlappingOfferExists, BadDates {

		DataAccess dbManager = new DataAccess();
		Offer o = null;
		if (firstDay.compareTo(lastDay) >= 0) {
			dbManager.close();
			throw new BadDates();
		}

		boolean b = dbManager.existsOverlappingOffer(ruralHouse, firstDay,
				lastDay);
		if (!b)
			o = dbManager.createOffer(ruralHouse, firstDay, lastDay, price);

		dbManager.close();

		return o;
	}

	public Client createClient(String userName, String password,
			String telegramId, String cuentaBancaria, String email)
			throws OverlappingClientExists {
		DataAccess dbManager = new DataAccess();
		Client c = dbManager.createClient(userName, password, telegramId,
				cuentaBancaria, email);
		dbManager.close();
		return c;

	}

	public Owner createOwner(String userName, String password,
			String telegramId, String cuentaBancaria, String email)
			throws OverlappingOwnerExist {
		DataAccess dbManager = new DataAccess();
		Owner o = dbManager.createOwner(userName, password, telegramId,
				cuentaBancaria, email);
		dbManager.close();
		return o;
	}

	public void updateClinetValidation(Client c) {
		DataAccess dbManager = new DataAccess();
		dbManager.updateClinetValidation(c);
	}

	@WebMethod
	public void updateOwnerValidation(Owner o) {
		DataAccess dbManager = new DataAccess();
		dbManager.updateOwnerValidation(o);
	}

	@WebMethod
	public RuralHouse createRuralHouse(Owner o, String city,
			String description, String address, String roomsNumber) {
		DataAccess dbManager = new DataAccess();
		RuralHouse rh = dbManager.createRuralHouse(o, city, description,
				address, roomsNumber);
		dbManager.close();

		return rh;
	}

	@WebMethod
	public Vector<RuralHouse> getAllRuralHouses() {
		DataAccess dbManager = new DataAccess();

		Vector<RuralHouse> ruralHouses = dbManager.getAllRuralHouses();
		dbManager.close();
		return ruralHouses;

	}

	@WebMethod
	public Vector<Offer> getOffers(RuralHouse rh, Date firstDay, Date lastDay) {

		DataAccess dbManager = new DataAccess();
		Vector<Offer> offers = new Vector<Offer>();
		offers = dbManager.getOffers(rh, firstDay, lastDay);
		dbManager.close();

		return offers;
	}

	@WebMethod
	public Client getClientByUsername(String user) {
		DataAccess dbManager = new DataAccess();
		Client c = dbManager.getClientByUsername(user);
		dbManager.close();

		return c;
	}

	@WebMethod
	public Owner getOwnerByUsername(String user) {
		DataAccess dbManager = new DataAccess();
		Owner o = dbManager.getOwnerByUsername(user);
		dbManager.close();

		return o;
	}

	@WebMethod
	public void addImageToRH(byte[] img, Owner owner, RuralHouse rh) {
		DataAccess dbManager = new DataAccess();
		dbManager.addImageToRH(img, rh, owner);

		dbManager.close();
	}

	@WebMethod
	public RuralHouse getRuralHouse(RuralHouse rh, Owner owner) {
		DataAccess dbManager = new DataAccess();
		RuralHouse rhouse = dbManager.getRuralHouse(owner, rh);

		dbManager.close();
		return rhouse;
	}

	@WebMethod
	public RuralHouse getRuralHousebyHouseNumber(int num) {
		DataAccess dbManager = new DataAccess();
		RuralHouse rhouse = dbManager.getRuralHouseByHouseNumber(num);

		dbManager.close();
		return rhouse;
	}

	@WebMethod
	public void removeRuralHouse(int num) {
		DataAccess dbManager = new DataAccess();
		dbManager.removeRuralHouse(num);
		dbManager.close();
	}

	@WebMethod
	public void close() {
		DataAccess dbManager = new DataAccess();
		dbManager.close();
	}
	
	@WebMethod
	public Reserva getReservaByNum(int n){
		DataAccess dbManager = new DataAccess();
		Reserva r1 =dbManager.getReservaByNum(n);
		dbManager.close();
		return r1;
	}
	@WebMethod
	public Client updateClient(Client c){
		DataAccess dbManager = new DataAccess();
		Client c1 = dbManager.updateClient(c);
		dbManager.close();
		return c1;
	}
	@WebMethod
	public Owner updateOwner(Owner o){
		DataAccess dbManager = new DataAccess();
		Owner o1 = dbManager.updateOwner(o);
		dbManager.close();
		return o1;
	}
	public ReservaOfferRhOwnerContainer getReservaOfferRhOwnerContainer(Reserva r){
		Reserva r1=getReservaByNum(r.getNumResrva());
		return new ReservaOfferRhOwnerContainer(r1);
	}

	@WebMethod
	public ArrayList<ReservaOfferRhOwnerContainer> getListReservaOfferRhOwnerContainer(
			Client c) {
		Client c1 = getClientByUsername(c.getUserName());
		ArrayList<ReservaOfferRhOwnerContainer> listROC = new ArrayList<ReservaOfferRhOwnerContainer>();
		Iterator<Reserva> it = c1.getListaReserva().iterator();
		while (it.hasNext()) {
			Reserva r = it.next();
			Reserva r1 = getReservaByNum(r.getNumResrva());
			listROC.add(new ReservaOfferRhOwnerContainer(r1));
		}
		return listROC;
	}
	@WebMethod
	public FeedBack createFeedBack(String s, int i, RuralHouse rh) {
		DataAccess dbManager = new DataAccess();
		FeedBack fb = dbManager.createFeedBack(i, s, rh);
		dbManager.close();
		return fb;
	}
	@WebMethod
	public void setRated(Reserva r) {
		DataAccess dbManager = new DataAccess();
		dbManager.setRated(r);
		dbManager.close();
	}
}