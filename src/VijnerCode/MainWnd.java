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
        
        StackPane buttonPane = new StackPane();//центрирует компоненты внутри себя
        
        HBox verticalPane = new HBox(); //ставит компоненты вертикально
        
        Button doItButton = new Button();
        doItButton.setText("Table example");
        doItButton.setAlignment(Pos.CENTER);
        verticalPane.getChildren().add(doItButton);
        verticalPane.setPadding(new Insets(5, 25, 5, 25));//отступы между элементами
        buttonPane.getChildren().add(verticalPane);
        
        rootPane.setCenter(buttonPane);
 
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
             
        StackPane textCryptPane = new StackPane();
        textCryptPane.getChildren().add(new TextArea("CrytpText"));
        rootPane.setTop(textCryptPane);
        
        StackPane textEncryptPane = new StackPane();
        textEncryptPane.getChildren().add(new TextArea("EncryptedText"));
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
    
    private static char findSymbol(String codeString, String code_key){
        //находим символ, соответствующий букве из шифрокода
        //примерно - поиск индекса символа в одномерном массиве и 26 букв пендосского алфавита
        //нужен поиск 2 индексов - два раза для i и j
        
        //запуск функции для подмены символа в шифротексте на символ[i,j] из таблицы вижнера
        return (char) 127; //127 - аски символ крышечки.
    }
    
    private static char changeSymbol(String codeString, int i,int j, char code_char){

        return (char) 127; //see below about 127
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}