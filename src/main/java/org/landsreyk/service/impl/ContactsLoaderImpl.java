package org.landsreyk.service.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.landsreyk.model.Contact;
import org.landsreyk.service.ContactsLoader;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ContactsLoaderImpl implements ContactsLoader {
    @Value("${app.contacts-init-file}")
    private String initFile;

    @Override
    public List<Contact> load() throws IOException {
        List<Contact> contacts = new ArrayList<>();
        File file = new File(initFile);
        if (!file.exists()) {
            System.err.printf("""
                    Файл для инициализации [%s] не существует.
                    Укажите путь к существующему файлу инициализации в application.yml.
                    Посмотрите README.md для ознакомления с инструкцией.
                    %n""", initFile);
            System.exit(1);
        }
        try (FileInputStream fileInputStream = FileUtils.openInputStream(file)) {
            List<String> lines = IOUtils.readLines(fileInputStream, StandardCharsets.UTF_8);
            for (String line : lines) {
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
