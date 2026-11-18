package model;
public class ChiTietHoaDon {
 private final int hoaDonId,monAnId,soLuong;private final long donGia;
 public ChiTietHoaDon(int hoaDonId,int monAnId,int soLuong,long donGia){this.hoaDonId=hoaDonId;this.monAnId=monAnId;this.soLuong=soLuong;this.donGia=donGia;}
 public int getHoaDonId(){return hoaDonId;}public int getMonAnId(){return monAnId;}public int getSoLuong(){return soLuong;}public long getDonGia(){return donGia;}public long getThanhTien(){return soLuong*donGia;}
}
