package org.landsreyk.service.impl;

import org.landsreyk.model.Contact;
import org.landsreyk.service.ContactsLoader;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactsLoaderImpl implements ContactsLoader {
    @Value("${app.contacts-initialization-file}")
    private String loadFileLocation;

    @Override
    public List<Contact> load() throws IOException {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(loadFileLocation))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Contact contact = new Contact();
                contact.setFullName(parts[0].trim());
                contact.setPhoneNumber(parts[1].trim());
                contact.setEmail(parts[2].trim());
                contacts.add(contact);
            }
        }
        return contacts;
    }
}
