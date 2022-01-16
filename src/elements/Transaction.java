package elements;
/**
 * class that includes properties and methods of a transaction
 * @author BASARAN
 *
 */
public class Transaction {
	/**
	 * selling order of transaction
	 */
	private SellingOrder sellingOrder;
	/**
	 * buying order of transaction
	 */
	private BuyingOrder buyingOrder;
	/**
	 * constructor with two parameters
	 * @param sellingOrder selling order of transaction
	 * @param buyingOrder buying order of transaction
	 */
	public Transaction(SellingOrder sellingOrder,BuyingOrder buyingOrder) {
		this.buyingOrder = buyingOrder;
		this.sellingOrder = sellingOrder;
	}
}
