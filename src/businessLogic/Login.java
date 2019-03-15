package businessLogic;

import dataAccess.DataAccess;
import exceptions.NoExistsClient;
import exceptions.NoExistsOwner;

public class Login {
	
	public Login() {

	}
 public boolean doLogin(String username, String password, String type)throws NoExistsClient,NoExistsOwner{
	 FacadeImplementationWS facade = new FacadeImplementationWS(); 
	 if (type.equals("Client")){
			try{
			String passwordUsername = facade.getClientByUsername(username).getPassword();
			return passwordUsername.equals(password);
			}catch(NullPointerException e){
				throw new NoExistsClient();
			}
		}else {
			assert type.equals("Owner");
			try{
			String passwordUsername = facade.getOwnerByUsername(username).getPassword();
			return passwordUsername.equals(password);
			}catch(NullPointerException e){
				throw new NoExistsOwner();
			}
		}
	}
}
