package elements;
/**
 * the class that includes properties and methods of an order.
 * @author BASARAN
 *
 */
public class Order {
	/**
	 * amount of pcoin
	 */
	protected double amount;
	/**
	 * price of pcoin
	 */
	protected double price;
	/**
	 * ID of trader which gives order
	 */
	protected int traderID;
	/**
	 * constructor with three parameters
	 * @param traderID ID of trader which gives order
	 * @param amount amount of pcoin 
	 * @param price price of coin trader wants to sell or buy
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}
	/**
	 * getter method of price
	 * @return price of coin trader wants to sell or buy
	 */
	public double getPrice () {
		return price;
	}
	/**
	 * getter method of amount of coin
	 * @return amount of coin
	 */
	public double getAmount () {
		return amount;
	}
}

