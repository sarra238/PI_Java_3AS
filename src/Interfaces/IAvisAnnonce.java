/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.AvisAnnonce;

public interface IAvisAnnonce {
    public abstract void AjouterAvisAnnonce(AvisAnnonce a);
    public abstract int CountAvis(String id); 
}
