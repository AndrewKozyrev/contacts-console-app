package org.landsreyk.model;

import lombok.Data;
import org.landsreyk.utils.EmailUtils;
import org.landsreyk.utils.PhoneUtils;

import java.text.MessageFormat;

@Data
public class Contact {
    private String fullName;
    private String phoneNumber;
    private String email;

    public void setEmail(String email) {
        if (!EmailUtils.isValidEmailAddress(email)) {
            System.err.println("Invalid email address format [%s]".formatted(email));
        }
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!PhoneUtils.isValidPhoneNumber(phoneNumber)) {
            System.err.println("Invalid phone number format [%s]".formatted(phoneNumber));
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return MessageFormat.format("""
                {0} | {1} | {2}
                """, fullName, phoneNumber, email);
    }
}