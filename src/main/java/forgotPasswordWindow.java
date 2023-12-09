import javax.swing.*;
import javax.swing.GroupLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/*
 * Created by JFormDesigner on Thu Nov 30 10:22:31 CST 2023
 */



/**
 * @author zhang
 */
public class forgotPasswordWindow extends JFrame {
    public forgotPasswordWindow() {
        initComponents();
    }

    private void buttonNextLinsten() {
        //
        if(forpassverinfor()){
            new passwdUpdateWindow2().setVisible(true);
            this.setVisible(false);
            this.dispose();
        }
    }

    private boolean forpassverinfor(){
        boolean flag=false;
        String username_mail,email;
        username_mail=textField1.getText();
        email=textField2.getText();
        // 连接数据库验证信息
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            String query = "SELECT * FROM user WHERE username_mail = ? AND email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username_mail);
                preparedStatement.setString(2, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // 如果结果集不为空，意味着用户名和邮箱是有效的
                    flag = resultSet.next();
                }
                if (!flag)
                {
                    // 用户名或邮箱不匹配，弹出提示
                    String errorMessage = "用户名或邮箱";
                    if (!isUsernameExists(username_mail) && !isEmailExists(email)) {
                        errorMessage = "用户名和邮箱";
                    } else if (!isUsernameExists(username_mail)) {
                        errorMessage = "用户名";
                    } else if (!isEmailExists(email)) {
                        errorMessage = "邮箱";
                    }
                    JOptionPane.showMessageDialog(null, errorMessage + "不匹配！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理数据库连接异常
        }finally {
            try {
                // 关闭数据库连接
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return flag;
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        button1 = new JButton();

        //======== this ========
        setTitle("\u5fd8\u8bb0\u5bc6\u7801");
        setIconImage(null);
        setResizable(false);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d\uff1a");

        //---- label2 ----
        label2.setText("\u90ae\u7bb1\uff1a");

        //---- button1 ----
        button1.setText("\u4e0b\u4e00\u6b65");
        button1.addActionListener(e -> buttonNextLinsten());

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(59, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
                            .addGap(66, 66, 66))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(button1)
                            .addContainerGap())))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(26, 26, 26)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                    .addComponent(button1)
                    .addContainerGap())
        );
        setSize(320, 205);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
