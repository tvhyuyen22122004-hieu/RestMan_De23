package model;
public class ThongKeMonAn {
 private final Monan mon;private final int soLanGoi,soLuong;private final long doanhThu;
 public ThongKeMonAn(Monan mon,int soLanGoi,int soLuong,long doanhThu){this.mon=mon;this.soLanGoi=soLanGoi;this.soLuong=soLuong;this.doanhThu=doanhThu;}
 public Monan getMon(){return mon;}public int getSoLanGoi(){return soLanGoi;}public int getSoLuong(){return soLuong;}public long getDoanhThu(){return doanhThu;}
}
