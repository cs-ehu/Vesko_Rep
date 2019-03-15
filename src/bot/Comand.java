package bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class Comand {
	
	public Comand() {
		// TODO Auto-generated constructor stub
	}
	
	public void sendMessageFromTelegramToClient(long id, String text){
		
			MyBot bot = new MyBot();
   	        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
   	                .setChatId(id)
   	                .setText(text);
   	
   	        		
   	        try {
   	            bot.sendMessage(message); // Call method to send the message
   	        } catch (TelegramApiException e) {
   	            e.printStackTrace();
   	        }
   	    }
	}


