import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
/*
 * Created by JFormDesigner on Wed Nov 29 23:01:29 CST 2023
 */



/**
 * @author zhang RDLS 小样
 */
public class registeredWindow extends JFrame {
    public registeredWindow() {
        initComponents();
        originalColor = textField1.getForeground();
        initWindow();
    }

    private void button2registerLinsten() {
        if(regisverifyinfor()){
            Main.loginWin.setEnabled(true);
            this.dispose();
        }else {
            this.repaint();
        }
    }

    private boolean regisverifyinfor(){
        String username,email,password,surpassword;
        boolean flag=false;
        username=textField1.getText();
        email=textField2.getText();
        password=new String(passwordField1.getPassword());
        surpassword=new String(passwordField2.getPassword());

        // 检查用户名是否已存在
        if (isUsernameExists(username)) {
            JOptionPane.showMessageDialog(null, "已存在该用户名！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 检查账号名是否匹配复杂性要求
        if (!isUsernameValid(username)) {
            JOptionPane.showMessageDialog(null, "用户名只能包含（大小写）字母、数字和下划线，长度在4到16位之间！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 检查邮箱是否已存在
        if (isEmailExists(email)) {
            JOptionPane.showMessageDialog(null, "已存在该邮箱！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 检查邮箱是否符合格式要求
        if (!isEmailValid(email)) {
            JOptionPane.showMessageDialog(null, "邮箱格式不正确！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 检查密码是否匹配
        if (!password.equals(surpassword)) {
            JOptionPane.showMessageDialog(null, "密码不一致！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 检查密码是否匹配复杂性要求
        if (!isPasswordValid(password)) {
            JOptionPane.showMessageDialog(null, "密码至少包含数字、（大或小写）英文字母，长度为6~64！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 将密码进行 SHA-256 哈希计算
        String hashedPassword = PasswordHasher.hashPasswordSHA256(password);
        // 将用户信息插入到数据库中
        insertUserIntoDatabase(username, email, hashedPassword);

        while (true){
            //用户名是否重复、邮箱检验
            if(!password.equals(surpassword)){
                JOptionPane.showMessageDialog(null, "密码不一致！", "错误", JOptionPane.ERROR_MESSAGE);
                break;
            }
            flag=true;
            break;
        }
        return flag;
    }

    // 检查用户名是否符合复杂性要求的方法
    private boolean isUsernameValid(String username) {
        // 用户名只能包含一个数字、一个小写字母、一个大写字母，总长度至少为6
        //String usernameRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,12}$";

        // 用户名只能包含字母、数字和下划线，长度在4到16位之间
        String usernameRegex = "^[a-zA-Z0-9_]{4,16}$";
        //System.out.println(username.matches(usernameRegex));
        return username.matches(usernameRegex);
    }

    // 检查密码是否符合复杂性要求的方法
    private boolean isPasswordValid(String password) {
        // 密码至少包含一个数字、一个小写字母、一个大写字母，总长度至少为6
        //String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,12}$";

        // 密码至少包含一个大写或小写字母，以及一个数字，总长度在6到64位之间
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9\\p{Punct}]{6,64}$";
        return password.matches(passwordRegex);
    }

    // 检查用户名是否已存在
    private boolean isUsernameExists(String username) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt")) {
            String query = "SELECT * FROM user WHERE username_mail = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // 如果存在记录，则用户名已存在
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 检查邮箱是否已存在
    private boolean isEmailExists(String email) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt")) {
            String query = "SELECT * FROM user WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // 如果存在记录，则邮箱已存在
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 检查邮箱是否符合格式要求的方法
    private boolean isEmailValid(String email) {
        // 使用正则表达式验证邮箱格式
        //String emailRegex = "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n";
        String emailRegex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        //System.out.println(email.matches(emailRegex));
        return email.matches(emailRegex);
    }

    // 将用户信息插入到数据库中
    private void insertUserIntoDatabase(String username, String email, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt")) {
            // 修改 SQL 查询语句，添加 email 列
            String insertQuery = "INSERT INTO user (username_mail, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "注册成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "注册失败！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void accountFieldFocusGained() {
        String temp = textField1.getText();
        if (temp.equals(initUsernameText)) {
            textField1.setText("");
            textField1.setForeground(originalColor);
        }
    }

    private void accountFieldFocusLost() {
        String temp = textField1.getText();
        if (temp.isEmpty()) {
            textField1.setText(initUsernameText);
            textField1.setForeground(Color.GRAY);
        }
    }

    private void emailFieldFocusGained() {
        String temp = textField2.getText();
        if (temp.equals(initEmailText)) {
            textField2.setText("");
            textField2.setForeground(originalColor);
        }
    }

    private void emailFieldFocusLost() {
        String temp = textField2.getText();
        if (temp.isEmpty()) {
            textField2.setText(initEmailText);
            textField2.setForeground(Color.GRAY);
        }
    }

    private void passwordFieldFocusGained() {
        String temp = passwordField1.getText();
        if (temp.equals(initPasswordText)) {
            passwordField1.setText("");
            passwordField1.setForeground(originalColor);
            passwordField1.setEchoChar('*');
        }
    }

    private void passwordFieldFocusLost() {
        String temp = passwordField1.getText();
        if (temp.isEmpty()) {
            passwordField1.setText(initPasswordText);
            passwordField1.setForeground(Color.GRAY);
            passwordField1.setEchoChar((char) 0);
        }
    }

    private void passwordConfirmFieldFocusGained() {
        String temp = passwordField2.getText();
        if (temp.equals(initConfirmPasswdText)) {
            passwordField2.setText("");
            passwordField2.setForeground(originalColor);
            passwordField2.setEchoChar('*');
        }
    }

    private void passwordConfirmFieldFocusLost() {
        String temp = passwordField2.getText();
        if (temp.isEmpty()) {
            passwordField2.setText(initConfirmPasswdText);
            passwordField2.setForeground(Color.GRAY);
            passwordField2.setEchoChar((char) 0);
        }
    }
    
    private void initWindow() {
        textField1.setForeground(Color.GRAY);
        textField2.setForeground(Color.GRAY);
        passwordField1.setForeground(Color.GRAY);
        passwordField2.setForeground(Color.GRAY);
        passwordField1.setEchoChar((char) 0);
        passwordField2.setEchoChar((char) 0);
        textField1.setText(initUsernameText);
        textField2.setText(initEmailText);
        passwordField1.setText(initPasswordText);
        passwordField2.setText(initConfirmPasswdText);
    }

    private void accountFieldKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {
            textField2.requestFocusInWindow();
        }
    }

    private void emailFieldKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {
            passwordField1.requestFocusInWindow();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            textField1.requestFocusInWindow();
        }
    }

    private void passwordFieldKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {
            passwordField2.requestFocusInWindow();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            textField2.requestFocusInWindow();
        }
    }

    private void passwordConfirmFieldKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            button2.doClick();
        } else if (e.getKeyCode() ==KeyEvent.VK_UP) {
            passwordField1.requestFocusInWindow();
        }
    }

    private void thisWindowClosing() {
        Main.loginWin.setEnabled(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        button2 = new JButton();
        textField1 = new JTextField();
        textField2 = new JTextField();
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== this ========
        setTitle("\u6ce8\u518c\u8d26\u53f7");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/icon-chatgpt.png")).getImage());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing();
            }
        });
        var contentPane = getContentPane();

        //---- button2 ----
        button2.setText("\u6ce8\u518c");
        button2.addActionListener(e -> button2registerLinsten());

        //---- textField1 ----
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                accountFieldFocusGained();
            }
            @Override
            public void focusLost(FocusEvent e) {
                accountFieldFocusLost();
            }
        });
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                accountFieldKeyPressed(e);
            }
        });

        //---- textField2 ----
        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                emailFieldFocusGained();
            }
            @Override
            public void focusLost(FocusEvent e) {
                emailFieldFocusLost();
            }
        });
        textField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                emailFieldKeyPressed(e);
            }
        });

        //---- passwordField1 ----
        passwordField1.setEchoChar('*');
        passwordField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordFieldFocusGained();
            }
            @Override
            public void focusLost(FocusEvent e) {
                passwordFieldFocusLost();
            }
        });
        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                passwordFieldKeyPressed(e);
            }
        });

        //---- passwordField2 ----
        passwordField2.setEchoChar('*');
        passwordField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordConfirmFieldFocusGained();
            }
            @Override
            public void focusLost(FocusEvent e) {
                passwordConfirmFieldFocusLost();
            }
        });
        passwordField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                passwordConfirmFieldKeyPressed(e);
            }
        });

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d\uff1a");

        //---- label2 ----
        label2.setText("\u90ae\u7bb1\uff1a");

        //---- label3 ----
        label3.setText("\u5bc6\u7801\uff1a");

        //---- label4 ----
        label4.setText("\u786e\u8ba4\u5bc6\u7801\uff1a");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(34, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(label1, GroupLayout.Alignment.TRAILING)
                                .addComponent(label4, GroupLayout.Alignment.TRAILING)
                                .addComponent(label3, GroupLayout.Alignment.TRAILING)
                                .addComponent(label2, GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addComponent(passwordField2, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
                            .addGap(38, 38, 38))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addGap(104, 104, 104))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label3))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordField2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label4))
                    .addGap(18, 18, 18)
                    .addComponent(button2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        setSize(310, 285);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton button2;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    // 自定义变量
    private String initUsernameText = "只允许字母数字和下划线";
    private String initEmailText = "mail@example.com";
    private String initPasswordText = "至少包含字母、数字";
    private String initConfirmPasswdText = "再次输入密码";
    private Color originalColor;
}
