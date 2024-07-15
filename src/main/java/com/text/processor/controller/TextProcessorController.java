package com.text.processor.controller;

import com.text.processor.service.TextProcessorService;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TextProcessorController implements Serializable {

    private String textInput;
    private List<String> processedTexts;

    @Inject
    private TextProcessorService textProcessorService;

    public String getTextInput() {
        return textInput;
    }

    public void setTextInput(String textInput) {
        this.textInput = textInput;
    }

    public List<String> getProcessedTexts() {
        return processedTexts;
    }

    public void processText() {
        processedTexts = textProcessorService.processTextEntries(textInput);
    }
}
