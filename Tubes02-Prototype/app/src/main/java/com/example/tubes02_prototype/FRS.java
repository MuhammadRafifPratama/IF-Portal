package com.example.tubes02_prototype;

public class FRS {
    private String code;
    private String nama_mata_kuliah;
    private String semester;

    public FRS(String code, String namaMatkul, String semester) {
        this.code = code;
        this.nama_mata_kuliah = namaMatkul;
        this.semester = semester;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNama_mata_kuliah(String nama_mata_kuliah) {
        this.nama_mata_kuliah = nama_mata_kuliah;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCode() {
        return code;
    }

    public String getNama_mata_kuliah() {
        return nama_mata_kuliah;
    }

    public String getSemester() {
        return semester;
    }
}
