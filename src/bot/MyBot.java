package bot;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class MyBot extends TelegramLongPollingBot{
	   
	    public void onUpdateReceived(Update update) {
	    	
	    }

	    @Override
	    public String getBotUsername() {
	        return "Scrum";
	    }

	    @Override
	    public String getBotToken() {
	        // TODO
	        return "361709931:AAEqTZk_7WiKhyavgtPbAguWhYKjkYnDLcQ";
	    }
}
