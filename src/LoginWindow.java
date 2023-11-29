import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Wed Nov 29 21:23:10 CST 2023
 */



/**
 * @author zhang
 */
public class LoginWindow {
    public LoginWindow() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        frame1 = new JFrame();
        textField1 = new JTextField();
        passwordField1 = new JPasswordField();
        label1 = new JLabel();
        label2 = new JLabel();
        checkBox1 = new JCheckBox();
        button1 = new JButton();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== frame1 ========
        {
            frame1.setTitle("\u767b\u5f55\u8d26\u53f7");
            frame1.setIconImage(null);
            frame1.setResizable(false);
            var frame1ContentPane = frame1.getContentPane();

            //---- label1 ----
            label1.setText("\u8d26\u53f7\uff1a");

            //---- label2 ----
            label2.setText("\u5bc6\u7801\uff1a");

            //---- checkBox1 ----
            checkBox1.setText("\u8bb0\u4f4f\u5bc6\u7801");

            //---- button1 ----
            button1.setText("\u5fd8\u8bb0\u5bc6\u7801");
            button1.setBorderPainted(false);

            //---- label4 ----
            label4.setIcon(new ImageIcon(getClass().getResource("/resource/ChatGPT_Logo_PNG.png")));

            GroupLayout frame1ContentPaneLayout = new GroupLayout(frame1ContentPane);
            frame1ContentPane.setLayout(frame1ContentPaneLayout);
            frame1ContentPaneLayout.setHorizontalGroup(
                frame1ContentPaneLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, frame1ContentPaneLayout.createSequentialGroup()
                        .addContainerGap(87, Short.MAX_VALUE)
                        .addGroup(frame1ContentPaneLayout.createParallelGroup()
                            .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                                .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(label2, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                    .addComponent(label1, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(frame1ContentPaneLayout.createParallelGroup()
                                    .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addComponent(label3)))
                        .addContainerGap(62, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, frame1ContentPaneLayout.createSequentialGroup()
                        .addContainerGap(98, Short.MAX_VALUE)
                        .addComponent(checkBox1)
                        .addGap(76, 76, 76)
                        .addComponent(button1)
                        .addGap(90, 90, 90))
                    .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(label4)
                        .addContainerGap(135, Short.MAX_VALUE))
            );
            frame1ContentPaneLayout.setVerticalGroup(
                frame1ContentPaneLayout.createParallelGroup()
                    .addGroup(frame1ContentPaneLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(label4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label3)
                        .addGap(18, 18, 18)
                        .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(checkBox1)
                            .addComponent(button1))
                        .addContainerGap())
            );
            frame1.setSize(420, 275);
            frame1.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JFrame frame1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel label1;
    private JLabel label2;
    private JCheckBox checkBox1;
    private JButton button1;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
