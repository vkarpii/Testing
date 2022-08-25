package AES;

import com.webapp.security.AES;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AESTest {
    @Test
    void aesTest(){
        String text = "Some text";
        String encrypted = AES.encrypt(text);
        Assertions.assertNotEquals(text, encrypted);
        Assertions.assertEquals(text,AES.decrypt(encrypted));
    }
    @Test
    void aesDecryptNullTest(){
        String text = "-1";
        Assertions.assertNull(AES.decrypt(text));
        Assertions.assertNull(AES.decrypt(null));
    }
    @Test
    void aesEncryptNullTest(){
        String text = null;
        Assertions.assertNull(AES.encrypt(text));
    }
}
