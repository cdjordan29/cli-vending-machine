
/**
 * Class ModeController:
 * 
 * This class represents the controller of the program. It is in charge of switching input
 * based off of user input. 
 * 
 * @author Daniel Jordan
 * @version 1.0
 * @date 2/13/17
 */
public class ModeController {

	/*
	 * Instance data section
	 */
	
	//Controller state
	private int state;
	
	//System constants 
	private static final int TERMINATE_MODE = 0;
	private static final int CUSTOMER_MODE = 1;
	private static final int OPERATOR_MODE = 9999;
	
	/**
	 * Method checkInput, checks the users' input and sets the state of 
	 * ModeController to either OPERATOR_MODE or CUSTOMER_MODE. 
	 * @param userInput: the input of the user
	 */
	public void checkInput(int userInput){
		
		if(userInput == 1){
			
			setState(CUSTOMER_MODE);
		}
		else if(userInput == 0){
			
			setState(TERMINATE_MODE);
			System.exit(TERMINATE_MODE);
		}
		else if(userInput == 9999){
			
			setState(OPERATOR_MODE);
		}		
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	
}
