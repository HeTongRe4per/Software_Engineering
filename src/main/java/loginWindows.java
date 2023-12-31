import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Scanner;
/*
 * Created by JFormDesigner on Thu Nov 30 13:18:45 CST 2023
 */



/**
 * @author zhang RDLS 小样
 */
public class loginWindows extends JFrame{

    public loginWindows() {
        initComponents();
        originalColor = accountField.getForeground(); //获取原始文字颜色
        LoginWindowInit();
        if(checkFileExistence(FILE_PATH)&&checkFileExistence(FILE_PATH + "boolean")){
            if(readBool()){
                remberPasswd.setSelected(true);
                try {
                    accountField.setForeground(originalColor);
                    passwordField1.setForeground(originalColor);
                    default_input();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //loginButton.requestFocusInWindow();
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
            accountField.setForeground(originalColor);
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
            passwordField1.setForeground(originalColor);
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

    private void loginButtonListener() {
        loginButton.setEnabled(false);
        registerButton.setEnabled(false);
        forgotPasswdButton.setEnabled(false);
        // 创建 SwingWorker
        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                // 在后台线程中执行登录验证
                return logVerifyInfo();
            }

            @Override
            protected void done() {
                try {
                    // 获取 doInBackground 返回的结果
                    boolean isValid = get();

                    if (isValid) {
                        // 在验证成功的情况下，将username_mail的值赋给username
                        // 添加用户名成员变量
                        username_s = accountField.getText();
                        isSelect = remberPasswd.isSelected();
                        boolIsSelect();
                        remberPasswdListen();
                        mainWin = new ChatInterface();
                        mainWin.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "账号或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
                        loginButton.setEnabled(true);
                        registerButton.setEnabled(true);
                        forgotPasswdButton.setEnabled(true);
                    }
                } catch (Exception e) {
                    loginButton.setEnabled(true);
                    registerButton.setEnabled(true);
                    forgotPasswdButton.setEnabled(true);
                    e.printStackTrace();
                }
            }
        };

        // 执行 SwingWorker
        worker.execute();
    }

    private void registerButtonlinsten() {
        Main.loginWin.setEnabled(false);
        new registeredWindow().setVisible(true);
    }

    private void forgotPasswdButtonLinsten() {
        Main.loginWin.setEnabled(false);
        forgotPasswordWin = new forgotPasswordWindow();
        forgotPasswordWin.setVisible(true);
    }

    private boolean logVerifyInfo(){
        //数据库验证
        boolean flag = false;
        String  username_mail,password;
        username_mail=accountField.getText();
        password= new String(passwordField1.getPassword());
        // 连接数据库验证信息
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "服务器连接失败，请检查网络或请联系管理员！", "错误", JOptionPane.ERROR_MESSAGE);
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

    private void boolIsSelect(){
        File file=new File(FILE_PATH+"boolean");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.valueOf(isSelect));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean readBool(){
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
    private void remberPasswdListen() {
        try {
            remberPasswdAction();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void remberPasswdAction() throws IOException {
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
            //System.out.println("source file not exist");
            return;
        }

        if (!encFile.exists()) {
            //System.out.println("encrypt file created");
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
            //System.out.println("加密文件不存在");
            return;
        }

        if (!decFile.exists()) {
            //System.out.println("解密文件已创建");
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
    static boolean checkFileExistence(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    private void accountFieldListen() {
        //
        passwordField1.requestFocusInWindow();
    }

    private void accountFieldKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {
            passwordField1.requestFocusInWindow();
        }
    }

    private void passwordField1KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            loginButton.doClick();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            accountField.requestFocus();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            loginButton.requestFocus();
        }
    }

    private void loginButtonKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            passwordField1.requestFocus();
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
        accountField.addActionListener(e -> accountFieldListen());
        accountField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                accountFieldKeyPressed(e);
            }
        });

        //---- loginButton ----
        loginButton.setText("\u767b\u5f55");
        loginButton.addActionListener(e -> loginButtonListener());

        //---- registerButton ----
        registerButton.setText("\u6ca1\u6709\u8d26\u53f7\uff1f\u70b9\u6211\u6ce8\u518c");
        registerButton.setBorderPainted(false);
        registerButton.addActionListener(e -> registerButtonlinsten());

        //---- remberPasswd ----
        remberPasswd.setText("\u8bb0\u4f4f\u5bc6\u7801");
        remberPasswd.addActionListener(e -> remberPasswdListen());

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
        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                passwordField1KeyPressed(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(58, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(label3)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(remberPasswd)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(forgotPasswdButton))
                                        .addComponent(loginButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                        .addComponent(passwordField1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(label2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(accountField, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label1))))
                            .addGap(84, 84, 84))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(registerButton)
                            .addContainerGap())))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(accountField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label3))
                    .addGap(18, 18, 18)
                    .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(forgotPasswdButton)
                        .addComponent(remberPasswd))
                    .addGap(18, 18, 18)
                    .addComponent(registerButton)
                    .addContainerGap())
        );
        setSize(420, 320);
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

    static ChatInterface mainWin;
    static forgotPasswordWindow forgotPasswordWin;
    private final String accountInit = "请输入用户名或邮箱";
    private final String passwordInit = "请输入密码";
    static String username_s;
    private boolean isSelect = false;
    private final String localAppDATA=System.getenv("LOCALAPPDATA");
    private final String FILE_PATH = localAppDATA+"\\Wise_Conversations\\credentials";
    private final int numOfEncAndDec = 0x99; // 加密解密秘钥
    private int dataOfFile = 0; // 文件字节内容
    //原始文字颜色
    private final Color originalColor;
}
