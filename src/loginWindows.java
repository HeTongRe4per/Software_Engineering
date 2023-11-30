import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
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
        this.accountInit = "请输入用户名或邮箱";
        this.passwordInit = "请输入密码";
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
        if (temp.equals("")) {
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
        if (temp.equals("")) {
            passwordField1.setText(passwordInit);
            passwordField1.setForeground(Color.GRAY);
            passwordField1.setEchoChar((char) 0);
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
        setIconImage(new ImageIcon(getClass().getResource("/resource/icon-chatgpt.png")).getImage());
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/resource/ChatGPT_Logo_PNG.png")));

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

        //---- registerButton ----
        registerButton.setText("\u6ca1\u6709\u8d26\u53f7\uff1f\u70b9\u6211\u6ce8\u518c");
        registerButton.setBorderPainted(false);

        //---- remberPasswd ----
        remberPasswd.setText("\u8bb0\u4f4f\u5bc6\u7801");

        //---- forgotPasswdButton ----
        forgotPasswdButton.setText("\u5fd8\u8bb0\u5bc6\u7801");
        forgotPasswdButton.setBorderPainted(false);

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
    private String accountInit;
    private String passwordInit;
}
