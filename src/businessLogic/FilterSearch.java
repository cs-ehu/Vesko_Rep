package businessLogic;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import domain.Offer;
import domain.RuralHouse;

public class FilterSearch {
	
	private java.util.Calendar cal = GregorianCalendar.getInstance();
	
	public int getMaxPrice(Vector<RuralHouse> list){
		int max = -1;
		for (RuralHouse rh: list){
			for (Offer o: rh.getOffers()){
				if (!o.isBooked()){
					if (o.getPrice()>max){
						max=(int) o.getPrice();
					}
				}
			}
		}
		return max;
	}
	
	public int getMinPrice(Vector<RuralHouse> list){
		int min = -1;
		for (RuralHouse rh: list){
			for (Offer o: rh.getOffers()){
				if (!o.isBooked()){
					if (o.getPrice()<min){
						min=(int) o.getPrice();
					}
				}
			}
		}
		return min;
	}
	public Vector<Offer> filterOfferByPrice(Vector<Offer> list, int maxPrice) {
		Vector<Offer> list2 = new Vector<Offer>();
		for(Offer o:list){
			if (o.getPrice()<=maxPrice){
				list2.add(o);
			}
		}
		return list2;
	}

	public Vector<Offer> filterOfferByRh(RuralHouse rh,Vector<Offer> list) {
		Vector<Offer> list2 = new Vector<Offer>();
		for(Offer o:list){
			System.out.println(o.getRuralHouse().getHouseNumber().equals(rh.getHouseNumber()));
			if(o.getRuralHouse().getHouseNumber().equals(rh.getHouseNumber())){
				list2.add(o);
			}
		}
		return list2;}
	
	public Vector<RuralHouse> filterByCity(String city,Vector<RuralHouse> list) {
		Vector<RuralHouse> list2 = new Vector<RuralHouse>();
		if(!city.equals("ALL")){
		for(RuralHouse rh:list){
			if(rh.getCity().equals(city)){
				list2.add(rh);
			}
		}
		return list2;}
		else{ return list;}
	}
	public Vector<Offer> filterOfferByCity(String city,Vector<Offer> list) {
		Vector<Offer> list2 = new Vector<Offer>();
		for(Offer o:list){
			if(o.getRuralHouse().getCity().equals(city)){
				list2.add(o);
			}
		}
		return list2;}
	

	public Vector<Offer> filterOfferByFirstDay(Date firstDay,Vector<Offer> list){
		Vector<Offer> listO = new Vector<Offer>();
		for(Offer o: list){
			System.out.println("Data offer:  "+ o.getFirstDay() +"    Date first Day:  "+firstDay + " resultado comparacion :" +o.getFirstDay().compareTo(firstDay) );
			if (o.getFirstDay().compareTo(firstDay)>=0&& !o.isBooked()){
				listO.add(o);
			}
		}
		return listO;
	}
	public Vector<Offer> filterOfferByLastDay(Date lastDay,Vector<Offer> list){
		Vector<Offer> listO = new Vector<Offer>();
		for(Offer o: list){
			if (o.getLastDay().compareTo(lastDay)<=0 && !o.isBooked()){
				System.out.println("Menor o igual que");
				listO.add(o);
			}
		}
		return listO;
	}
	public Vector<String> getAllCity(Vector<RuralHouse> list) {
		Vector<String> lista = new Vector<String>();
		for (RuralHouse rh:list){
			if (!lista.contains(rh.getCity())){
				lista.add(rh.getCity());
			}
		}
		return lista;
	}
	
	public Vector<Offer> filterOfferByNights(String s,Vector<RuralHouse> list){
		if(s.equals("none")){
			Vector<Offer> listO = new Vector<Offer>();
			for(RuralHouse rh : list){
					for(Offer o: rh.offers){
						if(!o.isBooked())
					listO.add(o);
				}
			}
			return listO;
		}else{
	    int n = Integer.parseInt(s);
		Vector<Offer> listO = new Vector<Offer>();
		Vector<Offer> listO2 = new Vector<Offer>();
		for(RuralHouse rh : list){
				for(Offer o: rh.offers){
					if(!o.isBooked())
				listO.add(o);
			}
		}
		for(int i=0; i<listO.size(); i++ ){
			Offer o = listO.get(i);
			System.out.println("tamañoLista = " +listO.size() + "  iteracion   " + i);
			cal.setTime(o.getFirstDay());
			cal.add(Calendar.DAY_OF_MONTH, + n);
			Date date2 = cal.getTime();	
			System.out.println();
			System.out.println("FROM : rh num "+o.getRuralHouse().getHouseNumber());
			System.out.println("input:     " + o.getFirstDay().toString());
			System.out.println("output:    " + date2.toString());
			System.out.println("compare to:" + o.getLastDay().toString());
			System.out.println(date2.compareTo(o.getLastDay())!=0);
			if (date2.compareTo(o.getLastDay())==0){
				listO2.add(o);
			}
		}
		for(Offer o:listO2){
			System.out.println(o);
		}
		return listO2;
	}}
}
