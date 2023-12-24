import javax.swing.*;
import javax.swing.GroupLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * Created by JFormDesigner on Thu Nov 30 10:45:02 CST 2023
 */



/**
 * @author zhang RDLS 小样
 */
public class confirmDelAccountWindow extends JDialog {

    public confirmDelAccountWindow() {
        initComponents();
    }

    private void confirmDelListen() {
        String username = accMgWindow.oldusername;
        // 执行删除数据库操作
        deleteAccountFromDatabase(username);
        // 关闭对话框
        dispose();
        // 关闭父窗口（accMgWindow）
        ChatInterface.accMgWin.dispose();
        // 关闭登录窗口（loginWindows）
        // 打开loginWindows窗口
        main.loginWin = new loginWindows();
        main.loginWin.setVisible(true);
    }

    // 从数据库中删除账号的方法
    private void deleteAccountFromDatabase(String username) {
        Connection connection = null;
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt");

            // 准备删除语句
            String deleteQuery = "DELETE FROM user WHERE username_mail = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                // 设置删除语句的参数
                preparedStatement.setString(1, username);

                // 执行删除语句
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

    private void cancelDelListen() {
        // 取消删除
        this.setVisible(false);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        confirmDelButton = new JButton();
        cancelDelButton = new JButton();

        //======== this ========
        setTitle("\u786e\u8ba4\u5220\u9664");
        setResizable(false);
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u786e\u8ba4\u5220\u9664\u8d26\u6237\u5417\uff1f");

        //---- label2 ----
        label2.setText("\u4f60\u7684\u8d26\u6237\u5c06\u4f1a\u6c38\u4e45\u5931\u53bb\uff01\uff08\u771f\u7684\u5f88\u4e45\uff01\uff09");

        //---- confirmDelButton ----
        confirmDelButton.setText("\u6211\u518d\u60f3\u60f3");
        confirmDelButton.setSelected(true);
        confirmDelButton.addActionListener(e -> confirmDelListen());

        //---- cancelDelButton ----
        cancelDelButton.setText("\u786e\u8ba4\u5220\u9664");
        cancelDelButton.addActionListener(e -> cancelDelListen());

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(51, 51, 51)
                    .addComponent(label2)
                    .addContainerGap(41, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 115, Short.MAX_VALUE)
                    .addComponent(label1)
                    .addGap(97, 97, 97))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(69, Short.MAX_VALUE)
                    .addComponent(confirmDelButton)
                    .addGap(18, 18, 18)
                    .addComponent(cancelDelButton)
                    .addGap(57, 57, 57))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(label2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(confirmDelButton)
                        .addComponent(cancelDelButton))
                    .addGap(18, 18, 18))
        );
        setSize(310, 200);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JButton confirmDelButton;
    private JButton cancelDelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
