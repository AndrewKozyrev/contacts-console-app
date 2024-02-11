
# Contacts Console App

Это консольное приложение устроено для обработки контактов через командную строку.
Оно позволяет пользователям выводить, добавлять, удалять и сохранять контакты через команды.


## Автор

- [@landsreyk](https://www.github.com/AndrewKozyrev)


## Установка

Склонируйте contacts-console-app с помощью Git

```cmd
  git clone https://github.com/AndrewKozyrev/contacts-console-app.git
  cd contacts-console-app
```
Для запуска через командную строку нужно выполнить команду

```cmd
  java -jar contacts.jar
```
## Команды и их описание

1. list: выводим список контактов.
2. add: добавляем новый контакт (считываем имя, телефон, email).
3. delete: удаляем контакт по email (считываем email).
4. save: сохраняем контакты в файл (используем application.yml для определения пути).
5. exit: завершаем работу приложения.
## Настройки

- Для изменения профиля нужно установить значение `spring.profiles.active`.
- Когда стоит профиль `init` при запуске приложения контакты считываются из файла, указанного в свойстве `app.contacts-initialization-file` настроек `application.yml`.
- Свойство настроек `app.contacts-save-file` хранит путь до файла, куда приложение сохраняет контакты.