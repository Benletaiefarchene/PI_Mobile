
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utiles.Statics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author solta
 */
public class ServiceUser {

    public static ServiceUser instance = null;

    public static ServiceUser getInstance() {
          if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceUser() {
        req = new ConnectionRequest();
    }

   


    public void ajoutUser(User ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL +"/registe1" +"/"+ ta.getEmail()
               + "/" + ta.getUsername() + "/ "+ ta.getPassword() +"/" + "user";
        con.setUrl(Url);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
 /*public void ajoutlogin(User ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL +"/login1" +"/"+ ta.getUsername()
               + "/" + ta.getPassword() ;
        con.setUrl(Url);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }*/

    public ArrayList<User> parseListUsersJson(String json) {

        ArrayList<User> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                User e = new User();

                float id = Float.parseFloat(obj.get("id").toString());

                //e.setId((int) id);
                e.setUsername(obj.get("username").toString());
                e.setEmail(obj.get("email").toString());
                e.setId((int) id);
                e.setPassword(obj.get("password").toString());
                System.out.println(e);
                listTasks.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listTasks);
        return listTasks;

    }

    ArrayList<User> listTasks = new ArrayList<>();

    public ArrayList<User> getList2(String x) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV/web/app_dev.php/blog/login1" + x);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                listTasks = ser.parseListUsersJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (listTasks.isEmpty()) {

        } else {
            return listTasks;
        }
        return null;
    }

    public boolean Modifierprofile(User u) {
        String url = Statics.BASE_URL + "/json/modifyProfile?username=" + u.getUsername() +
                    "&password=" + u.getPassword() + "&email=" + u.getEmail();
        ConnectionRequest request = new ConnectionRequest(url, false);
            request.setUrl(url);
            request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200; //Code HTTP 200 OK
                request.removeResponseListener(this);
                
            }
        });
            NetworkManager.getInstance().addToQueueAndWait(request);
       return resultOK;
    }
    public ArrayList<User> parseTasks(String jsonText){
        try {
            listTasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
               User t=new User();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setUsername(obj.get("username").toString());
                t.setEmail(obj.get("email").toString());
                
               listTasks.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
        return listTasks;
    }
    
    public ArrayList<User> getUser(User C){
        String url = Statics.BASE_URL+"/login1/"+C.getUsername()+"/"+C.getPassword();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listTasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listTasks;
    }
}
