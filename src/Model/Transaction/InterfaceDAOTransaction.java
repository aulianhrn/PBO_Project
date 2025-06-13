/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.Transaction;

import java.util.List;

/**
 *
 * @author Lenovo
 */
interface InterfaceDAOTransaction {
    public void insert(ModelTransaction transaction, int userId);
    
    public void update(ModelTransaction transaction);
    
    public void delete (int id);
    
    public List<ModelTransaction> getAll(int userId);
    
}
