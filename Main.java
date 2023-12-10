package org.example;

import java.sql.*;
import java.util.Scanner;
public class Main {

    public static void exitApp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вы действительно хотите выйти?");
        System.out.println("1. Да");
        System.out.println("2. Нет");
        int choice = scanner.nextInt();
        if (choice == 1) {
            ConnectionManager.closeConnection();
            System.out.println("Работа программы завершена.");
            System.exit(0);
        } else {
            System.out.println("Вы были возвращены в меню.");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        Connection connection = ConnectionManager.getConnection();
        System.out.println("База данных детского сада");

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Создать группу");
            System.out.println("2. Удалить группу");
            System.out.println("3. Добавить ребенка в группу");
            System.out.println("4. Удалить ребенка из группы");
            System.out.println("5. Изменить название группы");
            System.out.println("6. Изменить информацию о ребенке");
            System.out.println("7. Получить информацию о группе");
            System.out.println("8. Выйти из программы");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                Commands.addGroupApp(database, connection);
            } else if (choice == 2) {
                Commands.deleteGroupApp(database, connection);
            } else if (choice == 3) {
                Commands.addChildInGroupApp(database, connection);
            } else if (choice == 4) {
                Commands.deleteChildFromGroupApp(database, connection);
            } else if (choice == 5) {
                Commands.changeGroupNameApp(database, connection);
            } else if (choice == 6) {
                Commands.changeChildInfoApp(database, connection);
            } else if (choice == 7) {
                Commands.getGroupInfoApp(database, connection);
            } else if (choice == 8) {
                exitApp();
            }
        }
    }
}
