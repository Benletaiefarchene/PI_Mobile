package com.codename1.uikit.cleanmodern;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.entities.User;

import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;
/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {
    public SignInForm(Resources res) {
        super(new BorderLayout());
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        add(BorderLayout.NORTH, new Label(res.getImage("q.png"), "LogoLabel"));
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
       // Button Ajout = new Button("ajout");
        Button signUp = new Button("Sign Up");
        //signUp.addActionListener(e -> new SignUpForm(res).show());
        //signUp.setUIID("Link");
         signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Form current;
      current=this;
        //Ajout.addActionListener(e -> new AddEvenet(current).show());
        Label doneHaveAnAccount = new Label("Don't have an account?");
        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
               // createLineSeparator(),
               // Ajout,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        Post P=new Post();
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
      //  signIn.addActionListener(e -> new NewsfeedForm(res).show());
      signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
        ServiceUser C = new ServiceUser();
        User u = new User();
        
        u.setUsername(username.getText());
        u.setPassword(password.getText());
        ArrayList<User> l= C.getUser(u);
       if(!l.isEmpty()){
        new ListPost(P,l.get(0),res).show();
       }else{
            Dialog.show("ERROR", "Server error", new Command("OK"));
       }
        }});
    }
}