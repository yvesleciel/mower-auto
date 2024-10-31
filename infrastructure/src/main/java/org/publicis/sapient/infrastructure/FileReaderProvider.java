package org.publicis.sapient.infrastructure;

import org.publicis.sapient.core.feature.IFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderProvider implements IFeature {
    private final String filePath;

    public FileReaderProvider(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> readInput() {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Erreur de lecture du fichier", e);
        }
    }
}
