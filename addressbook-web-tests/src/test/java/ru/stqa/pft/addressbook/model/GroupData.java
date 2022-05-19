package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class GroupData {
    private int id;
    private final String header;
    private final String name;
    private final String footer;

    public GroupData(String header, String name, String footer) {
        this.id = Integer.MAX_VALUE;
        this.header = header;
        this.name = name;
        this.footer = footer;
    }

    public GroupData(int id, String header, String name, String footer) {
        this.id = id;
        this.header = header;
        this.name = name;
        this.footer = footer;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getName() {
        return name;
    }

    public String getFooter() {
        return footer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
