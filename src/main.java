import javax.swing.*;

public class main {

    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new ChatInterfaceOld().setVisible(true);
                /*
                new PasswordWinOld().setVisible(true);
                new RegisterWin().setVisible(true);
               */
            }
        });
    }
}
