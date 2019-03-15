package businessLogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.Date;

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

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ApplicationFacadeInterfaceWS {
	@WebMethod
	public Reserva createReserva(Offer offer, Client cliente, Date bookDate,
			String state);

	@WebMethod
	public void removeReserva(Reserva r);

	@WebMethod
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay,
			Date lastDay, float price) throws OverlappingOfferExists, BadDates;

	@WebMethod
	public Client createClient(String userName, String password,
			String telegramId, String cuentaBancaria, String email)
			throws OverlappingClientExists;

	@WebMethod
	public Owner createOwner(String userName, String password,
			String telegramId, String cuentaBancaria, String email)
			throws OverlappingOwnerExist;

	@WebMethod
	public void updateClinetValidation(Client c);

	@WebMethod
	public void updateOwnerValidation(Owner o);

	@WebMethod
	public RuralHouse createRuralHouse(Owner o, String city,
			String description, String address, String roomsNumber);

	@WebMethod
	public Vector<RuralHouse> getAllRuralHouses();

	@WebMethod
	public Vector<Offer> getOffers(RuralHouse rh, Date firstDay, Date lastDay);

	@WebMethod
	public Client getClientByUsername(String user);

	@WebMethod
	public Owner getOwnerByUsername(String user);

	@WebMethod
	public void addImageToRH(byte[] img, Owner owner, RuralHouse rh);

	@WebMethod
	public RuralHouse getRuralHouse(RuralHouse rh, Owner owner);

	@WebMethod
	public RuralHouse getRuralHousebyHouseNumber(int num);

	@WebMethod
	public void removeRuralHouse(int num);

	@WebMethod
	public void close();

	@WebMethod
	public Reserva getReservaByNum(int n);
	
	@WebMethod
	public ReservaOfferRhOwnerContainer getReservaOfferRhOwnerContainer(Reserva r);
	
	@WebMethod
	public Client updateClient(Client c);
	
	@WebMethod
	public Owner updateOwner(Owner o);
	
	@WebMethod
	public ArrayList<ReservaOfferRhOwnerContainer> getListReservaOfferRhOwnerContainer(Client c);

	@WebMethod
	public FeedBack createFeedBack(String s, int i, RuralHouse rh);

	@WebMethod
	public void setRated(Reserva r);
}
