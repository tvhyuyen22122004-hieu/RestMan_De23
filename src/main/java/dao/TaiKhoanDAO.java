package dao;
import model.TaiKhoan;import java.sql.*;
public class TaiKhoanDAO {
 public TaiKhoan dangNhap(String username,String password){if(username==null||password==null)return null;
 String sql="SELECT username,full_name,role FROM account WHERE username=? AND password_hash=SHA2(?,256) AND active=1";
 try(Connection c=Database.connect();PreparedStatement s=c.prepareStatement(sql)){s.setString(1,username.trim());s.setString(2,password);try(ResultSet r=s.executeQuery()){return r.next()?new TaiKhoan(r.getString(1),r.getString(2),r.getString(3)):null;}}catch(SQLException e){throw new IllegalStateException("Lỗi kết nối MySQL khi đăng nhập",e);}}
}
