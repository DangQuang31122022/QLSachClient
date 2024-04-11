/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Gui;

//import dao.TaiKhoanDAO;
//import dao.LoginDao;
//import connectDB.ConnectDB;
//import dao.NhanVienDAO;
//import entity.taiKhoan;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author LENOVO
 */
public class QuenMatKhau extends javax.swing.JFrame {
    private Socket client;

    /**
     * Creates new form QuenMatKhau
     */
    private static String ma;
    private String enteredUsername;
//    private LoginDao LoginDao;
//    private NhanVienDAO nhanVienDAO;
//    private TaiKhoanDAO taiKhoanDao;
//    private ConnectDB connectDB;
    private JTable table;
    private DefaultTableModel tableModel;
    
    public QuenMatKhau(Socket socket) {
        this.client = socket;
        initComponents();
//        LoginDao = new LoginDao();
//        nhanVienDAO = new NhanVienDAO();
//        taiKhoanDao = new TaiKhoanDAO();
//        connectDB = new ConnectDB();
//        try {
//            connectDB.connect();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //table = new JTable();
        //tableModel = (DefaultTableModel) jTable_mahc.getModel();
        jButton2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        mkmoi.setVisible(false);
        xnmk.setVisible(false);
    }
    
    public static String layMaNgauNhien() {
        int length = 6;
        String uppercasechars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        String validChars = uppercasechars + lowercaseChars + number;
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validChars.length());
            sb.append(validChars.charAt(randomIndex));
        }
        return sb.toString();
    }
    
    public static void giuiMa(String mailden) {
        final String from = "nguyenngoctai201202@gmail.com";
        final String password = "konzerfkavltbqpv";
        final String mail = mailden;
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.stmp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(from, password);
            }
        });
        
        MimeMessage msg = new MimeMessage(session);
        try {
            
            msg.setFrom(from);
            msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mail, false));
            ma = layMaNgauNhien();
            String noidung = "Mã OTP của bạn là: " + ma;
            msg.setText(noidung);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", from, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.err.println("thanh cong");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_otp = new javax.swing.JTextField();
        txt_gmail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btn_layma = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        xnmk = new javax.swing.JTextField();
        mkmoi = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Mã OTP:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 100, 30));

        jLabel2.setText("Nhập Email:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 100, 30));

        txt_otp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_otpActionPerformed(evt);
            }
        });
        jPanel1.add(txt_otp, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 280, 30));

        txt_gmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_gmailActionPerformed(evt);
            }
        });
        jPanel1.add(txt_gmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 280, 30));

        jButton1.setBackground(new java.awt.Color(102, 255, 255));
        jButton1.setText("Xác nhận");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, -1, 30));

        btn_layma.setBackground(new java.awt.Color(153, 255, 153));
        btn_layma.setText("Gửi OTP");
        btn_layma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_laymaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_layma, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 80, 30));

        jLabel3.setText("Mật khẩu mới:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 110, 30));

        jLabel4.setText("Mật khẩu mới:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 110, 30));
        jPanel1.add(xnmk, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 280, 30));
        jPanel1.add(mkmoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 280, 30));

        jButton2.setText("Lưu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_laymaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_laymaActionPerformed
        String gmail = txt_gmail.getText();
//        nhanVienDAO = new NhanVienDAO();
        try {
            PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
            pw.println("forgotPassword");
            Map<String, Object> request = new HashMap<>();
            request.put("isCheckMail", true);
            request.put("email", gmail);

            // convert map to json
            String json = new Gson().toJson(request);
            System.out.println(json);
            pw.println(json);

            // get response from server
            InputStream is = client.getInputStream();
            int response = is.read();
            if (response == 1) {
                JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra gmail của bạn và nhập mã!!!");
                giuiMa(gmail);
                System.out.println("Thành công");
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng tài khoảng và gmail!!!", "Cảnh báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        if (nhanVienDAO.dieuKienQuenMatkhau(gmail) == false) {
//            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng tài khoảng và gmail!!!", "Cảnh báo",
//                    JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra gmail của bạn và nhập mã!!!");
//            giuiMa(gmail);
//            System.out.println("Thành công");
//        }
    }//GEN-LAST:event_btn_laymaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String otp = txt_otp.getText();
        
        if (!otp.equals(ma)) {
            System.out.println("otp da nhap sai");
            JOptionPane.showMessageDialog(null, "Mã OTP không đúng!!!", "Cảnh báo",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("otp nhap da dung");
            JOptionPane.showMessageDialog(null, "Vui lòng thay đổi mật khẩu mới!!!", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
//            String taiKhoan = txt_taikhoan.getText();
            jButton2.setVisible(true);
            jLabel3.setVisible(true);
            jLabel4.setVisible(true);
            mkmoi.setVisible(true);
            xnmk.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_gmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_gmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_gmailActionPerformed

    private void txt_otpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_otpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_otpActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String matKhau = mkmoi.getText();
        String matKhauNhapLai = xnmk.getText();
        if (matKhau.equals(matKhauNhapLai)) {
            try {
                PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
                pw.println("forgotPassword");
                Map<String, Object> request = new HashMap<>();
                request.put("isCheckMail", false);
                request.put("email", txt_gmail.getText());
                request.put("password", matKhau);
                // convert map to json
                String json = new Gson().toJson(request);
                // send json include email and password
                pw.println(json);
                // get response from server
                InputStream is = client.getInputStream();
                int response = is.read();
                if (response == 1) {
                    JOptionPane.showMessageDialog(this, "Đặt mật khẩu thành công");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Đặt mật không khẩu thành công");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Mật khẩu không trùng khớp!!!", "Cảnh báo",
                    JOptionPane.INFORMATION_MESSAGE);
        }
//        taiKhoan tk = taiKhoanDao.timTaiKhoanByEmail(txt_gmail.getText());
//        if (tk != null) {
//            if (matKhau.equals(matKhauNhapLai)) {
//                taiKhoanDao.doiMatKhauTaiKhoan(tk, matKhau);
//                JOptionPane.showMessageDialog(this, "Đặt mật khẩu thành công");
//            } else {
//                                JOptionPane.showMessageDialog(this, "Đặt mật không khẩu thành công");
//
//            }
//        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_layma;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField mkmoi;
    private javax.swing.JTextField txt_gmail;
    private javax.swing.JTextField txt_otp;
    private javax.swing.JTextField xnmk;
    // End of variables declaration//GEN-END:variables
}
