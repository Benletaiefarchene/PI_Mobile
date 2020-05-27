/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utiles.Statics;

/**
 *
 * @author acer
 */
public class ServicePost {
    
     public ArrayList<Post> tasks;
    
    public static ServicePost instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServicePost() {
         req = new ConnectionRequest();
    }

    public static ServicePost getInstance() {
        if (instance == null) {
            instance = new ServicePost();
        }
        return instance;
    }

    
        public ArrayList<Post> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Post t = new Post();
              //  float id = Float.parseFloat(obj.get("id").toString());
                //t.getIdcour(obj.get("idvelo").toString());
              // t.setDate(obj.get("date").toString());
               
                t.setId((int)Float.parseFloat(obj.get("id").toString()));
            //  t.setId(obj.get("id").hashCode());
              // t.setCreator((int)Float.parseFloat(obj.get("creator").toString()));
               t.setTitle(obj.get("title").toString());
              t.setDescription(obj.get("description").toString());
             //  t.setPhoto(obj.get("photo").toString());
             //  t.setPostdate(obj.get("postdate").toString());
              t.setRating((int)Float.parseFloat(obj.get("rating").toString()));
             //  t.setBlocke((int)Float.parseFloat(obj.get("blocke").toString()));
               
               
                //t.setDate(obj.get("date").toString());
                
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    
}

         public ArrayList<Post> getAllTasks(){
        String url ="http://localhost/PIDEV/web/app_dev.php/blog/postslist";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
public boolean addPo(Post e) {
        String url = Statics.BASE_URL + "/addpost" +"/"+"91"+"/"+e.getTitle()+"/"+e.getDescription()+"/"+e.getPhoto()+"/"+e.getBlocke()+"/"+e.getRating();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

 public void Supprimer(int id) {
           // Post e= new Post();
        ConnectionRequest req = new ConnectionRequest();
         // String url = Statics.BASE_URL ;
        //cnx.setUrl("http://localhost/PIDEV/web/app_dev.php/blog/deletepost"+"/"+e.getId);
    String url = Statics.BASE_URL+"/deletepost/"+id; 
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            System.out.println(req.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
}

public boolean ModPo(Post e) {
        String url = Statics.BASE_URL + "/updatepost" +"/"+e.getId()+"/"+e.getTitle()+"/"+e.getDescription()+"/"+"bb"+"/"+e.getRating()+"/"+"0";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
 public ArrayList<Post> getrech(String title){
        String url ="http://localhost/PIDEV/web/app_dev.php/blog/recherche"+title;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;




}
}
