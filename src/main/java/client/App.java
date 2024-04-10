package client;

import Gui.Login;
import Gui.QuanLyHoaDon;
import Gui.TrangChu_NV;
import Gui.TrangChu_QL;

import java.io.IOException;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
//        new Login(socket).setVisible(true);
        new TrangChu_NV(socket).setVisible(true);
//        new QuanLyHoaDon(socket).setVisible(true);
//        System.out.println("check");
    }
}
