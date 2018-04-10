/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

public class Produit {
    public int id;
    private String NomProduit;
    private String Region;
    private String Categorie;
    private int Stock;
    private double Prix;
    private String Description;
    private String DateLancement;
    private String nomImage;
    private double longitude;
        private int etat;
        private int idUser;

    private double attitude;

    public String getDateLancement() {
        return DateLancement;
    }

    public void setDateLancement(String DateLancement) {
        this.DateLancement = DateLancement;
    }

    public Produit(String NomProduit, String Region, String Categorie, int stock, double prix, String Description, String DateLancement, double longitude, int etat, double attitude) {
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
        this.Stock = stock;
        this.Prix = prix;
        this.Description = Description;
        this.DateLancement = DateLancement;
        this.longitude = longitude;
        this.etat = etat;
        this.attitude = attitude;
    }

    public Produit(int id, String NomProduit, String Region, String Categorie, int stock, double prix, String Description, String DateLancement, double longitude, int etat, double attitude) {
        this.id = id;
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
        this.Stock = stock;
        this.Prix = prix;
        this.Description = Description;
        this.DateLancement = DateLancement;
        this.longitude = longitude;
        this.etat = etat;
        this.attitude = attitude;
    }

//    public Produit(int id, String NomProduit, String Region, String Categorie, double prix) {
//        this.id = id;
//        this.NomProduit = NomProduit;
//        this.Region = Region;
//        this.Categorie = Categorie;
//        this.prix = prix;
//    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    
    

  

   

    public int getId() {
        return id;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Produit(int id) {
        this.id = id;
    }
      public Produit() {
       
    }
    

   /* public Produit(int id, String NomProduit, String Region, String Categorie,int stock,double prix, String Description, double longitude, double attitude) {
        this.id = id;
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
          this.stock = stock;
        this.prix = prix;
        this.Description = Description;
        this.longitude = longitude;
        this.attitude = attitude;
    }*/
public Produit( String NomProduit, String Region, String Categorie,int stock, double prix, String Description, double longitude,double attitude) {
       
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
         this.Stock = stock;
        this.Prix = prix;
        this.Description = Description;
        this.longitude = longitude;
        this.attitude = attitude;
    }

   

   

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        this.Stock = stock;
    }

    @Override
    public String toString() {
        return "Produit{" + "NomProduit=" + NomProduit + ", Region=" + Region + ", Categorie=" + Categorie + ", Stock=" + Stock + '}';
    }

    public Produit(int id, String NomProduit, String Region, String Categorie, int Stock, double Prix, String Description, String DateLancement, String nomImage, double longitude, int etat, double attitude) {
        this.id = id;
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
        this.Stock = Stock;
        this.Prix = Prix;
        this.Description = Description;
        this.DateLancement = DateLancement;
        this.nomImage = nomImage;
        this.longitude = longitude;
        this.etat = etat;
        this.attitude = attitude;
    }

  
    public Produit(int id, String NomProduit, String Region, String Categorie, int Stock, double Prix, String Description, String DateLancement, String nomImage, float longitude, int etat, float attitude) {
        this.id = id;
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
        this.Stock = Stock;
        this.Prix = Prix;
        this.Description = Description;
        this.DateLancement = DateLancement;
        this.nomImage = nomImage;
        this.longitude = longitude;
        this.etat = etat;
        this.attitude = attitude;
    }


    public String getNomProduit() {
        return NomProduit;
    }

    public void setNomProduit(String NomProduit) {
        this.NomProduit = NomProduit;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(float prix) {
        this.Prix = prix;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public double getAttitude() {
        return attitude;
    }
public void setprodui( String NomProduit, String Region, String Categorie,int stock, double prix, String Description, double longitude,double attitude) {
       
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
         this.Stock = stock;
        this.Prix = prix;
        this.Description = Description;
        this.longitude = longitude;
        this.attitude = attitude;
    }
    public void setAttitude(float attitude) {
        this.attitude = attitude;
    }

    public Produit(String NomProduit, String Region, String Categorie, double prix) {
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
        this.Prix = prix;
    }

    public Produit(int id, String NomProduit, String Region, String Categorie, int stock, String Description, double longitude, double attitude) {
        this.id = id;
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
        this.Stock = stock;
        this.Description = Description;
        this.longitude = longitude;
        this.attitude = attitude;
    }

    public Produit(int id, String NomProduit, String Region, String Categorie, int stock, double prix, String Description, double longitude, int etat, double attitude) {
        this.id = id;
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
        this.Stock = stock;
        this.Prix = prix;
        this.Description = Description;
        this.longitude = longitude;
        this.etat = etat;
        this.attitude = attitude;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

   

    public Produit(String NomProduit, String Region, String Categorie, int Stock, double Prix, String Description, String DateLancement, String nomImage, double longitude, int etat, double attitude) {
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
        this.Stock = Stock;
        this.Prix = Prix;
        this.Description = Description;
        this.DateLancement = DateLancement;
        this.nomImage = nomImage;
        this.longitude = longitude;
        this.etat = etat;
        this.attitude = attitude;
    }
    
    public Produit(String NomProduit, String Region, String Categorie, int Stock, double Prix, String Description, String nomImage, float longitude, int etat, float attitude) {
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
        this.Stock = Stock;
        this.Prix = Prix;
        this.Description = Description;
        this.nomImage = nomImage;
        this.longitude = longitude;
        this.etat = etat;
        this.attitude = attitude;
    }

    public Produit(String NomProduit, String Region, String Categorie, int Stock, double Prix, String Description, String nomImage, float longitude, float attitude, int idUser) {
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
        this.Stock = Stock;
        this.Prix = Prix;
        this.Description = Description;
        this.nomImage = nomImage;
        this.longitude = longitude;
        this.attitude = attitude;
        this.idUser = idUser;
    }

    
    
}
