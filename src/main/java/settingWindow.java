import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Thu Nov 30 08:55:23 CST 2023
 */



/**
 * @author zhang
 */
public class settingWindow extends JFrame{
    public settingWindow() {
        initComponents();
        initWindow();
    }

    private void initWindow() {
        textField2.setText(Url);
        textField1.setText(ApiKey);
    }
    private void button2Linter() {
        // TODO 确认按钮监听
        Url = textField2.getText();
        ApiKey = textField1.getText();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        layeredPane1 = new JLayeredPane();
        label1 = new JLabel();
        label2 = new JLabel();
        textField2 = new JTextField();
        textField1 = new JTextField();
        layeredPane2 = new JLayeredPane();
        label3 = new JLabel();
        label4 = new JLabel();
        spinner1 = new JSpinner();
        button1 = new JButton();
        label5 = new JLabel();
        comboBox1 = new JComboBox();
        button4 = new JButton();
        label6 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setTitle("\u8bbe\u7f6e");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/icon-chatgpt.png")).getImage());
        var contentPane = getContentPane();

        //======== layeredPane1 ========
        {
            layeredPane1.setBorder(new TitledBorder("API\u8bbe\u7f6e"));

            //---- label1 ----
            label1.setText("\u63a5\u53e3URL\uff1a");
            layeredPane1.add(label1, JLayeredPane.DEFAULT_LAYER);
            label1.setBounds(new Rectangle(new Point(35, 25), label1.getPreferredSize()));

            //---- label2 ----
            label2.setText("API\uff1a");
            layeredPane1.add(label2, JLayeredPane.DEFAULT_LAYER);
            label2.setBounds(new Rectangle(new Point(35, 70), label2.getPreferredSize()));
            layeredPane1.add(textField2, JLayeredPane.DEFAULT_LAYER);
            textField2.setBounds(110, 20, 200, 25);
            layeredPane1.add(textField1, JLayeredPane.DEFAULT_LAYER);
            textField1.setBounds(110, 60, 200, 25);
        }

        //======== layeredPane2 ========
        {
            layeredPane2.setBorder(new TitledBorder("Chat\u6846\u8bbe\u7f6e"));

            //---- label3 ----
            label3.setText("\u5b57\u4f53");
            layeredPane2.add(label3, JLayeredPane.DEFAULT_LAYER);
            label3.setBounds(45, 30, 30, label3.getPreferredSize().height);

            //---- label4 ----
            label4.setText("\u5b57\u4f53\u989c\u8272");
            layeredPane2.add(label4, JLayeredPane.DEFAULT_LAYER);
            label4.setBounds(190, 70, 50, label4.getPreferredSize().height);
            layeredPane2.add(spinner1, JLayeredPane.DEFAULT_LAYER);
            spinner1.setBounds(245, 25, 70, 25);

            //---- button1 ----
            button1.setText("\u4fee\u6539");
            layeredPane2.add(button1, JLayeredPane.DEFAULT_LAYER);
            button1.setBounds(245, 65, 70, 25);

            //---- label5 ----
            label5.setText("\u80cc\u666f\u989c\u8272");
            layeredPane2.add(label5, JLayeredPane.DEFAULT_LAYER);
            label5.setBounds(new Rectangle(new Point(30, 70), label5.getPreferredSize()));
            layeredPane2.add(comboBox1, JLayeredPane.DEFAULT_LAYER);
            comboBox1.setBounds(90, 25, 70, 25);

            //---- button4 ----
            button4.setText("\u4fee\u6539");
            layeredPane2.add(button4, JLayeredPane.DEFAULT_LAYER);
            button4.setBounds(90, 65, 70, 25);

            //---- label6 ----
            label6.setText("\u5b57\u53f7");
            layeredPane2.add(label6, JLayeredPane.DEFAULT_LAYER);
            label6.setBounds(new Rectangle(new Point(210, 30), label6.getPreferredSize()));
        }

        //---- button2 ----
        button2.setText("\u786e\u8ba4");
        button2.addActionListener(e -> button2Linter());

        //---- button3 ----
        button3.setText("\u53d6\u6d88");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(layeredPane1, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(0, 223, Short.MAX_VALUE)
                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                        .addComponent(layeredPane2))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(layeredPane1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(layeredPane2, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        setSize(375, 325);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLayeredPane layeredPane1;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField2;
    private JTextField textField1;
    private JLayeredPane layeredPane2;
    private JLabel label3;
    private JLabel label4;
    private JSpinner spinner1;
    private JButton button1;
    private JLabel label5;
    private JComboBox comboBox1;
    private JButton button4;
    private JLabel label6;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    // 自定义变量
    public static String Url = "https://api.chatanywhere.com.cn";
    public static String ApiKey = "sk-bvhVMDkimbCNOeIemOS5giGyCa2CAiXIXKHq0t6ho5TrmBnY";
}
