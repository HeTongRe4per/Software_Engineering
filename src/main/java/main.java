import javax.swing.*;

public class main {

    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // 设置本机系统外观
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new loginWindows().setVisible(true);
                //new chatApiHttpClient();
                //new ChatInterfaceOld().setVisible(true);
            }
        });
    }
}
