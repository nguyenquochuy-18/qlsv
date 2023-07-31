package com.demo.qlsinhvien.model;

public class MonHoc {
    private String maMh;
    private String tenMH;
    private String soTc;
    private String soTh;

    public MonHoc(String tenMH, String soTc, String soTh) {
        this.tenMH = tenMH;
        this.soTc = soTc;
        this.soTh = soTh;
    }

    public MonHoc(String maMh, String tenMH, String soTc, String soTh) {
        this.maMh = maMh;
        this.tenMH = tenMH;
        this.soTc = soTc;
        this.soTh = soTh;
    }

    public String getMaMh() {
        return maMh;
    }

    public void setMaMh(String maMh) {
        this.maMh = maMh;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getSoTc() {
        return soTc;
    }

    public void setSoTc(String soTc) {
        this.soTc = soTc;
    }

    public String getSoTh() {
        return soTh;
    }

    public void setSoTh(String soTh) {
        this.soTh = soTh;
    }
}
