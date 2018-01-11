
/**
 * Class Bank:
 * 
 * This class represents the Bank inside the Vending Machine. It keeps count of the Coins
 * and the total for the Bank.
 * 
 * @author Daniel Jordan
 * @version 1.0
 * @date 2/16/17
 */
public class Bank {

	/**
	 * Instance data section
	 */

	//Bank counts.
	private double bankTotal;

	//Bank's object arrays
	private Coin[] heldChangeArray;

	//Variables used for holdChange() and getHeldChange()
	private int coinArrayIndex;
	private int coinArrayLength;

	//Bank's Coin totals
	private double dollarsInQuarters;
	private double dollarsInDimes;
	private double dollarsInNickels;
	private double dollarsInPennies;

	public Bank(){

		//Initialize array used for holdChange()
		heldChangeArray = new Coin[15];

		//Initialize variables for holdChange() and getHeldChange()
		coinArrayIndex = 0;
		coinArrayLength = 0;		
	}

	/**
	 * Method bankSetup, sets up the Bank's total values
	 * @param initialQuarterCount: number of quarters in Bank
	 * @param initialDimeCount: number of dimes in Bank
	 * @param initialNickelCount: number of nickels in Bank
	 * @param initialPennyCount: number of pennies in Bank
	 */
	public void bankSetup(double initialQuarterCount, double initialDimeCount, double initialNickelCount, double initialPennyCount){

		this.dollarsInQuarters = initialQuarterCount * 0.25;
		this.dollarsInDimes = initialDimeCount * 0.10;
		this.dollarsInNickels = initialNickelCount * 0.05;
		this.dollarsInPennies = initialPennyCount * 0.01;		
	}

	/**
	 * Method getHeldChange returns the change that was being held, if the user 
	 * decides to not purchase the Product.
	 */
	public void getHeldChange() {

		System.out.println("\n");

		if(heldChangeArray[0] == null){
			System.out.println("No change was input.");
		}

		else if (heldChangeArray[0] != null){

			for(int i = 0; i < coinArrayLength; i++){

				System.out.println("Change dispensed : " + heldChangeArray[i].getCoinType());
			}

		}
		System.out.println("HAVE A GREAT DAY!");
	}

	/**
	 * Method holdChange, temporarily holds the Coins before the user makes the 
	 * purchase. 
	 * @param coin: the Coin being held 
	 */
	public void holdChange(Coin coin) {

		heldChangeArray[coinArrayIndex] = coin;
		coinArrayIndex++;
		coinArrayLength++;
	}

	/**
	 * Method dispenseChange, dispenses the Coins in holdChange back to the user.
	 */
	public double dispenseChange(double change){

		this.bankTotal = bankTotal - change;
		return change;
	}

	/**
	 * Method depositChange, updates the Bank's coinCount and bankTotal.
	 */
	public void depsoitChange(double totalCoinValue){

		this.bankTotal = bankTotal + totalCoinValue;
	}


	/**
	 * @param coinValue: value of the Coin being added to the bank
	 */
	public void addToBank(){

		for(int i = 0; i < coinArrayLength; i++){

			this.bankTotal += heldChangeArray[i].getCoinValue();
		}
	}

	/**
	 * @return the bankTotal
	 */
	public double getBankTotal() {
		return bankTotal;
	}

	/**
	 * @param bankTotal the bankTotal to set
	 */
	public void setBankTotal(double bankTotal) {
		this.bankTotal = bankTotal;
	}

	/**
	 * Method calculateBankTotal calculates totals for the Bank and for individual Coins
	 */
	public void calculateBankTotal(){

		this.bankTotal = dollarsInQuarters + dollarsInDimes + dollarsInNickels + dollarsInPennies;
	}

	/**
	 * Method Bank toString, prints the toString for the Bank
	 * @return toString
	 */
	public void bankToString(){

		System.out.println("\t*******************"); 
		System.out.println("\tQuarter Total: $" + getDollarsInQuarters());
		System.out.println("\tDimes Total: $" + getDollarsInDimes());
		System.out.println("\tNickels Total: $" +  getDollarsInNickels());
		System.out.println("\tPennies Total: $" + getDollarsInPennies());
		System.out.println("\t*******************");
		System.out.println("\tBank Total: $" + getBankTotal());
		System.out.println("\t*******************");
	}

	/**
	 * @return dollarsInQuarters
	 */
	public double getDollarsInQuarters(){
		return dollarsInQuarters;
	}

	/**
	 * @return dollarsInDimes
	 */
	public double getDollarsInDimes(){
		return dollarsInDimes;
	}

	/**
	 * @return dollarsInNickels
	 */
	public double getDollarsInNickels(){
		return dollarsInNickels;
	}

	/**
	 * @return dollarsInPennies
	 */
	public double getDollarsInPennies(){
		return dollarsInPennies;
	}

	/**
	 * Method bankOperations, performs the withdrawals the operator will make
	 */
	public void bankOperations(int input){

		double total = 0;

		switch(input){

		case 1: 
			total = dollarsInQuarters;
			this.dollarsInQuarters = 0;
			System.out.println("Dispensed " + total);
			break;

		case 2:
			total = dollarsInDimes;
			this.dollarsInDimes = 0;
			System.out.println("Dispensed " + total);
			break;

		case 3:
			total = dollarsInNickels;
			this.dollarsInNickels = 0;
			System.out.println("Dispensed " + total);
			break;

		case 4:
			total = dollarsInPennies;
			this.dollarsInPennies = 0;
			System.out.println("Dispensed " + total);
			break;

		case 5:
			total = bankTotal;
			this.bankTotal = 0;
			this.dollarsInQuarters = 0;
			this.dollarsInDimes = 0;
			this.dollarsInNickels = 0;
			this.dollarsInPennies = 0;
			System.out.println("Dispensed " + total);
			break;
		}

		calculateBankTotal();
	}

}