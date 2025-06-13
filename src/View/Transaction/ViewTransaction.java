/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Transaction;

import Controller.ControllerTransaction;
import Model.Transaction.ModelTabelTransaction;
import Model.Transaction.ModelTransaction;
import View.Transaction.InputTransaction;
import View.ViewLogin;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
/**
 *
 * @author HP
 */
public class ViewTransaction extends javax.swing.JFrame {
    /**
     * Creates new form DeleteTransaction
     */
    
    public Integer baris = null;
    private int userId;
    ControllerTransaction controller;
    
    public ViewTransaction(int userId) {
        initComponents();    
        setSize(700,600);
        setLocationRelativeTo(null);
        
        labelWelcome.setText("Selamat Datang ");
        
        this.userId = userId; // simpen nilai user id
       
        controller = new ControllerTransaction(this, userId);
        controller.showAllTransaction(userId);        
        controller.showSummary(userId);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                baris = table.getSelectedRow();
            }
        });        
        
        // Memberikan event handling ketika tombol "Tambah Mahasiswa" diklik
        buttonTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ketika tombol tambah diklik, maka program akan berpindah ke halaman InputData()
                dispose();
                new InputTransaction(userId).setVisible(true);
            }
        });
        
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {    
                    
                    // ambilrow
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null, "Pilih baris transaksi yang ingin diedit.");
                        return; // Keluar dari metode jika tidak ada baris yang dipilih
                    }
                    
                    ModelTabelTransaction model = (ModelTabelTransaction) table.getModel();
                    int transactionId = model.getTransactionId(selectedRow);
                    ModelTransaction transaksiTerpilih = new ModelTransaction();                   

                    // Mengambil id dan nama berdasarkan baris yang dipilih
                    Date date = (Date) table.getValueAt(baris, 0);
                    String category = table.getValueAt(baris, 1).toString();
                    int amount = (int) table.getValueAt(baris, 2);
                    String description = table.getValueAt(baris, 3).toString();                    
                        
                    transaksiTerpilih.setDate(date);
                    transaksiTerpilih.setCategory(category);
                    transaksiTerpilih.setDescription(description);
                    transaksiTerpilih.setAmount(amount);

                    dispose();
                    new EditTransaction(transaksiTerpilih, transactionId, userId).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });
        
        buttonDelete.addActionListener(e -> {
            if (baris != null) {                
                controller.deleteTransaksi(baris);
                baris = null;
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris data yang ingin dihapus.");
            }
        });
        
        buttonLogOut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewTransaction.this.userId = 0;                
                dispose();
                
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        ViewLogin newLoginView = new ViewLogin(); // buat viewlogin baru biar datanya kereset
                        newLoginView.setVisible(true);
                        new Controller.LoginController(newLoginView); 
                    }
                });
            }
            
        });
        
        buttonEksport.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.exportTransactionData();
            }
        });
        
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
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        buttonTambah = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        totalPemasukkan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        totalPengeluaran = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        sisaUang = new javax.swing.JLabel();
        buttonLogOut = new javax.swing.JButton();
        labelWelcome = new javax.swing.JLabel();
        buttonEksport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 221, 226));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tanggal", "Kategori", "Deskripsi", "Jumlah"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setSelectionBackground(new java.awt.Color(187, 78, 105));
        table.setSelectionForeground(new java.awt.Color(255, 221, 226));
        scrollPane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
        }

        buttonTambah.setBackground(new java.awt.Color(187, 78, 105));
        buttonTambah.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        buttonTambah.setForeground(new java.awt.Color(255, 221, 226));
        buttonTambah.setText("Tambah");
        buttonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTambahActionPerformed(evt);
            }
        });

        buttonEdit.setBackground(new java.awt.Color(187, 78, 105));
        buttonEdit.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        buttonEdit.setForeground(new java.awt.Color(255, 221, 226));
        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonDelete.setBackground(new java.awt.Color(187, 78, 105));
        buttonDelete.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        buttonDelete.setForeground(new java.awt.Color(255, 221, 226));
        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });

        jLabel1.setText("Pemasukkan");

        totalPemasukkan.setText("Rp 0"
        );

        jLabel2.setText("Pengeluaran");

        totalPengeluaran.setText("Rp 0");

        jLabel3.setText("Sisa Uang");

        sisaUang.setText("Rp 0");

        buttonLogOut.setBackground(new java.awt.Color(187, 78, 105));
        buttonLogOut.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        buttonLogOut.setForeground(new java.awt.Color(255, 221, 226));
        buttonLogOut.setText("Log Out");

        labelWelcome.setText("Selamat Datang,");

        buttonEksport.setBackground(new java.awt.Color(187, 78, 105));
        buttonEksport.setText("📩 Eksport");
        buttonEksport.setToolTipText("");
        buttonEksport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEksportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(labelWelcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonLogOut)
                .addGap(33, 33, 33))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonEksport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(92, 92, 92)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(totalPemasukkan)
                        .addComponent(jLabel1))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel2)
                        .addComponent(totalPengeluaran))
                    .addGap(98, 98, 98)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(sisaUang)
                        .addComponent(jLabel3))
                    .addGap(112, 112, 112)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelWelcome)
                    .addComponent(buttonLogOut))
                .addGap(96, 96, 96)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEdit)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEksport))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(83, 83, 83)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(totalPemasukkan)
                        .addComponent(totalPengeluaran)
                        .addComponent(sisaUang))
                    .addContainerGap(418, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonTambahActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonEksportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEksportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEksportActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public void setSummary(int pemasukkan, int pengeluaran, int sisa) {
        System.out.println(pemasukkan + " --- " + pengeluaran + " --- " + sisa);
        NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));
        totalPemasukkan.setText("Rp " + nf.format(pemasukkan));
        totalPengeluaran.setText("Rp " + nf.format(pengeluaran));
        sisaUang.setText("Rp " + nf.format(sisa));
    }
    
    public JTable getTableTransaction() {
        return table;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton buttonDelete;
    public javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonEksport;
    public javax.swing.JButton buttonLogOut;
    public javax.swing.JButton buttonTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelWelcome;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel sisaUang;
    public javax.swing.JTable table;
    private javax.swing.JLabel totalPemasukkan;
    private javax.swing.JLabel totalPengeluaran;
    // End of variables declaration//GEN-END:variables
}
