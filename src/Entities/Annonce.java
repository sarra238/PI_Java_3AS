/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.CheckBox;

public class Annonce {
    private int id;
    private String nomAnnonce ;
    private String description;
    private String type;
    private double prixReducton;
    private String image;
    private int etat;
    private CheckBox select;

    public Annonce() {}

    public Annonce(String nomAnnonce, String description, String type, double prixReducton) {
        this.nomAnnonce = nomAnnonce;
        this.description = description;
        this.type = type;
        this.prixReducton = prixReducton;
    } 
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomAnnonce() {
        return nomAnnonce;
    }

    public void setNomAnnonce(String nomAnnonce) {
        this.nomAnnonce = nomAnnonce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrixReducton() {
        return prixReducton;
    }

    public void setPrixReducton(double prixReducton) {
        this.prixReducton = prixReducton;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
    
    
    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", nomAnnonce=" + nomAnnonce + ", description=" + description + ", type=" + type + ", prixReducton=" + prixReducton + '}';
    }
    
    
    
    
}
