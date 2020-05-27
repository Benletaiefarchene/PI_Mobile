/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;



import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceCom;

import com.mycompany.myapp.services.ServicePost;

import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author arche
 */
public class Detail extends BaseForm {
      public Detail(Commentaire C,Post P,User Us, Resources res)  {

          
            super("Newsfeed", BoxLayout.y());

               Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Détails Post");
        getContentPane().setScrollVisible(false);
              tb.addSearchCommand(e -> {});
        super.addSideMenu(res,Us);
         tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("b1.jpg"), spacer1, "15 Likes  ", "85 Comments", "BASKEL.TN ");
        addTab(swipe, res.getImage("Blog_(1).jpg"), spacer2, "100 Likes  ", "66 Comments", "Blog");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
            
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Detail Post", barGroup);
        all.setUIID("SelectBar");
        
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, all),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
      
        });
     Form previous = Display.getInstance().getCurrent();
        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
            
//       String url="http://127.0.0.1/pide/web/images/"+P.getImage();
//            Image imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
//           ImageViewer  img = new ImageViewer(imgs);
           
          
               
          
            
            Label Title = new Label(P.getTitle()+"  ");
            Label Description = new Label(P.getDescription()+ "");
             Label b = new Label(P.getRating()+ "");
                        
                         add(Title).add(Description).add(b);
                        
                         
                        // c1.add(img).add(C);
 
                      // Button valide = new Button("Telecharger");
        
    
                        
//                          Button rec = new Button("PasserReclamation");
   

      
  // valide.addPointerPressedListener(e -> {
  //  System.out.println("aa");
          ////  Dialog.show("Alert", "http://localhost/pidev_integration/web/uploads/brochures/"+P.getLien(),new Command("OK"));
                      //   ServiceProduits.getInstance().Contacter(P); 
                        //   previous.showBack();
                      //  Form hi = new Form("Browser", new BorderLayout());
//BrowserComponent browser = new BrowserComponent();
//browser.setURL("http://localhost/pidev_integration/web/uploads/brochures/bf8205a380b9b9a109c3dba3189410fa-5e5711dc7540d.pdf");
//Form hi = new Form("PDF Viewer", BoxLayout.y());
//  FileSystemStorage fs = FileSystemStorage.getInstance();
//    String fileName = fs.getAppHomePath() + "pdf-sample.pdf";
//    if(!fs.exists(fileName)) {
//        Util.downloadUrlToFile("http://localhost/pidev_integration/web/uploads/brochures/"+P.getLien(), fileName, true);
//    }
//    Display.getInstance().execute(fileName);
//   
//     
    // } );                         
//add(C).add(valide);
  Button Add = new Button("Ajouter Commentaire");
                     
        
      
                       // Form current;
                       // current=this;
                        Add.addActionListener(new ActionListener() {
                   @Override
         public void actionPerformed(ActionEvent evt) {
                
          new AddCom(res,Us,P,C).show();
            }} );
                       // new AddPost(current,res).show();
                        add(Add);
                        
     ArrayList<Commentaire> Cp = new ArrayList<>();
         Cp =ServiceCom.getInstance().getAllTasks(P); 
          
        for (Commentaire c: Cp){
        
            Component img;
            
           
        
           //add(res.getImage(("téléchargement.jpg")));
          
            //String url="http://127.0.0.1/pide/web/images/"+c.getImage();
            //Image imgs = URLImage.createToStorage(enc
                 
            addButton(res.getImage("Blog_(1).jpg"),c,c.getContent(), focusScrolling, LEFT, CENTER, res);
        
           //addButton( c.getNiveau() ,c, false , c.getEmail(), c.getLien(),res);
            Button supp = new Button("Supprimer");
                        
                        Button mod = new Button("Modifier");
                         supp.addActionListener(new ActionListener() {
                   @Override
         public void actionPerformed(ActionEvent evt) {
                ServiceCom Com= new  ServiceCom();
               Com.SuppCom(c.getId());
                 Dialog.show("Alert", "Votre Post est"+" Supprimer ", "ok", null);
          new Detail(C,P,Us,res).show();
            }} );
                          
          
                       // Form current;
                       // current=this;
                        mod.addActionListener(new ActionListener() {
                   @Override
         public void actionPerformed(ActionEvent evt) {
                      
                
          new ModifCom(res,P,Us,c).show();
         
    };
      
                         
                        
                      
                                });
add(supp);
add(mod);
                                }
      }
   private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
           
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    } 
    private void addButton(Image img, Commentaire C,String con  ,boolean liked, int likeCount, int commentCount ,Resources res) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(C.getContent());
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
       

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
     
          
   }
    
     private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                
            }
        });
    }
}

