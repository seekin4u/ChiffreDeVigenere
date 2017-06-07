/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VijnerCode;

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
import javafx.scene.layout.Pane;
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
 * НЕ сложный проект, но это интереснее чем копировать чужой код
 * Или просто пытаться в нем замести следы того, что ргз не твое :^)
 * 
 * Так же, у меня монитор в ~26 дюймов, так что комментарии будут длинными, сорянити.
 */

/*
*Шифторекст - зашифрованный с помощью ключа текст
Ключ шифрования/ключ (code_key - в этом примере) - ключ, с пом. которого из шифротекста
получаем расшифрованное сообщение - текст/сообщение

пример шифрования -
сообщение - мне не нравится писать на джаве
ключ - требуют наши сердца

копируем ключ столько раз, сколько необходимо, что бы длина ключа была равной длине сообщения
обрезаем при надобности
прим :
мне не нравится писать на джаве
пер ем ентребую тнашис ер дцапе - и дальше ключ обрезали.
i love write in java - аналог не великой мове
w enee dchan ge swen
Находим букву М в строке, находим соответствующую ей букву п в столбце и смотрим на символ, который пересекают столбец и строка, и записываем его

получаем :

e pbzi *******(дальше лень)
сопостовляем индекс буквы текста с индексом буквы в ключе, шифруем/дешифруем.

(а еще это уже готовый квадрат, для наглядности можно по дорисовать по алфавиту слева и справа
Как на кратинке по ссылке в оглавлении
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
        
        HBox verticalPane = new HBox(); //ставит компоненты вертикально
        
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
        
        generateCode.setOnAction(event -> {
            System.out.println("GENERATE button pressed");
            
        });
        
        doItButton.setOnAction(event -> {
            System.out.println("BATON crytp pressed");
            Stage GridWnd = new Stage();

            GridWnd.setTitle("Table");
            GridWnd.setWidth(550);//магические числа, которые почти идеально подходят для этого окна.
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
                    grid.add(buffer_label, i, j, 1, 1); //индекс строки, индекс столбца, количество ячеек(пикселей?) между занятыми , котоыре имеют индекс                
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
        TextArea cryptTextArea = new TextArea("");
        cryptTextArea.setPrefHeight(wight / 4);
        
        textAndKeyPane.getChildren().add(cryptTextArea);//addAll();
        
        //Key text area
        TextArea keyTextArea = new TextArea("");
        keyTextArea.setPrefHeight(wight / 10);
        
        textAndKeyPane.getChildren().add(keyTextArea);
        
        rootPane.setTop(textAndKeyPane);
        
        StackPane textEncryptPane = new StackPane();
        textEncryptPane.getChildren().add(new TextArea(""));
        rootPane.setBottom(textEncryptPane);
        
        Scene scene = new Scene(rootPane);
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    private static void code(String[][] vjTable, String message, String code_key){
        //nothing yet;
        //используем таблицу Вижнера, сгенерированную где-то ранее, что бы
        //кодировать сообщение message c помощью ключа code_key
    }
    
    private static void decode(String[][] vjTable, String message, String code_key){
        //nothing yet
        //используем таблицу, что бы декодировать сообщение message
        //с помощью ключа code_key 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}