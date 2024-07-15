package com.text.processor.service;


import com.text.processor.model.TextEntry;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ApplicationScoped
public class TextProcessorService {

    public List<String> processTextEntries(String input) {
        List<String> output = new ArrayList<>();

        String[] lines = input.split("\n");
        for (String line : lines) {
            try {
                TextEntry textEntry = parseTextEntry(line);
                String processedText = processTextEntry(textEntry);
                output.add(processedText);
            } catch (IllegalArgumentException e) {
                log.error("Error parsing text entry: {}", e.getMessage());
                // En caso de error al procesar una línea, omitir y continuar
            }
        }

        return output;
    }

    TextEntry parseTextEntry(String line) {
        int index = line.indexOf("\\");
        if (index == -1) {
            throw new IllegalArgumentException("Invalid input format");
        }

        String numberStr = line.substring(0, index).trim();
        String phrase = line.substring(index + 1).trim();

        try {
            int number = Integer.parseInt(numberStr);
            return new TextEntry(number, phrase);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format");
        }
    }
    public String processTextEntry(TextEntry textEntry) {
        // Remover caracteres no alfabéticos y convertir a minúsculas
        String cleanedPhrase = textEntry.getPhrase().replaceAll("[^a-zA-Z ]", "").toLowerCase();

        // Verificar si el número de palabras es igual al número dado
        boolean isWordCountCorrect = countWords(cleanedPhrase) == textEntry.getNumber();

        // Formato de salida requerido
        return cleanedPhrase + "\\" + isWordCountCorrect;
    }



    public int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        String[] words = text.split("\\s+");
        return words.length;
    }
}