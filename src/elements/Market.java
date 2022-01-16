package elements;

import java.util.*;
/**
 * the class that transactions occur. 
 * It includes buying and selling order queues and makes operations according to the queues.
 * @author BASARAN
 *
 */
public class Market {
	/**
	 * queue which includes selling orders according to increasing prices;
	 */
	private PriorityQueue<SellingOrder> sellingOrders = new PriorityQueue<SellingOrder>();
	/**
	 * queue which includes buying orders according to decreasing prices;
	 */
	private PriorityQueue<BuyingOrder> buyingOrders = new PriorityQueue<BuyingOrder>();
	/**
	 * list that includes successful transactions
	 */
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	/**
	 * rate of fee per thousand
	 */
	private int fee;
	/**
	 * list of all traders
	 */
	public ArrayList<Trader> traders = new ArrayList<Trader>();
	/**
	 * constructor with one parameter
	 * @param fee rate of fee per thousand
	 */
	public Market(int fee) {
		this.fee = fee;
	}
	/**
	 * adds order to selling orders queue 
	 * @param order order we want to add queue
	 */
	public void giveSellOrder(SellingOrder order) {
		sellingOrders.add(order);
		checkTransactions(traders);
		return;
	}
	/**
	 * adds order to buying orders queue 
	 * @param order order we want to add queue
	 */
	public void giveBuyOrder(BuyingOrder order) {
		buyingOrders.add(order);
		checkTransactions(traders);
		return;
	}
	
	
	/**
	 * Makes open market operation and set market price to given price 
	 * @param price price that we want to set
	 */
	public void makeOpenMarketOperation(double price) {
		if(buyingOrders.size() != 0) {
			while(buyingOrders.peek() != null && buyingOrders.peek().getPrice()>=price) {
				double amnt = buyingOrders.peek().amount;
				double prc = buyingOrders.peek().price;
				giveSellOrder(new SellingOrder(0,amnt,prc));
				checkTransactions(traders);
			}
		}
		if(sellingOrders.size() != 0) {
			while(sellingOrders.peek() != null && sellingOrders.peek().price<=price) {
				double amnt = sellingOrders.peek().amount;
				double prc = sellingOrders.peek().price;
				giveBuyOrder(new BuyingOrder(0,amnt,prc));
				checkTransactions(traders);
			}
		}
	}
	
	/**
	 * checks possible transactions until there is no possible transactions.
	 * @param traders trader list that includes all traders.
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		while(sellingOrders.peek() != null && buyingOrders.peek() != null && sellingOrders.peek().price <= buyingOrders.peek().price) {
			SellingOrder sellO = sellingOrders.poll(); 
			BuyingOrder buyO = buyingOrders.poll();
			if (buyO.price == sellO.price) {
				if(buyO.amount == sellO.amount) {
					traders.get(buyO.traderID).getWallet().setBlockedDollars(traders.get(buyO.traderID).getWallet().getBlockedDollars()-buyO.price*sellO.amount);
					traders.get(buyO.traderID).getWallet().setCoins(traders.get(buyO.traderID).getWallet().getCoins()+sellO.amount);
					traders.get(sellO.traderID).getWallet().setBlockedCoins(traders.get(sellO.traderID).getWallet().getBlockedCoins()-buyO.amount);
					traders.get(sellO.traderID).getWallet().setDollars(traders.get(sellO.traderID).getWallet().getDollars()+(sellO.amount*sellO.price*(1-(double)fee/1000)));
					transactions.add(new Transaction(sellO,buyO));
				}else if(buyO.amount > sellO.amount){
					traders.get(buyO.traderID).getWallet().setBlockedDollars(traders.get(buyO.traderID).getWallet().getBlockedDollars()-buyO.price*sellO.amount);
					traders.get(buyO.traderID).getWallet().setCoins(traders.get(buyO.traderID).getWallet().getCoins()+sellO.amount);
					traders.get(sellO.traderID).getWallet().setBlockedCoins(traders.get(sellO.traderID).getWallet().getBlockedCoins()-sellO.amount);
					traders.get(sellO.traderID).getWallet().setDollars(traders.get(sellO.traderID).getWallet().getDollars()+(sellO.amount*sellO.price*(1-(double)fee/1000)));
					transactions.add(new Transaction(sellO,buyO));
					buyO.amount -= sellO.amount;
					buyingOrders.add(buyO);
				}else if(buyO.amount<sellO.amount) {
					traders.get(buyO.traderID).getWallet().setBlockedDollars(traders.get(buyO.traderID).getWallet().getBlockedDollars()-buyO.price*buyO.amount);
					traders.get(buyO.traderID).getWallet().setCoins(traders.get(buyO.traderID).getWallet().getCoins()+buyO.amount);
					traders.get(sellO.traderID).getWallet().setBlockedCoins(traders.get(sellO.traderID).getWallet().getBlockedCoins()-buyO.amount);
					traders.get(sellO.traderID).getWallet().setDollars(traders.get(sellO.traderID).getWallet().getDollars()+(buyO.amount*sellO.price*(1-(double)fee/1000)));
					transactions.add(new Transaction(sellO,buyO));
					sellO.amount -= buyO.amount;
					sellingOrders.add(sellO);
				}
			}
			else if (buyO.price > sellO.price) {
				if(buyO.amount == sellO.amount) {
					traders.get(buyO.traderID).getWallet().setBlockedDollars(traders.get(buyO.traderID).getWallet().getBlockedDollars()-buyO.amount*buyO.price);
					traders.get(buyO.traderID).getWallet().setCoins(traders.get(buyO.traderID).getWallet().getCoins()+buyO.amount);
					traders.get(buyO.traderID).getWallet().setDollars(traders.get(buyO.traderID).getWallet().getDollars() + (buyO.price - sellO.price)*buyO.amount);
					traders.get(sellO.traderID).getWallet().setBlockedCoins(traders.get(sellO.traderID).getWallet().getBlockedCoins()-sellO.amount);
					traders.get(sellO.traderID).getWallet().setDollars(traders.get(sellO.traderID).getWallet().getDollars()+(sellO.amount*sellO.price*(1-(double)fee/1000)));
					transactions.add(new Transaction(sellO,buyO));
				}else if(buyO.amount > sellO.amount){
					traders.get(buyO.traderID).getWallet().setBlockedDollars(traders.get(buyO.traderID).getWallet().getBlockedDollars()-buyO.price*sellO.amount);
					traders.get(buyO.traderID).getWallet().setCoins(traders.get(buyO.traderID).getWallet().getCoins()+sellO.amount);
					traders.get(buyO.traderID).getWallet().setDollars(traders.get(buyO.traderID).getWallet().getDollars() + (buyO.price - sellO.price)*sellO.amount);
					traders.get(sellO.traderID).getWallet().setBlockedCoins(traders.get(sellO.traderID).getWallet().getBlockedCoins()-sellO.amount);
					traders.get(sellO.traderID).getWallet().setDollars(traders.get(sellO.traderID).getWallet().getDollars()+(sellO.amount*sellO.price*(1-(double)fee/1000)));
					transactions.add(new Transaction(sellO,buyO));
					buyO.amount -= sellO.amount;
					buyingOrders.add(buyO);
				}else if(buyO.amount<sellO.amount) {
					traders.get(buyO.traderID).getWallet().setBlockedDollars(traders.get(buyO.traderID).getWallet().getBlockedDollars()-buyO.amount*buyO.price);
					traders.get(buyO.traderID).getWallet().setCoins(traders.get(buyO.traderID).getWallet().getCoins()+buyO.amount);
					traders.get(buyO.traderID).getWallet().setDollars(traders.get(buyO.traderID).getWallet().getDollars() + (buyO.price - sellO.price)*buyO.amount);
					traders.get(sellO.traderID).getWallet().setBlockedCoins(traders.get(sellO.traderID).getWallet().getBlockedCoins()-buyO.amount);
					traders.get(sellO.traderID).getWallet().setDollars(traders.get(sellO.traderID).getWallet().getDollars()+(buyO.amount*sellO.price*(1-(double)fee/1000)));
					transactions.add(new Transaction(sellO,buyO));
					sellO.amount -= buyO.amount;
					sellingOrders.add(sellO);
				}
			}
		}
	}
	/**
	 * getter method for buying order list
	 * @return buying orders list
	 */
	public PriorityQueue<BuyingOrder> getBuyingOrders (){
		return buyingOrders;
	}
	/**
	 * getter method for selling order list
	 * @return selling order list
	 */
	public PriorityQueue<SellingOrder> getSellingOrders (){
		return sellingOrders;
	}
	/**
	 * getter method for transactions list
	 * @return transactions list
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	
}
