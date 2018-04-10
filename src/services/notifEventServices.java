/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.CommentEvent;
import Entities.notifEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author lv
 */
public class notifEventServices {
      private final Connection c = MyConnection.getInstance().getConnection();
       public void AjouteNotif(notifEvent e) {
        PreparedStatement st;
        String query="insert into notif (text,idUser,date,etat) values(?,?,?,?)";
        try {
            st= c.prepareStatement(query);
           
            st.setString(1,e.getText());
            st.setInt(2,e.getIdUser());
                 st.setString(3,e.getDaten());
                 st.setInt(4,0);
            st.executeUpdate();
            System.out.println("Ajout accompli avec succés");
        } 
         catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout du notification pour l'evenement " + ex.getMessage());
        } 
    }
    
    public List<notifEvent> AfficherUserNotif( int idUser ) {
       List<notifEvent> Ann= new ArrayList<>();
        String query="select  text,date from  notif where idUser='"+idUser+"' ";
       notifEventServices es = new notifEventServices();
            
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {    
                notifEvent A=new notifEvent();
            
                 A.setText(rs.getString(1));
                 A.setDate(rs.getString(2));
                
                Ann.add(A);
                             }
          
            return Ann;
        
        } 
       
        catch (SQLException ex) {
             System.out.println("erreur lors de l'affichage des comentaires pour l'evenement! " + ex.getMessage());
        }
        
        return null; 
    }
      public void ModifierEtat(int id) {
        try {
             List<notifEvent> Ann= new ArrayList<>();
         
            PreparedStatement pt;
            String query = "update notif set etat=?  where idUser='"+id+"'";
            pt=c.prepareStatement(query);
            pt.setInt(1,1);

        
            pt.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        }
        catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour del'etat de l'evenement " + ex.getMessage());
        }     
    }
       public int Count (int IdU){    
        int i =0; 
        try {
            PreparedStatement t ;
            String query= "Select * from notif where idUser ='"+IdU+"' AND etat =0 ";
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
    
    
}
