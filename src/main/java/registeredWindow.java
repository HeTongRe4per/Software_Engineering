import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
/*
 * Created by JFormDesigner on Wed Nov 29 23:01:29 CST 2023
 */



/**
 * @author zhang
 */
public class registeredWindow extends JFrame {
    public registeredWindow() {
        initComponents();
    }

    private void button2registerLinsten() {
        if(regisverifyinfor()){
            // TODO 信息输入数据库

            //注释。。。
            this.setVisible(false);
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
            JOptionPane.showMessageDialog(null, "密码不符合复杂性要求！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 将密码进行 SHA-256 哈希计算
        String hashedPassword = PasswordHasher.hashPasswordSHA256(password);
        // 将用户信息插入到数据库中
        insertUserIntoDatabase(username, email, hashedPassword);

        while (true){
            // TODO 用户名是否重复、邮箱检验？
            if(!password.equals(surpassword)){
                JOptionPane.showMessageDialog(null, "密码不一致！", "错误", JOptionPane.ERROR_MESSAGE);
                break;
            }
            flag=true;
            break;
        }
        return flag;
    }

    // 检查密码是否符合复杂性要求的方法
    private boolean isPasswordValid(String password) {
        // 密码至少包含一个数字、一个小写字母、一个大写字母，总长度至少为6
        String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$";
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
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
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
        var contentPane = getContentPane();

        //---- button2 ----
        button2.setText("\u6ce8\u518c");
        button2.addActionListener(e -> button2registerLinsten());

        //---- passwordField1 ----
        passwordField1.setEchoChar('*');

        //---- passwordField2 ----
        passwordField2.setEchoChar('*');

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
}
