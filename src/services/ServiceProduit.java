/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyConnection;
import Entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmmm
 */
public class ServiceProduit {
    
     Connection con=MyConnection.getInstance().getConnection();
     private Statement ste;     
     private Produit p;
     private static ServiceProduit instance;

public ServiceProduit() {
    }

    
   
    public static ServiceProduit getInstance()
    {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance; 
    }
    
public ObservableList<Produit> afficher()  throws SQLException
  {
  ObservableList<Produit> ls = FXCollections.observableArrayList();
   
    String sql="SELECT * FROM produit";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery(sql);
    while(rs.next())
    {
    p=new Produit(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getString(7),rs.getDouble(10),rs.getDouble(12));
    ls.add(p); 
    }
    return ls;
    
    
}
//*****************************************************************
public void approuver(double longitude) {

        try {
            String req = ("update produit set Etat=1 where longitude="+longitude);
            PreparedStatement pre = con.prepareStatement(req);
            pre.executeUpdate();
            System.out.println("ajout ");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
////************************************************************************
public void approuverdelate(double Prix) 
{

        try {
            String req = ("update produit set Etat=0 where Prix="+ Prix);
            PreparedStatement pre = con.prepareStatement(req);
            pre.executeUpdate();
            System.out.println("supmeryem ");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
////************************************************************************
//public void approuverid(String NomProduit) 
//{
//
//        try {
//            String req = ("update produit set Etat=0 where NomProduit="+ NomProduit);
//            PreparedStatement pre = con.prepareStatement(req);
//            pre.executeUpdate();
//            System.out.println("supmeryem ");
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
////***********************************************************************

 public ObservableList<Produit> afficherCommande()  throws SQLException
  {
      
  ObservableList<Produit> ls = FXCollections.observableArrayList();
   
    String sql="SELECT * FROM produit where etat=1";
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery(sql);
    while(rs.next())
    {
    p=new Produit(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getString(7),rs.getDouble(10),rs.getDouble(12));
    ls.add(p); 
    }
    return ls;
    
    
}
//********************************************************************

 
  public ObservableList<Produit> afficherproduitcommande()  throws SQLException
  {
  ObservableList<Produit> ls = FXCollections.observableArrayList();
   String sql="SELECT * FROM produit where etat=1";
    
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery(sql);
    while(rs.next())
    {
  p=new Produit(rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(6));
    ls.add(p); 
    }
    return ls;
   
}
 public Produit findProduitById(int id) throws SQLException
    {
        Produit owner = new Produit();
        int count = 0;
           
        String requete="select * from Produit where id="+id;
        try{
            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(requete);
            while(rsl.next())
            {
              //  owner.set(rsl.getInt(1));
                owner.setNomProduit(rsl.getString(2));
                owner.setRegion(rsl.getString(3));
                owner.setCategorie(rsl.getString(4));
//                owner.setDate_naissance(rsl.getDate(5));
                owner.setStock(rsl.getInt(5));
            //    owner.setPrix(rsl.getDouble(5));
//            owner.setNum_tel(rsl.getInt(8));
//            owner.setLogin(rsl.getString(9));
//            owner.setPassword(rsl.getString(10));
             
                count++;
            }
           if(count == 0){
                return null;
           }else{
               return owner;
           }  
        }
        catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   }
    public ObservableList<Produit> search_nom(String nomProduit) {
        ObservableList<Produit> ls = FXCollections.observableArrayList();
        try {
            String requete = "select * from Produit where nomProduit LIKE '%" + nomProduit + "%'";
          // String requete = "select * from patisserie where nom_patisserie LIKE '%" + nom_patisserie + "%' && activite LIKE '%" + activite + "%' && adresse_patisserie LIKE '%" + adresse_patisserie + "%'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);

           
                while(rs.next())
    {
    p=new Produit(rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDouble(6),rs.getString(7),rs.getDouble(10),rs.getDouble(12));
    ls.add(p); 
    }
  

        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return ls;
    }
//*************************

public Produit findProduitByNom(String NomProduit) throws SQLException
    {
        Produit owner = new Produit();
        int count = 0;
           
        String requete="select * from Produit where NomProduit="+NomProduit;
        try{
            Statement st = con.createStatement();
            ResultSet rsl = st.executeQuery(requete);
            while(rsl.next())
            {
              //  owner.set(rsl.getInt(1));
                owner.setNomProduit(rsl.getString(2));
                owner.setRegion(rsl.getString(3));
                owner.setCategorie(rsl.getString(4));
//                owner.setDate_naissance(rsl.getDate(5));
                owner.setStock(rsl.getInt(5));
            //    owner.setPrix(rsl.getDouble(5));
//            owner.setNum_tel(rsl.getInt(8));
//            owner.setLogin(rsl.getString(9));
//            owner.setPassword(rsl.getString(10));
             
                count++;
            }
           if(count == 0){
                return null;
           }else{
               return owner;
           }  
        }
        catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   }











}
////public void ajouP(Produit u) 
////    {
////        try {
////            String req = "INSERT INTO `Produit`(`id`, `NomProduit`, `Region`, `Categorie`, `Stock`, `Prix`, `DateLancement`, `nomImage`, `longitude`, `etat`, `attitude`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
////            PreparedStatement ste;
////            ste= con.prepareStatement(req);
////              //         ste.setInt(1, u.getId());
////
////            ste.setInt(1, u.getId());
////           // String m =u.getPassword(); 
////      ///      String hashed = BCrypt.hashpw(m, BCrypt.gensalt());
////        ste.setString(2,u.getNomProduit() );
////            ste.setString(3, u.getRegion());
////            ste.setString(4, u.getCategorie());
////            ste.setInt(5, u.getStock());
////            ste.setDouble(6, u.getPrix());
////            ste.setString(7, u.getDateLancement());
////            ste.setString(8, u.getNomImage());
////            ste.setDouble(9, u.getLongitude());
////            ste.setInt(10, u.getEtat());
////          ste.setDouble(11, u.getAttitude());
////
////            ste.executeUpdate();
////        }
////        catch (SQLException ex) {
////            System.out.println("erreur lors de l'inscription " + ex.getMessage());
////        }
////    }
//}
