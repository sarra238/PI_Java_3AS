/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyConnection;
import Entities.Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author maryem
 */
public class ServiceCommande 
{
       private final Connection con=MyConnection.getInstance().getConnection();

public ServiceCommande() {}
       private Commande c;

public void insertCommande(Commande c)
      {    
           PreparedStatement st;
            String req="INSERT INTO Commande (id,idProduits) VALUES (?,?)";
        try{
            st= con.prepareStatement(req);
            st.setInt(1,c.getId());
            st.setInt(2,c.getIdProduits());
            st.executeUpdate();
            System.out.println("ajout avec succées");
        } 
        catch (SQLException ex) {
            System.out.println("ERREUR");
        }
        
    }

  public ObservableList<Commande> afficher()  throws SQLException 
{
  
    ObservableList<Commande> ls = FXCollections.observableArrayList();
    String sql="SELECT * FROM commande";
    Statement ste=con.createStatement();
    ResultSet rs=ste.executeQuery(sql);
    while(rs.next())
    {
    c=new Commande (rs.getInt(1),rs.getInt(2));
    ls.add(c);
    }
    return ls;
}
  
      
    public void supprimer(Commande c){
        String req="DELETE FROM `Commande` WHERE id=?";
        try {
            PreparedStatement ste= con.prepareStatement(req);
            ste.setInt(1, c.getId());
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     
  
}
