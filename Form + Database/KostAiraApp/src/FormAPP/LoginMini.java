/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormAPP;

import static FormAPP.MenuUser.NamaUser2;
import static FormAPP.MenuUser.*;
import java.awt.Toolkit;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import static FormAPP.MenuAdmin.lbl_NamaUser1;

/**
 *
 * @author Tony's PC
 */
public class LoginMini extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public LoginMini() {
        initComponents();
        iconApp();
        loader.setVisible(false);
        loader.setEnabled(false);

    }
    int xy, xx;
    private boolean loginInProgress;

    private void iconApp() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/SI_AiraKost_Asset/LOGOKOST.png")));
    }
    
    private void loginUser() {
        if (loginInProgress) {
            return;
        }

        final String username = UserField.getText().trim();
        final String password = new String(PassField.getPassword());
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan password wajib diisi.",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        loginInProgress = true;
        LoginMini.setVisible(false);
        LoginMini.setEnabled(false);
        loader.setVisible(true);
        loader.setEnabled(true);

        new SwingWorker<PreparedLogin, Void>() {
            @Override
            protected PreparedLogin doInBackground() throws Exception {
                ConnectDB.AuthenticatedUser user = new ConnectDB().authenticate(username, password);
                if (user == null) {
                    return new PreparedLogin(null, null, null);
                }
                if ("Admin".equalsIgnoreCase(user.akses)) {
                    return new PreparedLogin(user, new MenuAdmin(), null);
                }
                if ("User".equalsIgnoreCase(user.akses)) {
                    return new PreparedLogin(user, null, new MenuUser());
                }
                throw new IllegalStateException("Hak akses akun tidak dikenali: " + user.akses);
            }

            @Override
            protected void done() {
                try {
                    PreparedLogin preparedLogin = get();
                    if (preparedLogin.user == null) {
                        restoreLoginForm();
                        JOptionPane.showMessageDialog(LoginMini.this,
                                "Username atau password salah.", "Login gagal",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    openDashboard(preparedLogin);
                    dispose();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    showLoginError(ex);
                } catch (ExecutionException ex) {
                    showLoginError(ex.getCause() == null ? ex : ex.getCause());
                } catch (Throwable ex) {
                    showLoginError(ex);
                }
            }
        }.execute();
    }

    private void openDashboard(PreparedLogin preparedLogin) {
        ConnectDB.AuthenticatedUser user = preparedLogin.user;
        if (preparedLogin.admin != null) {
            lbl_NamaUser1.setText(user.username);
            preparedLogin.admin.setVisible(true);
            return;
        }

        NamaUser2.setText(valueOrEmpty(user.namaLengkap));
        MenuUser.txtIdCust.setText(valueOrEmpty(user.idCust));
        MenuUser.txtNoKTP.setText(valueOrEmpty(user.noKtp));
        MenuUser.txtNoKTP.setEnabled(false);
        MenuUser.nm_lengkap.setText(valueOrEmpty(user.namaLengkap));
        MenuUser.nm_lengkap.setEnabled(false);
        MenuUser.txtAlamat.setText(valueOrEmpty(user.alamat));
        MenuUser.txtAlamat.setEnabled(true);
        MenuUser.txtJenisKelamin.setText(valueOrEmpty(user.jenisKelamin));
        MenuUser.txtJenisKelamin.setEnabled(false);
        MenuUser.txtNoHP.setText(valueOrEmpty(user.noHpPribadi));
        MenuUser.txtNoHP.setEnabled(true);
        MenuUser.txtNoDarurat.setText(valueOrEmpty(user.noHpDarurat));
        MenuUser.txtNoDarurat.setEnabled(true);
        preparedLogin.menuUser.setVisible(true);
    }

    private static final class PreparedLogin {

        private final ConnectDB.AuthenticatedUser user;
        private final MenuAdmin admin;
        private final MenuUser menuUser;

        private PreparedLogin(ConnectDB.AuthenticatedUser user, MenuAdmin admin, MenuUser menuUser) {
            this.user = user;
            this.admin = admin;
            this.menuUser = menuUser;
        }
    }

    private String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }

    private void restoreLoginForm() {
        loginInProgress = false;
        loader.setVisible(false);
        loader.setEnabled(false);
        LoginMini.setVisible(true);
        LoginMini.setEnabled(true);
    }

    private void showLoginError(Throwable error) {
        Logger.getLogger(LoginMini.class.getName()).log(Level.SEVERE, "Proses login gagal", error);
        restoreLoginForm();
        String detail = error.getMessage();
        if (detail == null || detail.trim().isEmpty()) {
            detail = error.getClass().getSimpleName();
        }
        JOptionPane.showMessageDialog(this,
                "Login tidak dapat diselesaikan.\n" + detail,
                "Kesalahan login", JOptionPane.ERROR_MESSAGE);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Bg = new javax.swing.JPanel();
        LoginMini = new javax.swing.JPanel();
        Lbl_Judul = new javax.swing.JLabel();
        Lbl_Username = new javax.swing.JLabel();
        UserField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        Lbl_Password = new javax.swing.JLabel();
        bttn_Hide = new javax.swing.JButton();
        bttn_Show = new javax.swing.JButton();
        PassField = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        Daftar = new javax.swing.JToggleButton();
        Masuk = new javax.swing.JToggleButton();
        bttn_fullscreen = new javax.swing.JButton();
        bttn_minimized1 = new javax.swing.JButton();
        bttn_close = new javax.swing.JButton();
        lbl_pola = new javax.swing.JLabel();
        lbl_gambar1 = new javax.swing.JLabel();
        loader = new javax.swing.JPanel();
        loading = new javax.swing.JPanel();
        lbl_loadjudul = new javax.swing.JLabel();
        lbl_loader = new javax.swing.JLabel();
        img_loader = new javax.swing.JLabel();
        lbl_loadbesar = new javax.swing.JLabel();
        jLabel2_load = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KostAiraApp");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setResizable(false);

        Bg.setBackground(new java.awt.Color(255, 255, 255));
        Bg.setLayout(null);

        LoginMini.setBackground(new java.awt.Color(255, 255, 255));
        LoginMini.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                LoginMiniMouseDragged(evt);
            }
        });
        LoginMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LoginMiniMousePressed(evt);
            }
        });
        LoginMini.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Lbl_Judul.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Lbl_Judul.setForeground(new java.awt.Color(51, 51, 51));
        Lbl_Judul.setText("Login to Kost Aira App ");
        LoginMini.add(Lbl_Judul, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, -1, -1));

        Lbl_Username.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Lbl_Username.setForeground(new java.awt.Color(51, 51, 51));
        Lbl_Username.setText("Username");
        LoginMini.add(Lbl_Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, -1, -1));

        UserField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        UserField.setBorder(null);
        UserField.setOpaque(false);
        LoginMini.add(UserField, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 270, 40));
        LoginMini.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, 270, 10));

        Lbl_Password.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Lbl_Password.setForeground(new java.awt.Color(51, 51, 51));
        Lbl_Password.setText("Password");
        LoginMini.add(Lbl_Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, -1, -1));

        bttn_Hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/hide_32px.png"))); // NOI18N
        bttn_Hide.setBorder(null);
        bttn_Hide.setBorderPainted(false);
        bttn_Hide.setContentAreaFilled(false);
        bttn_Hide.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttn_Hide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_HideActionPerformed(evt);
            }
        });
        LoginMini.add(bttn_Hide, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 340, 26, 18));

        bttn_Show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/show_32px.png"))); // NOI18N
        bttn_Show.setBorder(null);
        bttn_Show.setBorderPainted(false);
        bttn_Show.setContentAreaFilled(false);
        bttn_Show.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttn_Show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_ShowActionPerformed(evt);
            }
        });
        LoginMini.add(bttn_Show, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 340, 26, 18));

        PassField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        PassField.setBorder(null);
        PassField.setOpaque(false);
        LoginMini.add(PassField, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 270, 40));
        LoginMini.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, 270, 10));

        Daftar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Daftar.setForeground(new java.awt.Color(51, 51, 51));
        Daftar.setText("Daftar");
        Daftar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2));
        Daftar.setContentAreaFilled(false);
        Daftar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Daftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DaftarActionPerformed(evt);
            }
        });
        LoginMini.add(Daftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 390, 98, 39));

        Masuk.setBackground(new java.awt.Color(52, 135, 239));
        Masuk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Masuk.setForeground(new java.awt.Color(255, 255, 255));
        Masuk.setText("Login");
        Masuk.setBorder(null);
        Masuk.setBorderPainted(false);
        Masuk.setContentAreaFilled(false);
        Masuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Masuk.setOpaque(true);
        Masuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasukActionPerformed(evt);
            }
        });
        LoginMini.add(Masuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, 100, 40));

        bttn_fullscreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/maximize.png"))); // NOI18N
        bttn_fullscreen.setBorder(null);
        bttn_fullscreen.setBorderPainted(false);
        bttn_fullscreen.setContentAreaFilled(false);
        bttn_fullscreen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttn_fullscreen.setFocusPainted(false);
        bttn_fullscreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_fullscreenActionPerformed(evt);
            }
        });
        LoginMini.add(bttn_fullscreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 10, 20, 20));

        bttn_minimized1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/minimize.png"))); // NOI18N
        bttn_minimized1.setBorder(null);
        bttn_minimized1.setBorderPainted(false);
        bttn_minimized1.setContentAreaFilled(false);
        bttn_minimized1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttn_minimized1.setFocusPainted(false);
        bttn_minimized1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_minimized1ActionPerformed(evt);
            }
        });
        LoginMini.add(bttn_minimized1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 16, 20, 20));

        bttn_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/close.png"))); // NOI18N
        bttn_close.setBorder(null);
        bttn_close.setBorderPainted(false);
        bttn_close.setContentAreaFilled(false);
        bttn_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttn_close.setFocusPainted(false);
        bttn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttn_closeActionPerformed(evt);
            }
        });
        LoginMini.add(bttn_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, -1, 20));

        lbl_pola.setBackground(new java.awt.Color(255, 255, 255));
        lbl_pola.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/pola.png"))); // NOI18N
        lbl_pola.setOpaque(true);
        LoginMini.add(lbl_pola, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 450, 600));

        lbl_gambar1.setBackground(new java.awt.Color(102, 102, 102));
        lbl_gambar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/Thumbnail_Logins.png"))); // NOI18N
        lbl_gambar1.setOpaque(true);
        LoginMini.add(lbl_gambar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 600));

        Bg.add(LoginMini);
        LoginMini.setBounds(0, 0, 898, 600);

        loader.setBackground(new java.awt.Color(255, 255, 255));
        loader.setOpaque(false);
        loader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loading.setBackground(new java.awt.Color(255, 255, 255));
        loading.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_loadjudul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/Label1.png"))); // NOI18N
        loading.add(lbl_loadjudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 200, 60));

        lbl_loader.setBackground(new java.awt.Color(255, 255, 255));
        lbl_loader.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_loader.setForeground(new java.awt.Color(96, 82, 76));
        lbl_loader.setText("Mohon Tunggu . . .");
        loading.add(lbl_loader, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, -1, -1));

        img_loader.setBackground(new java.awt.Color(255, 255, 255));
        img_loader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/ring_1.gif"))); // NOI18N
        img_loader.setOpaque(true);
        loading.add(img_loader, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 139, 141));

        lbl_loadbesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/3.1.png"))); // NOI18N
        loading.add(lbl_loadbesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 310));

        loader.add(loading, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 610, 310));

        jLabel2_load.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SI_AiraKost_Asset/blurLoginM.png"))); // NOI18N
        jLabel2_load.setMaximumSize(new java.awt.Dimension(910, 600));
        jLabel2_load.setMinimumSize(new java.awt.Dimension(910, 600));
        jLabel2_load.setOpaque(true);
        jLabel2_load.setPreferredSize(new java.awt.Dimension(910, 600));
        loader.add(jLabel2_load, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 600));

        Bg.add(loader);
        loader.setBounds(0, 0, 910, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bg, javax.swing.GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(898, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DaftarActionPerformed
        LoginMini.setVisible(false);
        LoginMini.setEnabled(false);
        loader.setVisible(true);
        loader.setEnabled(true);

        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                DaftarMini mini = new DaftarMini();
                mini.show();
                dispose();
            }
            
        }, 5 * 5);
    }//GEN-LAST:event_DaftarActionPerformed

    private void MasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasukActionPerformed
        loginUser();
    }//GEN-LAST:event_MasukActionPerformed

    private void bttn_HideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_HideActionPerformed
        bttn_Show.setVisible(true);
        bttn_Show.setEnabled(true);
        PassField.setEchoChar((char) 0);
        bttn_Hide.setVisible(false);
        bttn_Hide.setEnabled(false);
    }//GEN-LAST:event_bttn_HideActionPerformed

    private void bttn_ShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_ShowActionPerformed
        bttn_Hide.setVisible(true);
        bttn_Hide.setEnabled(true);
        PassField.setEchoChar((char) 8226);
        bttn_Show.setVisible(false);
        bttn_Show.setEnabled(false);
    }//GEN-LAST:event_bttn_ShowActionPerformed

    private void bttn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_closeActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bttn_closeActionPerformed

    private void bttn_minimized1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_minimized1ActionPerformed
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_bttn_minimized1ActionPerformed

    private void LoginMiniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginMiniMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_LoginMiniMousePressed

    private void LoginMiniMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginMiniMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_LoginMiniMouseDragged

    private void bttn_fullscreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttn_fullscreenActionPerformed
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Login full = new Login();
                full.show();
                dispose();
            }
        }, 5 * 5);

    }//GEN-LAST:event_bttn_fullscreenActionPerformed

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
            java.util.logging.Logger.getLogger(LoginMini.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginMini.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginMini.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginMini.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginMini().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bg;
    private javax.swing.JToggleButton Daftar;
    private javax.swing.JLabel Lbl_Judul;
    private javax.swing.JLabel Lbl_Password;
    private javax.swing.JLabel Lbl_Username;
    private javax.swing.JPanel LoginMini;
    private javax.swing.JToggleButton Masuk;
    private javax.swing.JPasswordField PassField;
    private javax.swing.JTextField UserField;
    private javax.swing.JButton bttn_Hide;
    private javax.swing.JButton bttn_Show;
    private javax.swing.JButton bttn_close;
    private javax.swing.JButton bttn_fullscreen;
    private javax.swing.JButton bttn_minimized1;
    private javax.swing.JLabel img_loader;
    private javax.swing.JLabel jLabel2_load;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbl_gambar1;
    private javax.swing.JLabel lbl_loadbesar;
    private javax.swing.JLabel lbl_loader;
    private javax.swing.JLabel lbl_loadjudul;
    private javax.swing.JLabel lbl_pola;
    private javax.swing.JPanel loader;
    private javax.swing.JPanel loading;
    // End of variables declaration//GEN-END:variables
}
