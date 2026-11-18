package dao;
import model.Monan;import java.sql.*;import java.util.*;
public class MonanDAO {
 private Monan read(ResultSet r)throws SQLException{return new Monan(r.getInt("id"),r.getString("name"),r.getString("category"),r.getLong("price"),r.getString("serving"),r.getString("description"));}
 public List<Monan> timTheoTen(String tuKhoa){List<Monan> ds=new ArrayList<>();String sql="SELECT id,name,category,price,serving,description FROM food WHERE name LIKE ? ESCAPE '!' ORDER BY name";
 try(Connection c=Database.connect();PreparedStatement s=c.prepareStatement(sql)){s.setString(1,"%"+(tuKhoa==null?"":tuKhoa.trim().replace("!","!!").replace("%","!%").replace("_","!_"))+"%");try(ResultSet r=s.executeQuery()){while(r.next())ds.add(read(r));}}catch(SQLException e){throw new IllegalStateException("Lỗi truy vấn món ăn từ MySQL",e);}return ds;}
 public Monan timTheoId(int id){try(Connection c=Database.connect();PreparedStatement s=c.prepareStatement("SELECT id,name,category,price,serving,description FROM food WHERE id=?")){s.setInt(1,id);try(ResultSet r=s.executeQuery()){return r.next()?read(r):null;}}catch(SQLException e){throw new IllegalStateException("Lỗi truy vấn chi tiết món ăn",e);}}
}
