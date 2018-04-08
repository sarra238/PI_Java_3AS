/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyConnection;
import Entities.Annonce;
import Entities.Evenement;
import Interfaces.IEvenement;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.FileChooser;
import static services.UserService.conn;



public class EvenementServices implements IEvenement{
      private FileInputStream fis;
      private FileChooser fileChooser ;
      private final Connection c = MyConnection.getInstance().getConnection();
      public static int idEv;
      public EvenementServices(){}

    @Override
    public void AjouterEvenement(Evenement e) {
        PreparedStatement st;
        String query="insert into evenement (nomEvenement,dateDeb2,dateFin2,description,localisation,type,nomImg,etat,idUser) values(?,?,?,?,?,?,?,?,?)";
        try {
            st= c.prepareStatement(query);
            st.setString(1,e.getNomEvenement() );
            st.setString(2, e.getDateDeb());
            st.setString(3, e.getDateFin());
            st.setString(4,e.getDescription());
            st.setString(5,e.getLocalisation());
            st.setString(6,e.getType());
            st.setString(7,e.getNomImg());
            st.setInt(8,0);
            st.setInt(9,conn);
            st.executeUpdate();
            System.out.println("Ajout accompli avec succés");
            
        } 
        catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout de l'evenement " + ex.getMessage());
        }
    }

    @Override
    public List<Evenement> AfficherAllEvenement() {
        List<Evenement> Ann= new ArrayList<>();
        int e;
        String query="select id, nomEvenement,dateDeb2,dateFin2,description,localisation,type,nomImg,etat from evenement ";
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {
                Evenement A=new Evenement();
                A.setEtat(rs.getInt(9));
                e=A.getEtat();
                A.setId(rs.getInt(1));
                A.setNomEvenement(rs.getString(2));
                A.setDateDeb(rs.getString(3));
                A.setDateFin(rs.getString(4));
                A.setDescription(rs.getString(5));
                A.setLocalisation(rs.getString(6));
                A.setType(rs.getString(7));
                A.setNomImg(rs.getString(8));
                if(e==1){
                Ann.add(A);}
            }
            return Ann;
        } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de tous les evenements! " + ex.getMessage());
        }
        return null;
    }
      @Override
    public List<Evenement> AfficherAllEvenementC() {
        List<Evenement> Ann= new ArrayList<>();
        int e;
        String query="select id, nomEvenement,dateDeb2,dateFin2,description,localisation,type,nomImg,etat from evenement ";
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {
                Evenement A=new Evenement();
                A.setEtat(rs.getInt(9));
                e=A.getEtat();
                A.setId(rs.getInt(1));
                A.setNomEvenement(rs.getString(2));
                A.setDateDeb(rs.getString(3));
                A.setDateFin(rs.getString(4));
                A.setDescription(rs.getString(5));
                A.setLocalisation(rs.getString(6));
                A.setType(rs.getString(7));
                A.setNomImg(rs.getString(8));
                if(e==0){
                Ann.add(A);}
            }
            return Ann;
        } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de tous les evenements! " + ex.getMessage());
        }
        return null;
    }
    @Override
    public Evenement RechercherEvenementByName(String nom) {
        try {
            PreparedStatement ps;
            String query = "select id,nomEvenement,dateDeb2,dateFin2,description,localisation,type from evenement where nomEvenement LIKE '%"+nom+"%'";
            ps= c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Evenement A=new Evenement();
                A.setId(rs.getInt(1));
                A.setNomEvenement(rs.getString(2));
                A.setDateDeb(rs.getString(3));
                A.setDateFin(rs.getString(4));
                A.setDescription(rs.getString(5));
                A.setLocalisation(rs.getString(6));
                A.setType(rs.getString(7));
                return A;
            }    
            
        }
        catch (SQLException ex) {
               System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
      return null; 
    }
    @Override
    public Evenement RechercherEvenementById(int id) {
        try {
            PreparedStatement pt;
            String query = "select id,nomEvenement,dateDeb2,dateFin2,description,localisation,type,idUser from evenement where id='"+id+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
            Annonce a = new Annonce();
            if (rs.next()) {
                Evenement A=new Evenement();
                A.setId(rs.getInt(1));
                A.setNomEvenement(rs.getString(2));
                A.setDateDeb(rs.getString(3));
                A.setDateFin(rs.getString(4));
                A.setDescription(rs.getString(5));
                A.setLocalisation(rs.getString(6));
                A.setType(rs.getString(7));
                A.setIdUser(rs.getInt(rs.getString(8)));
                return A;
            }
        } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche de l'evenement " + ex.getMessage());
        }   
        return null;
    }
    @Override
    public void SupprimerEvenement(Evenement a) {
         try {
            Evenement b;
            EvenementServices A=new EvenementServices();
            b=A.RechercherEvenementByName(a.getNomEvenement());
            if(b!=null){
            PreparedStatement st;
            String query = "delete from evenement where id='"+a.getId()+"'";
            st=c.prepareStatement(query);
            st.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
            }
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la suppression de l'evenement " + ex.getMessage());
        }
    }
    @Override
    public void SupprimerEvenement(int id) {
        try {
            Evenement b;
            EvenementServices A = new EvenementServices();
            b=A.RechercherEvenementById(id);
            if(b!=null){
            PreparedStatement ps;
            String query = "delete from evenement where id='"+id+"'";
            ps=c.prepareStatement(query);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
            }
        } 
        catch (SQLException ex) {
            System.out.println("erreur lors de la suppression de l'evenement " + ex.getMessage());
        }}
    @Override
    public void ModifierEvenement(Evenement a) {
        try {
            PreparedStatement pt;
            String query = "update evenement set nomEvenement=? ,dateDeb2=?,dateFin2=?,description=?,localisation=?,type=? where id='"+a.getId()+"'";
            pt=c.prepareStatement(query);
            pt.setString(1,a.getNomEvenement());
            pt.setString(2,a.getDateDeb());
            pt.setString(3, a.getDateFin());
            pt.setString(4, a.getDescription());
             pt.setString(5,a.getLocalisation());
            pt.setString(6,a.getType());
            pt.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour de l'evenement " + ex.getMessage());
        }     
    }
      @Override
    public int Count (String id){    
        int i =0; 
        try {
            PreparedStatement t ;
            String query= "Select * from Evenement where type ='"+id+"'";
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
    public void ModifierEtat(Evenement a) {
        try {
            PreparedStatement pt;
            String query = "update evenement set etat=? where id='"+a.getId()+"'";
            pt=c.prepareStatement(query);
            pt.setInt(1,1);
            pt.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour del'etat de l'evenement " + ex.getMessage());
        }     
    }
}
