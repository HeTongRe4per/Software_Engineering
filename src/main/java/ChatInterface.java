import com.jgoodies.forms.factories.Borders;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.concurrent.CompletableFuture;
/*
 * Created by JFormDesigner on Thu Dec 07 23:57:49 CST 2023
 */



/**
 * @author zhang RDLS 小样
 */
public class ChatInterface extends JFrame  {
    public ChatInterface() {
		initComponents();
        OriginalColor = sendPanel.getForeground();
        chatArea.setOpaque(false);
        chatScrollPane.setOpaque(false);
        chatScrollPane.getViewport().setOpaque(false);
        sendPaneEmpty();
        refreshWin();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    private String Chatname(String name){
        // 判断是根据 username_mail 还是 email 查询
        String query;
        String columnToUpdate;
        String otherColumn;
        if (!isUsernameMail(name)) {
            query = "SELECT * FROM user WHERE username_mail = ?";
            columnToUpdate = "username_mail";
            otherColumn = "email";
        } else {
            query = "SELECT * FROM user WHERE email = ?";
            columnToUpdate = "email";
            otherColumn = "username_mail";
        }
        // 使用数据库连接函数创建连接
        Connection connection = createMySQLConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // 从结果集中获取另一列的值
                    String otherColumnValue = resultSet.getString(otherColumn);

                    // 更新 UI
                    if (columnToUpdate.equals("email")) {
                        return otherColumnValue;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return name;
    }

    //判断是否邮箱登陆
    private boolean isUsernameMail(String input) {
        // 这里简单地通过包含 "@" 符号来判断是否是 email
        return input.contains("@");
    }

    private Connection createMySQLConnection() {
        Connection connection = null;
        try {
            // 建立数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://database.hetong-re4per.icu/chatgpt_account", "chatgpt", "zl221021@Chatgpt");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

	public void accountMangeItemListen() {
        accMgWin = new accMgWindow();
		accMgWin.setVisible(true);
	}

	private void logoutItemListen() {
		this.dispose();
		main.loginWin = new loginWindows();
        main.loginWin.setVisible(true);
	}

	private void settingItemListen() {
		new settingWindow().setVisible(true);
	}

    private void sendButtonListen() {
        if (sendButtonFlag) {
            inputMessage = sendPane.getText();
            String user = username;
            chatArea.append(user + "：\n" + inputMessage + "\n");
            sendPane.setText("");
            sendButton.setEnabled(false);   // 发送消息后禁止再点击发送
            sendButtonFlag = false;  // 锁定按钮监听

            // 异步执行 chatAPI
            CompletableFuture.supplyAsync(chatAPI::new)
                    .thenAcceptAsync(chatAPI -> {
                        SwingUtilities.invokeLater(() -> {
                            chatArea.append("\nChatGPT：\n" + chatAPI.answer + "\n\n");
                            setScrollPaneBar();
                            sendButton.setEnabled(true);    // 解除发送按钮锁定
                            sendButtonFlag = true;  // 解除按钮监听锁定
                        });
                    });
        }
    }

    private void setScrollPaneBar() {
        SwingUtilities.invokeLater(() -> {
            // 滚动到最底部
            chatScrollBar = chatScrollPane.getVerticalScrollBar();
            if (chatScrollBar != null) {
                // 必须先获取一次chatScrollBar.getMaximum()，否则滚动不到最底部, swing bug
                chatScrollBar.setValue(chatScrollBar.getMaximum());
            }
        });
    }

	private void sendPaneEmpty() {
		sendPane.setText(initSendText);
        sendPane.setForeground(Color.GRAY);
	}

	private void sendPaneKeyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		// ESC快捷退出
		if (keyCode == KeyEvent.VK_ENTER && e.isControlDown()) {
			sendButton.doClick();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

	private void sendPaneFocusGained() {
		String temp = sendPane.getText();
		if (temp.equals(initSendText)) {
            sendPane.setForeground(OriginalColor);
			sendPane.setText("");
		}
	}

	private void sendPaneFocusLost() {
		String temp = sendPane.getText();
		if (temp.isEmpty()) {
			sendPaneEmpty();
		}
	}

    private void aboutItemLister() {
        new about().setVisible(true);
    }

    private void resetChat() {
        chatAPI.resetInputString();
        chatArea.setText("");
    }

    public static void refreshWin() {
        Font setFont = new Font(font, Font.PLAIN, fontSize);
        chatArea.setFont(setFont);
    }

    private void chatAreaKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        mainMenuBar = new JMenuBar();
        accountMenu = new JMenu();
        accountMangeItem = new JMenuItem();
        logoutItem = new JMenuItem();
        toolMenu = new JMenu();
        menuItem1 = new JMenuItem();
        settingItem = new JMenuItem();
        helpMenu = new JMenu();
        aboutItem = new JMenuItem();
        sendPanel = new JPanel();
        sendScrollPane = new JScrollPane();
        sendPane = new JTextPane();
        sendButton = new JButton();
        panel1 = new JPanel() {
            {
                this.setOpaque(false);
                this.setLayout(new BorderLayout());
            }
            public void paintComponent(Graphics g) {
                int x = getWidth() - imageIcon.getIconWidth();
                int y = getHeight() - imageIcon.getIconHeight();
                g.drawImage(imageIcon.getImage(), x, y - 13, this);
                super.paintComponents(g);
            }
        };
        chatScrollPane = new JScrollPane();
        chatArea = new JTextArea();

        //======== this ========
        setTitle("Chat Interface");
        setForeground(Color.black);
        setIconImage(new ImageIcon(getClass().getResource("/icon-chatgpt.png")).getImage());
        setMinimumSize(new Dimension(375, 400));
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

        //======== sendPanel ========
        {

            //======== sendScrollPane ========
            {

                //---- sendPane ----
                sendPane.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", Font.PLAIN, 14));
                sendPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                sendPane.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
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
            sendButton.setToolTipText("\u53ef\u4ee5\u4f7f\u7528Ctrl+Enter\u5feb\u6377\u53d1\u9001");
            sendButton.addActionListener(e -> sendButtonListen());

            GroupLayout sendPanelLayout = new GroupLayout(sendPanel);
            sendPanel.setLayout(sendPanelLayout);
            sendPanelLayout.setHorizontalGroup(
                sendPanelLayout.createParallelGroup()
                    .addGroup(sendPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(sendPanelLayout.createParallelGroup()
                            .addComponent(sendScrollPane, GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.TRAILING, sendPanelLayout.createSequentialGroup()
                                .addGap(0, 961, Short.MAX_VALUE)
                                .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
            );
            sendPanelLayout.setVerticalGroup(
                sendPanelLayout.createParallelGroup()
                    .addGroup(sendPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sendScrollPane, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))
            );
        }
        contentPane.add(sendPanel, BorderLayout.SOUTH);

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());

            //======== chatScrollPane ========
            {
                chatScrollPane.setBorder(Borders.DIALOG_BORDER);
                chatScrollPane.setAutoscrolls(true);
                chatScrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                chatScrollPane.setFocusable(false);

                //---- chatArea ----
                chatArea.setEditable(false);
                chatArea.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));
                chatArea.setBorder(new EtchedBorder());
                chatArea.setLineWrap(true);
                chatArea.setWrapStyleWord(true);
                chatArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                chatArea.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        chatAreaKeyPressed(e);
                    }
                });
                chatScrollPane.setViewportView(chatArea);
            }
            panel1.add(chatScrollPane, BorderLayout.CENTER);
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        setSize(1035, 620);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar mainMenuBar;
    private JMenu accountMenu;
    private JMenuItem accountMangeItem;
    private JMenuItem logoutItem;
    private JMenu toolMenu;
    private JMenuItem menuItem1;
    private JMenuItem settingItem;
    private JMenu helpMenu;
    private JMenuItem aboutItem;
    private JPanel sendPanel;
    private JScrollPane sendScrollPane;
    private JTextPane sendPane;
    private JButton sendButton;
    private JPanel panel1;
    private JScrollPane chatScrollPane;
    private static JTextArea chatArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

	// 自定义变量
    static accMgWindow accMgWin;
    private JScrollBar chatScrollBar;
	final String initSendText = "Message ChatGPT...";
	static String inputMessage = "";
	boolean sendButtonFlag = true;
    private boolean isdark=false;
    private String localAppDATA=System.getenv("LOCALAPPDATA");
    private final String FILE_PATH = localAppDATA+"\\CIF\\isdark";
    public static String font = "微软雅黑";
    public static Integer fontSize = 14;
    String username = Chatname(loginWindows.username_s);
    private final ImageIcon imageIcon = new ImageIcon(getClass().getResource("background-250x167-semitransparent.png"));
    private Color OriginalColor;
	// 自定义方法
}
