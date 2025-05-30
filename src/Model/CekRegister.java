package Model;

import javax.swing.JOptionPane;

public class CekRegister {
    private UserDAO userDao;
    
    public CekRegister() {
        this.userDao = new UserDAO();
    }
    
    public boolean processRegistration(String username, String password) {
        // Validasi input kosong
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Semua field harus diisi!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
       
        // Validasi panjang password
        if (password.length() < 3) {
            JOptionPane.showMessageDialog(null, 
                "Password harus minimal 3 karakter!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Cek ketersediaan username
        if (userDao.isUsernameExists(username)) {
            JOptionPane.showMessageDialog(null, 
                "Username sudah digunakan!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Simpan ke database (hanya username dan password)
        boolean success = userDao.registerUser(username, password);
        
        if (success) {
            JOptionPane.showMessageDialog(null, 
                "Registrasi berhasil! Silakan login.", 
                "Sukses", 
                JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, 
                "Gagal melakukan registrasi.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}