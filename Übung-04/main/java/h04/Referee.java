package h04;

import fopbot.Direction;
import fopbot.World;
import h04.participants.*;
import org.tudalgo.algoutils.student.Student;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.Objects;

/**
 * Instances of the referee class manage a rock paper scissors knock-out tournaments.
 */
@DoNotTouch
public class Referee {

    /**
     * This array holds all participants that have not been eliminated yet.
     */
    @DoNotTouch
    private Participant[] participants;

    /**
     * The initial line-up encoded by the representing species.
     */
    @DoNotTouch
    private Species[] lineUp;

    /**
     * Create a new referee and pass the line-up as an array of species.
     */
    @DoNotTouch
    public Referee(Species... lineUp) {
        this.lineUp = lineUp;
    }

    /**
     * Run the tournament.
     * Create participants from the initial line-up and hold rounds until a single participant wins.
     * The winner is then announced on the console and performs a victory dance.
     */
    @DoNotTouch
    public void hostTournament() {
        placeLineUp();

        while (participants.length > 1) {
            doRound();
        }

        Participant winner = participants[0];
        System.out.println("The winner is: " + winner.getSpecies());
        winner.doVictoryDance();
    }

    /**
     * Clear the world.
     * Then, according to the line-up, place new participants for the tournament.
     */
    @StudentImplementationRequired
    private void placeLineUp() {
        World.reset();
        participants = new Participant[lineUp.length];
        int xCoordinate = 0;
        for (Species type: lineUp) {
            switch (type) {
                case SCISSORS -> participants[xCoordinate] = new Scissors(xCoordinate, 0);
                case PAPER -> participants[xCoordinate] = new Paper(xCoordinate, 0);
                default -> participants[xCoordinate] = new Rock(xCoordinate, 0);
            }
            xCoordinate++;
        }
//        Student.crash("TODO: H4.2.2");
    }

    /**
     * Play a single round of the tournament and advance all winning participants.
     */
    @StudentImplementationRequired
    private void doRound() {
        participants = determineVictors(participants);
        arrangeParticipants(participants);
//        Student.crash("TODO: H4.2.5");
    }

    /**
     * Determines the victors from a given array of participants by simulating matchups.
     * <p>
     * Participants are paired sequentially (i.e., participant at index 0 vs. index 1, 2 vs. 3, etc.).
     * For each pair, save the winner in an array.
     *
     * @param participants An array of {@link Participant}s for this round
     *
     * @return An array of {@link Participant}s representing the victors of each matchup
     */
    @StudentImplementationRequired
    private Participant[] determineVictors(Participant[] participants) {
        int len = participants.length;
        Participant[] winner = new Participant[len%2==0? len/2 : len/2+1];
        for (int i = 0; i < participants.length; i=i+2) {
            winner[i/2] = playMatchUp(participants[i]);
        }
        return winner;
//        return Student.crash("TODO: H4.2.4");
    }

    /**
     * Find the opponent of the given participant and let them fight against one another.
     *
     * @param participant A participant
     *
     * @return The winner of this match
     */
    @DoNotTouch
    private Participant playMatchUp(Participant participant) {
        Participant opponent = getOpponent(participant);

        if (opponent == null) {
            // No one left to fight in this round
            return participant;
        }

        return participant.fight(opponent);
    }

    /**
     * Find the opponent of the given participant by looking at their x coordinate.
     * <p>
     * Neighboring participants are paired against each other so the participant
     * with x coordinate <code>x = 2*n</code> always play against <code>x = 2*n + 1</code>.
     * <p>
     * If the number participating is odd, this will return null for the right most participant
     * because they are left with one to play against.
     *
     * @param participant A participant
     * @return The opponent of the given participant or null
     */
    @StudentImplementationRequired
    private Participant getOpponent(Participant participant) {
        // Direction is condensed to make code lighter
        if (participant.getOrientation() == Direction.DOWN) {
            participant.turnLeft();
            participant.turnLeft();
        }

        // if participant stored in even position, then checks opponent to the right and vice versa
        if (participant.getX()%2==0) {
            for (int i = 0; i < 3; i++) {
                participant.turnLeft();
            }
        } else {
            participant.turnLeft();
        }

        // Return Participant temporarily stored to correct position before returning
        Participant returnValue = Utils.getParticipantInFrontOf(participant);
        while (participant.getDirection() != participant.getOrientation()) {
            participant.turnLeft();
        }
        return returnValue;
//        return Student.crash("TODO: H4.2.3");
    }

    /**
     * Move the given participants to the next round by first moving them up
     * and then as far to the left as possible until they are tightly packed together.
     *
     * @param participants An array of winning participants.
     */
    @DoNotTouch
    private void arrangeParticipants(Participant[] participants) {
        ascend(participants);
        for (int i = 0; i < participants.length; i++) {
            moveUp(participants[i]);
        }
    }

    /**
     * Ascend the given participants to the next round.
     *
     * @param participants An array of winning participants.
     */
    @StudentImplementationRequired
    private void ascend(Participant[] participants) {
        for (Participant p: participants) {
            if (p.getOrientation() == Direction.DOWN) {
                p.turnLeft();
                p.turnLeft();
            }
            p.move();
            while (p.getDirection() != p.getOrientation()) {
                p.turnLeft();
            }
        }

//        Student.crash("TODO: 4.2.1");
    }
    /**
     * Move the given participant to the left until they are tightly packed with their neighbor.
     *
     * @param participant An array of winning participants.
     */
    @StudentImplementationRequired
    private void moveUp(Participant participant) {
        if (participant.getOrientation() == Direction.DOWN) {
            for (int i = 0; i<3; i++) {
                participant.turnLeft();
            }
        } else {
            participant.turnLeft();
        }
        while (Utils.getParticipantInFrontOf(participant) == null & participant.isFrontClear()) {
            participant.move();
        }
        while (participant.getDirection() != participant.getOrientation())
            participant.turnLeft();
//        Student.crash("TODO: H4.2.1");
    }

    /**
     * Set the line-up of the next tournament to be played.
     *
     * @param lineUp An array of species representing the line-up.
     */
    public void setLineUp(Species... lineUp) {
        this.lineUp = lineUp;
    }

}
