import javax.swing.*;
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
		sendPaneEmpty();
        if(checkFileExistence(FILE_PATH)){
            if(readbool()){
                chatArea.setBackground(new Color(48, 48, 48, 255)); // Set background color to gray with alpha channel
                chatArea.setForeground(Color.white);
                sendPane.setBackground(new Color(48, 48, 48, 255)); // Set background color to gray with alpha channel
                sendPane.setForeground(Color.white);
            }else {
                chatArea.setBackground(Color.white);
                chatArea.setForeground(new Color(48, 48, 48, 255));
                sendPane.setBackground(Color.white);
                sendPane.setForeground(new Color(48, 48, 48, 255));
            }
            isdark=readbool();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void accountMangeItemListen() {
		//
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
			chatArea.append("\n用户：\n" + inputMessage + "\n");
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
			//sendPane.setForeground(Color.BLACK);
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
        // TODO add your code here
        if (!isdark) {
            chatArea.setBackground(new Color(48, 48, 48, 255)); // Set background color to gray with alpha channel
            chatArea.setForeground(Color.white);
            sendPane.setBackground(new Color(48, 48, 48, 255)); // Set background color to gray with alpha channel
            sendPane.setForeground(Color.white);
        } else {
            chatArea.setBackground(Color.white);
            chatArea.setForeground(new Color(48, 48, 48, 255));
            sendPane.setBackground(Color.white);
            sendPane.setForeground(new Color(48, 48, 48, 255));
        }
        isdark = !isdark;
        boolisdark();
    }
    private boolean isdark=false;
    private String localAppDATA=System.getenv("LOCALAPPDATA");
    private final String FILE_PATH = localAppDATA+"\\CIF\\isdark";
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
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        mainMenuBar = new JMenuBar();
        accountMenu = new JMenu();
        accountMangeItem = new JMenuItem();
        logoutItem = new JMenuItem();
        toolMenu = new JMenu();
        settingItem = new JMenuItem();
        changemodel = new JMenuItem();
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
                toolMenu.addSeparator();

                //---- changemodel ----
                changemodel.setText("\u5207\u6362\u80cc\u666f\u8272");
                changemodel.addActionListener(e -> changemodelListen());
                toolMenu.add(changemodel);
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
                        .addComponent(chatScrollPane, GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
                        .addComponent(snedPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addComponent(chatScrollPane, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
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
    private JMenuItem changemodel;
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
	static String inputMessage = "";
	boolean sendButtonFlag = true;
	// 自定义方法
}
