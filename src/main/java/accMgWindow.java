import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.io.File;
/*
 * Created by JFormDesigner on Thu Nov 30 09:10:35 CST 2023
 */



/**
 * @author zhang
 */
public class accMgWindow extends JFrame {
    private ChatInterface parent;
    public accMgWindow(ChatInterface parent) {
        initComponents();
        this.parent=parent;
        if(loginWindows.checkFileExistence(FILE_PATH)){
            try {
                default_input();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private final String localAppDATA=System.getenv("LOCALAPPDATA");
    private final String FILE_PATH = localAppDATA+"\\CIF\\credentials";
    private int dataOfFile = 0; // 文件字节内容
    private void DecFile(File encFile, File decFile) throws Exception {
        if (!encFile.exists()) {
            System.out.println("加密文件不存在");
            return;
        }

        if (!decFile.exists()) {
            System.out.println("解密文件已创建");
            decFile.createNewFile();
        }

        InputStream fis = new FileInputStream(encFile);
        OutputStream fos = new FileOutputStream(decFile);

        while ((dataOfFile = fis.read()) > -1) {
            // 加密解密秘钥
            int numOfEncAndDec = 0x99;
            fos.write(dataOfFile ^ numOfEncAndDec);
        }

        fis.close();
        fos.flush();
        fos.close();
    }

    private void default_input() throws IOException {
        File file = new File(FILE_PATH);
        File L_file = new File(FILE_PATH+"-L");
        L_file.getParentFile().mkdirs(); // 创建父文件夹（如果不存在）
        L_file.createNewFile(); // 创建文件（如果不存在）
        try {
            DecFile(file,L_file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        readfile(L_file);
        L_file.delete();
    }
    private void readfile(File file){
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
            accountTextField.setText(parts[0]);
            passwordField1.setText(parts[1]);
            passwordField1.setEchoChar('*');
            // 关闭Scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void delAccountListen() {
        //
        new confirmDelAccountWindow(this).setVisible(true);

        delefile();
        parent.dispose();
        dispose();
        new loginWindows().setVisible(true);
    }

    private void resetUserNameListen() {
        //
        String username=accountTextField.getText();
        // TODO 更新数据库

        delefile();
        parent.dispose();
        dispose();
        new loginWindows().setVisible(true);
    }

    private void resetEmailListen() {
        String email=emailTextField.getText();
        // TODO 更新数据库

        delefile();
        parent.dispose();
        dispose();
        new loginWindows().setVisible(true);
    }

    private void resetPasswdListen() {
        //todo 账号邮箱
        new passwdUpdateWindow2("123").setVisible(true);

        delefile();
        parent.dispose();
        dispose();
        new loginWindows().setVisible(true);
    }

    private void delefile(){
        String fp=localAppDATA+"\\CIF";
        File directory = new File(fp);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        delAccountButton = new JButton();
        accountTextField = new JTextField();
        emailTextField = new JTextField();
        resetUserNameButton = new JButton();
        resetEmailButton = new JButton();
        resetPasswdButton = new JButton();
        passwordField1 = new JPasswordField();

        //======== this ========
        setTitle("\u8d26\u53f7\u7ba1\u7406");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/icon-chatgpt.png")).getImage());
        var contentPane = getContentPane();

        //---- label1 ----
        label1.setText("\u7528\u6237\u540d\uff1a");

        //---- label2 ----
        label2.setText("\u90ae\u7bb1\uff1a");

        //---- label3 ----
        label3.setText("\u5bc6\u7801\uff1a");

        //---- delAccountButton ----
        delAccountButton.setText("\u5220\u9664\u8d26\u6237");
        delAccountButton.addActionListener(e -> delAccountListen());

        //---- resetUserNameButton ----
        resetUserNameButton.setText("\u4fee\u6539\u7528\u6237\u540d");
        resetUserNameButton.addActionListener(e -> resetUserNameListen());

        //---- resetEmailButton ----
        resetEmailButton.setText("\u4fee\u6539\u90ae\u7bb1");
        resetEmailButton.addActionListener(e -> resetEmailListen());

        //---- resetPasswdButton ----
        resetPasswdButton.setText("\u4fee\u6539\u5bc6\u7801");
        resetPasswdButton.addActionListener(e -> resetPasswdListen());

        //---- passwordField1 ----
        passwordField1.setText("******");
        passwordField1.setEchoChar('*');
        passwordField1.setEditable(false);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(accountTextField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resetUserNameButton))
                            .addComponent(delAccountButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addComponent(passwordField1)
                                .addGap(10, 10, 10)
                                .addComponent(resetPasswdButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(resetEmailButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(accountTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(resetUserNameButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(resetEmailButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addComponent(resetPasswdButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                    .addComponent(delAccountButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27))
        );
        setSize(395, 275);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JButton delAccountButton;
    private JTextField accountTextField;
    private JTextField emailTextField;
    private JButton resetUserNameButton;
    private JButton resetEmailButton;
    private JButton resetPasswdButton;
    private JPasswordField passwordField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
