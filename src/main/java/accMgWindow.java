import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/*
 * Created by JFormDesigner on Thu Nov 30 09:10:35 CST 2023
 */



/**
 * @author zhang
 */
public class accMgWindow extends JFrame {

    private ChatInterface parent;
    public accMgWindow(ChatInterface parent) {
        initComponents();
        this.parent=parent;
        if(loginWindows.checkFileExistence(FILE_PATH)){
            try {
                default_input();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private final String localAppDATA=System.getenv("LOCALAPPDATA");
    private final String FILE_PATH = localAppDATA+"\\CIF\\credentials";
    private int dataOfFile = 0; // 文件字节内容
    private void DecFile(File encFile, File decFile) throws Exception {
        if (!encFile.exists()) {
            return;
        }

        if (!decFile.exists()) {
            decFile.createNewFile();
        }

        InputStream fis = new FileInputStream(encFile);
        OutputStream fos = new FileOutputStream(decFile);

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
                        // 从结果集中获取其他列的值
                        String otherColumnValue = resultSet.getString(otherColumn);

                        // 更新 UI
                        if (columnToUpdate.equals("username_mail")) {
                            accountTextField.setText(parts[0]);
                            emailTextField.setText(otherColumnValue);
                        } else {
                            accountTextField.setText(otherColumnValue);
                            emailTextField.setText(parts[0]);
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
        //
        new confirmDelAccountWindow(this).setVisible(true);

        delefile();
        parent.dispose();
        dispose();
        new loginWindows().setVisible(true);
    }

    private void resetUserNameListen() {
        String username=accountTextField.getText();
        String email=emailTextField.getText();
        //连接数据库并执行更新操作
        updateUsernameInDatabase(email, username);
        // 删除文件
        delefile();
        // 关闭当前窗口和父窗口
        parent.dispose();
        dispose();
        // 打开新的登录窗口
        new loginWindows().setVisible(true);
    }

    private void resetEmailListen() {
        String username=accountTextField.getText();
        String email=emailTextField.getText();
        // 连接数据库并执行更新操作
        updateEmailInDatabase(username, email);

        delefile();
        parent.dispose();
        dispose();
        new loginWindows().setVisible(true);
    }

    private void resetPasswdListen() {
        //todo 账号邮箱
        new passwdUpdateWindow2("123").setVisible(true);

        delefile();
        parent.dispose();
        dispose();
        new loginWindows().setVisible(true);
    }

    // 更新数据库中的username_mail属性值
    private void updateUsernameInDatabase(String newusername, String email) {
        Connection connection = null;
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt");

            // 准备更新语句
            String updateQuery = "UPDATE user SET username_mail = ? WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // 设置更新参数
                preparedStatement.setString(1, newusername);
                preparedStatement.setString(2, email);

                // 执行更新
                preparedStatement.executeUpdate();
            }
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

    // 更新数据库中的email属性值
    private void updateEmailInDatabase(String newemail, String username_mail) {
        Connection connection = null;
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt");

            // 准备更新语句
            String updateQuery = "UPDATE user SET email = ? WHERE username_mail = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // 设置更新参数
                preparedStatement.setString(1, newemail);
                preparedStatement.setString(2, username_mail);

                // 执行更新
                preparedStatement.executeUpdate();
            }
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

    private void delefile(){
        String fp=localAppDATA+"\\CIF";
        File directory = new File(fp);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
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
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(accountTextField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resetUserNameButton))
                            .addComponent(delAccountButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addComponent(passwordField1)
                                .addGap(10, 10, 10)
                                .addComponent(resetPasswdButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(resetEmailButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(resetPasswdButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                    .addComponent(delAccountButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
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
    private JButton resetPasswdButton;
    private JPasswordField passwordField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
