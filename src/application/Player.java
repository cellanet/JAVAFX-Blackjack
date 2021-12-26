// TechLiterate - https://www.youtube.com/watch?v=buGFs1aQgaY
// Upgraded with GUI by Minh
// 11/13/2021
// simulate a player playing cards

package application;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {

	private String name;
	private final int MAX = 5;
	
	//number of cards in player's hand
	private Card[] hand = new Card[MAX];
	private int numCards;
	
	// create new player with empty hand. MAX = 5 cards/player
	public Player(String aName)
	{
		this.name = aName;
		
		this.emptyHand();
	}
	
	// empty player's hand
	public void emptyHand()
	{
		for (int c = 0; c < MAX; c++)	
		{
			this.hand[c] = null;
		}
		this.numCards = 0;
	}
	
	// add a card to player hand
	public boolean addCard(Card aCard)
	{
		if(this.numCards == MAX)
		{
			System.out.printf("%s's hand alreayd has 5 cards. Can't add more", this.name);
			System.exit(1);
		}
		
		this.hand[this.numCards] = aCard;
		this.numCards++;
		
		return (this.getHandSum() <=21);
	}
	
	public int getNumOfCard()
	{
		return this.numCards;
	}
	
	
	// get sum of value of cards on hand.
	public int getHandSum()
	{
		int handSum = 0;
		
		int cardNum;
		int numAces = 0;
		
		for(int c = 0; c < this.numCards; c++)
		{
			cardNum = this.hand[c].getNumber();
			
			if(cardNum == 1)
			{
				numAces++;
				handSum+=11;
			}
			else if(cardNum > 10)
				handSum +=10;
			else
				handSum += cardNum;
		}
		
		// if we have aces and our sum is > 21, set some/all ace to 1
		while(handSum > 21 && numAces > 0)
		{
			handSum -= 10;
			numAces--;
		}
		return handSum;
	}
	
	//print hand, for testing purpose only
	public void printHand(boolean show2ndCard)
	{
		System.out.printf("%s's cards: \n", this.name);
		
		for(int c = 0; c < this.numCards; c++)
		{
			if(c == 1 && !show2ndCard)
				System.out.println("  [hidden]");
			else
				System.out.printf("  %s\n", this.hand[c].toString());
		}
	}
	
	//using the path created earlier, to add a picture of that card and print it on the program/game.
	public void printPathCard(boolean show2ndCard, List<ImageView> playerSlot)
	{			
		for(int c = 0; c < this.numCards; c++)
		{
			if(c == 1 && !show2ndCard)
				playerSlot.get(c).setImage(new Image("File:src\\Backface_Red.png"));
			else
				playerSlot.get(c).setImage(new Image(this.hand[c].getPath()));
		}
	}
}
