import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;
/*
 * Created by JFormDesigner on Thu Nov 30 08:55:23 CST 2023
 */



/**
 * @author zhang RDLS
 */
public class settingWindow extends JFrame{
    public settingWindow() {
        initComponents();
        OriginalColor = textArea1.getForeground();
        if(!loginWindows.checkFileExistence(FILE_PATH)){
            initWindow();
            settingLength = 15;
        }else{
            readApiFile();
        }
        if (loginWindows.checkFileExistence(FILE_PATH_2)) {
            readThemeFile();
        } else checkThemeChange1 = "Arc";
        if (checkFileExistence(FILE_PATH_3)) {
            readCustomizePromptFile();
            textArea1.setText(customizePromptContent);
            checkPromptChange1 = ChatInterface.prompt;
        } else checkPromptChange1 = "默认";
        if (Objects.equals(ChatInterface.prompt, "自定义")) {
            textArea1.setEditable(true);
        } else {
            initPromptArea();
        }
    }

    private void initWindow() {
        textField2.setText(Url);
        textField1.setText(ApiKey);
    }

    private void settingInfo() throws IOException { // API写
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // 创建父文件夹（如果不存在）
        file.createNewFile(); // 创建文件（如果不存在）
        String url, apikey;
        url = textField2.getText();
        apikey = textField1.getText();
        ChatInterface.prompt = (String) comboBox3.getSelectedItem();
        settingLength = Integer.valueOf(spinner2.getValue().toString());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(url + "," + apikey + "," + ChatInterface.prompt  + "," + settingLength);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void readApiFile() {
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
            //主界面读取文件赋值
            textField2.setText(parts[0]);
            textField1.setText(parts[1]);
            comboBox3.setSelectedItem(parts[2]);
            spinner2.setValue(Integer.parseInt(parts[3]));
            // 关闭Scanner
            scanner.close();
        } catch (FileNotFoundException | NumberFormatException e) { e.printStackTrace(); }
    }

    private void themeWrite() throws IOException {
        // 主题文件写
        File file = new File(FILE_PATH_2);
        file.getParentFile().mkdirs(); // 创建父文件夹（如果不存在）
        file.createNewFile(); // 创建文件（如果不存在）
        String theme = Main.lookAndFeel;
        String font = settingFont;
        Integer fontSz = settingFontSize;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(font+ "," + fontSz + "," + theme);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readThemeFile() {
        // 主题文件读
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
            String font = parts[0];
            Integer fontSize = Integer.parseInt(parts[1]);
            String lookAndFeel = parts[2];
            String settingTheme = "com.formdev.flatlaf.intellijthemes.FlatArcIJTheme";
            switch (lookAndFeel) {
                case "com.sun.java.swing.plaf.windows.WindowsLookAndFeel": settingTheme = "Windows"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatArcIJTheme": settingTheme = "Arc"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme": settingTheme = "Arc - Orange"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme": settingTheme = "Arc Dark"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme": settingTheme = "Arc Dark - Orange"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme": settingTheme = "Carbon"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme": settingTheme = "Cobalt 2"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme": settingTheme = "Cyan light"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme": settingTheme = "Dark Flat"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme": settingTheme = "Dark purple"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme": settingTheme = "Dracula"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatGradiantoDarkFuchsiaIJTheme": settingTheme = "Gradianto Dark Fuchsia"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme": settingTheme = "Gradianto Deep Ocean"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme": settingTheme = "Gradianto Midnight Blue"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme": settingTheme = "Gradianto Nature Green"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatGrayIJTheme": settingTheme = "Gray"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme": settingTheme = "Gruvbox Dark Hard"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkMediumIJTheme": settingTheme = "Gruvbox Dark Medium"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkSoftIJTheme": settingTheme = "Gruvbox Dark Soft"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatHiberbeeDarkIJTheme": settingTheme = "Hiberbee Dark"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatHighContrastIJTheme": settingTheme = "High contrast"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme": settingTheme = "Light Flat"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme": settingTheme = "Material Design Dark"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatMonocaiIJTheme": settingTheme = "Monocai"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme": settingTheme = "Monokai Pro"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatNordIJTheme": settingTheme = "Nord"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme": settingTheme = "One Dark"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme": settingTheme = "Solarized Dark"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme": settingTheme = "Solarized Light"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatSpacegrayIJTheme": settingTheme = "Spacegray"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme": settingTheme = "Vuesion"; break;
                case "com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme": settingTheme = "Xcode-Dark";
            }
            checkThemeChange1 = settingTheme;
            // 主题读取到UI
            comboBox2.setSelectedItem(font);
            comboBox1.setSelectedItem(settingTheme);
            spinner1.setValue(fontSize);
            // 关闭Scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeCustomizePrompt() throws IOException { // 自定义写
        File file = new File(FILE_PATH_3);
        file.getParentFile().mkdirs(); // 创建父文件夹（如果不存在）
        file.createNewFile(); // 创建文件（如果不存在）
        String temp = customizePromptContent;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(temp);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void readCustomizePromptFile() {
        File file = new File(FILE_PATH_3);
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
            customizePromptContent = stringBuilder.toString();
            // 关闭Scanner
            scanner.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    private void button3Listen() { // 取消
        loginWindows.mainWin.setEnabled(true);
        this.dispose();
    }

    private void button2Linter() { // 确认
        String url = textField2.getText();
        String apikey = textField1.getText();
        String settingCustomPrompt = textArea1.getText();
        String checkThemeChange2 = (String) comboBox1.getSelectedItem();
        ChatInterface.prompt = (String) comboBox3.getSelectedItem();
        settingLength = Integer.valueOf(spinner2.getValue().toString());
        settingFont = (String) comboBox2.getSelectedItem();
        settingFontSize = Integer.valueOf(spinner1.getValue().toString());
        String checkPromptChange2 = ChatInterface.prompt;
        ChatInterface.font = settingFont;
        ChatInterface.fontSize = settingFontSize;
        ChatInterface.refreshWin();
        if(!url.matches("^(?:https?://)?(?:(?:[A-Za-z0-9]+(?:-[A-Za-z0-9]+)*\\.)+[A-Za-z]{2,}|localhost|\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(?::\\d+)?(?:/\\S*)?$" )) {
            JOptionPane.showMessageDialog(null, "URL格式错误，请检查", "错误", JOptionPane.ERROR_MESSAGE);
        } else if (!apikey.matches("sk-[a-zA-Z0-9]{48}")) {
            JOptionPane.showMessageDialog(null, "API格式错误，请检查", "错误", JOptionPane.ERROR_MESSAGE);
        } else if (Objects.equals(ChatInterface.prompt, "自定义") && (settingCustomPrompt.isEmpty() || Objects.equals(settingCustomPrompt, "自定义人格，“人格”下拉框选择“自定义”时有效"))) {
            JOptionPane.showMessageDialog(null, "自定义人格不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
        } else {
            ChatInterface.promptContent = textArea1.getText();
            try {
                settingInfo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                themeWrite();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (!Objects.equals(checkThemeChange1, checkThemeChange2))
            {
                JOptionPane.showMessageDialog(this,"主题修改需要重启应用","提示",JOptionPane.INFORMATION_MESSAGE);
            }
            if (!Objects.equals(checkPromptChange1, checkPromptChange2)) {
                JOptionPane.showMessageDialog(this,"修改人格将新开对话","提示",JOptionPane.INFORMATION_MESSAGE);
                ChatInterface.resetChat();
            } try {
                writeCustomizePrompt();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //System.out.println(checkThemeChange1 + " | " + checkThemeChange2);
            loginWindows.mainWin.setEnabled(true);
            this.setVisible(false);
            this.dispose();
        }
    }

    private void lookAndFeelBox() {
        //String lookAndFeel = (String) e.getItem();
        String lookAndFeel = (String) comboBox1.getSelectedItem();
        try {
            if (lookAndFeel != null) {
                switch (lookAndFeel) {
                    case "Arc":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatArcIJTheme"; break;
                    case "Arc - Orange":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme"; break;
                    case "Arc Dark":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme"; break;
                    case "Arc Dark - Orange":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme"; break;
                    case "Carbon":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme"; break;
                    case "Cobalt 2":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme"; break;
                    case "Cyan light":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme"; break;
                    case "Dark Flat":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme"; break;
                    case "Dark purple":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme"; break;
                    case "Dracula":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme"; break;
                    case "Gradianto Dark Fuchsia":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatGradiantoDarkFuchsiaIJTheme"; break;
                    case "Gradianto Deep Ocean":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme"; break;
                    case "Gradianto Midnight Blue":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme"; break;
                    case "Gradianto Nature Green":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatGradiantoNatureGreenIJTheme"; break;
                    case "Gray":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatGrayIJTheme"; break;
                    case "Gruvbox Dark Hard":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme"; break;
                    case "Gruvbox Dark Medium":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkMediumIJTheme"; break;
                    case "Gruvbox Dark Soft":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkSoftIJTheme"; break;
                    case "Hiberbee Dark":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatHiberbeeDarkIJTheme"; break;
                    case "High contrast":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatHighContrastIJTheme"; break;
                    case "Light Flat":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme"; break;
                    case "Material Design Dark":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme"; break;
                    case "Monocai":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatMonocaiIJTheme"; break;
                    case "Monokai Pro":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme"; break;
                    case "Nord":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatNordIJTheme"; break;
                    case "One Dark":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme"; break;
                    case "Solarized Dark":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme"; break;
                    case "Solarized Light":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme"; break;
                    case "Spacegray":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatSpacegrayIJTheme"; break;
                    case "Vuesion":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme"; break;
                    case "Xcode-Dark":
                        Main.lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme"; break;
                    case "Windows":
                        Main.lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                }
            }
            themeWrite();
            //System.out.println("Theme: " + lookAndFeel);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void thisWindowClosing() {
        loginWindows.mainWin.setEnabled(true);
    }

    private void initPromptArea() {
        textArea1.setText("自定义人格，“人格”下拉框选择“自定义”时有效");
        textArea1.setForeground(Color.GRAY);
        textArea1.setEditable(false);
    }

    private void textArea1FocusGained() {
        if (textArea1.getText().equals("自定义人格，“人格”下拉框选择“自定义”时有效")) {
            textArea1.setText("");
            textArea1.setForeground(OriginalColor);
        }
    }

    private void textArea1FocusLost() {
        if (textArea1.getText().isEmpty()) {
            textArea1.setText("自定义人格，“人格”下拉框选择“自定义”时有效");
            textArea1.setForeground(Color.GRAY);
        }
    }

    static boolean checkFileExistence(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    private void comboBox3ItemStateChanged() {
        // 获取当前选择
        Object selectedItem = comboBox3.getSelectedItem();

        // 检查是否是 "自定义"，设置 textArea1 的可编辑状态
        textArea1.setEditable(Objects.equals(selectedItem, "自定义"));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        layeredPane1 = new JLayeredPane();
        label1 = new JLabel();
        label2 = new JLabel();
        textField2 = new JTextField();
        textField1 = new JTextField();
        comboBox3 = new JComboBox<>();
        label6 = new JLabel();
        label7 = new JLabel();
        spinner2 = new JSpinner();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        label8 = new JLabel();
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing();
            }
        });
        var contentPane = getContentPane();

        //======== layeredPane1 ========
        {
            layeredPane1.setBorder(new TitledBorder("API\u8bbe\u7f6e"));

            //---- label1 ----
            label1.setText("\u63a5\u53e3URL\uff1a");
            layeredPane1.add(label1, JLayeredPane.DEFAULT_LAYER);
            label1.setBounds(new Rectangle(new Point(30, 25), label1.getPreferredSize()));

            //---- label2 ----
            label2.setText("API\uff1a");
            layeredPane1.add(label2, JLayeredPane.DEFAULT_LAYER);
            label2.setBounds(new Rectangle(new Point(60, 65), label2.getPreferredSize()));
            layeredPane1.add(textField2, JLayeredPane.DEFAULT_LAYER);
            textField2.setBounds(100, 20, 200, textField2.getPreferredSize().height);
            layeredPane1.add(textField1, JLayeredPane.DEFAULT_LAYER);
            textField1.setBounds(100, 60, 200, textField1.getPreferredSize().height);

            //---- comboBox3 ----
            comboBox3.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u9ed8\u8ba4",
                "\u82f1\u8bed\u7ffb\u8bd1\u548c\u6539\u8fdb\u8005",
                "\u82f1\u82f1\u8bcd\u5178(\u9644\u4e2d\u6587\u89e3\u91ca)",
                "\u63d0\u793a\u751f\u6210\u5668",
                "\u6b63\u5219\u751f\u6210\u5668",
                "\u7b26\u53f7\u8bbe\u8ba1",
                "\u6587\u7ae0\u7eed\u5199",
                "\u6587\u5b57\u5192\u9669\u6e38\u620f",
                "\u6d77\u7ef5\u5b9d\u5b9d\u7684\u795e\u5947\u6d77\u87ba",
                "\u5c0f\u8bf4\u5bb6",
                "\u5360\u661f\u5e08",
                "Loris",
                "\u81ea\u5b9a\u4e49"
            }));
            comboBox3.addItemListener(e -> comboBox3ItemStateChanged());
            layeredPane1.add(comboBox3, JLayeredPane.DEFAULT_LAYER);
            comboBox3.setBounds(55, 100, 95, 25);

            //---- label6 ----
            label6.setText("\u4eba\u683c");
            layeredPane1.add(label6, JLayeredPane.DEFAULT_LAYER);
            label6.setBounds(new Rectangle(new Point(20, 105), label6.getPreferredSize()));

            //---- label7 ----
            label7.setText("\u8bb0\u5fc6\u957f\u5ea6");
            layeredPane1.add(label7, JLayeredPane.DEFAULT_LAYER);
            label7.setBounds(new Rectangle(new Point(175, 105), label7.getPreferredSize()));

            //---- spinner2 ----
            spinner2.setModel(new SpinnerNumberModel(15, 1, 30, 1));
            spinner2.setToolTipText("\u4fee\u6539AI\u8bb0\u5fc6\u957f\u5ea6\uff0c\u5efa\u8bae10~15");
            layeredPane1.add(spinner2, JLayeredPane.DEFAULT_LAYER);
            spinner2.setBounds(230, 100, 70, 25);

            //======== scrollPane1 ========
            {

                //---- textArea1 ----
                textArea1.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        textArea1FocusGained();
                    }
                    @Override
                    public void focusLost(FocusEvent e) {
                        textArea1FocusLost();
                    }
                });
                scrollPane1.setViewportView(textArea1);
            }
            layeredPane1.add(scrollPane1, JLayeredPane.DEFAULT_LAYER);
            scrollPane1.setBounds(35, 155, 265, 65);

            //---- label8 ----
            label8.setText("\u81ea\u5b9a\u4e49\u4eba\u683c\uff1a");
            layeredPane1.add(label8, JLayeredPane.DEFAULT_LAYER);
            label8.setBounds(new Rectangle(new Point(20, 135), label8.getPreferredSize()));
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
                "Arc Dark",
                "Arc Dark - Orange",
                "Carbon",
                "Cobalt 2",
                "Cyan light",
                "Dark Flat",
                "Dark purple",
                "Dracula",
                "Gradianto Dark Fuchsia",
                "Gradianto Deep Ocean",
                "Gradianto Midnight Blue",
                "Gradianto Nature Green",
                "Gray",
                "Gruvbox Dark Hard",
                "Gruvbox Dark Medium",
                "Gruvbox Dark Soft",
                "Hiberbee Dark",
                "High contrast",
                "Light Flat",
                "Material Design Dark",
                "Monocai",
                "Monokai Pro",
                "Nord",
                "One Dark",
                "Solarized Dark",
                "Solarized Light",
                "Spacegray",
                "Vuesion",
                "Windows",
                "Xcode-Dark"
            }));
            comboBox1.setSelectedIndex(0);
            comboBox1.addItemListener(e -> lookAndFeelBox());
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
            label5.setBounds(195, 30, 25, label5.getPreferredSize().height);

            //---- spinner1 ----
            spinner1.setModel(new SpinnerNumberModel(14, 8, 36, 1));
            layeredPane2.add(spinner1, JLayeredPane.DEFAULT_LAYER);
            spinner1.setBounds(230, 25, 70, 25);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(0, 204, Short.MAX_VALUE)
                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                        .addComponent(layeredPane2, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                        .addComponent(layeredPane1, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(layeredPane1, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(layeredPane2, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(button3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        setSize(350, 450);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLayeredPane layeredPane1;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField2;
    private JTextField textField1;
    private JComboBox<String> comboBox3;
    private JLabel label6;
    private JLabel label7;
    private JSpinner spinner2;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JLabel label8;
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

    private final String localAppDATA=System.getenv("LOCALAPPDATA");
    private final String FILE_PATH = localAppDATA+"\\Wise_Conversations\\settingInfo";
    private final String FILE_PATH_2 = localAppDATA+"\\Wise_Conversations\\themeFile";
    private final String FILE_PATH_3 = localAppDATA+"\\Wise_Conversations\\customizePrompt";
    private String settingFont = "微软雅黑";
    private Integer settingFontSize =14;
    static String Url = "https://api.chatanywhere.com.cn";
    static String ApiKey = "sk-bvhVMDkimbCNOeIemOS5giGyCa2CAiXIXKHq0t6ho5TrmBnY";
    static Integer rememberLength = 15;
    static Integer settingLength;
    private String checkThemeChange1;
    private final String checkPromptChange1;
    private final Color OriginalColor;
    static String customizePromptContent;
}
