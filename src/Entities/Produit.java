/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


/**
 *
 * @author dell
 */
public class Produit {
    	
private int id;
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
private double attitude;
private int idUser;

    public Produit(String NomProduit, String Region, String Categorie, double Prix) {
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
        this.Prix = Prix;
    }


    public Produit(String NomProduit, String Region, String Categorie, int Stock, double Prix, String Description, double longitude, double attitude) {
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
        this.Stock = Stock;
        this.Prix = Prix;
        this.Description = Description;
        this.longitude = longitude;
        this.attitude = attitude;
    }

    public Produit(String NomProduit, String Region, String Categorie, int Stock) {
        this.NomProduit = NomProduit;
        this.Region = Region;
        this.Categorie = Categorie;
        this.Stock = Stock;
    }

   
        


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDateLancement() {
        return DateLancement;
    }

    public void setDateLancement(String DateLancement) {
        this.DateLancement = DateLancement;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public double getAttitude() {
        return attitude;
    }

    public void setAttitude(double attitude) {
        this.attitude = attitude;
    }

    public Produit() {
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

    public Produit(String NomProduit, String Region, String Categorie, int Stock, double Prix, String Description, String nomImage, double longitude, int etat, double attitude) {
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

    public Produit(String NomProduit, String Region, String Categorie, int Stock, double Prix, String Description, String nomImage, double longitude, double attitude, int idUser) {
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
       
}
