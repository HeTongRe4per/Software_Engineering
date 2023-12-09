import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class createMySQLConnection {
    private Connection createMySQLConnection() throws SQLException {
        String url = "jdbc:mysql://database.hetong-re4per.icu/chatgpt_account";
        String username = "chatgpt";
        String password = "zl221021@Chatgpt";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // 处理找不到驱动程序的异常
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        // 创建并返回数据库连接
        return DriverManager.getConnection(url, username, password);
    }
}