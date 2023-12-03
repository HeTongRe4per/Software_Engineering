import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Wed Nov 29 20:58:19 CST 2023
 */



/**
 * @author zhang
 */
public class ChatInterface extends JFrame  {

    public ChatInterface() {
        initComponents();
    }

    private void accountMangeItemListen() {
        //
        new  accMgWindow().setVisible(true);
    }

    private void logoutItemListen() {
        //
        this.setVisible(false);
        this.dispose();
        new loginWindows().setVisible(true);
    }

    private void settingItemListen() {
        //
        new settingWindow().setVisible(true);
    }

    private void sendButtonListen() {
        //
        input = sendPane.getText();
        chatArea.append("用户：\n" + input + "\n");
        sendPane.setText("");
        new chatApiHttpClient();
        chatArea.append("\nChatGPT：\n" + chatApiHttpClient.outputMessage + "\n");
    }

    private void sendPaneKeyPressedListen(KeyEvent e) {
        // TODO add your code here
        int keyCode = e.getKeyCode();
        // 处理特定的按键
        if (keyCode == KeyEvent.VK_ENTER && e.isShiftDown()) {
            sendButtonListen();
        }
    }

    private void sendPaneKeyPressedListen() {
        // TODO add your code here
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        mainMenuBar = new JMenuBar();
        accountMenu = new JMenu();
        accountMangeItem = new JMenuItem();
        logoutItem = new JMenuItem();
        toolMenu = new JMenu();
        settingItem = new JMenuItem();
        helpMenu = new JMenu();
        aboutItem = new JMenuItem();
        snedPanel = new JPanel();
        sendScrollPane = new JScrollPane();
        sendPane = new JTextPane();
        sendButton = new JButton();
        chatScrollPane = new JScrollPane();
        chatArea = new JTextArea();

        //======== this ========
        setTitle("Chat Interface");
        setForeground(Color.black);
        setIconImage(new ImageIcon(getClass().getResource("/icon-chatgpt.png")).getImage());
        setMinimumSize(new Dimension(200, 300));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== mainMenuBar ========
        {

            //======== accountMenu ========
            {
                accountMenu.setText("\u8d26\u53f7");

                //---- accountMangeItem ----
                accountMangeItem.setText("\u8d26\u53f7\u7ba1\u7406");
                accountMangeItem.addActionListener(e -> accountMangeItemListen());
                accountMenu.add(accountMangeItem);
                accountMenu.addSeparator();

                //---- logoutItem ----
                logoutItem.setText("\u9000\u51fa\u767b\u5f55");
                logoutItem.addActionListener(e -> logoutItemListen());
                accountMenu.add(logoutItem);
            }
            mainMenuBar.add(accountMenu);

            //======== toolMenu ========
            {
                toolMenu.setText("\u5de5\u5177");

                //---- settingItem ----
                settingItem.setText("\u8bbe\u7f6e");
                settingItem.addActionListener(e -> settingItemListen());
                toolMenu.add(settingItem);
            }
            mainMenuBar.add(toolMenu);

            //======== helpMenu ========
            {
                helpMenu.setText("\u5e2e\u52a9");

                //---- aboutItem ----
                aboutItem.setText("\u5173\u4e8e");
                helpMenu.add(aboutItem);
            }
            mainMenuBar.add(helpMenu);
        }
        setJMenuBar(mainMenuBar);

        //======== snedPanel ========
        {
            snedPanel.setLayout(new BoxLayout(snedPanel, BoxLayout.X_AXIS));

            //======== sendScrollPane ========
            {

                //---- sendPane ----
                sendPane.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));
                sendPane.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        sendPaneKeyPressedListen();
                    }
                });
                sendScrollPane.setViewportView(sendPane);
            }
            snedPanel.add(sendScrollPane);

            //---- sendButton ----
            sendButton.setIcon(new ImageIcon(getClass().getResource("/send.png")));
            sendButton.setMaximumSize(new Dimension(30, 30));
            sendButton.setMinimumSize(new Dimension(30, 30));
            sendButton.setPreferredSize(new Dimension(30, 30));
            sendButton.addActionListener(e -> sendButtonListen());
            snedPanel.add(sendButton);
        }
        contentPane.add(snedPanel, BorderLayout.SOUTH);

        //======== chatScrollPane ========
        {

            //---- chatArea ----
            chatArea.setEditable(false);
            chatArea.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));
            chatScrollPane.setViewportView(chatArea);
        }
        contentPane.add(chatScrollPane, BorderLayout.CENTER);
        setSize(745, 480);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar mainMenuBar;
    private JMenu accountMenu;
    private JMenuItem accountMangeItem;
    private JMenuItem logoutItem;
    private JMenu toolMenu;
    private JMenuItem settingItem;
    private JMenu helpMenu;
    private JMenuItem aboutItem;
    private JPanel snedPanel;
    private JScrollPane sendScrollPane;
    private JTextPane sendPane;
    private JButton sendButton;
    private JScrollPane chatScrollPane;
    private JTextArea chatArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    // 自定义变量
    static String input = "";
    // 自定义方法
}
