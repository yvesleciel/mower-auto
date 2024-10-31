package org.publicis.sapient.core.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.publicis.sapient.core.domain.Lawn;
import org.publicis.sapient.core.domain.Mower;
import org.publicis.sapient.core.domain.Orientation;
import org.publicis.sapient.core.service.MowerService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerServiceTest {

    private MowerService mowerService;
    private Lawn lawn;

    @BeforeEach
    void setUp() {
        mowerService = new MowerService();
        lawn = new Lawn(5, 5);  // Création d'une pelouse de 5x5 pour les tests
    }

    @Test
    void testMoveForwardWithinBounds() {
        Mower mower = new Mower(2, 2, Orientation.N);

        mowerService.executeInstructions(mower, "A", lawn);

        // La tondeuse avance vers le nord de 2,2 à 2,3
        assertEquals(2, mower.getX());
        assertEquals(3, mower.getY());
        assertEquals(Orientation.N, mower.getOrientation());
    }

    @Test
    void testMoveForwardOutOfBounds() {
        Mower mower = new Mower(0, 0, Orientation.S);

        mowerService.executeInstructions(mower, "A", lawn);

        // La tondeuse reste sur place car elle est au bord inférieur
        assertEquals(0, mower.getX());
        assertEquals(0, mower.getY());
        assertEquals(Orientation.S, mower.getOrientation());
    }

    @Test
    void testTurnLeft() {
        Mower mower = new Mower(2, 2, Orientation.N);

        mowerService.executeInstructions(mower, "G", lawn);

        // La tondeuse tourne à gauche de N à W
        assertEquals(Orientation.W, mower.getOrientation());
    }

    @Test
    void testTurnRight() {
        Mower mower = new Mower(2, 2, Orientation.N);

        mowerService.executeInstructions(mower, "D", lawn);

        // La tondeuse tourne à droite de N à E
        assertEquals(Orientation.E, mower.getOrientation());
    }

    @Test
    void testComplexInstructionSet() {
        Mower mower = new Mower(1, 2, Orientation.N);
        String instructions = "GAGAGAGAA";

        mowerService.executeInstructions(mower, instructions, lawn);

        // Attendu : la tondeuse est à 1,3 orientée vers le Nord après cette série d'instructions
        assertEquals(1, mower.getX());
        assertEquals(3, mower.getY());
        assertEquals(Orientation.N, mower.getOrientation());
    }

    @Test
    void testMultipleForwardMoves() {
        Mower mower = new Mower(0, 0, Orientation.N);
        String instructions = "AAAAA";  // Essaye de dépasser la limite nord de la pelouse

        mowerService.executeInstructions(mower, instructions, lawn);

        // La tondeuse devrait être à la limite supérieure : (0,5)
        assertEquals(0, mower.getX());
        assertEquals(5, mower.getY());
        assertEquals(Orientation.N, mower.getOrientation());
    }
}
