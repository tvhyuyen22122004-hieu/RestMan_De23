package model;
public class Monan {
 private final int id; private final String ten,loai,khauphan,mota; private final long gia;
 public Monan(int id,String ten,String loai,long gia,String khauphan,String mota){this.id=id;this.ten=ten;this.loai=loai;this.gia=gia;this.khauphan=khauphan;this.mota=mota;}
 public int getId(){return id;} public String getTen(){return ten;} public String getLoai(){return loai;} public long getGia(){return gia;} public String getKhauphan(){return khauphan;} public String getMota(){return mota;}
}
