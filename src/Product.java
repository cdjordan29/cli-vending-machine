/**
 * Class Product:
 * 
 * This class represents the Products that are stored in the Vending Machine.
 * 
 * @author Daniel Jordan
 * @version 1.0
 * @date 2/13/17
 */
public class Product {

	/**
	 * Instance Data Section
	 */
	
	//Instance variables
	private String productName;
	private double productCost;
	private int productStock;
	
	//Constructor for Product
	public Product(String _productName, double _productCost, int _productStock){
		
		setProductName(_productName);
		setProductCost(_productCost);
		setProductStock(_productStock);
	}

	/**
	 * @return the toString for the Product class
	 */
	public String toString(){
		
		return "\t******************************\n" +
			   "\t** Item: " + getProductName() + "\n" +
		       "\t** Price: $" + getProductCost() + "\n" +
			   "\t** Quantity: " + getProductStock() + "\n" +
			   "\t******************************";
		
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productCost
	 */
	public double getProductCost() {
		return productCost;
	}

	/**
	 * @param productCost the productCost to set
	 */
	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}

	public void sellProduct(){
		this.productStock -= 1;
	}
	
	/**
	 * @return the productStock
	 */
	public int getProductStock() {
		return productStock;
	}

	/**
	 * @param productStock the productStock to set
	 */
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
}
