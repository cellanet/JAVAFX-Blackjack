// TechLiterate - https://www.youtube.com/watch?v=buGFs1aQgaY
// 11/13/2021
// simualate deck(s) of card
package application;

import java.util.Random;

public class Deck {

	// array of cards in the deck, first index = top card
	private Card[] myCards;
	
	//number of cards currently in the deck
	private int numCards;
	
	//default constructor with 1 deck (52 cards) and no shuffling
	public Deck()
	{
		// call 1 deck and no shuffling
		this(1, false);
	}
	
	// how many set of 52 cards are in the deck and if it would be shuffled
	public Deck(int numDecks, boolean shuffle)
	{
		this.numCards = numDecks * 52;
		this.myCards = new Card[this.numCards];
		
		//card index
		int c = 0;
		
		// for each deck
		for (int d = 0; d < numDecks; d++)
		{
			//for each suit
			for(int s = 0; s < 4; s++)
			{
				//for each  number
				for (int n = 1; n <= 13; n++)
				{
					this.myCards[c] = new Card(Suit.values()[s], n);
					c++;
				}
			}
		}
		
		//shuffle if needed
		if (shuffle)
			this.shuffle();
	}
	
	public void shuffle()
	{
		Random rng = new Random();
		
		Card temp;
		
		int j;
		for(int i = 0; i < this.numCards; i++)
			{
				//random card j swap with i
				j = rng.nextInt(this.numCards);
				
				//swap
				temp = this.myCards[i];
				this.myCards[i] = this.myCards[j];
				this.myCards[j] = temp;
			}
	}		
	// deal card currently on top
	public Card dealNextCard()
	{
		//get top card
		Card top = this.myCards[0];
		
		//shift all card to the left by 1
		for(int c = 1; c < this.numCards; c++)
		
			this.myCards[c-1] = this.myCards[c];
		
		this.myCards[this.numCards-1] = null;
		
		this.numCards--;	
		return top;
	}	
	
	// for testing purpose only
	public void printDeck(int numToPrint)
	{
		for(int c = 0; c < numToPrint; c++)
		{
			System.out.printf("% 3d/%d %s\n", c+1, this.numCards, 
										this.myCards[c].toString());
		}
		System.out.printf("\t[%d others]\n", this.numCards-numToPrint);
	}
}
