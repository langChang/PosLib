package com.nhsoft.poslib.entity;

public class ReadPrint {

    private String name;
    private int line;
    private String print_name;
    private boolean is_center;
    private boolean is_same_line;

    public String getPrint_name() {
        return print_name;
    }

    public void setPrint_name(String print_name) {
        this.print_name = print_name;
    }

    public boolean isIs_center() {
        return is_center;
    }

    public void setIs_center(boolean is_center) {
        this.is_center = is_center;
    }

    public boolean isIs_same_line() {
        return is_same_line;
    }

    public void setIs_same_line(boolean is_same_line) {
        this.is_same_line = is_same_line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
