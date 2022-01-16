package elements;
/**
 * class that includes properties and methods of a wallet
 * @author BASARAN
 *
 */
public class Wallet {
	/**
	 * dollar amount in wallet
	 */
	private double dollars;
	/**
	 * coin amount in wallet
	 */
	private double coins;
	/**
	 * blocked dollar amount in wallet
	 */
	private double blockedDollars;
	/**
	 * blocked coin amount in wallet
	 */
	private double blockedCoins;
	
	/**
	 * constructor with two parameters
	 * @param dollars initial dollars in wallet
	 * @param coins initial coins in wallet
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
	}
	
	/**
	 * setter method for dollar
	 * @param amount new amount of dollar
	 */
	public void setDollars(double amount){
		this.dollars = amount;
	}
	/**
	 * setter method for coins
	 * @param amount new amount of coins
	 */
	public void setCoins(double amount){
		this.coins = amount;
	}
	/**
	 * setter method for blocked dollars
	 * @param amount new amount of blocked dollars
	 */
	public void setBlockedDollars(double amount){
		this.blockedDollars = amount;
	}
	/**
	 * setter method for blocked coins
	 * @param amount new amount of blocked coins
	 */
	public void setBlockedCoins(double amount){
		this.blockedCoins = amount;
	}
	/**
	 * getter method for blocked dollars
	 * @return amount of blocked dollars
	 */
	public double getBlockedDollars() {
		return blockedDollars;
	}
	/**
	 * getter method for blocked coins
	 * @return amount of blocked coins
	 */
	public double getBlockedCoins() {
		return blockedCoins;
	}
	/**
	 * getter method for coins
	 * @return amount of coins
	 */
	public double getCoins() {
		return coins;}
	/**
	 * getter method for dollars
	 * @return amount of dollars
	 */
	public double getDollars() {
		return dollars;}
		
}
