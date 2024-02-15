package org.landsreyk.utils;

import lombok.experimental.UtilityClass;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@UtilityClass
public class EmailUtils {
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
