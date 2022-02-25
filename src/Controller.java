// Jose Javier Saldivar
// cs-401
// Professor Hecker

// The Stock program is following the MVC design template and this is our controller object.
// The main functionality for buying and selling the stocks are in this controller object.
// This is the ONLY file you may edit

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import javax.xml.soap.Node;

import java.util.Queue;

public class Controller {
	static LinkedList<Stock> allStock = new LinkedList<Stock>();
	
	public Controller() {
		Scanner input = new Scanner(System.in);
		//Controller.buyStock(null,"Google", 10, 100);
		//Controller.buyStock(null,"Yahoo", 20, 200);
		//Controller.buyStock(null,"Apple", 30, 300);
		LinkedList<Stock> googList = new LinkedList<Stock>();
		LinkedList<Stock> amazList = new LinkedList<Stock>();
		//Scanner input = new Scanner(System.in);
		
		do {
			System.out.print("Enter 1 for Google stock or 2 for Amazon, 3 to quit: ");
			int stockSelect = input.nextInt();
			if(stockSelect == 3)
				break;
			System.out.print("Input 1 to buy, 2 to sell: ");
			int controlNum = input.nextInt();
			System.out.print("How many stocks: ");
			int quantity = input.nextInt();		// how many stocks to SELL or BUY
			
			
			if(controlNum == 1) {
				System.out.print("At what price: ");
				double price = input.nextDouble();
				if(stockSelect == 1) {
					Controller.buyStock(googList, "Google", quantity, price);
				}
				else
					Controller.buyStock(amazList, "Amazon", quantity, price);
			}
// if chose [ 2 ] then it will bring you to this else statement
			else {
				System.out.print("Press 1 for LIFO accounting, 2 for FIFO accounting: ");
				controlNum = input.nextInt();
				System.out.println("At what price: ");
				double price = input.nextDouble();
				
				if(controlNum == 1) {
					if(stockSelect == 1)
						Controller.sellLIFO(googList, quantity, price);
					else
						Controller.sellLIFO(amazList, quantity, price);
				}
				else {
					if(stockSelect == 1)
						Controller.sellFIFO(googList, quantity, price);
					else
						Controller.sellFIFO(amazList, quantity, price);
				}
			}
			
		} while(true);
		input.close();
	}
			
	public static void buyStock(LinkedList<Stock> list, String name, int quantity, double price) {
		Stock temp = new Stock(name,quantity,price);
		list.push(temp);
		int total = 0;
		int count = 0;
		count = count + quantity;
		total = count + quantity;
		System.out.printf("You bought %d shares of %s stock at $%.2f per share %n", quantity, name, price);
		System.out.println("Testing toString method : " + temp.toString());
		//System.out.println("************** Count is count + quantity = " + count);
		//System.out.println("*************** This Total equals = " + total);
		
	}
	
	public static void sellLIFO(LinkedList<Stock> list, int numToSell, double price) {
	    //[Stack] 
		System.out.println("This contains 'listSize': " + list.size());
		System.out.println("This contains 'numToSell': " + numToSell);
		System.out.println(list);
		//System.out.println("This contains 'numToSell': " + quantity);
		
		if(list.isEmpty()) 
		{
			System.out.println("-------- Nothing in Stack ---------");
			return;
		}	  
		if(numToSell < list.size())
		{
			System.out.println("Trying to sell more than you have? \n Try again");
			return;
		}
		
	    double total = 0; // this variable will store the total after the sale
	    double profit = 0; // the price paid minus the sale price, negative # means a loss
	    
	    total = price;
	    profit = total - list.getFirst().getPrice();
	    
		list.removeFirst();
	    
		System.out.printf("You sold %d shares of %s stock at %.2f per share %n", numToSell, list.element().getName(), price);
	    System.out.printf("You made $%.2f on the sale %n", profit);
	}
	
	public static void sellFIFO(LinkedList<Stock> list, int numToSell, double price) {
		//[Queue]
		if(list.isEmpty()) 
		{
			System.out.println("-------- Nothing in Queue ---------");
			return;
		}
		if(numToSell < list.size())
		{
			System.out.println("Trying to sell more than you have? \n Try again");
			return;
		}
		
	    double total = 0; // this variable will store the total after the sale
	    double profit = 0; // the price paid minus the sale price, negative # means a loss
	    
	    total = price;
	    profit = total - list.getLast().getPrice();
	    
		list.removeLast();
	    
		System.out.printf("You sold %d shares of %s stock at $%.2f per share %n", numToSell, list.element().getName(), price);
	    System.out.printf("You made $%.2f on the sale %n", profit);	    
	}
	
}
