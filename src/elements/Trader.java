package elements;
/**
 * the class that contains methods and properties of a trader 
 * @author BASARAN
 *
 */
public class Trader {
	/**
	 * id of trader
	 */
	private int id;
	/**
	 * wallet of trader
	 */
	private Wallet wallet;
	/**
	 * total number of traders
	 */
	public static int numberOfUsers = 0;
	/**
	 * total number of invalid actions
	 */
	public static int invalidActions = 0;
	/**
	 * constructor with two parameters
	 * @param dollars initial number of dollars in trader's wallet
	 * @param coins initial number of coins in trader's wallet
	 */
	public Trader(double dollars, double coins) {
		this.wallet = new Wallet(dollars,coins);
		numberOfUsers += 1;
	}
	/**
	 * sell method for selling pcoins
	 * @param amount amount of coins that trader wants to sell
	 * @param price price of coins that trader wants to sell
	 * @param market market which transaction occurs inside
	 * @return integer
	 */
	public int sell(double amount, double price, Market market) {
		if (this.id == 0) {
			market.giveSellOrder(new SellingOrder(this.id, amount,price));
		} else if (wallet.getCoins() >= amount) {
			this.wallet.setCoins(wallet.getCoins()-amount);
			this.wallet.setBlockedCoins(wallet.getBlockedCoins()+amount);
			market.giveSellOrder(new SellingOrder(this.id, amount,price));
		} else {invalidActions += 1;}
		return 0;
		}
	/**
	 * buy method for buying pcoins
	 * @param amount amount of coins that trader wants to buy
	 * @param price price of coins that trader wants to buy
	 * @param market market which transaction occurs inside
	 * @return integer
	 */
	public int buy(double amount, double price, Market market) {
		if (this.id == 0) {
		market.giveBuyOrder(new BuyingOrder(this.id, amount,price));
		}else if (wallet.getDollars() >= amount*price) {
			this.wallet.setDollars(wallet.getDollars()-(amount*price));
			this.wallet.setBlockedDollars(wallet.getBlockedDollars()+(amount*price));
			market.giveBuyOrder(new BuyingOrder(this.id, amount,price));
		}else {invalidActions += 1;}
		return 0;
		}
	/**
	 * getter method for wallet
	 * @return wallet of trader
	 */
	public Wallet getWallet() {
		return wallet;
	}
	/**
	 * setter method for id
	 * @param id of trader
	 */
	public void setID(int id) {
		this.id = id;
	}
	/**
	 * getter method for id
	 * @return id of trader
	 */
	public int getID() {
		return id;
	}
	
}
