package org.landsreyk.service.impl;

import org.landsreyk.model.Contact;
import org.landsreyk.service.ContactsLoader;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ContactsLoaderImpl implements ContactsLoader {
    @Value("${app.contacts-init-file}")
    private String initFile;

    @Override
    public List<Contact> load() throws IOException {
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        InputStream inputStream = appClassLoader.getResourceAsStream(initFile);
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
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
