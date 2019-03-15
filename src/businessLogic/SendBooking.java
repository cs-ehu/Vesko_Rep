package businessLogic;

import bot.Comand;
import domain.Client;
import domain.Owner;
import domain.Reserva;

public class SendBooking {
	
	public SendBooking() {
		// TODO Auto-generated constructor stub
	}
	// 87664746 -- DANI
	// 169540342 -- HARITZ
	// 236834006 -- JORTXX

	public void sendBookToTelegram(Client c,Owner o, Reserva r, String type){
		ApplicationFacadeInterfaceWS facade = new FacadeImplementationWS();
		Reserva reserva = facade.getReservaByNum(r.getNumResrva());
	if(type.equals("Client")){
		Long id = Long.parseLong(c.getTelegramId());
		Comand cmd  = new Comand();
		StringBuilder sb = new StringBuilder();
		sb.append("Acabas de realizar la siguiente reserva : \n");
		sb.append(reserva.toString2()+"\n");
		sb.append("Gracias por utilizar nuestra aplicación");
		cmd.sendMessageFromTelegramToClient(id, sb.toString());
	}
	else if(type.equals("Owner")){
		Long id = Long.parseLong(o.getTelegramId());
		Comand cmd  = new Comand();
		StringBuilder sb = new StringBuilder();
		sb.append("Acaban de realizar la siguiente reserva de tu casa : \n");
		sb.append(reserva.toString2()+"\n");
		cmd.sendMessageFromTelegramToClient(id, sb.toString());
	}
		
	}


}
