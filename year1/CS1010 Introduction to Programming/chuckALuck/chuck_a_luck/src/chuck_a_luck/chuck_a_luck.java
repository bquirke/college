package chuck_a_luck;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class chuck_a_luck {

	public static void main(String[] args) {
		Wallet wallet = new Wallet(); //creating wallet
		Die die1 = new Die (6);		//creating die
		Die die2 = new Die (6);
		Die die3 = new Die (6);
		String money = JOptionPane.showInputDialog("Enter amount you want in wallet:");
		Scanner moneyScanner = new Scanner( money );
		
		int walletAmount = moneyScanner.nextInt();
		moneyScanner.close();
		wallet.put(walletAmount);
		
		int typeOfBet;
		double amountBetted;
		double payout;
		int m = 0;  // multiplier for deciding winnings
		
		
		
		
		do{    // should execute below code while the user has not opted to leave game and while the wallet != 0
			String input = JOptionPane.showInputDialog("Enter 0 if you do not want to play(still enter an amount to bet) \n 1 if you want to place a Triple bet \n 2 if you want to place a Big bet \n 3 if you want to place a field bet \n and 4 if you wish to place a small bet. \n Then the amount you wish to bet (seperated by a space) ");
			Scanner inputScanner = new Scanner( input );
			typeOfBet = inputScanner.nextInt(); // triple, big, field etc
			amountBetted = inputScanner.nextInt();
			inputScanner.close();
			
			switch(typeOfBet) // decides which multiplier to use
			{
			case 0:
				break;
			case 1: m = 30;
			break;
			
			case 2:
			case 3:
			case 4:
				m = 1;
				break;	
			}
			
			wallet.get(amountBetted); // takes amount of money out..... idea is it does not return if user loses
			int result1 = die1.roll();
			int result2 = die2.roll();
			int result3 = die3.roll();
			
			if(((result1 == result2) && (result2 == result3)) && (typeOfBet == 1)) //triple bet
			{
				payout = (m * amountBetted) + amountBetted;
				wallet.put(payout);
				JOptionPane.showMessageDialog(null,"Triple BET! You won " + payout);
				
			}
			
			else if (((result1 + result2 + result3) >= 11) && typeOfBet == 2) //big
			{
				payout = (m * amountBetted) + amountBetted;
				wallet.put(payout);
				JOptionPane.showMessageDialog(null,"Big! You won " + payout);
			}
			
			else if (((result1 + result2 + result3) >12) || ((result1 + result2 + result3) <8) && typeOfBet == 3) //field
			{
				payout = (m * amountBetted) + amountBetted;
				wallet.put(payout);
				JOptionPane.showMessageDialog(null,"Field! You won " + payout);
			}
			
			else if (((result1 + result2 + result3) <= 11) && typeOfBet == 4) //small
			{
				payout = (m * amountBetted) + amountBetted;
				wallet.put(payout);
				JOptionPane.showMessageDialog(null,"Small! You won " + payout);
			}
			
			else
			{
				JOptionPane.showMessageDialog(null, "You lost "); // loss
				
			}
			
		}while(typeOfBet != 0 && wallet.count() > 0);
		
		
		
		
		if(typeOfBet == 0)
		{
			wallet.put(amountBetted);
			JOptionPane.showMessageDialog(null, "You have " + wallet.count() + " in your wallet");
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Hardluck you ran out of money!");
		}
		
		
		
		
		

	}

}

class Die
{
        private final int sides; // invariant: sides > 0
       
        //current number on top face of die
        private int topFace; //invariant: 1 <= topFace <= 0
       
        // construct a die with n sides
        // pre-condition: n > 0
        public Die(int n)
        {
                assert n > 0 : "Die consutrctor: pre-condition violated!";
                sides = n;
                topFace = (int)(Math.random() * sides) + 1;
        }
       
        //return current value of top face of die
        public int getTopFace()
        {
                return topFace;
        }
       
        //roll the die; returning the result
        public int roll()
        {
                topFace = (int)(Math.random() * sides) +1;
                return topFace;
        }
       
        public String toString()
        {
                return getClass().getName() + "[sides = " + " , topFace = " + topFace + "]";
        }
}
 
class Wallet
{
        //Wallet data type
        //current cash in wallet
        private double cash;    // invariant: cash >= 0
       
        // construct wallet with zero cash
        public Wallet()
        {
                cash = 0.0;
        }
       
        //put an amount of money into wallet
        //pre-condition: money > 0
        public void put(double money)
        {
                assert money > 0 : "Wallet put method: pre-condition violated!";
                if(money > 0) cash = cash + money;
        }
       
        // get an amount of money from wallet
        // returns true if wallet had enough cash, false otherwise
        // pre-condition: money > 0
        public boolean get(double money)
        {
                assert money > 0 : "Wallet get method: pre-condition violated!";
                if(money > 0 && cash >= money)
                {
                        cash = cash - money;
                        return true;
                }
                return false;
        }
       
        //return current amount of cash in wallet
        public double count()
        {
                return cash;
        }
       
        // convert to a String data type value
        public String toString()
        {
                return getClass().getName() + "[cash = " + cash + "]";
        }
}


