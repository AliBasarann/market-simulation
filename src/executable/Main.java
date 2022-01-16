package executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import elements.*;

/**
 * class that all inputs handled and given appropriate responses
 * it also prints an output file
 * @author BASARAN
 *
 */
public class Main{
	/**
	 * creates a random number for using in actions
	 */
	public static Random myRandom = new Random();
	/**
	 * method that all inputs handled and given appropriate responses
	 * @param args takes arguments as input and output files
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		
		ArrayList<Trader> traders = new ArrayList<Trader>();
		
		int A = in.nextInt();
		myRandom.setSeed(A);
		
		int B = in.nextInt();
		Market market = new Market(B);
		
		int C = in.nextInt();
		int D = in.nextInt();
		in.nextLine();
		
		for(int i = 0; i<C;i++) {
			String line = in.nextLine();
			line = line.strip();
			line = line.replaceAll("\\s+", " ");
			String[] itemArray;
			itemArray = line.split(" ");
			Trader temp = new Trader(Double.parseDouble(itemArray[0]), Double.parseDouble(itemArray[1]));
			temp.setID(Trader.numberOfUsers-1);
			traders.add(temp);
			market.traders.add(temp);
		}
		for(int i = 0; i<D; i++) {
			String line = in.nextLine();
			line = line.strip();
			line = line.replaceAll("\\s+", " ");
			String[] itemArray;
			itemArray = line.split(" ");
			if (itemArray[0].equals("10")) {
				int id = Integer.parseInt(itemArray[1]);
				double prc = Double.parseDouble(itemArray[2]);
				double amt = Double.parseDouble(itemArray[3]);
				traders.get(id).buy(amt,prc,market);
			}else if (itemArray[0].equals("11")) {
				int id = Integer.parseInt(itemArray[1]);
				double amt = Double.parseDouble(itemArray[2]);
				if(market.getSellingOrders().size() != 0) {
				traders.get(id).buy(amt,market.getSellingOrders().peek().getPrice(),market);
				}else {Trader.invalidActions += 1;}
			}else if (itemArray[0].equals("20")) {
				int id = Integer.parseInt(itemArray[1]);
				double prc = Double.parseDouble(itemArray[2]);
				double amt = Double.parseDouble(itemArray[3]);
				traders.get(id).sell(amt,prc,market);
			}else if (itemArray[0].equals("21")) {
				int id = Integer.parseInt(itemArray[1]);
				double amt = Double.parseDouble(itemArray[2]);
				if (market.getBuyingOrders().size() != 0) {
				traders.get(id).sell(amt,market.getBuyingOrders().peek().getPrice(),market);
				}else {Trader.invalidActions += 1;}
			}else if (itemArray[0].equals("3")) {
				int id = Integer.parseInt(itemArray[1]);
				double amt = Double.parseDouble(itemArray[2]);
				traders.get(id).getWallet().setDollars(traders.get(id).getWallet().getDollars()+amt);
			}else if (itemArray[0].equals("4")) {
				int id = Integer.parseInt(itemArray[1]);
				double amt = Double.parseDouble(itemArray[2]);
				if(traders.get(id).getWallet().getDollars() >= amt) {
				traders.get(id).getWallet().setDollars(traders.get(id).getWallet().getDollars()-amt);
				}else {Trader.invalidActions += 1;}
			}else if (itemArray[0].equals("5")) {
				int id = Integer.parseInt(itemArray[1]);
				out.println("Trader "+ id +": "+ String.format("%.5f",traders.get(id).getWallet().getDollars()+traders.get(id).getWallet().getBlockedDollars())+"$ " + String.format("%.5f",traders.get(id).getWallet().getCoins()+traders.get(id).getWallet().getBlockedCoins())+"PQ");
			}else if (itemArray[0].equals("777")) {
				for(Trader trader: traders ) {
					trader.getWallet().setCoins(trader.getWallet().getCoins()+myRandom.nextDouble()*10);
				}
			}else if (itemArray[0].equals("666")) {
				double prc = Double.parseDouble(itemArray[1]);
				market.makeOpenMarketOperation(prc);
			}else if (itemArray[0].equals("500")) {
				double totalDollar = 0;
				double totalpq = 0;
				for(BuyingOrder buy : market.getBuyingOrders()) {
					totalDollar += buy.getPrice()*buy.getAmount();
				}
				for(SellingOrder sell : market.getSellingOrders()) {
					totalpq += sell.getAmount();
				}
				out.println("Current market size: "+ String.format("%.5f",totalDollar) +" "+String.format("%.5f",totalpq));
			}else if (itemArray[0].equals("501")) {
				out.println("Number of successful transactions: "+ market.getTransactions().size());
			}else if (itemArray[0].equals("502")) {
				out.println("Number of invalid queries: "+ Trader.invalidActions);
			}else if (itemArray[0].equals("505")) {
				double cpSelling;
				double cpBuying;
				double cpAverage;
				if (market.getBuyingOrders().size() == 0 && market.getSellingOrders().size() == 0) {
					cpSelling = 0;
					cpBuying = 0;
					cpAverage = 0;
				} else if(market.getSellingOrders().size() == 0) {
					cpSelling = 0;
					cpBuying = market.getBuyingOrders().peek().getPrice();
					cpAverage = cpBuying;
				} else if(market.getBuyingOrders().size() == 0) {
					cpBuying = 0;
					cpSelling = market.getSellingOrders().peek().getPrice();
					cpAverage = cpSelling;
				} else {
					cpBuying = market.getBuyingOrders().peek().getPrice();
					cpSelling = market.getSellingOrders().peek().getPrice();
					cpAverage = (double) (cpBuying+cpSelling)/2;
				}
				out.println("Current prices: " + String.format("%.5f",cpBuying)+" " + String.format("%.5f",cpSelling)+" "+  String.format("%.5f",cpAverage));
					
			}else if (itemArray[0].equals("555")) {
				for(Trader trader:traders) {
					out.println("Trader "+ trader.getID()+": "+ String.format("%.5f",trader.getWallet().getDollars()+trader.getWallet().getBlockedDollars())+"$ " + String.format("%.5f",trader.getWallet().getCoins()+trader.getWallet().getBlockedCoins())+"PQ");
				}
			}
			market.checkTransactions(traders);
			
		}
		
		
	}
}

