/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Transaction;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author HP
 */
public class ModelTabelTransaction extends AbstractTableModel {
    List<ModelTransaction> daftarTransaksi;
    
    String kolom[] = {"Tanggal", "Kategori", "Jumlah", "Deskripsi"};
    
    public ModelTabelTransaction(List<ModelTransaction> daftarTransaksi) {
        this.daftarTransaksi = daftarTransaksi;
    }

    // Method untuk mengambil jumlah baris dari tabel
    @Override
    public int getRowCount() {
        return daftarTransaksi.size();
    }

    // Method untuk mengambil jumlah kolom dari tabel
    @Override
    public int getColumnCount() {
        return kolom.length;
    }       

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return daftarTransaksi.get(rowIndex).getDate();
            case 1:
                return daftarTransaksi.get(rowIndex).getCategory();
            case 2:
//                NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));
//                return nf.format(daftarTransaksi.get(rowIndex).getAmount());
                return daftarTransaksi.get(rowIndex).getAmount();
            case 3:
                return daftarTransaksi.get(rowIndex).getDescription();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return kolom[columnIndex];
    }    
    
    public int getTransactionId(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < daftarTransaksi.size()) {
            return daftarTransaksi.get(rowIndex).getId();
        }
        return -1; // Return -1 jika indeks tidak valid
    }
    
}
