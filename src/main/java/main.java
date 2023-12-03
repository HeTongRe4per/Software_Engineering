import javax.swing.*;

public class main {

    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //ows().setVisible(true);
                new loginWindows().setVisible(true);
                //new chatApiHttpClient();//
            }
        });
    }
}
