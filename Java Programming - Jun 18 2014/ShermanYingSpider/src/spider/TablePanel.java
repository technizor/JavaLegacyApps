package spider;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * The GUI of the Spider Solitaire program table that displays the card stacks
 * and the undealt cards.
 * 
 * @author Sherman Ying
 * @version May 14, 2013
 */
public class TablePanel extends JPanel
{
	// Inner class to handle mouse events
	private class MouseHandler extends MouseAdapter
	{
		@Override
		public void mousePressed(MouseEvent event)
		{
			suggestion = null;
			if (selectedItem != null)
				return;

			Point selectedPoint = event.getPoint();

			// Pick up one of cards from a Stack
			for (Stack nextStack : stacks)
				if (nextStack.contains(selectedPoint)) {
					// Split off this section or pick up a Card
					selectedItem = nextStack.pickUp(selectedPoint);

					// In case our move is not valid, we want to return the
					// Card(s) to where they initially came from
					sourceStack = nextStack;
					lastPoint = selectedPoint;
					repaint();
					return;
				}
			if (DECK_AREA.contains(selectedPoint))
				dealCards();
		}

		@Override
		public void mouseReleased(MouseEvent event)
		{
			if (event.getButton() != MouseEvent.BUTTON1
					&& event.getButton() != MouseEvent.BUTTON2
					&& event.getButton() != MouseEvent.BUTTON3)
				return;

			// If an item is selected, check out where it was dropped
			if (selectedItem != null) {
				// Check to see if we can add this to another stack
				for (Stack nextStack : stacks)
					if (selectedItem.intersects(nextStack)
							&& selectedItem.canPlaceOnStack(nextStack)) {
						moveCards(selectedItem, sourceStack, nextStack);
						selectedItem = null;
						return;
					}

				// Return to original stack if not valid spot to drop
				selectedItem.placeOnStack(sourceStack);
				selectedItem = null;
				repaint();
			}
		}
	}

	// Inner Class to handle mouse movements
	private class MouseMotionHandler implements MouseMotionListener
	{
		@Override
		public void mouseDragged(MouseEvent event)
		{
			Point currentPoint = event.getPoint();

			if (selectedItem != null) {
				// We use the difference between the lastPoint and the
				// currentPoint to drag the Stack/Card so that the position
				// of the mouse on the Stack/Card doesn't matter.
				// i.e. we can drag the card from any point on the card image
				selectedItem.drag(lastPoint, currentPoint);
				lastPoint = currentPoint;
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent event)
		{
			// Set the cursor to the hand if we are on a card
			Point currentPoint = event.getPoint();

			if (DECK_AREA.contains(currentPoint) && myDeck.cardsLeft() > 0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				return;
			}

			// Count down, since higher cards are on top
			for (Stack next : stacks)
				if (next.canPickUp(currentPoint)) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					return;
				}

			// Otherwise we just use the default cursor
			setCursor(Cursor.getDefaultCursor());
		}
	}

	private static final boolean animate = true;
	// Code for animation
	final static int ANIMATION_FRAMES = 3;
	private static final Rectangle DECK_AREA = new Rectangle(
			900 - Card.WIDTH - 50, 600 - Card.HEIGHT, Card.WIDTH + 40,
			Card.HEIGHT);

	private static final Point DECK_POS = new Point(900 - Card.WIDTH - 50,
			600 - Card.HEIGHT);
	private static final Image ARROW = new ImageIcon("images\\arrow.png").getImage();

	// Table display size
	final static int DISPLAY_HEIGHT = 600;

	final static int DISPLAY_WIDTH = 900;

	private static final Point FOUNDATION_POS = new Point(DISPLAY_WIDTH, 0);

	private static final long serialVersionUID = 5847365077984662238L;

	private boolean canSavePlayer;
	
	private Point suggestion;

	private Stack foundationStack;

	private Point lastPoint;

	private java.util.Stack<CardMove> moveStack;

	private Card movingCard;

	private SpiderDeck myDeck;

	private SpiderMain parent;

	private Player player;

	private Movable selectedItem;

	private Stack sourceStack;

	private Stack[] stacks;

	/**
	 * Creates a Table Panel that graphically displays the spider solitaire
	 * table.
	 * 
	 * @param parent the containing frame of this panel.
	 */
	public TablePanel(SpiderMain parent)
	{
		// Set up the size and background colour
		setPreferredSize(new Dimension(900, 600));
		this.setBackground(new Color(0, 125, 125));

		stacks = new Stack[10];
		int xStack = 30;
		int yStack = 30;
		for (int i = 0; i < stacks.length; i++) {
			stacks[i] = new Stack(xStack, yStack);
			xStack += 80;
		}
		myDeck = new SpiderDeck(-1, FOUNDATION_POS);

		moveStack = new java.util.Stack<CardMove>();
		foundationStack = new Stack(FOUNDATION_POS.x, FOUNDATION_POS.y);
		movingCard = null;
		this.parent = parent;
		// Add mouse listeners to the table panel
		this.addMouseListener(new MouseHandler());
		this.addMouseMotionListener(new MouseMotionHandler());
	}

	/**
	 * Checks whether the game has changed state from its original setting.
	 * 
	 * @return true if the game can be restarted, false if no actions have been
	 *         made.
	 */
	public boolean canReset()
	{
		if (player == null)
			return false;
		return player.canReset();
	}

	/**
	 * Check whether we can undo a move.
	 * 
	 * @return true if there are moves that have already been made, and false if
	 *         there are none.
	 */
	public boolean canUndo()
	{
		return !moveStack.isEmpty();
	}

	/**
	 * Delays the main thread by the given number of milliseconds.
	 * 
	 * @param msec the number of milliseconds to delay.
	 */
	private void delay(int msec)
	{
		try {
			Thread.sleep(msec);
		} catch (Exception e) {
		}
	}

	/**
	 * Animates a card that is moving.
	 * 
	 * @param cardToMove the card that is being animated.
	 * @param pos the initial position of the card.
	 * @param finalPos the final position of the card.
	 */
	public void moveACard(final Card cardToMove, Point pos, Point finalPos)
	{
		int dx = (finalPos.x - pos.x) / ANIMATION_FRAMES;
		int dy = (finalPos.y - pos.y) / ANIMATION_FRAMES;

		for (int times = 1; times <= ANIMATION_FRAMES; times++) {
			pos.x += dx;
			pos.y += dy;
			cardToMove.setLocation(pos.x, pos.y);

			// Update the drawing area
			paintImmediately(0, 0, getWidth(), getHeight());
			delay(30);

		}
		cardToMove.setLocation(finalPos);
	}

	/**
	 * Starts a new game with the specific seed, and number of suits.
	 * 
	 * @param player the current player.
	 * @param noOfSuits the number of suits to play with.
	 * @param seed the shuffling seed.
	 */
	public void newGame(Player player, int noOfSuits, int seed)
	{
		if (noOfSuits != myDeck.getNoOfSuits()
				|| seed != myDeck.getRandomSeed()) {
			myDeck = new SpiderDeck(noOfSuits, DECK_POS, seed);
		}
		this.player = player;
		// Shuffle and re position the Deck
		myDeck.shuffle();
		resetGame();
	}

	/**
	 * Draws the spider solitaire table.
	 * 
	 * @param g the Graphics object to draw onto.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Draw the Stacks and Deck
		for (Hand next : stacks)
			next.draw(g);

		myDeck.draw(g);

		// For animation
		if (movingCard != null)
			movingCard.draw(g);

		// Draw selected Stack or Card on top
		if (selectedItem != null)
			selectedItem.draw(g);

		if (player != null) {
			// Draw the score
			int score = player.getBaseScore() + player.getTotalPenalty();
			String str = "Score: " + score;
			g.drawString(str, 10, 20);
		}
		if(suggestion != null)
		{
			g.drawImage(ARROW, suggestion.x-ARROW.getWidth(null)/2, suggestion.y-ARROW.getHeight(null)/2, null);
		}
	}

	/**
	 * Resets all of the card stacks and returns all the cards to the deck in
	 * order to deal the initial layout of cards.
	 */
	public void resetGame()
	{
		foundationStack.clear();
		moveStack = new java.util.Stack<CardMove>();
		canSavePlayer = true;
		myDeck.rePositionCards();

		// Clear out all of the Stacks
		for (Hand next : stacks)
			next.clear();

		// Deal the next set of Cards to the Stack
		int index = 0;
		while (myDeck.cardsLeft() > 50) {
			Card dealtCard = myDeck.dealACard();
			Point pos = new Point(dealtCard.getLocation());
			stacks[index].addCard(dealtCard);
			Point finalPos = new Point(dealtCard.getLocation());
			if (animate)
				moveACard(dealtCard, pos, finalPos);
			index++;
			if (index == stacks.length)
				index = 0;
			if (myDeck.cardsLeft() < 60)
				dealtCard.flip();
		}
		repaint();
		player.resetScore();
	}

	/**
	 * Reverts the changes to the table that were made by the last move or deal.
	 */
	public void undoMove()
	{
		suggestion = null;
		CardMove lastMove = moveStack.pop();
		if (lastMove == null)
			return;

		if (lastMove.dealtCards()) {
			// Return any removed stacks from the deal in reverse order
			boolean[] transferredStacks = lastMove.getTransferredStacks();
			boolean[] flippedStacks = lastMove.getFlippedStacks();
			for (int s = 9; s >= 0; s--) {
				if (flippedStacks[s]) {
					stacks[s].getTopCard().flip();
					player.undoFlip();
				}
				if (transferredStacks[s]) {
					Stack sentCards = new Stack(stacks[s].x, stacks[s].y
							+ stacks[s].height + Stack.SHIFT);
					int displacement = foundationStack.getNoOfCards() - 13;
					for (int c = 0; c < 13; c++)
						sentCards.addCard(foundationStack.remove(displacement));
					sentCards.placeOnStack(stacks[s]);
					player.undoFoundation();
				}
			}

			// Undeal the dealt cards if it was the last action
			for (int c = 9; c >= 0; c--)
				myDeck.undeal(stacks[c].removeTopCard());
		}

		// Otherwise, revert the last move
		else {
			Stack sourceStack = lastMove.getSourceStack();
			Stack targetStack = lastMove.getTargetStack();
			int cardsMoved = lastMove.getCardsMoved();

			if (lastMove.sentToFoundation()) {
				// Flip down the top target card if it was facedown to before
				// the move and revert the score gained
				if (lastMove.flippedTarget()) {
					targetStack.getTopCard().flip();
					player.undoFlip();
				}

				// Return the sent cards back to the stack they were sent from
				// and
				// revert the score gained
				Stack sentCards = new Stack(sourceStack.x, sourceStack.y
						+ sourceStack.height + Stack.SHIFT);
				int displacement = foundationStack.getNoOfCards() - 13;
				for (int c = 0; c < 13; c++)
					sentCards.addCard(foundationStack.remove(displacement));
				sentCards.placeOnStack(targetStack);
				player.undoFoundation();
			}

			// Flip back the top source card if it was facedown to before the
			// move and revert the score gained
			if (lastMove.flippedSource()) {
				sourceStack.getTopCard().flip();
				player.undoFlip();
			}

			// Return the moved cards to the original stack
			Stack movedCards = new Stack(sourceStack.x, sourceStack.y
					+ sourceStack.height + Stack.SHIFT);
			int origCards = targetStack.getNoOfCards() - cardsMoved;
			for (int c = 0; c < cardsMoved; c++)
				movedCards.addCard(targetStack.remove(origCards));
			movedCards.placeOnStack(sourceStack);
		}

		// Penalize the player for undoing a move
		player.undo();
		repaint();
	}

	/**
	 * Suggests a move for the player to make.
	 */
	public void useHint()
	{
		// Ensure that a game is being played.
		if (myDeck.getNoOfSuits() < 0)
			return;
		if (foundationStack.getNoOfCards() == 104)
			return;
		// Don't make a hint again if the same hint is already shown
		if(suggestion != null)
			return;

		// Compile a list of the best possible moves from each Stack
		Point[] pointList = new Point[stacks.length];
		int bestStack = -1;
		for (int stackNo = 0; stackNo < stacks.length; stackNo++) {
			pointList[stackNo] = stacks[stackNo].getBestMove(stacks);
			if (pointList[stackNo] != null
					&& (bestStack == -1 || stacks[bestStack].getNoOfCards()
							- stacks[bestStack]
									.getCardIndex(pointList[bestStack]) < stacks[stackNo]
							.getNoOfCards()
							- stacks[stackNo].getCardIndex(pointList[stackNo])))
				bestStack = stackNo;
		}

		// If there are no moves that offer any progress, deal cards
		if (bestStack == -1) {
			if(myDeck.cardsLeft() > 0)
			{
				suggestion = new Point(DECK_POS.x+Card.WIDTH/2, DECK_POS.y+Stack.SHIFT/2);
				player.useHint();
			}
			
			/* Automatically deal
			if (dealCards())
				player.useHint();
				*/
		} else {
			// Suggest the move
			suggestion = pointList[bestStack];
			player.useHint();
			/* Automatically do the move
			Stack target = stacks[bestStack].getBestTarget(
					pointList[bestStack], stacks);
			if (target != null) {
				moveCards(stacks[bestStack].pickUp(pointList[bestStack]),
						stacks[bestStack], target);
				player.useHint();
			}*/
		}
		repaint();
	}

	/**
	 * Deals cards from the deck if possible.
	 * 
	 * @return true if cards were dealt, false if they were not.
	 */
	private boolean dealCards()
	{
		// Deal new Cards from Deck if clicked in bottom corner
		if (myDeck.cardsLeft() >= 10) {
			// Deal 10 new Cards
			boolean[] transferredStacks = new boolean[10];
			boolean[] flippedStacks = new boolean[10];
			for (int index = 0; index < 10; index++) {
				Card dealtCard = myDeck.dealACard();
				Point pos = new Point(dealtCard.getLocation());
				stacks[index].addCard(dealtCard);
				Point finalPos = new Point(dealtCard.getLocation());
				if (animate)
					moveACard(dealtCard, pos, finalPos);
				dealtCard.flip();

				// Track the stacks that removed a sequence from the deal
				Stack sequence = stacks[index].removeFullSequence();
				if (sequence != null) {
					transferredStacks[index] = true;
					sequence.placeOnStack(foundationStack);
					player.addFoundationScore();
				}
				if (!stacks[index].getTopCard().isFaceUp()) {
					flippedStacks[index] = true;
					stacks[index].getTopCard().flip();
					player.addFlipScore();
				}
			}
			moveStack.add(new CardMove(transferredStacks, flippedStacks));
			player.incrementMoves();
			repaint();
			return true;
		}
		return false;
	}

	/**
	 * Makes a move from the source stack to the target stack.
	 * 
	 * @param item the stack of cards being moved.
	 * @param source the stack from which the cards came.
	 * @param target the stack to move the card to.
	 */
	private void moveCards(Movable item, Stack source, Stack target)
	{
		// Data to track the details of the move
		int cardsMoved = ((Stack) item).getNoOfCards();
		boolean transferredToFoundation = false;
		boolean flippedSourceCard = false;
		boolean flippedTargetCard = false;

		// Place the selected item onto the new Stack
		item.placeOnStack(target);

		// If a full sequence is created, move it to the
		// foundation
		Stack fullSequence = target.removeFullSequence();
		if (fullSequence != null) {
			transferredToFoundation = true;
			fullSequence.placeOnStack(foundationStack);
			player.addFoundationScore();

			Card topCard = target.getTopCard();
			if (topCard != null && !topCard.isFaceUp()) {
				topCard.flip();
				flippedTargetCard = true;
				player.addFlipScore();
			}
		}

		// Check to see if the top card of the source stack needs to be flipped
		Card topCard = source.getTopCard();
		if (topCard != null && !topCard.isFaceUp()) {
			topCard.flip();
			flippedSourceCard = true;
			player.addFlipScore();
		}

		// Add the information to the move list, then increment moves and check
		// for a win condition
		moveStack.add(new CardMove(source, target, cardsMoved,
				flippedSourceCard, flippedTargetCard, transferredToFoundation));

		player.incrementMoves();
		repaint();
		if (foundationStack.getNoOfCards() == 104 && canSavePlayer) {
			canSavePlayer = false;
			parent.savePlayer();
		}
	}
}
