import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class VendingSystem: 
 * 
 * This class represents the Vending Machine which creates the Product Array List,
 * Coins, Bank, and is where user transactions are made.
 * 
 * @author Daniel Jordan
 * @version 1.0
 * @date 2/15/17
 */
public class VendingSystem {

	/*
	 * Instance Data Section.
	 */

	//Instance variable CONSTANTS used to make the Coin objects. 
	private final String QUARTER;
	private final double QUARTER_VALUE;
	private final String DIME;
	private final double DIME_VALUE;
	private final String NICKEL;
	private final double NICKEL_VALUE;
	private final String PENNY;
	private final double PENNY_VALUE;

	//VendingSystem CONSTANT for the initial prompt.
	private static final String INITIAL_PROMPT = "WELCOME TO RADFORD'S VENDING SYSTEM.\n"
			+"Press 1 to Order:\n"
			+"Press 0 to Exit: "; 
	//Class declarations.
	private ModeController controller;
	private Bank bank;

	//Array List of Products that are in the Vending Machine.
	private ArrayList<Product> productList;

	//Arrays of Product attributes.
	private String[] productNameArray;
	private double[] productCostArray;
	private int[] productStockArray;

	//Product references used to create the Product Array List. 
	private String productNameIndex;
	private double productCostIndex;
	private int productStockIndex;

	//Scanner
	private Scanner scanner;

	//System runningTotal
	private double runningTotal;

	/**
	 * Constructor for VendingSystem:
	 *  
	 * This constructor initializes the values for the Product Array List,
	 * Coin instance variables, and the Scanner.
	 * 
	 * @param productCount: count of the Products in the Vending Machine
	 */
	public VendingSystem(int productCount){

		//Initialize the Coin CONSTANTS.
		this.QUARTER = "quarter";
		this.QUARTER_VALUE = 0.25;
		this.DIME = "dime";
		this.DIME_VALUE = 0.10;
		this.NICKEL = "nickel";
		this.NICKEL_VALUE = 0.05;
		this.PENNY = "penny";
		this.PENNY_VALUE = 0.01;

		//Initialize Classes.
		controller = new ModeController();
		bank = new Bank();

		//Initialize Product Array List.
		productList = new ArrayList<Product>();

		//Initialize Product attribute arrays.
		productNameArray = new String[] {"Pepsi", "Mountain Dew", "Dr. Pepper", "7up",
				"Powerade-Grape", "Powerade-Blue"};
		productCostArray = new double[] {1.50, 1.50, 1.75, 1.75, 2.00, 2.00};
		productStockArray = new int[] {25, 25, 25, 25, 25, 25};

		//For loops to create each element of the Array List.
		for(int i = 0; i < productCount; i++){

			//Saving the references to their corresponding array element. 
			productNameIndex = productNameArray[i];
			productCostIndex = productCostArray[i];
			productStockIndex = productStockArray[i];

			//Creating and adding the Products to the Array list.
			productList.add(new Product(productNameIndex,
					productCostIndex,
					productStockIndex));
		}

		//Initialize the Scanner.
		scanner = new Scanner(System.in);

		//Initialize the runningTotal
		runningTotal = 0;

		//Initialize the total coins and dollars in the Bank
		bank.bankSetup(200, 200, 200, 200);
		bank.calculateBankTotal();
	}

	/**
	 * Method start begins the program and is where user input is conducted. 
	 */
	public void start(){

		//Begin system output. 
		System.out.println(INITIAL_PROMPT);
		System.out.print("Input your selection: ");

		//Start of user input.
		int userInput = scanner.nextInt();
		controller.checkInput(userInput);

		//Next steps for customers, after the controller switches to CUSTOMER_MODE.
		if(controller.getState() == 1){

			printMenu();
			//Getting user's order selection. 
			System.out.print("Input the number of the item you wish to purchase: ");
			int selectionNum = scanner.nextInt();

			//Confirming the selection.
			System.out.println("You have selected: \n" + "\n" + findProduct(selectionNum));
			System.out.print("Is this correct? (y/n) : ");
			String verification = scanner.next();

			//If yes to confirmation. 
			if(verification.equalsIgnoreCase("y")){

				double productCost = findProduct(selectionNum).getProductCost();

				System.out.println("\nPlease insert $" + productCost);

				boolean done = false;

				while(!done){

					if(runningTotal < productCost){

						System.out.println("Type 'quarter', 'dime', 'nickel' or 'penny' to insert that coin type : ");
						System.out.print("Type 'exit' to return change : ");
						String usersCoin = scanner.next();
						usersCoin = usersCoin.toLowerCase();

						if(usersCoin.equalsIgnoreCase("exit")){

							runningTotal = productCost;
							done = true;
							bank.getHeldChange();
						}
						else{

							insertCoin(usersCoin);
						}
					}

					else if(runningTotal >= productCost){

						//done = true;
						double change = runningTotal - productCost;
						System.out.println("Dispensed Product:");
						System.out.println(findProduct(selectionNum));
						findProduct(selectionNum).sellProduct();
						System.out.println("Dispensed change: " + bank.dispenseChange(change));
						System.out.println("\nTHANK YOU!");
						runningTotal = 0.0;
						bank.addToBank();

						System.out.print("Would you like to order again? (y/n) ");
						String finalVerification = scanner.next();

						if(finalVerification.equalsIgnoreCase("y")){

							printMenu();
						}

						else if (finalVerification.equalsIgnoreCase("n")){
							System.out.println("HAVE A GREAT DAY!");
							done = true;
						}
					}
				}
			}
			//If no to confirmation.
			else if(verification.equalsIgnoreCase("n")){

				start();
			}

		}

		//Next steps for operators, after the controller to OPERATOR_MODE. 
		else if(controller.getState() == 9999){

			System.out.println("\n");
			System.out.println("SWITCHING TO OPERATOR MODE");
			System.out.println("Please select from the operator options.");

			boolean doneOperations = false;

			while(!doneOperations){

				printOperatorMenu();

				int input = scanner.nextInt();

				//Product Maintenance
				if(input == 1){

					boolean done = false;

					while (!done){

						System.out.println("\t*******************");
						System.out.println("\tPRODUCTS IN MACHINE");
						System.out.println("\t*******************");

						printMenu();

						System.out.print("Select item you wish to stock : ");

						input = scanner.nextInt();
						insertStock(input);

						System.out.print("Continue Stocking? (y/n) : ");

						String continueStocking = scanner.next();

						if(continueStocking.equalsIgnoreCase("n")){
							done = true;
							printOperatorMenu();
						}
					}
				}
				//Bank Maintenance
				else if(input == 2){

					boolean done = false;

					while(!done){

						bank.bankToString();

						System.out.println("Press 1 to withdraw all quarters: ");
						System.out.println("Press 2 to withdraw all dimes: ");
						System.out.println("Press 3 to withdraw all nickels: ");
						System.out.println("Press 4 to withdraw all pennies: ");
						System.out.println("Press 5 to withdraw entire Bank: ");
						System.out.print("Press 6 to exit: ");

						input = scanner.nextInt();

						if(input == 6){
							done = true;
						}
						else{
							bank.bankOperations(input);
						}


					}

				}

				else if(input == 0){

					doneOperations = true;
					System.exit(0);
				}
			}
		}
	}

	/**
	 * Method printMenu, iterates and prints the toString of the Product class.
	 */
	public void printMenu(){

		//Count for the selection number. 
		int selectionNum = 1;
		//Iterator that prints Product's toString.
		for(Product p : productList){

			System.out.println("\n");
			System.out.println(selectionNum + " " + p.toString());
			selectionNum ++;
		}
	}

	/**
	 * Method printOperatorMenu, displays the menu for the 
	 */
	public void printOperatorMenu(){

		System.out.println("\n");
		System.out.println("Press 1 for Stock maintenance.");
		System.out.println("Press 2 for Bank maintenance.");
		System.out.print("Press 0 to exit : ");
	}

	/**
	 * Method findProduct locates the product within the Product Array list
	 * @param input: user's selection number
	 * @return the Product or null if not found
	 */
	public Product findProduct(int input){

		//If, to prevent out of bounds
		if(1 <= input && input <= productList.size()){

			return productList.get(input - 1);
		}

		else{
			return null;
		}
	}

	/**
	 * Method insertCoin takes in the "coin" the user types in and 
	 * creates the Coin object.
	 * @param usersCoin: the "coin" the user types in
	 */
	public void insertCoin(String usersCoin){

		switch(usersCoin){

		case "quarter" : 
			double quarterValue = 0.25;
			Coin quarter = new Coin(usersCoin, quarterValue);
			bank.holdChange(quarter);
			runningTotal = runningTotal + quarter.getCoinValue();
			System.out.println(runningTotal);
			break;

		case "dime" :
			double dimeValue = 0.10;
			Coin dime = new Coin(usersCoin, dimeValue);
			bank.holdChange(dime);
			runningTotal = runningTotal + dime.getCoinValue();
			System.out.println(runningTotal);
			break;

		case "nickel" :
			double nickelValue = 0.05;
			Coin nickel = new Coin(usersCoin, nickelValue);
			bank.holdChange(nickel);
			runningTotal = runningTotal + nickel.getCoinValue();
			System.out.println(runningTotal);
			break;

		case "penny" :
			double pennyValue = 0.01;
			Coin penny = new Coin(usersCoin, pennyValue);
			bank.holdChange(penny);
			runningTotal = runningTotal + penny.getCoinValue();
			System.out.println(runningTotal);
			break;
		}
	}

	public void insertStock(int input){

		switch(input){

		case 1 :
			System.out.println("You have selected " + productNameArray[0] + " :");
			System.out.print("Input the new stock value : ");
			int stockValPepsi = scanner.nextInt();
			productStockArray[0] = stockValPepsi;
			System.out.println("Stock value for " + productNameArray[0] + "\n" +
					"Has been set to : " + productStockArray[0]);
			break;

		case 2 :
			System.out.println("You have selected " + productNameArray[1] + " :");
			System.out.print("Input the new stock value : ");
			int stockValDew = scanner.nextInt();
			productStockArray[1] = stockValDew;
			System.out.println("Stock value for " + productNameArray[1] + "\n" +
					"Has been set to : " + productStockArray[1]);
			break;

		case 3 :
			System.out.println("You have selected " + productNameArray[2] + " :");
			System.out.print("Input the new stock value : ");
			int stockValPepper = scanner.nextInt();
			productStockArray[2] = stockValPepper;
			System.out.println("Stock value for " + productNameArray[2] + "\n" +
					"Has been set to : " + productStockArray[2]);
			break;

		case 4 :
			System.out.println("You have selected " + productNameArray[3] + " :");
			System.out.print("Input the new stock value : ");
			int stockVal7up = scanner.nextInt();
			productStockArray[3] = stockVal7up;
			System.out.println("Stock value for " + productNameArray[3] + "\n" +
					"Has been set to : " + productStockArray[3]);
			break;

		case 5 :
			System.out.println("You have selected " + productNameArray[4] + " :");
			System.out.print("Input the new stock value : ");
			int stockValGrape = scanner.nextInt();
			productStockArray[4] = stockValGrape;
			System.out.println("Stock value for " + productNameArray[4] + "\n" +
					"Has been set to : " + productStockArray[4]);
			break;

		case 6 :
			System.out.println("You have selected " + productNameArray[5] + " :");
			System.out.print("Input the new stock value : ");
			int stockValBlue = scanner.nextInt();
			productStockArray[5] = stockValBlue;
			System.out.println("Stock value for " + productNameArray[5] + "\n" +
					"Has been set to : " + productStockArray[5]);
			break;
		}

	}
}
