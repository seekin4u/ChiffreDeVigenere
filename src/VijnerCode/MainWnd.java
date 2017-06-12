/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VijnerCode;

import java.security.CryptoPrimitive;
import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
 * @author IMM VCA - Vijner Crypt App Многоалфавитный шифр замены - 18** год
 * Рисшифрован офицером прусской армии
 *
 * Подробнее о шифре - https://ru.wikipedia.org/wiki/Шифр_Виженера
 *
 */
public class MainWnd extends Application {

    public static final int wight = 550;
    public static final int heigth = 850;

    public TextArea cryptTextArea;
    public TextArea keyTextArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("VCA");
        primaryStage.setWidth(wight / 2);
        primaryStage.setHeight(heigth / 2);

        BorderPane rootPane = new BorderPane();

        VBox verticalPane = new VBox();

        HBox horizontalCheckBox = new HBox();

        Button doItButton = new Button();
        doItButton.setText("Table example");
        doItButton.setAlignment(Pos.CENTER);
        verticalPane.getChildren().add(doItButton);
        verticalPane.setPadding(new Insets(25, 25, 25, 25));

        Button generateCode = new Button();
        generateCode.setText("Generate");
        generateCode.setAlignment(Pos.CENTER);
        verticalPane.getChildren().add(generateCode);

        //checkbox' in horizontal grou[p
        CheckBox codeBox = new CheckBox("Code");
        codeBox.setSelected(true);
        CheckBox deCodeBox = new CheckBox("Decode");
        horizontalCheckBox.setAlignment(Pos.CENTER);

        horizontalCheckBox.getChildren().addAll(codeBox, deCodeBox);

        verticalPane.getChildren().add(horizontalCheckBox);

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

            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    Label buffer_label = new Label(Character.toString((char) (asci_char + (asci_count % 26)))); //25 % 10 = 5
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
        TextArea cryptTextArea = new TextArea("thisisatesttexttexttext");
        cryptTextArea.setPrefHeight(wight / 4);

        textAndKeyPane.getChildren().add(cryptTextArea);//addAll();

        //Key text area
        TextArea keyTextArea = new TextArea("solaris");
        keyTextArea.setPrefHeight(wight / 10);

        textAndKeyPane.getChildren().add(keyTextArea);

        rootPane.setTop(textAndKeyPane);

        StackPane textEncryptPane = new StackPane();

        TextArea encryptTextArea = new TextArea();
        textEncryptPane.getChildren().add(encryptTextArea);
        rootPane.setBottom(textEncryptPane);

        Scene scene = new Scene(rootPane);

        primaryStage.setScene(scene);

        primaryStage.show();

        generateCode.setOnAction(event -> {
            System.out.println("GENERATE button pressed");
            String key = keyTextArea.getText();
            String crypt = cryptTextArea.getText();
            String enCrypt = encryptTextArea.getText();

            if (keyTextArea.getText().length() == 0) {
                keyTextArea.setText("KeyTextWasNotSet");
            }

            if (cryptTextArea.getText().length() == 0) {
                cryptTextArea.setText("CrytpTextWasNotSet");
            }

            key = key.toLowerCase();
            crypt = crypt.toLowerCase();//make cryptotext right form

            key = fillKey(key, crypt); //also make key right form

            if (codeBox.isSelected() && deCodeBox.isSelected()) {
                deCodeBox.setSelected(false);
            } else {
                if (codeBox.isSelected()) {
                    encryptTextArea.setText(code(crypt, key));
                } else {
                    if (deCodeBox.isSelected()) {
                        cryptTextArea.setText(deCode(encryptTextArea.getText(), fillKey(key, encryptTextArea.getText())));
                    }
                }
            }

        });
    }

    //cut or fill key to needed size
    private String fillKey(String keyText, String cryptText) {

        String Skey = removePunct(keyText);
        String Scrypt = removePunct(cryptText);

        //bufkey less or the same length as crypt length
        //so we need key array was the same length as crypt
        char[] bufkey = Skey.toCharArray();//making 2 new objects
        char[] crypt = Scrypt.toCharArray();
        char[] key = Arrays.copyOf(bufkey, Scrypt.length()); //copy array without creating new obj

        int crypt_size = Scrypt.length();
        int key_size = Skey.length();

        //making key needed length
        if (key_size < crypt_size) {
            for (int i = 0; i < crypt_size - key_size; i++) {
                key[i + key_size] = key[i];
            }
        }

        String answer = String.valueOf(key); //cast to string to see it normally

        System.out.println("cryptText:" + Scrypt);
        System.out.println("fillKey:" + answer);

        return answer;
    }

    //returns already coded string
    private static String code(String message, String key) {
        char[] Cmessage = message.toCharArray();
        char[] Ckey = key.toCharArray();
        char[] Canswer = new char[Cmessage.length]; //our encoded char[]

        //код iscii символа в десятичной системе складываем по модулю 26
        //т.е. по количеству буков алфавита, и получаем код финального символа
        //обратное преобразование должно работать
        //get ascii symbol's code in decimal and summ it in mod26
        //will get another code of final char which we will cast to char
        //two-ways converting should work.
        for (int i = 0; i < Canswer.length; i++) {
            Canswer[i] = (char) ((int) 'a' + ((int) Cmessage[i] + (int) Ckey[i]) % 26); //cast some number to char to display it
            System.out.print((((int) Cmessage[i] + (int) Ckey[i]) % 26) + (int) 'a' + " ");//debug info
        }

        String answer = String.valueOf(Canswer);

        return answer;
    }

    private static String deCode(String encrypted_message, String key) {
        char[] Cmessage = encrypted_message.toCharArray();
        char[] Ckey = key.toCharArray();
        char[] Canswer = new char[Cmessage.length]; //our encoded char[]

        System.out.println("Cmessage len :" + Cmessage.length);
        System.out.println("ckey len :" + Ckey.length);
        System.out.println("Canswer len :" + Canswer.length);

        for (int i = 0; i < Cmessage.length; i++) {
            Canswer[i] = (char) (((int) 'a' + ((int) Cmessage[i] - (int) Ckey[i]) + 26) % 26); //cast some number to char to display it
            System.out.print((int) Canswer[i] + (int) 'a' + "-");//debug info

        }

        String answer = String.valueOf(Canswer);
        System.out.println("Answer is:" + answer);
        return answer;
    }

    //restricted symbols
    private static final String PUNCT = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~123456789";

    public static String removePunct(String str) {
        StringBuilder result = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (PUNCT.indexOf(c) < 0) { //getting index of first including of char in that string
                result.append(c);
            } else {
                result.append('*'); //if there are all symbols are restricted in string
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
