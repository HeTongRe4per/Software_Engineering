import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
/*
 * Created by JFormDesigner on Thu Dec 14 09:32:45 CST 2023
 */



/**
 * @author zhang
 */
public class about extends JFrame {
    public about() {
        initComponents();
    }

    private void toGithubButton() {
        try {
            // 打开默认浏览器并加载GitHub页面
            Desktop.getDesktop().browse(new URI("https://github.com/HeTongRe4per/Software_Engineering"));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    private void sendEmailButton() {
        try {
            // 使用Desktop类打开默认邮件客户端，并创建新的邮件
            URI mailtoUri = new URI("mailto:" + "zhang_zlf@outlook.com");
            Desktop.getDesktop().mail(mailtoUri);
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    private void label2MouseClicked() {
        egg++;
        if (egg.equals(5)) {
            egg = 0;
            try {
                JOptionPane.showMessageDialog(this, "小彩蛋 (。・ω・。)");
                Desktop.getDesktop().browse(new URI("https://music.163.com/song?id=33367332"));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        layeredPane1 = new JLayeredPane();
        scrollPane1 = new JScrollPane();
        label1 = new JTextArea();
        button1 = new JButton();
        button2 = new JButton();
        label2 = new JLabel();

        //======== this ========
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/icon-chatgpt.png")).getImage());
        setTitle("\u5173\u4e8e");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BorderLayout());

                //======== layeredPane1 ========
                {
                    layeredPane1.setBorder(new EtchedBorder());

                    //======== scrollPane1 ========
                    {

                        //---- label1 ----
                        label1.setText("\u672c\u7a0b\u5e8f\u662f2021\u7ea7\u4fe1\u8ba1\u4e00\u73ed2023\u8f6f\u4ef6\u5de5\u7a0b\u201c\u8a00\u8c08\u667a\u53cb\u201d\u5c0f\u7ec4\u7684\u8bfe\u8bbe\n\u672c\u8f6f\u4ef6\u4f7f\u7528Java\u548cSQL\u8bed\u8a00\u7f16\u5199\uff0c\u5f00\u6e90\u5728github\n\n\u5c0f\u7ec4\u7ec4\u5458\uff1a\n\u53ef\u884c\u6027&\u9879\u76ee\u8fdb\u5ea6:\u5e2d\u7965\uff0c\u5510\u6b63\u9633\n\u9700\u6c42:\u6731\u6052\u575a\uff0c\u738b\u884c\u676d\n\u8bbe\u8ba1:\u8983\u5fd7\u5e06\uff0c\u5218\u9aa5\u6f47\n\u7f16\u7801:\u8bb8\u9e4f\uff0c\u5f20\u529b\uff0c\u5f20\u8d8a\n\u6d4b\u8bd5:\u55bb\u535a\u6587\uff0c\u59da\u96e8\u679c");
                        label1.setWrapStyleWord(true);
                        label1.setLineWrap(true);
                        label1.setBorder(null);
                        label1.setEditable(false);
                        scrollPane1.setViewportView(label1);
                    }
                    layeredPane1.add(scrollPane1, JLayeredPane.DEFAULT_LAYER);
                    scrollPane1.setBounds(5, 5, 350, 155);

                    //---- button1 ----
                    button1.setText("Github");
                    button1.setIcon(new ImageIcon(getClass().getResource("/github-mark-30x30.png")));
                    button1.setFont(new Font("Cascadia Code", Font.PLAIN, 20));
                    button1.setToolTipText("Go to Github Repository");
                    button1.setBorder(null);
                    button1.addActionListener(e -> toGithubButton());
                    layeredPane1.add(button1, JLayeredPane.DEFAULT_LAYER);
                    button1.setBounds(235, 185, 120, 45);

                    //---- button2 ----
                    button2.setText("E-mail");
                    button2.setBorder(null);
                    button2.setFont(new Font("Cascadia Code", Font.PLAIN, 20));
                    button2.setIcon(new ImageIcon(getClass().getResource("/email-30x30.png")));
                    button2.setToolTipText("Send E-mail to Developers");
                    button2.addActionListener(e -> sendEmailButton());
                    layeredPane1.add(button2, JLayeredPane.DEFAULT_LAYER);
                    button2.setBounds(110, 185, 120, 45);

                    //---- label2 ----
                    label2.setIcon(new ImageIcon(getClass().getResource("/painted_eggshell-50x45.png")));
                    label2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            label2MouseClicked();
                        }
                    });
                    layeredPane1.add(label2, JLayeredPane.DEFAULT_LAYER);
                    label2.setBounds(20, 180, 50, 50);
                }
                contentPanel.add(layeredPane1, BorderLayout.CENTER);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(400, 305);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLayeredPane layeredPane1;
    private JScrollPane scrollPane1;
    private JTextArea label1;
    private JButton button1;
    private JButton button2;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    private Integer egg = 0;
}
