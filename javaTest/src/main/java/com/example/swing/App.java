package com.example.swing;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;

public class App extends JFrame {
    public App(){
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        App jFrame = new App();
        JFXPanel jfxPanel = new JFXPanel();
        jFrame.add(jfxPanel);

        // Creation of scene and future interactions with JFXPanel
        //      should take place on the JavaFX Application Thread
        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webView.getEngine().load("http://www.baidu.com/");
        });
    }
}
