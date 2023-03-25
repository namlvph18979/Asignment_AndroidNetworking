package com.example.assignment_androidnetworking.Model;

import java.io.Serializable;

public class Binh_luan implements Serializable {

    String id_truyen;
    String id_nguoidung;
    String noidung;
    String thoigian;


    public Binh_luan() {
    }

    public Binh_luan(String id_truyen, String id_nguoidung, String noidung, String thoigian) {
        this.id_truyen = id_truyen;
        this.id_nguoidung = id_nguoidung;
        this.noidung = noidung;
        this.thoigian = thoigian;
    }

    public String getId_truyen() {
        return id_truyen;
    }

    public void setId_truyen(String id_truyen) {
        this.id_truyen = id_truyen;
    }

    public String getId_nguoidung() {
        return id_nguoidung;
    }

    public void setId_nguoidung(String id_nguoidung) {
        this.id_nguoidung = id_nguoidung;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }
}
