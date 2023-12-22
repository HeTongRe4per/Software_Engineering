import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

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
                if(checkFileExistence(FILE_PATH)){
                    try {
                        readfile();
                        // 设置本机系统外观
                        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        UIManager.setLookAndFeel(lookAndFeel);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        // 设置本机系统外观
                        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        UIManager.setLookAndFeel(lookAndFeel);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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


    private static void readfile(){
        File file = new File(FILE_PATH);
        try {
            // 创建Scanner对象读取文件
            Scanner scanner = new Scanner(file);
            // 使用StringBuilder拼接读取到的数据
            StringBuilder stringBuilder = new StringBuilder();
            // 读取文件内容
            while (scanner.hasNext()) {
                String data = scanner.next();
                // 将字段添加到StringBuilder中
                stringBuilder.append(data);
            }
            // 将StringBuilder的内容赋值给文本框
            lookAndFeel = stringBuilder.toString();
            // 关闭Scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*private static boolean readBool() {
        boolean value = false;
        File file = new File(FILE_PATH);
        try {
            // 创建Scanner对象读取文件
            Scanner scanner = new Scanner(file);
            // 使用StringBuilder拼接读取到的数据
            StringBuilder stringBuilder = new StringBuilder();
            // 读取文件内容
            while (scanner.hasNext()) {
                String data = scanner.next();
                // 将字段添加到StringBuilder中
                stringBuilder.append(data);
            }
            // 将StringBuilder的内容赋值给文本框
            String Text = stringBuilder.toString();
            value = Boolean.parseBoolean(Text);
            // 关闭Scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }*/

    public static boolean checkFileExistence(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static String lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatGrayIJTheme"/*UIManager.getSystemLookAndFeelClassName()*/;
    static String localAppDATA=System.getenv("LOCALAPPDATA");
    static String FILE_PATH = localAppDATA+"\\CIF\\themeFile";

}
