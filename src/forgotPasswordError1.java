import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Thu Nov 30 10:32:24 CST 2023
 */



/**
 * @author zhang
 */
public class forgotPasswordError1 extends JDialog {
    public forgotPasswordError1(Window owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialog1 = new JDialog();
        label3 = new JLabel();
        label4 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();

        //======== dialog1 ========
        {
            dialog1.setTitle("\u9519\u8bef");
            dialog1.setResizable(false);
            var dialog1ContentPane = dialog1.getContentPane();

            //---- label3 ----
            label3.setText("\u7528\u6237\u540d\u6216\u5bc6\u7801\u9519\u8bef");

            //---- label4 ----
            label4.setText("\u8bf7\u68c0\u67e5\u662f\u5426\u8f93\u5165\u6709\u8bef");

            //---- button2 ----
            button2.setText("\u786e\u8ba4");

            //---- button3 ----
            button3.setText("\u53d6\u6d88");

            GroupLayout dialog1ContentPaneLayout = new GroupLayout(dialog1ContentPane);
            dialog1ContentPane.setLayout(dialog1ContentPaneLayout);
            dialog1ContentPaneLayout.setHorizontalGroup(
                dialog1ContentPaneLayout.createParallelGroup()
                    .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                        .addContainerGap(66, Short.MAX_VALUE)
                        .addGroup(dialog1ContentPaneLayout.createParallelGroup()
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 47, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, dialog1ContentPaneLayout.createSequentialGroup()
                        .addContainerGap(126, Short.MAX_VALUE)
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button3)
                        .addGap(17, 17, 17))
            );
            dialog1ContentPaneLayout.setVerticalGroup(
                dialog1ContentPaneLayout.createParallelGroup()
                    .addGroup(dialog1ContentPaneLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(dialog1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button2)
                            .addComponent(button3))
                        .addGap(20, 20, 20))
            );
            dialog1.setSize(265, 175);
            dialog1.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JDialog dialog1;
    private JLabel label3;
    private JLabel label4;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
