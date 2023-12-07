import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import javax.swing.*;
/*
 * Created by JFormDesigner on Wed Nov 29 20:58:19 CST 2023
 */



/**
 * @author zhang xp
 */
public class ChatInterface extends JFrame  {

    public ChatInterface() {
        initComponents();
        sendPaneEmpty();
    }

    private void accountMangeItemListen() {
        //
        new accMgWindow().setVisible(true);
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
        if (sendButtonFlag) {
            input = sendPane.getText();
            chatArea.append("\n用户：\n" + input + "\n");
            sendPane.setText("");
            sendButton.setEnabled(false);   // 发送消息后禁止再点击发送
            sendButtonFlag =false;  // 锁定按钮监听

            // 异步执行 chatApiHttpClient
            CompletableFuture.supplyAsync(chatApiHttpClient::new)
                    .thenAcceptAsync(chatApiHttpClient -> {
                        chatArea.append("\nChatGPT：\n" + chatApiHttpClient.outputMessage + "\n");
                        sendButton.setEnabled(true);    // 解除发送按钮锁定
                        sendButtonFlag = true;  // 解除按钮监听锁定
                    });
        }
    }

    private void sendPaneEmpty() {
        sendPane.setText(initSendText);
        sendPane.setForeground(Color.GRAY);
    }

    private void sendPaneKeyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        // 处理特定的按键
        if (keyCode == KeyEvent.VK_ENTER && e.isControlDown()) {
            sendButtonListen();
        }
    }

    private void sendPaneFocusGained() {
        String temp = sendPane.getText();
        if (temp.equals(initSendText)) {
            sendPane.setForeground(Color.BLACK);
            sendPane.setText("");
        }
    }

    private void sendPaneFocusLost() {
        String temp = sendPane.getText();
        if (temp.isEmpty()) {
            sendPaneEmpty();
        }
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

            //======== sendScrollPane ========
            {

                //---- sendPane ----
                sendPane.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));
                sendPane.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        sendPaneKeyPressed(e);
                    }
                });
                sendPane.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        sendPaneFocusGained();
                    }
                    @Override
                    public void focusLost(FocusEvent e) {
                        sendPaneFocusLost();
                    }
                });
                sendScrollPane.setViewportView(sendPane);
            }

            //---- sendButton ----
            sendButton.setIcon(new ImageIcon(getClass().getResource("/send.png")));
            sendButton.setMaximumSize(new Dimension(30, 30));
            sendButton.setMinimumSize(new Dimension(30, 30));
            sendButton.setPreferredSize(new Dimension(30, 30));
            sendButton.addActionListener(e -> sendButtonListen());

            GroupLayout snedPanelLayout = new GroupLayout(snedPanel);
            snedPanel.setLayout(snedPanelLayout);
            snedPanelLayout.setHorizontalGroup(
                snedPanelLayout.createParallelGroup()
                    .addGroup(snedPanelLayout.createSequentialGroup()
                        .addComponent(sendScrollPane, GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
            );
            snedPanelLayout.setVerticalGroup(
                snedPanelLayout.createParallelGroup()
                    .addGroup(snedPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(snedPanelLayout.createParallelGroup()
                            .addComponent(sendScrollPane)
                            .addGroup(snedPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
            );
        }

        //======== chatScrollPane ========
        {

            //---- chatArea ----
            chatArea.setEditable(false);
            chatArea.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));
            chatScrollPane.setViewportView(chatArea);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(chatScrollPane, GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                        .addComponent(snedPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(chatScrollPane, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(snedPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        setSize(845, 555);
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
    final String initSendText = "Message ChatGPT...";
    static String input = "";
    boolean sendButtonFlag = true;
    // 自定义方法
}
