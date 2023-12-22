import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 创建 MySQL 数据库连接
                try {
                    Connection connection = createMySQLConnection();
                    // 在这里可以使用数据库连接执行其他操作

                    // 关闭数据库连接
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // 处理数据库连接异常
                }

                try {
                    // 设置本机系统外观
                    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    UIManager.setLookAndFeel(lookAndFeel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new loginWindows().setVisible(true);
            }
        });
    }
//
    public static Connection createMySQLConnection() throws SQLException {
        String url = "jdbc:mysql://database.hetong-re4per.icu/chatgpt_account";
        String username = "chatgpt";
        String password = "zl221021@Chatgpt";

        // 加载 MySQL 驱动程序
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        // 创建数据库连接
        return DriverManager.getConnection(url, username, password);
    }

    public static String lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatGrayIJTheme"/*UIManager.getSystemLookAndFeelClassName()*/;
}
