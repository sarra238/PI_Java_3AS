/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author lv
 */
public class notifEvent {
   private int  idUser;
   private String textn;
   private int id;
   private String date;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getText() {
        return textn;
    }

    public void setText(String textn) {
        this.textn = textn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDaten() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

  
   
}
