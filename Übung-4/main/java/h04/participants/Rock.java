package h04.participants;

import fopbot.Direction;
import fopbot.Robot;

/**
 * Base class for the rocks in a rock paper scissors tournament.
 * Rocks are descendants of {@link Participant} and fight on one-on-one matchups.
 * If a rock loses, it's turned off.
 * Once a rock wins a tournament, it performs a victory dance
 * by moving in a square shape, who's size depends on how many rounds the rock won.
 */
public class Rock extends Participant {
    private int roundsWon;

    /**
     * Creates a participant of species ROCK and orientation UP
     * initializes attributes roundsWon to 0 and numberOfCoins to 3
     *
     * @param x The x coordinate of this participant
     * @param y The y coordinate of this participant
     */
    public Rock(int x, int y) {
        super(Species.ROCK, x, y, Direction.UP);
        setNumberOfCoins(3);
        roundsWon = 0;
    }

    /**
     * Assuming this Rock has won at least one round,
     * makes it perform its victory dance.
     * It moves in a square shape, where number of rounds won represents side length of square
     */
    @Override
    public void doVictoryDance() {
        if (roundsWon > 0) {
            for (int i = 0; i<4; i++) {
                for (int n = 0; n<roundsWon; n++) {
                    if (isFrontClear()) {move();}
                }
                for (int number_of_turns = 0; number_of_turns<3; number_of_turns++) {
                    turnLeft();
                }
            }
        }
    }

    /**
     * Makes this Rock fight against the given opponent.
     * Depending on Species of opponent, the winner is calculated and returned, leaving loser de-activated.
     * If this Rock wins, then its counter-attribute "roundsWon" is incremented by 1.
     *
     * @param opponent The opponent to fight
     * @return Either <code>this</code> or <code>opponent</code>.
     */
    @Override
    public Participant fight(Participant opponent) {
        switch (opponent.getSpecies()) {
            case SCISSORS:
                opponent.turnOff();
                roundsWon++;
                return this;
            case ROCK:
                if (opponent.getX() > getX()) {
                    turnOff();
                    return opponent;
                } else {
                    opponent.turnOff();
                    roundsWon++;
                    return this;
                }
            default:
                turnOff();
                return opponent;
        }
    }
}
