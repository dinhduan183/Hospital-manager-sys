/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.print.PrintException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Trangchu extends javax.swing.JFrame {

    /**
     * Creates new form Trangchu
     */
    private String xdoituong, xgioitinh, xketquadieutri, userr;
    int year = Calendar.getInstance().get(Calendar.YEAR);

    public Trangchu() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        LoadDatauser();
    }
    
//    public String Setuser(String un) {
//        return userr = un;
//    }
    public void SetU(String un) {
        this.userr = un;
    }
    public String GetU() {
        return this.userr;
    }
    private String xyz;
    private void LoadDatauser() {         
        //hàm hiện thị thông tin từ sql về bảng
        
         try{ 
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM bacsi WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,GetU());
            xyz = GetU();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                countrow++;
                jLabel51.setText(rs.getString("hoten"));
                jLabel52.setText(rs.getString("chucvu"));
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,e);
            }
//         System.out.println(jLabel52.getText());
//         if(jLabel52.getText() == "Bác sĩ"){
//             
//             BTN_BacSi.setEnabled(false);
//             Btn_ThongKe.setEnabled(false);
//         }
//         if(jLabel52.getText() == "Viện trưởng"){
//             BTN_BacSi.setEnabled(true);
//             Btn_ThongKe.setEnabled(true);
//         }
    }
    /*--- Hàm Load Table tab Bệnh nhân ---*/
    int countrow;
    private void LoadDatabenhnhan() {         
        //hàm hiện thị thông tin từ sql về bảng
         try{ 
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM benhnhan";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            DefaultTableModel tm =(DefaultTableModel)Tbl_BenhNhan.getModel();
            tm.setRowCount(0);
            
            while(rs.next()){
                countrow++;
                Object o[]={rs.getString("id"),rs.getString("hoten"),rs.getString("ngaysinh"),rs.getString("diachi"),rs.getString("khoadieutri")};
                tm.addRow(o);
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,e);
            }
    }
    
    /*--- Tăng ID bệnh nhân lên +1*/
    private String TangID(){
        countrow = -1;
        LoadDatabenhnhan();
        //int row = jTable2.getRowCount();
        //System.out.println(countrow);;
        String ID = (String) Tbl_BenhNhan.getValueAt(countrow,0);
        String[] parts = ID.split("N");
        int ID1 = 1 + Integer.parseInt(parts[1]);
        String IDfinal = "BN"+ID1;
        return IDfinal;
    }
    
    private void loadDatabacsi() {         
        //hàm hiện thị thông tin từ sql về bảng

         try{ 
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM bacsi";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            DefaultTableModel tm =(DefaultTableModel)Table_bacsi.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("id"),rs.getString("hoten"),rs.getInt("tuoi"),rs.getString("chuyenkhoa")};
                tm.addRow(o);
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,e);
            }
        // TODO add your handling code here:
    }
    
    private void LoadDatakhothuoc(){       
        //hàm hiện thị thông tin từ sql về bảng
         try{ 
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM khothuoc";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            DefaultTableModel tm =(DefaultTableModel)Table_khothuoc.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getInt("tenthuoc")};
                tm.addRow(o);
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,e);
            }
    }
    
    public void bill(){

        DefaultTableModel tablemodel = (DefaultTableModel) Table_kethuoc.getModel();

        txt_bill.setText(txt_bill.getText()+"----------------------------------------------------------------\n");
//        txt_bill.setText(txt_bill.getText()+"||"+"\t"+"Mã thuốc"+
//                "\t"+"||"+"\t"+"Tên thuốc"+"\t"+"||"+"\t"+
//                "Nhóm thuốc"+"\t"+"||"+"\t"+"\n");
        
        for(int i=0; i<tablemodel.getRowCount();i++){
            String mathuoc =tablemodel.getValueAt(i,1).toString();
            String tenthuoc =tablemodel.getValueAt(i,2).toString();
            String soluong =tablemodel.getValueAt(i,3).toString();
            String ghichu = tablemodel.getValueAt(i,5).toString();
//            String ghichu = "1 ngày uống 1 viên sau ăn 2 lần";
//            int soluong = 2;
            txt_bill.setText(txt_bill.getText()+"     "+(i+1)+"/   "+tenthuoc+"\t Số lượng: "+soluong+"\n"
                    +"           Mã: "+mathuoc+"\n"
                            +"          "+ghichu+"\n");
        }
        
        txt_bill.setText(txt_bill.getText()+"----------------------------------------------------------------\n");
        
    }
    
    /*--- CÁC HÀM LIÊN QUAN ĐẾN THĂM KHÁM -----*/
    private void LoadDataThamKham(){
        try{ 
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM thamkham";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            DefaultTableModel tm =(DefaultTableModel)TB_danhsach.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                countrow++;
                Object o[]={rs.getString("phong"),rs.getString("hoten"),rs.getInt("tuoi"),rs.getString("sdt"),rs.getString("doituong"),rs.getString("trangthai")};
                tm.addRow(o);
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
    }
    
    private void Doitrangthai(String s){
        try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
                Statement st = conn.createStatement();
                String sql = "UPDATE thamkham SET namsinh=?, gioitinh=?, nghenghiep=?, diachi=?, sdt=?, doituong=?, cmnd=?, "
                        + "hotennguoinha=?, sdtnguoinha=?, ngayvao=?, tinhtrang=?, phong=?, henkham=?,trangthai=? WHERE hoten=? AND tuoi=?";
                   
                PreparedStatement ptst = conn.prepareStatement(sql);
                ptst.setString(15, ahoten.getText());
                ptst.setString(16, atuoi.getText());
                ptst.setString(1, angaysinh.getText());
                ptst.setString(2, xgioitinh);
                ptst.setString(3, anghenghiep.getText());
                ptst.setString(4, adiachi.getText());
                ptst.setString(5, asdt.getText());
                ptst.setString(6, xdoituong);
                ptst.setString(7,acmnd.getText());
                ptst.setString(8,atennguoithan.getText());
                ptst.setString(9,asdtnguoithan.getText());
                ptst.setString(10,angaynhapvien.getText());
                ptst.setString(11,achuandoan.getText());
                ptst.setString(12,aphong.getText());
                ptst.setString(13,ahenkham.getText());
                ptst.setString(14, s);
                ptst.executeUpdate();
                LoadDataThamKham();
                conn.close();
            } catch(Exception e) {
                    JOptionPane.showMessageDialog(this,e);
            }
    }
    
    private void LocThamKham(String s){
        try{ 
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM thamkham WHERE trangthai=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s);
            ResultSet rs = pstmt.executeQuery();
            DefaultTableModel tm =(DefaultTableModel)TB_danhsach.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getString("phong"),rs.getString("hoten"),rs.getInt("tuoi"),rs.getString("sdt"),rs.getString("doituong"),rs.getString("trangthai")};
                tm.addRow(o);
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,e);
            }
    }
    
    private void LocKhoThuoc(String s){
        try{ 
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM khothuoc WHERE nhom=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s);
            ResultSet rs = pstmt.executeQuery();
            DefaultTableModel tm =(DefaultTableModel)Table_khothuoc.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getString("tenthuoc")};
                tm.addRow(o);
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,e);
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

        btngroupGioiTinh = new javax.swing.ButtonGroup();
        btngroupDoiTuong = new javax.swing.ButtonGroup();
        Tab_Panel = new javax.swing.JTabbedPane();
        TAB_HOME = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Tab_BenhNhan = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tbl_BenhNhan = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        BTN_RefeshBN = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        timkiem1 = new javax.swing.JTextField();
        BTN_TimKiem_BN = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        BTNbenhAN = new javax.swing.JButton();
        BtnAddBenhNhan = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        fid = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        fhoten1 = new javax.swing.JTextField();
        fngaysinh = new javax.swing.JTextField();
        fdiachi1 = new javax.swing.JTextField();
        fkhoadieutri = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ftennguoithan = new javax.swing.JTextField();
        fsdtnguoithan = new javax.swing.JTextField();
        Tab_TiepDon = new javax.swing.JPanel();
        pnhanhchinh = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ahoten = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        angaysinh = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        anghenghiep = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        adiachi = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        atennguoithan = new javax.swing.JTextField();
        asdtnguoithan = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        asdt = new javax.swing.JTextField();
        RBbhyt = new javax.swing.JRadioButton();
        RBthuphi = new javax.swing.JRadioButton();
        RBnam = new javax.swing.JRadioButton();
        RBnu = new javax.swing.JRadioButton();
        jLabel30 = new javax.swing.JLabel();
        atuoi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        acmnd = new javax.swing.JTextField();
        BtnAddBenhNhan1 = new javax.swing.JButton();
        pnquanlynguoibenh = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        angaynhapvien = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        achuandoan = new javax.swing.JTextArea();
        jLabel32 = new javax.swing.JLabel();
        aphong = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        ahenkham = new javax.swing.JTextField();
        BTN_InGiayHen = new javax.swing.JButton();
        ComboBox_trangthai = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TB_danhsach = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        ComboBox_trangthai_LOC = new javax.swing.JComboBox<>();
        Panel_KeThuoc = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Table_kethuoc = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        Table_khothuoc = new javax.swing.JTable();
        ComboBoxLoc = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        Btn_XoaThuocKhoiDanhSach = new javax.swing.JButton();
        Btn_InKeThuoc = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        Btn_Updatethamkham = new javax.swing.JButton();
        Tab_ThongTin = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        Btn_doimatkhau = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Panel_in = new javax.swing.JPanel();
        Pntitletrai = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        ngayintoathuoc = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        tentoathuoc = new javax.swing.JLabel();
        tuoitoathuoc = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txt_bill = new javax.swing.JTextArea();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        Tab_Bacsi = new javax.swing.JPanel();
        Panel_ThongtinBacSi1 = new javax.swing.JPanel();
        Label_thongtinbacsi1 = new javax.swing.JLabel();
        fhoten = new javax.swing.JTextField();
        Label_hotenbacsi1 = new javax.swing.JLabel();
        Label_gioitinhbacsi1 = new javax.swing.JLabel();
        Label_tuoibacsi1 = new javax.swing.JLabel();
        Label_chuyenkhoabacsi1 = new javax.swing.JLabel();
        Label_sdtbacsi1 = new javax.swing.JLabel();
        Label_emailbacsi1 = new javax.swing.JLabel();
        Label_diachibacsi1 = new javax.swing.JLabel();
        ftuoi = new javax.swing.JTextField();
        fgioitinh = new javax.swing.JComboBox<>();
        fchuyenkhoa = new javax.swing.JComboBox<>();
        fdiachi = new javax.swing.JTextField();
        fsdt = new javax.swing.JTextField();
        femail = new javax.swing.JTextField();
        btnsave = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnxoathongtin = new javax.swing.JButton();
        Label_emailbacsi2 = new javax.swing.JLabel();
        fusername = new javax.swing.JTextField();
        Panel_QuanLibacsi = new javax.swing.JPanel();
        btnrefesh = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        Table_bacsi = new javax.swing.JTable();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        idchon = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        timkiem = new javax.swing.JTextField();
        btntimkiem = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Panel_GiayHen = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        giayhen_ngaysinh = new javax.swing.JLabel();
        giayhen_hoten = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        giayhen_gioitinh = new javax.swing.JLabel();
        giayhen_diachi = new javax.swing.JLabel();
        giayhen_chuandoan = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        giayhen_ngayhen = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        Panel_ChucNang = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        BTN_BacSi = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        Btn_ThongKe = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        MENUBAR = new javax.swing.JMenuBar();
        itemmenuTEP = new javax.swing.JMenu();
        itemURL = new javax.swing.JMenuItem();
        itemDangXuat = new javax.swing.JMenuItem();
        itemThoat = new javax.swing.JMenuItem();
        itemabout = new javax.swing.JMenu();
        itemdeveloper = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý bệnh viện");
        setBackground(new java.awt.Color(0, 221, 193));
        setPreferredSize(new java.awt.Dimension(1200, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tab_Panel.setBackground(new java.awt.Color(0, 221, 193));
        Tab_Panel.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        Tab_Panel.setMaximumSize(new java.awt.Dimension(1100, 680));
        Tab_Panel.setPreferredSize(new java.awt.Dimension(1100, 680));

        TAB_HOME.setBackground(new java.awt.Color(255, 255, 255));
        TAB_HOME.setPreferredSize(new java.awt.Dimension(1100, 680));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/HOME-1.png"))); // NOI18N

        javax.swing.GroupLayout TAB_HOMELayout = new javax.swing.GroupLayout(TAB_HOME);
        TAB_HOME.setLayout(TAB_HOMELayout);
        TAB_HOMELayout.setHorizontalGroup(
            TAB_HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1084, Short.MAX_VALUE)
        );
        TAB_HOMELayout.setVerticalGroup(
            TAB_HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TAB_HOMELayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Tab_Panel.addTab("", TAB_HOME);

        Tab_BenhNhan.setBackground(new java.awt.Color(255, 255, 255));
        Tab_BenhNhan.setPreferredSize(new java.awt.Dimension(1100, 680));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Tbl_BenhNhan.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        Tbl_BenhNhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Họ tên", "Ngày sinh", "Địa chỉ", "Khoa điều trị"
            }
        ));
        Tbl_BenhNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tbl_BenhNhanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Tbl_BenhNhanMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(Tbl_BenhNhan);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setText("QUẢN LÝ BỆNH NHÂN");

        BTN_RefeshBN.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        BTN_RefeshBN.setText("Refesh");
        BTN_RefeshBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_RefeshBNActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel16.setText("Tìm kiếm theo tên");

        BTN_TimKiem_BN.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        BTN_TimKiem_BN.setText("Tìm kiếm");
        BTN_TimKiem_BN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_TimKiem_BNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 98, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(33, 33, 33)
                        .addComponent(timkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(BTN_TimKiem_BN)
                        .addGap(34, 34, 34)
                        .addComponent(BTN_RefeshBN)
                        .addGap(57, 57, 57))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(jLabel15)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(timkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_TimKiem_BN)
                    .addComponent(BTN_RefeshBN))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        BTNbenhAN.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        BTNbenhAN.setText("Xem bệnh án");
        BTNbenhAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNbenhANActionPerformed(evt);
            }
        });

        BtnAddBenhNhan.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        BtnAddBenhNhan.setText("Thêm bệnh nhân");
        BtnAddBenhNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddBenhNhanActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel20.setText("ID");

        fid.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel21.setText("Họ tên:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel22.setText("Ngày sinh:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel23.setText("Địa chỉ");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel24.setText("Khoa điều trị");

        fhoten1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        fngaysinh.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        fdiachi1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        fkhoadieutri.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fid)
                    .addComponent(fhoten1)
                    .addComponent(fkhoadieutri, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(fdiachi1)
                    .addComponent(fngaysinh))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(fid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(fhoten1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(fngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(fdiachi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(fkhoadieutri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel25.setText("THÔNG TIN CHI TIẾT");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel17.setText("THÔNG TIN NGƯỜI NHÀ");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel18.setText("Họ tên người nhà:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel19.setText("Số điện thoại:");

        ftennguoithan.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        fsdtnguoithan.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ftennguoithan, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fsdtnguoithan, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(81, 81, 81))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(9, 9, 9)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(ftennguoithan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(fsdtnguoithan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(BtnAddBenhNhan)
                        .addGap(40, 40, 40)
                        .addComponent(BTNbenhAN))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addGap(44, 44, 44))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAddBenhNhan)
                    .addComponent(BTNbenhAN))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Tab_BenhNhanLayout = new javax.swing.GroupLayout(Tab_BenhNhan);
        Tab_BenhNhan.setLayout(Tab_BenhNhanLayout);
        Tab_BenhNhanLayout.setHorizontalGroup(
            Tab_BenhNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tab_BenhNhanLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Tab_BenhNhanLayout.setVerticalGroup(
            Tab_BenhNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tab_BenhNhanLayout.createSequentialGroup()
                .addGroup(Tab_BenhNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Tab_Panel.addTab("", Tab_BenhNhan);

        Tab_TiepDon.setBackground(new java.awt.Color(255, 255, 255));
        Tab_TiepDon.setPreferredSize(new java.awt.Dimension(1100, 680));

        pnhanhchinh.setBackground(new java.awt.Color(255, 255, 255));
        pnhanhchinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnhanhchinh.setPreferredSize(new java.awt.Dimension(600, 300));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("I. HÀNH CHÍNH");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("1. Họ và tên: ");

        ahoten.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Sinh năm:");

        angaysinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        angaysinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                angaysinhActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("3. Giới tính:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("4. Nghề nghiệp:");

        anghenghiep.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("5. Địa chỉ:");

        adiachi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("7. Đối tượng:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("9. Thông tin người nhà:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Họ tên:");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("SĐT:");

        atennguoithan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        asdtnguoithan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setText("6. SĐT:");

        asdt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btngroupDoiTuong.add(RBbhyt);
        RBbhyt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RBbhyt.setText("BHYT");
        RBbhyt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBbhytActionPerformed(evt);
            }
        });

        btngroupDoiTuong.add(RBthuphi);
        RBthuphi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RBthuphi.setText("Thu phí");
        RBthuphi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBthuphiActionPerformed(evt);
            }
        });

        btngroupGioiTinh.add(RBnam);
        RBnam.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RBnam.setText("Nam");
        RBnam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBnamActionPerformed(evt);
            }
        });

        btngroupGioiTinh.add(RBnu);
        RBnu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        RBnu.setText("Nữ");
        RBnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBnuActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("2. Tuổi:");

        atuoi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        atuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atuoiActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("8. Số CMND/Căn cước:");

        acmnd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout pnhanhchinhLayout = new javax.swing.GroupLayout(pnhanhchinh);
        pnhanhchinh.setLayout(pnhanhchinhLayout);
        pnhanhchinhLayout.setHorizontalGroup(
            pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnhanhchinhLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnhanhchinhLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnhanhchinhLayout.createSequentialGroup()
                        .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnhanhchinhLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(RBbhyt)
                                .addGap(18, 18, 18)
                                .addComponent(RBthuphi))
                            .addGroup(pnhanhchinhLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(adiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnhanhchinhLayout.createSequentialGroup()
                                .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnhanhchinhLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(ahoten, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnhanhchinhLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(RBnam)
                                        .addGap(10, 10, 10)
                                        .addComponent(RBnu)))
                                .addGap(18, 18, 18)
                                .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnhanhchinhLayout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(atuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8)
                                        .addGap(11, 11, 11)
                                        .addComponent(angaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnhanhchinhLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(anghenghiep, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)))))
                        .addContainerGap(46, Short.MAX_VALUE))))
            .addGroup(pnhanhchinhLayout.createSequentialGroup()
                .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnhanhchinhLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnhanhchinhLayout.createSequentialGroup()
                        .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnhanhchinhLayout.createSequentialGroup()
                                .addGap(312, 312, 312)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnhanhchinhLayout.createSequentialGroup()
                                    .addGap(69, 69, 69)
                                    .addComponent(jLabel27)
                                    .addGap(18, 18, 18)
                                    .addComponent(atennguoithan, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(86, 86, 86)
                                    .addComponent(jLabel28))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnhanhchinhLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(asdt, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(asdtnguoithan)
                            .addComponent(acmnd))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnhanhchinhLayout.setVerticalGroup(
            pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnhanhchinhLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnhanhchinhLayout.createSequentialGroup()
                        .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(ahoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(RBnam)
                            .addComponent(RBnu)))
                    .addGroup(pnhanhchinhLayout.createSequentialGroup()
                        .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(angaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(atuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(anghenghiep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(asdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(RBbhyt)
                    .addComponent(RBthuphi)
                    .addComponent(jLabel5)
                    .addComponent(acmnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnhanhchinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(atennguoithan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(asdtnguoithan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(23, 23, 23))
        );

        BtnAddBenhNhan1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        BtnAddBenhNhan1.setText("Lưu mới");
        BtnAddBenhNhan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddBenhNhan1ActionPerformed(evt);
            }
        });

        pnquanlynguoibenh.setBackground(new java.awt.Color(255, 255, 255));
        pnquanlynguoibenh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnquanlynguoibenh.setPreferredSize(new java.awt.Dimension(450, 300));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("II. THÔNG TIN KHÁM");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("10. Ngày vào:");

        angaynhapvien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setText("11. Tình trạng BN:");

        achuandoan.setColumns(20);
        achuandoan.setRows(5);
        jScrollPane3.setViewportView(achuandoan);

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("12. Phòng:");

        aphong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel35.setText("13. Hẹn khám:");

        ahenkham.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ahenkham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ahenkhamActionPerformed(evt);
            }
        });

        BTN_InGiayHen.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        BTN_InGiayHen.setText("In giấy hẹn");
        BTN_InGiayHen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_InGiayHenActionPerformed(evt);
            }
        });

        ComboBox_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ", "Nhập viện", "Đã xong", "Hẹn khám" }));

        javax.swing.GroupLayout pnquanlynguoibenhLayout = new javax.swing.GroupLayout(pnquanlynguoibenh);
        pnquanlynguoibenh.setLayout(pnquanlynguoibenhLayout);
        pnquanlynguoibenhLayout.setHorizontalGroup(
            pnquanlynguoibenhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnquanlynguoibenhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnquanlynguoibenhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnquanlynguoibenhLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ComboBox_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnquanlynguoibenhLayout.createSequentialGroup()
                        .addGroup(pnquanlynguoibenhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnquanlynguoibenhLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(pnquanlynguoibenhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnquanlynguoibenhLayout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addGap(18, 18, 18)
                                        .addComponent(aphong, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnquanlynguoibenhLayout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addGap(18, 18, 18)
                                        .addComponent(ahenkham, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(BTN_InGiayHen))))
                            .addGroup(pnquanlynguoibenhLayout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 23, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnquanlynguoibenhLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel33)
                .addGap(18, 18, 18)
                .addComponent(angaynhapvien, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnquanlynguoibenhLayout.setVerticalGroup(
            pnquanlynguoibenhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnquanlynguoibenhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnquanlynguoibenhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(ComboBox_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnquanlynguoibenhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(angaynhapvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(pnquanlynguoibenhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnquanlynguoibenhLayout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(0, 67, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(pnquanlynguoibenhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(aphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnquanlynguoibenhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(ahenkham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_InGiayHen))
                .addGap(24, 24, 24))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        TB_danhsach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Phòng", "Họ tên", "Tuổi", "SĐT", "Đối tượng", "Trạng thái"
            }
        ));
        TB_danhsach.setMinimumSize(new java.awt.Dimension(75, 250));
        TB_danhsach.setPreferredSize(new java.awt.Dimension(320, 250));
        TB_danhsach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TB_danhsachMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TB_danhsach);
        if (TB_danhsach.getColumnModel().getColumnCount() > 0) {
            TB_danhsach.getColumnModel().getColumn(0).setPreferredWidth(40);
            TB_danhsach.getColumnModel().getColumn(1).setPreferredWidth(120);
            TB_danhsach.getColumnModel().getColumn(2).setPreferredWidth(30);
            TB_danhsach.getColumnModel().getColumn(3).setPreferredWidth(60);
            TB_danhsach.getColumnModel().getColumn(4).setPreferredWidth(60);
            TB_danhsach.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("DANH SÁCH TIẾP NHẬN");

        ComboBox_trangthai_LOC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chờ", "Nhập viện", "Đã xong", "Hẹn khám" }));
        ComboBox_trangthai_LOC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBox_trangthai_LOCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel37)
                .addGap(73, 73, 73)
                .addComponent(ComboBox_trangthai_LOC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(ComboBox_trangthai_LOC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Panel_KeThuoc.setBackground(new java.awt.Color(255, 255, 255));
        Panel_KeThuoc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Panel_KeThuoc.setPreferredSize(new java.awt.Dimension(550, 300));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("II. KÊ THUỐC");

        Table_kethuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã thuốc", "Tên thuốc", "SL", "Cách dùng", "Ghi chú"
            }
        ));
        jScrollPane5.setViewportView(Table_kethuoc);
        if (Table_kethuoc.getColumnModel().getColumnCount() > 0) {
            Table_kethuoc.getColumnModel().getColumn(0).setPreferredWidth(10);
            Table_kethuoc.getColumnModel().getColumn(1).setPreferredWidth(35);
            Table_kethuoc.getColumnModel().getColumn(2).setPreferredWidth(130);
            Table_kethuoc.getColumnModel().getColumn(3).setPreferredWidth(10);
            Table_kethuoc.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        Table_khothuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "DS thuốc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table_khothuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_khothuocMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(Table_khothuoc);

        ComboBoxLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giảm đau", "Kháng sinh", "Dạ dày", "Da liễu", "Não" }));
        ComboBoxLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxLocActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setText("Nhóm thuốc:");

        Btn_XoaThuocKhoiDanhSach.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        Btn_XoaThuocKhoiDanhSach.setText("Xóa");
        Btn_XoaThuocKhoiDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_XoaThuocKhoiDanhSachActionPerformed(evt);
            }
        });

        Btn_InKeThuoc.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        Btn_InKeThuoc.setText("In toa thuốc");
        Btn_InKeThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_InKeThuocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_KeThuocLayout = new javax.swing.GroupLayout(Panel_KeThuoc);
        Panel_KeThuoc.setLayout(Panel_KeThuocLayout);
        Panel_KeThuocLayout.setHorizontalGroup(
            Panel_KeThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_KeThuocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_KeThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_KeThuocLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_KeThuocLayout.createSequentialGroup()
                        .addGroup(Panel_KeThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_KeThuocLayout.createSequentialGroup()
                                .addGroup(Panel_KeThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36)
                                    .addGroup(Panel_KeThuocLayout.createSequentialGroup()
                                        .addGap(173, 173, 173)
                                        .addComponent(Btn_InKeThuoc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Btn_XoaThuocKhoiDanhSach)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(Panel_KeThuocLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        Panel_KeThuocLayout.setVerticalGroup(
            Panel_KeThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_KeThuocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_KeThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_KeThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_KeThuocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_XoaThuocKhoiDanhSach)
                    .addComponent(Btn_InKeThuoc))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton8.setText("Nhập viện");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        Btn_Updatethamkham.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        Btn_Updatethamkham.setText("Cập nhật lại");
        Btn_Updatethamkham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_UpdatethamkhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Tab_TiepDonLayout = new javax.swing.GroupLayout(Tab_TiepDon);
        Tab_TiepDon.setLayout(Tab_TiepDonLayout);
        Tab_TiepDonLayout.setHorizontalGroup(
            Tab_TiepDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tab_TiepDonLayout.createSequentialGroup()
                .addGroup(Tab_TiepDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Tab_TiepDonLayout.createSequentialGroup()
                        .addComponent(Panel_KeThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Tab_TiepDonLayout.createSequentialGroup()
                        .addComponent(pnhanhchinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnquanlynguoibenh, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tab_TiepDonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Btn_Updatethamkham)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAddBenhNhan1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addGap(352, 352, 352))
        );
        Tab_TiepDonLayout.setVerticalGroup(
            Tab_TiepDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tab_TiepDonLayout.createSequentialGroup()
                .addGroup(Tab_TiepDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnhanhchinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnquanlynguoibenh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Tab_TiepDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Panel_KeThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(Tab_TiepDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAddBenhNhan1)
                    .addComponent(jButton8)
                    .addComponent(Btn_Updatethamkham))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        Tab_Panel.addTab("", Tab_TiepDon);

        Tab_ThongTin.setBackground(new java.awt.Color(0, 221, 193));
        Tab_ThongTin.setMaximumSize(new java.awt.Dimension(1100, 680));
        Tab_ThongTin.setPreferredSize(new java.awt.Dimension(1100, 680));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel47.setText("Họ tên:");

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel50.setText("Chức vụ:");

        Btn_doimatkhau.setText("Đổi mật khẩu");
        Btn_doimatkhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_doimatkhauActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout Tab_ThongTinLayout = new javax.swing.GroupLayout(Tab_ThongTin);
        Tab_ThongTin.setLayout(Tab_ThongTinLayout);
        Tab_ThongTinLayout.setHorizontalGroup(
            Tab_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tab_ThongTinLayout.createSequentialGroup()
                .addGroup(Tab_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Tab_ThongTinLayout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addGroup(Tab_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(Tab_ThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Tab_ThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(Tab_ThongTinLayout.createSequentialGroup()
                        .addGap(391, 391, 391)
                        .addComponent(Btn_doimatkhau)))
                .addContainerGap(528, Short.MAX_VALUE))
        );
        Tab_ThongTinLayout.setVerticalGroup(
            Tab_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tab_ThongTinLayout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addGroup(Tab_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(Tab_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(Btn_doimatkhau)
                .addContainerGap(347, Short.MAX_VALUE))
        );

        Tab_Panel.addTab("", Tab_ThongTin);

        Panel_in.setBackground(new java.awt.Color(146, 151, 161));
        Panel_in.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Panel_in.setPreferredSize(new java.awt.Dimension(612, 800));

        Pntitletrai.setBackground(new java.awt.Color(146, 151, 161));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel40.setText("SỞ Y TẾ HÀ NỘI");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setText("BỆNH VIÊN ĐA KHOA KMA");

        javax.swing.GroupLayout PntitletraiLayout = new javax.swing.GroupLayout(Pntitletrai);
        Pntitletrai.setLayout(PntitletraiLayout);
        PntitletraiLayout.setHorizontalGroup(
            PntitletraiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PntitletraiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PntitletraiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PntitletraiLayout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(175, 175, 175))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PntitletraiLayout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(220, 220, 220))))
        );
        PntitletraiLayout.setVerticalGroup(
            PntitletraiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PntitletraiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ngayintoathuoc.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        ngayintoathuoc.setForeground(new java.awt.Color(153, 0, 0));
        ngayintoathuoc.setText("jLabel43");

        jLabel42.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel42.setText("Thời gian:");

        jLabel44.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel44.setText("Họ và tên: ");

        tentoathuoc.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        tentoathuoc.setForeground(new java.awt.Color(153, 0, 0));
        tentoathuoc.setText("Nguyễn Đình Duẩn ABCDE");

        tuoitoathuoc.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        tuoitoathuoc.setForeground(new java.awt.Color(153, 0, 0));
        tuoitoathuoc.setText("20");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel48.setText("TOA THUỐC");

        jLabel39.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel39.setText("Tuổi:");

        txt_bill.setBackground(new java.awt.Color(146, 151, 161));
        txt_bill.setColumns(20);
        txt_bill.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        txt_bill.setRows(5);
        txt_bill.setOpaque(false);
        jScrollPane7.setViewportView(txt_bill);

        jLabel43.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel43.setText("Bác sĩ kê thuốc");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel45.setText("Tên nè");

        jLabel46.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel46.setText("Đưa phiếu này cho nhân viên nhà thuốc bệnh viện để mua thuốc đúng chỉ định!");

        javax.swing.GroupLayout Panel_inLayout = new javax.swing.GroupLayout(Panel_in);
        Panel_in.setLayout(Panel_inLayout);
        Panel_inLayout.setHorizontalGroup(
            Panel_inLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_inLayout.createSequentialGroup()
                .addGap(0, 31, Short.MAX_VALUE)
                .addGroup(Panel_inLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_inLayout.createSequentialGroup()
                        .addGroup(Panel_inLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_inLayout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(18, 18, 18)
                                .addComponent(ngayintoathuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_inLayout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(18, 18, 18)
                                .addComponent(tentoathuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tuoitoathuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(149, 149, 149))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_inLayout.createSequentialGroup()
                        .addGroup(Panel_inLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel43)
                            .addComponent(jLabel46))
                        .addGap(79, 79, 79))
                    .addGroup(Panel_inLayout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(Panel_inLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_inLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_inLayout.createSequentialGroup()
                        .addGroup(Panel_inLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Pntitletrai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_inLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel48)
                        .addGap(225, 225, 225))))
        );
        Panel_inLayout.setVerticalGroup(
            Panel_inLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_inLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(Pntitletrai, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel48)
                .addGap(43, 43, 43)
                .addGroup(Panel_inLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(ngayintoathuoc))
                .addGap(18, 18, 18)
                .addGroup(Panel_inLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(tentoathuoc)
                    .addComponent(tuoitoathuoc)
                    .addComponent(jLabel39))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46)
                .addGap(18, 18, 18)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(281, Short.MAX_VALUE)
                .addComponent(Panel_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(Panel_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tab_Panel.addTab("", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setText("TỶ LỆ SỐ LẦN THĂM KHÁM SO VỚI SỐ LẦN NHẬP VIỆN");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel55.setText("5");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel56.setText("6");

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel57.setText("Số lần thăm khám:");

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel58.setText("Số lần nhập viện:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(jLabel54))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(368, 368, 368)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel58)
                            .addComponent(jLabel57))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55)
                            .addComponent(jLabel56))))
                .addContainerGap(356, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jLabel55))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel56))
                .addContainerGap(325, Short.MAX_VALUE))
        );

        Tab_Panel.addTab("", jPanel2);

        Panel_ThongtinBacSi1.setBackground(new java.awt.Color(255, 255, 255));
        Panel_ThongtinBacSi1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Label_thongtinbacsi1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Label_thongtinbacsi1.setText("THÔNG TIN CHI TIẾT");

        fhoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fhotenActionPerformed(evt);
            }
        });

        Label_hotenbacsi1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_hotenbacsi1.setText("Họ tên");

        Label_gioitinhbacsi1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_gioitinhbacsi1.setText("Giới tính");

        Label_tuoibacsi1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_tuoibacsi1.setText("Tuổi");

        Label_chuyenkhoabacsi1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_chuyenkhoabacsi1.setText("Chuyên khoa");

        Label_sdtbacsi1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_sdtbacsi1.setText("Số điện thoại");

        Label_emailbacsi1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_emailbacsi1.setText("Email");

        Label_diachibacsi1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_diachibacsi1.setText("Địa chỉ");

        ftuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftuoiActionPerformed(evt);
            }
        });

        fgioitinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fgioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        fchuyenkhoa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fchuyenkhoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cấp cứu", "Chấn thương chỉnh hình", "Bỏng", "Ngoại thần kinh", "Phụ sản", "Nhi", "Xét nghiệm" }));

        btnsave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnsave.setText("Thêm vào danh sách");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnupdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnupdate.setText("Sửa");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btnxoathongtin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnxoathongtin.setText("Xóa thông tin");
        btnxoathongtin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoathongtinActionPerformed(evt);
            }
        });

        Label_emailbacsi2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_emailbacsi2.setText("Username");

        fusername.setEditable(false);

        javax.swing.GroupLayout Panel_ThongtinBacSi1Layout = new javax.swing.GroupLayout(Panel_ThongtinBacSi1);
        Panel_ThongtinBacSi1.setLayout(Panel_ThongtinBacSi1Layout);
        Panel_ThongtinBacSi1Layout.setHorizontalGroup(
            Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ThongtinBacSi1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Panel_ThongtinBacSi1Layout.createSequentialGroup()
                        .addComponent(Label_sdtbacsi1)
                        .addGap(18, 18, 18)
                        .addComponent(fsdt))
                    .addGroup(Panel_ThongtinBacSi1Layout.createSequentialGroup()
                        .addGroup(Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Label_chuyenkhoabacsi1)
                            .addComponent(Label_tuoibacsi1)
                            .addComponent(Label_hotenbacsi1)
                            .addComponent(Label_diachibacsi1))
                        .addGap(18, 18, 18)
                        .addGroup(Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fhoten)
                            .addGroup(Panel_ThongtinBacSi1Layout.createSequentialGroup()
                                .addComponent(ftuoi)
                                .addGap(18, 18, 18)
                                .addComponent(Label_gioitinhbacsi1)
                                .addGap(18, 18, 18)
                                .addComponent(fgioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fchuyenkhoa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Panel_ThongtinBacSi1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnxoathongtin)
                            .addGroup(Panel_ThongtinBacSi1Layout.createSequentialGroup()
                                .addComponent(btnupdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnsave))
                            .addGroup(Panel_ThongtinBacSi1Layout.createSequentialGroup()
                                .addComponent(Label_emailbacsi1)
                                .addGap(18, 18, 18)
                                .addComponent(femail, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ThongtinBacSi1Layout.createSequentialGroup()
                        .addComponent(Label_emailbacsi2)
                        .addGap(18, 18, 18)
                        .addComponent(fusername, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(125, 125, 125))
            .addGroup(Panel_ThongtinBacSi1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(Label_thongtinbacsi1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_ThongtinBacSi1Layout.setVerticalGroup(
            Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ThongtinBacSi1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(Label_thongtinbacsi1)
                .addGap(34, 34, 34)
                .addGroup(Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fhoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_hotenbacsi1))
                .addGap(18, 18, 18)
                .addGroup(Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_tuoibacsi1)
                    .addComponent(ftuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_gioitinhbacsi1)
                    .addComponent(fgioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_chuyenkhoabacsi1)
                    .addComponent(fchuyenkhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_diachibacsi1)
                    .addComponent(fdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_sdtbacsi1)
                    .addComponent(fsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_emailbacsi1)
                    .addComponent(femail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_emailbacsi2)
                    .addComponent(fusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btnxoathongtin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_ThongtinBacSi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsave)
                    .addComponent(btnupdate))
                .addGap(29, 29, 29))
        );

        Panel_QuanLibacsi.setBackground(new java.awt.Color(255, 255, 255));
        Panel_QuanLibacsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnrefesh.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnrefesh.setText("Refesh");
        btnrefesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefeshActionPerformed(evt);
            }
        });

        Table_bacsi.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        Table_bacsi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Họ tên", "Tuổi", "Chuyên khoa"
            }
        ));
        Table_bacsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_bacsiMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(Table_bacsi);
        if (Table_bacsi.getColumnModel().getColumnCount() > 0) {
            Table_bacsi.getColumnModel().getColumn(0).setPreferredWidth(50);
            Table_bacsi.getColumnModel().getColumn(1).setPreferredWidth(200);
            Table_bacsi.getColumnModel().getColumn(2).setPreferredWidth(50);
            Table_bacsi.getColumnModel().getColumn(3).setPreferredWidth(120);
        }

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel59.setText("QUẢN LÝ BÁC SỸ");

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel60.setText("Đang chọn");

        idchon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idchonActionPerformed(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel61.setText("Tìm kiếm theo tên");

        btntimkiem.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btntimkiem.setText("Tìm kiếm");
        btntimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemActionPerformed(evt);
            }
        });

        jButton7.setText("Xóa khỏi danh sách");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_QuanLibacsiLayout = new javax.swing.GroupLayout(Panel_QuanLibacsi);
        Panel_QuanLibacsi.setLayout(Panel_QuanLibacsiLayout);
        Panel_QuanLibacsiLayout.setHorizontalGroup(
            Panel_QuanLibacsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_QuanLibacsiLayout.createSequentialGroup()
                .addGroup(Panel_QuanLibacsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_QuanLibacsiLayout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idchon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Panel_QuanLibacsiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane8)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_QuanLibacsiLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel59)
                .addGap(295, 295, 295))
            .addGroup(Panel_QuanLibacsiLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel61)
                .addGap(18, 18, 18)
                .addComponent(timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btntimkiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnrefesh)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        Panel_QuanLibacsiLayout.setVerticalGroup(
            Panel_QuanLibacsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_QuanLibacsiLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel59)
                .addGap(30, 30, 30)
                .addGroup(Panel_QuanLibacsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_QuanLibacsiLayout.createSequentialGroup()
                        .addGroup(Panel_QuanLibacsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel61)
                            .addComponent(timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(Panel_QuanLibacsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(idchon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7))
                        .addGap(51, 51, 51))
                    .addGroup(Panel_QuanLibacsiLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(Panel_QuanLibacsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btntimkiem)
                            .addComponent(btnrefesh))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout Tab_BacsiLayout = new javax.swing.GroupLayout(Tab_Bacsi);
        Tab_Bacsi.setLayout(Tab_BacsiLayout);
        Tab_BacsiLayout.setHorizontalGroup(
            Tab_BacsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tab_BacsiLayout.createSequentialGroup()
                .addComponent(Panel_QuanLibacsi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Panel_ThongtinBacSi1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Tab_BacsiLayout.setVerticalGroup(
            Tab_BacsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tab_BacsiLayout.createSequentialGroup()
                .addGroup(Tab_BacsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel_QuanLibacsi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Tab_BacsiLayout.createSequentialGroup()
                        .addComponent(Panel_ThongtinBacSi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        Tab_Panel.addTab("", Tab_Bacsi);

        Panel_GiayHen.setBackground(new java.awt.Color(255, 255, 255));
        Panel_GiayHen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Panel_GiayHen.setPreferredSize(new java.awt.Dimension(612, 800));

        jLabel64.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel64.setText("Chuẩn đoán:");

        jLabel65.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel65.setText("Họ và tên: ");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel66.setText("GIẤY HẸN");

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel63.setText("BỆNH VIÊN ĐA KHOA KMA");

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel62.setText("SỞ Y TẾ HÀ NỘI");

        jLabel71.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel71.setText("Địa chỉ:");

        jLabel72.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel72.setText("Sinh ngày:");

        giayhen_ngaysinh.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        giayhen_ngaysinh.setText("15/12/1999");

        giayhen_hoten.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        giayhen_hoten.setText("Nguyễn Đình Duẩn Xóm 3 xã nghi diên");
        giayhen_hoten.setPreferredSize(new java.awt.Dimension(300, 18));

        jLabel75.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel75.setText("Giới tính:");

        giayhen_gioitinh.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        giayhen_gioitinh.setText("Nam");

        giayhen_diachi.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        giayhen_diachi.setText("Địa chỉ:");

        giayhen_chuandoan.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        giayhen_chuandoan.setText("Chuẩn đoán:");

        jLabel79.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel79.setText("Hẹn tái khám vào ngày");

        giayhen_ngayhen.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        giayhen_ngayhen.setText("22/12/2022");

        jLabel81.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel81.setText("(hoặc bất cứ khi nào có triệu chứng)");

        jLabel82.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel82.setText("Bác sĩ khám");

        jLabel83.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel83.setText("đến phòng khám để khám và chuẩn đoán lại!");

        javax.swing.GroupLayout Panel_GiayHenLayout = new javax.swing.GroupLayout(Panel_GiayHen);
        Panel_GiayHen.setLayout(Panel_GiayHenLayout);
        Panel_GiayHenLayout.setHorizontalGroup(
            Panel_GiayHenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_GiayHenLayout.createSequentialGroup()
                .addGroup(Panel_GiayHenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Panel_GiayHenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Panel_GiayHenLayout.createSequentialGroup()
                            .addGap(209, 209, 209)
                            .addGroup(Panel_GiayHenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_GiayHenLayout.createSequentialGroup()
                                    .addComponent(jLabel62)
                                    .addGap(45, 45, 45))
                                .addGroup(Panel_GiayHenLayout.createSequentialGroup()
                                    .addGap(53, 53, 53)
                                    .addComponent(jLabel66)
                                    .addGap(42, 42, 42))))
                        .addGroup(Panel_GiayHenLayout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addComponent(jLabel79)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(giayhen_ngayhen, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(Panel_GiayHenLayout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(Panel_GiayHenLayout.createSequentialGroup()
                            .addGap(93, 93, 93)
                            .addComponent(jLabel82))
                        .addGroup(Panel_GiayHenLayout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addGroup(Panel_GiayHenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel71)
                                .addComponent(jLabel64))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(Panel_GiayHenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(giayhen_chuandoan, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(giayhen_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Panel_GiayHenLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel72)
                        .addGap(28, 28, 28)
                        .addComponent(giayhen_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Panel_GiayHenLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(giayhen_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel75)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(giayhen_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        Panel_GiayHenLayout.setVerticalGroup(
            Panel_GiayHenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_GiayHenLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel66)
                .addGap(31, 31, 31)
                .addGroup(Panel_GiayHenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(jLabel75)
                    .addComponent(giayhen_gioitinh)
                    .addComponent(giayhen_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_GiayHenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(giayhen_ngaysinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_GiayHenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(giayhen_diachi))
                .addGap(18, 18, 18)
                .addGroup(Panel_GiayHenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(giayhen_chuandoan, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64))
                .addGap(30, 30, 30)
                .addGroup(Panel_GiayHenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(giayhen_ngayhen)
                    .addComponent(jLabel81))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel83)
                .addGap(30, 30, 30)
                .addComponent(jLabel82)
                .addContainerGap(359, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(246, Short.MAX_VALUE)
                .addComponent(Panel_GiayHen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(226, 226, 226))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_GiayHen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tab_Panel.addTab("", jPanel4);

        getContentPane().add(Tab_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 0, 1105, 670));

        Panel_ChucNang.setBackground(new java.awt.Color(0, 221, 193));
        Panel_ChucNang.setMaximumSize(new java.awt.Dimension(100, 680));
        Panel_ChucNang.setPreferredSize(new java.awt.Dimension(100, 690));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/patient.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(100, 100));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/profile-btn.png"))); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(100, 100));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        BTN_BacSi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/doctor-btn.png"))); // NOI18N
        BTN_BacSi.setPreferredSize(new java.awt.Dimension(100, 100));
        BTN_BacSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_BacSiActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Quản lý bác sĩ");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Bệnh nhân");

        jLabel53.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Thống kê");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Thông tin");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/patient2-btn.png"))); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(100, 100));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Tiếp nhận");

        Btn_ThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/Analysis.png"))); // NOI18N
        Btn_ThongKe.setPreferredSize(new java.awt.Dimension(98, 98));
        Btn_ThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ThongKeActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 221, 193));
        jButton6.setText("HOME");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_ChucNangLayout = new javax.swing.GroupLayout(Panel_ChucNang);
        Panel_ChucNang.setLayout(Panel_ChucNangLayout);
        Panel_ChucNangLayout.setHorizontalGroup(
            Panel_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ChucNangLayout.createSequentialGroup()
                .addGroup(Panel_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_ChucNangLayout.createSequentialGroup()
                        .addGroup(Panel_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_ChucNangLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(Panel_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel2)))
                            .addGroup(Panel_ChucNangLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addGroup(Panel_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BTN_BacSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Btn_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Panel_ChucNangLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel53)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        Panel_ChucNangLayout.setVerticalGroup(
            Panel_ChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_ChucNangLayout.createSequentialGroup()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTN_BacSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_ThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel53)
                .addGap(19, 19, 19))
        );

        getContentPane().add(Panel_ChucNang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 660));

        MENUBAR.setBackground(new java.awt.Color(255, 255, 255));
        MENUBAR.setPreferredSize(new java.awt.Dimension(1200, 20));

        itemmenuTEP.setBackground(new java.awt.Color(255, 255, 255));
        itemmenuTEP.setText("Tệp");
        itemmenuTEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemmenuTEPActionPerformed(evt);
            }
        });

        itemURL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/icons8-database-administrator-48.png"))); // NOI18N
        itemURL.setText("Mở Database");
        itemURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemURLActionPerformed(evt);
            }
        });
        itemmenuTEP.add(itemURL);

        itemDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/icons8-logout-48.png"))); // NOI18N
        itemDangXuat.setText("Đăng xuất");
        itemDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDangXuatActionPerformed(evt);
            }
        });
        itemmenuTEP.add(itemDangXuat);

        itemThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/icons8-no-entry-48.png"))); // NOI18N
        itemThoat.setText("Thoát");
        itemThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemThoatActionPerformed(evt);
            }
        });
        itemmenuTEP.add(itemThoat);

        MENUBAR.add(itemmenuTEP);

        itemabout.setBackground(new java.awt.Color(255, 255, 255));
        itemabout.setText("Thông tin");

        itemdeveloper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/icons8-developer-20.png"))); // NOI18N
        itemdeveloper.setText("Nhà phát triển :v");
        itemdeveloper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemdeveloperActionPerformed(evt);
            }
        });
        itemabout.add(itemdeveloper);

        MENUBAR.add(itemabout);

        setJMenuBar(MENUBAR);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_BacSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BacSiActionPerformed
        if(!jLabel52.getText().equals("Viện trưởng")){
            if(JOptionPane.showConfirmDialog(this,"Bạn không được cấp quyền cho truy cập này! Trở về trang chủ?") == JOptionPane.NO_OPTION) {
                
                return;
            }
            else {
                Tab_Panel.setSelectedIndex(0);
            }
        }
        else{
        Tab_Panel.setSelectedIndex(6);
        loadDatabacsi();
        }
    }//GEN-LAST:event_BTN_BacSiActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Tab_Panel.setSelectedIndex(1);
        LoadDatabenhnhan();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void itemURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemURLActionPerformed
         if(!jLabel52.getText().equals("Viện trưởng")){
            if(JOptionPane.showConfirmDialog(this,"Bạn không được cấp quyền cho truy cập này! Trở về trang chủ?") == JOptionPane.NO_OPTION) {
                
                return;
            }
            else {
                Tab_Panel.setSelectedIndex(0);
            }
        }
        else{
        try {
            Desktop.getDesktop().browse(new URL("http://localhost/phpmyadmin/index.php?route=/database/structure&server=1&db=qlbv").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        
    }//GEN-LAST:event_itemURLActionPerformed

    private void itemDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDangXuatActionPerformed
        Login_View obj = new Login_View();
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_itemDangXuatActionPerformed

    private void itemThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemThoatActionPerformed
        dispose();
    }//GEN-LAST:event_itemThoatActionPerformed

    private void itemmenuTEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemmenuTEPActionPerformed

    }//GEN-LAST:event_itemmenuTEPActionPerformed

    private void itemdeveloperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemdeveloperActionPerformed
        nhaphattrien obj = new nhaphattrien();
        obj.setVisible(true);
    }//GEN-LAST:event_itemdeveloperActionPerformed

    private void Tbl_BenhNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tbl_BenhNhanMouseClicked
        int row = Tbl_BenhNhan.getSelectedRow();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM benhnhan WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, (String) Tbl_BenhNhan.getValueAt(row,0));
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                fid.setText(rs.getString("id"));
                fhoten1.setText(rs.getString("hoten"));
                fngaysinh.setText(rs.getString("ngaysinh"));
                fdiachi1.setText(rs.getString("diachi"));
                fkhoadieutri.setText(rs.getString("khoadieutri"));
                ftennguoithan.setText(rs.getString("tennguoithan"));
                fsdtnguoithan.setText(rs.getString("sdtnguoithan"));
            }
            rs.close();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this,e);
        }
    }//GEN-LAST:event_Tbl_BenhNhanMouseClicked

    private void Tbl_BenhNhanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tbl_BenhNhanMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Tbl_BenhNhanMouseEntered

    private void BTN_RefeshBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_RefeshBNActionPerformed
        LoadDatabenhnhan();
        fid.setText("");
        fhoten.setText("");
        fngaysinh.setText("");
        fdiachi.setText("");
        fkhoadieutri.setText("");
        ftennguoithan.setText("");
        fsdtnguoithan.setText("");
        timkiem1.setText("");
    }//GEN-LAST:event_BTN_RefeshBNActionPerformed

    private void BTN_TimKiem_BNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_TimKiem_BNActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM benhnhan WHERE hoten LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%"+timkiem1.getText()+"%");
            ResultSet rs = pstmt.executeQuery();
            DefaultTableModel tm =(DefaultTableModel)Tbl_BenhNhan.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getString("id"),rs.getString("hoten"),rs.getString("ngaysinh"),rs.getString("diachi"),rs.getString("khoadieutri")};
                tm.addRow(o);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,e);
        }
    }//GEN-LAST:event_BTN_TimKiem_BNActionPerformed

    private void BTNbenhANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNbenhANActionPerformed
        benhanbenhnhan benhan = new benhanbenhnhan();
        benhan.SetID(fid.getText());
        benhan.setVisible(true);
    }//GEN-LAST:event_BTNbenhANActionPerformed

    private void BtnAddBenhNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddBenhNhanActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlbv","root","");
            String sql = "INSERT INTO benhnhan(id, hoten, ngaysinh, diachi, tennguoithan, sdtnguoithan, khoadieutri) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setString(2, fhoten.getText());
            ptst.setString(1,fid.getText());
            ptst.setString(3,fngaysinh.getText());
            ptst.setString(4,fdiachi.getText());
            ptst.setString(5,ftennguoithan.getText());
            ptst.setString(6,fsdtnguoithan.getText());
            ptst.setString(7,fkhoadieutri.getText());
            ptst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Cập nhật dữ liệu thành công!");
//            LoadData();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_BtnAddBenhNhanActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
     Tab_Panel.setSelectedIndex(3);
     LoadDatauser();
     
    
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    Tab_Panel.setSelectedIndex(2);
        LoadDataThamKham();
    /*--- Lấy ngày hiện tại để vào ô ngày nhập viện ---*/
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");  
    LocalDateTime now = LocalDateTime.now();  
    angaynhapvien.setText(dtf.format(now));
    /*---   ---*/
    /*--- Ngày hẹn khám ---*/
    ahenkham.setText("__/__/"+year);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void RBbhytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBbhytActionPerformed
        xdoituong = "BHYT";
    }//GEN-LAST:event_RBbhytActionPerformed

    private void RBthuphiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBthuphiActionPerformed
        xdoituong = "Thu phí";
    }//GEN-LAST:event_RBthuphiActionPerformed

    private void RBnamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBnamActionPerformed
        xgioitinh = "Nam";
    }//GEN-LAST:event_RBnamActionPerformed

    private void RBnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBnuActionPerformed
        xgioitinh = "Nữ";
    }//GEN-LAST:event_RBnuActionPerformed

    private void atuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atuoiActionPerformed
        int tinhtuoi = year - Integer.parseInt(atuoi.getText());
        angaysinh.setText("01/01/"+tinhtuoi);
    }//GEN-LAST:event_atuoiActionPerformed

    private void angaysinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_angaysinhActionPerformed
   
    }//GEN-LAST:event_angaysinhActionPerformed

    private void BtnAddBenhNhan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddBenhNhan1ActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlbv","root","");
            String sql = "INSERT INTO thamkham(hoten, tuoi, namsinh, gioitinh, nghenghiep, diachi, sdt, doituong, cmnd, hotennguoinha, sdtnguoinha, ngayvao, tinhtrang, phong, henkham,trangthai) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setString(1, ahoten.getText());
            ptst.setString(2, atuoi.getText());
            ptst.setString(3, angaysinh.getText());
            ptst.setString(4, xgioitinh);
            ptst.setString(5, anghenghiep.getText());
            ptst.setString(6, adiachi.getText());
            ptst.setString(7, asdt.getText());
            ptst.setString(8, xdoituong);
            ptst.setString(9,acmnd.getText());
            ptst.setString(10,atennguoithan.getText());
            ptst.setString(11,asdtnguoithan.getText());
            ptst.setString(12,angaynhapvien.getText());
            ptst.setString(13,achuandoan.getText());
            ptst.setString(14,aphong.getText());
            ptst.setString(15,ahenkham.getText());
            ptst.setString(16, (String) ComboBox_trangthai.getSelectedItem());
            ptst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Cập nhật dữ liệu thành công!");
//            LoadData();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
        LoadDataThamKham();
    }//GEN-LAST:event_BtnAddBenhNhan1ActionPerformed

    private void ahenkhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ahenkhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ahenkhamActionPerformed

    private void ComboBoxLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxLocActionPerformed
        LocKhoThuoc((String) ComboBoxLoc.getSelectedItem());
    }//GEN-LAST:event_ComboBoxLocActionPerformed

    private void Table_khothuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_khothuocMouseClicked
        int row = Table_khothuoc.getSelectedRow();
        String tthuoc = (String) Table_khothuoc.getValueAt(row,0);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM khothuoc WHERE tenthuoc=?"; 
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,tthuoc);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
            DefaultTableModel tblmodel = (DefaultTableModel) Table_kethuoc.getModel();
            Object obj[]={tblmodel.getRowCount() + 1, rs.getString("mathuoc"),rs.getString("tenthuoc"),"",rs.getString("cachdung"),""};
            tblmodel.addRow(obj);
            }
            rs.close();
        } catch(Exception e) {
                JOptionPane.showMessageDialog(this,e);
        }
    }//GEN-LAST:event_Table_khothuocMouseClicked

    private void Btn_XoaThuocKhoiDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_XoaThuocKhoiDanhSachActionPerformed
        DefaultTableModel  model = (DefaultTableModel) Table_kethuoc.getModel();
        int row= Table_kethuoc.getSelectedRow();
        JOptionPane.showMessageDialog(this, "Xóa thuốc này khỏi danh sách!");
        model.removeRow(row);
    }//GEN-LAST:event_Btn_XoaThuocKhoiDanhSachActionPerformed

    private void Btn_InKeThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InKeThuocActionPerformed
        /*--- Lấy ngày hiện tại để vào ô ngày nhập viện ---*/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss/dd/MM/yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        ngayintoathuoc.setText(dtf.format(now));
        /*---   ---*/
        /*--- Viết thuốc vào phiếu  ---*/
        tentoathuoc.setText(ahoten.getText());
        tuoitoathuoc.setText(atuoi.getText());
         /*---   ---*/
         bill();
        /*--- In PANEL in ---*/
        PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("Print Data");
            
            job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                    pf.setOrientation(PageFormat.LANDSCAPE);
                 if(pageNum > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(1,1);
                Panel_in.print(g2);
                return Printable.PAGE_EXISTS;
            }
            });
            boolean comp = job.printDialog();
        if(comp){
            JOptionPane.showMessageDialog(this,"In thành công!","Thông tin",JOptionPane.INFORMATION_MESSAGE);
        try{
        job.print();
        }
        catch (PrinterException ex){
	ex.printStackTrace();
        }
        }
//        try{
//            boolean comp = TextFieldToaThuoc.print();
//            if (comp){
//                JOptionPane.showMessageDialog(this,"In thành công!","Thông tin",JOptionPane.INFORMATION_MESSAGE);
//            } else {
//                JOptionPane.showMessageDialog(this,"Đang in", "Đã in",JOptionPane.ERROR_MESSAGE);
//            }
//        } catch(PrinterException e){
//            JOptionPane.showMessageDialog(this, e);
//        }

        String xtrangthai = "Đã xong";
        Doitrangthai(xtrangthai);
    }//GEN-LAST:event_Btn_InKeThuocActionPerformed

    private void TB_danhsachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_danhsachMouseClicked
        int row = TB_danhsach.getSelectedRow();
//        String s1 = (String) TB_danhsach.getValueAt(row,1);
//        String s2 = (String) TB_danhsach.getValueAt(row,2);
//         ahoten.setText(s1);
        if(row>-1){
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
                    Statement st = conn.createStatement(); 
                    String sql = "SELECT * FROM thamkham WHERE hoten=?"; 
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,(String) TB_danhsach.getValueAt(row,1));
//                    pstmt.setString(2,tuoi);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()){
                                    ahoten.setText(rs.getString("hoten"));
//                                    ahoten.setText(s1);
                                    atuoi.setText(rs.getString("tuoi"));
                                    angaysinh.setText(rs.getString("namsinh"));
                                    /* Set lựa chọn của buttongroup giới tính */
                                    switch(rs.getString("gioitinh")){ 
                                        case "Nam":
                                            RBnam.setSelected(true);
                                            xgioitinh = "Nam";
                                            break;
                                        case "Nữ":
                                            RBnu.setSelected(true);
                                            xgioitinh = "Nữ";
                                            break;
                                        default:
                                            btngroupGioiTinh.clearSelection();
                                    }
                                    /* *********************** */
                                    anghenghiep.setText(rs.getString("nghenghiep"));
                                    adiachi.setText(rs.getString("diachi"));
                                    asdt.setText(rs.getString("sdt"));
                                    /* Set lựa chọn của buttongroup đối tượng */
                                    switch(rs.getString("doituong")){
                                        case "BHYT":
                                            RBbhyt.setSelected(true);
                                            xdoituong = "BHYT";
                                            break;
                                        case "Thu phí":
                                            RBthuphi.setSelected(true);
                                            xdoituong = "Thu phí";
                                            break;
                                        default:
                                            btngroupDoiTuong.clearSelection();
                                    }
                                  /* *********************** */
                                    acmnd.setText(rs.getString("cmnd"));
                                    atennguoithan.setText(rs.getString("hotennguoinha"));
                                    asdtnguoithan.setText(rs.getString("sdtnguoinha"));
                                    angaynhapvien.setText(rs.getString("ngayvao"));
                                    achuandoan.setText(rs.getString("tinhtrang"));
                                    aphong.setText(rs.getString("phong"));
                                    ahenkham.setText(rs.getString("henkham"));
                                    ComboBox_trangthai.setSelectedItem(rs.getString("trangthai"));
                    }
                    rs.close();
                } catch(Exception e) {
                        JOptionPane.showMessageDialog(this,e);
                }
        }else{
            JOptionPane.showMessageDialog(this,"Vui lòng thao tác vào đúng vị trí!");
        }
    }//GEN-LAST:event_TB_danhsachMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String input=null;
        input = JOptionPane.showInputDialog(this,"Chuyển bệnh nhân này vào khoa:");    
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlbv","root","");
            String sql = "INSERT INTO benhnhan(id, hoten, ngaysinh, gioitinh, nghenghiep, diachi, "
                    + "sdt, doituong, tennguoithan, sdtnguoithan, vaovien, chuandoan, khoadieutri) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setString(1,TangID());
//            System.out.println(TangID());
            ptst.setString(2, ahoten.getText());
            ptst.setString(3,angaysinh.getText());
            ptst.setString(4,xgioitinh);
            ptst.setString(5,anghenghiep.getText());
            ptst.setString(6,adiachi.getText());
            ptst.setString(7,asdt.getText());
            ptst.setString(8, xdoituong);
            ptst.setString(9,atennguoithan.getText());
            ptst.setString(10,asdtnguoithan.getText());
            ptst.setString(11,angaynhapvien.getText());
            ptst.setString(12,achuandoan.getText());
            ptst.setString(13,input);

            ptst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Cập nhật dữ liệu thành công!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
        String xtrangthai = "Nhập viện";
        Doitrangthai(xtrangthai);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void ComboBox_trangthai_LOCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBox_trangthai_LOCActionPerformed
        if((String) ComboBox_trangthai_LOC.getSelectedItem() == "Tất cả"){
            LoadDataThamKham();
        }else{
        LocThamKham((String) ComboBox_trangthai_LOC.getSelectedItem());
        }
    }//GEN-LAST:event_ComboBox_trangthai_LOCActionPerformed

    private void Btn_doimatkhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_doimatkhauActionPerformed

        doimatkhau mk = new doimatkhau();
        mk.SetUs(GetU());
        mk.setVisible(true);
        
       
    }//GEN-LAST:event_Btn_doimatkhauActionPerformed

    private void Btn_UpdatethamkhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_UpdatethamkhamActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Bạn chắc chắn thay đổi thông tin?") == JOptionPane.NO_OPTION) {
                return;
            }
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
                Statement st = conn.createStatement();
                String sql = "UPDATE thamkham SET tuoi=?, namsinh=?, gioitinh=?, nghenghiep=?, diachi=?, sdt=?, doituong=?, cmnd=?, "
                        + "hotennguoinha=?, sdtnguoinha=?, ngayvao=?, tinhtrang=?, phong=?, henkham=?,trangthai=? WHERE hoten=?";
                   
                PreparedStatement ptst = conn.prepareStatement(sql);
                ptst.setString(16, ahoten.getText());
                ptst.setString(1, atuoi.getText());
                ptst.setString(2, angaysinh.getText());
                ptst.setString(3, xgioitinh);
                ptst.setString(4, anghenghiep.getText());
                ptst.setString(5, adiachi.getText());
                ptst.setString(6, asdt.getText());
                ptst.setString(7, xdoituong);
                ptst.setString(8,acmnd.getText());
                ptst.setString(9,atennguoithan.getText());
                ptst.setString(10,asdtnguoithan.getText());
                ptst.setString(11,angaynhapvien.getText());
                ptst.setString(12,achuandoan.getText());
                ptst.setString(13,aphong.getText());
                ptst.setString(14,ahenkham.getText());
                ptst.setString(15, (String) ComboBox_trangthai.getSelectedItem());
                ptst.executeUpdate();
                LoadDataThamKham();
                JOptionPane.showMessageDialog(this,"Cập nhật dữ liệu thành công!");
                conn.close();
            } catch(Exception e) {
                    JOptionPane.showMessageDialog(this,e);
            }
    }//GEN-LAST:event_Btn_UpdatethamkhamActionPerformed

    private void BTN_InGiayHenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_InGiayHenActionPerformed
        String xtrangthai = "Hẹn khám";
        Doitrangthai(xtrangthai);
        giayhen_hoten.setText(ahoten.getText());
        giayhen_gioitinh.setText(xgioitinh);
        giayhen_ngaysinh.setText(angaysinh.getText());
        giayhen_diachi.setText(adiachi.getText());
        giayhen_ngayhen.setText(ahenkham.getText());
        giayhen_chuandoan.setText(achuandoan.getText());
                /*--- In PANEL in ---*/
        PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("Print Giấy Hẹn");
            
            job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                    pf.setOrientation(PageFormat.LANDSCAPE);
                 if(pageNum > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(1,1);
                Panel_GiayHen.print(g2);
                return Printable.PAGE_EXISTS;
            }
            });
            boolean comp = job.printDialog();
        if(comp){
            JOptionPane.showMessageDialog(this,"In thành công!","Thông tin",JOptionPane.INFORMATION_MESSAGE);
        try{
        job.print();
        }
        catch (PrinterException ex){
	ex.printStackTrace();
        }
        }
    }//GEN-LAST:event_BTN_InGiayHenActionPerformed

    private void Btn_ThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ThongKeActionPerformed
        if(!jLabel52.getText().equals("Viện trưởng")){
            if(JOptionPane.showConfirmDialog(this,"Bạn không được cấp quyền cho truy cập này! Trở về trang chủ?") == JOptionPane.NO_OPTION) {
                
                return;
            }
            else {
                Tab_Panel.setSelectedIndex(0);
            }
        }
        else{
        Tab_Panel.setSelectedIndex(5);
         LoadDatauser();
         
         
        countrow = 0;
        LoadDatabenhnhan();
        int a = countrow;
        countrow = 0;
        LoadDataThamKham();
        int b = countrow;
        String x = ""+b;
        String y = ""+a;
        jLabel55.setText(x);
        
        jLabel56.setText(y);
        }
        
        
         
    }//GEN-LAST:event_Btn_ThongKeActionPerformed

    private void fhotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fhotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fhotenActionPerformed

    private void ftuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftuoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftuoiActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/qlbv","root","");
            String sql = "INSERT INTO bacsi (hoten, tuoi, gioitinh, chuyenkhoa, diachi, sdt, email) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ptst = conn.prepareStatement(sql);
            ptst.setString(1, fhoten.getText());
            ptst.setString(2,ftuoi.getText());
            ptst.setString(3,(String)fgioitinh.getSelectedItem());
            ptst.setString(4,(String)fchuyenkhoa.getSelectedItem());
            ptst.setString(5, fdiachi.getText());
            ptst.setString(6, fsdt.getText());
            ptst.setString(7, femail.getText());

            ptst.executeUpdate();
            JOptionPane.showMessageDialog(this,"Cập nhật dữ liệu thành công!");
//            loadtable();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Bạn chắc chắn thay đổi thông tin?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();
            

            String sql = "UPDATE bacsi SET hoten=? , tuoi=? , gioitinh=? , chuyenkhoa=? , diachi=? , sdt=? , email=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(8,idchon.getText());
            pstmt.setString(1, fhoten.getText());
            pstmt.setString(2,ftuoi.getText());
            pstmt.setString(3,(String)fgioitinh.getSelectedItem());
            pstmt.setString(4,(String)fchuyenkhoa.getSelectedItem());
            pstmt.setString(5, fdiachi.getText());
            pstmt.setString(6, fsdt.getText());
            pstmt.setString(7, femail.getText());

            pstmt.executeUpdate();
//            loadtable();
            JOptionPane.showMessageDialog(this,"Cập nhật dữ liệu thành công!");
            conn.close();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this,e);
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnxoathongtinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoathongtinActionPerformed
        fhoten.setText("");
        ftuoi.setText("");
        fgioitinh.setSelectedItem("");
        fchuyenkhoa.setSelectedItem("");
        fdiachi.setText("");
        fsdt.setText("");
        femail.setText("");
        timkiem.setText("");
    }//GEN-LAST:event_btnxoathongtinActionPerformed

    private void btnrefeshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefeshActionPerformed
        loadDatabacsi();
        btnxoathongtinActionPerformed(evt);
    }//GEN-LAST:event_btnrefeshActionPerformed

    private void Table_bacsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table_bacsiMouseClicked
//         int row = Table_bacsi.getSelectedRow();
//
//        //        if(row>-1){
//            int idtb = (int) Table_bacsi.getValueAt(row,0);
//            String idhere = String.valueOf(idtb);
//            idchon.setText(idhere);
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
//                Statement st = conn.createStatement();
//                //String sql = "SELECT * FROM bacsi WHERE id = " + idtb;
//                String sql = "SELECT * FROM bacsi WHERE id=?";
//                PreparedStatement pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1,idhere);
//
//                ResultSet rs = pstmt.executeQuery();
//                while(rs.next()){
//                    fhoten.setText(rs.getString("hoten"));
//                    ftuoi.setText(rs.getString("tuoi"));
//                    fgioitinh.setSelectedItem(rs.getString("gioitinh"));
//                    fchuyenkhoa.setSelectedItem(rs.getString("chuyenkhoa"));
//                    fdiachi.setText(rs.getString("diachi"));
//                    fsdt.setText(rs.getString("sdt"));
//                    femail.setText(rs.getString("email"));
//                    fusername.setText(rs.getString("username"));
//                }
//                rs.close();
//            } catch(Exception e) {
//                JOptionPane.showMessageDialog(this,e);
//            }
            
            
             int row = Table_bacsi.getSelectedRow();
        try {
            idchon.setText((String) Table_bacsi.getValueAt(row,0));
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM bacsi WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, (String) Table_bacsi.getValueAt(row,0));
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                    fhoten.setText(rs.getString("hoten"));
                    ftuoi.setText(rs.getString("tuoi"));
                    fgioitinh.setSelectedItem(rs.getString("gioitinh"));
                    fchuyenkhoa.setSelectedItem(rs.getString("chuyenkhoa"));
                    fdiachi.setText(rs.getString("diachi"));
                    fsdt.setText(rs.getString("sdt"));
                    femail.setText(rs.getString("email"));
                    fusername.setText(rs.getString("username"));
                }
//                rs.close();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this,e);
            }
            
    }//GEN-LAST:event_Table_bacsiMouseClicked
  
    private void idchonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idchonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idchonActionPerformed

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM bacsi WHERE hoten LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%"+timkiem.getText()+"%");
            ResultSet rs = pstmt.executeQuery();
            DefaultTableModel tm =(DefaultTableModel)Table_bacsi.getModel();
            tm.setRowCount(0);
            while(rs.next()){
                Object o[]={rs.getString("id"),rs.getString("hoten"),rs.getInt("tuoi"),rs.getString("chuyenkhoa")};
                tm.addRow(o);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,e);
        }
    }//GEN-LAST:event_btntimkiemActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(JOptionPane.showConfirmDialog(this,"Bạn chắc chắn xóa người ngày ra khỏi danh sách?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/QLBV","root","");
            Statement st = conn.createStatement();

            String sql = "DELETE FROM bacsi WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,idchon.getText());

            pstmt.executeUpdate();
            loadDatabacsi();
            conn.close();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this,e);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Tab_Panel.setSelectedIndex(0);
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_BacSi;
    private javax.swing.JButton BTN_InGiayHen;
    private javax.swing.JButton BTN_RefeshBN;
    private javax.swing.JButton BTN_TimKiem_BN;
    private javax.swing.JButton BTNbenhAN;
    private javax.swing.JButton BtnAddBenhNhan;
    private javax.swing.JButton BtnAddBenhNhan1;
    private javax.swing.JButton Btn_InKeThuoc;
    private javax.swing.JButton Btn_ThongKe;
    private javax.swing.JButton Btn_Updatethamkham;
    private javax.swing.JButton Btn_XoaThuocKhoiDanhSach;
    private javax.swing.JButton Btn_doimatkhau;
    private javax.swing.JComboBox<String> ComboBoxLoc;
    private javax.swing.JComboBox<String> ComboBox_trangthai;
    private javax.swing.JComboBox<String> ComboBox_trangthai_LOC;
    private javax.swing.JLabel Label_chuyenkhoabacsi1;
    private javax.swing.JLabel Label_diachibacsi1;
    private javax.swing.JLabel Label_emailbacsi1;
    private javax.swing.JLabel Label_emailbacsi2;
    private javax.swing.JLabel Label_gioitinhbacsi1;
    private javax.swing.JLabel Label_hotenbacsi1;
    private javax.swing.JLabel Label_sdtbacsi1;
    private javax.swing.JLabel Label_thongtinbacsi1;
    private javax.swing.JLabel Label_tuoibacsi1;
    private javax.swing.JMenuBar MENUBAR;
    private javax.swing.JPanel Panel_ChucNang;
    private javax.swing.JPanel Panel_GiayHen;
    private javax.swing.JPanel Panel_KeThuoc;
    private javax.swing.JPanel Panel_QuanLibacsi;
    private javax.swing.JPanel Panel_ThongtinBacSi1;
    private javax.swing.JPanel Panel_in;
    private javax.swing.JPanel Pntitletrai;
    private javax.swing.JRadioButton RBbhyt;
    private javax.swing.JRadioButton RBnam;
    private javax.swing.JRadioButton RBnu;
    private javax.swing.JRadioButton RBthuphi;
    private javax.swing.JPanel TAB_HOME;
    private javax.swing.JTable TB_danhsach;
    private javax.swing.JPanel Tab_Bacsi;
    private javax.swing.JPanel Tab_BenhNhan;
    private javax.swing.JTabbedPane Tab_Panel;
    private javax.swing.JPanel Tab_ThongTin;
    private javax.swing.JPanel Tab_TiepDon;
    private javax.swing.JTable Table_bacsi;
    private javax.swing.JTable Table_kethuoc;
    private javax.swing.JTable Table_khothuoc;
    private javax.swing.JTable Tbl_BenhNhan;
    private javax.swing.JTextArea achuandoan;
    private javax.swing.JTextField acmnd;
    private javax.swing.JTextField adiachi;
    private javax.swing.JTextField ahenkham;
    private javax.swing.JTextField ahoten;
    private javax.swing.JTextField angaynhapvien;
    private javax.swing.JTextField angaysinh;
    private javax.swing.JTextField anghenghiep;
    private javax.swing.JTextField aphong;
    private javax.swing.JTextField asdt;
    private javax.swing.JTextField asdtnguoithan;
    private javax.swing.JTextField atennguoithan;
    private javax.swing.JTextField atuoi;
    private javax.swing.ButtonGroup btngroupDoiTuong;
    private javax.swing.ButtonGroup btngroupGioiTinh;
    private javax.swing.JButton btnrefesh;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton btnxoathongtin;
    private javax.swing.JComboBox<String> fchuyenkhoa;
    private javax.swing.JTextField fdiachi;
    private javax.swing.JTextField fdiachi1;
    private javax.swing.JTextField femail;
    private javax.swing.JComboBox<String> fgioitinh;
    private javax.swing.JTextField fhoten;
    private javax.swing.JTextField fhoten1;
    private javax.swing.JTextField fid;
    private javax.swing.JTextField fkhoadieutri;
    private javax.swing.JTextField fngaysinh;
    private javax.swing.JTextField fsdt;
    private javax.swing.JTextField fsdtnguoithan;
    private javax.swing.JTextField ftennguoithan;
    private javax.swing.JTextField ftuoi;
    private javax.swing.JTextField fusername;
    private javax.swing.JLabel giayhen_chuandoan;
    private javax.swing.JLabel giayhen_diachi;
    private javax.swing.JLabel giayhen_gioitinh;
    private javax.swing.JLabel giayhen_hoten;
    private javax.swing.JLabel giayhen_ngayhen;
    private javax.swing.JLabel giayhen_ngaysinh;
    private javax.swing.JTextField idchon;
    private javax.swing.JMenuItem itemDangXuat;
    private javax.swing.JMenuItem itemThoat;
    private javax.swing.JMenuItem itemURL;
    private javax.swing.JMenu itemabout;
    private javax.swing.JMenuItem itemdeveloper;
    private javax.swing.JMenu itemmenuTEP;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel ngayintoathuoc;
    private javax.swing.JPanel pnhanhchinh;
    private javax.swing.JPanel pnquanlynguoibenh;
    private javax.swing.JLabel tentoathuoc;
    private javax.swing.JTextField timkiem;
    private javax.swing.JTextField timkiem1;
    private javax.swing.JLabel tuoitoathuoc;
    private javax.swing.JTextArea txt_bill;
    // End of variables declaration//GEN-END:variables
}
