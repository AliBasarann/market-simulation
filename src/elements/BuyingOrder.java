package elements;
/**
 * the class that includes properties and methods of a buying order.
 * It extends Order class.
 * @author BASARAN
 *
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder> {
	/**
	 * constructor with 3 parameters which implies super constructor
	 * @param traderID id of trader which gives buying order
	 * @param amount amount of pcoin that buyer want to buy
	 * @param price price that buyer want to buy coin
	 */
	BuyingOrder(int traderID, double amount, double price){
		super(traderID, amount, price);
	}
	
	/**
	 * compareTo method for comparable interface
	 * it compares prices of buying orders.
	 * @param other takes buying order that we want to compare as parameter.
	 * 
	 */
	public int compareTo(BuyingOrder other) {
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
			return -1;
		}else {
			return 1;
		}		
	}
}
