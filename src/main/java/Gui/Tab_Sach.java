/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.formula.functions.Columns;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.reflect.TypeToken;

import dao.NhaCungCapDAO;
import dao.NhaXuatBanDao;
import dao.SachDao;
import dao.SanPhamDAO;
import dao.TheLoaiDao;
import entity.KhachHang;
import entity.NhaCungCap;
import entity.NhaXuatBan;
import entity.NhanVien;
import entity.Sach;
import entity.SanPham;
import entity.TheLoai;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional.TxType;

/**
 *
 * @author LENOVO
 */
public class Tab_Sach extends javax.swing.JPanel implements ActionListener,MouseListener {
	private Socket socket;
	 PrintWriter out;
 	Scanner sc;
    /**
     * Creates new form Tab_Sach
     */
    public Tab_Sach(Socket socket) {
    	initComponents();
    	try {
			out = new PrintWriter(socket.getOutputStream(), true);
			sc = new Scanner(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	this.socket = socket;
        loadtableSach();
    }
    public void TimKiem() {
        jTable_Sach.clearSelection();
        clearTableSach();
        DefaultTableModel dtm = (DefaultTableModel) jTable_Sach.getModel();
        String timKiem = "";
        if (txtTimKiem.getText().length() > 0) {
            timKiem = txtTimKiem.getText();
        }
        try {
    		out.println("QLBH");
    		out.println("getAllSach");
    		String listObject = sc.nextLine();
    		InstanceCreator<Sach> sachInstanceCreator = new InstanceCreator<Sach>() {
    			@Override
    			public Sach createInstance(Type type) {
    				return new Sach();
    			}
    		};
    		Gson gsonSach = new GsonBuilder().registerTypeAdapter(Sach.class, sachInstanceCreator).create();

    		List<Sach> listSach = gsonSach.fromJson(listObject, new TypeToken<List<Sach>>() {
    		}.getType());
    		for (Sach s : listSach) {
    			
    			  if (s.getMaSP().toLowerCase().contains(timKiem.toLowerCase())
                          || s.getTenSP().toLowerCase().contains(timKiem.toLowerCase()) 
                          || s.getNhaCungCap().getTenNCC().contains(timKiem.toLowerCase())
                          || s.getNhaXuatBan().getTenNXB().contains(timKiem.toLowerCase())
                        ) {
    				Object[] rowData = { s.getMaSP(),s.getTenSP(),s.getTheLoai().getTenTheLoai(),s.getHinhAnh(),s.getNhaCungCap().getTenNCC(),s.getNhaXuatBan().getTenNXB(),s.getTacGia(),s.getSoLuongTK(),s.getDonGiaBan() };
    				dtm.addRow(rowData);
    		}
        }
        } catch (Exception e) {
    		e.printStackTrace();
    	}
    	out.flush();
    }

    public void loadtableSach() {
    	clearTableSach();
    	try {
		out.println("QLBH");
		out.println("getAllSach");
		DefaultTableModel dtm = (DefaultTableModel) jTable_Sach.getModel();
		String listObject = sc.nextLine();
		InstanceCreator<Sach> sachInstanceCreator = new InstanceCreator<Sach>() {
			@Override
			public Sach createInstance(Type type) {
				return new Sach();
			}
		};
		Gson gsonSach = new GsonBuilder().registerTypeAdapter(Sach.class, sachInstanceCreator).create();
		List<Sach> listSach = gsonSach.fromJson(listObject, new TypeToken<List<Sach>>() {
		}.getType());
		for (Sach s : listSach) {
			
				Object[] rowData = { s.getMaSP(),s.getTenSP(),s.getTheLoai().getTenTheLoai(),s.getHinhAnh(),s.getNhaCungCap().getTenNCC(),s.getNhaXuatBan().getTenNXB(),s.getTacGia(),s.getSoLuongTK(),s.getDonGiaBan() };
				dtm.addRow(rowData);
		}}
	 catch (Exception e) {
		e.printStackTrace();
	}
	out.flush();
}
    public void clearTableSach() {
        DefaultTableModel dtm = (DefaultTableModel) jTable_Sach.getModel();
        dtm.setRowCount(0);
    }
//    private void themSanPham() throws SQLException {
//        String maSp = txtMaSach.getText();
//        String tenSach = txtTenSach.getText();
//        String tacGia = txtTacGia.getText();
//    	int soLuong=Integer.parseInt(txtSoLuong.getText());
//    	double donGia=Double.parseDouble(txtDonGia.getText());
//       
//        try {
//            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
//            pw.println("QLSP");
//            pw.println("addSach");
//            String json = "{\"maSp\":\"" + maSp + "\",\"tenSach\":\"" + tenSach + "\",\"theLoai\":\"" + theLoai + "\",\"nhaCungCap\":" + nhaCungCap + ",\"nhaXuatBan\":\"" + nhaXuatBan + "\",\"tacGia\":\"" + tacGia + "\",}";
//            pw.println(json);
//
//            Scanner sc = new Scanner(socket.getInputStream());
//            String response = sc.nextLine();
//            if (response.equals("Email exists")) {
//                JOptionPane.showMessageDialog(null, "Email đã tồn tại trong hệ thống. Vui lòng sử dụng email khác");
//                return;
//            }
//            if (response.equals("Phone exists")) {
//                JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại trong hệ thống. Vui lòng sử dụng sdt khác");
//                return;
//            }
//            if (response.equals("Add success")) {
//                JOptionPane.showMessageDialog(null, "Thêm thành công");
//                loadtableNhanVien();
//                clearInput();
//                huyThaoTac();
//                return;
//            }
//            JOptionPane.showMessageDialog(null, "Thêm thất bại");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    public void napDuLieuComBoBoxNhaCungCap() {
//    	String items[] = new String[30];
//    	try {
//    		out.println("QLSP");
//    		out.println("getAllNcc");
//    		String listObject = sc.nextLine();
//    		InstanceCreator<NhaCungCap> sachInstanceCreator = new InstanceCreator<NhaCungCap>() {
//    			@Override
//    			public NhaCungCap createInstance(Type type) {
//    				return new NhaCungCap();
//    			}
//    		};
//    		Gson gsonSach = new GsonBuilder().registerTypeAdapter(NhaCungCap.class, sachInstanceCreator).create();
//
//    		List<NhaCungCap> listSach = gsonSach.fromJson(listObject, new TypeToken<List<NhaCungCap>>() {
//    		}.getType());
//    		for (NhaCungCap s : listSach) {
//    			
//    			int i = 0;
//				items[i] = s.getTenNCC() + "";
//				i++;
//    		}}
//    	 catch (Exception e) {
//    		e.printStackTrace();
//    	}
//    	out.flush();
//    	
//		cbxNhaCungCap.setModel(new DefaultComboBoxModel<String>(items));
//    }
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        cbxNhaSanXuat = new javax.swing.JComboBox<>();
        cbxTheLoai = new javax.swing.JComboBox<>();
        txtTenSach = new javax.swing.JTextField();
        txtTacGia = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        cbxNhaCungCap = new javax.swing.JComboBox<>();
        btnXuatExcel = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jpnHinhAnh = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Sach = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1260, 530));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("ĐƠN GIÁ:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 140, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("NHÀ XUẤT BẢN:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 140, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("THỂ LOẠI:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 140, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("NHÀ CUNG CẤP:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 140, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("TÊN SÁCH:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 140, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("TÁC GIẢ:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 140, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("SỐ LƯỢNG:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 140, 40));
        jPanel1.add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, 260, 40));

        cbxNhaSanXuat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NXB Giáo Dục Việt Nam", "NXB Trẻ", "NXB Kim Đồng", "NXB Đà Nẵng","NXB Hội Nhà Văn","NXB Tổng hợp thành phố Hồ Chí Minh","NXB Chính trị quốc gia Sự thật","NXB Phụ nữ Việt Nam","NXB Lao Động","Nhã Nam","Đinh Tị Books","Đông A","NXB Văn Học","NXB Quân Đội Nhân Dân" }));
        cbxNhaSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(cbxNhaSanXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 260, 40));

        cbxTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Giáo khoa","Sách thiếu nhi","Kinh tế","Chính trị","Tiểu thuyết","Quản trị-lãnh đạo","Marketing-Bán hàng","Phân tích kinh tế","Kỹ năng sống","Tâm lý","Câu chuyện cuộc đời","Lịch sử","Nghệ thuật-Giải trí" }));
        jPanel1.add(cbxTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 260, 40));
        jPanel1.add(txtTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 260, 40));
        jPanel1.add(txtTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 260, 40));
        jPanel1.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 260, 40));

        cbxNhaCungCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhà xuất bản Giáo Dục", "Cty Phương Nam", "Phanbook", "Cty Văn Hóa & Truyền Thông Trí Việt","Huy Hoang Bookstore","Thiên Long","MORNING GLORY CORP","Cty VPP Hồng Hà","Cty Giấy Hải Tiến","Vận Tải Quốc Anh","Cty TM Hạnh Thuận" }));
        jPanel1.add(cbxNhaCungCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 260, 40));

        btnXuatExcel.setBackground(new java.awt.Color(102, 255, 102));
        btnXuatExcel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXuatExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icons8_ms_excel_30px.png"))); // NOI18N
        btnXuatExcel.setText("XUẤT EXCEL");
        btnXuatExcel.setBorder(null);
        btnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelActionPerformed(evt);
            }
        });
        jPanel1.add(btnXuatExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 180, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("MÃ SÁCH:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 40));
        jPanel1.add(txtMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 260, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 153, 0));
        jLabel10.setText("Tìm kiếm:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 70, 40));

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtTimKiem.setToolTipText("");
        jPanel1.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 340, 40));

        btnTimKiem.setBackground(new java.awt.Color(255, 153, 0));
        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("TÌM KIẾM");
        btnTimKiem.setBorder(null);
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSachActionPerformed(evt);
            }
        });
        jPanel1.add(btnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, 110, 40));

        jpnHinhAnh.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHinhAnh.setBackground(new java.awt.Color(204, 255, 255));
        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 153, 0), null));
        jpnHinhAnh.add(lblHinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 190));

        jPanel1.add(jpnHinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 240, 190));

        btnChonAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/btnFile.png"))); // NOI18N
        btnChonAnh.setBorder(null);
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnChonAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 210, 200, 40));

        btnLuu.setBackground(new java.awt.Color(0, 255, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/check-icon.png"))); // NOI18N
        btnLuu.setText("LƯU");
        btnLuu.setBorder(null);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 210, 130, 40));

        btnReset.setBackground(new java.awt.Color(255, 153, 0));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/reset.png"))); // NOI18N
        btnReset.setText("RESET");
        btnReset.setBorder(null);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel1.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 160, 130, 40));

        btnXoaTrang.setBackground(new java.awt.Color(102, 255, 102));
        btnXoaTrang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaTrang.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Refresh-icon.png"))); // NOI18N
        btnXoaTrang.setText("XÓA TRẮNG");
        btnXoaTrang.setBorder(null);
        btnXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrangActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoaTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 110, 130, 40));

        btnSua.setBackground(new java.awt.Color(153, 255, 204));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/icons8_maintenance_30px.png"))); // NOI18N
        btnSua.setText("SỬA");
        btnSua.setBorder(null);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 60, 130, 40));

        btnThem.setBackground(new java.awt.Color(255, 102, 102));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Add-icon.png"))); // NOI18N
        btnThem.setText("THÊM");
        btnThem.setBorder(null);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 130, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1260, 270));

        jTable_Sach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
            	"MÃ SÁCH", "TÊN SÁCH", "THỂ LOẠI","HÌNH ẢNH", "NHÀ CUNG CẤP", "NHÀ XUẤT BẢN", "TÁC GIẢ", "SỐ LƯỢNG", "ĐƠN GIÁ"
            }
        ));
        jScrollPane1.setViewportView(jTable_Sach);
        jTable_Sach.setShowHorizontalLines(true);
        jTable_Sach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               jtable_SachMousePressed(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
            	jtable_SachMousePressed(evt);
        }
        });
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 1260, 210));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("QUẢN LÝ SÁCH");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 40));
   
      
       
    }// </editor-fold>//GEN-END:initComponents
   
    private void jtable_SachMousePressed(java.awt.event.MouseEvent evt) {
    	String id = (String) jTable_Sach.getValueAt(jTable_Sach.getSelectedRow(), 0);
    	
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println("QLSP");
            pw.println("getSachID");
            pw.println(id);

            Scanner sc = new Scanner(socket.getInputStream());
            String nhanVien = sc.nextLine();
            InstanceCreator<Sach> sachInstanceCreator = new InstanceCreator<Sach>() {
    			@Override
    			public Sach createInstance(Type type) {
    				return new Sach();
    			}
    		};
    		Gson gsonSach = new GsonBuilder().registerTypeAdapter(Sach.class, sachInstanceCreator).create();
    		Sach sach = gsonSach.fromJson(nhanVien, new TypeToken<Sach>() {
    		}.getType());
            if (nhanVien.equals("null")) {
                System.out.println("Không tìm thấy sach");
                return;
            }
         
            txtMaSach.setText(sach.getMaSP());
            txtTenSach.setText(sach.getTenSP());
            cbxTheLoai.setSelectedItem(sach.getTheLoai().getTenTheLoai());
            cbxNhaCungCap.setSelectedItem(sach.getNhaCungCap().getTenNCC());
            cbxNhaSanXuat.setSelectedItem(sach.getNhaXuatBan().getTenNXB());
            txtTacGia.setText(sach.getTacGia());
            txtSoLuong.setText(String.valueOf(sach.getSoLuongTK()));
            txtDonGia.setText(String.valueOf(sach.getDonGiaBan()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    	
    }
    private static String[] Columns= {"MÃ SÁCH", "TÊN SÁCH", "THỂ LOẠI","HÌNH ẢNH", "NHÀ CUNG CẤP", "NHÀ XUẤT BẢN", "TÁC GIẢ", "SỐ LƯỢNG", "ĐƠN GIÁ"};
    private void btnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        // TODO add your handling code here:
    	
    
    }//GEN-LAST:event_btn2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnTimSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // TODO add your handling code here:
    	TimKiem();
    }//GEN-LAST:event_btn1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//       
    	  JFileChooser filechoose = new JFileChooser("src\\Img\\SanPham");
          FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("hinhAnh", "jpg", "png");
          filechoose.setFileFilter(imageFilter);
          filechoose.setMultiSelectionEnabled(false);

          int x = filechoose.showDialog(this, "Chọn Ảnh");
          if (x == JFileChooser.APPROVE_OPTION) {
              file = filechoose.getSelectedFile();
              lblHinhAnh.setText("");
              lblHinhAnh.setIcon(ResizeImage(file.getAbsolutePath()));
          }
        
    }//GEN-LAST:event_jButton1ActionPerformed
    public ImageIcon ResizeImage(String imgPath) {
        ImageIcon myImage = new ImageIcon(imgPath);
        Image img = myImage.getImage();
		
		Image newImg = img.getScaledInstance(jpnHinhAnh.getWidth(),jpnHinhAnh.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        // TODO add your handling code here:
 
    	
    	
    }//GEN-LAST:event_btn4ActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        // TODO add your handling code here:
    	
    }//GEN-LAST:event_btn6ActionPerformed

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
    	
    }//GEN-LAST:event_btn5ActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        // TODO add your handling code here:
    	
    	
    }//GEN-LAST:event_btn8ActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
	    // TODO add your handling code here:
		
		
	}//GEN-LAST:event_btn8ActionPerformed
	private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
    	//GEN-LAST:event_btn3ActionPerformed
//    public Sach getDataOnTextField() {
//    	ncc_dao = new NhaCungCapDAO();
//    	nhaxuatban_dao = new NhaXuatBanDao();
//    	theloai_dao= new TheLoaiDao();
//    	String maSP = txtMaSach.getText().trim();
//    	String tenSP=txtTenSach.getText().trim();
//    	String tacGia=txtTacGia.getText().trim();
//    	int soLuong=Integer.parseInt(txtSoLuong.getText());
//    	double donGia=Double.parseDouble(txtDonGia.getText());
//    	String hinhAnh="";
//    	if (file != null) {
//            hinhAnh = file.getName();
//        }
//    	NhaXuatBan nhaXuatBan=nhaxuatban_dao.getNhaXuatBanByName(cbxNhaSanXuat.getSelectedItem().toString());
//    	TheLoai theLoai=theloai_dao.getTheLoaiByName(cbxTheLoai.getSelectedItem().toString());
//    	NhaCungCap nhaCungCap=ncc_dao.getNhaCungCapByName(cbxNhaCungCap.getSelectedItem().toString());
//    	Sach sach = new Sach(maSP, tenSP, nhaCungCap, soLuong, donGia, hinhAnh, nhaXuatBan, theLoai, tacGia);
//    	return sach;
    	if(btnThem.getText().equalsIgnoreCase("Thêm")) {
    		xoatrang();
    	Sach sach=new Sach();
    	btnChonAnh.setEnabled(true);
    	btnLuu.setEnabled(true);
    	btnThem.setText("Hủy");
    	btnSua.setEnabled(false);
    	btnXoaTrang.setEnabled(false);
    	txtDonGia.setText("0");
    	txtDonGia.setEnabled(false);
    	txtSoLuong.setText("0");
    	txtSoLuong.setEnabled(false);
    	txtMaSach.setText(sach.auto_ID());
    	}
    	else if(btnThem.getText().equalsIgnoreCase("Hủy")) {
    		btnThem.setText("Thêm");
    		txtMaSach.setText("");
        	btnSua.setEnabled(true);
        	btnXoaTrang.setEnabled(true);
        	btnChonAnh.setEnabled(false);
        	btnLuu.setEnabled(false);
    		lblHinhAnh.setIcon(null);
    		txtDonGia.setEnabled(true);
    		txtSoLuong.setEnabled(true);
    	}
  }
    public void xoatrang() {
    	txtMaSach.setText("");
    	txtTenSach.setText("");
    	txtTacGia.setText("");
    	txtSoLuong.setText("");
    	txtDonGia.setText("");
    	cbxNhaCungCap.setSelectedIndex(0);
    	cbxTheLoai.setSelectedIndex(0);
    	cbxTheLoai.setSelectedIndex(0);
    	lblHinhAnh.setIcon(new ImageIcon());
    }
    private static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
//    private void updateTableSach(Sach sach) {
//    	int row =jTable_Sach.getSelectedRow();
//    	DefaultTableModel model =(DefaultTableModel) jTable_Sach.getModel();
//    	model.setValueAt(sach.getMaSP(), row, 0);
//    	model.setValueAt(sach.getTenSP(), row, 1);
//    	model.setValueAt(sach.getTheLoai().getTenTheLoai(), row, 2);
//    	model.setValueAt(sach.getHinhAnh(), row, 3);
//    	model.setValueAt(sach.getNhaCungCap().getTenNCC(), row, 4);
//    	model.setValueAt(sach.getNhaXuatBan().getTenNXB(), row, 5);
//    	model.setValueAt(sach.getTacGia(), row,6);
//    	model.setValueAt(sach.getSoLuongTK(), row, 7);
//    	model.setValueAt(sach.getDonGiaBan(), row, 8);
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXuatExcel;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JComboBox<String> cbxNhaSanXuat;
    private javax.swing.JComboBox<String> cbxTheLoai;
    private javax.swing.JComboBox<String> cbxNhaCungCap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jpnHinhAnh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Sach;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtTacGia;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JLabel lblHinhAnh;
    private EntityManager em;
//    private NhaCungCapDAO ncc_dao = new NhaCungCapDAO();
//    private TheLoaiDao theloai_dao= new TheLoaiDao();
//    private NhaXuatBanDao nhaxuatban_dao=new NhaXuatBanDao();
//    private SanPhamDAO sanpham_dao = new SanPhamDAO();
//    private SachDao sach_dao=new SachDao();
    private File file=null;
    // End of variables declaration//GEN-END:variables
	@Override
	public void mouseClicked(MouseEvent e) {
//		fillForm(jTable_Sach.getSelectedRow());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

