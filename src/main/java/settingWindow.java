import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
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
            //TODO 读取文件赋值url和apikey

        }
    }


    private void initWindow() {
        textField2.setText(Url);
        textField1.setText(ApiKey);

    }
    private void button2Linter() {
        // TODO 判定输入链接格式和APIKEY格式,没问题则建立文件

        try {
            settinginfor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setVisible(false);
        this.dispose();
    }
    private void settinginfor() throws IOException {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // 创建父文件夹（如果不存在）
        file.createNewFile(); // 创建文件（如果不存在）
        String url,apikey;
        url = textField2.getText();
        apikey = textField1.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(url + "," + apikey);
        } catch (IOException e) {
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
            //textField2.setText(parts[0]);
            //textField2.setText(parts[1]);
            // 关闭Scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private String localAppDATA=System.getenv("LOCALAPPDATA");
    private final String FILE_PATH = localAppDATA+"\\CIF\\settinginfor";
    private void button3Listen() {
        //
        this.setVisible(false);
        this.dispose();
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

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(layeredPane1, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(0, 198, Short.MAX_VALUE)
                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(layeredPane1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        setSize(350, 205);
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    // 自定义变量
    public static String Url = "https://api.chatanywhere.com.cn";
    public static String ApiKey = "sk-bvhVMDkimbCNOeIemOS5giGyCa2CAiXIXKHq0t6ho5TrmBnY";
}
