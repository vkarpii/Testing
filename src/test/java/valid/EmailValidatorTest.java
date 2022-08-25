package valid;

import com.webapp.valid.EmailValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailValidatorTest {

    @Test
    void validEmails(){
        String validEmail = "example@gmail.com";
        boolean actual = EmailValidator.validate(validEmail);
        boolean expected = true;
        assertEquals(expected,actual);
    }
    @Test
    void unvalidEmails(){
        String unvalidEmail = "examplegmail.com";
        boolean actual = EmailValidator.validate(unvalidEmail);
        boolean expected = false;
        assertEquals(expected,actual);
    }
}
