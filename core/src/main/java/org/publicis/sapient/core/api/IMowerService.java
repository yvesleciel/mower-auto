package org.publicis.sapient.core.api;

import org.publicis.sapient.core.domain.Lawn;
import org.publicis.sapient.core.domain.Mower;


public interface IMowerService {
    public void executeInstructions(Mower mower, String instructions, Lawn lawn);
}
