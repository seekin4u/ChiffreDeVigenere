/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VijnerCode;

import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

/**
 *
 * @author IMM
 * VCA - Vijner Crypt App
 * Многоалфавитный шифр замены - 18** год
 * Рисшифрован офицером прусской армии
 * 
 * Подробнее о шифре - https://ru.wikipedia.org/wiki/Шифр_Виженера
 *
*/

public class MainWnd extends Application {
    public static final int wight = 550;
    public static final int heigth = 700;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Vijner Crypt App");
        primaryStage.setWidth(wight / 2);
        primaryStage.setHeight(heigth / 2);
        
        BorderPane rootPane = new BorderPane();
        
        HBox verticalPane = new HBox();
        
        Button doItButton = new Button();
        doItButton.setText("Table example");
        doItButton.setAlignment(Pos.CENTER);
        verticalPane.getChildren().add(doItButton);
        verticalPane.setPadding(new Insets(25, 25, 25, 25));
        
        Button generateCode = new Button();
        generateCode.setText("Generate");
        generateCode.setAlignment(Pos.CENTER);
        verticalPane.getChildren().add(generateCode);
        
        verticalPane.setAlignment(Pos.CENTER);
        verticalPane.setSpacing(25);
        rootPane.setCenter(verticalPane);
        
        doItButton.setOnAction(event -> {
            System.out.println("BATON crytp pressed");
            Stage GridWnd = new Stage();

            GridWnd.setTitle("Table");
            GridWnd.setWidth(550);
            GridWnd.setHeight(700);
            GridWnd.setResizable(false);
            
            VBox root = new VBox();
            
            Label desc = new Label("Вид таблицы Виженера");
            desc.setAlignment(Pos.CENTER);
            desc.setPadding(new Insets(25, 25, 25, 25));
            
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(5);
            grid.setVgap(5);
            grid.setGridLinesVisible(true);//ЛИНИИ ГРИДА
            grid.setPadding(new Insets(25, 25, 25, 25));            
            
            String buffer = "";
        
            int asci_char = 65;//начало англицкого алфавита в ASCII
            int asci_count = 0;
        
            for(int i = 0; i < 26; i ++){
                for(int j = 0; j< 26; j++)
                {         
                    Label buffer_label = new Label(Character.toString((char) (asci_char + ( asci_count % 26) ) )); //25 % 10 = 5
                    grid.add(buffer_label, i, j, 1, 1);               
                    asci_count++;
                }
                asci_count++;
            }
            
            root.setAlignment(Pos.CENTER);
            root.getChildren().addAll(desc, grid);
            
            Scene scene = new Scene(root);
            GridWnd.setScene(scene);
            
            GridWnd.initOwner(primaryStage);
            GridWnd.initModality(Modality.APPLICATION_MODAL);
            GridWnd.showAndWait();
        });
             
        VBox textAndKeyPane = new VBox();
        
        //Crypt text area
        TextArea cryptTextArea = new TextArea("This is a test text text text");
        cryptTextArea.setPrefHeight(wight / 4);
        
        textAndKeyPane.getChildren().add(cryptTextArea);//addAll();
        
        //Key text area
        TextArea keyTextArea = new TextArea("This is a test key");
        keyTextArea.setPrefHeight(wight / 10);
        
        textAndKeyPane.getChildren().add(keyTextArea);
        
        rootPane.setTop(textAndKeyPane);
        
        StackPane textEncryptPane = new StackPane();
        textEncryptPane.getChildren().add(new TextArea(""));
        rootPane.setBottom(textEncryptPane);
        
        Scene scene = new Scene(rootPane);
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        generateCode.setOnAction(event -> {
            System.out.println("GENERATE button pressed");
            fillKey(keyTextArea, cryptTextArea);
        });
    }
    
    //cut or fill key to needed size
    private static String fillKey(TextArea keyTextArea, TextArea cryptTextArea){ 
        
        String Skey = removePunct(keyTextArea.getText());
        String Scrypt = removePunct(cryptTextArea.getText());
        
        //bufkey less or the same length as crypt length
        //so we need key array was the same length as crypt
        char[] bufkey = Skey.toCharArray();//making 2 new objects
        char[] crypt = Scrypt.toCharArray();
        char[] key = Arrays.copyOf(bufkey, Scrypt.length()); //copy array without creating new obj
        
        int crypt_size = Scrypt.length();
        int key_size = Skey.length();
        
        //olwer-case both
        for(int i = 0; i < crypt_size; i++){
            crypt[i] = Character.toLowerCase(crypt[i]);
        }
        
        for(int i = 0; i < key_size; i++){
            key[i] = Character.toLowerCase(key[i]);
        }
        
        //making key needed length
        if(key_size < crypt_size){
            for(int i = 1; i < crypt_size - key_size; i++){
                key[i + key_size] = key[i];
            }
        }
        
        String answer = key.toString(); //cast to string to make input
        
        //some debug info
        System.out.println("\nKey after fillKey():" + key);
        
        return answer;
    }
    
    //returns already coded string
    private static String code(String message, String code_key){
        int ascii_code = 65;
        
        String answer = "";
        
        return answer;
    }
    
    //restricted symbols
    private static final String PUNCT = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ ";

    public static String removePunct(String str) {
        StringBuilder result = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (PUNCT.indexOf(c) < 0) { //getting indes of first includint of char in that string
               result.append(c);
            }
        }
        
    return result.toString();
}

    public static void main(String[] args) {
        launch(args);
    }
    
}