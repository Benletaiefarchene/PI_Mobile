/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;


import com.codename1.messaging.Message;
import com.codename1.ui.Display;
/**
 *
 * @author Sam
 */
public class Mail {
    public Mail(){
    Message m = new Message("écrire votre message ici:");
//m.getAttachments().put(textAttachmentUri, "text/plain");
//m.getAttachments().put(imageAttachmentUri, "image/png");
Display.getInstance().sendMessage(new String[] {"archene9@gmail.com"}, "Baskel.tn", m);
    }
}