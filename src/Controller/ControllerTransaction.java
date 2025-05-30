/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Transaction.ModelTabelTransaction;
import Model.Transaction.ModelTransaction;
import Model.Transaction.TransactionDAO;
import View.Transaction.ViewTransaction;
import View.Transaction.EditTransaction;
import View.Transaction.InputTransaction;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author HP
 */
public class ControllerTransaction {
    int userId;
    ViewTransaction halamanTable;
    EditTransaction halamanEdit;
    InputTransaction halamanInput;
    
    TransactionDAO daoTransaction;
    List<ModelTransaction> daftarTransaksi;
    
    public ControllerTransaction (ViewTransaction halamanTable, int userId){
        this.userId = userId;
        this.halamanTable = halamanTable;
        daoTransaction = new TransactionDAO();
    }
    
    public ControllerTransaction(InputTransaction halamanInput){
        this.halamanInput = halamanInput;
        daoTransaction = new TransactionDAO();
    }
    
    public ControllerTransaction(EditTransaction halamanEdit){
        this.halamanEdit = halamanEdit;
        daoTransaction = new TransactionDAO();
    }
    
    public void showAllTransaction(int userId) {
        this.userId = userId;
        daftarTransaksi = daoTransaction.getAll(userId);

        ModelTabelTransaction table = new ModelTabelTransaction(daftarTransaksi);

        halamanTable.getTableTransaction().setModel(table);
    }
    
    public void showSummary(int userId) {
        List<ModelTransaction> list = daoTransaction.getAll(userId);
        int pemasukkan = 0;
        int pengeluaran = 0;

        for (ModelTransaction t : list) {
            if (t.getCategory().equalsIgnoreCase("Pemasukkan")) {
                pemasukkan += t.getAmount();
            } else if (t.getCategory().equalsIgnoreCase("Pengeluaran")) {
                pengeluaran += t.getAmount();
            }
        }

        int sisa = pemasukkan - pengeluaran;
           
        halamanTable.setSummary(pemasukkan, pengeluaran, sisa);
    }

    public void insertTransaksi(int userId) {
        try {
            ModelTransaction transaksiBaru = new ModelTransaction();
            
            /*
              Mengambil input nama dan nim menggunakan getter yang telah dibuat di view
              Nilai dari input kemudian disimpan ke dalam variabel "nama" dan "nim".
            */
            String category = halamanInput.getInputCategory();
            Date date = halamanInput.getInputDate();
            String description = halamanInput.getInputDescription();
            int amount = (int) halamanInput.getInputAmount();

            if ("".equals(category) || date == null || "".equals(description) || amount <= 0) {
                throw new Exception("Data tidak boleh kosong!");
            }
                        
            
            transaksiBaru.setCategory(category);
            transaksiBaru.setDate(date);
            transaksiBaru.setDescription(description);
            transaksiBaru.setAmount(amount);
            
            // Memasukkan "mahasiswa baru" ke dalam database.
            daoTransaction.insert(transaksiBaru, userId);
            
            // Menampilkan pop-up ketika berhasil mengedit data
            JOptionPane.showMessageDialog(null, "Transaksi baru berhasil ditambahkan.");
            
            // Terakhir, program akan pindah ke halaman Table Mahasiswa()
            halamanInput.dispose();
            new ViewTransaction(userId).setVisible(true);
        } catch (Exception e) {
            // Menampilkan pop-up ketika terjadi error
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editTransaksi(int id, int userId) {
        this.userId = userId;
        
        try {
            ModelTransaction transaksiDiEdit = new ModelTransaction();
                        
            String category = halamanEdit.getInputCategory();
            Date date = halamanEdit.getInputDate();
            String description = halamanEdit.getInputDescription();
            int amount = halamanEdit.getInputAmount();

            if ("".equals(category) || date == null || "".equals(description) || amount <= 0) {
                throw new Exception("Data tidak boleh kosong!");
            }
            
            transaksiDiEdit.setId(id);
            transaksiDiEdit.setCategory(category);
            transaksiDiEdit.setDate(date);
            transaksiDiEdit.setDescription(description);
            transaksiDiEdit.setAmount(amount);
            
            daoTransaction.update(transaksiDiEdit);

            JOptionPane.showMessageDialog(null, "Data Transaksi berhasil diubah.");

            halamanEdit.dispose();
            new ViewTransaction(userId).setVisible(true);
        } catch (Exception e) {
            // Menampilkan pop-up ketika terjadi error
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteTransaksi(Integer baris) {
        // Mengambil id dan nama berdasarkan baris yang dipilih
        ModelTabelTransaction model = (ModelTabelTransaction) halamanTable.getTableTransaction().getModel();
        Integer id = model.getTransactionId(baris);
        
        String nama = halamanTable.getTableTransaction().getValueAt(baris, 3).toString();

        // Membuat Pop-Up untuk mengonfirmasi apakah ingin menghapus data
        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus transaksi?" ,
                "Hapus Transaksi",
                JOptionPane.YES_NO_OPTION
        );

        // Jika user memilih opsi "yes", maka hapus data.
        if (input == 0) {

            daoTransaction.delete(id);
            
            // Menampilkan pop-up jika berhasil menghapus.
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            // Memanggil method "showAllMahasiswa()" untuk merefresh table.
            showAllTransaction(userId);
            showSummary(userId);
        }
    }
}
