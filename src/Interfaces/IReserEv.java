/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.ReserEv;


public interface IReserEv {
    public abstract void AjouterRes(ReserEv e);
    public abstract int Count (int idEv,int IdU);
    public abstract ReserEv RechercherParById(int idEv, int idU);
    public abstract int RechercherId(int idEv, int idU);
    public abstract void ModifierPart(ReserEv a);
    public abstract int CountRes(String id);
}
