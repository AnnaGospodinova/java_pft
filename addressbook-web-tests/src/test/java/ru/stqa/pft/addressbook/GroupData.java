package ru.stqa.pft.addressbook;

public class GroupData {
    private final String header;
    private final String name;
    private final String footer;

    public GroupData(String header, String name, String footer) {
        this.header = header;
        this.name = name;
        this.footer = footer;
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
}
