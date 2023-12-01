import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Thu Nov 30 08:55:23 CST 2023
 */



/**
 * @author zhang
 */
public class settingWindow {
    public settingWindow() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        settingWindow = new JFrame();
        layeredPane1 = new JLayeredPane();
        label1 = new JLabel();
        label2 = new JLabel();
        textField2 = new JTextField();
        textField1 = new JTextField();
        layeredPane2 = new JLayeredPane();
        label3 = new JLabel();
        label4 = new JLabel();
        comboBox2 = new JComboBox();
        spinner1 = new JSpinner();
        button1 = new JButton();
        button2 = new JButton();

        //======== settingWindow ========
        {
            settingWindow.setTitle("\u8bbe\u7f6e");
            settingWindow.setResizable(false);
            settingWindow.setIconImage(new ImageIcon(getClass().getResource("/icon-chatgpt.png")).getImage());
            var settingWindowContentPane = settingWindow.getContentPane();

            //======== layeredPane1 ========
            {
                layeredPane1.setBorder(new TitledBorder("ChatGPI API\u8bbe\u7f6e"));

                //---- label1 ----
                label1.setText("\u63a5\u53e3URL\uff1a");
                layeredPane1.add(label1, JLayeredPane.DEFAULT_LAYER);
                label1.setBounds(new Rectangle(new Point(35, 30), label1.getPreferredSize()));

                //---- label2 ----
                label2.setText("API Key\uff1a");
                layeredPane1.add(label2, JLayeredPane.DEFAULT_LAYER);
                label2.setBounds(35, 75, 55, label2.getPreferredSize().height);
                layeredPane1.add(textField2, JLayeredPane.DEFAULT_LAYER);
                textField2.setBounds(110, 25, 190, 25);
                layeredPane1.add(textField1, JLayeredPane.DEFAULT_LAYER);
                textField1.setBounds(110, 70, 190, 25);
            }

            //======== layeredPane2 ========
            {
                layeredPane2.setBorder(new TitledBorder("Chat\u533a\u8bbe\u7f6e"));

                //---- label3 ----
                label3.setText("Chat\u6846\u5b57\u4f53");
                layeredPane2.add(label3, JLayeredPane.DEFAULT_LAYER);
                label3.setBounds(new Rectangle(new Point(15, 35), label3.getPreferredSize()));

                //---- label4 ----
                label4.setText("Chat\u6846\u989c\u8272");
                layeredPane2.add(label4, JLayeredPane.DEFAULT_LAYER);
                label4.setBounds(new Rectangle(new Point(165, 35), label4.getPreferredSize()));
                layeredPane2.add(comboBox2, JLayeredPane.DEFAULT_LAYER);
                comboBox2.setBounds(230, 30, 70, 25);
                layeredPane2.add(spinner1, JLayeredPane.DEFAULT_LAYER);
                spinner1.setBounds(80, 30, 70, 25);
            }

            //---- button1 ----
            button1.setText("\u786e\u5b9a");

            //---- button2 ----
            button2.setText("\u53d6\u6d88");

            GroupLayout settingWindowContentPaneLayout = new GroupLayout(settingWindowContentPane);
            settingWindowContentPane.setLayout(settingWindowContentPaneLayout);
            settingWindowContentPaneLayout.setHorizontalGroup(
                settingWindowContentPaneLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, settingWindowContentPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(settingWindowContentPaneLayout.createSequentialGroup()
                                .addGap(0, 201, Short.MAX_VALUE)
                                .addComponent(button2)
                                .addGap(18, 18, 18)
                                .addComponent(button1))
                            .addComponent(layeredPane1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                            .addComponent(layeredPane2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
                        .addContainerGap())
            );
            settingWindowContentPaneLayout.setVerticalGroup(
                settingWindowContentPaneLayout.createParallelGroup()
                    .addGroup(settingWindowContentPaneLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(layeredPane1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(layeredPane2, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(14, Short.MAX_VALUE))
            );
            settingWindow.setSize(355, 305);
            settingWindow.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JFrame settingWindow;
    private JLayeredPane layeredPane1;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField2;
    private JTextField textField1;
    private JLayeredPane layeredPane2;
    private JLabel label3;
    private JLabel label4;
    private JComboBox comboBox2;
    private JSpinner spinner1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
