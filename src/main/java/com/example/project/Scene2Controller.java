/*
 * Overview: Implementation for the Scene2Controller class. This class controls
 *           the second scene presented in the Laser Tag game. This scene shows a Red and Green data entry tables
 *           with 15 rows each.
 * Purpose: To collect data from each player. Each table collects a unique ID, a code name, and team color for each player
 *          User inputs include: ID and code name only. The Team color is automatically collected depending on which table the user is
 *          putting their information.
 * @version 1.0
 * @since 11/04/22
 * */
package com.example.project;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.lang.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Scene2Controller  implements Initializable
{

    PlayerPersistenceInterface persistenceHandler = PersistenceHandler.getInstance();

    private Stage stage;

    private static Scene scene;

    Map<String, String> redMap = new HashMap<String, String>();

    Map<String, String> grnMap = new HashMap<String, String>();



    @FXML
    private TextField red_id1, red_id2,red_id3,red_id4,red_id5,red_id6,red_id7,
            red_id8,red_id9,red_id10,red_id11,red_id12,red_id13,red_id14, red_id15;

    @FXML
    private TextField red_name1,red_name2, red_name3, red_name4, red_name5,red_name6, red_name7, red_name8,
            red_name9, red_name10, red_name11, red_name12, red_name13, red_name14, red_name15;

    @FXML
    private TextField green_id1,green_id2,green_id3,green_id4,green_id5,green_id6,green_id7,
            green_id8,green_id9,green_id10,green_id11,green_id12,green_id13,green_id14, green_id15;

    @FXML
    private TextField green_name1,green_name2, green_name3, green_name4, green_name5,green_name6, green_name7, green_name8,
            green_name9, green_name10, green_name11, green_name12, green_name13, green_name14, green_name15;


    @FXML
    private Label welcomeText;

    // create a alert
    Alert a = new Alert(Alert.AlertType.NONE);

    @FXML
    void button (ActionEvent event) throws IOException
    {

        addContinue(event);
    }

    @FXML
    void keyEnter(KeyEvent event) throws  IOException
    {
        if (event.getCode() == KeyCode.SHIFT)
        {

            addContinue(event);

        }
    }


    int addContinue(Event event) throws  IOException
    {

        //putting red team data to a Map
        redMap.put((red_id1.getText()), red_name1.getText());
        red_id1.setText("Please insert a valid ID (only Numbers)!!");

        if (!((red_id2.getText() == "") || (red_id2.getText().contains(" ")) || (red_id2.getText().isEmpty()) || (red_id2.getText().chars().allMatch(Character::isWhitespace)) || (red_id2.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id2.getText().startsWith(";")) || (red_id2.getText().startsWith(".")) || (red_id2.getText().startsWith("/")) || ((red_id2.getText().startsWith("&"))) || (red_id2.getText().equals(null))))
        {
            if (!redMap.containsKey(red_id2.getText())) {
                redMap.put((red_id2.getText()), red_name2.getText());
            } else {
                System.out.println("Duplicate ID 2 Warning:Please enter a new ID in row 2!!");
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 2 Warning:Please enter a new ID at row 2!!");
                // show the dialog
                a.show();
                redMap.clear();
                return 0;
            }
        }

        if (!((red_id3.getText() == "") || (red_id3.getText().contains(" ")) || (red_id3.getText().isEmpty()) || (red_id3.getText().chars().allMatch(Character::isWhitespace)) || (red_id3.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id3.getText().startsWith(";")) || (red_id3.getText().startsWith(".")) || (red_id3.getText().startsWith("/")) || ((red_id3.getText().startsWith("&"))) || (red_id3.getText().equals(null))))
        {
            if (!redMap.containsKey(red_id3.getText())) {
                redMap.put((red_id3.getText()), red_name3.getText());
            } else {
                System.out.println("Duplicate ID3 Warning:Please enter a new ID!!");
                redMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 3 Warning:Please enter a new ID at row 3!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if (!((red_id4.getText() == "") || (red_id4.getText().contains(" ")) || (red_id4.getText().isEmpty()) || (red_id4.getText().chars().allMatch(Character::isWhitespace)) || (red_id4.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id4.getText().startsWith(";")) || (red_id4.getText().startsWith(".")) || (red_id4.getText().startsWith("/")) || ((red_id4.getText().startsWith("&"))) || (red_id4.getText().equals(null))))
        {
            if (!redMap.containsKey(red_id4.getText())) {
                redMap.put((red_id4.getText()), red_name4.getText());
            } else {
                System.out.println("Duplicate ID 4 Warning:Please enter a new ID!!");
                redMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 4 Warning:Please enter a new ID at row 4!!");
                // show the dialog
                a.show();
                return 0;
            }
        }


        if(!((red_id5.getText() == "")||(red_id5.getText().contains(" ")) || (red_id5.getText().isEmpty())|| (red_id5.getText().chars().allMatch(Character::isWhitespace)) || (red_id5.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id5.getText().startsWith(";"))||(red_id5.getText().startsWith("."))||(red_id5.getText().startsWith("/")) || ((red_id5.getText().startsWith("&"))) || (red_id5.getText().equals(null))))
        {
            if(!redMap.containsKey(red_id5.getText())){
                redMap.put((red_id5.getText()), red_name5.getText());
            }else{
                System.out.println("Duplicate ID5 Warning:Please enter a new ID!!");
                redMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 5 Warning:Please enter a new ID at row 5!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((red_id6.getText() == "")||(red_id6.getText().contains(" ")) || (red_id6.getText().isEmpty())|| (red_id6.getText().chars().allMatch(Character::isWhitespace)) || (red_id6.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id6.getText().startsWith(";"))||(red_id6.getText().startsWith("."))||(red_id6.getText().startsWith("/")) || ((red_id6.getText().startsWith("&"))) || (red_id6.getText().equals(null))))
        {
            if(!redMap.containsKey(red_id6.getText())){
                redMap.put((red_id6.getText()), red_name6.getText());
            }else{
                System.out.println("Duplicate ID 6 Warning:Please enter a new ID!!");
                redMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 6 Warning:Please enter a new ID at row 6!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((red_id7.getText() == "")||(red_id7.getText().contains(" ")) || (red_id7.getText().isEmpty())|| (red_id7.getText().chars().allMatch(Character::isWhitespace)) || (red_id7.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id7.getText().startsWith(";"))||(red_id7.getText().startsWith("."))||(red_id7.getText().startsWith("/")) || ((red_id7.getText().startsWith("&"))) || (red_id7.getText().equals(null))))
        {
            if(!redMap.containsKey(red_id7.getText())){
                redMap.put((red_id7.getText()), red_name7.getText());
            }else{
                System.out.println("Duplicate ID7 Warning:Please enter a new ID!!");
                redMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 7 Warning:Please enter a new ID at row 7!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((red_id8.getText() == "")||(red_id8.getText().contains(" ")) || (red_id8.getText().isEmpty())|| (red_id8.getText().chars().allMatch(Character::isWhitespace)) || (red_id8.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id8.getText().startsWith(";"))||(red_id8.getText().startsWith("."))||(red_id8.getText().startsWith("/")) || ((red_id8.getText().startsWith("&"))) || (red_id8.getText().equals(null))))
        {
            if(!redMap.containsKey(red_id8.getText())){
                redMap.put((red_id8.getText()), red_name8.getText());
            }else{
                System.out.println("Duplicate ID8 Warning:Please enter a new ID!!");
                redMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 8 Warning:Please enter a new ID at row 8!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((red_id9.getText() == "")||(red_id9.getText().contains(" ")) || (red_id9.getText().isEmpty())|| (red_id9.getText().chars().allMatch(Character::isWhitespace)) || (red_id9.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id9.getText().startsWith(";"))||(red_id9.getText().startsWith("."))||(red_id9.getText().startsWith("/")) || ((red_id9.getText().startsWith("&"))) || (red_id9.getText().equals(null))))
        {
            if(!redMap.containsKey(red_id9.getText())){
                redMap.put((red_id9.getText()), red_name9.getText());
            }else{
                System.out.println("Duplicate ID9 Warning:Please enter a new ID!!");
                redMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 9 Warning:Please enter a new ID at row 9!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((red_id10.getText() == "")||(red_id10.getText().contains(" ")) || (red_id10.getText().isEmpty())|| (red_id10.getText().chars().allMatch(Character::isWhitespace)) || (red_id10.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id10.getText().startsWith(";"))||(red_id10.getText().startsWith("."))||(red_id10.getText().startsWith("/")) || ((red_id10.getText().startsWith("&"))) || (red_id10.getText().equals(null))))
        {
            if(!redMap.containsKey(red_id10.getText())){
                redMap.put((red_id10.getText()), red_name10.getText());
            }else{
                System.out.println("Duplicate ID 10 Warning:Please enter a new ID!!");
                redMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 10 Warning:Please enter a new ID at row 10!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((red_id11.getText() == "")||(red_id11.getText().contains(" ")) || (red_id11.getText().isEmpty())|| (red_id11.getText().chars().allMatch(Character::isWhitespace)) || (red_id11.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id11.getText().startsWith(";"))||(red_id11.getText().startsWith("."))||(red_id11.getText().startsWith("/")) || ((red_id11.getText().startsWith("&"))) || (red_id11.getText().equals(null))))
        {
            if(!redMap.containsKey(red_id11.getText())){
                redMap.put((red_id11.getText()), red_name11.getText());
            }else{
                System.out.println("Duplicate ID 11 Warning:Please enter a new ID!!");
                redMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 11 Warning:Please enter a new ID at row 11!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((red_id12.getText() == "")||(red_id12.getText().contains(" ")) || (red_id12.getText().isEmpty())|| (red_id12.getText().chars().allMatch(Character::isWhitespace)) || (red_id12.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id12.getText().startsWith(";"))||(red_id12.getText().startsWith("."))||(red_id12.getText().startsWith("/")) || ((red_id12.getText().startsWith("&"))) || (red_id12.getText().equals(null))))
        {
            if(!redMap.containsKey(red_id12.getText())){
                redMap.put((red_id12.getText()), red_name12.getText());
            }else{
                System.out.println("Duplicate ID 12 Warning:Please enter a new ID!!");
                redMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 12 Warning:Please enter a new ID at row 12!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((red_id13.getText() == "")||(red_id13.getText().contains(" ")) || (red_id13.getText().isEmpty())|| (red_id13.getText().chars().allMatch(Character::isWhitespace)) || (red_id13.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id13.getText().startsWith(";"))||(red_id13.getText().startsWith("."))||(red_id13.getText().startsWith("/")) || ((red_id13.getText().startsWith("&"))) || (red_id13.getText().equals(null))))
        {
            if(!redMap.containsKey(red_id13.getText())){
                redMap.put((red_id13.getText()), red_name13.getText());
            }else{
                System.out.println("Duplicate ID 13 Warning:Please enter a new ID!!");
                redMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 13 Warning:Please enter a new ID at row 13!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((red_id14.getText() == "")||(red_id14.getText().contains(" ")) || (red_id14.getText().isEmpty())|| (red_id14.getText().chars().allMatch(Character::isWhitespace)) || (red_id14.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id14.getText().startsWith(";"))||(red_id14.getText().startsWith("."))||(red_id14.getText().startsWith("/")) || ((red_id14.getText().startsWith("&"))) || (red_id14.getText().equals(null))))
        {
            if(!redMap.containsKey(red_id14.getText())){
                redMap.put((red_id14.getText()), red_name14.getText());
            }else{
                System.out.println("Duplicate ID 14 Warning:Please enter a new ID!!");
                redMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 14 Warning:Please enter a new ID at row 14!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((red_id15.getText() == "")||(red_id15.getText().contains(" ")) || (red_id15.getText().isEmpty())|| (red_id15.getText().chars().allMatch(Character::isWhitespace)) || (red_id15.getText().chars().anyMatch(Character::isWhitespace)) ||
                (red_id15.getText().startsWith(";"))||(red_id15.getText().startsWith("."))||(red_id15.getText().startsWith("/")) || ((red_id15.getText().startsWith("&"))) || (red_id15.getText().equals(null))))
        {
            if(!redMap.containsKey(red_id15.getText())){
                redMap.put((red_id15.getText()), red_name15.getText());
            }else{
                System.out.println("Duplicate ID 15 Warning:Please enter a new ID!!");
                redMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 15 Warning:Please enter a new ID at row 15!!");
                // show the dialog
                a.show();
                return 0;
            }
        }


        // using for-each loop for iteration over Map.entrySet()
        for (Map.Entry<String,String> entry : redMap.entrySet()) {

            Player red_player = new Player();

            if (!((entry.getKey() == "")||(entry.getKey().contains(" ")) || (entry.getKey().isEmpty())|| (entry.getKey().chars().allMatch(Character::isWhitespace)) || (entry.getKey().chars().anyMatch(Character::isWhitespace)) ||
                    (entry.getKey().startsWith(";"))||(entry.getKey().startsWith("."))||(entry.getKey().startsWith("/")) || ((entry.getKey().startsWith("&"))) || (entry.getKey().equals(null)))) {
                //setting id and codename for red team
                red_player.setId(Integer.parseInt(entry.getKey()));
                red_player.setCodename(entry.getValue());
                //Calling createPlayer method to insert red team data to Database
                if (persistenceHandler.createRedPlayer(red_player)) {
                    System.out.println("Player inserted into database!!");
                } else {
                    System.out.println("Error while inserting player into database!!");
                }
            }
        }

        //putting Green team data to a Map
        grnMap.put((green_id1.getText()), green_name1.getText());
        green_id1.setText("Please insert a valid ID (only Numbers)!!");

        if (!((green_id2.getText() == "") || (green_id2.getText().contains(" ")) || (green_id2.getText().isEmpty()) || (green_id2.getText().chars().allMatch(Character::isWhitespace)) || (green_id2.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id2.getText().startsWith(";")) || (green_id2.getText().startsWith(".")) || (green_id2.getText().startsWith("/")) || ((green_id2.getText().startsWith("&"))) || (green_id2.getText().equals(null))))
        {
            if (!grnMap.containsKey(green_id2.getText())) {
                grnMap.put((green_id2.getText()), green_name2.getText());
            } else {
                System.out.println("Duplicate ID 2 Warning:Please enter a new ID in row 2!!");
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 2 Warning:Please enter a new ID at row 2!!");
                // show the dialog
                a.show();
                grnMap.clear();
                return 0;
            }
        }

        if (!((green_id3.getText() == "") || (green_id3.getText().contains(" ")) || (green_id3.getText().isEmpty()) || (green_id3.getText().chars().allMatch(Character::isWhitespace)) || (green_id3.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id3.getText().startsWith(";")) || (green_id3.getText().startsWith(".")) || (green_id3.getText().startsWith("/")) || ((green_id3.getText().startsWith("&"))) || (green_id3.getText().equals(null))))
        {
            if (!grnMap.containsKey(green_id3.getText())) {
                grnMap.put((green_id3.getText()), green_name3.getText());
            } else {
                System.out.println("Duplicate ID3 Warning:Please enter a new ID!!");
                grnMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 3 Warning:Please enter a new ID at row 3!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if (!((green_id4.getText() == "") || (green_id4.getText().contains(" ")) || (green_id4.getText().isEmpty()) || (green_id4.getText().chars().allMatch(Character::isWhitespace)) || (green_id4.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id4.getText().startsWith(";")) || (green_id4.getText().startsWith(".")) || (green_id4.getText().startsWith("/")) || ((green_id4.getText().startsWith("&"))) || (green_id4.getText().equals(null))))
        {
            if (!grnMap.containsKey(green_id4.getText())) {
                grnMap.put((green_id4.getText()), green_name4.getText());
            } else {
                System.out.println("Duplicate ID 4 Warning:Please enter a new ID!!");
                grnMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 4 Warning:Please enter a new ID at row 4!!");
                // show the dialog
                a.show();
                return 0;
            }
        }


        if(!((green_id5.getText() == "")||(green_id5.getText().contains(" ")) || (green_id5.getText().isEmpty())|| (green_id5.getText().chars().allMatch(Character::isWhitespace)) || (green_id5.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id5.getText().startsWith(";"))||(green_id5.getText().startsWith("."))||(green_id5.getText().startsWith("/")) || ((green_id5.getText().startsWith("&"))) || (green_id5.getText().equals(null))))
        {
            if(!grnMap.containsKey(green_id5.getText())){
                grnMap.put((green_id5.getText()), green_name5.getText());
            }else{
                System.out.println("Duplicate ID5 Warning:Please enter a new ID!!");
                grnMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 5 Warning:Please enter a new ID at row 5!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((green_id6.getText() == "")||(green_id6.getText().contains(" ")) || (green_id6.getText().isEmpty())|| (green_id6.getText().chars().allMatch(Character::isWhitespace)) || (green_id6.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id6.getText().startsWith(";"))||(green_id6.getText().startsWith("."))||(green_id6.getText().startsWith("/")) || ((green_id6.getText().startsWith("&"))) || (green_id6.getText().equals(null))))
        {
            if(!grnMap.containsKey(green_id6.getText())){
                grnMap.put((green_id6.getText()), green_name6.getText());
            }else{
                System.out.println("Duplicate ID 6 Warning:Please enter a new ID!!");
                grnMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 6 Warning:Please enter a new ID at row 6!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((green_id7.getText() == "")||(green_id7.getText().contains(" ")) || (green_id7.getText().isEmpty())|| (green_id7.getText().chars().allMatch(Character::isWhitespace)) || (green_id7.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id7.getText().startsWith(";"))||(green_id7.getText().startsWith("."))||(green_id7.getText().startsWith("/")) || ((green_id7.getText().startsWith("&"))) || (green_id7.getText().equals(null))))
        {
            if(!grnMap.containsKey(green_id7.getText())){
                grnMap.put((green_id7.getText()), green_name7.getText());
            }else{
                System.out.println("Duplicate ID7 Warning:Please enter a new ID!!");
                grnMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 7 Warning:Please enter a new ID at row 7!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((green_id8.getText() == "")||(green_id8.getText().contains(" ")) || (green_id8.getText().isEmpty())|| (green_id8.getText().chars().allMatch(Character::isWhitespace)) || (green_id8.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id8.getText().startsWith(";"))||(green_id8.getText().startsWith("."))||(green_id8.getText().startsWith("/")) || ((green_id8.getText().startsWith("&"))) || (green_id8.getText().equals(null))))
        {
            if(!grnMap.containsKey(green_id8.getText())){
                grnMap.put((green_id8.getText()), green_name8.getText());
            }else{
                System.out.println("Duplicate ID8 Warning:Please enter a new ID!!");
                grnMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 8 Warning:Please enter a new ID at row 8!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((green_id9.getText() == "")||(green_id9.getText().contains(" ")) || (green_id9.getText().isEmpty())|| (green_id9.getText().chars().allMatch(Character::isWhitespace)) || (green_id9.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id9.getText().startsWith(";"))||(green_id9.getText().startsWith("."))||(green_id9.getText().startsWith("/")) || ((green_id9.getText().startsWith("&"))) || (green_id9.getText().equals(null))))
        {
            if(!grnMap.containsKey(green_id9.getText())){
                grnMap.put((green_id9.getText()), green_name9.getText());
            }else{
                System.out.println("Duplicate ID9 Warning:Please enter a new ID!!");
                grnMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 9 Warning:Please enter a new ID at row 9!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((green_id10.getText() == "")||(green_id10.getText().contains(" ")) || (green_id10.getText().isEmpty())|| (green_id10.getText().chars().allMatch(Character::isWhitespace)) || (green_id10.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id10.getText().startsWith(";"))||(green_id10.getText().startsWith("."))||(green_id10.getText().startsWith("/")) || ((green_id10.getText().startsWith("&"))) || (green_id10.getText().equals(null))))
        {
            if(!grnMap.containsKey(green_id10.getText())){
                grnMap.put((green_id10.getText()), green_name10.getText());
            }else{
                System.out.println("Duplicate ID 10 Warning:Please enter a new ID!!");
                grnMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 10 Warning:Please enter a new ID at row 10!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((green_id11.getText() == "")||(green_id11.getText().contains(" ")) || (green_id11.getText().isEmpty())|| (green_id11.getText().chars().allMatch(Character::isWhitespace)) || (green_id11.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id11.getText().startsWith(";"))||(green_id11.getText().startsWith("."))||(green_id11.getText().startsWith("/")) || ((green_id11.getText().startsWith("&"))) || (green_id11.getText().equals(null))))
        {
            if(!grnMap.containsKey(green_id11.getText())){
                grnMap.put((green_id11.getText()), green_name11.getText());
            }else{
                System.out.println("Duplicate ID 11 Warning:Please enter a new ID!!");
                grnMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 11 Warning:Please enter a new ID at row 11!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((green_id12.getText() == "")||(green_id12.getText().contains(" ")) || (green_id12.getText().isEmpty())|| (green_id12.getText().chars().allMatch(Character::isWhitespace)) || (green_id12.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id12.getText().startsWith(";"))||(green_id12.getText().startsWith("."))||(green_id12.getText().startsWith("/")) || ((green_id12.getText().startsWith("&"))) || (green_id12.getText().equals(null))))
        {
            if(!grnMap.containsKey(green_id12.getText())){
                grnMap.put((green_id12.getText()), green_name12.getText());
            }else{
                System.out.println("Duplicate ID 12 Warning:Please enter a new ID!!");
                grnMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 12 Warning:Please enter a new ID at row 12!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((green_id13.getText() == "")||(green_id13.getText().contains(" ")) || (green_id13.getText().isEmpty())|| (green_id13.getText().chars().allMatch(Character::isWhitespace)) || (green_id13.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id13.getText().startsWith(";"))||(green_id13.getText().startsWith("."))||(green_id13.getText().startsWith("/")) || ((green_id13.getText().startsWith("&"))) || (green_id13.getText().equals(null))))
        {
            if(!grnMap.containsKey(green_id13.getText())){
                grnMap.put((green_id13.getText()), green_name13.getText());
            }else{
                System.out.println("Duplicate ID 13 Warning:Please enter a new ID!!");
                grnMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 13 Warning:Please enter a new ID at row 13!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((green_id14.getText() == "")||(green_id14.getText().contains(" ")) || (green_id14.getText().isEmpty())|| (green_id14.getText().chars().allMatch(Character::isWhitespace)) || (green_id14.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id14.getText().startsWith(";"))||(green_id14.getText().startsWith("."))||(green_id14.getText().startsWith("/")) || ((green_id14.getText().startsWith("&"))) || (green_id14.getText().equals(null))))
        {
            if(!grnMap.containsKey(green_id14.getText())){
                grnMap.put((green_id14.getText()), green_name14.getText());
            }else{
                System.out.println("Duplicate ID 14 Warning:Please enter a new ID!!");
                grnMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 14 Warning:Please enter a new ID at row 14!!");
                // show the dialog
                a.show();
                return 0;
            }
        }

        if(!((green_id15.getText() == "")||(green_id15.getText().contains(" ")) || (green_id15.getText().isEmpty())|| (green_id15.getText().chars().allMatch(Character::isWhitespace)) || (green_id15.getText().chars().anyMatch(Character::isWhitespace)) ||
                (green_id15.getText().startsWith(";"))||(green_id15.getText().startsWith("."))||(green_id15.getText().startsWith("/")) || ((green_id15.getText().startsWith("&"))) || (green_id15.getText().equals(null))))
        {
            if(!grnMap.containsKey(green_id15.getText())){
                grnMap.put((green_id15.getText()), green_name15.getText());
            }else{
                System.out.println("Duplicate ID 15 Warning:Please enter a new ID!!");
                grnMap.clear();
                // set alert type
                a.setAlertType(Alert.AlertType.WARNING);
                // set content text
                a.setContentText("Duplicate ID 15 Warning:Please enter a new ID at row 15!!");
                // show the dialog
                a.show();
                return 0;
            }
        }


        // using for-each loop for iteration over Map.entrySet()
        for (Map.Entry<String,String> entry : grnMap.entrySet()) {

            Player green_player = new Player();

            if (!((entry.getKey() == "")||(entry.getKey().contains(" ")) || (entry.getKey().isEmpty())|| (entry.getKey().chars().allMatch(Character::isWhitespace)) || (entry.getKey().chars().anyMatch(Character::isWhitespace)) ||
                    (entry.getKey().startsWith(";"))||(entry.getKey().startsWith("."))||(entry.getKey().startsWith("/")) || ((entry.getKey().startsWith("&"))) || (entry.getKey().equals(null)))) {
                //setting id and codename for green team
                green_player.setId(Integer.parseInt(entry.getKey()));
                green_player.setCodename(entry.getValue());
                //Calling createPlayer method to insert Green_team data to Database
                if (persistenceHandler.createGreenPlayer(green_player)) {
                    System.out.println("Green Player inserted into database!!");
                } else {
                    System.out.println("Error while inserting player into database!!");
                }
            }
        }



        System.setProperty("javafx.preloader", HelloPreloader.class.getCanonicalName());

        Parent root = FXMLLoader.load(getClass().getResource("GameAction.fxml"));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

        updateUI();

        return 0;
    }

    private void updateUI(){
        red_id1.clear();red_id2.clear();red_id3.clear();red_id4.clear();red_id5.clear();
        red_id6.clear();red_id7.clear();red_id8.clear();red_id9.clear();red_id10.clear();
        red_id11.clear();red_id12.clear();red_id13.clear();red_id14.clear();red_id15.clear();

        red_name1.clear();red_name2.clear();red_name3.clear();red_name4.clear();red_name5.clear();
        red_name6.clear();red_name7.clear();red_name8.clear();red_name9.clear();red_name10.clear();
        red_name11.clear();red_name12.clear();red_name13.clear();red_name14.clear();red_name15.clear();

        green_id1.clear();green_id2.clear();green_id3.clear();green_id4.clear();green_id5.clear();
        green_id6.clear();green_id7.clear();green_id8.clear();green_id9.clear();green_id10.clear();
        green_id11.clear();green_id12.clear();green_id13.clear();green_id14.clear();green_id15.clear();

        green_name1.clear();green_name2.clear();green_name3.clear();green_name4.clear();green_name5.clear();
        green_name6.clear();green_name7.clear();green_name8.clear();green_name9.clear();green_name10.clear();
        green_name11.clear();green_name12.clear();green_name13.clear();green_name14.clear();green_name15.clear();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public Scene2Controller() {
    }

    @FXML

    public void EditGame (ActionEvent event) throws IOException
    {

    }
    public void StartGame (ActionEvent event) throws IOException
    {
//        System.setProperty("javafx.preloader", HelloPreloader.class.getCanonicalName());
//
//        Parent root = FXMLLoader.load(getClass().getResource("GameAction.fxml"));
//
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//        scene = new Scene(root);
//
//        stage.setScene(scene);
//
//        stage.show();
    }
    public void ViewGame (ActionEvent event) throws IOException
    {

    }
    public void PreEnteredGames (ActionEvent event) throws IOException
    {

    }
    public void FlickSync (ActionEvent event) throws IOException
    {

    }

    public void ClearGame (ActionEvent event) throws IOException
    {

    }

    public class Controller
    {
        public void move_up ()
        {

        }
        public void move_down()
        {

        }
        public void move_left ()
        {

        }
        public void move_right()
        {

        }
    }
    public void closeGame(ActionEvent event) throws IOException
    {
        System.exit(0);

    }

}