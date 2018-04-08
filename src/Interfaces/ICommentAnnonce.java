/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.CommentAnn;
import java.util.List;

public interface ICommentAnnonce {
    public abstract void AjouterCommentAnnonce(CommentAnn a);
    public abstract List<CommentAnn> AfficherAllComment();
    public abstract void SupprimerCommentA(CommentAnn a);
}
