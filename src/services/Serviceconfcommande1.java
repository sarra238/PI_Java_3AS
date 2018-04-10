/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyConnection;
import static utils.util.pr;
import static utils.util.user;
import Entities.confcommande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;




/**
 *
 * @author maryem
 */
public class Serviceconfcommande1 {
    
   Connection con;
    private Statement ste;  
     
    private static Serviceconfcommande1 instance;
     
     
     
     
      public Serviceconfcommande1()
      {
        this.con = MyConnection.getInstance().getConnection();
      }
    


    public static Serviceconfcommande1 getInstance() {
        if (instance == null) {
            instance = new Serviceconfcommande1();
        }
        return instance;
    }

 
    
    
    
    
    
    
    
      public void ajouterC(confcommande g) throws SQLException {


        String req = "INSERT INTO `confcommande` ( `nom`, `prenom`,`adresse`,`idProduit`,`iduser`)" + " VALUES (?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setString(1, g.getNom());
        pre.setString(2, g.getPrenom());
        pre.setString(3, g.getAdresse());
        pre.setInt(4, pr.getId());
        pre.setInt(5, user.getId());
        pre.executeUpdate();
        System.out.println("ajout meryem mmm avec succées");

    }
    
    
    
    
    
 //*************************************************   
    
    public void ajouterCommande(confcommande c) throws SQLException  {
//    ste.executeUpdate(req);//executeupdate-->insert,delete,update

        String req = "INSERT INTO `confcommande` ( `nom`, `prenom`,`adresse`,`idProduit`,`idUser`)" + " VALUES (?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setString(1, c.getNom());
        pre.setString(2, c.getPrenom());
        pre.setString(3, c.getAdresse());
        pre.setInt(4, c.getProduit().getId());
        pre.setInt(5, 55);
 //      System.out.println(c.getUser().getId());
    }
 





//
  public ObservableList<confcommande> afficherconfcommande()  throws SQLException
  {
  ObservableList<confcommande> ls = FXCollections.observableArrayList();
   String sql="SELECT * FROM confcommande ";
    //String sql="SELECT NomProduit,Region,Categorie,Prix FROM produit where etat=1";
   
    Statement ste=con.createStatement();
    ResultSet rs=ste.executeQuery(sql);
    while(rs.next())
    {                 confcommande p = new confcommande();
   
      p.setNom(rs.getString(1));
        p.setPrenom(rs.getString(2));
        p.setAdresse(rs.getString(3));
               
        int nomProduit = rs.getInt(4);
        //  int idUser=rs.getInt(5);
                       ServiceProduit daoProduit = ServiceProduit.getInstance();
                 UserService Use = UserService.getInstance();
               
              //  prod =daoUser.findProduitById(id);
                
                p.setProduit(daoProduit.findProduitById(nomProduit));
               p.setUser(user);
// confcommande   p=new confcommande(rs.getString(1),rs.getString(2),rs.getString(3));
 //produit= p.getProduit();
    ls.add(p); 
    }
    return ls;
    
    
}


//public ObservableList<confcommande> afficherconfcommandeUser(int idUser)  throws SQLException
//  {
//  ObservableList<confcommande> ls = FXCollections.observableArrayList();
//   String sql="SELECT * FROM confcommande where idUser=" + idUser ;
//    //String sql="SELECT NomProduit,Region,Categorie,Prix FROM produit where etat=1";
//   
//    Statement ste=con.createStatement();
//    ResultSet rs=ste.executeQuery(sql);
//    while(rs.next())
//    {                 confcommande p = new confcommande();
//   
//      p.setNom(rs.getString(1));
//        p.setPrenom(rs.getString(2));
//        p.setAdresse(rs.getString(3));
//               
//                int idProduit = rs.getInt(4);
//                ServiceProduit daoUser = ServiceProduit.getInstance();
//                Produit prod=new Produit();
//                prod =daoUser.findProduitById(idProduit);
//                
//                p.setProduit(daoUser.findProduitById(idProduit));
//               
//// confcommande   p=new confcommande(rs.getString(1),rs.getString(2),rs.getString(3));
// //produit= p.getProduit();
//    ls.add(p); 
//    }
//    return ls;
//    
    





public void supprimercommande(String nom) throws SQLException {

        String req = "delete from  confcommande where nom= ?";

        PreparedStatement pre = con.prepareStatement(req);//3adelou el requst te3a eeli heya delete 
      pre.setString(1, nom);

        pre.executeUpdate();// taamel delete 3al base de donné 
        System.out.println("commande  supprimer");

    }
}





