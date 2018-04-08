/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.User;
import java.util.List;


public interface IUser {
    public abstract String login(User u);
    public abstract User RechercherUserByName(String nom);
    public abstract boolean VerifRole(String nom,String s);
    public abstract void signIn(User u);
    public abstract boolean MdpO(User u);
    public abstract String RechercherUsertByPass(String p);
    public abstract List<User> RechercherUserByRole(String nom);
    public abstract User RechercherUsertById(int id);
}
