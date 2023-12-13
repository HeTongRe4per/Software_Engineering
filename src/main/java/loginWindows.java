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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
 * Created by JFormDesigner on Thu Nov 30 13:18:45 CST 2023
 */



/**
 * @author zhang
 */
public class loginWindows extends JFrame{
    private String username; // 添加用户名成员变量

    public loginWindows() {
        initComponents();
        LoginWindowInit();
        if(checkFileExistence(FILE_PATH)&&checkFileExistence(FILE_PATH+"boolean")){
            if(readbool()){
                remberPasswd.setSelected(true);
                try {
                    default_input();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //。。
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
            // 在验证成功的情况下，将username_mail的值赋给username
            username = accountField.getText();

            isselect=remberPasswd.isSelected();
            boolisselect();
            try {
                remberPasswdListen();//。
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
            // 修改查询条件，检查用户名或邮箱是否匹配
            String query = "SELECT * FROM user WHERE username_mail = ? OR email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username_mail);
                preparedStatement.setString(2, username_mail);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // 如果结果集不为空，则验证密码
                    if (resultSet.next()) {
                        String storedPassword = resultSet.getString("password");
                        flag = hashedPassword.equals(storedPassword);
                    }
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
    private String localAppDATA=System.getenv("LOCALAPPDATA");
    private final String FILE_PATH = localAppDATA+"\\CIF\\credentials";
    private final int numOfEncAndDec = 0x99; // 加密解密秘钥
    private int dataOfFile = 0; // 文件字节内容
    private boolean isselect = false;
    private void boolisselect(){
        File file=new File(FILE_PATH+"boolean");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.valueOf(isselect));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean readbool(){
        boolean value=false;
        File file=new File(FILE_PATH+"boolean");
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
    }
    private void remberPasswdListen() throws IOException {
        // 记住密码，加密保存账号和密码到文件
        File F_file = new File(FILE_PATH+"-F");
        F_file.getParentFile().mkdirs(); // 创建父文件夹（如果不存在）
        F_file.createNewFile(); // 创建文件（如果不存在）
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // 创建父文件夹（如果不存在）
        file.createNewFile(); // 创建文件（如果不存在）
        String  username_mail,password;
        username_mail=accountField.getText();
        password= new String(passwordField1.getPassword());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(F_file))) {
            writer.write(username_mail + "," + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            EncFile(F_file,file); // 加密操作
        } catch (Exception e) {
            e.printStackTrace();
        }
        F_file.delete();
    }

    private void EncFile(File srcFile, File encFile) throws Exception {
        if (!srcFile.exists()) {
            System.out.println("source file not exist");
            return;
        }

        if (!encFile.exists()) {
            System.out.println("encrypt file created");
            encFile.createNewFile();
        }
        InputStream fis = new FileInputStream(srcFile);
        OutputStream fos = new FileOutputStream(encFile);

        while ((dataOfFile = fis.read()) > -1) {
            fos.write(dataOfFile ^ numOfEncAndDec);
        }

        fis.close();
        fos.flush();
        fos.close();
    }

    private void DecFile(File encFile, File decFile) throws Exception {
        if (!encFile.exists()) {
            System.out.println("加密文件不存在");
            return;
        }

        if (!decFile.exists()) {
            System.out.println("解密文件已创建");
            decFile.createNewFile();
        }

        InputStream fis = new FileInputStream(encFile);
        OutputStream fos = new FileOutputStream(decFile);

        while ((dataOfFile = fis.read()) > -1) {
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

    private void readfile(File file){
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
            String[] parts = Text.split(",");
            accountField.setText(parts[0]);
            passwordField1.setText(parts[1]);
            passwordField1.setEchoChar('*');
            // 关闭Scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkFileExistence(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    private void accountFieldListen() {
        //
        passwordField1.requestFocusInWindow();
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
        accountField.addActionListener(e -> accountFieldListen());

        //---- loginButton ----
        loginButton.setText("\u767b\u5f55");
        loginButton.addActionListener(e -> loginButtonLinster());

        //---- registerButton ----
        registerButton.setText("\u6ca1\u6709\u8d26\u53f7\uff1f\u70b9\u6211\u6ce8\u518c");
        registerButton.setBorderPainted(false);
        registerButton.addActionListener(e -> registerButtonlinsten());

        //---- remberPasswd ----
        remberPasswd.setText("\u8bb0\u4f4f\u5bc6\u7801");
        remberPasswd.addActionListener(e -> {try {
            remberPasswdListen();} catch (IOException ex) {
            throw new RuntimeException(ex);
        }});

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

    public String getUsername() {
        return username;
    }
}
