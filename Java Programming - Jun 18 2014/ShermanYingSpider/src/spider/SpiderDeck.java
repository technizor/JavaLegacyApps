package spider;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Creates a Spider Solitaire deck with 104 cards and either 1, 2, or 4 suits.
 * 
 * @author Sherman Ying
 * @version May 16, 2013
 */
public class SpiderDeck extends Deck
{
	private Point deckPos;
	private int noOfSuits;

	/**
	 * Creates a SpiderDeck with a given number of suits at the given position.
	 * 
	 * @param noOfSuits the number of suits being played.
	 * @param position the initial location of the deck.
	 */
	public SpiderDeck(int noOfSuits, Point position)
	{
		super();
		this.noOfSuits = noOfSuits;
		deckPos = position;
		deck = new Card[104];
		topCard = 0;
		while (topCard < deck.length)
			deck[topCard++] = new Card(topCard % 13 + 1, 4 - topCard
					% (noOfSuits > 0 ? noOfSuits : -1));
		rePositionCards();
	}

	/**
	 * Creates a SpiderDeck with the given number of suits at the given
	 * position, with the given shuffling seed.
	 * 
	 * @param noOfSuits the number of suits being played.
	 * @param position the position of this deck.
	 * @param seed the random seed used by the shuffler.
	 */
	public SpiderDeck(int noOfSuits, Point position, int seed)
	{
		super(seed);
		this.noOfSuits = noOfSuits;
		deckPos = position;
		deck = new Card[104];
		topCard = 0;
		while (topCard < deck.length)
			deck[topCard++] = new Card(topCard % 13 + 1, 4 - topCard
					% (noOfSuits > 0 ? noOfSuits : -1));
		rePositionCards();
	}

	/**
	 * Draws the all of the cards still in the deck.
	 * 
	 * @param the Graphics object to draw to.
	 */
	public void draw(Graphics g)
	{
		for (int i = 0; i < topCard; i++)
			deck[i].draw(g);
	}

	/**
	 * Gives the number of suits in this deck.
	 * 
	 * @return the number of suits
	 */
	public int getNoOfSuits()
	{
		return noOfSuits;
	}

	/**
	 * Resets the positions of the cards.
	 */
	public void rePositionCards()
	{
		topCard = 104;
		Point position = new Point(deckPos);
		for (int card = 0; card < topCard; card++) {
			// Stagger bottom 50 cards into piles of 10
			if (card < 50 && (card) % 10 == 0)
				position.x += 10;
			if (deck[card].isFaceUp())
				deck[card].flip();
			deck[card].setLocation(position);
		}
	}

	/**
	 * Returns the last card to the top of the deck.
	 * 
	 * @param card the card to return to the deck.
	 */
	public void undeal(Card card)
	{
		if (topCard > 50)
			return;
		Point position = new Point(deckPos);
		position.x += (topCard / 10) * 10;
		topCard++;
		card.setLocation(position);
		if (card.isFaceUp())
			card.flip();
	}
}
