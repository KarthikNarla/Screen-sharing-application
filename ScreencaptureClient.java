
import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ScreencaptureClient extends javax.swing.JFrame {

    ClientThread ct;
    User u;

    /**
     * Creates new form ScreencaptureClient
     */
    ICaptureScreen lmodel;
    RemoteScreen rmodel;
    Socket s;
    String un;
    DefaultListModel<String> lstModel = new DefaultListModel<>();
    String serverName;
    int serverPort;
    boolean isPresenter;

    public ScreencaptureClient() throws AWTException {
        initComponents();
        un = JOptionPane.showInputDialog(this, "Enter your name");
        lblwelcome.setText("welcome" + un + "!");
        int width;
        int height;
        lmodel = new LocalScreen();
        serverName = JOptionPane.showInputDialog(this, "Enter Server IP/Name", "localhost");
        serverPort = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Port", "9000"));
        lstOnlineUsers.setModel(lstModel);
        btnSend.setEnabled(false);
        try {
            s = new Socket(serverName, serverPort);
            u = new User(s);
            u.name = un;
            setTitle("welcome " + u.name + "!");
            rmodel = new RemoteScreen(u);
            ct = new ClientThread(u);
            ct.start();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "server is not running localscreen can capture");
            RemoteScreen.setEnabled(false);
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

        pmPresenter = new javax.swing.JPopupMenu();
        mniMakePresenter = new javax.swing.JMenuItem();
        Chat = new javax.swing.JMenuItem();
        LocalScreen = new javax.swing.JButton();
        RemoteScreen = new javax.swing.JButton();
        lbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstOnlineUsers = new javax.swing.JList();
        btnSend = new javax.swing.JButton();
        lblwelcome = new javax.swing.JLabel();

        mniMakePresenter.setText("Make Presenter");
        mniMakePresenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniMakePresenterActionPerformed(evt);
            }
        });
        pmPresenter.add(mniMakePresenter);

        Chat.setText("Chat");
        pmPresenter.add(Chat);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LocalScreen.setText("Local");
        LocalScreen.setEnabled(false);
        LocalScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalScreenActionPerformed(evt);
            }
        });

        RemoteScreen.setText("Remote");
        RemoteScreen.setEnabled(false);
        RemoteScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoteScreenActionPerformed(evt);
            }
        });

        lstOnlineUsers.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstOnlineUsers.setComponentPopupMenu(pmPresenter);
        lstOnlineUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstOnlineUsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstOnlineUsers);

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(RemoteScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LocalScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend)
                    .addComponent(lblwelcome))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblwelcome)
                .addGap(7, 7, 7)
                .addComponent(LocalScreen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RemoteScreen)
                .addGap(1, 1, 1)
                .addComponent(btnSend)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LocalScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocalScreenActionPerformed
        // TODO add your handling code here:
        //BufferedImage bi=icap.CaptureLocalScreen();
        //lbl.setIcon(new ImageIcon(bi));
        BufferedImage bi = lmodel.CaptureScreen(lbl.getWidth(), lbl.getHeight());
        lbl.setIcon(new ImageIcon(bi));
    }//GEN-LAST:event_LocalScreenActionPerformed

    private void RemoteScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoteScreenActionPerformed
        // TODO add your handling code here:
        BufferedImage bi = rmodel.CaptureScreen(lbl.getWidth(), lbl.getHeight());
        lbl.setIcon(new ImageIcon(bi));
    }//GEN-LAST:event_RemoteScreenActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                btnSend.setEnabled(false);
                lbl.setText("You are a presenter now");
                lbl.setIcon(null);
                while (isPresenter) {
                    BufferedImage lbi = lmodel.CaptureScreen();
                    BufferedSerializable bsi = new BufferedSerializable(lbi);
                    try {
                        u.sendMessage("CAPTURE", bsi);
                        Thread.sleep(1000);
                    } catch (InterruptedException | IOException ex) {
                        Logger.getLogger(ScreencaptureClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    bsi.freeBufferedSerializable();
                }
            }
        });
        t.start();
    }//GEN-LAST:event_btnSendActionPerformed

    private void mniMakePresenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniMakePresenterActionPerformed
        if (!isPresenter) {
            return;
        }
        Object selectedName = lstOnlineUsers.getSelectedValue();
        if (selectedName.equals(u.name)) {
            return;
        }
        btnSend.setEnabled(false);
        try {
            u.sendMessage("PRESENTER", selectedName);
            isPresenter = false;
            lbl.setText("Waiting for Presenter to start Screen Share");
            lbl.setIcon(null);
        } catch (IOException ex) {
            //Logger.getLogger(ScreencaptureClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniMakePresenterActionPerformed

    private void lstOnlineUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstOnlineUsersMouseClicked

    }//GEN-LAST:event_lstOnlineUsersMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScreencaptureClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScreencaptureClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScreencaptureClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScreencaptureClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ScreencaptureClient().setVisible(true);
                } catch (AWTException ex) {
                    //Logger.getLogger(ScreencaptureClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Chat;
    private javax.swing.JButton LocalScreen;
    private javax.swing.JButton RemoteScreen;
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel lblwelcome;
    private javax.swing.JList lstOnlineUsers;
    private javax.swing.JMenuItem mniMakePresenter;
    private javax.swing.JPopupMenu pmPresenter;
    // End of variables declaration//GEN-END:variables

    public class ClientThread extends Thread {

        User u;

        ClientThread(User u) throws IOException {
            this.u = u;
            u.sendMessage("LOGIN", u.name);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String msg = (String) u.in.readObject();

                    if (msg.equals("LOGIN")) {
                        String username = (String) u.in.readObject();
                        lstModel.addElement(username);
                    } else if (msg.equals("LOGOUT")) {
                        String username = (String) u.in.readObject();
                        lstModel.removeElement(username);
                    } else if (msg.contains("CAPTURE")) {
                        if (lbl.getText().trim().length() != 0) {
                            lbl.setText("");
                        }
                        BufferedSerializable bs = (BufferedSerializable) u.in.readObject();
                        BufferedImage bi = bs.getBufferedImage();
                        BufferedImage rbi = ImageResizer.getResizedImage(lbl.getWidth(), lbl.getHeight(), bi);
                        lbl.setIcon(new ImageIcon(rbi));
                        bs.freeBufferedSerializable();
                    } else if (msg.equals("PRESENTER")) {
                        String username = (String) u.in.readObject();
                        isPresenter = true;
                        btnSend.setEnabled(true);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    //Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    lstModel.clear();
                }
            }
        }
    }
}
