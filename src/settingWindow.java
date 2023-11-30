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
        comboBox2 = new JComboBox();
        spinner1 = new JSpinner();

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
                        .addContainerGap(38, Short.MAX_VALUE)
                        .addGroup(settingWindowContentPaneLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label2, GroupLayout.Alignment.TRAILING))
                            .addComponent(label1, GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(settingWindowContentPaneLayout.createParallelGroup()
                            .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinner1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46))
            );
            settingWindowContentPaneLayout.setVerticalGroup(
                settingWindowContentPaneLayout.createParallelGroup()
                    .addGroup(settingWindowContentPaneLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1))
                        .addGap(18, 18, 18)
                        .addGroup(settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2))
                        .addGap(18, 18, 18)
                        .addGroup(settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinner1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(settingWindowContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(90, Short.MAX_VALUE))
            );
            settingWindow.setSize(355, 315);
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
    private JComboBox comboBox2;
    private JSpinner spinner1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
