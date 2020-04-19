package phamthanhhuong.com.model;

public class NhanVien {
    private String ma;
    private String ten;
    private  boolean laNu;

    public NhanVien() {
    }

    public NhanVien(String ma, String ten, boolean laNu) {
        this.ma = ma;
        this.ten = ten;
        this.laNu = laNu;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setLaNu(boolean laNu) {
        this.laNu = laNu;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public boolean isLaNu() {
        return laNu;
    }

    public void setMa(String toString) {
    }
}
