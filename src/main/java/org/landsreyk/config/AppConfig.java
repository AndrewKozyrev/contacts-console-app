package org.landsreyk.config;

import org.landsreyk.service.ContactsLoader;
import org.landsreyk.service.impl.ContactsLoaderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;

@Configuration
public class AppConfig {

    @Bean
    @Profile("init")
    public ContactsLoader contactsLoader() {
        return new ContactsLoaderImpl();
    }

    @Bean
    public ContactsLoader contactsInitializer() {
        return ArrayList::new;
    }
}
