package model;
import java.time.LocalDateTime;
public class HoaDon {
 private final int id;private final String khachHang,ban;private final LocalDateTime ngayLap;
 public HoaDon(int id,String khachHang,String ban,LocalDateTime ngayLap){this.id=id;this.khachHang=khachHang;this.ban=ban;this.ngayLap=ngayLap;}
 public int getId(){return id;}public String getKhachHang(){return khachHang;}public String getBan(){return ban;}public LocalDateTime getNgayLap(){return ngayLap;}
}
