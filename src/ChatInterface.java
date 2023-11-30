import java.awt.*;
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        mainMenuBar = new JMenuBar();
        accountMenu = new JMenu();
        accountMange = new JMenuItem();
        logout = new JMenuItem();
        toolMenu = new JMenu();
        setting = new JMenuItem();
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
        setIconImage(new ImageIcon(getClass().getResource("/resource/icon-chatgpt.png")).getImage());
        setMinimumSize(new Dimension(100, 50));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== mainMenuBar ========
        {

            //======== accountMenu ========
            {
                accountMenu.setText("\u8d26\u53f7");

                //---- accountMange ----
                accountMange.setText("\u8d26\u53f7\u7ba1\u7406");
                accountMenu.add(accountMange);
                accountMenu.addSeparator();

                //---- logout ----
                logout.setText("\u9000\u51fa\u767b\u5f55");
                accountMenu.add(logout);
            }
            mainMenuBar.add(accountMenu);

            //======== toolMenu ========
            {
                toolMenu.setText("\u5de5\u5177");

                //---- setting ----
                setting.setText("\u8bbe\u7f6e");
                toolMenu.add(setting);
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
                sendScrollPane.setViewportView(sendPane);
            }
            snedPanel.add(sendScrollPane);

            //---- sendButton ----
            sendButton.setIcon(new ImageIcon(getClass().getResource("/resource/send.png")));
            sendButton.setMaximumSize(new Dimension(30, 30));
            sendButton.setMinimumSize(new Dimension(30, 30));
            sendButton.setPreferredSize(new Dimension(30, 30));
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
        setSize(680, 450);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar mainMenuBar;
    private JMenu accountMenu;
    private JMenuItem accountMange;
    private JMenuItem logout;
    private JMenu toolMenu;
    private JMenuItem setting;
    private JMenu helpMenu;
    private JMenuItem aboutItem;
    private JPanel snedPanel;
    private JScrollPane sendScrollPane;
    private JTextPane sendPane;
    private JButton sendButton;
    private JScrollPane chatScrollPane;
    private JTextArea chatArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

}
