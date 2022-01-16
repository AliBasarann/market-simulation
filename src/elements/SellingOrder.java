package elements;
/**
 * the class that includes properties and methods of a selling order.
 * It extends Order class.
 * @author BASARAN
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder> {
	/**
	 * the constructor with three parameter
	 * @param traderID id of trader which gives selling order
	 * @param amount amount of pcoin that seller wants to sell
	 * @param price price that buyer wants to sell coin
	 */
	SellingOrder(int traderID, double amount, double price){
		super(traderID, amount, price);
	}
	
	/**
	 * compareTo method for comparable interface
	 * it compares prices of selling orders.
	 * @param other takes selling order that we want to compare as parameter.
	 */
	public int compareTo(SellingOrder other) {
		if(price==other.price) {
			if (amount==other.amount) {
				if(traderID==other.traderID) {
					return 0;
				}else {
					return traderID-other.traderID;
				}
			}else if(amount>other.amount){
				return -1;				
			}else {
				return 1;
			}
		}else if(price>other.price) {
			return 1;
		}else {
			return -1;
		}		
	}
}
