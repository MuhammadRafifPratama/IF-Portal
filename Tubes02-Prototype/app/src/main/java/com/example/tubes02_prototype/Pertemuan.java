package com.example.tubes02_prototype;


public class Pertemuan {
    private String id;
    private String title;
    private String user;
    private String desc;
    private String start_date;
    private String end_date;

    public Pertemuan(String title, String user, String desc, String waktuPertemuan, String deskripsi){
        this.title = title;
        this.user = user;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

}
