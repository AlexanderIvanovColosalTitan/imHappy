package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Database {
    private Map<String, Group> groups;

    public Database() {
        this.groups = new HashMap<>();
    }

    public void addGroup(Group group) {
        groups.put(group.getName(), group);
    }

    public void removeGroup(String groupName) {
        groups.remove(groupName);
    }

    public void addChildToGroup(String groupName, Child child) {
        Group group = groups.get(groupName);
        if (group != null) {
            group.addChild(child);
        }
    }

    public void removeChildFromGroup(String groupName, Child child) {
        Group group = groups.get(groupName);
        if (group != null) {
            group.removeChild(child);
        }
    }

    public void editGroupName(String groupName, String newGroupName) {
        Group group = groups.get(groupName);
        if (group != null) {
            group.setName(newGroupName);
            groups.remove(groupName);
            groups.put(newGroupName, group);
        }
    }

    public void editChild(String groupName, String childName, String newChildName, String newChildGender, int newChildAge) {
        Group group = groups.get(groupName);
        if (group != null) {
            List<Child> children = group.getChildren();
            for (Child child : children) {
                if (child.getName().equals(childName)) {
                    child.setName(newChildName);
                    child.setGender(newChildGender);
                    child.setAge(newChildAge);
                    break;
                }
            }
        }
    }

    public void getGroupInfo(String groupName) {
        Group group = groups.get(groupName);
        if (group != null) {
            System.out.print("Название группы: " + group.getName() + ", ");
            System.out.println("номер группы: " + group.getNumber());
            System.out.println("Дети в группе:");
            List<Child> children = group.getChildren();
            for (Child child : children) {
                System.out.print("Имя ребёнка: " + child.getName() + "; ");
                System.out.print("Пол ребёнка: " + child.getGender() + "; ");
                System.out.println("Лет ребёнку: " + child.getAge());
            }
            System.out.println("");
        }
    }
}
