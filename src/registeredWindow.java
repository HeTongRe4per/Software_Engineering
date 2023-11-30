import java.awt.*;
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
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        textField1 = new JTextField();
        passwordField1 = new JPasswordField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== registeredWindow ========
        {
            registeredWindow.setTitle("\u6ce8\u518c\u8d26\u53f7");
            registeredWindow.setResizable(false);
            var registeredWindowContentPane = registeredWindow.getContentPane();

            //---- label1 ----
            label1.setText("\u7528\u6237\u540d\uff1a");

            //---- label2 ----
            label2.setText("\u90ae\u7bb1\uff1a");

            //---- label3 ----
            label3.setText("\u5bc6\u7801\uff1a");

            //---- label4 ----
            label4.setText("\u786e\u8ba4\u5bc6\u7801\uff1a");

            //---- button1 ----
            button1.setText("\u53d6\u6d88");

            //---- button2 ----
            button2.setText("\u6ce8\u518c");

            GroupLayout registeredWindowContentPaneLayout = new GroupLayout(registeredWindowContentPane);
            registeredWindowContentPane.setLayout(registeredWindowContentPaneLayout);
            registeredWindowContentPaneLayout.setHorizontalGroup(
                registeredWindowContentPaneLayout.createParallelGroup()
                    .addGroup(registeredWindowContentPaneLayout.createSequentialGroup()
                        .addGroup(registeredWindowContentPaneLayout.createParallelGroup()
                            .addGroup(registeredWindowContentPaneLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                            .addGroup(registeredWindowContentPaneLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(registeredWindowContentPaneLayout.createParallelGroup()
                                    .addComponent(label4, GroupLayout.Alignment.TRAILING)
                                    .addGroup(GroupLayout.Alignment.TRAILING, registeredWindowContentPaneLayout.createParallelGroup()
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(registeredWindowContentPaneLayout.createParallelGroup()
                                    .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(38, Short.MAX_VALUE))
            );
            registeredWindowContentPaneLayout.setVerticalGroup(
                registeredWindowContentPaneLayout.createParallelGroup()
                    .addGroup(registeredWindowContentPaneLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(registeredWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField1, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(registeredWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordField1, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(registeredWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(registeredWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(registeredWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(36, Short.MAX_VALUE))
            );
            registeredWindow.setSize(310, 305);
            registeredWindow.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JFrame registeredWindow;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
