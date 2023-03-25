package com.example.assignment_androidnetworking.Model;

import java.io.Serializable;

public class Truyen_tranh implements Serializable {
    String tentruyen;
    String mota;
    String namxuatban;
    String tacgia;
    String anhbia;
    String id;

    public Truyen_tranh() {
    }

    public Truyen_tranh(String id,String tentruyen, String mota, String namxuatban, String tacgia, String anhbia) {
        this.id = id;
        this.tentruyen = tentruyen;
        this.mota = mota;
        this.namxuatban = namxuatban;
        this.tacgia = tacgia;
        this.anhbia = anhbia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getNamxuatban() {
        return namxuatban;
    }

    public void setNamxuatban(String namxuatban) {
        this.namxuatban = namxuatban;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getAnhbia() {
        return anhbia;
    }

    public void setAnhbia(String anhbia) {
        this.anhbia = anhbia;
    }


}
