package com.demo.qlsinhvien.model;

public class SinhVien {
    private String ma;
    private String ten;
    private String gioiTinh;
    private String sdt;
    private String tenmh;
    byte[] hinhanh;

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    public SinhVien(String ten, String gioiTinh, String sdt, String tenmh, byte[] hinhanh) {
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.tenmh = tenmh;
        this.hinhanh = hinhanh;

    }

    public SinhVien(String ma, String ten, String gioiTinh, String sdt, String diaChi, byte[] hinhanh) {
        this.ma = ma;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.tenmh = diaChi;
        this.hinhanh = hinhanh;

    }








    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenmh() {
        return tenmh;
    }

    public void setTenmh(String tenmh) {
        this.tenmh = tenmh;
    }
}

