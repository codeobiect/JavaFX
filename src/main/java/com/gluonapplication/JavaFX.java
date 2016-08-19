package com.gluonapplication;

import com.gluonhq.charm.glisten.application.MobileApplication;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class JavaFX extends Application {

    Stage window;
    Scene scene1, scene2;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        double width = visualBounds.getWidth();
        double height = visualBounds.getHeight();

        Label label1 = new Label("Click the button");
        Label label2 = new Label();
        Button button1 = new Button("Button");
        Button button3 = new Button("List content folder");
        Button openTxt = new Button("Open .txt");
        Button closeApp = new Button("Close");
        TextField textField = new TextField();
        button1.setOnAction(e -> window.setScene(scene2));
        button1.setOnAction((e) -> {
            window.setScene(scene2);
            label2.setText(textField.getText());

        });

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, textField, button1, button3, openTxt,closeApp);
        scene1 = new Scene(layout1, width, height);

        Button button2 = new Button("Go back");
        button2.setOnAction(e -> window.setScene(scene1));

        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(label2, button2);
        scene2 = new Scene(layout2, width, height);

        button3.setOnAction((e) ->{
            window.setScene(scene2);

            String[] elementy = new File("/storage/emulated/0/DCIM").list();
            ArrayList<String> lista = new ArrayList<String>();
            lista.addAll(Arrays.asList(elementy));
            label2.setText(lista.toString());
            //System.out.println(lista);
        });

        closeApp.setOnAction((e)->{
            System.exit(0);
        });

        openTxt.setOnAction((e) ->{
            try {
                window.setScene(scene2);

                BufferedReader readTxt = new BufferedReader(new FileReader("/storage/emulated/0/DCIM/Wycieraczki.txt"));
                String s = "", line = null;
                while ((line = readTxt.readLine()) != null){
                    s += line;
                }
                label2.setText(s.toString());
                //System.out.println(s);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        window.setScene(scene1);
        window.setTitle("Elenx");
        window.show();
    }

}
