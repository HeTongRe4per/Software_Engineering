import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Thu Nov 30 10:45:02 CST 2023
 */



/**
 * @author zhang
 */
public class confirmDelAccountWindow extends JDialog {
    public confirmDelAccountWindow(Window owner) {
        super(owner);
        initComponents();
    }

    private void cancelDelListen() {
        // TODO 获取信息、删除数据库

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
                    .addGap(0, 66, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(label1)
                            .addGap(97, 97, 97))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(confirmDelButton)
                            .addGap(18, 18, 18)
                            .addComponent(cancelDelButton)
                            .addGap(62, 62, 62))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(label2)
                    .addGap(37, 37, 37)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(confirmDelButton)
                        .addComponent(cancelDelButton))
                    .addContainerGap(27, Short.MAX_VALUE))
        );
        setSize(310, 190);
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
