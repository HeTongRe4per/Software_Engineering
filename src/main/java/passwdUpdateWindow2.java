import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/*
 * Created by JFormDesigner on Thu Nov 30 16:08:18 CST 2023
 */



/**
 * @author zhang RDLS 小样
 */
public class passwdUpdateWindow2 extends JFrame {
    public passwdUpdateWindow2(String username_mail) {
        this.username_mail = username_mail;
        initComponents();
        this.setVisible(true);
    }

    private boolean buttonSureListen() {
        boolean flag=true;
        String newpassword,surpassword;
        newpassword=new String(passwordField1.getPassword());
        surpassword=new String(passwordField2.getPassword());

        // 检查密码是否匹配复杂性要求
        if (!isPasswordValid(newpassword)) {
            JOptionPane.showMessageDialog(null, "密码不符合复杂性要求！", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(newpassword.equals(surpassword)){
            if (updatePasswordInDatabase(newpassword)) {
                JOptionPane.showMessageDialog(null, "密码修改成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                accMgWindow.delefile();

                // 当密码成功更改时，自动关闭主窗口和和账户管理
                dispose();
                if (ChatInterface.accMgWin != null) {
                    ChatInterface.accMgWin.dispose();
                    loginWindows.mainWin.dispose();
                } if (Main.loginWin != null) {
                    loginWindows.forgotPasswordWin.dispose();
                }
                Main.loginWin = new loginWindows();
                Main.loginWin.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "密码修改失败！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(null, "密码不一致！", "错误", JOptionPane.ERROR_MESSAGE);
        }
        return flag;
    }

    // 检查密码是否符合复杂性要求的方法
    private boolean isPasswordValid(String password) {
        // 密码至少包含一个数字、一个小写字母、一个大写字母，总长度至少为6
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9\\p{Punct}]{6,64}$";
        return password.matches(passwordRegex);
    }

    private boolean updatePasswordInDatabase(String newpassword) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt")) {
            String updateQuery = "UPDATE user SET password = ? WHERE username_mail = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, PasswordHasher.hashPasswordSHA256(newpassword));
                preparedStatement.setString(2, username_mail);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void passwordField1KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN){
            passwordField2.requestFocus();
        }
    }

    private void passwordField2KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            button1.doClick();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            passwordField1.requestFocus();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            button1.requestFocus();
        }
    }

    private void thisWindowClosing() {
        if (ChatInterface.accMgWin != null) {
            ChatInterface.accMgWin.setEnabled(true);
        } if (loginWindows.forgotPasswordWin !=null) {
            loginWindows.forgotPasswordWin.setEnabled(true);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();
        button1 = new JButton();

        //======== this ========
        setTitle("\u4fee\u6539\u5bc6\u7801");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing();
            }
        });
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u65b0\u5bc6\u7801\uff1a");

        //---- label2 ----
        label2.setText("\u786e\u8ba4\u5bc6\u7801\uff1a");

        //---- passwordField1 ----
        passwordField1.setEchoChar('*');
        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                passwordField1KeyPressed(e);
            }
        });

        //---- passwordField2 ----
        passwordField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                passwordField2KeyPressed(e);
            }
        });

        //---- button1 ----
        button1.setText("\u786e\u8ba4");
        button1.addActionListener(e -> buttonSureListen());

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(label2)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(passwordField2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(62, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(243, Short.MAX_VALUE)
                    .addComponent(button1)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1)
                        .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(19, 19, 19)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordField2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                    .addComponent(button1)
                    .addContainerGap())
        );
        setSize(325, 205);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    private String username_mail;
}
