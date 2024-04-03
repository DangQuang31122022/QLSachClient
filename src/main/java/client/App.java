package client;

import Gui.Login;

import java.io.IOException;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        new Login(socket).setVisible(true);
    }
}
