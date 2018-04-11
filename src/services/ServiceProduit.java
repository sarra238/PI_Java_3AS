/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.Produit;
import static services.UserService.conn;
import utils.MyConnection;
/**
 *
 * @author dell
 */
public class ServiceProduit {
    
       static MyConnection ds = MyConnection.getInstance();

    public static void insererProduit(Produit o) {
        try {
            String req = "Insert into produit (NomProduit,Region,Categorie,Stock,Prix,Description,nomImage,longitude,attitude,etat,idUser,DateLancement) values(?,?,?,?,?,?,?,?,?,?,?,?)";
// id_objet 	id_user 	nom_objet 	categorie 	img 	lieu 	description 	type 
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, o.getNomProduit());
            ste.setString(2, o.getRegion());
            ste.setString(3, o.getCategorie());
            ste.setInt(4, o.getStock());
            ste.setDouble(5, o.getPrix());
            ste.setString(6, o.getDescription());
            ste.setString(7, o.getNomImage());
            ste.setDouble(8, o.getLongitude());
            ste.setDouble(9, o.getAttitude());
            ste.setInt(10,0);
            ste.setInt(11,conn);
            ste.setString(12,o.getDateLancement());
            ste.executeUpdate();
        } catch (SQLException ex) {
            
            System.out.println(ex);
        }
    }
    
    public static void updateProduit(Produit o) {
        try {
            String req = "UPDATE Produit SET nomproduit=?,region=?, categorie=?,stock =?,prix=?,description=?,datelancement=?,nomImage=?,longitude=?,etat=?,attitude=? where id='"+o.getId()+"'and idUser='"+conn+"'";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, o.getNomProduit());
            ste.setString(2, o.getRegion());
            ste.setString(3, o.getCategorie());
            ste.setInt(4, o.getStock());
            ste.setDouble(5, o.getPrix());
            ste.setString(6, o.getDescription());
            ste.setString(7, o.getDateLancement());
            ste.setString(8, o.getNomImage());
            ste.setDouble(9, o.getLongitude());
            ste.setInt(10, 1);
            ste.setDouble(11, o.getAttitude());

            ste.executeUpdate();
        } catch (SQLException ex) {
             System.out.println(ex);
        }
    }
    
public static void deleteProduit(int id) {
        try {
            String req = "delete from Produit where id=?";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public static void deleteProduit2(int id) {
        try {
            String req = "delete from Produit where id=? and idUser='"+conn+"'";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 public static Produit selectProduit(int id) {
        Produit o = new Produit();
        try {
            String req = "select * from Produit where id=? and etat=0";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, id);

            ResultSet resultat = ste.executeQuery();
            resultat.last();
            o = new Produit(resultat.getInt("id"),
                    resultat.getString("nomProduit"),
                    resultat.getString("region"),
                    resultat.getString("categorie"),
                    resultat.getInt("stock"),
                    resultat.getDouble("prix"),
                    resultat.getString("description"),
                    resultat.getString("dateLancement"),
                    resultat.getString("nomImage"),
                    resultat.getDouble("longitude"), 
                    resultat.getInt("etat"),
                    resultat.getDouble("attitude"));

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return o;
    }
   public static List<Produit> selectAllProduit() {
        List<Produit> list = new ArrayList<>();
        try {
            String req = "select * from Produit";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
       

            ResultSet resultat = ste.executeQuery();
            while (resultat.next()) {

                list.add(new Produit(resultat.getInt("id"),
                    resultat.getString("nomProduit"),
                    resultat.getString("region"),
                    resultat.getString("categorie"),
                    resultat.getInt("stock"),
                    resultat.getDouble("prix"),
                    resultat.getString("description"),
                    resultat.getString("dateLancement"),
                    resultat.getString("nomImage"),
                    resultat.getDouble("longitude"), 
                    resultat.getInt("etat"),
                    resultat.getFloat("attitude")));
            }
        } catch (SQLException ex) {
            
             System.out.println(ex);
        }
        return list;
    }
   
    public static List<Produit> selectAllProduitE (int e){
        List<Produit> list = new ArrayList<>();
        try {
            String req = "select * from Produit where etat=?";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(1, e);
       

            ResultSet resultat = ste.executeQuery();
            while (resultat.next()) {

                list.add(new Produit(resultat.getInt("id"),
                    resultat.getString("nomProduit"),
                    resultat.getString("region"),
                    resultat.getString("categorie"),
                    resultat.getInt("stock"),
                    resultat.getDouble("prix"),
                    resultat.getString("description"),
                    resultat.getString("dateLancement"),
                    resultat.getString("nomImage"),
                    resultat.getDouble("longitude"), 
                    resultat.getInt("etat"),
                    resultat.getDouble("attitude")));
            }
        } catch (SQLException ex) {
            
             System.out.println(ex);
        }
        return list;
    }
    
    
     public static List<Produit> selectLesProduitE ( ){
        List<Produit> list = new ArrayList<>();
        try {
            String req = "select * from Produit ";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            
           


            ResultSet resultat = ste.executeQuery();
            while (resultat.next()) {

                list.add(new Produit(resultat.getInt("id"),
                    resultat.getString("nomProduit"),
                    resultat.getString("region"),
                    resultat.getString("categorie"),
                    resultat.getInt("stock"),
                    resultat.getDouble("prix"),
                    resultat.getString("description"),
                    resultat.getString("dateLancement"),
                    resultat.getString("nomImage"),
                    resultat.getDouble("longitude"), 
                    resultat.getInt("etat"),
                    resultat.getDouble("attitude")));
            }
        } catch (SQLException ex) {
            
             System.out.println(ex);
        }
        return list;
    }
    
          public static List<Produit> searchcategdate(int e , int id , String c ,Date d){
        List<Produit> list = new ArrayList<>();
        try {
            String req = "select * from Produit where  categorie=? and dateLancement=? ";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            
            ste.setString(3, c);
            ste.setDate(4, d);


            ResultSet resultat = ste.executeQuery();
            while (resultat.next()) {

                list.add(new Produit(resultat.getInt("id"),
                    resultat.getString("nomProduit"),
                    resultat.getString("region"),
                    resultat.getString("categorie"),
                    resultat.getInt("stock"),
                    resultat.getDouble("prix"),
                    resultat.getString("description"),
                    resultat.getString("dateLancement"),
                    resultat.getString("nomImage"),
                    resultat.getDouble("longitude"), 
                    resultat.getInt("etat"),
                    resultat.getDouble("attitude")));
            }
        } catch (SQLException ex) {
            
             System.out.println(ex);
        }
        return list;
    }
          
                public static List<Produit> searchdate( Date c ){
        List<Produit> list = new ArrayList<>();
        try {
            String req = "select * from Produit where  dateLancement=?  ";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
          
            ste.setDate(3, c);


            ResultSet resultat = ste.executeQuery();
            while (resultat.next()) {

                list.add(new Produit(resultat.getInt("id"),
                    resultat.getString("nomProduit"),
                    resultat.getString("region"),
                    resultat.getString("categorie"),
                    resultat.getInt("stock"),
                    resultat.getDouble("prix"),
                    resultat.getString("description"),
                    resultat.getString("dateLancement"),
                    resultat.getString("nomImage"),
                    resultat.getDouble("longitude"), 
                    resultat.getInt("etat"),
                    resultat.getDouble("attitude")));
            }
        } catch (SQLException ex) {
            
             System.out.println(ex);
        }
        return list;
    }
                
                      public static List<Produit> searchcateg( String c ){
        List<Produit> list = new ArrayList<>();
        try {
            String req = "select * from Produit where etat=? and idUser!=? and  categorie=?  ";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
          
            ste.setString(3, c);


            ResultSet resultat = ste.executeQuery();
            while (resultat.next()) {

                list.add(new Produit(resultat.getInt("id"),
                    resultat.getString("nomProduit"),
                    resultat.getString("region"),
                    resultat.getString("categorie"),
                    resultat.getInt("stock"),
                    resultat.getDouble("prix"),
                    resultat.getString("description"),
                    resultat.getString("dateLancement"),
                    resultat.getString("nomImage"),
                    resultat.getDouble("longitude"), 
                    resultat.getInt("etat"),
                    resultat.getDouble("attitude")));
            }
        } catch (SQLException ex) {
            
             System.out.println(ex);
        }
        return list;
    }
                      
                      
     public static List<Produit> selectMesProduitE (int e , int id ){
        List<Produit> list = new ArrayList<>();
        try {
            String req = "select * from Produit where etat=? and idUser=?";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setInt(0, e);
            ste.setInt(2, id);


            ResultSet resultat = ste.executeQuery();
            while (resultat.next()) {

                list.add(new Produit(resultat.getInt("id"),
                    resultat.getString("nomProduit"),
                    resultat.getString("region"),
                    resultat.getString("categorie"),
                    resultat.getInt("stock"),
                    resultat.getDouble("prix"),
                    resultat.getString("description"),
                    resultat.getString("dateLancement"),
                    resultat.getString("nomImage"),
                    resultat.getDouble("longitude"), 
                    resultat.getInt("etat"),
                    resultat.getDouble("attitude")));
            }
        } catch (SQLException ex) {
            
             System.out.println(ex);
        }
        return list;
    }
     
     public static List<Produit> searchcateg1( String c ){
        List<Produit> list = new ArrayList<>();
        try {
            String req = "select * from Produit where etat=? and idUser=? and  categorie=?  ";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
      
            ste.setString(3, c);


            ResultSet resultat = ste.executeQuery();
            while (resultat.next()) {

                list.add(new Produit(resultat.getInt("id"),
                    resultat.getString("nomProduit"),
                    resultat.getString("region"),
                    resultat.getString("categorie"),
                    resultat.getInt("stock"),
                    resultat.getDouble("prix"),
                    resultat.getString("description"),
                    resultat.getString("dateLancement"),
                    resultat.getString("nomImage"),
                    resultat.getDouble("longitude"), 
                    resultat.getInt("etat"),
                    resultat.getDouble("attitude")));
            }
        } catch (SQLException ex) {
            
             System.out.println(ex);
        }
        return list;
    }
     
     public static List<Produit> searchcategdate1( String c ,Date d){
        List<Produit> list = new ArrayList<>();
        try {
            String req = "select * from Produit where etat=? and idUser=? and  categorie=? and dateLancement=? ";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
         
            ste.setString(3, c);
            ste.setDate(4, d);


            ResultSet resultat = ste.executeQuery();
            while (resultat.next()) {

                list.add(new Produit(resultat.getInt("id"),
                    resultat.getString("nomProduit"),
                    resultat.getString("region"),
                    resultat.getString("categorie"),
                    resultat.getInt("stock"),
                    resultat.getDouble("prix"),
                    resultat.getString("description"),
                    resultat.getString("dateLancement"),
                    resultat.getString("nomImage"),
                    resultat.getDouble("longitude"), 
                    resultat.getInt("etat"),
                    resultat.getDouble("attitude")));
            }
        } catch (SQLException ex) {
            
             System.out.println(ex);
        }
        return list;
    }
     
     
     public static List<Produit> searchdate1( Date c ){
        List<Produit> list = new ArrayList<>();
        try {
            String req = "select * from Produit where  and  dateLancement=?  ";

            PreparedStatement ste = ds.getConnection().prepareStatement(req);
        
            ste.setDate(3, c);


            ResultSet resultat = ste.executeQuery();
            while (resultat.next()) {

                list.add(new Produit(resultat.getInt("id"),
                    resultat.getString("nomProduit"),
                    resultat.getString("region"),
                    resultat.getString("categorie"),
                    resultat.getInt("stock"),
                    resultat.getDouble("prix"),
                    resultat.getString("description"),
                    resultat.getString("dateLancement"),
                    resultat.getString("nomImage"),
                    resultat.getDouble("longitude"), 
                    resultat.getInt("etat"),
                    resultat.getDouble("attitude")));
            }
        } catch (SQLException ex) {
            
             System.out.println(ex);
        }
        return list;
    }
     //******************************************************
}

 
