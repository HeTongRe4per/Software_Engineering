import java.awt.*;
import javax.swing.*;

public class ChatGPT {
    public ChatGPT() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        chatWin = new JPanel();
        sendPanel = new JPanel();
        sendField = new JTextField();
        var vSpacer1 = new JPanel(null);
        sendButton = new JButton();
        var scrollPane1 = new JScrollPane();
        chatArea = new JTextArea();

        //======== chatWin ========
        {
            chatWin.setLayout(new BorderLayout());

            //======== sendPanel ========
            {
                sendPanel.setLayout(new FlowLayout());

                //---- sendField ----
                sendField.setEnabled(true);
                sendField.setHorizontalAlignment(SwingConstants.LEFT);
                sendField.putClientProperty("caretWidth", 500);
                sendPanel.add(sendField);
                sendPanel.add(vSpacer1);

                //---- sendButton ----
                sendButton.setHorizontalAlignment(SwingConstants.CENTER);
                sendButton.setHorizontalTextPosition(SwingConstants.CENTER);
                sendButton.setText("\u53d1\u9001");
                sendPanel.add(sendButton);
            }
            chatWin.add(sendPanel, BorderLayout.SOUTH);
            chatWin.add(scrollPane1, BorderLayout.EAST);

            //---- chatArea ----
            chatArea.setText("");
            chatWin.add(chatArea, BorderLayout.WEST);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel chatWin;
    private JPanel sendPanel;
    private JTextField sendField;
    private JButton sendButton;
    private JTextArea chatArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
