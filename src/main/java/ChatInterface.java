import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
/*
 * Created by JFormDesigner on Thu Dec 07 23:57:49 CST 2023
 */



/**
 * @author zhang xp
 */
public class ChatInterface extends JFrame  {

    public ChatInterface() {
		initComponents();
        if(checkFileExistence(FILE_PATH)){
            if(readbool()) darkMode();
            else lightMode();
            isdark=readbool();
        }
        sendPaneEmpty();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatArea.requestFocus();
	}

	public void accountMangeItemListen() {
        accMgWindow ac =new accMgWindow(ChatInterface.this);
		ac.setVisible(true);
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
			inputMessage = sendPane.getText();

            String user = loginWindows.username_s;
            System.out.println(user);
			chatArea.append(user + "：\n" + inputMessage + "\n");
			sendPane.setText("");
			sendButton.setEnabled(false);   // 发送消息后禁止再点击发送
			sendButtonFlag =false;  // 锁定按钮监听

			// 异步执行 chatApiHttpClient
			CompletableFuture.supplyAsync(chatApiHttpClient::new)
					.thenAcceptAsync(chatApiHttpClient -> {
						chatArea.append("\nChatGPT：\n" + chatApiHttpClient.outputMessage + "\n\n");
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
			sendButton.doClick();
		}
	}

	private void sendPaneFocusGained() {
		String temp = sendPane.getText();
		if (temp.equals(initSendText)) {
            if (!isdark) sendPane.setForeground(Color.BLACK);
            else sendPane.setForeground(Color.WHITE);
			sendPane.setText("");
		}
	}

	private void sendPaneFocusLost() {
		String temp = sendPane.getText();
		if (temp.isEmpty()) {
			sendPaneEmpty();
		}
	}

    private void changemodelListen() {
        if (!isdark) darkMode();
        else lightMode();
        isdark = !isdark;
        sendPane.requestFocus();
        boolisdark();
    }

    private void darkMode() {
        chatArea.setBackground(new Color(48, 48, 48, 255));
        chatArea.setForeground(Color.WHITE);
        sendPane.setBackground(new Color(48, 48, 48, 255));
        sendPane.setForeground(Color.WHITE);
        sendPane.setCaretColor(Color.WHITE);
    }

    private void lightMode() {
        chatArea.setBackground(Color.WHITE);
        chatArea.setForeground(new Color(48, 48, 48, 255));
        sendPane.setBackground(Color.WHITE);
        sendPane.setForeground(new Color(48, 48, 48, 255));
        sendPane.setCaretColor(Color.BLACK);
    }

    private void boolisdark(){
        File file=new File(FILE_PATH);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.valueOf(isdark));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean readbool(){
        boolean value=false;
        File file=new File(FILE_PATH);
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
            value = Boolean.parseBoolean(Text);
            // 关闭Scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }
    private boolean checkFileExistence(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    private void aboutItemLister() {
        new about().setVisible(true);
    }

    private void chatWinKeyPressed(KeyEvent e) {
        // TODO 主窗口ESC快捷键终止主进程
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    private void resetChat() {
        chatAPI.resetInputString();
        chatArea.setText("");
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        mainMenuBar = new JMenuBar();
        accountMenu = new JMenu();
        accountMangeItem = new JMenuItem();
        logoutItem = new JMenuItem();
        toolMenu = new JMenu();
        changemodel = new JMenuItem();
        menuItem1 = new JMenuItem();
        settingItem = new JMenuItem();
        helpMenu = new JMenu();
        aboutItem = new JMenuItem();
        chatScrollPane = new JScrollPane();
        chatArea = new JTextArea();
        sendPanel = new JPanel();
        sendScrollPane = new JScrollPane();
        sendPane = new JTextPane();
        sendButton = new JButton();

        //======== this ========
        setTitle("Chat Interface");
        setForeground(Color.black);
        setIconImage(new ImageIcon(getClass().getResource("/icon-chatgpt.png")).getImage());
        setMinimumSize(new Dimension(200, 300));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                chatWinKeyPressed(e);
            }
        });
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

                //---- changemodel ----
                changemodel.setText("\u5207\u6362\u80cc\u666f\u8272");
                changemodel.addActionListener(e -> changemodelListen());
                toolMenu.add(changemodel);

                //---- menuItem1 ----
                menuItem1.setText("\u91cd\u5f00\u804a\u5929");
                menuItem1.addActionListener(e -> resetChat());
                toolMenu.add(menuItem1);
                toolMenu.addSeparator();

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
                aboutItem.addActionListener(e -> aboutItemLister());
                helpMenu.add(aboutItem);
            }
            mainMenuBar.add(helpMenu);
        }
        setJMenuBar(mainMenuBar);

        //======== chatScrollPane ========
        {

            //---- chatArea ----
            chatArea.setEditable(false);
            chatArea.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));
            chatArea.setBorder(new BevelBorder(BevelBorder.LOWERED));
            chatArea.setLineWrap(true);
            chatScrollPane.setViewportView(chatArea);
        }

        //======== sendPanel ========
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
            sendButton.setMaximumSize(null);
            sendButton.setMinimumSize(null);
            sendButton.setPreferredSize(null);
            sendButton.addActionListener(e -> sendButtonListen());

            GroupLayout sendPanelLayout = new GroupLayout(sendPanel);
            sendPanel.setLayout(sendPanelLayout);
            sendPanelLayout.setHorizontalGroup(
                sendPanelLayout.createParallelGroup()
                    .addGroup(sendPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(sendPanelLayout.createParallelGroup()
                            .addComponent(sendScrollPane, GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.TRAILING, sendPanelLayout.createSequentialGroup()
                                .addGap(0, 792, Short.MAX_VALUE)
                                .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
            );
            sendPanelLayout.setVerticalGroup(
                sendPanelLayout.createParallelGroup()
                    .addGroup(sendPanelLayout.createSequentialGroup()
                        .addContainerGap(10, Short.MAX_VALUE)
                        .addComponent(sendScrollPane, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(sendPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(chatScrollPane, GroupLayout.PREFERRED_SIZE, 880, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(23, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(chatScrollPane, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(sendPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        setSize(915, 640);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar mainMenuBar;
    private JMenu accountMenu;
    private JMenuItem accountMangeItem;
    private JMenuItem logoutItem;
    private JMenu toolMenu;
    private JMenuItem changemodel;
    private JMenuItem menuItem1;
    private JMenuItem settingItem;
    private JMenu helpMenu;
    private JMenuItem aboutItem;
    private JScrollPane chatScrollPane;
    private JTextArea chatArea;
    private JPanel sendPanel;
    private JScrollPane sendScrollPane;
    private JTextPane sendPane;
    private JButton sendButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

	// 自定义变量
	final String initSendText = "Message ChatGPT...";
	static String inputMessage = "";
	boolean sendButtonFlag = true;
    private boolean isdark=false;
    private String localAppDATA=System.getenv("LOCALAPPDATA");
    private final String FILE_PATH = localAppDATA+"\\CIF\\isdark";

	// 自定义方法
}
