package com.teachmeskills.lesson_14.methodsForDoc.validation;

import com.teachmeskills.lesson_14.constants.Constants;

public class ValidateDocument {
    public static String validateDocumentNumber(String docNum) {

        if (docNum.length() != Constants.DOC_LENGTH) {
            return "Invalid length; must be " + Constants.DOC_LENGTH + " characters.";
        }

        if (!docNum.startsWith(Constants.DOCUMENT_PREFIX) && !docNum.startsWith(Constants.CONTRACT_PREFIX)) {
            return "Invalid prefix; must start with 'docnum' or 'contract'.";
        }

        if (!docNum.matches("[a-zA-Z0-9]+")) {
            return "Contains invalid characters; only letters and numbers are allowed.";
        }

        return null;
    }
}
