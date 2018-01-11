/**
 * Class VendingSystemDriver:
 * 
 * This class represents the driver for the Vending Machine.
 * 
 * @author Daniel Jordan
 * @version 1.0
 * @date 2/13/17
 */
public class VendingSystemDriver{

	//CONSTANT to set the count for the Products in the Vending Machine.
	private static final int PRODUCT_COUNT = 6;
	
	//System main.
	public static void main (String[] args){

		//Initialize the VendingSystem and starts the program.
		VendingSystem system = new VendingSystem(PRODUCT_COUNT);
		system.start();
	}
}
