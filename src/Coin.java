/**
 * Class Coin:
 * 
 * This class represents the Coins that will be input by the user into the
 * Vending Machine.
 * 
 * @author Daniel Jordan
 * @version 1.0
 * @date 2/16/17
 *
 */
public class Coin {

	//Instance Data
	private String coinType;
	private double coinValue;
	
	//Constructor for Coin
	public Coin(String _coinType, double _coinValue){
		
		setCoinType(_coinType);
		setCoinValue(_coinValue);
	}

	/**
	 * @return the coinType
	 */
	public String getCoinType() {
		return coinType;
	}

	/**
	 * @param coinType the coinType to set
	 */
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	/**
	 * @return the coinValue
	 */
	public double getCoinValue() {
		return coinValue;
	}

	/**
	 * @param coinValue the coinValue to set
	 */
	public void setCoinValue(double coinValue) {
		this.coinValue = coinValue;
	}

}
