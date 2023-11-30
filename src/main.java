import javax.swing.*;

public class main {

    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new passwdUpdateWindow().setVisible(true);
                new loginWindows().setVisible(true);
                new ChatInterface().setVisible(true);
            }
        });
    }
}
