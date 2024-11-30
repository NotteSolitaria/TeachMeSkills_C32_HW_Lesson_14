package com.teachmeskills.lesson_14;

import com.teachmeskills.lesson_14.constants.Constants;
import com.teachmeskills.lesson_14.methodsForDoc.validation.ValidateDocument;
import com.teachmeskills.lesson_14.methodsForDoc.writeToFile.WriteToFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Let's say there is a file with document numbers.
 * A document number is a string consisting of letters and numbers (without service characters).
 * Let this file contain each document number on a new line and no other information in the line, only the document number.
 * A valid document number must be 15 characters long and begin with the sequence docnum (then any sequence of letters/numbers) or contract (then any sequence of letters/numbers).
 * Write a program to read information from the input file - the path to the input file must be specified through the console.
 * The program must check the document numbers for validity.
 * Valid docnum document numbers should be written to one report file.
 * Valid contract numbers contract should be written to another report file.
 * Invalid document numbers should be written to another report file, but after the document numbers, information should be added about why this document is invalid (incorrect sequence of characters at the beginning / there are service characters in the document name, etc.).
 */

public class ApplicationRunner {
    public static void main(String[] args) {

        String FilePath = "C:\\Users\\danii\\IdeaProjects\\TeachMeSkills_C32_HW_Lesson_14\\resourses\\sources\\docexample.txt";

        List<String> validDoc = new ArrayList<>();
        List<String> validContract = new ArrayList<>();
        List<String> invalidDoc = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String validationResult = ValidateDocument.validateDocumentNumber(line.trim());

                if (validationResult == null) {
                    if (line.startsWith(Constants.DOCUMENT_PREFIX)) {
                        validDoc.add(line);
                    } else if (line.startsWith(Constants.CONTRACT_PREFIX)) {
                        validContract.add(line);
                    }
                } else {
                    invalidDoc.add(line + " - " + validationResult);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        WriteToFile.writeToFile("valid_documents.txt", validDoc);
        WriteToFile.writeToFile("valid_contracts.txt", validContract);
        WriteToFile.writeToFile("invalid_documents.txt", invalidDoc);

        System.out.println("Processing complete. Check the output files for results.");
    }
}
