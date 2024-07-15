package com.text.processor.service;

import static org.junit.jupiter.api.Assertions.*;

import com.text.processor.model.TextEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class TextProcessorServiceTest {

    @Mock
    private TextEntry mockedTextEntry;

    @InjectMocks
    private TextProcessorService textProcessorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testProcessTextEntries_InvalidInputFormat() {

        String invalidInput = "3the force is strong in this one\n";


        List<String> output = textProcessorService.processTextEntries(invalidInput);

        assertNotNull(output);
        assertTrue(output.isEmpty());
    }

    @Test
    void testProcessTextEntries_InvalidNumberFormat() {

        String invalidNumberInput = "3A\\the force is strong in this one\n";

        List<String> output = textProcessorService.processTextEntries(invalidNumberInput);

        assertNotNull(output);
        assertTrue(output.isEmpty());
    }
}