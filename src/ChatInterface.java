import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatInterface extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;

    public ChatInterface() {
        // 设置窗口标题
        super("ChatGPT Interface");

        // 设置布局为边界布局
        setLayout(new BorderLayout());

        // 创建聊天区域
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        // 创建输入框和发送按钮
        inputField = new JTextField();
        JButton sendButton = new JButton("发送");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // 创建底部面板，并添加输入框和发送按钮
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        // 将底部面板添加到窗口底部
        add(bottomPanel, BorderLayout.SOUTH);

        // 设置窗口大小和关闭操作
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 将窗口居中显示
    }

    private void sendMessage() {
        String userMessage = inputField.getText();
        if (!userMessage.trim().isEmpty()) {
            // 将用户输入的消息添加到聊天框
            appendMessage("User: " + userMessage);

            // TODO: 调用ChatGPT或其他聊天机器人的API，获取回复消息

            // 暂时使用简单的回复
            String replyMessage = "这是ChatGPT的回复。";
            appendMessage("ChatGPT: " + replyMessage);

            // 清空输入框
            inputField.setText("");
        }
    }

    private void appendMessage(String message) {
        chatArea.append(message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength()); // 滚动到最底部
    }
}