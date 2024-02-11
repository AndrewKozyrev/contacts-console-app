package org.landsreyk.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.landsreyk.model.Contact;
import org.landsreyk.service.ContactsLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import static org.landsreyk.service.impl.ConsoleContactsManager.MenuAction.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsoleContactsManager implements CommandLineRunner {
    private final Scanner input = new Scanner(System.in);
    private List<Contact> contacts;

    private final ContactsLoader contactsLoader;
    @Value("${app.contacts-save-file}")
    private String saveFileLocation;

    @PostConstruct
    public void initContacts() throws IOException {
        contacts = contactsLoader.load();
    }

    @Override
    public void run(String... args) throws Exception {
        displayMenu();
        while (true) {
            System.out.print("\nВыберите действие: ");
            int action = input.nextInt();
            input.nextLine();
            switch (action) {
                case LIST_CONTACTS -> listContacts(contacts);
                case ADD_CONTACT -> addContact();
                case DELETE_CONTACT -> deleteContact();
                case SAVE_CONTACTS -> saveContacts();
                case EXIT -> System.out.println("\nДо свидания!");
                default -> System.err.printf("Неверный ввод [%s]%n", action);
            }
            if (action == EXIT) {
                break;
            }
        }
    }

    private void listContacts(List<Contact> contacts) {
        System.out.println("""
                                
                ---
                                
                Ф.И.О. | Номер телефона | Адрес электронной почты
                """);
        contacts.forEach(System.out::println);
    }

    private void addContact() {
        System.out.println("""
                                
                Введите контакт в формате:
                                
                Иванов Иван Иванович; +890999999; someEmail@example.example
                """);
        String line = input.nextLine();
        String[] parts = line.split(";");
        Contact contact = new Contact();
        contact.setFullName(parts[0].trim());
        contact.setPhoneNumber(parts[1].trim());
        contact.setEmail(parts[2].trim());
        contacts.add(contact);
        System.out.println("Контакт добавлен.");
    }

    private void deleteContact() {
        System.out.print("\nВведите email для удаления: ");
        String email = input.nextLine();
        boolean isDeleted = contacts.removeIf(contact -> email.equals(contact.getEmail()));
        System.out.println(isDeleted ? "Контакт удален." : "Контакт не найден.");
    }

    private void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFileLocation))) {
            for (Contact contact : contacts) {
                StringJoiner joiner = new StringJoiner(";");
                joiner
                        .add(contact.getFullName())
                        .add(contact.getPhoneNumber())
                        .add(contact.getEmail());
                writer.write(joiner.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nКонтакты сохранены в файл.");
    }

    private void displayMenu() {
        System.out.print("""
                1. Список контактов
                2. Добавить контакт
                3. Удалить контакт
                4. Сохранить контакты
                5. Выход
                """);
    }

    final class MenuAction {
        public static final int LIST_CONTACTS = 1;
        public static final int ADD_CONTACT = 2;
        public static final int DELETE_CONTACT = 3;
        public static final int SAVE_CONTACTS = 4;
        public static final int EXIT = 5;
    }
}