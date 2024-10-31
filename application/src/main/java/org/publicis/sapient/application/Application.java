package org.publicis.sapient.application;

import org.publicis.sapient.core.domain.Lawn;
import org.publicis.sapient.core.domain.Mower;
import org.publicis.sapient.core.domain.Orientation;
import org.publicis.sapient.core.feature.IFeature;
import org.publicis.sapient.core.service.MowerService;
import org.publicis.sapient.infrastructure.FileReaderProvider;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Erreur : veuillez spécifier le chemin du fichier en argument.");
            System.exit(1);
        }
        String filePath = args[0];
        IFeature fileReader = new FileReaderProvider(filePath);
        List<String> lines = fileReader.readInput();
        Lawn lawn = parseLawn(lines.get(0));
        MowerService mowerService = new MowerService();

        for (int i = 1; i < lines.size(); i += 2) {
            Mower mower = parseMower(lines.get(i));
            String instructions = lines.get(i + 1);
            mowerService.executeInstructions(mower, instructions, lawn);
            System.out.println(mower);  // Affiche la position finale
        }
    }

    private static Lawn parseLawn(String line) {
        String[] parts = line.split(" ");
        int width = Integer.parseInt(parts[0]);
        int height = Integer.parseInt(parts[1]);
        return new Lawn(width, height);
    }

    private static Mower parseMower(String line) {
        String[] parts = line.split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        Orientation orientation = Orientation.valueOf(parts[2]);
        return new Mower(x, y, orientation);
    }
}
