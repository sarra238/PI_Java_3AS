/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Annonce;
import java.util.List;

public interface IAnnonce {
    public abstract void AjouterAnnonce(Annonce a);
    public abstract List<Annonce> AfficherAllAnnonce();
    public abstract Annonce RechercherAnnonceByName(String nom);
    public abstract Annonce RechercherAnnonceById(int id);
    public abstract void SupprimerAnnonceA(Annonce a);
    public abstract void ModifierAnnonce(Annonce a);
    public abstract void SupprimerAnnonce(int id); 
    public abstract int Count(String id);
    public abstract List<Annonce> AfficherAllAnnonceC();
    public abstract void ModifierEtat(Annonce a);
}
