import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * @author zhang RDLS 小样
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            if(settingWindow.checkFileExistence(FILE_PATH)){
                try {
                    readThemeFile();
                    // 设置本机系统外观
                    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    UIManager.setLookAndFeel(lookAndFeel);
                } catch (Exception e) { e.printStackTrace(); }
            } else {
                try {
                    // 设置本机系统外观
                    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    UIManager.setLookAndFeel(lookAndFeel);
                } catch (Exception e) { e.printStackTrace(); }
            }
            if (settingWindow.checkFileExistence(FILE_PATH2)) { readApiFile(); }
            loginWin = new loginWindows();
            loginWin.setVisible(true);
        });
    }

    private static void readThemeFile() {
        // 主题文件读
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
            try {
                // 主题读取到UI
                ChatInterface.font = parts[0];
                ChatInterface.fontSize = Integer.parseInt(parts[1]);
                lookAndFeel = parts[2];
                //System.out.println(lookAndFeel + ChatInterface.font + ChatInterface.fontSize);
            } catch (ArrayIndexOutOfBoundsException e) {
                //System.err.println("数组越界异常: " + e.getMessage());
                // 删除文件
                scanner.close();
                if (file.exists()) {
                    file.delete();
                    //System.out.println("文件已删除: " + FILE_PATH);
                }
            }
            //System.out.println(lookAndFeel + ChatInterface.font + ChatInterface.fontSize);
            // 关闭Scanner
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private static void readCustomizePromptFile() {
        File file = new File(FILE_PATH3);
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
            settingWindow.customizePrompt = stringBuilder.toString();
            // 关闭Scanner
            scanner.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }*/

    private static void readApiFile() {
        File file = new File(FILE_PATH2);
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
            try {
            settingWindow.Url = parts[0];
            settingWindow.ApiKey = parts[1];
            ChatInterface.prompt = parts[2];
            settingWindow.rememberLength = Integer.valueOf(parts[3]);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                scanner.close();
                if (file.exists()) {
                    file.delete();
                }
            }
            // 关闭Scanner
            scanner.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    public static String lookAndFeel = "com.formdev.flatlaf.intellijthemes.FlatArcIJTheme";
    static String localAppDATA=System.getenv("LOCALAPPDATA");
    static String FILE_PATH = localAppDATA+"\\Wise_Conversations\\themeFile";
    static String FILE_PATH2 = localAppDATA+"\\Wise_Conversations\\settingInfo";
    private static final String FILE_PATH3 = localAppDATA+"\\Wise_Conversations\\customizePrompt";
    static loginWindows loginWin;
}
