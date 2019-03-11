package spider;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * A Spider Solitaire Card that has a suit, rank, image, and can be faceup or
 * facedown, and can be dragged.
 * 
 * @author Sherman Ying
 * @version May 14, 2013
 */
public class Card extends Rectangle implements Movable, Comparable<Card>
{
	private static Image backgroundImage = new ImageIcon("images\\blueback.png")
			.getImage();

	public static final int HEIGHT = backgroundImage.getHeight(null);

	private static final long serialVersionUID = -8330587880011500286L;

	public static final int WIDTH = backgroundImage.getWidth(null);

	private Image image;

	private boolean isFaceUp;

	private int rank; // A -1, 2 - 10, J Q K

	private int suit; // D - 1, C - 2, H - 3, S - 4

	/**
	 * Creates a Spider Solitaire Card with the specified rank and suit.
	 * 
	 * @param rank the rank of this card.
	 * @param suit the suit of this card.
	 */
	public Card(int rank, int suit)
	{
		// Set up the underlining Rectangle
		super(0, 0, WIDTH, HEIGHT);

		// Set the initial values of rank and suit
		this.rank = rank;
		this.suit = suit;
		isFaceUp = false;

		// Load up the appropriate image file for this card
		String imageFile = "images\\" + " dchs".charAt(suit) + rank + ".png";
		image = new ImageIcon(imageFile).getImage();
	}

	/**
	 * Checks whether the given card can be placed on this card.
	 * 
	 * @param card the card to check.
	 * @return true if the other card can be placed on this card, and false if
	 *         it cannot.
	 */
	public boolean canPlace(Card card)
	{
		if (card.rank + 1 == rank)
			return true;
		return false;
	}

	/**
	 * Checks whether this card can be placed on top of the given stack of
	 * cards.
	 * 
	 * @param stack the stack of cards to check.
	 * @return true if this card can be placed and false if this card cannot be
	 *         placed.
	 */
	@Override
	public boolean canPlaceOnStack(Stack stack)
	{
		if (stack.getNoOfCards() == 0)
			return true;
		return stack.getTopCard().canPlace(this);
	}

	/**
	 * Compares the suit and rank of a given card.
	 * 
	 * @param other the card to compare with.
	 * @return 0 if the cards are the same, less than 1 if the card is of a
	 *         lower suit or rank, and greater than 1 if the card is of a higher
	 *         suit or rank.
	 */
	@Override
	public int compareTo(Card other)
	{
		if (suit == other.suit)
			return other.rank - rank;
		return other.suit - suit;
	}

	/**
	 * Repositions this card based on the distance dragged.
	 * 
	 * @param initialPos the original position of the mouse.
	 * @param finalPos the final position of the mouse.
	 */
	@Override
	public void drag(Point initialPos, Point finalPos)
	{
		translate(finalPos.x - initialPos.x, finalPos.y - initialPos.y);
	}

	/**
	 * Draws a card in a Graphics context.
	 * 
	 * @param g Graphics to draw the card in.
	 */
	@Override
	public void draw(Graphics g)
	{
		if (isFaceUp)
			g.drawImage(image, x, y, null);
		else
			g.drawImage(backgroundImage, x, y, null);
	}

	/**
	 * Flips this card.
	 */
	public void flip()
	{
		isFaceUp = !isFaceUp;
	}

	/**
	 * Generates a hashcode for this card. 13*Suit + Rank.
	 * 
	 * @return a generated hashcode for this card.
	 */
	@Override
	public int hashCode()
	{
		return suit * 13 + rank;
	}

	/**
	 * Checks whether this card is faceup.
	 * 
	 * @return true if this card is faceup, and false if it is not.
	 */
	public boolean isFaceUp()
	{
		return isFaceUp;
	}

	/**
	 * Checks whether the given card has the same suit as this card.
	 * 
	 * @param card the card to compare the suit of.
	 * @return true if the other card has the same suit, and false if it does
	 *         not.
	 */
	public boolean isSameSuit(Card card)
	{
		return this.suit == card.suit;
	}

	/**
	 * Places this card onto the given stack.
	 * 
	 * @param stack the stack to place this card on.
	 */
	@Override
	public void placeOnStack(Stack stack)
	{
		stack.addCard(this);
	}

	/**
	 * Gives a String representation of this card. For example, the Ace of
	 * Spades is AS.
	 * 
	 * @return a string representation of this card.
	 */
	@Override
	public String toString()
	{
		return "" + " A23456789TJQK".charAt(rank) + " DCHS".charAt(suit);

	}

}
