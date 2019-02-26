package com.mycompany.myapp;

import static com.codename1.ui.CN.*;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import java.io.IOException;
import com.codename1.ui.Toolbar;
import com.codename1.io.NetworkEvent;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class HelloWorld {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        Form hi = new Form("Welcome", new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        final Label apple = new Label(theme.getImage("apple-icon.png")); 
        final Label android = new Label(theme.getImage("android-icon.png")); 
        final Label windows = new Label(theme.getImage("windows-icon.png")); 
        Button getStarted = new Button("Let's Get Started!");
        FontImage.setMaterialIcon(getStarted, FontImage.MATERIAL_LINK);
        getStarted.setUIID("GetStarted");
        hi.addComponent(BorderLayout.CENTER, 
                LayeredLayout.encloseIn(
                        BoxLayout.encloseY(
                                new Label(theme.getImage("duke-no-logos.png")),
                                getStarted
                        ),
                        FlowLayout.encloseRightMiddle(apple)
                    )
        );
        
        getStarted.addActionListener((e) -> {
            execute("https://www.codenameone.com/developers.html");
        });
        
        new UITimer(() -> {
            if(apple.getParent() != null) {
                apple.getParent().replace(apple, android, CommonTransitions.createFade(500));
            } else {
                if(android.getParent() != null) {
                    android.getParent().replace(android, windows, CommonTransitions.createFade(500));
                } else {
                    windows.getParent().replace(windows, apple, CommonTransitions.createFade(500));
                }                
            }
        }).schedule(2200, true, hi);
        hi.show();
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
