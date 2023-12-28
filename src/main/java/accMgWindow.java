import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.sql.*;
import java.util.Scanner;


/*
 * Created by JFormDesigner on Thu Nov 30 09:10:35 CST 2023
 */



/**
 * @author zhang RDLS 小样
 */
public class accMgWindow extends JFrame {
    public accMgWindow() {
        initComponents();
        if(loginWindows.checkFileExistence(FILE_PATH)){
            try {
                default_input();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void DecFile(File encFile, File decFile) throws Exception {
        if (!encFile.exists()) {
            return;
        }

        if (!decFile.exists()) {
            decFile.createNewFile();
        }

        InputStream fis = new FileInputStream(encFile);
        OutputStream fos = new FileOutputStream(decFile);

        // 文件字节内容
        int dataOfFile;
        while ((dataOfFile = fis.read()) > -1) {
            // 加密解密秘钥
            int numOfEncAndDec = 0x99;
            fos.write(dataOfFile ^ numOfEncAndDec);
        }

        fis.close();
        fos.flush();
        fos.close();
    }

    private void default_input() throws IOException {
        File file = new File(FILE_PATH);
        File L_file = new File(FILE_PATH+"-L");
        L_file.getParentFile().mkdirs(); // 创建父文件夹（如果不存在）
        L_file.createNewFile(); // 创建文件（如果不存在）
        try {
            DecFile(file,L_file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        readfile(L_file);
        L_file.delete();
    }

    private void readfile(File file) {

        try {
            Scanner scanner = new Scanner(file);
            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNext()) {
                String data = scanner.next();
                stringBuilder.append(data);
            }

            String usernameOrEmail = stringBuilder.toString();
            String[] parts = usernameOrEmail.split(",");

            String query;
            String columnToUpdate;
            String otherColumn;

            // 判断是根据 username_mail 还是 email 查询
            if (!isUsernameMail(parts[0])) {
                query = "SELECT * FROM user WHERE username_mail = ?";
                columnToUpdate = "username_mail";
                otherColumn = "email";
            } else {
                query = "SELECT * FROM user WHERE email = ?";
                columnToUpdate = "email";
                otherColumn = "username_mail";
            }

            // 使用数据库连接函数创建连接
            Connection connection = createMySQLConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, parts[0]);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // 从结果集中获取另一列的值
                        String otherColumnValue = resultSet.getString(otherColumn);

                        // 更新 UI
                        if (columnToUpdate.equals("username_mail")) {
                            accountTextField.setText(parts[0]);
                            emailTextField.setText(otherColumnValue);
                            oldUsername = parts[0];
                            oldEmail = otherColumnValue;
                        } else {
                            accountTextField.setText(otherColumnValue);
                            emailTextField.setText(parts[0]);
                            oldEmail = parts[0];
                            oldUsername = otherColumnValue;
                        }
                        passwordField1.setEchoChar('*');
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean isUsernameMail(String input) {
        // 根据需要实现判断是 username_mail 还是 email 的逻辑
        // 这里简单地通过包含 "@" 符号来判断是否是 email
        return input.contains("@");
    }

    private Connection createMySQLConnection() {
        Connection connection = null;
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void delAccountListen() {
        ChatInterface.accMgWin.setEnabled(false);
        confirmDelAccountWindow confirmDialog = new confirmDelAccountWindow();
        confirmDialog.setVisible(true);
    }

    private void resetUserNameListen() {
        String newusername = accountTextField.getText();
        //连接数据库并执行更新操作
        updateUsernameInDatabase(oldUsername, newusername);
    }

    private void resetEmailListen() {
        String newemail = emailTextField.getText();
        // 连接数据库并执行更新操作
        updateEmailInDatabase(oldEmail, newemail);
    }

    private void resetPasswdListen() {
        //new passwdUpdateWindow2(oldusername).setVisible(true);
        ChatInterface.accMgWin.setEnabled(false);
        passwdUpdateWin = new passwdUpdateWindow2(oldUsername);
        passwdUpdateWin.setVisible(true);
    }

    // 更新数据库中的username_mail属性值
    private void updateUsernameInDatabase(String oldusername, String newusername) {
        // 判断 newusername 是否与 oldusername 相同
        if (newusername.equals(oldusername)) {
            JOptionPane.showMessageDialog(this, "与原用户名相同！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 判断在数据库 user 表中的 username_mail 属性中是否存在与 newusername 相同的属性值
        if (isUsernameAlreadyExist(newusername)) {
            JOptionPane.showMessageDialog(this, "已存在该用户名！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 判断 newusername 是否满足复杂性要求的正则表达式
        if (!isValidUsername(newusername)) {
            JOptionPane.showMessageDialog(this, "用户名至少包含数字、（大、小）英文字母，长度为6~12！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection connection = null;
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt");

            // 准备更新语句
            String updateQuery = "UPDATE user SET username_mail = ? WHERE username_mail = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // 设置更新参数
                preparedStatement.setString(1,newusername);
                preparedStatement.setString(2,oldusername);

                // 执行更新
                preparedStatement.executeUpdate();
            }

            // 关闭当前窗口和父窗口
            dispose();
            loginWindows.mainWin.dispose();

            // 打开新的登录窗口
            Main.loginWin = new loginWindows();
            Main.loginWin.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            // 处理数据库连接异常
        } finally {
            try {
                // 关闭数据库连接
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 检查在数据库 user 表中的 username_mail 属性中是否存在与 username 相同的属性值
    private boolean isUsernameAlreadyExist(String username) {
        boolean exists = false;
        Connection connection = null;
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt");

            // 检查是否存在相同的 username
            String queryUsername = "SELECT * FROM user WHERE username_mail = ?";
            try (PreparedStatement preparedStatementUsername = connection.prepareStatement(queryUsername)) {
                preparedStatementUsername.setString(1, username);
                try (ResultSet resultSetUsername = preparedStatementUsername.executeQuery()) {
                    if (resultSetUsername.next()) {
                        exists = true;
                        return exists;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭数据库连接
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }

    // 检查 username 是否满足复杂性要求的正则表达式
    private boolean isValidUsername(String username) {
        // 此处添加检查 username 复杂性的逻辑，例如：使用正则表达式判断 username 格式
        // 返回 true 表示 username 符合要求，返回 false 表示不符合要求
        // 示例逻辑：
        return username.matches("^[a-zA-Z0-9_]{4,16}$");
    }

    // 更新数据库中的email属性值
    private void updateEmailInDatabase(String oldemail, String newemail) {
        // 判断newemail是否与oldemail相同
        if (newemail.equals(oldemail)) {
            JOptionPane.showMessageDialog(this, "与原邮箱相同！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 判断在数据库user表中的email属性中是否存在与newemail相同的属性值
        if (isEmailAlreadyExist(newemail)) {
            JOptionPane.showMessageDialog(this, "已存在该邮箱！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 判断newemail是否满足复杂性要求的正则表达式
        if (!isValidEmail(newemail)) {
            JOptionPane.showMessageDialog(this, "邮箱至少包含数字、（大、小）英文字母，长度为6~12！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection connection = null;
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt");

            // 准备更新语句
            String updateQuery = "UPDATE user SET email = ? WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // 设置更新参数
                preparedStatement.setString(1, newemail);
                preparedStatement.setString(2, oldemail);

                // 执行更新
                preparedStatement.executeUpdate();
            }

            // 关闭当前窗口和父窗口
            dispose();
            loginWindows.mainWin.dispose();

            // 打开新的登录窗口
            Main.loginWin = new loginWindows();
            Main.loginWin.setVisible(true);

        } catch (SQLException e) { e.printStackTrace(); }
        finally {
            try {
                // 关闭数据库连接
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    // 检查在数据库user表中的email属性中是否存在与email相同的属性值
    private boolean isEmailAlreadyExist(String email) {
        boolean exists = false;
        Connection connection = null;
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt");

            // 检查是否存在相同的email
            String queryEmail = "SELECT * FROM user WHERE email = ?";
            try (PreparedStatement preparedStatementEmail = connection.prepareStatement(queryEmail)) {
                preparedStatementEmail.setString(1, email);
                try (ResultSet resultSetEmail = preparedStatementEmail.executeQuery()) {
                    if (resultSetEmail.next()) {
                        exists = true;
                        return exists;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭数据库连接
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }

    // 检查email是否满足复杂性要求的正则表达式
    private boolean isValidEmail(String email) {
        // 此处添加检查email复杂性的逻辑，例如：使用正则表达式判断email格式
        // 返回 true 表示email符合要求，返回 false 表示不符合要求
        // 示例逻辑：
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    static void delefile(){
        String fp=localAppDATA+"\\Wise_Conversations";
        File directory = new File(fp);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        file.delete();
                    }
                }
            }
        }
    }

    private void thisWindowClosing() {
        loginWindows.mainWin.setEnabled(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        delAccountButton = new JButton();
        accountTextField = new JTextField();
        emailTextField = new JTextField();
        resetUserNameButton = new JButton();
        resetEmailButton = new JButton();
        resetPasswdButton = new JButton();
        passwordField1 = new JPasswordField();

        //======== this ========
        setTitle("\u8d26\u53f7\u7ba1\u7406");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/icon-chatgpt.png")).getImage());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing();
            }
        });
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d\uff1a");

        //---- label2 ----
        label2.setText("\u90ae\u7bb1\uff1a");

        //---- label3 ----
        label3.setText("\u5bc6\u7801\uff1a");

        //---- delAccountButton ----
        delAccountButton.setText("\u5220\u9664\u8d26\u6237");
        delAccountButton.addActionListener(e -> delAccountListen());

        //---- resetUserNameButton ----
        resetUserNameButton.setText("\u4fee\u6539\u7528\u6237\u540d");
        resetUserNameButton.addActionListener(e -> resetUserNameListen());

        //---- resetEmailButton ----
        resetEmailButton.setText("\u4fee\u6539\u90ae\u7bb1");
        resetEmailButton.addActionListener(e -> resetEmailListen());

        //---- resetPasswdButton ----
        resetPasswdButton.setText("\u4fee\u6539\u5bc6\u7801");
        resetPasswdButton.addActionListener(e -> resetPasswdListen());

        //---- passwordField1 ----
        passwordField1.setText("******");
        passwordField1.setEchoChar('*');
        passwordField1.setEditable(false);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addComponent(delAccountButton, GroupLayout.Alignment.TRAILING)
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(passwordField1, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(emailTextField, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                    .addComponent(resetEmailButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(resetPasswdButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(accountTextField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(resetUserNameButton, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                    .addContainerGap(32, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(accountTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(resetUserNameButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(resetEmailButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(resetPasswdButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                    .addComponent(delAccountButton)
                    .addGap(27, 27, 27))
        );
        setSize(395, 275);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JButton delAccountButton;
    private JTextField accountTextField;
    private JTextField emailTextField;
    private JButton resetUserNameButton;
    private JButton resetEmailButton;
    public static JButton resetPasswdButton;
    private JPasswordField passwordField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    // 自定义变量

    static passwdUpdateWindow2 passwdUpdateWin;
    private static final String localAppDATA=System.getenv("LOCALAPPDATA");
    private final String FILE_PATH = localAppDATA+"\\Wise_Conversations\\credentials";
    static String oldUsername;
    String oldEmail;
}
