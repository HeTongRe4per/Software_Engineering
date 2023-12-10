import javax.swing.*;
/*
 * Created by JFormDesigner on Thu Nov 30 09:10:35 CST 2023
 */



/**
 * @author zhang
 */
public class accMgWindow extends JFrame {
    public accMgWindow() {
        initComponents();
    }

    private void delAccountListen() {
        //
        new confirmDelAccountWindow(this).setVisible(true);
    }

    private void resetUserNameListen() {
        //
        String username=accountTextField.getText();
        // TODO 更新数据库

    }

    private void resetEmailListen() {
        String email=emailTextField.getText();
        // TODO 更新数据库

    }

    private void resetPasswdListen() {
        //todo 账号邮箱
        new passwdUpdateWindow2("123").setVisible(true);
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
