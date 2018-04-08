/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.particEv;


public interface IpartEv {
    public abstract void AjouterPart(particEv e);
    public abstract int Count (int idEv,int IdU);
    public abstract particEv RechercherParById(int idEv, int idU);
    public abstract int RechercherId(int idEv, int idU);
    public abstract void ModifierPart(particEv a);
    public abstract int CountAvis(String id);
}
