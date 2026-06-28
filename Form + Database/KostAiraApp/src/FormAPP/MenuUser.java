/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormAPP;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tony's PC
 */
public class MenuUser extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public MenuUser() {
        initComponents();
        ScrollData.getVerticalScrollBar().setUnitIncrement(8);
        iconApp();
        BerandaSideBar.setVisible(true);
        BerandaSideBar.setEnabled(true);
        KamarSideBar.setVisible(false);
        KamarSideBar.setEnabled(false);
        PemesananSideBar.setVisible(false);
        PemesananSideBar.setEnabled(false);
        showTableKamar2();
        autoNumbers();
        cmb_Status2.setSelectedItem("Tidak Tersedia");
        gb1.setSelected(true);
    }

    public void showTableKamar2() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("KODE KAMAR");
        tbl.addColumn("LOKASI KAMAR");
        tbl.addColumn("JENIS KAMAR");
        tbl.addColumn("DESKRIPSI KAMAR");
        tbl.addColumn("HARGA");
        tbl.addColumn("STATUS");

        DataKamar.setModel(tbl);

        try {
            ConnectDB konek = new ConnectDB();
            ResultSet rs = konek.selectDB8();
            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("kode_kamar"),
                    rs.getString("lokasi_kamar"),
                    rs.getString("jenis_kamar"),
                    rs.getString("dsc_fasilitas"),
                    rs.getString("harga"),
                    rs.getString("status")
                });
                DataKamar.setModel(tbl);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Salah");
            System.out.println(ex.getMessage());
//            Logger.getLogger(DataMahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void resetForm2() {
        autoNumbers();
        txtKodeKamarPsn.setText("");
        tgl_masuk.setDate(null);
        tgl_keluar.setDate(null);
        txtLamaSewa.setText("");
        txtTotaBayar.setText("");
        FotoKamar.setIcon(null);
        FieldKodeKamar.setText("");
        FieldLokasiKamar.setText("");
        FieldJenisKamar.setText("");
        FieldDsc.setText("");
        FieldHarga.setText("");
        FieldStatus.setText("");
    }

    private void autoNumbers() {
        String idBooking = "B0";
        int i = 0;
        try {
            ConnectDB konek = new ConnectDB();
            ResultSet rs = konek.selectDB0(idBooking);

            while (rs.next()) {
                idBooking = rs.getString("idBooking");
            }
            idBooking = idBooking.substring(1);
            i = Integer.parseInt(idBooking) + 1;
            idBooking = "00" + i;
            idBooking = "B0" + idBooking.substring(idBooking.length() - 2);
            txtBooking.setText(idBooking);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error pada Pemesanan");
            System.out.println(e.getMessage());
        }
    }
    
        private void hitungselisih() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String strDate1 = df.format(tgl_masuk.getDate());
            String strDate2 = df.format(tgl_keluar.getDate());
            Date Tanggal1 = df.parse(strDate1);
            Date Tanggal2 = df.parse(strDate2);
            long Hari1 = Tanggal1.getTime();
            long Hari2 = Tanggal2.getTime();
            long diff = Hari2 - Hari1;
            long Lama = diff / (24 * 60 * 60 * 1000);
            String Hasil = (Long.toString(Lama));
            txtLamaSewa.setText(Hasil);

            int harga_sewa = Integer.parseInt(FieldHarga.getText()); 
            int lama_sewa = Integer.parseInt(txtLamaSewa.getText());   
            int Total = harga_sewa * lama_sewa;                     
            String a = Integer.toString(Total);
            txtTotaBayar.setText(a);



        } catch (Exception a) {
            JOptionPane.showMessageDialog(this, "Masukan Tanggal Peminjaman dan Tanggal Pengembalian");
        }
    }

    private void iconApp() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/SI_AiraKost_Asset/LOGOKOST.png")));
    }

    private void setColorText(JLabel label) {
        label.setForeground(new java.awt.Color(255, 255, 255));
    }

    private void resetColorText(JLabel label) {
        label.setForeground(new java.awt.Color(207, 207, 207));
    }

    private void setColor(JPanel panel) {
        panel.setBackground(new java.awt.Color(96, 82, 76));
    }

    private void resetColor(JPanel panel) {
        panel.setBackground(new java.awt.Color(140, 120, 111));
    }

    private void setPanel(JPanel panel) {
        SideBarMenu.setVisible(false);
        SideBarMenu.setEnabled(false);
        panel.setBounds(0, 0, 1366, 768);
    }

    private void resetPanel(JPanel panel) {
        SideBarMenu.setVisible(true);
        SideBarMenu.setEnabled(true);
        panel.setBounds(206, 0, 1160, 768);
    }

    int xy, xx;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Bg = new javax.swing.JPanel();
        BerandaSideBar = new javax.swing.JPanel();
        lbl_Menu1 = new javax.swing.JLabel();
        bttn_Menu7 = new javax.swing.JLabel();
        bttn_minimized2 = new javax.swing.JButton();
        bttn_close1 = new javax.swing.JButton();
        Cek2 = new javax.swing.JButton();
        Cek1 = new javax.swing.JButton();
        lbl_judul1 = new javax.swing.JLabel();
        lbl_Slide1 = new javax.swing.JLabel();
        lbl_Text2 = new javax.swing.JLabel();
        lbl_Kamar3 = new javax.swing.JLabel();
        lbl_Kamar4 = new javax.swing.JLabel();
        button = new javax.swing.JPanel();
        gb1 = new javax.swing.JRadioButton();
        gb2 = new javax.swing.JRadioButton();
        gb3 = new javax.swing.JRadioButton();
        gb4 = new javax.swing.JRadioButton();
        gb5 = new javax.swing.JRadioButton();
        gb6 = new javax.swing.JRadioButton();
        gb7 = new javax.swing.JRadioButton();
        gb8 = new javax.swing.JRadioButton();
        gb9 = new javax.swing.JRadioButton();
        gb10 = new javax.swing.JRadioButton();
        PanelChat = new javax.swing.JPanel();
        lbl_chatskrng_txt = new javax.swing.JLabel();
        lbl_wa_icon = new javax.swing.JLabel();
        chatBx = new javax.swing.JToggleButton();
        KamarSideBar = new javax.swing.JPanel();
        lbl_Menu2 = new javax.swing.JLabel();
        lbl_Menu12 = new javax.swing.JLabel();
        bttn_minimized4 = new javax.swing.JButton();
        bttn_close2 = new javax.swing.JButton();
        lbl_judul2 = new javax.swing.JLabel();
        ScrollData2 = new javax.swing.JScrollPane();
        PanelKamar = new javax.swing.JPanel();
        cmbkategori = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        DataKamar = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtcari2 = new javax.swing.JTextField();
        refresh3 = new javax.swing.JLabel();
        SideBarMenu = new javax.swing.JPanel();
        lbl_photos = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        beranda = new javax.swing.JPanel();
        ico_beranda = new javax.swing.JLabel();
        Lbl_beranda = new javax.swing.JLabel();
        kamar = new javax.swing.JPanel();
        ico_kamar = new javax.swing.JLabel();
        Lbl_kamar = new javax.swing.JLabel();
        pemesanan = new javax.swing.JPanel();
        ico_pemesanan = new javax.swing.JLabel();
        Lbl_pemesanan = new javax.swing.JLabel();
        Logout = new javax.swing.JToggleButton();
        PemesananSideBar = new javax.swing.JPanel();
        lbl_Menu5 = new javax.swing.JLabel();
        lbl_Menu10 = new javax.swing.JLabel();
        bttn_minimized8 = new javax.swing.JButton();
        bttn_close4 = new javax.swing.JButton();
        lbl_judul4 = new javax.swing.JLabel();
        bttn_CekButton6 = new javax.swing.JButton();
        form = new javax.swing.JPanel();
        lbl_judul = new javax.swing.JLabel();
        refresh2 = new javax.swing.JLabel();
        ScrollData = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        Pesan = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtBooking = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtTotaBayar = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        txtKodeBayar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jLabel51 = new javax.swing.JLabel();
        tgl_keluar = new com.toedter.calendar.JDateChooser();
        tgl_masuk = new com.toedter.calendar.JDateChooser();
        jLabel50 = new javax.swing.JLabel();
        txtLamaSewa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lbl_besar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KostAiraApp");
        setUndecorated(true);

        Bg.setBackground(new java.awt.Color(242, 242, 242));
        Bg.setLayout(null);

        BerandaSideBar.setBackground(new java.awt.Color(242, 242, 242));
        BerandaSideBar.setMinimumSize(new java.awt.Dimension(1160, 768));
        BerandaSideBar.setPreferredSize(new java.awt.Dimension(1160, 768));
        BerandaSideBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BerandaSideBarMouseDragged(evt);
            }
        });
        BerandaSideBar.setLayout(null);

        lbl_Menu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/menu.png"))); // NOI18N
        lbl_Menu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_Menu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_Menu1MouseClicked(evt);
            }
        });
        BerandaSideBar.add(lbl_Menu1);
        lbl_Menu1.setBounds(10, 10, 34, 30);

        bttn_Menu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/menu.png"))); // NOI18N
        bttn_Menu7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttn_Menu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttn_Menu7MouseClicked(evt);
            }
        });
        BerandaSideBar.add(bttn_Menu7);
        bttn_Menu7.setBounds(10, 10, 34, 30);

        bttn_minimized2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/minimize.png"))); // NOI18N
        bttn_minimized2.setBorder(null);
        bttn_minimized2.setBorderPainted(false);
        bttn_minimized2.setContentAreaFilled(false);
        bttn_minimized2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttn_minimized2.setFocusPainted(false);
        bttn_minimized2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_minimized2ActionPerformed(evt);
            }
        });
        BerandaSideBar.add(bttn_minimized2);
        bttn_minimized2.setBounds(1080, 15, 20, 20);

        bttn_close1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/close.png"))); // NOI18N
        bttn_close1.setBorder(null);
        bttn_close1.setBorderPainted(false);
        bttn_close1.setContentAreaFilled(false);
        bttn_close1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttn_close1.setFocusPainted(false);
        bttn_close1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_close1ActionPerformed(evt);
            }
        });
        BerandaSideBar.add(bttn_close1);
        bttn_close1.setBounds(1110, 10, 20, 20);

        Cek2.setContentAreaFilled(false);
        Cek2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cek2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cek2ActionPerformed(evt);
            }
        });
        BerandaSideBar.add(Cek2);
        Cek2.setBounds(720, 665, 62, 25);

        Cek1.setContentAreaFilled(false);
        Cek1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cek1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cek1ActionPerformed(evt);
            }
        });
        BerandaSideBar.add(Cek1);
        Cek1.setBounds(385, 665, 62, 25);

        lbl_judul1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/Label.png"))); // NOI18N
        BerandaSideBar.add(lbl_judul1);
        lbl_judul1.setBounds(445, 10, 260, 90);

        lbl_Slide1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKostFoto/1.png"))); // NOI18N
        BerandaSideBar.add(lbl_Slide1);
        lbl_Slide1.setBounds(350, 130, 450, 260);

        lbl_Text2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_Text2.setText("Jl. Taman Kampus, Lingkungan Krajan Timur, Tegalgede, Kec. Sumbersari, Kabupaten Jember");
        BerandaSideBar.add(lbl_Text2);
        lbl_Text2.setBounds(300, 440, 570, 20);

        lbl_Kamar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/buttonKamar1.png"))); // NOI18N
        lbl_Kamar3.setPreferredSize(new java.awt.Dimension(190, 230));
        BerandaSideBar.add(lbl_Kamar3);
        lbl_Kamar3.setBounds(340, 500, 160, 200);

        lbl_Kamar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/buttonKamar2.png"))); // NOI18N
        BerandaSideBar.add(lbl_Kamar4);
        lbl_Kamar4.setBounds(670, 500, 160, 200);

        button.setOpaque(false);
        button.setLayout(null);

        buttonGroup1.add(gb1);
        gb1.setForeground(new java.awt.Color(255, 255, 255));
        gb1.setBorder(null);
        gb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gb1ActionPerformed(evt);
            }
        });
        button.add(gb1);
        gb1.setBounds(10, 0, 13, 20);

        buttonGroup1.add(gb2);
        gb2.setBorder(null);
        gb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gb2ActionPerformed(evt);
            }
        });
        button.add(gb2);
        gb2.setBounds(29, 0, 13, 20);

        buttonGroup1.add(gb3);
        gb3.setBorder(null);
        gb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gb3ActionPerformed(evt);
            }
        });
        button.add(gb3);
        gb3.setBounds(48, 0, 13, 20);

        buttonGroup1.add(gb4);
        gb4.setBorder(null);
        gb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gb4ActionPerformed(evt);
            }
        });
        button.add(gb4);
        gb4.setBounds(67, 0, 13, 20);

        buttonGroup1.add(gb5);
        gb5.setBorder(null);
        gb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gb5ActionPerformed(evt);
            }
        });
        button.add(gb5);
        gb5.setBounds(86, 0, 13, 20);

        buttonGroup1.add(gb6);
        gb6.setBorder(null);
        gb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gb6ActionPerformed(evt);
            }
        });
        button.add(gb6);
        gb6.setBounds(105, 0, 13, 20);

        buttonGroup1.add(gb7);
        gb7.setBorder(null);
        gb7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gb7ActionPerformed(evt);
            }
        });
        button.add(gb7);
        gb7.setBounds(124, 0, 13, 20);

        buttonGroup1.add(gb8);
        gb8.setBorder(null);
        gb8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gb8ActionPerformed(evt);
            }
        });
        button.add(gb8);
        gb8.setBounds(143, 0, 13, 20);

        buttonGroup1.add(gb9);
        gb9.setBorder(null);
        gb9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gb9ActionPerformed(evt);
            }
        });
        button.add(gb9);
        gb9.setBounds(162, 0, 13, 20);

        buttonGroup1.add(gb10);
        gb10.setBorder(null);
        gb10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gb10ActionPerformed(evt);
            }
        });
        button.add(gb10);
        gb10.setBounds(181, 0, 13, 20);

        BerandaSideBar.add(button);
        button.setBounds(480, 390, 200, 20);

        PanelChat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelChat.setFocusable(false);
        PanelChat.setOpaque(false);
        PanelChat.setLayout(null);

        lbl_chatskrng_txt.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_chatskrng_txt.setForeground(new java.awt.Color(255, 255, 255));
        lbl_chatskrng_txt.setText("Chat Sekarang !!!");
        PanelChat.add(lbl_chatskrng_txt);
        lbl_chatskrng_txt.setBounds(60, 27, 140, 20);

        lbl_wa_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icoWaP.png"))); // NOI18N
        PanelChat.add(lbl_wa_icon);
        lbl_wa_icon.setBounds(20, 22, 30, 30);

        chatBx.setBackground(new java.awt.Color(96, 82, 76));
        chatBx.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chatBx.setForeground(new java.awt.Color(255, 255, 255));
        chatBx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/Untitled-1.png"))); // NOI18N
        chatBx.setBorder(null);
        chatBx.setBorderPainted(false);
        chatBx.setContentAreaFilled(false);
        chatBx.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chatBx.setFocusPainted(false);
        chatBx.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chatBx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatBxActionPerformed(evt);
            }
        });
        PanelChat.add(chatBx);
        chatBx.setBounds(0, 0, 210, 70);

        BerandaSideBar.add(PanelChat);
        PanelChat.setBounds(932, 710, 210, 70);

        Bg.add(BerandaSideBar);
        BerandaSideBar.setBounds(206, 0, 1160, 768);

        KamarSideBar.setBackground(new java.awt.Color(242, 242, 242));
        KamarSideBar.setMinimumSize(new java.awt.Dimension(1160, 768));
        KamarSideBar.setLayout(null);

        lbl_Menu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/menu.png"))); // NOI18N
        lbl_Menu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_Menu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_Menu2MouseClicked(evt);
            }
        });
        KamarSideBar.add(lbl_Menu2);
        lbl_Menu2.setBounds(10, 10, 34, 30);

        lbl_Menu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/menu.png"))); // NOI18N
        lbl_Menu12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_Menu12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_Menu12MouseClicked(evt);
            }
        });
        KamarSideBar.add(lbl_Menu12);
        lbl_Menu12.setBounds(10, 10, 34, 30);

        bttn_minimized4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/minimize.png"))); // NOI18N
        bttn_minimized4.setBorder(null);
        bttn_minimized4.setBorderPainted(false);
        bttn_minimized4.setContentAreaFilled(false);
        bttn_minimized4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttn_minimized4.setFocusPainted(false);
        bttn_minimized4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_minimized4ActionPerformed(evt);
            }
        });
        KamarSideBar.add(bttn_minimized4);
        bttn_minimized4.setBounds(1080, 15, 20, 20);

        bttn_close2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/close.png"))); // NOI18N
        bttn_close2.setBorder(null);
        bttn_close2.setBorderPainted(false);
        bttn_close2.setContentAreaFilled(false);
        bttn_close2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttn_close2.setFocusPainted(false);
        bttn_close2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_close2ActionPerformed(evt);
            }
        });
        KamarSideBar.add(bttn_close2);
        bttn_close2.setBounds(1110, 10, 20, 20);

        lbl_judul2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/Label.png"))); // NOI18N
        KamarSideBar.add(lbl_judul2);
        lbl_judul2.setBounds(445, 10, 260, 90);

        ScrollData2.setBackground(new java.awt.Color(255, 255, 255));
        ScrollData2.setBorder(null);
        ScrollData2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollData2.setOpaque(false);

        PanelKamar.setBackground(new java.awt.Color(242, 242, 242));
        PanelKamar.setLayout(null);

        cmbkategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "*Kategori", "Kamar 1", "Kamar 2", " " }));
        cmbkategori.setBorder(null);
        cmbkategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbkategoriActionPerformed(evt);
            }
        });
        PanelKamar.add(cmbkategori);
        cmbkategori.setBounds(330, 60, 130, 30);

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane5.setOpaque(false);

        DataKamar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        DataKamar.setForeground(new java.awt.Color(51, 51, 51));
        DataKamar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", "", "", null},
                {"", "", "", "", null},
                {"", "", "", "", null},
                {"", "", "", "", null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Kamar", "Jeni Kamar", "Deskripsi Fasilitas", "Harga", "Status"
            }
        )

        {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        DataKamar.setDefaultEditor(Object.class, null);
        DataKamar.setFocusable(false);
        DataKamar.setGridColor(new java.awt.Color(255, 255, 255));
        DataKamar.setIntercellSpacing(new java.awt.Dimension(0, 0));
        DataKamar.setRowHeight(20);
        DataKamar.setSelectionBackground(new java.awt.Color(140, 120, 111));
        DataKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataKamarMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(DataKamar);

        PanelKamar.add(jScrollPane5);
        jScrollPane5.setBounds(0, 100, 1160, 510);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Cari");
        PanelKamar.add(jLabel1);
        jLabel1.setBounds(30, 63, 30, 22);

        txtcari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcari2ActionPerformed(evt);
            }
        });
        txtcari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcari2KeyReleased(evt);
            }
        });
        PanelKamar.add(txtcari2);
        txtcari2.setBounds(90, 60, 220, 30);

        refresh3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/refresh1.png"))); // NOI18N
        refresh3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refresh3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refresh3MouseClicked(evt);
            }
        });
        PanelKamar.add(refresh3);
        refresh3.setBounds(1100, 70, 30, 30);

        ScrollData2.setViewportView(PanelKamar);

        KamarSideBar.add(ScrollData2);
        ScrollData2.setBounds(0, 110, 1160, 610);

        Bg.add(KamarSideBar);
        KamarSideBar.setBounds(206, 0, 1160, 768);

        SideBarMenu.setBackground(new java.awt.Color(140, 120, 111));
        SideBarMenu.setMinimumSize(new java.awt.Dimension(210, 768));
        SideBarMenu.setPreferredSize(new java.awt.Dimension(210, 768));
        SideBarMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_photos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/profileW.png"))); // NOI18N
        SideBarMenu.add(lbl_photos, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 30, 100, 100));

        jSeparator1.setBackground(new java.awt.Color(96, 82, 76));
        jSeparator1.setForeground(new java.awt.Color(96, 82, 76));
        SideBarMenu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 188, 210, -1));

        NamaUser2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        NamaUser2.setForeground(new java.awt.Color(255, 255, 255));
        NamaUser2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NamaUser2.setText("User");
        SideBarMenu.add(NamaUser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 150, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(179, 166, 165));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Menu");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        SideBarMenu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        beranda.setBackground(new java.awt.Color(96, 82, 76));
        beranda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                berandaMouseClicked(evt);
            }
        });

        ico_beranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_1_30px_P.png"))); // NOI18N

        Lbl_beranda.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lbl_beranda.setForeground(new java.awt.Color(255, 255, 255));
        Lbl_beranda.setText("Beranda");

        javax.swing.GroupLayout berandaLayout = new javax.swing.GroupLayout(beranda);
        beranda.setLayout(berandaLayout);
        berandaLayout.setHorizontalGroup(
            berandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(berandaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(ico_beranda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Lbl_beranda, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        berandaLayout.setVerticalGroup(
            berandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, berandaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(berandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ico_beranda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Lbl_beranda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        SideBarMenu.add(beranda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 205, 50));

        kamar.setBackground(new java.awt.Color(140, 120, 111));
        kamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kamarMouseClicked(evt);
            }
        });

        ico_kamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_2_30px.png"))); // NOI18N

        Lbl_kamar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lbl_kamar.setForeground(new java.awt.Color(207, 207, 207));
        Lbl_kamar.setText("Kamar");

        javax.swing.GroupLayout kamarLayout = new javax.swing.GroupLayout(kamar);
        kamar.setLayout(kamarLayout);
        kamarLayout.setHorizontalGroup(
            kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kamarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(ico_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Lbl_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kamarLayout.setVerticalGroup(
            kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kamarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kamarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lbl_kamar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(kamarLayout.createSequentialGroup()
                        .addComponent(ico_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        SideBarMenu.add(kamar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 205, 50));

        pemesanan.setBackground(new java.awt.Color(140, 120, 111));
        pemesanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pemesananMouseClicked(evt);
            }
        });

        ico_pemesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_3_30px.png"))); // NOI18N

        Lbl_pemesanan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Lbl_pemesanan.setForeground(new java.awt.Color(207, 207, 207));
        Lbl_pemesanan.setText("Pemesanan");

        javax.swing.GroupLayout pemesananLayout = new javax.swing.GroupLayout(pemesanan);
        pemesanan.setLayout(pemesananLayout);
        pemesananLayout.setHorizontalGroup(
            pemesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pemesananLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(ico_pemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Lbl_pemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pemesananLayout.setVerticalGroup(
            pemesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pemesananLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pemesananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lbl_pemesanan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pemesananLayout.createSequentialGroup()
                        .addComponent(ico_pemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        SideBarMenu.add(pemesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 205, 50));

        Logout.setBackground(new java.awt.Color(96, 82, 76));
        Logout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Logout.setForeground(new java.awt.Color(255, 255, 255));
        Logout.setText("Logout");
        Logout.setBorder(null);
        Logout.setBorderPainted(false);
        Logout.setContentAreaFilled(false);
        Logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logout.setFocusPainted(false);
        Logout.setOpaque(true);
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });
        SideBarMenu.add(Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 700, 100, 40));

        Bg.add(SideBarMenu);
        SideBarMenu.setBounds(0, 0, 205, 768);

        PemesananSideBar.setBackground(new java.awt.Color(242, 242, 242));
        PemesananSideBar.setMinimumSize(new java.awt.Dimension(1160, 768));
        PemesananSideBar.setLayout(null);

        lbl_Menu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/menu.png"))); // NOI18N
        lbl_Menu5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_Menu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_Menu5MouseClicked(evt);
            }
        });
        PemesananSideBar.add(lbl_Menu5);
        lbl_Menu5.setBounds(10, 10, 34, 30);

        lbl_Menu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/menu.png"))); // NOI18N
        lbl_Menu10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_Menu10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_Menu10MouseClicked(evt);
            }
        });
        PemesananSideBar.add(lbl_Menu10);
        lbl_Menu10.setBounds(10, 10, 34, 30);

        bttn_minimized8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/minimize.png"))); // NOI18N
        bttn_minimized8.setBorder(null);
        bttn_minimized8.setBorderPainted(false);
        bttn_minimized8.setContentAreaFilled(false);
        bttn_minimized8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttn_minimized8.setFocusPainted(false);
        bttn_minimized8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_minimized8ActionPerformed(evt);
            }
        });
        PemesananSideBar.add(bttn_minimized8);
        bttn_minimized8.setBounds(1080, 15, 20, 20);

        bttn_close4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/close.png"))); // NOI18N
        bttn_close4.setBorder(null);
        bttn_close4.setBorderPainted(false);
        bttn_close4.setContentAreaFilled(false);
        bttn_close4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttn_close4.setFocusPainted(false);
        bttn_close4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_close4ActionPerformed(evt);
            }
        });
        PemesananSideBar.add(bttn_close4);
        bttn_close4.setBounds(1110, 10, 20, 20);

        lbl_judul4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/Label.png"))); // NOI18N
        PemesananSideBar.add(lbl_judul4);
        lbl_judul4.setBounds(445, 10, 260, 90);

        bttn_CekButton6.setBorder(null);
        bttn_CekButton6.setBorderPainted(false);
        bttn_CekButton6.setContentAreaFilled(false);
        bttn_CekButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttn_CekButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_CekButton6ActionPerformed(evt);
            }
        });
        PemesananSideBar.add(bttn_CekButton6);
        bttn_CekButton6.setBounds(390, 660, 61, 23);

        form.setBackground(new java.awt.Color(255, 255, 255));
        form.setOpaque(false);
        form.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_judul.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_judul.setForeground(new java.awt.Color(96, 82, 76));
        lbl_judul.setText("Formulir Pemesanan Kamar");
        form.add(lbl_judul, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        refresh2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/refresh1.png"))); // NOI18N
        refresh2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refresh2MouseClicked(evt);
            }
        });
        form.add(refresh2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 20, -1, -1));

        ScrollData.setBackground(new java.awt.Color(255, 255, 255));
        ScrollData.setBorder(null);
        ScrollData.setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Pesan.setBackground(new java.awt.Color(52, 135, 239));
        Pesan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Pesan.setForeground(new java.awt.Color(255, 255, 255));
        Pesan.setText("Pesan");
        Pesan.setBorder(null);
        Pesan.setBorderPainted(false);
        Pesan.setContentAreaFilled(false);
        Pesan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Pesan.setOpaque(true);
        Pesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesanActionPerformed(evt);
            }
        });
        jPanel1.add(Pesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 820, 100, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("ID Booking");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 126, -1));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel39.setText("Kode Kamar");
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 126, -1));

        txtBooking.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtBooking.setEnabled(false);
        jPanel1.add(txtBooking, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 335, -1));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel41.setText("ID Cust");
        jPanel1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 126, -1));

        txtIdCust.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtIdCust.setEnabled(false);
        jPanel1.add(txtIdCust, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 335, -1));

        txtKodeKamarPsn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtKodeKamarPsn.setEnabled(false);
        jPanel1.add(txtKodeKamarPsn, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 335, -1));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel40.setText("Nama Lengkap");
        jPanel1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 126, -1));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel42.setText("No. HP Pribadi");
        jPanel1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 126, -1));

        txtNoHP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel1.add(txtNoHP, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 335, -1));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel43.setText("Jenis Kelamin");
        jPanel1.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 126, -1));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel44.setText("Alamat");
        jPanel1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 126, -1));

        txtAlamat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel1.add(txtAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 335, -1));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel45.setText("Total Bayar");
        jPanel1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 650, 126, -1));

        txtTotaBayar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel1.add(txtTotaBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 650, 335, -1));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel46.setText("Lama sewa");
        jPanel1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 600, 126, -1));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel47.setText("Tanggal Keluar");
        jPanel1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 126, -1));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel48.setText("No. HP Darurat");
        jPanel1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 126, -1));

        txtNoDarurat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel1.add(txtNoDarurat, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 335, -1));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel49.setText("Kode Bayar");
        jPanel1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 700, 126, -1));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel52.setText("Status");
        jPanel1.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 755, 126, -1));

        txtKodeBayar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtKodeBayar.setText("0410-01-000301-50-4");
        jPanel1.add(txtKodeBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 700, 335, -1));

        jPanel2.setLayout(null);

        FotoKamar.setText("Gambar");
        jPanel2.add(FotoKamar);
        FotoKamar.setBounds(50, 60, 260, 150);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Data");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(161, 24, 36, 22);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setText("Kode Kamar");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 230, 64, 16);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setText("Jenis Kamar");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(20, 290, 62, 16);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setText("Lokasi Kamar");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(20, 260, 70, 16);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setText("Deskripsi Kamar");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(20, 340, 84, 16);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel9.setText("Harga");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(30, 400, 32, 16);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setText("Status");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(30, 430, 32, 16);

        FieldKodeKamar.setText("??");
        jPanel2.add(FieldKodeKamar);
        FieldKodeKamar.setBounds(130, 230, 129, 14);

        FieldLokasiKamar.setText("??");
        jPanel2.add(FieldLokasiKamar);
        FieldLokasiKamar.setBounds(130, 260, 129, 14);

        FieldJenisKamar.setText("??");
        jPanel2.add(FieldJenisKamar);
        FieldJenisKamar.setBounds(130, 290, 129, 14);

        FieldHarga.setText("??");
        jPanel2.add(FieldHarga);
        FieldHarga.setBounds(130, 400, 129, 14);

        FieldStatus.setText("??");
        jPanel2.add(FieldStatus);
        FieldStatus.setBounds(130, 430, 129, 14);

        FieldDsc.setEditable(false);
        FieldDsc.setColumns(20);
        FieldDsc.setRows(5);
        jScrollPane6.setViewportView(FieldDsc);

        jPanel2.add(jScrollPane6);
        jScrollPane6.setBounds(130, 320, 210, 70);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 360, 480));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel51.setText("Tanggal Masuk");
        jPanel1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 126, -1));

        tgl_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tgl_keluarMouseClicked(evt);
            }
        });
        jPanel1.add(tgl_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 540, 335, 30));
        jPanel1.add(tgl_masuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, 335, 30));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel50.setText("No. KTP");
        jPanel1.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 126, -1));

        nm_lengkap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel1.add(nm_lengkap, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 335, -1));

        txtJenisKelamin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel1.add(txtJenisKelamin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 335, 30));

        txtNoKTP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel1.add(txtNoKTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 335, -1));

        cmb_Status2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmb_Status2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Status", "Tersedia", "Tidak Tersedia" }));
        cmb_Status2.setEnabled(false);
        jPanel1.add(cmb_Status2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 750, 335, 30));
        jPanel1.add(txtLamaSewa, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 600, 335, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icons8_refresh_24px.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 540, 30, 30));

        ScrollData.setViewportView(jPanel1);

        form.add(ScrollData, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1064, 480));

        lbl_besar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/Edge.png"))); // NOI18N
        form.add(lbl_besar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 610));

        PemesananSideBar.add(form);
        form.setBounds(50, 110, 1050, 610);

        Bg.add(PemesananSideBar);
        PemesananSideBar.setBounds(206, 0, 1160, 768);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bg, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1366, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kamarMouseClicked
        ImageIcon kmr = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_2_30px_P.png"));
        ico_kamar.setIcon(kmr);
        ImageIcon brd = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_1_30px.png"));
        ico_beranda.setIcon(brd);
        ImageIcon psn = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_3_30px.png"));
        ico_pemesanan.setIcon(psn);
        setColor(kamar);
        setColorText(Lbl_kamar);
        resetColor(beranda);
        resetColorText(Lbl_beranda);
        resetColor(pemesanan);
        resetColorText(Lbl_pemesanan);

        KamarSideBar.setVisible(true);
        KamarSideBar.setEnabled(true);
        BerandaSideBar.setVisible(false);
        BerandaSideBar.setEnabled(false);
        PemesananSideBar.setVisible(false);
        PemesananSideBar.setEnabled(false);
    }//GEN-LAST:event_kamarMouseClicked

    private void berandaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_berandaMouseClicked
        ImageIcon brd = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_1_30px_P.png"));
        ico_beranda.setIcon(brd);
        ImageIcon kmr = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_2_30px.png"));
        ico_kamar.setIcon(kmr);
        ImageIcon psn = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_3_30px.png"));
        ico_pemesanan.setIcon(psn);
        setColor(beranda);
        setColorText(Lbl_beranda);
        resetColor(kamar);
        resetColorText(Lbl_kamar);
        resetColor(pemesanan);
        resetColorText(Lbl_pemesanan);

        BerandaSideBar.setVisible(true);
        BerandaSideBar.setEnabled(true);
        KamarSideBar.setVisible(false);
        KamarSideBar.setEnabled(false);
        PemesananSideBar.setVisible(false);
        PemesananSideBar.setEnabled(false);
    }//GEN-LAST:event_berandaMouseClicked

    private void BerandaSideBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BerandaSideBarMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_BerandaSideBarMouseDragged

    private void bttn_close1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_close1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bttn_close1ActionPerformed

    private void bttn_minimized2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_minimized2ActionPerformed
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_bttn_minimized2ActionPerformed

    private void pemesananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pemesananMouseClicked
        ImageIcon psn = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_3_30px_P.png"));
        ico_pemesanan.setIcon(psn);
        ImageIcon brd = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_1_30px.png"));
        ico_beranda.setIcon(brd);
        ImageIcon kmr = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_2_30px.png"));
        ico_kamar.setIcon(kmr);

        setColor(pemesanan);
        setColorText(Lbl_pemesanan);
        resetColor(beranda);
        resetColorText(Lbl_beranda);
        resetColor(kamar);
        resetColorText(Lbl_kamar);

        PemesananSideBar.setVisible(true);
        PemesananSideBar.setEnabled(true);
        BerandaSideBar.setVisible(false);
        BerandaSideBar.setEnabled(false);
        KamarSideBar.setVisible(false);
        KamarSideBar.setEnabled(false);

    }//GEN-LAST:event_pemesananMouseClicked

    private void lbl_Menu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_Menu1MouseClicked
        lbl_Menu1.setVisible(false);
        lbl_Menu1.setEnabled(false);
        bttn_Menu7.setVisible(true);
        bttn_Menu7.setEnabled(true);
        setPanel(BerandaSideBar);
        lbl_judul1.setBounds(545, 10, 260, 90);
        lbl_Slide1.setBounds(448, 130, 450, 260);
        button.setBounds(575, 390, 200, 220);
        lbl_Text2.setBounds(400, 440, 570, 20);
        lbl_Kamar3.setBounds(420, 500, 160, 200);
        lbl_Kamar4.setBounds(790, 500, 160, 200);
        bttn_minimized2.setBounds(1286, 15, 20, 20);
        bttn_close1.setBounds(1316, 10, 20, 20);
        PanelChat.setBounds(1138, 710, 210, 70);
    }//GEN-LAST:event_lbl_Menu1MouseClicked

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Login lg = new Login();
                lg.show();
                dispose();
            }
        }, 10 * 5);
    }//GEN-LAST:event_LogoutActionPerformed

    private void gb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gb1ActionPerformed
        ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKostFoto/1.png"));
        lbl_Slide1.setIcon(gmbr);
    }//GEN-LAST:event_gb1ActionPerformed

    private void gb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gb2ActionPerformed
        ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKostFoto/2.png"));
        lbl_Slide1.setIcon(gmbr);
    }//GEN-LAST:event_gb2ActionPerformed

    private void gb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gb3ActionPerformed
        ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKostFoto/3.png"));
        lbl_Slide1.setIcon(gmbr);
    }//GEN-LAST:event_gb3ActionPerformed

    private void gb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gb4ActionPerformed
        ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKostFoto/4.png"));
        lbl_Slide1.setIcon(gmbr);
    }//GEN-LAST:event_gb4ActionPerformed

    private void gb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gb5ActionPerformed
        ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKostFoto/5.png"));
        lbl_Slide1.setIcon(gmbr);
    }//GEN-LAST:event_gb5ActionPerformed

    private void gb6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gb6ActionPerformed
        ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKostFoto/6.png"));
        lbl_Slide1.setIcon(gmbr);
    }//GEN-LAST:event_gb6ActionPerformed

    private void gb7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gb7ActionPerformed
        ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKostFoto/7.png"));
        lbl_Slide1.setIcon(gmbr);
    }//GEN-LAST:event_gb7ActionPerformed

    private void gb8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gb8ActionPerformed
        ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKostFoto/8.png"));
        lbl_Slide1.setIcon(gmbr);
    }//GEN-LAST:event_gb8ActionPerformed

    private void gb9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gb9ActionPerformed
        ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKostFoto/9.png"));
        lbl_Slide1.setIcon(gmbr);
    }//GEN-LAST:event_gb9ActionPerformed

    private void gb10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gb10ActionPerformed
        ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKostFoto/10.png"));
        lbl_Slide1.setIcon(gmbr);
    }//GEN-LAST:event_gb10ActionPerformed

    private void bttn_Menu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttn_Menu7MouseClicked
        bttn_Menu7.setVisible(false);
        bttn_Menu7.setEnabled(false);
        lbl_Menu1.setVisible(true);
        lbl_Menu1.setEnabled(true);
        lbl_judul1.setBounds(445, 10, 260, 90);
        lbl_Slide1.setBounds(350, 130, 450, 260);
        button.setBounds(480, 390, 200, 220);
        lbl_Text2.setBounds(300, 440, 570, 20);
        lbl_Kamar3.setBounds(340, 500, 160, 200);
        lbl_Kamar4.setBounds(670, 500, 160, 200);
        bttn_minimized2.setBounds(1080, 15, 20, 20);
        bttn_close1.setBounds(1110, 10, 20, 20);
        PanelChat.setBounds(932, 710, 210, 70);
        resetPanel(BerandaSideBar);
    }//GEN-LAST:event_bttn_Menu7MouseClicked

    private void chatBxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatBxActionPerformed
        try {
            Desktop.getDesktop().browse(new URL("https://api.whatsapp.com/send?phone=6287743310528&text=Kak%20saya%20ingin%20cek%20kamar.😊").toURI());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_chatBxActionPerformed

    private void lbl_Menu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_Menu2MouseClicked
        lbl_Menu2.setVisible(false);
        lbl_Menu2.setEnabled(false);
        lbl_Menu12.setVisible(true);
        lbl_Menu12.setEnabled(true);
        setPanel(KamarSideBar);
        lbl_judul2.setBounds(545, 10, 260, 90);
        ScrollData2.setBounds(154, 110, 1050, 610);
        bttn_minimized4.setBounds(1286, 15, 20, 20);
        bttn_close2.setBounds(1316, 10, 20, 20);
    }//GEN-LAST:event_lbl_Menu2MouseClicked

    private void lbl_Menu12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_Menu12MouseClicked
        lbl_Menu12.setVisible(false);
        lbl_Menu12.setEnabled(false);
        lbl_Menu2.setVisible(true);
        lbl_Menu2.setEnabled(true);
        resetPanel(KamarSideBar);
        lbl_judul2.setBounds(445, 10, 260, 90);
        ScrollData2.setBounds(50, 110, 1050, 610);
        bttn_minimized4.setBounds(1080, 15, 20, 20);
        bttn_close2.setBounds(1110, 10, 20, 20);
    }//GEN-LAST:event_lbl_Menu12MouseClicked

    private void bttn_minimized4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_minimized4ActionPerformed
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_bttn_minimized4ActionPerformed

    private void bttn_close2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_close2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bttn_close2ActionPerformed

    private void DataKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataKamarMouseClicked
        try {
            int row = DataKamar.getSelectedRow();
            txtKodeKamarPsn.setText(DataKamar.getValueAt(row, 0).toString());
            FieldKodeKamar.setText(DataKamar.getValueAt(row, 0).toString());
            FieldLokasiKamar.setText(DataKamar.getValueAt(row, 1).toString());
            FieldJenisKamar.setText(DataKamar.getValueAt(row, 2).toString());
            FieldDsc.setText(DataKamar.getValueAt(row, 3).toString());
            FieldHarga.setText(DataKamar.getValueAt(row, 4).toString());
            FieldStatus.setText(DataKamar.getValueAt(row, 5).toString());

            String kode_kamar = txtKodeKamarPsn.getText();
            ConnectDB konek = new ConnectDB();
            ResultSet rs = konek.selectDB7(kode_kamar);
            if (rs.next()) {
                byte[] img = rs.getBytes("gambar");
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(FotoKamar.getWidth(), FotoKamar.getHeight(), Image.SCALE_SMOOTH));
                FotoKamar.setIcon(imageIcon);

                if ("Tersedia".equals(rs.getString("status"))) {
                    ImageIcon psn = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_3_30px_P.png"));
                    ico_pemesanan.setIcon(psn);
                    ImageIcon brd = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_1_30px.png"));
                    ico_beranda.setIcon(brd);
                    ImageIcon kmr = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_2_30px.png"));
                    ico_kamar.setIcon(kmr);

                    setColor(pemesanan);
                    setColorText(Lbl_pemesanan);
                    resetColor(beranda);
                    resetColorText(Lbl_beranda);
                    resetColor(kamar);
                    resetColorText(Lbl_kamar);

                    BerandaSideBar.setVisible(false);
                    BerandaSideBar.setEnabled(false);
                    KamarSideBar.setVisible(false);
                    KamarSideBar.setEnabled(false);
                    PemesananSideBar.setVisible(true);
                    PemesananSideBar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih Kamar Yang Tersedia");
                    resetForm2();
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(MenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_DataKamarMouseClicked

    private void lbl_Menu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_Menu5MouseClicked
        lbl_Menu5.setVisible(false);
        lbl_Menu5.setEnabled(false);
        lbl_Menu10.setVisible(true);
        lbl_Menu10.setEnabled(true);
        setPanel(PemesananSideBar);
        lbl_judul4.setBounds(545, 10, 260, 90);
        form.setBounds(154, 110, 1050, 610);
        bttn_minimized8.setBounds(1286, 15, 20, 20);
        bttn_close4.setBounds(1316, 10, 20, 20);
    }//GEN-LAST:event_lbl_Menu5MouseClicked

    private void lbl_Menu10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_Menu10MouseClicked
        lbl_Menu10.setVisible(false);
        lbl_Menu10.setEnabled(false);
        lbl_Menu5.setVisible(true);
        lbl_Menu5.setEnabled(true);
        resetPanel(PemesananSideBar);
        lbl_judul4.setBounds(445, 10, 260, 90);
        form.setBounds(50, 110, 1050, 610);
        bttn_minimized8.setBounds(1080, 15, 20, 20);
        bttn_close4.setBounds(1110, 10, 20, 20);
    }//GEN-LAST:event_lbl_Menu10MouseClicked

    private void bttn_minimized8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_minimized8ActionPerformed
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_bttn_minimized8ActionPerformed

    private void bttn_close4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_close4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bttn_close4ActionPerformed

    private void bttn_CekButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_CekButton6ActionPerformed

    }//GEN-LAST:event_bttn_CekButton6ActionPerformed

    private void txtcari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcari2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcari2ActionPerformed


    private void cmbkategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbkategoriActionPerformed
        try {
            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("KODE KAMAR");
            tbl.addColumn("LOKASI KAMAR");
            tbl.addColumn("JENIS KAMAR");
            tbl.addColumn("DESKRIPSI KAMAR");
            tbl.addColumn("HARGA");
            tbl.addColumn("STATUS");
            DataKamar.setModel(tbl);

            String cari = cmbkategori.getSelectedItem().toString();
            ConnectDB konek = new ConnectDB();
            ResultSet rs = konek.selectDB9(cari);
            if ("Kategori".equals("Kategori")) {
                showTableKamar2();
            }
            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("kode_kamar"),
                    rs.getString("lokasi_kamar"),
                    rs.getString("jenis_kamar"),
                    rs.getString("dsc_fasilitas"),
                    rs.getString("harga"),
                    rs.getString("status")
                });
                DataKamar.setModel(tbl);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Error Pada Pilih Kategori!!!");
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_cmbkategoriActionPerformed

    private void txtcari2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcari2KeyReleased
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("KODE KAMAR");
        tbl.addColumn("LOKASI KAMAR");
        tbl.addColumn("JENIS KAMAR");
        tbl.addColumn("DESKRIPSI KAMAR");
        tbl.addColumn("HARGA");
        tbl.addColumn("STATUS");
        DataKamar.setModel(tbl);

        try {
            String cari = txtcari2.getText();
            ConnectDB konek = new ConnectDB();
            ResultSet rs = konek.selectDB6(cari);

            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("kode_kamar"),
                    rs.getString("lokasi_kamar"),
                    rs.getString("jenis_kamar"),
                    rs.getString("dsc_fasilitas"),
                    rs.getString("harga"),
                    rs.getString("status")
                });
                DataKamar.setModel(tbl);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Error !!!");
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_txtcari2KeyReleased

    private void refresh2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh2MouseClicked
        ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/refresh.gif"));
        refresh2.setIcon(gmbr);
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                resetForm2();
                ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/refresh1.png"));
                refresh2.setIcon(gmbr);
            }
        }, 1 * 5);
    }//GEN-LAST:event_refresh2MouseClicked

    private void Cek1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cek1ActionPerformed
        try {
        ImageIcon kmr = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_2_30px_P.png"));
        ico_kamar.setIcon(kmr);
        ImageIcon brd = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_1_30px.png"));
        ico_beranda.setIcon(brd);
        ImageIcon psn = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_3_30px.png"));
        ico_pemesanan.setIcon(psn);
        setColor(kamar);
        setColorText(Lbl_kamar);
        resetColor(beranda);
        resetColorText(Lbl_beranda);
        resetColor(pemesanan);
        resetColorText(Lbl_pemesanan);

        KamarSideBar.setVisible(true);
        KamarSideBar.setEnabled(true);
        BerandaSideBar.setVisible(false);
        BerandaSideBar.setEnabled(false);
        PemesananSideBar.setVisible(false);
        PemesananSideBar.setEnabled(false);

            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("KODE KAMAR");
            tbl.addColumn("LOKASI KAMAR");
            tbl.addColumn("JENIS KAMAR");
            tbl.addColumn("DESKRIPSI KAMAR");
            tbl.addColumn("HARGA");
            tbl.addColumn("STATUS");
            DataKamar.setModel(tbl);

            String cari = cmbkategori.getSelectedItem().toString();
            ConnectDB konek = new ConnectDB();
            ResultSet rs = konek.selectDB10();

            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("kode_kamar"),
                    rs.getString("lokasi_kamar"),
                    rs.getString("jenis_kamar"),
                    rs.getString("dsc_fasilitas"),
                    rs.getString("harga"),
                    rs.getString("status")
                });
                DataKamar.setModel(tbl);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Error Pada Pilih Kategori!!!");
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_Cek1ActionPerformed

    private void Cek2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cek2ActionPerformed
        try {
        ImageIcon kmr = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_2_30px_P.png"));
        ico_kamar.setIcon(kmr);
        ImageIcon brd = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_1_30px.png"));
        ico_beranda.setIcon(brd);
        ImageIcon psn = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/icon_3_30px.png"));
        ico_pemesanan.setIcon(psn);
        setColor(kamar);
        setColorText(Lbl_kamar);
        resetColor(beranda);
        resetColorText(Lbl_beranda);
        resetColor(pemesanan);
        resetColorText(Lbl_pemesanan);

        KamarSideBar.setVisible(true);
        KamarSideBar.setEnabled(true);
        BerandaSideBar.setVisible(false);
        BerandaSideBar.setEnabled(false);
        PemesananSideBar.setVisible(false);
        PemesananSideBar.setEnabled(false);

            DefaultTableModel tbl = new DefaultTableModel();
            tbl.addColumn("KODE KAMAR");
            tbl.addColumn("LOKASI KAMAR");
            tbl.addColumn("JENIS KAMAR");
            tbl.addColumn("DESKRIPSI KAMAR");
            tbl.addColumn("HARGA");
            tbl.addColumn("STATUS");
            DataKamar.setModel(tbl);

            String cari = cmbkategori.getSelectedItem().toString();
            ConnectDB konek = new ConnectDB();
            ResultSet rs = konek.selectDB11();

            while (rs.next()) {
                tbl.addRow(new Object[]{
                    rs.getString("kode_kamar"),
                    rs.getString("lokasi_kamar"),
                    rs.getString("jenis_kamar"),
                    rs.getString("dsc_fasilitas"),
                    rs.getString("harga"),
                    rs.getString("status")
                });
                DataKamar.setModel(tbl);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Error Pada Pilih Kategori!!!");
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_Cek2ActionPerformed

    private void refresh3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh3MouseClicked
        ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/refresh.gif"));
        refresh3.setIcon(gmbr);
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                showTableKamar2();
                ImageIcon gmbr = new ImageIcon(getClass().getResource("/SI_AiraKost_Asset/refresh1.png"));
                refresh3.setIcon(gmbr);
            }
        }, 1 * 5);
    }//GEN-LAST:event_refresh3MouseClicked

    private void PesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesanActionPerformed
        try {
            String IdBooking, kode_kamar, id_cust, TglMasuk, TglKeluar, kode_pembayaran, TotalHarga, status;
            String  LamaSewa = null;
            IdBooking = txtBooking.getText();
            kode_kamar = txtKodeKamarPsn.getText();
            id_cust = txtIdCust.getText();
            String tampil = "yyyy-MM-dd";
            SimpleDateFormat fmt = new SimpleDateFormat(tampil);
            TglMasuk = String.valueOf(fmt.format(tgl_masuk.getDate()));
            TglKeluar = String.valueOf(fmt.format(tgl_keluar.getDate()));
            LamaSewa = txtLamaSewa.getText();
            kode_pembayaran = txtKodeBayar.getText();
            TotalHarga = txtTotaBayar.getText();
            status = cmb_Status2.getSelectedItem().toString();

            ConnectDB konek = new ConnectDB();
            konek.insertDB0(IdBooking, kode_kamar, id_cust, TglMasuk, LamaSewa, TglKeluar, kode_pembayaran, TotalHarga);
            konek.updateDB(kode_kamar, status);
            JOptionPane.showMessageDialog(rootPane, "Tambah Data Pemesanan Sukses!!!");
            resetForm2();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Ada Kesalahan Pada Data Pemesanan");
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_PesanActionPerformed

    private void tgl_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_keluarMouseClicked

    }//GEN-LAST:event_tgl_keluarMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        hitungselisih();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BerandaSideBar;
    private javax.swing.JPanel Bg;
    private javax.swing.JButton Cek1;
    private javax.swing.JButton Cek2;
    private javax.swing.JTable DataKamar;
    public static final javax.swing.JTextArea FieldDsc = new javax.swing.JTextArea();
    public static final javax.swing.JLabel FieldHarga = new javax.swing.JLabel();
    public static final javax.swing.JLabel FieldJenisKamar = new javax.swing.JLabel();
    public static final javax.swing.JLabel FieldKodeKamar = new javax.swing.JLabel();
    public static final javax.swing.JLabel FieldLokasiKamar = new javax.swing.JLabel();
    public static final javax.swing.JLabel FieldStatus = new javax.swing.JLabel();
    public static final javax.swing.JLabel FotoKamar = new javax.swing.JLabel();
    private javax.swing.JPanel KamarSideBar;
    private javax.swing.JLabel Lbl_beranda;
    private javax.swing.JLabel Lbl_kamar;
    private javax.swing.JLabel Lbl_pemesanan;
    private javax.swing.JToggleButton Logout;
    public static final javax.swing.JLabel NamaUser2 = new javax.swing.JLabel();
    private javax.swing.JPanel PanelChat;
    private javax.swing.JPanel PanelKamar;
    private javax.swing.JPanel PemesananSideBar;
    private javax.swing.JToggleButton Pesan;
    private javax.swing.JScrollPane ScrollData;
    private javax.swing.JScrollPane ScrollData2;
    private javax.swing.JPanel SideBarMenu;
    private javax.swing.JPanel beranda;
    private javax.swing.JButton bttn_CekButton6;
    private javax.swing.JLabel bttn_Menu7;
    private javax.swing.JButton bttn_close1;
    private javax.swing.JButton bttn_close2;
    private javax.swing.JButton bttn_close4;
    private javax.swing.JButton bttn_minimized2;
    private javax.swing.JButton bttn_minimized4;
    private javax.swing.JButton bttn_minimized8;
    private javax.swing.JPanel button;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JToggleButton chatBx;
    public static final javax.swing.JComboBox<String> cmb_Status2 = new javax.swing.JComboBox<>();
    private javax.swing.JComboBox<String> cmbkategori;
    private javax.swing.JPanel form;
    private javax.swing.JRadioButton gb1;
    private javax.swing.JRadioButton gb10;
    private javax.swing.JRadioButton gb2;
    private javax.swing.JRadioButton gb3;
    private javax.swing.JRadioButton gb4;
    private javax.swing.JRadioButton gb5;
    private javax.swing.JRadioButton gb6;
    private javax.swing.JRadioButton gb7;
    private javax.swing.JRadioButton gb8;
    private javax.swing.JRadioButton gb9;
    private javax.swing.JLabel ico_beranda;
    private javax.swing.JLabel ico_kamar;
    private javax.swing.JLabel ico_pemesanan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel kamar;
    private javax.swing.JLabel lbl_Kamar3;
    private javax.swing.JLabel lbl_Kamar4;
    private javax.swing.JLabel lbl_Menu1;
    private javax.swing.JLabel lbl_Menu10;
    private javax.swing.JLabel lbl_Menu12;
    private javax.swing.JLabel lbl_Menu2;
    private javax.swing.JLabel lbl_Menu5;
    private javax.swing.JLabel lbl_Slide1;
    private javax.swing.JLabel lbl_Text2;
    private javax.swing.JLabel lbl_besar;
    private javax.swing.JLabel lbl_chatskrng_txt;
    private javax.swing.JLabel lbl_judul;
    private javax.swing.JLabel lbl_judul1;
    private javax.swing.JLabel lbl_judul2;
    private javax.swing.JLabel lbl_judul4;
    private javax.swing.JLabel lbl_photos;
    private javax.swing.JLabel lbl_wa_icon;
    public static final javax.swing.JTextField nm_lengkap = new javax.swing.JTextField();
    private javax.swing.JPanel pemesanan;
    private javax.swing.JLabel refresh2;
    private javax.swing.JLabel refresh3;
    private com.toedter.calendar.JDateChooser tgl_keluar;
    private com.toedter.calendar.JDateChooser tgl_masuk;
    public static final javax.swing.JTextField txtAlamat = new javax.swing.JTextField();
    private javax.swing.JTextField txtBooking;
    public static final javax.swing.JTextField txtIdCust = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtJenisKelamin = new javax.swing.JTextField();
    private javax.swing.JTextField txtKodeBayar;
    public static final javax.swing.JTextField txtKodeKamarPsn = new javax.swing.JTextField();
    private javax.swing.JTextField txtLamaSewa;
    public static final javax.swing.JTextField txtNoDarurat = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtNoHP = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtNoKTP = new javax.swing.JTextField();
    private javax.swing.JTextField txtTotaBayar;
    private javax.swing.JTextField txtcari2;
    // End of variables declaration//GEN-END:variables
}
