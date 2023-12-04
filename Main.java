package org.example;

import java.sql.*;
import java.util.Scanner;
public class Main {
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
                System.out.println("Введите название новой группы:");
                String groupName = scanner.nextLine();
                System.out.println("Введите номер новой группы:");
                int groupNumber = scanner.nextInt();
                scanner.nextLine();

                Group group = new Group(groupName, groupNumber);
                database.addGroup(group);

                try {
                    String sql = "INSERT INTO groups (name, number) VALUES (?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, groupName);
                    statement.setInt(2, groupNumber);
                    statement.executeUpdate();
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Ошибка при добавлении группы в базу данных: " + e.getMessage());
                }

                System.out.println("Группа успешно создана!");
            } else if (choice == 2) {
                System.out.println("Введите название группы, которую нужно удалить:");
                String groupName = scanner.nextLine();
                database.removeGroup(groupName);

                try {
                    String sql = "DELETE FROM groups WHERE name = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, groupName);
                    statement.executeUpdate();
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Ошибка при удалении группы из базы данных: " + e.getMessage());
                }

                System.out.println("Группа успешно удалена!");
            } else if (choice == 3) {
                System.out.println("Введите название группы, в которую нужно добавить ребенка:");
                String groupName = scanner.nextLine();
                System.out.println("Введите имя ребенка:");
                String childName = scanner.nextLine();
                System.out.println("Введите пол ребенка:");
                String childGender = scanner.nextLine();
                System.out.println("Введите возраст ребенка:");
                int childAge = scanner.nextInt();
                scanner.nextLine();

                Child child = new Child(childName, childGender, childAge);
                database.addChildToGroup(groupName, child);

                try {
                    String sql = "INSERT INTO children (group_name, name, gender, age) VALUES (?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, groupName);
                    statement.setString(2, childName);
                    statement.setString(3, childGender);
                    statement.setInt(4, childAge);
                    statement.executeUpdate();
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Ошибка при добавлении ребенка в группу в базу данных: " + e.getMessage());
                }

                System.out.println("Ребенок успешно добавлен в группу!");
            } else if (choice == 4) {
                System.out.println("Введите название группы, из которой нужно удалить ребенка:");
                String groupName = scanner.nextLine();
                System.out.println("Введите имя ребенка, которого нужно удалить:");
                String childName = scanner.nextLine();
                Child child = new Child(childName, "", 0);
                database.removeChildFromGroup(groupName, child);

                try {
                    String sql = "DELETE FROM children WHERE group_name = ? AND name = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, groupName);
                    statement.setString(2, childName);
                    statement.executeUpdate();
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Ошибка при удалении ребенка из группы в базе данных: " + e.getMessage());
                }

                System.out.println("Ребенок успешно удален из группы!");
            } else if (choice == 5) {
                System.out.println("Введите название группы, которую нужно переименовать:");
                String groupName = scanner.nextLine();
                System.out.println("Введите новое название группы:");
                String newGroupName = scanner.nextLine();
                database.editGroupName(groupName, newGroupName);

                try {
                    String sql = "UPDATE groups SET name = ? WHERE name = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, newGroupName);
                    statement.setString(2, groupName);
                    statement.executeUpdate();
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Ошибка при изменении названия группы в базе данных: " + e.getMessage());
                }

                System.out.println("Название группы успешно изменено!");
            } else if (choice == 6) {
                System.out.println("Введите название группы, в которой нужно изменить информацию о ребенке:");
                String groupName = scanner.nextLine();
                System.out.println("Введите имя ребенка, информацию о котором нужно изменить:");
                String childName = scanner.nextLine();
                System.out.println("Введите новое имя ребенка:");
                String newChildName = scanner.nextLine();
                System.out.println("Введите новый пол ребенка:");
                String newChildGender = scanner.nextLine();
                System.out.println("Введите новый возраст ребенка:");
                int newChildAge = scanner.nextInt();
                scanner.nextLine();

                database.editChild(groupName, childName, newChildName, newChildGender, newChildAge);

                try {
                    String sql = "UPDATE children SET name = ?, gender = ?, age = ? WHERE group_name = ? AND name = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, newChildName);
                    statement.setString(2, newChildGender);
                    statement.setInt(3, newChildAge);
                    statement.setString(4, groupName);
                    statement.setString(5, childName);
                    statement.executeUpdate();
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Ошибка при изменении информации о ребенке в базе данных: " + e.getMessage());
                }

                System.out.println("Информация о ребенке успешно изменена!");
            } else if (choice == 7) {
                System.out.println("Введите название группы, о которой нужно получить информацию:");
                String groupName = scanner.nextLine();
                database.getGroupInfo(groupName);

                try {
                    String sql = "SELECT * FROM groups WHERE name = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, groupName);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        System.out.println("Название группы: " + resultSet.getString("name") + ", номер группы: " + resultSet.getInt("number"));
                    } else {
                        System.out.println("Группа не найдена.");
                    }
                    resultSet.close();
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Ошибка при получении информации о группе из базы данных: " + e.getMessage());
                }
            } else if (choice == 8) {
                System.out.println("Работа программы завершена.");
                break;
            } else {
                System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }
        ConnectionManager.closeConnection();
    }
}
