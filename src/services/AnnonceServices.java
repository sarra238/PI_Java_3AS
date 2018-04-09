/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyConnection;
import Entities.Annonce;
import Interfaces.IAnnonce;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static services.UserService.conn;
     
public class AnnonceServices implements IAnnonce {
    private final Connection c = MyConnection.getInstance().getConnection();
    
    public AnnonceServices(){
       
    }
    @Override
    public void AjouterAnnonce(Annonce a) {
     PreparedStatement st;
     String query="insert into annonce (NomAnnonce,Description,type,PrixReducton,nomImage,etat,idUser) values(?,?,?,?,?,?,?)";
        try {
            st= c.prepareStatement(query);
            st.setString(1,a.getNomAnnonce() );
            st.setString(2,a.getDescription());
            st.setString(3,a.getType());
            st.setDouble(4,a.getPrixReducton());
            st.setString(5,a.getImage());
            st.setInt(6,0);
            st.setInt(7,conn);
            st.executeUpdate();
            System.out.println("Ajout accompli avec succés");
            
        } 
        catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout de l'annonce " + ex.getMessage());
        }
    }
    @Override
    public List<Annonce> AfficherAllAnnonce()
    {
        List<Annonce> Ann= new ArrayList<>();
        
       String query="select id,nomAnnonce,Description,Type,PrixReducton,nomImage,etat,idUser from Annonce ";
       int e;
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {
                Annonce A=new Annonce();
                A.setEtat(rs.getInt(7));
                e=A.getEtat();
                A.setId(rs.getInt(1));
                A.setNomAnnonce(rs.getString(2));
                A.setDescription(rs.getString(3));
                A.setType(rs.getString(4));
                A.setPrixReducton(rs.getDouble(5));
                A.setImage(rs.getString(6));
                A.setIdUser(rs.getInt(7));
                if(e==1){
                Ann.add(A);}
            }
            return Ann;
        } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de toutes les annonces " + ex.getMessage());
        }
        return null;
    }
    @Override
     public List<Annonce> AfficherAllAnnonceC()
    {
        List<Annonce> Ann= new ArrayList<>();
        
       String query="select id,nomAnnonce,Description,Type,PrixReducton,nomImage,etat,idUser from Annonce ";
       int e;
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {
                Annonce A=new Annonce();
                A.setEtat(rs.getInt(7));
                e=A.getEtat();
                A.setId(rs.getInt(1));
                A.setNomAnnonce(rs.getString(2));
                A.setDescription(rs.getString(3));
                A.setType(rs.getString(4));
                A.setPrixReducton(rs.getDouble(5));
                A.setImage(rs.getString(6));
                A.setIdUser(rs.getInt(7));
                if(e==0){
                Ann.add(A);}
            }
            return Ann;
        } 
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage de toutes les annonces " + ex.getMessage());
        }
        return null;
    }
    @Override
    public Annonce RechercherAnnonceByName(String nom) {
        try {
            PreparedStatement ps;
            String query = "select id,nomAnnonce,Description,Type,PrixReducton from annonce where nomAnnonce LIKE '%"+nom+"%'";
            ps= c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Annonce A=new Annonce();
                A.setId(rs.getInt(1));
                A.setNomAnnonce(rs.getString(2));
                A.setDescription(rs.getString(3));
                A.setType(rs.getString(4));
                A.setPrixReducton(rs.getDouble(5));
                return A;
            }    
        }
        catch (SQLException ex) {
               System.out.println("erreur lors de la recherche de l'annonce " + ex.getMessage());
        }   
      return null;       
    }
     @Override
    public Annonce RechercherAnnonceById(int id) {
        try {
            PreparedStatement pt;
            String query = "select id,nomAnnonce,Description,Type,PrixReducton from annonce where id='"+id+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
            Annonce a = new Annonce();
            if (rs.next()) {
                a.setId(rs.getInt(1));
                a.setNomAnnonce(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setType(rs.getString(4));
                a.setPrixReducton(rs.getDouble(5));
                return a;
            }
        } catch (SQLException ex) {
                System.out.println("erreur lors de la recherche de l'annonce " + ex.getMessage());
        }   
        return null;
    }
    @Override
    public void SupprimerAnnonceA(Annonce a) {
        try {
            Annonce b;
            AnnonceServices A=new AnnonceServices();
            b=A.RechercherAnnonceByName(a.getNomAnnonce());
            if(b!=null){
            PreparedStatement st;
            String query = "delete from annonce where id='"+a.getId()+"'";
            st=c.prepareStatement(query);
            st.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
            }
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la suppression de l'annonce " + ex.getMessage());
        }
    }
     public void SupprimerAnnonceA2(Annonce a) {
        try {
            Annonce b;
            AnnonceServices A=new AnnonceServices();
            b=A.RechercherAnnonceByName(a.getNomAnnonce());
            if(b!=null){
            PreparedStatement st;
            String query = "delete from annonce where id='"+a.getId()+"' and idUser='"+conn+"'";
            st=c.prepareStatement(query);
            st.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
            }
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la suppression de l'annonce " + ex.getMessage());
        }
    }
    @Override
    public void SupprimerAnnonce(int id) {
        try {
            Annonce b;
            AnnonceServices A = new AnnonceServices();
            b=A.RechercherAnnonceById(id);
            if(b!=null){
            PreparedStatement ps;
            String query = "delete from annonce where id='"+id+"'";
            ps=c.prepareStatement(query);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
            }
        } 
        catch (SQLException ex) {
            System.out.println("erreur lors de la suppression de l'annonce " + ex.getMessage());
        }
    }
    @Override
    public void ModifierAnnonce(Annonce a) {
         try {
            PreparedStatement pt;
            String query = "update annonce set nomAnnonce=? ,description=?,type=?,prixReducton=?,nomImage=? where id='"+a.getId()+"' and idUser='"+a.getIdUser()+"'";
            pt=c.prepareStatement(query);
            pt.setString(1,a.getNomAnnonce());
            pt.setString(2,a.getDescription());
            pt.setString(3,a.getType());
            pt.setDouble(4,a.getPrixReducton());
            pt.setString(5, a.getImage());
           
            pt.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        }
         catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour de l'annonce " + ex.getMessage());
        }               
    }

    @Override
    public int Count(String id) {
        int i=0;
        try {
            PreparedStatement pt;
            String query = "select * from annonce where type='"+id+"'";
            pt=c.prepareStatement(query);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                i+=1;
            }
        }
         catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour de l'annonce " + ex.getMessage());
        }  
        return i;
    }
    @Override
    public void ModifierEtat(Annonce a) {
        try {
            PreparedStatement pt;
            String query = "update annonce set etat=? where id='"+a.getId()+"'";
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

