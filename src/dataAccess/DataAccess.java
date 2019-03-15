package dataAccess;

import java.awt.Image;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Client;
import domain.FeedBack;
//import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.Reserva;
import domain.RuralHouse;
import exceptions.OverlappingClientExists;
import exceptions.OverlappingOfferExists;
import exceptions.OverlappingOwnerExist;

public class DataAccess {

	public static String fileName;
	protected static EntityManagerFactory emf;
	protected static EntityManager db;

	ConfigXML c;
	public boolean debuger = false;

	public DataAccess() {

		c = ConfigXML.getInstance();

		/*
		 * System.out.println("Creating objectdb instance => isDatabaseLocal: "
		 * + c.isDatabaseLocal() + " getDatabBaseOpenMode: " +
		 * c.getDataBaseOpenMode());
		 */
		String filename = c.getDbFilename();

		if (c.isDatabaseLocal()) {

			emf = Persistence.createEntityManagerFactory(c.getDbFilename());
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());
			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":"
							+ c.getDatabasePort() + "/" + c.getDbFilename(),
					properties);
			db = emf.createEntityManager();
		}
	}

	/**
	 * Crea un cliente en la base de datos
	 * 
	 * @param String
	 *            username
	 * @param String
	 *            password
	 * @throws OverlappingClientExists
	 */
	@WebMethod
	public Client createClient(String username, String password,
			String telegramId, String cuentaBancaria, String email)
			throws OverlappingClientExists {
		Client cliente = new Client(username, password, telegramId,
				cuentaBancaria, email);
		if (!existClient(username)) {
			db.getTransaction().begin();
			db.persist(cliente);
			db.getTransaction().commit();
		} else
			throw new OverlappingClientExists();
		if (debuger) {
			System.out.println("In createClient: " + cliente.toString());
		}
		return cliente;
	}

	/**
	 * Crea un propietario en la base de datos
	 * 
	 * @param String
	 *            username
	 * @param String
	 *            password
	 * @throws OverlappingOwnerExist
	 */
	@WebMethod
	public Owner createOwner(String username, String password,
			String telegramId, String cuentaBancaria, String email)
			throws OverlappingOwnerExist {
		Owner dueno = new Owner(username, password, telegramId, cuentaBancaria,
				email);
		if (!existOwner(username)) {
			db.getTransaction().begin();
			db.persist(dueno);
			db.getTransaction().commit();
			if (debuger) {
				System.out.println("In createOwner: " + dueno.toString());
			}
		} else
			throw new OverlappingOwnerExist();
		return dueno;
	}
	@WebMethod
	public Reserva createReserva(Offer offer, Client cliente, Date bookDate) {
		Reserva reserva = new Reserva(offer, cliente, bookDate);
		Client c = getClientByUsername(cliente.getUserName());
		Offer o = getOfferByNum(offer.getOfferNumber());
		db.getTransaction().begin();
		c.addReserva(reserva);
		o.setReserva(reserva);
		db.persist(reserva);
		db.persist(o);
		db.persist(c);
		db.getTransaction().commit();
		return reserva;
	}

	/**
	 * Crea una casa rural en la base de datos y actualiza la lista de casas del
	 * propietario con esta casa
	 * 
	 * @param String
	 *            city
	 * @param String
	 *            description
	 * @param Owner
	 *            o
	 */
	@WebMethod
	public RuralHouse createRuralHouse(Owner o, String city,
			String description, String address, String roomsNumber) {
		Owner owner = getOwnerByUsername(o.getUserName());
		RuralHouse rh = new RuralHouse(city, description, address, roomsNumber,
				owner);
		db.getTransaction().begin();
		owner.addRhList(rh);
		db.persist(rh);
		db.getTransaction().commit();
		if (debuger) {
			System.out.println("In createRuralHouse: " + rh.toString());
			System.out.println(owner.toString());
		}
		return rh;
	}
	@WebMethod
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay,
			Date lastDay, float price) {
		System.out.println(">> DataAccess: createOffer=> ruralHouse= "
				+ ruralHouse + " firstDay= " + firstDay + " lastDay=" + lastDay
				+ " price=" + price);

		try {
			RuralHouse rh = db.find(RuralHouse.class,
					ruralHouse.getHouseNumber());

			db.getTransaction().begin();
			Offer o = rh.createOffer(firstDay, lastDay, price);
			db.persist(o);
			db.getTransaction().commit();
			return o;
		} catch (Exception e) {
			System.out.println("Offer not created: " + e.toString());
			return null;
		}
	}
	@WebMethod
	public void addImageToRH(byte[] img, RuralHouse rh, Owner owner) {
		db.getTransaction().begin();
		RuralHouse rhouse = getRuralHouse(owner, rh);
		rhouse.addImage(img);
		if (debuger) {
			System.out.println("In addImageToRH: " + rhouse.toString());
		}
		db.persist(rhouse);
		db.getTransaction().commit();
	}
	@WebMethod
	public Reserva getReservaByNum(int n) {
		Reserva r = db.find(Reserva.class, n);
		return r;
	}
	@WebMethod
	public void removeReserva(Reserva r) {
		Reserva reserva = getReservaByNum(r.getNumResrva());
		Client c = reserva.getCliente();
		Offer o = reserva.getOffer();
		db.getTransaction().begin();
		c.removeReserva(reserva);
		o.removeReserva();
		db.persist(c);
		db.persist(o);
		db.remove(reserva);
		db.getTransaction().commit();
	}
	@WebMethod
	public Offer getOfferByNum(int n) {
		Offer o = db.find(Offer.class, n);
		return o;
	}
	@WebMethod
	public Vector<byte[]> getImagesFromRH(RuralHouse rh, Owner owner) {
		TypedQuery<RuralHouse> query = db
				.createQuery(
						"SELECT rh.images FROM RuralHouse rh WHERE rh.owner=?1 AND rh.houseNumber=?2",
						RuralHouse.class);
		query.setParameter(1, owner);
		query.setParameter(2, rh.getHouseNumber());
		List<RuralHouse> results = query.getResultList();
		if (debuger) {
			System.out.println("In getImagesFromRH: "
					+ results.get(0).toString());
		}
		return results.get(0).getImages();
	}
	@WebMethod
	public RuralHouse getRuralHouse(Owner owner, RuralHouse rh) {
		TypedQuery<RuralHouse> query = db
				.createQuery(
						"SELECT rh FROM RuralHouse rh WHERE rh.owner=?1 AND rh.houseNumber=?2",
						RuralHouse.class);
		query.setParameter(1, owner);
		System.out.println(rh.toString());
		query.setParameter(2, rh.getHouseNumber());
		List<RuralHouse> colecction = query.getResultList();
		RuralHouse rhouse = colecction.get(0);
		if (debuger) {
			if (rhouse == null) {
				System.out.println("In getRuralHouse: rh = null");
			} else {
				System.out.println("In getRuralHouse: " + rhouse.toString());
			}
		}
		return rhouse;
	}
	@WebMethod
	public Vector<RuralHouse> getAllRuralHouses() {
		System.out.println(">> DataAccess: getAllRuralHouses");
		Vector<RuralHouse> res = new Vector<>();

		TypedQuery<RuralHouse> query = db.createQuery(
				"SELECT c FROM RuralHouse c", RuralHouse.class);
		List<RuralHouse> results = query.getResultList();

		Iterator<RuralHouse> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}
		return res;

	}
	@WebMethod
	public Vector<Offer> getOffers(RuralHouse rh, Date firstDay, Date lastDay) {
		System.out.println(">> DataAccess: getOffers");
		Vector<Offer> res = new Vector<>();
		RuralHouse rhn = db.find(RuralHouse.class, rh.getHouseNumber());
		res = rhn.getOffers(firstDay, lastDay);
		return res;
	}

	/**
	 * devuelve un cliente dado su "username"
	 * 
	 * @param String
	 * @return Client
	 */
	@WebMethod
	public Client getClientByUsername(String username) {
		db.getTransaction().begin();
		Client cliente = db.find(Client.class, username);
		db.getTransaction().commit();
		if (debuger) {
			if (cliente == null) {
				System.out.println("In getClientByUsername: client = null");
			} else {
				System.out.println("In getClientByUsername: "
						+ cliente.toString());
			}
		}
		return cliente;
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	@WebMethod
	public Owner getOwnerByUsername(String username) {
		db.getTransaction().begin();
		Owner propietario = db.find(Owner.class, username);
		if (propietario == null) {
			System.out.println("In getClientByUsername: client = null");
		} else {
			System.out.println("In getClientByUsername: "
					+ propietario.toString());
		}
		db.getTransaction().commit();
		return propietario;
	}
	@WebMethod
	public boolean existsOverlappingOffer(RuralHouse rh, Date firstDay,
			Date lastDay) throws OverlappingOfferExists {
		try {
			RuralHouse rhn = db.find(RuralHouse.class, rh.getHouseNumber());
			if (rhn.overlapsWith(firstDay, lastDay) != null)
				return true;
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
			return true;
		}
		return false;
	}
	@WebMethod
	public boolean existClient(String username) {
		Client cliente = getClientByUsername(username);
		if (cliente == null) {
			return false;
		} else
			return true;
	}
	@WebMethod
	public boolean existOwner(String username) {
		Owner propietario = getOwnerByUsername(username);
		if (propietario == null) {
			return false;
		} else
			return true;
	}
	@WebMethod
	public RuralHouse getRuralHouseByHouseNumber(int num) {
		RuralHouse rh = db.find(RuralHouse.class, num);
		return rh;
	}
	@WebMethod
	public void removeRuralHouse(int num) {
		RuralHouse rh = db.find(RuralHouse.class, num);
		db.getTransaction().begin();
		Owner o = rh.getOwner();
		o.removeRuralHouse(rh);
		db.persist(o);
		db.remove(rh);
		db.getTransaction().commit();
	}
	@WebMethod
	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}
	@WebMethod
	public void updateClinetValidation(Client c){
		Client c1 = db.find(Client.class, c.getUserName());
		db.getTransaction().begin();
		c1.setValidated(true);
		db.persist(c1);
		db.getTransaction().commit();
	}
	@WebMethod
	public void updateOwnerValidation(Owner o){
		Owner o1 = db.find(Owner.class, o.getUserName());
		db.getTransaction().begin();
		o1.setValidated(true);
		db.persist(o1);
		db.getTransaction().commit();
	}
	@WebMethod
	public void remove2(RuralHouse rh) {
		db.detach(rh);
	}
	@WebMethod
	public Client updateClient(Client c) {
		Client c1 = db.find(Client.class, c.getUserName());
		db.getTransaction().begin();
		c1.setCuentaBancaria(c.getCuentaBancaria());
		c1.setPassword(c.getPassword());
		c1.setEmail(c.getEmail());
		c1.setTelegramId(c.getTelegramId());
		c1.setSendBookByEmail(c.isSendBookByEmail());
		c1.setSendBookByTelegram(c.isSendBookByTelegram());
		db.persist(c1);
		db.getTransaction().commit();
		return c1;
	}
	@WebMethod
	public Owner updateOwner(Owner o) {
		Owner o1 = db.find(Owner.class, o.getUserName());
		db.getTransaction().begin();
		o1.setCuentaBancaria(o.getCuentaBancaria());
		o1.setPassword(o.getPassword());
		o1.setEmail(o.getEmail());
		o1.setTelegramId(o.getTelegramId());
		o1.setSendBookByTelegram(o.isSendBookByTelegram());
		db.persist(o1);
		db.getTransaction().commit();
		return o1;
	}
	@WebMethod
	public FeedBack createFeedBack(int i, String s, RuralHouse rh){
		FeedBack fb = new FeedBack(s, i, rh);
		RuralHouse rh1 = db.find(RuralHouse.class, rh.getHouseNumber());
		db.getTransaction().begin();
		rh1.addFeedBack(fb);
		db.persist(rh1);
		db.persist(fb);
		db.getTransaction().commit();
return fb;
	}
	@WebMethod
	public void setRated (Reserva r){
		Reserva r1 = db.find(Reserva.class, r.getNumResrva());
		db.getTransaction().begin();
		r1.setRated(true);
		db.persist(r1);
		db.getTransaction().commit();
	}
}