import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.*;
import java.util.Scanner;
/*
 * Created by JFormDesigner on Thu Nov 30 08:55:23 CST 2023
 */



/**
 * @author zhang
 */
public class settingWindow extends JFrame{
    public settingWindow() {
        initComponents();
        if(!loginWindows.checkFileExistence(FILE_PATH)){
            initWindow();
        }else{
            readfile();
        }
        if (!loginWindows.checkFileExistence(FILE_PATH_2)) {
            readthemeFile();
        }
    }


    private void initWindow() {
        textField2.setText(Url);
        textField1.setText(ApiKey);
    }
    private void button2Linter() {
        String url = textField2.getText();
        String apikey = textField1.getText();
        if(url.equals("")||apikey.equals("")) {
            JOptionPane.showMessageDialog(null,"请输入完整信息","错误",JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                settinginfor();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                themeSet();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.setVisible(false);
            this.dispose();
        }
    }
    private void settinginfor() throws IOException {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // 创建父文件夹（如果不存在）
        file.createNewFile(); // 创建文件（如果不存在）
        String url, apikey, theme;
        url = textField2.getText();
        apikey = textField1.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(url + "," + apikey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void themeSet() throws IOException {// TODO 主题文件写
        File file = new File(FILE_PATH_2);
        file.getParentFile().mkdirs(); // 创建父文件夹（如果不存在）
        file.createNewFile(); // 创建文件（如果不存在）
        String theme;
        theme = main.lookAndFeel;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(theme);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readthemeFile() {// TODO 主题文件读
        File file = new File(FILE_PATH_2);
        try {
            // 创建Scanner对象读取文件
            Scanner scanner = new Scanner(file);
            // 使用StringBuilder拼接读取到的数据
            StringBuilder stringBuilder = new StringBuilder();
            // 读取文件内容
            while (scanner.hasNext()) {
                String data = scanner.next();
                // 将字段添加到StringBuilder中
                stringBuilder.append(data);
            }
            // 将StringBuilder的内容赋值给文本框
            String Text = stringBuilder.toString();
            String[] parts = Text.split(",");
            // TODO 主题读取到UI


            // 关闭Scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readfile(){
        File file = new File(FILE_PATH);
        try {
            // 创建Scanner对象读取文件
            Scanner scanner = new Scanner(file);
            // 使用StringBuilder拼接读取到的数据
            StringBuilder stringBuilder = new StringBuilder();
            // 读取文件内容
            while (scanner.hasNext()) {
                String data = scanner.next();
                // 将字段添加到StringBuilder中
                stringBuilder.append(data);
            }
            // 将StringBuilder的内容赋值给文本框
            String Text = stringBuilder.toString();
            String[] parts = Text.split(",");
            //TODO 主界面读取文件赋值
            textField2.setText(parts[0]);
            textField1.setText(parts[1]);
            // 关闭Scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private String localAppDATA=System.getenv("LOCALAPPDATA");
    private final String FILE_PATH = localAppDATA+"\\CIF\\settinginfor";
    private final String FILE_PATH_2 = localAppDATA+"\\CIF\\themeFile";
    private void button3Listen() {
        //
        this.setVisible(false);
        this.dispose();
    }

    private void lookAndFeelBox(ItemEvent e) {
        String lookAndFeel = (String) e.getItem();
        try {
            switch (lookAndFeel) {
                case "Windows": main.lookAndFeel = UIManager.getSystemLookAndFeelClassName(); break;
                case "Arc": main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatArcIJTheme"; break;
                case "Arc - Orange": main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme"; break;
                case "Cyan light": main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme"; break;
                case "Light Flat": main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme"; break;
                case "Solarized Light": main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme";
            }
            themeSet();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        layeredPane1 = new JLayeredPane();
        label1 = new JLabel();
        label2 = new JLabel();
        textField2 = new JTextField();
        textField1 = new JTextField();
        button2 = new JButton();
        button3 = new JButton();
        layeredPane2 = new JLayeredPane();
        label3 = new JLabel();
        comboBox1 = new JComboBox<>();
        label4 = new JLabel();
        comboBox2 = new JComboBox<>();
        label5 = new JLabel();
        spinner1 = new JSpinner();

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
            label1.setBounds(new Rectangle(new Point(20, 25), label1.getPreferredSize()));

            //---- label2 ----
            label2.setText("API\uff1a");
            layeredPane1.add(label2, JLayeredPane.DEFAULT_LAYER);
            label2.setBounds(new Rectangle(new Point(40, 65), label2.getPreferredSize()));
            layeredPane1.add(textField2, JLayeredPane.DEFAULT_LAYER);
            textField2.setBounds(85, 20, 200, 25);
            layeredPane1.add(textField1, JLayeredPane.DEFAULT_LAYER);
            textField1.setBounds(85, 60, 200, 25);
        }

        //---- button2 ----
        button2.setText("\u786e\u8ba4");
        button2.addActionListener(e -> button2Linter());

        //---- button3 ----
        button3.setText("\u53d6\u6d88");
        button3.addActionListener(e -> button3Listen());

        //======== layeredPane2 ========
        {
            layeredPane2.setBorder(new TitledBorder("\u98ce\u683c\u8bbe\u7f6e"));

            //---- label3 ----
            label3.setText("\u4e3b\u9898");
            layeredPane2.add(label3, JLayeredPane.DEFAULT_LAYER);
            label3.setBounds(new Rectangle(new Point(20, 30), label3.getPreferredSize()));

            //---- comboBox1 ----
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "Arc",
                "Arc - Orange",
                "Cyan light",
                "Light Flat",
                "Solarized Light",
                "Windows"
            }));
            comboBox1.addItemListener(e -> lookAndFeelBox(e));
            layeredPane2.add(comboBox1, JLayeredPane.DEFAULT_LAYER);
            comboBox1.setBounds(55, 25, 95, 24);

            //---- label4 ----
            label4.setText("\u5b57\u4f53");
            layeredPane2.add(label4, JLayeredPane.DEFAULT_LAYER);
            label4.setBounds(20, 70, 30, label4.getPreferredSize().height);

            //---- comboBox2 ----
            comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u5fae\u8f6f\u96c5\u9ed1",
                "\u5fae\u8f6f\u96c5\u9ed1 Light",
                "\u534e\u6587\u4e2d\u5b8b",
                "\u534e\u6587\u4eff\u5b8b",
                "\u534e\u6587\u5b8b\u4f53",
                "\u534e\u6587\u5f69\u4e91",
                "\u534e\u6587\u65b0\u9b4f",
                "\u534e\u6587\u6977\u4f53",
                "\u534e\u6587\u7425\u73c0",
                "\u534e\u6587\u7ec6\u9ed1",
                "\u534e\u6587\u884c\u6977",
                "\u534e\u6587\u96b6\u4e66",
                "\u5b8b\u4f53",
                "\u5e7c\u5706",
                "\u65b9\u6b63\u59da\u4f53",
                "\u65b9\u6b63\u8212\u4f53",
                "\u6977\u4f53",
                "\u7b49\u7ebf",
                "\u7b49\u7ebf Light",
                "\u96b6\u4e66",
                "\u9ed1\u4f53"
            }));
            layeredPane2.add(comboBox2, JLayeredPane.DEFAULT_LAYER);
            comboBox2.setBounds(55, 65, 95, 24);

            //---- label5 ----
            label5.setText("\u5b57\u53f7");
            layeredPane2.add(label5, JLayeredPane.DEFAULT_LAYER);
            label5.setBounds(190, 30, 25, label5.getPreferredSize().height);

            //---- spinner1 ----
            spinner1.setModel(new SpinnerNumberModel(12, 8, 36, 1));
            layeredPane2.add(spinner1, JLayeredPane.DEFAULT_LAYER);
            spinner1.setBounds(225, 25, 55, 25);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(layeredPane1, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(0, 204, Short.MAX_VALUE)
                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                        .addComponent(layeredPane2, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(layeredPane1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(layeredPane2, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        setSize(350, 315);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLayeredPane layeredPane1;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField2;
    private JTextField textField1;
    private JButton button2;
    private JButton button3;
    private JLayeredPane layeredPane2;
    private JLabel label3;
    private JComboBox<String> comboBox1;
    private JLabel label4;
    private JComboBox<String> comboBox2;
    private JLabel label5;
    private JSpinner spinner1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    // 自定义变量
    public static String Url = "https://api.chatanywhere.com.cn";
    public static String ApiKey = "sk-bvhVMDkimbCNOeIemOS5giGyCa2CAiXIXKHq0t6ho5TrmBnY";
}
