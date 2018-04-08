/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.CommentEvent;
import Interfaces.ICommentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

public class CommentEventServices implements ICommentEvent{
     private final Connection c = MyConnection.getInstance().getConnection();
     
     @Override
    public void AjouterComm(CommentEvent e) {
        PreparedStatement st;
        String query="insert into com_event (commentaire,idUser,idEvenement,date) values(?,?,?,?)";
        try {
            st= c.prepareStatement(query);
           
            st.setString(1,e.getCommentaire());
            st.setInt(2,e.getUserId());
            st.setInt(3,e.getIdEv());
            st.setString(4,e.getDate());
            st.executeUpdate();
            System.out.println("Ajout accompli avec succés");
        } 
         catch (SQLException ex) {
           System.out.println("erreur lors de l'ajout du comentaire pour l'evenement " + ex.getMessage());
        } 
    }
     @Override
    public List<CommentEvent> AfficherAllComm( int ide ) {
       List<CommentEvent> Ann= new ArrayList<>();
        String query="select  commentaire,date from  com_event where idEvenement='"+ide+"' ";
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {
                CommentEvent A=new CommentEvent();
            
                 A.setCommentaire(rs.getString(1));
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
     @Override
    public List<CommentEvent> AfficherAllCommA() {
       List<CommentEvent> Ann= new ArrayList<>();
        String query="select  commentaire,date from  com_event";
        try {
            Statement st=c.createStatement();
            ResultSet rs =st.executeQuery(query);
            while(rs.next())
            {
                CommentEvent A=new CommentEvent();
            
                 A.setCommentaire(rs.getString(1));
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
    
}
