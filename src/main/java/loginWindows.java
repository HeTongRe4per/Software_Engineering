import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/*
 * Created by JFormDesigner on Thu Nov 30 13:18:45 CST 2023
 */



/**
 * @author zhang
 */
public class loginWindows extends JFrame{

    public loginWindows() {
        initComponents();
        LoginWindowInit();
    }

    public void LoginWindowInit() {
        // 账号，密码框默认显示
        passwordField1.setEchoChar((char) 0);
        accountField.setText(accountInit);
        passwordField1.setText(passwordInit);
        accountField.setForeground(Color.GRAY);
        passwordField1.setForeground(Color.GRAY);
    }

    private void accountFieldFocusGained() {
        // 账号输入框被聚焦
        String temp = accountField.getText();
        if (temp.equals(accountInit)) {
            accountField.setText("");
            accountField.setForeground(Color.BLACK);
        }
    }

    private void accountFieldFocusLost() {
        // 账号输入框失去聚焦
        String temp = accountField.getText();
        if (temp.isEmpty()) {
            accountField.setText(accountInit);
            accountField.setForeground(Color.GRAY);
        }
    }

    private void passwordField1FocusGained() {
        // 密码框被聚焦
        String temp = String.valueOf(passwordField1.getPassword());
        if (temp.equals(passwordInit)) {
            passwordField1.setText("");
            passwordField1.setForeground(Color.BLACK);
            passwordField1.setEchoChar('*');
        }
    }

    private void passwordField1FocusLost() {
        // 密码框失去聚焦
        String temp = String.valueOf(passwordField1.getPassword());
        if (temp.isEmpty()) {
            passwordField1.setText(passwordInit);
            passwordField1.setForeground(Color.GRAY);
            passwordField1.setEchoChar((char) 0);
        }
    }

    private void loginButtonLinster() {
        //
        if(logverifyinfor()) {
            this.setVisible(false);
            this.dispose();
            new ChatInterface().setVisible(true);
        }else {
            JOptionPane.showMessageDialog(null, "账号或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void registerButtonlinsten() {
        //
        new registeredWindow().setVisible(true);
    }

    private void forgotPasswdButtonLinsten() {
        //
        new forgotPasswordWindow().setVisible(true);
    }

    private boolean logverifyinfor(){
        //数据库验证
        boolean flag = false;
        String  username_mail,password;
        username_mail=accountField.getText();
        password= new String(passwordField1.getPassword());
        // TODO 连接数据库验证信息
        // 连接数据库验证信息
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // 对密码进行 SHA-256 哈希
            String hashedPassword = hashPasswordSHA256(password);
            String query = "SELECT * FROM user WHERE username_mail = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username_mail);
                preparedStatement.setString(2, hashedPassword);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // 如果结果集不为空，则验证成功
                    flag = resultSet.next();
                }
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

        // 查询数据库返回flag
        return flag;
    }

    // 使用 SHA-256 对密码进行哈希
    private String hashPasswordSHA256(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(password.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                stringBuilder.append(Integer.toString((hashedByte & 0xff) + 0x100, 16).substring(1));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        accountField = new JTextField();
        loginButton = new JButton();
        registerButton = new JButton();
        remberPasswd = new JCheckBox();
        forgotPasswdButton = new JButton();
        passwordField1 = new JPasswordField();

        //======== this ========
        setResizable(false);
        setTitle("\u767b\u5f55");
        setIconImage(new ImageIcon(getClass().getResource("/icon-chatgpt.png")).getImage());
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/ChatGPT_Logo_PNG.png")));

        //---- label2 ----
        label2.setText("\u7528\u6237\u540d\u6216\u90ae\u7bb1\uff1a");

        //---- label3 ----
        label3.setText("\u5bc6\u7801\uff1a");

        //---- accountField ----
        accountField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                accountFieldFocusGained();
            }
            @Override
            public void focusLost(FocusEvent e) {
                accountFieldFocusLost();
            }
        });

        //---- loginButton ----
        loginButton.setText("\u767b\u5f55");
        loginButton.addActionListener(e -> loginButtonLinster());

        //---- registerButton ----
        registerButton.setText("\u6ca1\u6709\u8d26\u53f7\uff1f\u70b9\u6211\u6ce8\u518c");
        registerButton.setBorderPainted(false);
        registerButton.addActionListener(e -> registerButtonlinsten());

        //---- remberPasswd ----
        remberPasswd.setText("\u8bb0\u4f4f\u5bc6\u7801");

        //---- forgotPasswdButton ----
        forgotPasswdButton.setText("\u5fd8\u8bb0\u5bc6\u7801");
        forgotPasswdButton.setBorderPainted(false);
        forgotPasswdButton.addActionListener(e -> forgotPasswdButtonLinsten());

        //---- passwordField1 ----
        passwordField1.setEchoChar('*');
        passwordField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField1FocusGained();
            }
            @Override
            public void focusLost(FocusEvent e) {
                passwordField1FocusLost();
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(label2)
                        .addComponent(label3))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(remberPasswd)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                            .addComponent(forgotPasswdButton))
                        .addComponent(loginButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addComponent(passwordField1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addComponent(accountField, GroupLayout.Alignment.LEADING))
                    .addGap(87, 87, 87))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(148, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(registerButton)
                            .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(label1)
                            .addGap(130, 130, 130))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(label1)
                    .addGap(27, 27, 27)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(accountField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(28, 28, 28)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3)
                        .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(forgotPasswdButton)
                        .addComponent(remberPasswd))
                    .addGap(18, 18, 18)
                    .addComponent(registerButton)
                    .addContainerGap(11, Short.MAX_VALUE))
        );
        setSize(430, 325);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField accountField;
    private JButton loginButton;
    private JButton registerButton;
    private JCheckBox remberPasswd;
    private JButton forgotPasswdButton;
    private JPasswordField passwordField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    // 自定义变量
    private final String accountInit = "请输入用户名或邮箱";
    private final String passwordInit = "请输入密码";
}
