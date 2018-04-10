/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.ReserEv;
import Interfaces.IReserEv;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

public class ReserEvServices {
  public static String pass ;
      private final Connection c = MyConnection.getInstance().getConnection();
      
     public void AjouterRes(ReserEv e) {
        PreparedStatement st;
        String query="insert into reserv_event (nom,prenom,mail,tel,idUser,idEvenement,etat,identifiant) values(?,?,?,?,?,?,?,?)";
        try {
            st= c.prepareStatement(query);
           
            st.setString(1,e.getNom());
            st.setString(2,e.getPrenom());
            st.setString(3,e.getMail());
            st.setInt(4,e.getTel());
            st.setInt(5,e.getUserId());
            st.setInt(6,e.getIdEv());
            st.setInt(7,0);
            st.setString(8,"");
            st.executeUpdate();
            System.out.println("Ajout accompli avec succés");
           
        } 
         catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout du reservation " + ex.getMessage());
        }
    }
      
    public int Count (int idEv,int IdU){    
        int i =0; 
        try {
            PreparedStatement t ;
            String query= "Select * from reserv_event where (idEvenement ='"+idEv+"') AND (idUser ='"+IdU+"')";
            t = c.prepareStatement(query);
            ResultSet rs = t.executeQuery();
            while (rs.next()){
                i+=1;   
            }
        } catch(SQLException ex) {
            System.out.println("erreur lors de la mise à jour de l'evenement " + ex.getMessage());
        }  
        return i;
    }
    
    public ReserEv RechercherReserById(int idEv, int idU) {
        try {
            PreparedStatement pt;
            String query = "select nom ,prenom,mail,tel,etat from reserv_event where idEvenement='"+idEv+"' AND idUser='"+idU+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
        
            if (rs.first()) {
                ReserEv A=new ReserEv();
            
                A.setNom(rs.getString(1));
                 A.setPrenom(rs.getString(2));
                 A.setMail(rs.getString(3));
                  A.setTel(rs.getInt(4));
                    A.setTel(rs.getInt(5));
                return A;
            }
        } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
        return null;
    }
      
    public int RechercherId(int idEv, int idU) {
        try {
            PreparedStatement pt;
            String query = "select id from reserv_event where idEvenement='"+idEv+"' AND idUser='"+idU+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
        
            if (rs.first()) {
                ReserEv A=new ReserEv();
            A.setId(rs.getInt(1));
             
                return A.getId();
            }
        } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
        return 0;
    }
    
      
    public void ModifierRes(ReserEv a) {
        try {
            PreparedStatement pt;
            String query = "update reserv_event set nom=? ,prenom=?,mail=?,tel=? where id='"+a.getId()+"'";
            pt=c.prepareStatement(query);
           
            pt.setString(1,a.getNom());
             pt.setString(1,a.getPrenom());
             
              pt.setString(1,a.getMail()); 
              pt.setInt(1,a.getTel());
            pt.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour de l'evenement " + ex.getMessage());
        }     
    }
      
    public int CountRes(String id) {
        int i=0;
        try {
            PreparedStatement pt;
            String query = "select * fromreserv_event where type='"+id+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                i+=1;
            }
        }
         catch (SQLException ex) {
            System.out.println("Erreur " + ex.getMessage());
        }  
        return i;
    }
            public List<ReserEv> AfficherAllRes(int id) {
        List<ReserEv> Ann= new ArrayList<>();
        int e;
        String query="select nom, prenom,mail,tel,etat,id , identifiant,idUser from reserv_event where idEvenement='"+id+"' ";
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {
                ReserEv A=new ReserEv();
         
                A.setNom(rs.getString(1));
                A.setPrenom(rs.getString(2));
                A.setMail(rs.getString(3));
                A.setTel(rs.getInt(4));
                A.setEtat(rs.getInt(5));
                A.setId(rs.getInt(6));
                       A.setIdentifiant(rs.getString(7));
                        A.setUserId(rs.getInt(8));
          if(A.getEtat()==0){
                Ann.add(A);
          }
            }
            return Ann;
        } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de tous les evenements! " + ex.getMessage());
        }
        return null;
}  public List<ReserEv> AfficherAllRes() {
        List<ReserEv> Ann= new ArrayList<>();
        int e;
        String query="select nom, prenom,mail,tel,etat,id,identifiant,idUser from reserv_event  ";
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {
                ReserEv A=new ReserEv();
         
                A.setNom(rs.getString(1));
                A.setPrenom(rs.getString(2));
                A.setMail(rs.getString(3));
                A.setTel(rs.getInt(4));
                A.setEtat(rs.getInt(5));
                A.setId(rs.getInt(6));
                 A.setIdentifiant(rs.getString(7));
                    A.setUserId(rs.getInt(8));
          if(A.getEtat()==0){
                Ann.add(A);
          }
            }
            return Ann;
        } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de tous les evenements! " + ex.getMessage());
        } return null;}
    
        public List<ReserEv> AfficherAllReseC(int id) {
        List<ReserEv> Ann= new ArrayList<>();
        int e;
        String query="select nom, prenom,mail,tel,etat,id,identifiant , idUser from reserv_event where idEvenement='"+id+"'    ";
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {
                ReserEv A=new ReserEv();
         
                A.setNom(rs.getString(1));
                A.setPrenom(rs.getString(2));
                A.setMail(rs.getString(3));
                A.setTel(rs.getInt(4));
                A.setEtat(rs.getInt(5));
                A.setId(rs.getInt(6));
                 A.setIdentifiant(rs.getString(7));
                    A.setUserId(rs.getInt(8));
          if(A.getEtat()==1){
                Ann.add(A);
          }
            }
            return Ann;
        } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de tous les evenements! " + ex.getMessage());
        }
        return null;
}
            public String generate(int length)
{
	    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // Tu supprimes les lettres dont tu ne veux pas
	    String pass = "";
	    for(int x=0;x<length;x++)
	    {
	       int i = (int)Math.floor(Math.random() * 62); // Si tu supprimes des lettres tu diminues ce nb
	       pass += chars.charAt(i);
	    }
	    System.out.println(pass);
	    return pass;}
     
      public void ModifierEtat(ReserEv a) {
        try {
            PreparedStatement pt;
            String query = "update reserv_event set etat=? ,identifiant=? where id='"+a.getId()+"'";
            pt=c.prepareStatement(query);
            pt.setInt(1,1);
ReserEvServices   rs =new ReserEvServices();
        pass= rs.generate(8);
            pt.setString(2,pass);
            pt.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour del'etat de l'evenement " + ex.getMessage());
        }     
    }
      
 public void SupprimerREs(ReserEv a) {
         try {
            
            ReserEvServices A=new ReserEvServices();
            
            PreparedStatement st;
            String query = "delete from reserv_event where id='"+a.getId()+"'";
            st=c.prepareStatement(query);
            st.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
            
 }
  catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour del'etat de l'evenement " + ex.getMessage());
        } 
}

    public ReserEv RechercherReserByName(String nom) {
        try {
            PreparedStatement pt;
            String query = "select nom,prenom,mail,tel,etat from reserv_event where nom='"+nom+"' ";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
        
            if (rs.first()) {
                ReserEv A=new ReserEv();
            
                A.setNom(rs.getString(1));
                 A.setPrenom(rs.getString(2));
                 A.setMail(rs.getString(3));
                  A.setTel(rs.getInt(4));
                   A.setEtat(rs.getInt(5));
                return A;
            }
        } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
        return null;
    }
       public void SupprimerEvenement(ReserEv a) {
         try {
            ReserEv b;
            ReserEvServices A=new ReserEvServices();
           
            PreparedStatement st;
            String query = "delete from reserv_event where id='"+a.getId()+"'";
            st=c.prepareStatement(query);
            st.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
            
       
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la suppression de l'evenement " + ex.getMessage());
        }
    }
}
