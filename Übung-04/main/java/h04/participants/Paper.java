package h04.participants;

import fopbot.Direction;

/**
 * Base class for the papers in a rock paper scissors tournament.
 * Papers are descendants of {@link Participant} and fight on one-on-one matchups.
 * If a paper loses, they're turned off.
 * Once a paper wins a tournament, it performs a victory dance
 * by performing laps around its row, depending on its starting x coordinate.
 */
public class Paper extends Participant{
    private static int numberOfPapers = 0;
    private static int startingXPosition;

    /**
     * Creates a participant of species PAPER and orientation UP or DOWN
     * Orientation depends on whether the amount of existing papers are even or odd
     *
     * @param x The x coordinate of this participant
     * @param y The y coordinate of this participant
     */
    public Paper (int x, int y) {
        super(Species.PAPER, x, y, numberOfPapers%2==0? Direction.UP:Direction.DOWN);
        numberOfPapers++;
        setNumberOfCoins(2);
        /*
        If there's a paper winner, then it has to be the one assigned last i.e. in the largest X-Coordinate, therefore
        allowing this assignment to work, as startingXPosition is reassigned each time a new paper-object is made.
         */
        startingXPosition = x;
    }

    /**
     * Makes this Paper perform its victory dance
     * It's turned to face to the Right.
     * It runs a victory lap along its row and repeats as many times as it's initial startingXPosition
     * Since the furtherest most Paper always wins, the startingXPosition is always its own
     */
    @Override
    public void doVictoryDance() {
        if (getOrientation() == Direction.UP) {
            for (int i = 0; i < 3; i++) {
                turnLeft();
            }
        } else {
            turnLeft();
        }

        for (int i = 0; i<startingXPosition; i++) {
            while (isFrontClear()) {
                move();
            }
            turnLeft();
            turnLeft();
        }

    }

    /**
     * Makes this Paper fight against the given opponent.
     * Depending on Species of opponent, the winner is calculated and returned, leaving loser de-activated.
     *
     * @param opponent The opponent to fight
     * @return Either <code>this</code> or <code>opponent</code>.
     */
    @Override
    public Participant fight(Participant opponent) {
        switch (opponent.getSpecies()) {
            case ROCK:
                opponent.turnOff();
                return this;
            case PAPER:
                if (opponent.getX() > getX()) {
                    turnOff();
                    return opponent;
                } else {
                    opponent.turnOff();
                    return this;
                }
            default:
                turnOff();
                return opponent;
        }
    }
}
