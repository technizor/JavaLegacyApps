package spider;

/**
 * Tracks the changes to the board when a move is made to allow for undoing.
 * 
 * @author Sherman Ying
 * @version May 16, 2013
 */
public class CardMove
{
	private int cardsMoved;
	private boolean dealtCards;
	private boolean[] transferredStacks;
	private boolean[] flippedStacks;
	private boolean flippedSource;
	private boolean flippedTarget;
	private boolean sentToFoundation;
	private Stack source;
	private Stack target;

	/**
	 * Creates a CardMove object that tracks a deal.
	 * 
	 * @param transferredStacks whether each stack sent cards to the foundation
	 *            as a result of the deal.
	 * @param flippedStacks whether each stack flipped the top card after
	 *            sending to the foundation.
	 */
	public CardMove(boolean[] transferredStacks, boolean[] flippedStacks)
	{
		dealtCards = true;
		this.transferredStacks = transferredStacks;
		this.flippedStacks = flippedStacks;
	}

	/**
	 * Creates a CardMove object that tracks the two Stacks affected, the number
	 * of cards moved, and whether the first stack flipped a card, and if the
	 * move transferred cards to the foundation.
	 * 
	 * @param firstStack the stack the cards move from.
	 * @param targetStack the stack the cards move to.
	 * @param cardsMoved the number of cards moved.
	 * @param flippedSource whether the move flipped the top source stack card.
	 * @param flippedTarget whether the move flipped the top target stack card.
	 * @param sentToFoundation whether the move sent cards to the foundation.
	 */
	public CardMove(Stack source, Stack target, int cardsMoved,
			boolean flippedSource, boolean flippedTarget,
			boolean sentToFoundation)
	{
		this.source = source;
		this.target = target;
		this.cardsMoved = cardsMoved;
		this.flippedSource = flippedSource;
		this.flippedTarget = flippedTarget;
		this.sentToFoundation = sentToFoundation;
		this.dealtCards = false;
		this.transferredStacks = new boolean[10];
		this.flippedStacks = new boolean[10];
	}

	/**
	 * Checks whether this move dealt any cards.
	 * 
	 * @return true if cards were dealt, false if not.
	 */
	public boolean dealtCards()
	{
		return dealtCards;
	}

	/**
	 * Tells whether the move triggered a card flip on the source stack.
	 * 
	 * @return whether the first stack flipped the new top card.
	 */
	public boolean flippedSource()
	{
		return flippedSource;
	}

	/**
	 * Tells whether the move triggered a card flip on the target stack.
	 * 
	 * @return whether the first stack flipped the new top card.
	 */
	public boolean flippedTarget()
	{
		return flippedTarget;
	}

	/**
	 * Gives the number of cards relocated in this move.
	 * 
	 * @return the number of cards moved
	 */
	public int getCardsMoved()
	{
		return cardsMoved;
	}

	/**
	 * Gives a reference to the stack giving the cards from the move.
	 * 
	 * @return the stack the cards moved from
	 */
	public Stack getSourceStack()
	{
		return source;
	}

	/**
	 * Gives a reference to the stack receiving the cards from the move.
	 * 
	 * @return the stack that had cards moved to it
	 */
	public Stack getTargetStack()
	{
		return target;
	}

	/**
	 * Checks whether this move triggered a transfer to the foundation stack.
	 * 
	 * @return true if this move sent cards to the foundation, false if it did
	 *         not.
	 */
	public boolean sentToFoundation()
	{
		return sentToFoundation;
	}

	/**
	 * Tracks the stacks that were sent to the foundation by a deal.
	 * 
	 * @return true or false depending on whether each stack was sent to the
	 *         foundation.
	 */
	public boolean[] getTransferredStacks()
	{
		return transferredStacks;
	}

	/**
	 * Tracks the stacks that had the top card flipped after a deal.
	 * 
	 * @return true of false depending on whether each stack was flipped.
	 */
	public boolean[] getFlippedStacks()
	{
		return flippedStacks;
	}
}
