/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

public class Evenement {
    
      private int id;
      private String nomEvenement ;
      private String dateDeb;
      private String dateFin;
      private String Description;
      private String localisation;
      private String type;
      private String NomImg;
      private int etat;
      private int IdUser;

    public Evenement( String nomEvenement, String dateDeb, String dateFin, String Description, String localisation ) {

        this.nomEvenement = nomEvenement;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.Description = Description;
        this.localisation = localisation;
      
       

    }
     public Evenement( String nomEvenement, String Description, String localisation, String type) {
       
        this.nomEvenement = nomEvenement;
     
        this.Description = Description;
        this.localisation = localisation;
        this.type = type;
    }
   public Evenement(int id, String nomEvenement, String Description, String localisation, String type) {
        this.id = id;
        this.nomEvenement = nomEvenement;
        this.Description = Description;
        this.localisation = localisation;
        this.type = type;
    }

    public Evenement(int id, String nomEvenement, String dateDeb, String dateFin, String Description, String localisation, String type, String NomImg) {
        this.id = id;
        this.nomEvenement = nomEvenement;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.Description = Description;
        this.localisation = localisation;
        this.type = type;
        this.NomImg = NomImg;
    }
   
   
    public Evenement() {
    }

    public String getNomImg() {
        return NomImg;
    }

    public void setNomImg(String NomImg) {
        this.NomImg = NomImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }
    
    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nomEvenement=" + nomEvenement + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + ", Description=" + Description + ", localisation=" + localisation + ", type=" + type + '}';
    }
    
    
    
}
