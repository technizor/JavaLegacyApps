package spider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * The Deck is a generic deck of cards that can be dealt and shuffled.
 * 
 * @author Sherman Ying
 * @version May 16, 2013
 */
public class Deck
{
	protected Card[] deck;
	private int randomSeed;
	private Random seed;
	protected int topCard;

	/**
	 * Creates an default deck with a randomized shuffling seed.
	 */
	public Deck()
	{
		randomSeed = (int) (Math.random() * 32000);
		seed = new Random(randomSeed);
		deck = new Card[52];
		topCard = 0;
		while (topCard < deck.length)
			deck[topCard++] = new Card(topCard % 13 + 1, 4 - topCard % 4);
	}

	/**
	 * Creates a default deck with the given shuffling seed.
	 * 
	 * @param seed the shuffling seed to use.
	 */
	public Deck(int seed)
	{
		randomSeed = seed;
		this.seed = new Random(randomSeed);
		deck = new Card[52];
		topCard = 0;
		while (topCard < deck.length)
			deck[topCard++] = new Card(topCard % 13 + 1, 4 - topCard % 4);
	}

	/**
	 * Gives the number of cards still in the deck.
	 * 
	 * @return the number of cards remaining in the deck.
	 */
	public int cardsLeft()
	{
		return topCard;
	}

	/**
	 * Deals the next card.
	 * 
	 * @return the next card, or null if there are no more cards.
	 */
	public Card dealACard()
	{
		if (topCard > 0)
			return deck[--topCard];
		return null;
	}

	/**
	 * Gives the randomized seed used to shuffle.
	 * 
	 * @return the shuffling seed.
	 */
	public int getRandomSeed()
	{
		return randomSeed;
	}

	/**
	 * Reorders the cards by natural order.
	 */
	private void reorder()
	{
		ArrayList<Card> order = new ArrayList<Card>();
		for (Card card : deck) {
			int index = Collections.binarySearch(order, card);
			if (index < 0)
				index = -index - 1;
			order.add(index, card);
		}
		for (int c = 0; c < deck.length; c++)
			deck[c] = order.get(c);
	}

	/**
	 * Resets the order of the deck of cards, then shuffles the cards.
	 */
	public void shuffle()
	{
		// Reset the deck order to natural order and reset the seed to ensure we
		// get the same shuffles for this game seed.
		reorder();
		seed = new Random(randomSeed);

		// Shuffle the deck by inserting the cards into random indexes with two
		// passes
		LinkedList<Card> cardPass1 = new LinkedList<Card>();
		for (Card card : deck) {
			int pos = cardPass1.size() > 0 ? seed.nextInt(cardPass1.size()) : 0;
			cardPass1.add(pos, card);
		}

		int index = 0;

		for (Card card : cardPass1) {
			if (card.isFaceUp())
				card.flip();
			deck[index] = card;
			index++;
		}
		topCard = deck.length;
	}

	/**
	 * Gives a String representation of this Deck.
	 * 
	 * @return a String representation of all of the Cards in this deck.
	 */
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder(topCard * 3);
		for (int i = 0; i < topCard; i++)
			result.append(deck[i].toString() + " ");
		return result.toString();
	}
}
