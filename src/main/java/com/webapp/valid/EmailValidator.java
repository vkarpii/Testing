package com.webapp.valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EmailValidator check the correctness of the email entered by the user
 */
public class EmailValidator {
    private EmailValidator(){}
    //This is a valid regex for validating e-mails.
    // It's totally compliant with RFC822 and accepts IP
    // address and server names (for intranet purposes).
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
                    Pattern.CASE_INSENSITIVE);

    public static boolean validate(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

}
