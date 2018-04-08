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
import utils.MyConnection;

public class ReserEvServices implements IReserEv{
      private final Connection c = MyConnection.getInstance().getConnection();
      @Override
     public void AjouterRes(ReserEv e) {
        PreparedStatement st;
        String query="insert into reserv_event (nom,prenom,mail,tel,idUser,idEvenement) values(?,?,?,?,?,?)";
        try {
            st= c.prepareStatement(query);
           
            st.setString(1,e.getNom());
            st.setString(2,e.getPrenom());
            st.setString(3,e.getMail());
            st.setInt(4,e.getTel());
            st.setInt(5,e.getUserId());
            st.setInt(6,e.getIdEv());
            st.executeUpdate();
            System.out.println("Ajout accompli avec succés");
           
        } 
         catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout du reservation " + ex.getMessage());
        }
    }
      @Override
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
      @Override
    public ReserEv RechercherParById(int idEv, int idU) {
        try {
            PreparedStatement pt;
            String query = "select type from reserv_event where idEvenement='"+idEv+"' AND idUser='"+idU+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
        
            if (rs.first()) {
                ReserEv A=new ReserEv();
            
                A.setNom(rs.getString(1));
                 A.setPrenom(rs.getString(2));
                 A.setMail(rs.getString(3));
                  A.setTel(rs.getInt(4));
                return A;
            }
        } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
        return null;
    }
      @Override
    public int RechercherId(int idEv, int idU) {
        try {
            PreparedStatement pt;
            String query = "select id,type from reserv_event where idEvenement='"+idEv+"' AND idUser='"+idU+"'";
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
      @Override
    public void ModifierPart(ReserEv a) {
        try {
            PreparedStatement pt;
            String query = "update reserv_event set type=? where id='"+a.getId()+"'";
            pt=c.prepareStatement(query);
           
            pt.setString(1,a.getNom());
             pt.setString(1,a.getPrenom());
             
              pt.setString(1,a.getMail()); pt.setInt(1,a.getTel());
            pt.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour de l'evenement " + ex.getMessage());
        }     
    }
      @Override
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
}
