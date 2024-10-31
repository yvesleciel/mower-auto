package org.publicis.sapient.core.service;

import org.publicis.sapient.core.api.IMowerService;
import org.publicis.sapient.core.domain.Lawn;
import org.publicis.sapient.core.domain.Mower;
import org.publicis.sapient.core.exception.MowerInstructionException;

public class MowerService implements IMowerService {

    @Override
    public void executeInstructions(Mower mower, String instructions, Lawn lawn) {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'G':
                    mower.turnLeft();
                    break;
                case 'D':
                    mower.turnRight();
                    break;
                case 'A':
                    mower.moveForward(lawn);
                    break;
                default:
                    throw new MowerInstructionException("Invalid instruction: " + instruction);
            }
        }
    }
}
