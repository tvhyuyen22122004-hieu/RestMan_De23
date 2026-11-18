
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {

    private Database() {}

    // Cau hinh MySQL
    private static final String URL =
        "jdbc:mysql://localhost:3306/restman_de23"
        + "?useUnicode=true"
        + "&characterEncoding=UTF-8"
        + "&connectionTimeZone=LOCAL"
        + "&useSSL=false"
        + "&allowPublicKeyRetrieval=true";

    private static final String USER = "root";

    // Thay bang mat khau MySQL cua ban
    private static final String PASSWORD = "123456";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                "Khong tim thay MySQL JDBC Driver", e
            );
        }

        return DriverManager.getConnection(
            URL, USER, PASSWORD
        );
    }
}
