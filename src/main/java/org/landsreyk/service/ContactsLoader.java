package org.landsreyk.service;


import org.landsreyk.model.Contact;

import java.io.IOException;
import java.util.List;

@FunctionalInterface
public interface ContactsLoader {

    List<Contact> load() throws IOException;
}
