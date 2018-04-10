/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


public class confcommande {
    private String nom;
private String prenom;
private String adresse;
    private Produit  produit;
    private User user;

    public confcommande(String nom, String prenom, String adresse, Produit produit, User user) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.produit = produit;
        this.user = user;
    }

    public confcommande(String nom, String prenom, String adresse, User user) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.user = user;
    }

    

    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public confcommande(String nom, String prenom, String adresse, Produit produit) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.produit = produit;
    }

    public confcommande(String prenom, String adresse, Produit produit) {
        this.prenom = prenom;
        this.adresse = adresse;
        this.produit = produit;
    }

    public confcommande(String nom, String prenom, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public confcommande() {
    }

    


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "confcommande{" + "nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", produit=" + produit + '}';
    }

   
    
    


}
