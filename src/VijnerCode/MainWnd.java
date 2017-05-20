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
    public static final int wight = 650;
    public static final int heigth = 900;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Vijner Crypt App");
        primaryStage.setWidth(wight / 2);
        primaryStage.setHeight(heigth / 2);
        
        VBox rootPane = new VBox();
        
        StackPane buttonPane = new StackPane();
        Button doItButton = new Button();
        doItButton.setText("Crypt\\Decrypt");
        buttonPane.getChildren().add(doItButton);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(25, 25, 10, 25));
 
        doItButton.setOnAction(event -> {
            System.out.println("BATON crytp pressed");
            Stage GridWnd = new Stage();
            ///\
            GridWnd.setTitle("123456789");
            GridWnd.setWidth(wight);
            GridWnd.setHeight(heigth);
            
            VBox root = new VBox();
            
            GridPane grid = new GridPane();
            //grid.setAlignment(Pos.CENTER);
            grid.setHgap(5);//количество Horizontal и VErtical пикселей между ячейками таблицы - больше - не расплываются лэйблы
            grid.setVgap(5);
            grid.setVisible(true);
            grid.setPadding(new Insets(25, 25, 25, 25));            
            
            Label buffer_label = new Label("*");
        
            String buffer;
        
            int asci_char = 65;
            int asci_count = 0;
        
            String[][] vij_table = new String[27][27];
        
            for(int i = 0; i < 26; i ++){
                for(int j = 0; j< 26; j++)
                {
                    buffer = Character.toString((char) (asci_char + ( asci_count % 26) ) );
                    //генерация кода символа, учитывая его смещение от 65 символа
                    //                
                    buffer_label = new Label(buffer); //25 % 10 = 5
                    grid.add(buffer_label, i, j, 1, 1); //индекс строки, индекс столбца, количество ячеек(пикселей?) между занятыми , котоыре имеют индекс
                    vij_table[i][j] = buffer;
                
                    asci_count++;
                }
                asci_count++;
            }
        
            //root.getChildren().add(grid);
            
            Scene scene = new Scene(root);
            
            GridWnd.initOwner(primaryStage);
            GridWnd.initModality(Modality.APPLICATION_MODAL);
            GridWnd.showAndWait();
        });
        
        rootPane.getChildren().add(buttonPane);
        
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
        return (char) 127; //127 - аски символ крышечки. Такой символ не будет поддерживаться (в теории даже удаляться из строки)
    }
    
    private static char changeSymbol(String codeString, int i,int j, char code_char){
        //меняем в шифротексте символ зашифрованный на соответствующий символ из таблицы
        //
        return (char) 127; //see below about 127
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}