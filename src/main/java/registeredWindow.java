import javax.swing.*;
/*
 * Created by JFormDesigner on Wed Nov 29 23:01:29 CST 2023
 */



/**
 * @author zhang
 */
public class registeredWindow {
    public registeredWindow() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        registeredWindow = new JFrame();
        button1 = new JButton();
        button2 = new JButton();
        textField1 = new JTextField();
        textField2 = new JTextField();
        passwordField1 = new JPasswordField();
        textField3 = new JPasswordField();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== registeredWindow ========
        {
            registeredWindow.setTitle("\u6ce8\u518c\u8d26\u53f7");
            registeredWindow.setResizable(false);
            registeredWindow.setIconImage(new ImageIcon(getClass().getResource("/icon-chatgpt.png")).getImage());
            var registeredWindowContentPane = registeredWindow.getContentPane();

            //---- button1 ----
            button1.setText("\u53d6\u6d88");

            //---- button2 ----
            button2.setText("\u6ce8\u518c");

            //---- passwordField1 ----
            passwordField1.setEchoChar('*');

            //---- textField3 ----
            textField3.setEchoChar('*');

            //---- label1 ----
            label1.setText("\u7528\u6237\u540d\uff1a");

            //---- label2 ----
            label2.setText("\u90ae\u7bb1\uff1a");

            //---- label3 ----
            label3.setText("\u5bc6\u7801\uff1a");

            //---- label4 ----
            label4.setText("\u786e\u8ba4\u5bc6\u7801\uff1a");

            GroupLayout registeredWindowContentPaneLayout = new GroupLayout(registeredWindowContentPane);
            registeredWindowContentPane.setLayout(registeredWindowContentPaneLayout);
            registeredWindowContentPaneLayout.setHorizontalGroup(
                registeredWindowContentPaneLayout.createParallelGroup()
                    .addGroup(registeredWindowContentPaneLayout.createSequentialGroup()
                        .addContainerGap(36, Short.MAX_VALUE)
                        .addGroup(registeredWindowContentPaneLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, registeredWindowContentPaneLayout.createSequentialGroup()
                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58))
                            .addGroup(GroupLayout.Alignment.TRAILING, registeredWindowContentPaneLayout.createSequentialGroup()
                                .addGroup(registeredWindowContentPaneLayout.createParallelGroup()
                                    .addComponent(label1, GroupLayout.Alignment.TRAILING)
                                    .addComponent(label4, GroupLayout.Alignment.TRAILING)
                                    .addComponent(label3, GroupLayout.Alignment.TRAILING)
                                    .addComponent(label2, GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(registeredWindowContentPaneLayout.createParallelGroup()
                                    .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38))))
            );
            registeredWindowContentPaneLayout.setVerticalGroup(
                registeredWindowContentPaneLayout.createParallelGroup()
                    .addGroup(registeredWindowContentPaneLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(registeredWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1))
                        .addGap(18, 18, 18)
                        .addGroup(registeredWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2))
                        .addGap(18, 18, 18)
                        .addGroup(registeredWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3))
                        .addGap(18, 18, 18)
                        .addGroup(registeredWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4))
                        .addGap(18, 18, 18)
                        .addGroup(registeredWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
            );
            registeredWindow.setSize(310, 285);
            registeredWindow.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JFrame registeredWindow;
    private JButton button1;
    private JButton button2;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JPasswordField textField3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
