import javax.swing.*;
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
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        textField2 = new JTextField();
        textField1 = new JTextField();
        comboBox1 = new JComboBox();
        comboBox2 = new JComboBox();

        //======== settingWindow ========
        {
            settingWindow.setTitle("\u8bbe\u7f6e");
            settingWindow.setResizable(false);
            settingWindow.setIconImage(new ImageIcon(getClass().getResource("/resource/icon-chatgpt.png")).getImage());
            var settingWindowContentPane = settingWindow.getContentPane();

            //---- label1 ----
            label1.setText("\u63a5\u53e3URL\uff1a");

            //---- label2 ----
            label2.setText("API\uff1a");

            //---- label3 ----
            label3.setText("Chat\u6846\u5b57\u4f53");

            //---- label4 ----
            label4.setText("Chat\u6846\u989c\u8272");

            GroupLayout settingWindowContentPaneLayout = new GroupLayout(settingWindowContentPane);
            settingWindowContentPane.setLayout(settingWindowContentPaneLayout);
            settingWindowContentPaneLayout.setHorizontalGroup(
                settingWindowContentPaneLayout.createParallelGroup()
                    .addGroup(settingWindowContentPaneLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(settingWindowContentPaneLayout.createSequentialGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                            .addGroup(settingWindowContentPaneLayout.createSequentialGroup()
                                .addGroup(settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(settingWindowContentPaneLayout.createParallelGroup()
                                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(comboBox2, GroupLayout.Alignment.LEADING)
                                        .addComponent(comboBox1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(44, Short.MAX_VALUE))
            );
            settingWindowContentPaneLayout.setVerticalGroup(
                settingWindowContentPaneLayout.createParallelGroup()
                    .addGroup(settingWindowContentPaneLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(51, Short.MAX_VALUE))
            );
            settingWindow.setSize(355, 280);
            settingWindow.setLocationRelativeTo(null);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JFrame settingWindow;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField textField2;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
