package spider;

import java.awt.Point;
import java.util.ArrayList;

/**
 * A Stack is a list of cards, with staggered positions.
 * 
 * @author Sherman Ying
 * @version May 16, 2013
 */
public class Stack extends Hand implements Movable
{
	private static final long serialVersionUID = -13810666092016796L;
	public static final int SHIFT = 20;

	/**
	 * Creates an empty stack of cards at the given location.
	 * 
	 * @param x the x-coordinate of the top left corner of the stack.
	 * @param y the y-coordinate of the top left corner of the stack.
	 */
	public Stack(int x, int y)
	{
		super(x, y);
	}

	/**
	 * Adds the given card to this stack, and then adjusts the location of the
	 * card.
	 * 
	 * @param card the card to add to the top of the stack.
	 */
	@Override
	public void addCard(Card card)
	{
		hand.add(card);
		adjustSize();
		card.setLocation(x, y + height - Card.HEIGHT);
	}

	/**
	 * Updates the stack size based on the number of cards in the stack.
	 */
	private void adjustSize()
	{
		if (getNoOfCards() > 0)
			this.setSize(width, Card.HEIGHT + SHIFT * getNoOfCards() - SHIFT);
		else
			setSize(Card.WIDTH, Card.HEIGHT);
	}

	/**
	 * Checks whether the card that the specified point can be picked up.
	 * 
	 * @param point the position of the card to pick up.
	 * @return true if the card at the point can be picked up, and false if it
	 *         cannot be picked up.
	 */
	public boolean canPickUp(Point point)
	{
		Card lastCard = getTopCard();
		if (lastCard == null)
			return false;
		if (lastCard.contains(point) && lastCard.isFaceUp())
			return true;

		for (int index = getNoOfCards() - 2; index >= 0; index--) {
			Card card = hand.get(index);
			if (card.isFaceUp() && card.canPlace(lastCard)
					&& card.isSameSuit(lastCard)) {
				if (card.contains(point))
					return true;
				lastCard = card;
			} else
				return false;
		}
		return false;
	}

	/**
	 * Checks whether this stack can be placed on the given stack.
	 * 
	 * @param stack the stack to check.
	 * @return true if this stack can be placed, and false if this cannot be
	 *         placed.
	 */
	@Override
	public boolean canPlaceOnStack(Stack stack)
	{
		return this.getBottomCard().canPlaceOnStack(stack);
	}

	/**
	 * Removes all of the cards in this stack and readjusts the stack size.
	 */
	@Override
	public void clear()
	{
		super.clear();
		adjustSize();
	}

	/**
	 * Picks up the cards below the card at the given point.
	 * 
	 * @param point the position of the bottom card to be picked up.
	 * @return a stack of cards that are removed from this stack, and null if
	 *         the cards cannot be picked up.
	 */
	public Movable pickUp(Point point)
	{
		if (!canPickUp(point))
			return null;

		// Pick up cards until the bottom card to be picked up is found
		ArrayList<Card> pickedUpCards = new ArrayList<Card>();
		Card c = getTopCard();
		while (c != null && c.isFaceUp()) {
			pickedUpCards.add(0, removeTopCard());
			if (c.contains(point)) {
				// Add the cards to a new stack
				Stack heldCards = new Stack(c.x, c.y);
				for (Card card : pickedUpCards) {
					heldCards.addCard(card);
				}
				adjustSize();
				return heldCards;
			}
			c = getTopCard();
		}
		return null;
	}

	/**
	 * Places all of the cards of this stack on the given stack.
	 * 
	 * @param stack the stack of cards to place onto.
	 */
	@Override
	public void placeOnStack(Stack stack)
	{
		while (this.getNoOfCards() > 0) {
			stack.addCard(remove(0));
		}
	}

	/**
	 * Removes the card at the specified index and adjusts the size of the stack
	 * and the cards on top of it.
	 * 
	 * @param index the index of the card to remove.
	 * @return the removed card.
	 */
	@Override
	public Card remove(int index)
	{
		Card removedCard = super.remove(index);
		for (int c = index; c < hand.size(); c++)
			hand.get(c).setLocation(x, hand.get(c).y - SHIFT);
		adjustSize();
		return removedCard;
	}

	/**
	 * Removes the top 13 cards if they form a full Spider Solitaire sequence.
	 * 
	 * @return the 13 cards removed, if any, or null if no cards were removed.
	 */
	public Stack removeFullSequence()
	{
		if (getNoOfCards() < 13)
			return null;
		Point p = new Point(x + width / 2, y + height - Card.HEIGHT - 12
				* SHIFT + SHIFT / 2);
		if (canPickUp(p)
				&& hand.get(getNoOfCards() - 1).toString().charAt(0) == 'A') {
			int index = getNoOfCards() - 13;

			// Remove all the top 13 cards
			Stack cardsRemoved = new Stack(x, hand.get(index).y);
			for (int i = 0; i < 13; i++)
				cardsRemoved.addCard(hand.remove(index));
			adjustSize();
			return cardsRemoved;
		}
		return null;
	}

	/**
	 * Gives the point of the best possible card to pick up from this stack.
	 * 
	 * @param stacks the stacks to consider in the move checking.
	 * @return the point of the best card to move.
	 */
	public Point getBestMove(Stack[] stacks)
	{
		Point point = new Point(x + Card.WIDTH / 2, y + SHIFT / 2);
		while (contains(point)) {
			if (canPickUp(point)) {
				int index = getCardIndex(point);
				Card card = hand.get(index);
				if (index == 0
						|| (index > 0 && !(hand.get(index - 1).isFaceUp() && hand
								.get(index - 1).canPlace(card)))) {

					for (Stack stack : stacks) {
						if (stack != this) {
							if (stack.getTopCard() == null) {
								if (index != 0)
									return point;
							} else if (stack.getTopCard().canPlace(card))
								return point;
						}
					}
				}
			}
			point.translate(0, SHIFT);
		}
		return null;
	}

	/**
	 * Gets the best stack to move cards to.
	 * 
	 * @param point the point to move cards from.
	 * @param stacks the list of stacks to consider.
	 * @return the best stack to move to.
	 */
	public Stack getBestTarget(Point point, Stack[] stacks)
	{
		Card card = hand.get(getCardIndex(point));
		Stack firstPossible = null;
		Stack firstEmpty = null;
		for (Stack stack : stacks) {
			Card topCard = stack.getTopCard();
			if (topCard == null) {
				firstEmpty = stack;
			} else if (topCard.canPlace(card)) {
				if (topCard.isSameSuit(card))
					return stack;
				if (firstPossible == null)
					firstPossible = stack;
			}
		}
		if (firstPossible == null)
			return firstEmpty;
		return firstPossible;
	}

	/**
	 * Gives the index of the card at the given point.
	 * 
	 * @param point the point to check for the card at.
	 * @return the index of the card at the given point.
	 */
	public int getCardIndex(Point point)
	{
		int index = getNoOfCards() - 1;
		while (index >= 0 && !hand.get(index).contains(point))
			index--;
		return index;
	}
}
