package org.landsreyk.model;

import lombok.Data;

import java.text.MessageFormat;

@Data
public class Contact {
    private String fullName;
    private String phoneNumber;
    private String email;

    @Override
    public String toString() {
        return MessageFormat.format("""
                {0} | {1} | {2}
                """, fullName, phoneNumber, email);
    }
}