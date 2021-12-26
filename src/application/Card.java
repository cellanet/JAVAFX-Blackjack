// TechLiterate - https://www.youtube.com/watch?v=buGFs1aQgaY
// Upgraded with GUI by Minh
// 11/13/2021
// Assign value for 52 cards
package application;

public class Card {
	
	//4 types of cards
	private Suit mySuit;
	// from Ace:1 to King:13
	private int myNumber;
	
	// create the card
	public Card(Suit aSuit, int aNumber)
	{
		this.mySuit =  aSuit;
		
		if(aNumber >= 1 && aNumber <=13)	
			this.myNumber = aNumber;
		else
		{
			System.out.println(aNumber + " is not a vlid card number.");
			System.exit(1);
		}
	}
	
	public int getNumber()
	{
		return myNumber;
	}

	//translate the string to Card Value. Exp: King of Heart.
	public String toString()
	{
		String numStr = "";
		
		switch(this.myNumber)
		{
		case 2: numStr = "Two";break;
		case 3: numStr = "Three";break;
		case 4: numStr = "Four";break;
		case 5: numStr = "Five";break;
		case 6: numStr = "Six";break;
		case 7: numStr = "Seven";break;
		case 8: numStr = "Eight";break;
		case 9: numStr = "Nine";break;
		case 10: numStr = "Ten";break;
		case 11: numStr = "Jack";break;
		case 12: numStr = "Queen";break;
		case 13: numStr = "King";break;
		case 1: numStr = "1";break;
		}
		return numStr + " of " + mySuit.toString();
	}
	
	//translate string value to the path of the correct card's picture. Exp: 2_Clubs.png
	// this path will then be added to the Image to create the picture for each card that was dealed to the player
	public String getPath()
	{
		String numStr = "";
		String fileName = "";
		String path = "";
		
		switch(this.myNumber)
		{
		case 2: numStr = "2";break;
		case 3: numStr = "3";break;
		case 4: numStr = "4";break;
		case 5: numStr = "5";break;
		case 6: numStr = "6";break;
		case 7: numStr = "7";break;
		case 8: numStr = "8";break;
		case 9: numStr = "9";break;
		case 10: numStr = "10";break;
		case 11: numStr = "11";break;
		case 12: numStr = "12";break;
		case 13: numStr = "13";break;
		case 1: numStr = "1";break;
		}
		
		fileName = String.format("%s_%s.png", numStr, mySuit.toString());
		path = String.format("File:src\\\\%s", fileName);
		return path;
	}
}
