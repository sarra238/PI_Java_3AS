/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Evenement;
import java.util.List;

public interface IEvenement {
    public abstract void AjouterEvenement(Evenement a);
    public abstract List<Evenement> AfficherAllEvenement();
    public abstract Evenement RechercherEvenementByName(String nom);
    public abstract Evenement RechercherEvenementById(int id);
    public abstract void SupprimerEvenement(Evenement a);
    public abstract void ModifierEvenement(Evenement a);
    public abstract void SupprimerEvenement(int id); 
    public abstract int Count(String id);
    public abstract List<Evenement> AfficherAllEvenementC();
    public abstract void ModifierEtat(Evenement a);
}
