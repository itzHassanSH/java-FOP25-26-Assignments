package h04.participants;

import fopbot.Direction;
import fopbot.Robot;

/**
 * Base class for the scissors in a rock paper scissors tournament.
 * Scissors are descendants of {@link Participant} and fight on one-on-one matchups.
 * If scissors lose, they're turned off.
 * Once scissors win a tournament, they perform a victory dance
 * by dropping their stolen coins along their row.
 */
public class Scissors extends Participant {
    /**
     * Creates a participant of species SCISSORS and orientation UP
     *
     * @param x The x coordinate of this participant
     * @param y The y coordinate of this participant
     */
    public Scissors(int x, int y) {
        super(Species.SCISSORS, x, y, Direction.UP);
    }

    /**
     * Makes this Scissor perform its victory dance
     * It turned to face to the Right.
     * It drops a coin on every position for its row, stopping when either the coins run out or it reaches the end.
     */
    @Override
    public void doVictoryDance() {
        for (int i = 0; i<3; i++) {
            turnLeft();
        }
        while (isFrontClear() & hasAnyCoins()) {
            putCoin();
            move();
        }
    }

    /**
     * Makes this Scissor fight against the given opponent.
     * Regardless of winning, the Scissor steals its opponent's Coins, as long as they themselves are not scissors.
     * Depending on Species of opponent, the winner is calculated and returned, leaving loser de-activated.
     *
     * @param opponent The opponent to fight
     * @return Either <code>this</code> or <code>opponent</code>.
     */
    @Override
    public Participant fight(Participant opponent) {
        if (opponent.getSpecies() != Species.SCISSORS) {
            setNumberOfCoins(getNumberOfCoins()+opponent.getNumberOfCoins());
            opponent.setNumberOfCoins(0);
        }
        switch (opponent.getSpecies()) {
            case PAPER:
                opponent.turnOff();
                return this;
            case SCISSORS:
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
