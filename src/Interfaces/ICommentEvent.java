/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.CommentEvent;
import java.util.List;

public interface ICommentEvent {
    public abstract void AjouterComm(CommentEvent e);
    public abstract List<CommentEvent> AfficherAllComm(int ide);
    public abstract List<CommentEvent> AfficherAllCommA();
    
}
